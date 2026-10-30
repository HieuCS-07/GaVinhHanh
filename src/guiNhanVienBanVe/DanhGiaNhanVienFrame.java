package guiNhanVienBanVe;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class DanhGiaNhanVienFrame extends JFrame {

    private Sidebar sidebar;
    private JComboBox<String> staffComboBox;
    private JTextArea feedbackTextArea;
    private JSlider ratingSlider;
    private JLabel ratingValueLabel;
    private JButton submitButton;
    private JPanel contentPanel;
    private final Color PRIMARY_COLOR = new Color(52, 152, 219); // Màu xanh dương chủ đạo
    private final Color SECONDARY_COLOR = new Color(236, 240, 241); // Màu xám nhạt thứ cấp
    private final Color ACCENT_COLOR = new Color(27, 188, 155); // Màu xanh lá cây nhấn mạnh
    private final Color TEXT_COLOR = new Color(52, 73, 94); // Màu chữ tối

    public DanhGiaNhanVienFrame() {
        setTitle("Đánh Giá Nhân Viên");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(SECONDARY_COLOR); // Màu nền cho JFrame

        setLayout(new BorderLayout());

        // Tạo Sidebar
        sidebar = new Sidebar(null);
        add(sidebar, BorderLayout.WEST);

        // Tạo panel chứa nội dung chính
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(15, 15));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(SECONDARY_COLOR); // Màu nền cho contentPanel

        // Phần chọn nhân viên
        JPanel staffPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        staffPanel.setBackground(SECONDARY_COLOR);
        JLabel staffLabel = new JLabel("Chọn Nhân Viên:");
        staffLabel.setForeground(TEXT_COLOR);
        List<String> staffList = Arrays.asList("Nguyễn Văn A", "Trần Thị B", "Lê Hoàng C", "Phạm Thu D");
        staffComboBox = new JComboBox<>(staffList.toArray(new String[0]));
        staffComboBox.setPreferredSize(new Dimension(250, 30));
        staffComboBox.setBackground(Color.WHITE);
        staffComboBox.setForeground(TEXT_COLOR);
        staffPanel.add(staffLabel);
        staffPanel.add(staffComboBox);
        contentPanel.add(staffPanel, BorderLayout.NORTH);

        // Phần đánh giá (Slider)
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.setBackground(SECONDARY_COLOR);
        JLabel ratingLabel = new JLabel("Đánh Giá (1 - 5 Sao):");
        ratingLabel.setForeground(TEXT_COLOR);
        ratingSlider = new JSlider(1, 5, 3);
        ratingSlider.setPreferredSize(new Dimension(200, 40));
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setForeground(TEXT_COLOR);
        ratingSlider.setBackground(SECONDARY_COLOR);
        ratingValueLabel = new JLabel("3 Sao");
        ratingValueLabel.setPreferredSize(new Dimension(50, 30));
        ratingValueLabel.setForeground(TEXT_COLOR);

        ratingSlider.addChangeListener(e -> {
            ratingValueLabel.setText(ratingSlider.getValue() + " Sao");
        });

        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingSlider);
        ratingPanel.add(ratingValueLabel);
        contentPanel.add(ratingPanel, BorderLayout.CENTER);

        // Phần phản hồi (TextArea)
        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackPanel.setBackground(SECONDARY_COLOR);
        JLabel feedbackLabel = new JLabel("Phản Hồi/Góp Ý:");
        feedbackLabel.setForeground(TEXT_COLOR);
        feedbackTextArea = new JTextArea(5, 20);
        feedbackTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        feedbackTextArea.setForeground(TEXT_COLOR);
        feedbackTextArea.setBackground(Color.WHITE);
        JScrollPane feedbackScrollPane = new JScrollPane(feedbackTextArea);
        feedbackPanel.add(feedbackLabel, BorderLayout.NORTH);
        feedbackPanel.add(feedbackScrollPane, BorderLayout.CENTER);
        contentPanel.add(feedbackPanel, BorderLayout.SOUTH);

        // Nút gửi đánh giá
        submitButton = new JButton("Gửi Đánh Giá");
        submitButton.setBackground(ACCENT_COLOR);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(SECONDARY_COLOR);
        submitButton.setPreferredSize(new Dimension(120, 35));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStaff = (String) staffComboBox.getSelectedItem();
                int rating = ratingSlider.getValue();
                String feedback = feedbackTextArea.getText();

                JOptionPane.showMessageDialog(DanhGiaNhanVienFrame.this,
                        "Đánh giá cho nhân viên " + selectedStaff + "\n" +
                        "Đánh giá: " + rating + " sao\n" +
                        "Phản hồi: " + feedback,
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(submitButton);
        contentPanel.add(buttonPanel, BorderLayout.EAST);

        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        SwingUtilities.invokeLater(DanhGiaNhanVienFrame::new);
    }
}