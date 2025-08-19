package guiNhanVIenQuanLy;

import entity.ChuyenTau;
import entity.Tau;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import dao.ChuyenTauDAO;
import dao.TauDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class CapNhatChuyenTauDialog extends JDialog {
    private JComboBox<String> cboTau;
    private JComboBox<String> cboGaDi, cboGaDen;
    private JTextField txtMaTau, txtGaDi, txtGaDen, txtSoGhe;
    private JButton btnCapNhat, btnHuy;
    private boolean daCapNhat = false;
    private ChuyenTau chuyenTauMoi;
	private JDateChooser dateChooserKhoiHanh;
	private JSpinner  spnGioDi, spnGioDen;
	private JDateChooser dateChooserKetThuc;
	
	private static final String[] DS_GA_VIETNAM = {
            "Sài gòn", "Bình Thuận", "Phan Thiết", "Nha Trang", "Quy Nhơn",
            "Đà Nẵng", "Huế", "Vinh", "Thanh Hóa", "Nam Định", "Hà Nội",
            "Hải Phòng", "Phú Thọ", "Yên Bái", "Lào Cai"
        };

    private ChuyenTau chuyenTau;
    private ChuyenTauDAO chuyenTauDAO;
	private TauDAO tauDAO;

    public CapNhatChuyenTauDialog(ChuyenTau chuyenTau) {
        super();
        setModal(true);
        
        chuyenTauDAO = new ChuyenTauDAO();
        tauDAO = new TauDAO();
        this.chuyenTau = chuyenTau;

        setSize(650, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Dòng tiêu đề
        JLabel lblTieuDe = new JLabel("CẬP NHẬT THÔNG TIN CHUYẾN TÀU", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        add(lblTieuDe, BorderLayout.NORTH);

        // Panel nhập liệu có viền
        JPanel panelMain = new JPanel(new GridBagLayout());
        panelMain.setBorder(BorderFactory.createTitledBorder("Thông tin chuyến tàu"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


		// Row 1 - Ga khởi hành & Ga kết thúc
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelMain.add(new JLabel("Ga khởi hành:"), gbc);
		cboGaDi = new JComboBox<>(DS_GA_VIETNAM);
		cboGaDi.setSelectedItem(chuyenTau.getGaKhoiHanh());
		gbc.gridx = 1;
		panelMain.add(cboGaDi, gbc);

		gbc.gridx = 2;
		panelMain.add(new JLabel("Ga kết thúc:"), gbc);
		cboGaDen = new JComboBox<>(DS_GA_VIETNAM);
		cboGaDen.setSelectedItem(chuyenTau.getGaKetThuc());
		gbc.gridx = 3;
		panelMain.add(cboGaDen, gbc);

		// Row 2 - Ngày và giờ khởi hành
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelMain.add(new JLabel("Ngày khởi hành:"), gbc);
		dateChooserKhoiHanh = new JDateChooser();
		dateChooserKhoiHanh.setDateFormatString("dd/MM/yyyy");
		dateChooserKhoiHanh.setDate(Date.from(chuyenTau.getNgayKhoiHanh().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		gbc.gridx = 1;
		panelMain.add(dateChooserKhoiHanh, gbc);

		gbc.gridx = 2;
		panelMain.add(new JLabel("Giờ khởi hành:"), gbc);
		spnGioDi = createTimeSpinner();
		spnGioDi.setValue(Date.from(chuyenTau.getNgayKhoiHanh().atZone(ZoneId.systemDefault()).toInstant()));
		gbc.gridx = 3;
		panelMain.add(spnGioDi, gbc);

		// Row 3 - Ngày và giờ kết thúc
		gbc.gridy = 3;
		gbc.gridx = 0;
		panelMain.add(new JLabel("Ngày kết thúc:"), gbc);
		dateChooserKetThuc = new JDateChooser();
		dateChooserKetThuc.setDateFormatString("dd/MM/yyyy");
		dateChooserKetThuc.setDate(Date.from(chuyenTau.getNgayKetThuc().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		gbc.gridx = 1;
		panelMain.add(dateChooserKetThuc, gbc);

		gbc.gridx = 2;
		panelMain.add(new JLabel("Giờ kết thúc:"), gbc);
		spnGioDen = createTimeSpinner();
		spnGioDen.setValue(Date.from(chuyenTau.getNgayKetThuc().atZone(ZoneId.systemDefault()).toInstant()));
		gbc.gridx = 3;
		panelMain.add(spnGioDen, gbc);
		
		// Row 4 - Chọn tàu
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelMain.add(new JLabel("Chọn tàu:"), gbc);
		cboTau = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		panelMain.add(cboTau, gbc);

		// Row 5 - Nút
		gbc.gridwidth = 1;
		btnCapNhat = new JButton("Cập nhật");
		btnHuy = new JButton("Hủy");

		gbc.gridy = 5;
		gbc.gridx = 2;
		panelMain.add(btnCapNhat, gbc);
		gbc.gridx = 3;
		panelMain.add(btnHuy, gbc);

		add(panelMain, BorderLayout.CENTER);

		// Sự kiện
		btnHuy.addActionListener(e -> dispose());
		dateChooserKhoiHanh.getDateEditor().addPropertyChangeListener(evt -> themTauVaoBox());
		spnGioDi.addChangeListener(e -> themTauVaoBox());
		dateChooserKetThuc.getDateEditor().addPropertyChangeListener(evt -> themTauVaoBox());
		spnGioDen.addChangeListener(e -> themTauVaoBox());


        btnCapNhat.addActionListener(e -> {
            try {
//              Tau tau = (Tau) cboTau.getSelectedItem();
                String gaDi = cboGaDi.getSelectedItem().toString();
                String gaDen = cboGaDen.getSelectedItem().toString();

                Date ngayKhoiHanh = dateChooserKhoiHanh.getDate();
                Date gioKhoiHanh = (Date) spnGioDi.getValue();

                // Lấy ngày giờ kết thúc
                Date ngayKetThuc = dateChooserKetThuc.getDate();
                Date gioKetThuc = (Date) spnGioDen.getValue();

                // Chuyển sang LocalDateTime
                LocalDateTime ngayDi = LocalDateTime.ofInstant(ngayKhoiHanh.toInstant(), ZoneId.systemDefault())
                        .withHour(gioKhoiHanh.getHours())
                        .withMinute(gioKhoiHanh.getMinutes());
                
                LocalDateTime ngayDen = LocalDateTime.ofInstant(ngayKetThuc.toInstant(), ZoneId.systemDefault())
                        .withHour(gioKetThuc.getHours());
            	
                chuyenTau.setGaKhoiHanh(gaDi);
                chuyenTau.setGaKetThuc(gaDen);
                chuyenTau.setNgayKhoiHanh(ngayDi);
                chuyenTau.setNgayKetThuc(ngayDen);
                chuyenTau.setTrangThai(true);

                daCapNhat = chuyenTauDAO.update(chuyenTau);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        });

        btnHuy.addActionListener(e -> dispose());
    }

    public boolean isDaCapNhat() {
        return daCapNhat;
    }
    
    private JSpinner createTimeSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
        return spinner;
    }
    
	private void themTauVaoBox() {
		cboTau.removeAllItems();
        Date ngayKhoiHanh = dateChooserKhoiHanh.getDate();
        Date gioKhoiHanh = (Date) spnGioDi.getValue();

        // Lấy ngày giờ kết thúc
        Date ngayKetThuc = dateChooserKetThuc.getDate();
        Date gioKetThuc = (Date) spnGioDen.getValue();

        if (ngayKhoiHanh == null || ngayKetThuc == null || gioKhoiHanh == null || gioKetThuc == null) {
            return; 
        }
        
        // Chuyển sang LocalDateTime
        LocalDateTime ngayDi = LocalDateTime.ofInstant(ngayKhoiHanh.toInstant(), java.time.ZoneId.systemDefault())
                .withHour(gioKhoiHanh.getHours())
                .withMinute(gioKhoiHanh.getMinutes());

        LocalDateTime ngayDen = LocalDateTime.ofInstant(ngayKetThuc.toInstant(), java.time.ZoneId.systemDefault())
                .withHour(gioKetThuc.getHours())
                .withMinute(gioKetThuc.getMinutes());

		
		ArrayList<Tau> taus = tauDAO.timTauTheoNgay(ngayDi, ngayDen);
		for (Tau tau : taus) {
			cboTau.addItem(tau.getMaTau());
		}
	}
}

