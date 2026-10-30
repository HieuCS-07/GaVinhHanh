package guiNhanVIenQuanLy;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel panelMainContent; // vùng nội dung thay đổi
    private CardLayout cardLayout;
	private MenuPanel menu;
	private TopBarPanel topBar;

    public MainFrame() {
        setTitle("Hệ thống quản lý");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Khởi tạo layout
        setLayout(new BorderLayout());
        
        // Menu bên trái
        menu = new MenuPanel(this); // truyền MainFrame để gọi showCard
        menu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        add(menu, BorderLayout.WEST);

        // Top Bar
        topBar = new TopBarPanel(this, menu, "Trang chủ");
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(topBar, BorderLayout.NORTH);

        // Panel nội dung trung tâm
        cardLayout = new CardLayout();
        panelMainContent = new JPanel(cardLayout);

        // Các card
        panelMainContent.add(new TrangChuPanel(), "trangChu");
        panelMainContent.add(new QuanLyChuyenTauPanel(), "qlChuyenTau");
        panelMainContent.add(new QuanLyTauPanel(), "qlTau");
        
        // Vùng nội dung trung tâm
        add(panelMainContent, BorderLayout.CENTER);
    }

    // Hàm cho phép panel menu gọi để đổi nội dung
    public void showCard(String name, String title) {
    	topBar.setTieuDe(title);
        cardLayout.show(panelMainContent, name);
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}

