package guiNhanVIenQuanLy;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TopBarPanel extends JPanel {
	private JLabel lblTitle;
	private MainFrame mainFrame;

	public TopBarPanel(MainFrame mainFrame, MenuPanel menuLeft, String title) {
		this.mainFrame = mainFrame;
		
		setPreferredSize(new Dimension(220, 100));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		// Tiêu đề
		lblTitle = new JLabel(title, SwingConstants.CENTER);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(lblTitle, BorderLayout.CENTER);

		// Avatar nhân viên
		JLabel avatar = new JLabel("👤");
		avatar.setFont(new Font("SansSerif", Font.PLAIN, 26));
		avatar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		add(avatar, BorderLayout.EAST);

		// ======= PANEL TRÁI: CHỨA NÚT VÀ LOGO =======
        JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelLeft.setBackground(Color.WHITE);
		
		// Nút toggle menu (hamburger)
		JButton toggleSidebarBtn = new JButton(new ImageIcon(new File("images/IconHamburger.png").getAbsolutePath()));
		toggleSidebarBtn.setBorderPainted(false);
		toggleSidebarBtn.setContentAreaFilled(false);
		toggleSidebarBtn.setFocusPainted(false);

		// Logo (ảnh)
		JButton btnLogo = new JButton();
        ImageIcon iconLogo = new ImageIcon(new File("images//logo.png").getAbsolutePath());
        Image scaled = iconLogo.getImage().getScaledInstance(128, 96, Image.SCALE_SMOOTH);
        btnLogo.setBorderPainted(false);
        btnLogo.setContentAreaFilled(false);
        btnLogo.setFocusPainted(false);
        btnLogo.setIcon(new ImageIcon(scaled));
        btnLogo.addActionListener(e -> mainFrame.showCard("trangChu", "Trang chủ"));
        
        // Thêm nút và logo vào panel trái
        panelLeft.add(toggleSidebarBtn);
        panelLeft.add(btnLogo);

        // Thêm panel trái vào WEST
        add(panelLeft, BorderLayout.WEST);
		
		// Toggle Sidebar
		toggleSidebarBtn.addActionListener(e -> {
			boolean sidebarVisible = menuLeft.isVisible();
			menuLeft.setVisible(!sidebarVisible);
		});
	}
	
	public void setTieuDe(String text) {
		lblTitle.setText(text);
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			JFrame mainFrame = new JFrame("Demo Giao Diện");
//			MenuPanel sidebar = new MenuPanel(mainFrame);
//			TopBarPanel topBar = new TopBarPanel(sidebar,"");
//			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			mainFrame.setSize(700, 700);
//			mainFrame.setLayout(new BorderLayout());
//			mainFrame.add(sidebar, BorderLayout.WEST);
//			mainFrame.add(topBar, BorderLayout.NORTH);
//			mainFrame.setLocationRelativeTo(null);
//			mainFrame.setVisible(true);
//		});
//	}
}
