package guiNhanVIenQuanLy;

import entity.ChuyenTau;
import entity.Tau;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import dao.ChuyenTauDAO;
import dao.TauDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThemChuyenTauDialog extends JDialog {
	private JComboBox<String> cboTau;
	private JComboBox<String> cboGaDi, cboGaDen;
	private JTextField txtGaDi, txtGaDen, txtNgayDi, txtNgayDen;
	private JButton btnThem, btnHuy;
	private boolean daThem = false;
	private ChuyenTau chuyenTauMoi;
	private JDateChooser dateChooserKhoiHanh;
	private JSpinner spnGioDi, spnGioDen;
	private JDateChooser dateChooserKetThuc;

	private ChuyenTauDAO chuyenTauDAO;
	private TauDAO tauDAO;

	private static final String[] DS_GA_VIETNAM = { "Sài gòn", "Bình Thuận", "Phan Thiết", "Nha Trang", "Quy Nhơn",
			"Đà Nẵng", "Huế", "Vinh", "Thanh Hóa", "Nam Định", "Hà Nội", "Hải Phòng", "Phú Thọ", "Yên Bái", "Lào Cai" };

	public ThemChuyenTauDialog() {
		super();
		setModal(true);

		chuyenTauDAO = new ChuyenTauDAO();
		tauDAO = new TauDAO();

		setTitle("Thêm chuyến tàu mới");
		setSize(650, 350);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Dòng tiêu đề
		JLabel lblTieuDe = new JLabel("THÊM CHUYẾN TÀU MỚI", SwingConstants.CENTER);
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
		gbc.gridx = 1;
		panelMain.add(cboGaDi, gbc);

		gbc.gridx = 2;
		panelMain.add(new JLabel("Ga kết thúc:"), gbc);
		cboGaDen = new JComboBox<>(DS_GA_VIETNAM);
		gbc.gridx = 3;
		panelMain.add(cboGaDen, gbc);

		// Row 2 - Ngày và giờ khởi hành
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelMain.add(new JLabel("Ngày khởi hành:"), gbc);
		dateChooserKhoiHanh = new JDateChooser();
		dateChooserKhoiHanh.setDateFormatString("dd/MM/yyyy");
		gbc.gridx = 1;
		panelMain.add(dateChooserKhoiHanh, gbc);

		gbc.gridx = 2;
		panelMain.add(new JLabel("Giờ khởi hành:"), gbc);
		spnGioDi = createTimeSpinner();
		gbc.gridx = 3;
		panelMain.add(spnGioDi, gbc);

		// Row 3 - Ngày và giờ kết thúc
		gbc.gridy = 3;
		gbc.gridx = 0;
		panelMain.add(new JLabel("Ngày kết thúc:"), gbc);
		dateChooserKetThuc = new JDateChooser();
		dateChooserKetThuc.setDateFormatString("dd/MM/yyyy");
		gbc.gridx = 1;
		panelMain.add(dateChooserKetThuc, gbc);

		gbc.gridx = 2;
		panelMain.add(new JLabel("Giờ kết thúc:"), gbc);
		spnGioDen = createTimeSpinner();
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
		btnThem = new JButton("Thêm");
		btnHuy = new JButton("Hủy");

		gbc.gridy = 5;
		gbc.gridx = 2;
		panelMain.add(btnThem, gbc);
		gbc.gridx = 3;
		panelMain.add(btnHuy, gbc);

		add(panelMain, BorderLayout.CENTER);

		// Sự kiện
		btnThem.addActionListener(this::actionThem);
		btnHuy.addActionListener(e -> dispose());
		dateChooserKhoiHanh.getDateEditor().addPropertyChangeListener(evt -> themTauVaoBox());
		spnGioDi.addChangeListener(e -> themTauVaoBox());
		dateChooserKetThuc.getDateEditor().addPropertyChangeListener(evt -> themTauVaoBox());
		spnGioDen.addChangeListener(e -> themTauVaoBox());
	}

	private JSpinner createTimeSpinner() {
		SpinnerDateModel model = new SpinnerDateModel();
		JSpinner spinner = new JSpinner(model);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
		spinner.setEditor(editor);
		return spinner;
	}

	private void actionThem(ActionEvent e) {
        try {
            ChuyenTau chuyenTau = taoChuyenTauMoi();

            this.chuyenTauMoi = chuyenTau;
            daThem = chuyenTauDAO.add(chuyenTau);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + ex.getMessage());
        }
    }

	public boolean isDaThem() {
		return daThem;
	}

	public ChuyenTau getChuyenTauMoi() {
		return chuyenTauMoi;
	}

	private String removeVietnameseAccents(String input) {
		String normalized = Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
		return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replaceAll("đ", "d").replaceAll("Đ", "D");
	}

	private String taoMaChuyenTau(String gaDi, String gaDen, LocalDateTime ngayGioKhoiHanh, int soThuTu) {
		// Loại bỏ dấu và khoảng trắng
		String gaDiMa = removeVietnameseAccents(gaDi).replaceAll("\\s+", "");
		String gaDenMa = removeVietnameseAccents(gaDen).replaceAll("\\s+", "");

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

		String ngay = ngayGioKhoiHanh.format(dateFormatter);
		String gio = ngayGioKhoiHanh.format(timeFormatter);

		String stt = String.format("%02d", soThuTu);

		return gaDiMa + "-" + gaDenMa + "-" + ngay + "-" + gio + "-" + stt;
	}
	
	private ChuyenTau taoChuyenTauMoi() {
        String maTau = cboTau.getSelectedItem().toString();
        String gaKhoiHanh = cboGaDi.getSelectedItem().toString();
        String gaKetThuc = cboGaDen.getSelectedItem().toString();

        Date ngayKhoiHanh = dateChooserKhoiHanh.getDate();
        Date gioKhoiHanh = (Date) spnGioDi.getValue();

        // Lấy ngày giờ kết thúc
        Date ngayKetThuc = dateChooserKetThuc.getDate();
        Date gioKetThuc = (Date) spnGioDen.getValue();

        // Chuyển sang LocalDateTime
        LocalDateTime ngayDi = LocalDateTime.ofInstant(ngayKhoiHanh.toInstant(), java.time.ZoneId.systemDefault())
                .withHour(gioKhoiHanh.getHours())
                .withMinute(gioKhoiHanh.getMinutes());

        LocalDateTime ngayDen = LocalDateTime.ofInstant(ngayKetThuc.toInstant(), java.time.ZoneId.systemDefault())
                .withHour(gioKetThuc.getHours())
                .withMinute(gioKetThuc.getMinutes());

        int soChuyen = chuyenTauDAO.demSoTauTrenCungChuyen(gaKhoiHanh, gaKetThuc, ngayDi);
        int soThuTu = soChuyen + 1;
        String maChuyenTau = taoMaChuyenTau(gaKhoiHanh, gaKetThuc, ngayDi, soThuTu);
        
        ChuyenTau chuyenTau = new ChuyenTau();
        chuyenTau.setMaChuyenTau(maChuyenTau);
        chuyenTau.setTau(tauDAO.timTauTheoMa(maTau));
        chuyenTau.setGaKhoiHanh(gaKhoiHanh);
        chuyenTau.setGaKetThuc(gaKetThuc);
        chuyenTau.setNgayKhoiHanh(ngayDi);
        chuyenTau.setNgayKetThuc(ngayDen);
        return chuyenTau;
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
