package guiNhanVienBanVe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class PhanCongCaLamViecFrame extends JFrame {

    public PhanCongCaLamViecFrame() {
        setTitle("Phân Công Ca Làm Việc");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top bar
        JPanel topBar = createTopBar();
        add(topBar, BorderLayout.NORTH);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.add(createJobTablePanel(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Right side panel
        JPanel rightPanel = createRightPanel();
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createTopBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Good afternoon, Jane");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(title, BorderLayout.WEST);

        JButton btnNewOrder = new JButton("New Order");
        btnNewOrder.setBackground(new Color(102, 102, 255));
        btnNewOrder.setForeground(Color.WHITE);
        btnNewOrder.setFocusPainted(false);
        panel.add(btnNewOrder, BorderLayout.EAST);

        return panel;
    }

    private JScrollPane createJobTablePanel() {
        String[] columns = {"Nhân viên", "Công việc", "Ngày", "Thời gian", "Địa điểm"};
        Object[][] data = {
                {"Esther Howard", "Phục vụ", "Thứ 2", "6:00 AM - 9:00 AM", "Chi nhánh A"},
                {"Jane Cooper", "Bán vé", "Thứ 2", "9:00 AM - 5:00 PM", "Palm Beach"},
                {"Wade Warren", "Kiểm soát vé", "Thứ 2", "9:00 AM - 5:00 PM", "Chi nhánh C"}
        };

        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        return new JScrollPane(table);
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(300, getHeight()));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        // Calendar section
        JLabel lblCalendar = new JLabel("Lịch");
        lblCalendar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCalendar.setAlignmentX(Component.CENTER_ALIGNMENT);

        LocalDate today = LocalDate.now();
        JLabel lblToday = new JLabel(today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                ", " + today.getDayOfMonth() + " " +
                today.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        lblToday.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblToday.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblCalendar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblToday);
        panel.add(Box.createVerticalGlue());

        // Rating Section
        JLabel lblRating = new JLabel("danh sach nhan vien");
        lblRating.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblRating.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblRating);

        // Placeholder avatars
        JPanel avatarPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        for (int i = 0; i < 12; i++) {
            JLabel avatar = new JLabel();
            avatar.setPreferredSize(new Dimension(40, 40));
            avatar.setOpaque(true);
            avatar.setBackground(new Color(200 + (i * 3 % 55), 180 + (i * 5 % 55), 220)); // màu giả
            avatar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            avatarPanel.add(avatar);
        }

        panel.add(Box.createVerticalStrut(10));
        panel.add(avatarPanel);

        return panel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new PhanCongCaLamViecFrame().setVisible(true));
    }
}
