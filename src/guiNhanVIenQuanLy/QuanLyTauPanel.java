package guiNhanVIenQuanLy;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.TauDAO;
import entity.LoaiTrangThai;
import entity.Tau;

public class QuanLyTauPanel extends JPanel {
	private JTextField txtTenTau;
	private JComboBox<String> cboTrangThai;
	private JSpinner spnSoLuongToaTau;
	private JSpinner spnSoLuongGhe;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnLamMoi;

	private TauDAO tauDAO;
	private Tau tauDangChon;

	public QuanLyTauPanel() {
		ConnectDB.getInstance().connect();
		tauDAO = new TauDAO();

		setSize(1000, 700);
		setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(Color.white);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các ô

		Dimension inputSize = new Dimension(180, 25);
		Dimension buttonSize = new Dimension(100, 30);

		// Panel nhập liệu trung gian để căn giữa và giữ bố cục
		JPanel panelNhapLieu = new JPanel(new GridBagLayout());
		panelNhapLieu.setOpaque(false); // Trong suốt để đồng màu nền

		GridBagConstraints gbcInput = new GridBagConstraints();
		gbcInput.insets = new Insets(5, 10, 5, 10);
		gbcInput.anchor = GridBagConstraints.LINE_END;

//		Dimension inputSize = new Dimension(180, 25);

		// Dòng 1: Tên tàu + Trạng thái
		gbcInput.gridx = 0;
		gbcInput.gridy = 0;
		panelNhapLieu.add(new JLabel("Tên tàu:"), gbcInput);

		gbcInput.gridx = 1;
		gbcInput.anchor = GridBagConstraints.LINE_START;
		txtTenTau = new JTextField();
		txtTenTau.setPreferredSize(inputSize);
		panelNhapLieu.add(txtTenTau, gbcInput);

		gbcInput.gridx = 2;
		gbcInput.anchor = GridBagConstraints.LINE_END;
		panelNhapLieu.add(new JLabel("Trạng thái:"), gbcInput);

		gbcInput.gridx = 3;
		gbcInput.anchor = GridBagConstraints.LINE_START;
		cboTrangThai = new JComboBox<>(new String[] { "Hoạt động", "Dừng hoạt động", "Bảo trì" });
		cboTrangThai.setPreferredSize(inputSize);
		panelNhapLieu.add(cboTrangThai, gbcInput);

		// Dòng 2: Số lượng toa tàu + Số lượng ghế
		gbcInput.gridx = 0;
		gbcInput.gridy = 1;
		gbcInput.anchor = GridBagConstraints.LINE_END;
		panelNhapLieu.add(new JLabel("Số lượng toa tàu:"), gbcInput);

		gbcInput.gridx = 1;
		gbcInput.anchor = GridBagConstraints.LINE_START;
		spnSoLuongToaTau = new JSpinner();
		spnSoLuongToaTau.setPreferredSize(inputSize);
		panelNhapLieu.add(spnSoLuongToaTau, gbcInput);

		gbcInput.gridx = 2;
		gbcInput.anchor = GridBagConstraints.LINE_END;
		panelNhapLieu.add(new JLabel("Số lượng ghế:"), gbcInput);

		gbcInput.gridx = 3;
		gbcInput.anchor = GridBagConstraints.LINE_START;
		spnSoLuongGhe = new JSpinner();
		spnSoLuongGhe.setPreferredSize(inputSize);
		panelNhapLieu.add(spnSoLuongGhe, gbcInput);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(panelNhapLieu, gbc);

		// Dòng 3: Tiêu đề "Danh sách tàu"
		gbc.gridy = 2;
		JLabel lblDanhSachTau = new JLabel("Danh sách tàu");
		lblDanhSachTau.setFont(new Font("SansSerif", Font.BOLD, 20));
		contentPanel.add(lblDanhSachTau, gbc);

		// Dòng 4: Bảng
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		String[] headers = { "STT", "Mã tàu", "Tên tàu", "Số lượng toa tàu", "Số lượng ghế", "Trạng thái" };
		tableModel = new DefaultTableModel(headers, 0);
		table = new JTable(tableModel);

		// Đặt kích thước cho các cột
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		table.setRowHeight(30);

		// Căn giữa các ô trong bảng
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// Căn giữa các cột
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		table.setFont(new Font("SansSerif", Font.PLAIN, 14));

		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(900, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane, gbc);

		// Dòng 5: Các nút chức năng
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		gbc.weighty = 0;

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		buttonPanel.setBackground(Color.white);
		buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chức năng",
				TitledBorder.LEFT, TitledBorder.TOP));

		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnCapNhat = new JButton("Cập nhật");
		btnLamMoi = new JButton("Làm mới");

		Border buttonBorder = BorderFactory.createLineBorder(Color.GRAY, 1, true);
		JButton[] buttons = { btnThem, btnXoa, btnCapNhat, btnLamMoi };
		for (JButton btn : buttons) {
			btn.setPreferredSize(buttonSize);
			btn.setBorder(buttonBorder);
			buttonPanel.add(btn);
		}

		contentPanel.add(buttonPanel, gbc);

		add(contentPanel, BorderLayout.CENTER);

		btnThem.addActionListener(e -> {
		    String maTau = tauDAO.generateMaTau(); // Lấy mã tàu tự động
		    String tenTau = txtTenTau.getText(); // Lấy tên tàu
		    String trangThai = (String) cboTrangThai.getSelectedItem(); // Lấy trạng thái từ combobox
		    int soLuongToa = (int) spnSoLuongToaTau.getValue(); // Lấy số lượng toa tàu từ spinner
		    int soLuongGhe = (int) spnSoLuongGhe.getValue(); // Lấy số lượng ghế từ spinner

		    System.out.println(trangThai);
		    // Chuyển trạng thái thành enum LoaiTrangThaiTau
		    LoaiTrangThai loaiTrangThaiTau = null;
		    if ("Hoạt động".equals(trangThai)) {
		        loaiTrangThaiTau = LoaiTrangThai.HOAT_DONG;
		    } else if ("Dừng hoạt động".equals(trangThai)) {
		        loaiTrangThaiTau = LoaiTrangThai.DUNG_HOAT_DONG;
		    } else if ("Bảo trì".equals(trangThai)) {
		        loaiTrangThaiTau = LoaiTrangThai.BAO_TRI;
		    }
		    System.out.println(loaiTrangThaiTau);
		    // Tạo đối tượng Tau và gọi phương thức add
		    Tau tau = new Tau(maTau, tenTau, soLuongGhe, soLuongToa, loaiTrangThaiTau);
		    boolean success = tauDAO.add(tau); // Thêm tàu vào cơ sở dữ liệu

		    // Nếu thêm thành công, cập nhật bảng
		    if (success) {
		        loadDataToTable();
		    } else {
		        JOptionPane.showMessageDialog(null, "Thêm tàu không thành công!");
		    }
		});

		btnXoa.addActionListener(e -> {
			// Lấy mã tàu từ bảng (có thể chọn từ dòng đã chọn trong bảng)
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
				String maTau = table.getValueAt(selectedRow, 1).toString(); // Lấy mã tàu từ cột thứ 1 (cột mã tàu)

				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tàu " + maTau + "?",
						"Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				// Gọi phương thức delete để xóa tàu
				boolean success = tauDAO.delete(maTau);

				if (success) {
					JOptionPane.showMessageDialog(null, "Xóa tàu thành công!");

					// Cập nhật lại bảng sau khi xóa
					loadDataToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Xóa tàu không thành công!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn tàu để xóa!");
			}
		});

		btnCapNhat.addActionListener(e -> {
		    int row = table.getSelectedRow();
		    if (row == -1) {
		        JOptionPane.showMessageDialog(null, "Vui lòng chọn một tàu để cập nhật.");
		        return;
		    }
		    String tenTau = txtTenTau.getText().trim();
		    String trangThai = (String) cboTrangThai.getSelectedItem();
		    int soLuongToa = (int) spnSoLuongToaTau.getValue();
		    int soLuongGhe = (int) spnSoLuongGhe.getValue();

		    LoaiTrangThai loaiTrangThaiTau = null;
		    if ("Hoạt động".equals(trangThai)) {
		        loaiTrangThaiTau = LoaiTrangThai.HOAT_DONG;
		    } else if ("Dừng hoạt động".equals(trangThai)) {
		        loaiTrangThaiTau = LoaiTrangThai.DUNG_HOAT_DONG;
		    } else if ("Bảo trì".equals(trangThai)) {
		        loaiTrangThaiTau = LoaiTrangThai.BAO_TRI;
		    }
		    
		    tauDangChon.setTenTau(tenTau);
		    tauDangChon.setSoLuongGhe(soLuongGhe);
		    tauDangChon.setSoLuongToaTau(soLuongToa);
		    tauDangChon.setLoaiTrangThaiTau(loaiTrangThaiTau);

		    boolean success = tauDAO.update(tauDangChon);

		    if (success) {
		        JOptionPane.showMessageDialog(null, "Cập nhật tàu thành công!");
		        loadDataToTable(); // làm mới bảng
		    } else {
		        JOptionPane.showMessageDialog(null, "Cập nhật tàu thất bại!");
		    }
		});

		
		btnLamMoi.addActionListener(e -> {
			loadDataToTable(); // Cập nhật lại bảng khi nhấn Làm mới
		});

		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();
		        if (row != -1) {
		            // Lấy dữ liệu từ table
		        	String maTau = table.getValueAt(row, 1).toString();
		            String tenTau = table.getValueAt(row, 2).toString();
		            String trangThai = table.getValueAt(row, 5).toString();
		            int soLuongGhe = Integer.parseInt(table.getValueAt(row, 4).toString());
		            int soLuongToa = Integer.parseInt(table.getValueAt(row, 3).toString());
		            
		            LoaiTrangThai loaiTrangThaiTau = null;
				    if ("Hoạt động".equals(trangThai)) {
				        loaiTrangThaiTau = LoaiTrangThai.HOAT_DONG;
				    } else if ("Dừng hoạt động".equals(trangThai)) {
				        loaiTrangThaiTau = LoaiTrangThai.DUNG_HOAT_DONG;
				    } else if ("Bảo trì".equals(trangThai)) {
				        loaiTrangThaiTau = LoaiTrangThai.BAO_TRI;
				    }
		            
		            tauDangChon = new Tau(maTau, tenTau, soLuongGhe, soLuongToa, loaiTrangThaiTau);

		            // Hiển thị lên các ô nhập
		            txtTenTau.setText(tenTau);
		            cboTrangThai.setSelectedItem(trangThai);
		            spnSoLuongGhe.setValue(soLuongGhe);
		            spnSoLuongToaTau.setValue(soLuongToa);
		        }
		    }
		});
		
		loadDataToTable();
	}

	private void loadDataToTable() {
		ArrayList<Tau> danhSachTau = tauDAO.getAll(); // Giả sử bạn có đối tượng tauDao gọi đến DAO

		// Xóa dữ liệu cũ
		tableModel.setRowCount(0);

		int stt = 1;
		for (Tau tau : danhSachTau) {
			String trangThai = switch (tau.getLoaiTrangThaiTau()) {
			case HOAT_DONG -> "Hoạt động";
			case DUNG_HOAT_DONG -> "Dừng hoạt động";
			case BAO_TRI -> "Bảo trì";
			};
			Object[] rowData = { stt++, tau.getMaTau(), tau.getTenTau(), tau.getSoLuongToaTau(), tau.getSoLuongGhe(),
					trangThai };
			tableModel.addRow(rowData);
		}
	}

}
