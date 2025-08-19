package guiNhanVienBanVe;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.HashSet;

public class SeatMapPanel extends JPanel {
    private final String seatType;
    private final Set<JButton> selectedSeats;
    private final Set<JButton> bookedSeats;
    
    // Màu pastel
    public static final Color AVAILABLE_COLOR = new Color(189, 224, 254);
    public static final Color SELECTED_COLOR = new Color(255, 195, 160);
    public static final Color BOOKED_COLOR = new Color(220, 220, 220);
    public static final Color TEXT_COLOR = new Color(50, 50, 50);
    public static final Color BED_AVAILABLE_COLOR = new Color(210, 180, 140);

    public SeatMapPanel(String seatType, Set<JButton> selectedSeats, Set<JButton> bookedSeats) {
        this.seatType = seatType;
        this.selectedSeats = selectedSeats;
        this.bookedSeats = bookedSeats;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 10));
        
        initSeatMap();
        add(createLegendPanel(), BorderLayout.SOUTH);
    }

    private void initSeatMap() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.WHITE);
        
        JPanel mapPanel = new JPanel();
        mapPanel.setBackground(Color.WHITE);
        
        if (isBedType()) {
            setupBedLayout(mapPanel);
        } else {
            setupSeatLayout(mapPanel);
        }
        
        container.add(mapPanel);
        add(container, BorderLayout.CENTER);
    }

    private void setupSeatLayout(JPanel panel) {
        panel.setLayout(new BorderLayout());
        
        JPanel seatsPanel = new JPanel();
        seatsPanel.setLayout(new BoxLayout(seatsPanel, BoxLayout.X_AXIS));
        seatsPanel.setBackground(Color.WHITE);
        
        seatsPanel.add(createSeatColumn("A", 1, 10));
        
        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new BoxLayout(gapPanel, BoxLayout.Y_AXIS));
        gapPanel.setBackground(Color.WHITE);
        gapPanel.add(Box.createVerticalGlue());
        
        JLabel walkwayLabel = new JLabel("Lối đi");
        walkwayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        walkwayLabel.setForeground(new Color(120, 120, 120));
        walkwayLabel.setAlignmentX(CENTER_ALIGNMENT);
        gapPanel.add(walkwayLabel);
        
        gapPanel.add(Box.createVerticalGlue());
        gapPanel.setPreferredSize(new Dimension(40, 0));
        
        seatsPanel.add(gapPanel);
        seatsPanel.add(createSeatColumn("B", 11, 20));
        
        panel.add(seatsPanel, BorderLayout.CENTER);
    }

    private void setupBedLayout(JPanel panel) {
        panel.setLayout(new GridLayout(2, 3, 15, 15));
        
        for (int i = 1; i <= 6; i++) {
            JButton bed = createBedButton(seatType + "-" + i);
            panel.add(bed);
        }
    }

    private JPanel createSeatColumn(String prefix, int start, int end) {
        JPanel column = new JPanel();
        column.setLayout(new GridLayout(5, 2, 8, 12));
        column.setBackground(Color.WHITE);
        
        for (int i = start; i <= end; i++) {
            JButton seat = createSeatButton(prefix + (i-start+1));
            column.add(seat);
            
            if (i % 7 == 0 || i == 3) {
                seat.setBackground(BOOKED_COLOR);
                seat.setEnabled(false);
                bookedSeats.add(seat);
            }
        }
        return column;
    }

    private JButton createSeatButton(String label) {
        JButton btn = new JButton(label) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(TEXT_COLOR);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBackground(AVAILABLE_COLOR);
        btn.setPreferredSize(new Dimension(48, 36));
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setBorder(BorderFactory.createEmptyBorder());
        
        btn.addActionListener(e -> toggleSeatSelection(btn));
        return btn;
    }

    private JButton createBedButton(String label) {
        JButton btn = new JButton(label) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(TEXT_COLOR);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBackground(BED_AVAILABLE_COLOR);
        btn.setPreferredSize(new Dimension(80, 40));
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setBorder(BorderFactory.createEmptyBorder());
        
        btn.addActionListener(e -> toggleSeatSelection(btn));
        return btn;
    }

    private void toggleSeatSelection(JButton btn) {
        if (bookedSeats.contains(btn)) return;

        if (selectedSeats.contains(btn)) {
            btn.setBackground(isBedType() ? BED_AVAILABLE_COLOR : AVAILABLE_COLOR);
            selectedSeats.remove(btn);
        } else {
            btn.setBackground(SELECTED_COLOR);
            selectedSeats.add(btn);
        }
    }

    private JPanel createLegendPanel() {
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        legend.setBackground(Color.WHITE);
        
        legend.add(createLegendItem(AVAILABLE_COLOR, "Có sẵn"));
        legend.add(createLegendItem(SELECTED_COLOR, "Đang chọn"));
        legend.add(createLegendItem(BOOKED_COLOR, "Đã đặt"));
        
        return legend;
    }

    private JPanel createLegendItem(Color color, String text) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        item.setBackground(Color.WHITE);
        
        JLabel colorLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
            }
        };
        colorLabel.setPreferredSize(new Dimension(16, 16));
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textLabel.setForeground(TEXT_COLOR);
        
        item.add(colorLabel);
        item.add(textLabel);
        return item;
    }

    public void selectAll() {
        for (Component comp : getComponents()) {
            if (comp instanceof JPanel container) {
                for (Component c : container.getComponents()) {
                    if (c instanceof JPanel panel) {
                        for (Component btn : panel.getComponents()) {
                            if (btn instanceof JButton && !bookedSeats.contains(btn)) {
                                JButton button = (JButton) btn;
                                button.setBackground(SELECTED_COLOR);
                                selectedSeats.add(button);
                            }
                        }
                    }
                }
            }
        }
    }

    public void clearAll() {
        for (Component comp : getComponents()) {
            if (comp instanceof JPanel container) {
                for (Component c : container.getComponents()) {
                    if (c instanceof JPanel panel) {
                        for (Component btn : panel.getComponents()) {
                            if (btn instanceof JButton) {
                                JButton button = (JButton) btn;
                                if (selectedSeats.contains(button)) {
                                    button.setBackground(isBedType() ? BED_AVAILABLE_COLOR : AVAILABLE_COLOR);
                                }
                            }
                        }
                    }
                }
            }
        }
        selectedSeats.removeIf(btn -> getAllSeatButtons().contains(btn));
    }

    public Set<JButton> getAllSeatButtons() {
        Set<JButton> allButtons = new HashSet<>();
        for (Component comp : getComponents()) {
            if (comp instanceof JPanel container) {
                for (Component c : container.getComponents()) {
                    if (c instanceof JPanel panel) {
                        for (Component btn : panel.getComponents()) {
                            if (btn instanceof JButton) {
                                allButtons.add((JButton) btn);
                            }
                        }
                    }
                }
            }
        }
        return allButtons;
    }

    private boolean isBedType() {
        return seatType.startsWith("Giường");
    }

    public String getSeatType() {
        return seatType;
    }
}