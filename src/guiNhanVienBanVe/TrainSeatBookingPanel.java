package guiNhanVienBanVe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TrainSeatBookingPanel extends JPanel {
    private JPanel seatPanelContainer;
    private JPanel tierPanel;
    private JButton btnDatVe;
    private Set<JButton> selectedSeats = new HashSet<>();
    private Set<JButton> bookedSeats = new HashSet<>();
    private String selectedSeatType = "";
    private String selectedCoach = "";

    public TrainSeatBookingPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        add(Box.createVerticalStrut(10));
        add(createSeatTypePanel());
        add(Box.createVerticalStrut(15));
        add(createCoachSelectionPanel());
        add(Box.createVerticalStrut(15));

        tierPanel = createTierPanel();
        tierPanel.setVisible(false);
        add(tierPanel);

        seatPanelContainer = new JPanel(new BorderLayout());
        seatPanelContainer.setBackground(Color.WHITE);
        seatPanelContainer.setVisible(false);
        add(seatPanelContainer);

        add(Box.createVerticalStrut(15));
        add(createBookButton());
    }

    private JPanel createSeatTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Chọn loại ghế:");
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(label);

        String[] seatTypes = {"Ghế cứng", "Ghế mềm", "Giường nằm"};
        ButtonGroup group = new ButtonGroup();

        for (String type : seatTypes) {
            JToggleButton btn = createOptionButton(type);
            group.add(btn);
            panel.add(btn);

            btn.addActionListener(e -> {
                if (btn.isSelected()) {
                    selectedSeatType = type;
                    tierPanel.setVisible(type.equals("Giường nằm"));
                    seatPanelContainer.setVisible(false);
                    btnDatVe.setVisible(false);
                    updateCoachSelection();
                }
            });
        }
        return panel;
    }

    private JPanel createCoachSelectionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);
        panel.setName("coachPanel"); // Đặt tên để có thể tìm lại sau này

        JLabel label = new JLabel("Chọn toa:");
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(label);

        return panel;
    }

    private void updateCoachSelection() {
        JPanel coachPanel = null;
        for (Component comp : getComponents()) {
            if (comp instanceof JPanel && "coachPanel".equals(((JPanel)comp).getName())) {
                coachPanel = (JPanel)comp;
                break;
            }
        }

        if (coachPanel == null) return;

        // Xóa các nút toa cũ
        Component[] components = coachPanel.getComponents();
        for (int i = 1; i < components.length; i++) {
            coachPanel.remove(components[i]);
        }

        ButtonGroup group = new ButtonGroup();
        
        // Thêm các toa phù hợp với loại ghế đã chọn
        if ("Ghế cứng".equals(selectedSeatType)) {
            addCoachButtons(coachPanel, group, new int[]{1, 4, 7});
        } else if ("Ghế mềm".equals(selectedSeatType)) {
            addCoachButtons(coachPanel, group, new int[]{2, 5, 8});
        } else if ("Giường nằm".equals(selectedSeatType)) {
            addCoachButtons(coachPanel, group, new int[]{3, 6, 9});
        }

        coachPanel.revalidate();
        coachPanel.repaint();
    }

    private void addCoachButtons(JPanel panel, ButtonGroup group, int[] coachNumbers) {
        for (int num : coachNumbers) {
            JToggleButton btn = createOptionButton("Toa " + num);
            group.add(btn);
            panel.add(btn);
            
            btn.addActionListener(e -> {
                if (btn.isSelected()) {
                    selectedCoach = "Toa " + num;
                    showSeatSelection(selectedSeatType);
                    btnDatVe.setVisible(true);
                }
            });
        }
    }

    private JPanel createTierPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Chọn tầng:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(label);

        String[] tiers = {"Tầng 1", "Tầng 2"};
        ButtonGroup group = new ButtonGroup();

        for (String tier : tiers) {
            JToggleButton btn = createOptionButton(tier);
            group.add(btn);
            panel.add(btn);
            btn.addActionListener(e -> {
                if (btn.isSelected()) {
                    populateSeatLayout(tier.equals("Tầng 1") ? 1 : 2);
                }
            });
        }
        return panel;
    }

    private JButton createBookButton() {
        btnDatVe = new JButton("Đặt vé");
        btnDatVe.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnDatVe.setBackground(new Color(255, 140, 0));
        btnDatVe.setForeground(Color.WHITE);
        btnDatVe.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDatVe.setVisible(false);
        btnDatVe.addActionListener(e -> bookSelectedSeats());
        return btnDatVe;
    }

    private void showSeatSelection(String seatType) {
        if (selectedCoach.isEmpty()) return;
        
        seatPanelContainer.removeAll();
        seatPanelContainer.setVisible(true);
        selectedSeats.clear();

        if (!seatType.equals("Giường nằm")) {
            SeatMapPanel seatMap = new SeatMapPanel(seatType, selectedSeats, bookedSeats);
            seatPanelContainer.add(seatMap, BorderLayout.CENTER);
        }
        revalidate();
    }

    private void populateSeatLayout(int tier) {
        seatPanelContainer.removeAll();
        selectedSeats.clear();

        SeatMapPanel seatMap = new SeatMapPanel("Giường " + tier, selectedSeats, bookedSeats);
        seatPanelContainer.add(seatMap, BorderLayout.CENTER);
        revalidate();
    }

    private void bookSelectedSeats() {
        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một chỗ ngồi!");
            return;
        }

        bookedSeats.addAll(selectedSeats);
        selectedSeats.forEach(btn -> {
            btn.setBackground(SeatMapPanel.BOOKED_COLOR);
            btn.setEnabled(false);
        });
        selectedSeats.clear();
        JOptionPane.showMessageDialog(this, "Đặt vé thành công");
    }

    private JToggleButton createOptionButton(String text) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0)));
        btn.setPreferredSize(new Dimension(100, 35));
        return btn;
    }
}