package guiNhanVienBanVe;

import javax.swing.*;

import connectDB.ConnectDB;
import dao.ChuyenTauDAO;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entity.Ve;
import entity.ChuyenTau;
import entity.Tau;
import entity.LoaiVe;

public class DatVeFrame extends JFrame {
    private Sidebar sidebar;
    private JPanel contentPanel;
    private RightPanel rightPanel;
    private JPanel bookingPanel;
    private JPanel ticketListPanel;
    
    private ChuyenTauDAO chuyenTauDAO;
    
    private static final String[] DS_GA_VIETNAM = { "Sài gòn", "Bình Thuận", "Phan Thiết", "Nha Trang", "Quy Nhơn",
			"Đà Nẵng", "Huế", "Vinh", "Thanh Hóa", "Nam Định", "Hà Nội", "Hải Phòng", "Phú Thọ", "Yên Bái", "Lào Cai" };

    public DatVeFrame() {
    	ConnectDB.getInstance().connect();
    	chuyenTauDAO = new ChuyenTauDAO();
    	
        setTitle("Giao diện đặt vé");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        sidebar = new Sidebar(this);
        add(sidebar, BorderLayout.WEST);

        rightPanel = new RightPanel();
        rightPanel.setVisible(false);
        add(rightPanel, BorderLayout.EAST);

        // TopBar
        TopBar topBar = new TopBar(sidebar, rightPanel);
        add(topBar, BorderLayout.NORTH);

        // Main container
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.WHITE);
        add(contentPanel, BorderLayout.CENTER);

        // === Booking View Panel ===
        bookingPanel = new JPanel();
        bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
        bookingPanel.setBackground(Color.WHITE);

        BookingFirstView bookingForm = new BookingFirstView(() -> showTicketList());
        bookingForm.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingPanel.add(Box.createVerticalStrut(20));
        bookingPanel.add(bookingForm);
        bookingPanel.add(Box.createVerticalStrut(20));

        // === Ticket List Panel ===
        ticketListPanel = new JPanel();
        ticketListPanel.setLayout(new BoxLayout(ticketListPanel, BoxLayout.Y_AXIS));
        ticketListPanel.setBackground(Color.WHITE);
        ticketListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(bookingPanel, "BOOKING");
        contentPanel.add(new JScrollPane(ticketListPanel), "TICKET_LIST");

        showBookingForm();
    }

    public void showBookingForm() {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "BOOKING");
    }

    private void showTicketList() {
        ticketListPanel.removeAll(); // Xóa dữ liệu cũ

        try {
            // Tạo dữ liệu giả thay vì lấy từ database
            List<Ve> danhSachVe = taoDuLieuGiaVe();
            
            if (danhSachVe.isEmpty()) {
                JLabel emptyLabel = new JLabel("Không có vé tàu nào được tìm thấy.");
                emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                ticketListPanel.add(emptyLabel);
            } else {
                for (Ve ve : danhSachVe) {
                    TicketCard card = new TicketCard(ve);
                    ticketListPanel.add(card);
                    ticketListPanel.add(Box.createVerticalStrut(10));
                }
            }
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Đã xảy ra lỗi khi tải dữ liệu vé tàu: " + e.getMessage());
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            ticketListPanel.add(errorLabel);
            e.printStackTrace();
        }

        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "TICKET_LIST");

        ticketListPanel.revalidate();
        ticketListPanel.repaint();
    }

    // Phương thức tạo dữ liệu giả cho vé
    private List<Ve> taoDuLieuGiaVe() {
        List<Ve> danhSachVe = new ArrayList<>();
        BookingFirstView bookingFirstView = new BookingFirstView(null);
        
        int i = 1;
        String gaDi = bookingFirstView.getGaDi();
		String gaDen = bookingFirstView.getGaDen();
		
		LocalDateTime ngayKhoiHanh = bookingFirstView.getNgayDi();
//		LocalDateTime ngayKetThuc = bookingFirstView.getNgayVe();

		// Gọi phương thức tìm kiếm chuyến tàu
		ArrayList<ChuyenTau> chuyenTaus = chuyenTauDAO.timKiemChuyenTauMotChieu(gaDi, gaDen, ngayKhoiHanh);
		System.out.println(chuyenTaus);
        for (ChuyenTau chuyenTau : chuyenTaus) {
        	Ve ve = new Ve();
            ve.setMaVe("V00" + i);
            ve.setGiaVe(100000 + i * 50000);
//            ve.setNgayDat(LocalDateTime.now().minusDays(i));
            
            ve.setChuyenTau(chuyenTau);
            
//            Tau tau = new Tau();
//            tau.setMaTau("T00" + i);
//            tau.setTenTau("Tàu SE" + i);
//            chuyenTau.setTau(tau);
            
            LoaiVe loaiVe = LoaiVe.MOT_CHIEU;
//            loaiVe.setMaLoai("LV00" + i);
//            loaiVe.setTenLoai(i == 1 ? "Ngồi mềm" : (i == 2 ? "Nằm khoang 4" : "Nằm khoang 6"));
            ve.setLoaiVe(loaiVe);
            
            danhSachVe.add(ve);
            
            i++;
		}
//        for (int i = 1; i <= 3; i++) {
//            Ve ve = new Ve();
//            ve.setMaVe("V00" + i);
//            ve.setGiaVe(100000 + i * 50000);
////            ve.setNgayDat(LocalDateTime.now().minusDays(i));
//            
//            ChuyenTau chuyenTau = new ChuyenTau();
//            chuyenTau.setMaChuyenTau("CT00" + i);
////            chuyenTau.setTenChuyenTau("Hà Nội - Sài Gòn " + i);
//            chuyenTau.setNgayKhoiHanh(LocalDateTime.now().plusDays(i));
//            ve.setChuyenTau(chuyenTau);
//            
//            Tau tau = new Tau();
//            tau.setMaTau("T00" + i);
//            tau.setTenTau("Tàu SE" + i);
//            chuyenTau.setTau(tau);
//            
//            LoaiVe loaiVe = LoaiVe.MOT_CHIEU;
////            loaiVe.setMaLoai("LV00" + i);
////            loaiVe.setTenLoai(i == 1 ? "Ngồi mềm" : (i == 2 ? "Nằm khoang 4" : "Nằm khoang 6"));
//            ve.setLoaiVe(loaiVe);
//            
//            danhSachVe.add(ve);
//        }
        
        return danhSachVe;
    }
}