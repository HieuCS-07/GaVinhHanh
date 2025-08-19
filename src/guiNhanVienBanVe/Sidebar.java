package guiNhanVienBanVe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    private JPanel subMenu;
    private JButton btnVeTau;
    private JLabel arrowIcon;
    private JPanel menuContainer;
    private ArrayList<JButton> menuButtons = new ArrayList<>();
    private ArrayList<JButton> subButtons = new ArrayList<>();
    private MainFrame parentFrame; // Không còn cần thiết cho việc hiển thị frame độc lập

    public Sidebar() { // Loại bỏ tham số MainFrame
        setPreferredSize(new Dimension(220, 700));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

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

        // Search box
        JTextField searchField = new JTextField("🔍 Tra cứu");
        searchField.setEnabled(false);
        searchField.setDisabledTextColor(Color.BLACK);
        searchField.setMaximumSize(new Dimension(180, 35));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBackground(new Color(240, 240, 240));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuContainer.add(searchField);
        menuContainer.add(Box.createVerticalStrut(10));

        // Vé tàu + submenu
        btnVeTau = createMainButton("🖨️  Vé tàu");
        arrowIcon = new JLabel(">");
        arrowIcon.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnVeTau.setLayout(new BorderLayout());
        btnVeTau.add(arrowIcon, BorderLayout.EAST);
        btnVeTau.addActionListener(e -> toggleSubMenu());
        menuContainer.add(btnVeTau);

        subMenu = new JPanel();
        subMenu.setLayout(new BoxLayout(subMenu, BoxLayout.Y_AXIS));
        subMenu.setBackground(Color.WHITE);
        subMenu.setVisible(false);

        String[] subItems = { "➕ Đặt vé", "🔁 Đổi thông tin vé", "❌ Hủy vé" };
        for (String sub : subItems) {
            JButton subBtn = createSubButton(sub);
            subButtons.add(subBtn);
            subMenu.add(subBtn);
        }
        menuContainer.add(subMenu);

        // Thống kê
        JButton btnThongKe = createMainButton("📊 Thống kê...");
        menuButtons.add(btnThongKe);
        menuContainer.add(btnThongKe);

        // Quản lý nhân viên + submenu
        JButton btnQLNhanVien = createMainButton("👥 Quản lý nhân viên");
        JLabel arrowNhanVien = new JLabel(">");
        arrowNhanVien.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnQLNhanVien.setLayout(new BorderLayout());
        btnQLNhanVien.add(arrowNhanVien, BorderLayout.EAST);
        menuButtons.add(btnQLNhanVien);
        menuContainer.add(btnQLNhanVien);

        JPanel subNhanVien = new JPanel();
        subNhanVien.setLayout(new BoxLayout(subNhanVien, BoxLayout.Y_AXIS));
        subNhanVien.setBackground(Color.WHITE);
        subNhanVien.setVisible(false);

        String[] subNVItems = {
                "📅 Phân công ca làm việc", "➕ Thêm nhân viên", "⭐ Đánh giá nhân viên", "🔍 Tra cứu thông tin nhân viên"
        };
        for (String sub : subNVItems) {
            JButton subBtn = createSubButton(sub);
            subButtons.add(subBtn);
            subNhanVien.add(subBtn);

            // Gắn sự kiện chuyển trang
            subBtn.addActionListener(e -> {
                switch (sub) {
                    case "📅 Phân công ca làm việc":
                    	new PhanCongCaLamViecFrame().setVisible(true);
                        break;
                    case "➕ Thêm nhân viên":
                        JOptionPane.showMessageDialog(this, "Chức năng thêm nhân viên đang phát triển");
                        break;
                    case "⭐ Đánh giá nhân viên":
                        new DanhGiaNhanVienFrame(); // Tạo và hiển thị DanhGiaNhanVienFrame
                        break;
                    case "🔍 Tra cứu thông tin nhân viên":
                        JOptionPane.showMessageDialog(this, "Chức năng tra cứu đang phát triển");
                        break;
                }
            });
        }
        menuContainer.add(subNhanVien);

        btnQLNhanVien.addActionListener(e -> {
            subNhanVien.setVisible(!subNhanVien.isVisible());
            arrowNhanVien.setText(subNhanVien.isVisible() ? "▼" : ">");
        });

        // Các menu khác
        String[] otherMenus = {
                "🚌 Quản lý chuyến", "👤 Quản lý khách hàng", "📅 Quản lý ca làm việc"
        };
        for (String item : otherMenus) {
            JButton btn = createMainButton(item);
            menuButtons.add(btn);
            menuContainer.add(btn);
        }

        add(menuContainer, BorderLayout.NORTH);
    }

    public ArrayList<JButton> getSubButtons() {
        return subButtons;
    }

    private void toggleSubMenu() {
        subMenu.setVisible(!subMenu.isVisible());
        arrowIcon.setText(subMenu.isVisible() ? "▼" : ">");
    }

    private JButton createMainButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    private JButton createSubButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(200, 35));
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setBackground(new Color(245, 245, 245));
        button.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 10));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    // Constructor để nhận MainFrame (sử dụng khi Sidebar nằm trong MainFrame)
    public Sidebar(MainFrame parentFrame) {
        this(); // Gọi constructor mặc định để khởi tạo giao diện
        this.parentFrame = parentFrame;
    }
}
