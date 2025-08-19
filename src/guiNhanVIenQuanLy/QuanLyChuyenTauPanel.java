package guiNhanVIenQuanLy;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChuyenTauDAO;
import entity.ChuyenTau;
import entity.Tau;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class QuanLyChuyenTauPanel extends JPanel {

	private MenuPanel menu_GUI;
	private JPanel contentPanel;
	private TopBarPanel topBar_GUI;
	private JButton btnTimKiem;
	private JLabel timKiemIcon;
	private ChuyenDiCard cardDangChon = null;

	private ChuyenTauDAO chuyenTauDAO;
	private JButton btnTimKiem1;

	private static final String[] DS_GA_VIETNAM = { "Sài gòn", "Bình Thuận", "Phan Thiết", "Nha Trang", "Quy Nhơn",
			"Đà Nẵng", "Huế", "Vinh", "Thanh Hóa", "Nam Định", "Hà Nội", "Hải Phòng", "Phú Thọ", "Yên Bái", "Lào Cai" };

	public QuanLyChuyenTauPanel() {
		ConnectDB.getInstance().connect();
		chuyenTauDAO = new ChuyenTauDAO();

		setSize(1000, 700);
		setLayout(new BorderLayout());

		// Panel chứa toàn bộ content
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(Color.WHITE);

		// Panel tìm kiếm (combo + ô tìm kiếm)
		JPanel timKiemPanel = new JPanel();
		timKiemPanel.setLayout(new BoxLayout(timKiemPanel, BoxLayout.X_AXIS));
		timKiemPanel.setBackground(Color.WHITE);
		timKiemPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding

		// Label "Tìm kiếm theo:"
		JLabel lblTimKiemTheo = new JLabel("Tìm kiếm theo:");
		lblTimKiemTheo.setFont(new Font("SansSerif", Font.BOLD, 14));
		timKiemPanel.add(lblTimKiemTheo);
		timKiemPanel.add(Box.createHorizontalStrut(10));

		// Combo box
		JComboBox<String> cboTieuChi = new JComboBox<>(new String[] { "Mã tàu", "Nhiều tiêu chí" });
		cboTieuChi.setBackground(new Color(230, 230, 230));
		cboTieuChi.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cboTieuChi.setPreferredSize(new Dimension(120, 30));
		cboTieuChi.setMaximumSize(new Dimension(180, 30));

		// Nút tìm kiếm (chứa icon + text field)
		btnTimKiem = new JButton();
		btnTimKiem.setLayout(new BorderLayout());
		btnTimKiem.setPreferredSize(new Dimension(250, 35));
		btnTimKiem.setMaximumSize(new Dimension(250, 35));
		btnTimKiem.setBackground(Color.WHITE);
		btnTimKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		timKiemIcon = new JLabel(new ImageIcon(new File("images//IconTimKiem.jpg").getAbsolutePath()));
		btnTimKiem.add(timKiemIcon, BorderLayout.WEST);

		JTextField txtTimKiem = new JTextField(" Tìm kiếm ...");
		txtTimKiem.setForeground(Color.GRAY);
		txtTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTimKiem.setBackground(new Color(240, 240, 240));
		txtTimKiem.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		btnTimKiem.add(txtTimKiem, BorderLayout.CENTER);

		JComboBox<String> cboGaDi = new JComboBox<>(DS_GA_VIETNAM);
		JComboBox<String> cboGaDen = new JComboBox<>(DS_GA_VIETNAM);
		JDateChooser dateChooserNgayDi = new JDateChooser();
		JDateChooser dateChooserNgayDen = new JDateChooser();
		btnTimKiem1 = new JButton();
		btnTimKiem1.setLayout(new BorderLayout());
		btnTimKiem1.setPreferredSize(new Dimension(35, 35));
		btnTimKiem1.setMaximumSize(new Dimension(35, 35));
		btnTimKiem1.setBackground(Color.WHITE);
		btnTimKiem1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnTimKiem1.add(new JLabel(new ImageIcon(new File("images//IconTimKiem.jpg").getAbsolutePath())));

		// Panel chính để chứa các dòng
		JPanel tieuChiPanel = new JPanel();
		tieuChiPanel.setLayout(new GridBagLayout());
		tieuChiPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		// ========== Dòng 1 ==========
		gbc.gridy = 0;
		gbc.gridx = 0;
		tieuChiPanel.add(new JLabel("Ga đi:"), gbc);
		gbc.gridx = 1;
		tieuChiPanel.add(cboGaDi, gbc);
		gbc.gridx = 2;
		tieuChiPanel.add(new JLabel("Ga đến:"), gbc);
		gbc.gridx = 3;
		tieuChiPanel.add(cboGaDen, gbc);
		gbc.gridx = 4;
		tieuChiPanel.add(btnTimKiem1, gbc);

		// ========== Dòng 2 ==========
		gbc.gridy = 1;
		gbc.gridx = 0;
		tieuChiPanel.add(new JLabel("Ngày đi:"), gbc);
		gbc.gridx = 1;
		dateChooserNgayDi.setPreferredSize(new Dimension(100, 25));
		tieuChiPanel.add(dateChooserNgayDi, gbc);
		gbc.gridx = 2;
		tieuChiPanel.add(new JLabel("Ngày đến:"), gbc);
		gbc.gridx = 3;
		dateChooserNgayDen.setPreferredSize(new Dimension(100, 25));
		tieuChiPanel.add(dateChooserNgayDen, gbc);

		tieuChiPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(180, 180, 180)), "Tìm kiếm nâng cao", // tiêu đề của viền
				TitledBorder.LEFT, // canh trái tiêu đề
				TitledBorder.TOP, // nằm trên cùng
				new Font("SansSerif", Font.BOLD, 13), // font tiêu đề
				Color.DARK_GRAY // màu tiêu đề
		));

		// Ban đầu ẩn tiêu chí mở rộng
		tieuChiPanel.setVisible(false);

		// Thêm các thành phần vào panel
		timKiemPanel.add(cboTieuChi);
		timKiemPanel.setBackground(Color.WHITE);
		timKiemPanel.add(Box.createHorizontalStrut(50));
		timKiemPanel.add(btnTimKiem);
		timKiemPanel.add(tieuChiPanel);

		// Thêm ActionListener cho JComboBox
		cboTieuChi.addActionListener(e -> {
			String selectedItem = (String) cboTieuChi.getSelectedItem(); // Lấy giá trị đã chọn

			if ("Mã tàu".equals(selectedItem)) {
				// Hiển thị ô tìm kiếm theo mã tàu và ẩn các tiêu chí khác
				btnTimKiem.setVisible(true);
				tieuChiPanel.setVisible(false);
			} else if ("Nhiều tiêu chí".equals(selectedItem)) {
				// Hiển thị các tiêu chí tìm kiếm như ga đi, ga đến, ngày đi, ngày đến
				btnTimKiem.setVisible(false);
				tieuChiPanel.setVisible(true);
			}
		});

		// Focus event cho placeholder
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				if (txtTimKiem.getText().equals(" Tìm kiếm ...")) {
					txtTimKiem.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent evt) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText(" Tìm kiếm ...");
				}
			}
		});

		JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		wrapperPanel.setBackground(Color.WHITE);
		wrapperPanel.add(timKiemPanel);

		contentPanel.add(wrapperPanel);

		JLabel lblTitle = new JLabel("Danh sách chuyến tàu", SwingConstants.CENTER);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(lblTitle);

		JPanel contentList = new JPanel();
		contentList.setLayout(new BoxLayout(contentList, BoxLayout.Y_AXIS));
		JScrollPane scrollPaneList = new JScrollPane(contentList);
		contentPanel.add(scrollPaneList);

		ArrayList<ChuyenTau> chuyenTaus = chuyenTauDAO.getAll();
		for (ChuyenTau chuyenTau : chuyenTaus) {
			ChuyenDiCard card = new ChuyenDiCard(chuyenTau);

			card.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (cardDangChon != null) {
						cardDangChon.setSelected(false); // bỏ chọn card cũ
					}
					cardDangChon = card;
					cardDangChon.setSelected(true); // chọn card mới
				}
			});

			contentList.add(card);
		}

		btnTimKiem.addActionListener(e -> {
			String maTauCanTim = txtTimKiem.getText().trim();

			if (maTauCanTim.isEmpty() || maTauCanTim.equals("Tìm kiếm ...")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mã tàu cần tìm!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			ArrayList<ChuyenTau> ketQua1 = chuyenTauDAO.timKiemTheoMaTau(maTauCanTim);

			// Xoá danh sách cũ
			contentList.removeAll();

			if (ketQua1 == null || ketQua1.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến tàu với mã: " + maTauCanTim,
						"Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
			} else {
				for (ChuyenTau chuyenTaus2 : ketQua1) {
					ChuyenDiCard card = new ChuyenDiCard(chuyenTaus2);

					card.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (cardDangChon != null) {
								cardDangChon.setSelected(false);
							}
							cardDangChon = card;
							cardDangChon.setSelected(true);
						}
					});

					contentList.add(card);
				}
			}

			// Cập nhật lại giao diện
			contentList.revalidate();
			contentList.repaint();
		});

		btnTimKiem1.addActionListener(e -> {
			// Lấy giá trị từ các ComboBox và DateChooser
			String gaDi = (String) cboGaDi.getSelectedItem();
			String gaDen = (String) cboGaDen.getSelectedItem();
			System.out.println(gaDi + " " + gaDen);
			LocalDateTime ngayKhoiHanh = dateChooserNgayDi.getDate().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDateTime();
			LocalDateTime ngayKetThuc = dateChooserNgayDen.getDate().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDateTime();

			// Gọi phương thức tìm kiếm chuyến tàu
			ArrayList<ChuyenTau> ketQua2 = chuyenTauDAO.timKiemChuyenTau(gaDi, gaDen, ngayKhoiHanh, ngayKetThuc);

			// Xóa toàn bộ các card hiện tại
			contentList.removeAll();

			// Thêm các chuyến tàu tìm được vào contentList
			for (ChuyenTau chuyenTau : ketQua2) {
				ChuyenDiCard card = new ChuyenDiCard(chuyenTau);

				card.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (cardDangChon != null) {
							cardDangChon.setSelected(false); // Bỏ chọn card cũ
						}
						cardDangChon = card;
						cardDangChon.setSelected(true); // Chọn card mới
					}
				});

				contentList.add(card);
			}

			// Cập nhật lại giao diện
			contentList.revalidate();
			contentList.repaint();
		});

		// Panel chứa nút chức năng
		JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		actionPanel.setBackground(Color.WHITE);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(e -> {
			ThemChuyenTauDialog dialog = new ThemChuyenTauDialog();
			dialog.setModal(true);
			dialog.setVisible(true);

			if (dialog.isDaThem()) {
				ChuyenTau moi = dialog.getChuyenTauMoi();
				ChuyenDiCard card = new ChuyenDiCard(moi);
				contentList.add(card);
				contentList.revalidate();
				contentList.repaint();
			}
		});

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(e -> {
			if (cardDangChon != null) {
				int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa chuyến tàu này?", "Xác nhận xóa",
						JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {
					String maChuyenTau = cardDangChon.getChuyenTau().getMaChuyenTau();
					if (chuyenTauDAO.xoaChuyenTau(maChuyenTau)) {
						contentList.remove(cardDangChon);
						contentList.revalidate();
						contentList.repaint();
						cardDangChon = null;
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn chuyến tàu trước khi xóa.");
			}
		});

		JButton btnCapNhat = new JButton("Cập nhật thông tin");
		btnCapNhat.addActionListener(e -> {
			if (cardDangChon == null) {
				JOptionPane.showMessageDialog(this, "Hãy chọn một chuyến tàu để cập nhật.");
				return;
			}

			CapNhatChuyenTauDialog dialog = new CapNhatChuyenTauDialog(cardDangChon.getChuyenTau());
			dialog.setModal(true);
			dialog.setVisible(true);

			if (dialog.isDaCapNhat()) {
				cardDangChon.capNhatLaiUI(cardDangChon.getChuyenTau());
				contentList.revalidate();
				contentList.repaint();
			}
		});

		// Nút Reset để hiển thị lại tất cả các chuyến tàu
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(e -> {
			// Xoá tất cả các card hiện tại trong contentList
			contentList.removeAll();

			// Lấy lại tất cả các chuyến tàu
			ArrayList<ChuyenTau> allChuyenTaus = chuyenTauDAO.getAll();

			// Thêm các chuyến tàu vào contentList
			for (ChuyenTau chuyenTau : allChuyenTaus) {
				ChuyenDiCard card = new ChuyenDiCard(chuyenTau);

				card.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (cardDangChon != null) {
							cardDangChon.setSelected(false);
						}
						cardDangChon = card;
						cardDangChon.setSelected(true);
					}
				});

				contentList.add(card);
			}

			// Cập nhật lại giao diện
			contentList.revalidate();
			contentList.repaint();
		});

		actionPanel.add(btnThem);
		actionPanel.add(btnXoa);
		actionPanel.add(btnCapNhat);
		actionPanel.add(btnLamMoi);
		contentPanel.add(actionPanel);

		add(contentPanel, BorderLayout.CENTER);
	}

}
