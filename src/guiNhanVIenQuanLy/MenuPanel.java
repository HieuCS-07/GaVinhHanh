package guiNhanVIenQuanLy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
	private JButton btnQLNV;
	private JLabel arrowIcon;
	private JPanel menuContainer;
	private ArrayList<JButton> menuButtons = new ArrayList<>();
	private ArrayList<JButton> subButtons = new ArrayList<>();
	private JLabel nhanVienIcon;
	private JButton btnQLKM;
	private JPanel subMenuQLKM;
	private JPanel subMenuQLNV;
	private JButton btnQLCT;
	private JLabel khuyenMaiIcon;
	private JLabel chuyenTauIcon;
	private JLabel arrowIconNV;
	private JLabel arrowIconKM;
	private JButton btnTK;
	private JLabel thongKeIcon;
	private JLabel arrowIconTK;
	private JPanel subMenuTK;
	private JButton btnQLT;
	private JLabel tauIcon;
	private JLabel arrowIconTau;
	private JPanel subMenuQLT;
	private JLabel timKiemIcon;
	private JButton btnTimKiem;
	
	private MainFrame mainFrame;

	public MenuPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setPreferredSize(new Dimension(220, 700));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1)); 

		// Container chính cho phần menu
		menuContainer = new JPanel();
		menuContainer.setLayout(new BoxLayout(menuContainer, BoxLayout.Y_AXIS));
		menuContainer.setBackground(Color.WHITE);

		// Logo
		ImageIcon logoIcon = new ImageIcon("src/resources/train.png");
		Image img = logoIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(img));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		menuContainer.add(logo);

		// Search box bo tròn
		btnTimKiem = createMainButton("");
		timKiemIcon = new JLabel(new ImageIcon(new File("images//IconTimKiem.jpg").getAbsolutePath()));
		btnTimKiem.setLayout(new BorderLayout());
		btnTimKiem.add(timKiemIcon, BorderLayout.WEST);
		JTextField txtTimKiem = new JTextField(" Tìm kiếm ...");
		txtTimKiem.setDisabledTextColor(Color.BLACK);
		txtTimKiem.setMaximumSize(new Dimension(180, 35));
		txtTimKiem.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTimKiem.setBackground(new Color(240, 240, 240));
		txtTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtTimKiem.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnTimKiem.add(txtTimKiem,BorderLayout.CENTER);
		menuContainer.add(Box.createHorizontalStrut(5));
		menuContainer.add(btnTimKiem);
		menuContainer.add(Box.createVerticalStrut(10));
		
		txtTimKiem.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent evt) {
		        // Xóa chữ "Search ..." khi người dùng click vào
		        if (txtTimKiem.getText().equals(" Tìm kiếm ...")) {
		            txtTimKiem.setText("");
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent evt) {
		        // Hiện lại chữ "Search ..." nếu ô trống
		        if (txtTimKiem.getText().isEmpty()) {
		            txtTimKiem.setText(" Tìm kiếm ...");
		        }
		    }
		});

		// Quản lý nhân viên + submenu
		btnQLNV = createMainButton("           Quản lý nhân viên");
		nhanVienIcon = new JLabel(new ImageIcon(new File("images//IconNhanVien.jpg").getAbsolutePath()));
		arrowIconNV = new JLabel("►");
		arrowIconNV.setFont(new Font("Arial", Font.BOLD, 12));
		btnQLNV.setLayout(new BorderLayout());
		btnQLNV.add(nhanVienIcon, BorderLayout.WEST);
		btnQLNV.add(arrowIconNV, BorderLayout.EAST);
		btnQLNV.addActionListener(e -> toggleSubMenu(btnQLNV));
		menuContainer.add(btnQLNV);

		subMenuQLNV = new JPanel();
		subMenuQLNV.setLayout(new BoxLayout(subMenuQLNV, BoxLayout.Y_AXIS));
		subMenuQLNV.setBackground(Color.WHITE);
		subMenuQLNV.setVisible(false);

		String[] subItemsQLVN = { "➕ Đặt vé", "🔁 Đổi thông tin vé", "❌ Hủy vé" };
		for (String sub : subItemsQLVN) {
			JButton subBtn = createSubButton(sub);
			subButtons.add(subBtn);
			subMenuQLNV.add(subBtn);
		}
		menuContainer.add(subMenuQLNV);

		// Quản lý khuyến mãi + submenu
		btnQLKM = createMainButton("           Quản lý khuyến mãi");
		khuyenMaiIcon = new JLabel(new ImageIcon(new File("images//IconKhuyenMai.png").getAbsolutePath()));
		arrowIconKM = new JLabel("►");
		arrowIconKM.setFont(new Font("Arial", Font.BOLD, 12));
		btnQLKM.setLayout(new BorderLayout());
		btnQLKM.add(khuyenMaiIcon, BorderLayout.WEST);
		btnQLKM.add(arrowIconKM, BorderLayout.EAST);
		btnQLKM.addActionListener(e -> toggleSubMenu(btnQLKM));
		menuContainer.add(btnQLKM);

		subMenuQLKM = new JPanel();
		subMenuQLKM.setLayout(new BoxLayout(subMenuQLKM, BoxLayout.Y_AXIS));
		subMenuQLKM.setBackground(Color.WHITE);
		subMenuQLKM.setVisible(false);

		String[] subItemsQLKM = { "➕ Đặt vé", "🔁 Đổi thông tin vé", "❌ Hủy vé" };
		for (String sub : subItemsQLKM) {
			JButton subBtn = createSubButton(sub);
			subButtons.add(subBtn);
			subMenuQLKM.add(subBtn);
		}
		menuContainer.add(subMenuQLKM);

		// Quản lý chuyến tàu
		btnQLCT = createMainButton("           Quản lý chuyến tàu");
		chuyenTauIcon = new JLabel(new ImageIcon(new File("images//IconChuyenDi.jpg").getAbsolutePath()));
		btnQLCT.setLayout(new BorderLayout());
		btnQLCT.add(chuyenTauIcon, BorderLayout.WEST);
		btnQLCT.addActionListener(e -> mainFrame.showCard("qlChuyenTau", "Quản lý chuyến tàu"));
		menuContainer.add(btnQLCT);

		// Thống kê + submenu
		btnTK = createMainButton("           Thống kê");
		thongKeIcon = new JLabel(new ImageIcon(new File("images//IconThongKe.jpg").getAbsolutePath()));
		arrowIconTK = new JLabel("►");
		arrowIconTK.setFont(new Font("Arial", Font.BOLD, 12));
		btnTK.setLayout(new BorderLayout());
		btnTK.add(thongKeIcon, BorderLayout.WEST);
		btnTK.add(arrowIconTK, BorderLayout.EAST);
		btnTK.addActionListener(e -> toggleSubMenu(btnTK));
		menuContainer.add(btnTK);

		subMenuTK = new JPanel();
		subMenuTK.setLayout(new BoxLayout(subMenuTK, BoxLayout.Y_AXIS));
		subMenuTK.setBackground(Color.WHITE);
		subMenuTK.setVisible(false);

		String[] subItemsTK = { "+ Theo lượng vé", "+ Theo tình trạng ghế", "+ Theo khuyến mãi", "+ Theo tình trạng tàu", "+ Theo doanh thu" };
		for (String sub : subItemsTK) {
			JButton subBtn = createSubButton(sub);
			subButtons.add(subBtn);
			subMenuTK.add(subBtn);
		}
		menuContainer.add(subMenuTK);

		// Quản lý tàu + submenu
		btnQLT = createMainButton("           Quản lý tàu");
		tauIcon = new JLabel(new ImageIcon(new File("images//IconTau.jpg").getAbsolutePath()));
		arrowIconTau = new JLabel("►");
		arrowIconTau.setFont(new Font("Arial", Font.BOLD, 12));
		btnQLT.setLayout(new BorderLayout());
		btnQLT.add(tauIcon, BorderLayout.WEST);
//		btnQLT.add(arrowIconTau, BorderLayout.EAST);
		btnQLT.addActionListener(e -> mainFrame.showCard("qlTau", "Quản lý tàu"));
		menuContainer.add(btnQLT);

//		subMenuQLT = new JPanel();
//		subMenuQLT.setLayout(new BoxLayout(subMenuQLT, BoxLayout.Y_AXIS));
//		subMenuQLT.setBackground(Color.WHITE);
//		subMenuQLT.setVisible(false);
//
//		String[] subItemsQLT = { "➕ Đặt vé", "🔁 Đổi thông tin vé", "❌ Hủy vé" };
//		for (String sub : subItemsQLT) {
//			JButton subBtn = createSubButton(sub);
//			subButtons.add(subBtn);
//			subMenuQLT.add(subBtn);
//		}
//		menuContainer.add(subMenuQLT);

		add(menuContainer, BorderLayout.NORTH);
	}

	// Mở / đóng submenu
	private void toggleSubMenu(JButton clickedButton) {
		if (clickedButton == btnQLNV) {
			subMenuQLNV.setVisible(!subMenuQLNV.isVisible());
			arrowIconNV.setText(subMenuQLNV.isVisible() ? "▼" : "►");
			
	        subMenuQLKM.setVisible(false);
	        arrowIconKM.setText("►");
	        
	        subMenuTK.setVisible(false);
	        arrowIconTK.setText("►");
	        
//	        subMenuQLT.setVisible(false);
//	        arrowIconTau.setText("►");
		}
		if (clickedButton == btnQLKM) {
			subMenuQLKM.setVisible(!subMenuQLKM.isVisible());
			arrowIconKM.setText(subMenuQLKM.isVisible() ? "▼" : "►");
			
			subMenuQLNV.setVisible(false);
			arrowIconNV.setText("►");
	        
	        subMenuTK.setVisible(false);
	        arrowIconTK.setText("►");
	        
//	        subMenuQLT.setVisible(false);
//	        arrowIconTau.setText("►");
		}
		if (clickedButton == btnTK) {
			subMenuTK.setVisible(!subMenuTK.isVisible());
			arrowIconTK.setText(subMenuTK.isVisible() ? "▼" : "►");
			
	        subMenuQLKM.setVisible(false);
	        arrowIconKM.setText("►");
	        
	        subMenuQLNV.setVisible(false);
	        arrowIconNV.setText("►");
	        
//	        subMenuQLT.setVisible(false);
//	        arrowIconTau.setText("►");
		}
//		if (clickedButton == btnQLT) {
//			subMenuQLT.setVisible(!subMenuQLT.isVisible());
//			arrowIconTau.setText(subMenuQLT.isVisible() ? "▼" : "►");
//			
//	        subMenuQLKM.setVisible(false);
//	        arrowIconKM.setText("►");
//	        
//	        subMenuTK.setVisible(false);
//	        arrowIconTK.setText("►");
//	        
//	        subMenuQLNV.setVisible(false);
//	        arrowIconNV.setText("►");
//		}

	}

	// Nút menu chính
	private JButton createMainButton(String text) {
		JButton button = new JButton(text);
		button.setName(text);
		button.setFocusPainted(false);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setMaximumSize(new Dimension(200, 40));
		button.setFont(new Font("SansSerif", Font.BOLD, 14)); // Font in đậm
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10));
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button.setToolTipText(text);
		return button;
	}

	// Nút submenu
	private JButton createSubButton(String text) {
		JButton button = new JButton(text);
		button.setName(text);
		button.setFocusPainted(false);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setMaximumSize(new Dimension(200, 35));
		button.setFont(new Font("SansSerif", Font.BOLD, 13)); // Font in đậm
		button.setBackground(new Color(255, 255, 255));
		button.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 10));
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		return button;
	}

//	// MAIN test
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("Sidebar Demo");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(new BorderLayout());
//
//		MenuPanel menuGUI = new MenuPanel();
//		frame.add(menuGUI, BorderLayout.WEST);
//
//		frame.setSize(300, 700);
//		frame.setVisible(true);
//	}
}
