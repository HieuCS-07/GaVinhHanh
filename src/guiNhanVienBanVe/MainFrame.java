package guiNhanVienBanVe;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Hệ thống quản lý");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar(this);
        add(sidebar, BorderLayout.WEST);

        // Panel chính ở giữa
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Chào mừng đến hệ thống!", SwingConstants.CENTER), BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }

    // Hàm để set nội dung chính giữa
    public void setContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

