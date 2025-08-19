package guiNhanVienBanVe;

import javax.swing.*;
import javax.swing.border.Border;

import entity.ChuyenTau;
import entity.Ve;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TicketCard extends JPanel {
    private boolean isExpanded = false;
    private JPanel detailsPanel;
	private JLabel lblTenTau;
	private JLabel lblMaTau;
	private JLabel lblGheTrong;
	private JLabel lblGaDi;
	private JLabel lblGaDen;
	private JLabel lblDuration;
	private JLabel lblGioDi;
	private JLabel lblGioDen;
	private JLabel lblNgayDi;
	private JLabel lblNgayDen;;

    public TicketCard(Ve ve) {  
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220))
        ));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 1000));
		
		// === MAIN CONTENT (THEO MẪU GIAO DIỆN) ===
		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.setOpaque(false);

		// LEFT - TÊN TÀU VÀ SỐ CHỖ
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.setOpaque(false);
		left.add(Box.createHorizontalGlue());

		lblTenTau = new JLabel(ve.getChuyenTau().getTau().getTenTau());
		lblTenTau.setFont(new Font("SansSerif", Font.PLAIN, 14));
		left.add(lblTenTau);
		left.add(Box.createVerticalStrut(5));

		lblMaTau = new JLabel(ve.getChuyenTau().getTau().getMaTau());
		lblMaTau.setFont(new Font("SansSerif", Font.BOLD, 20));
		left.add(lblMaTau);
		left.add(Box.createVerticalStrut(5));

		lblGheTrong = new JLabel("Còn " + ve.getChuyenTau().getSoGheTrong() + " chỗ");
		lblGheTrong.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblGheTrong.setOpaque(true);
		lblGheTrong.setBackground(new Color(255, 243, 200));
		lblGheTrong.setForeground(Color.ORANGE.darker());
		lblGheTrong.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
		left.add(lblGheTrong);

		infoPanel.add(left, BorderLayout.WEST);

		// === CENTER - 3 HÀNG: GA, GIỜ, NGÀY + DURATION ===
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setOpaque(false);

		// Font
		Font fontNormal = new Font("SansSerif", Font.PLAIN, 14);
		Font fontBold = new Font("SansSerif", Font.BOLD, 16);
		Font fontSmallItalic = new Font("SansSerif", Font.ITALIC, 12);

		// Hàng 1 - GA & DURATION
		JPanel hang1 = new JPanel(new BorderLayout());
		hang1.setOpaque(false);

		// Panel giữa chứa tên ga
		JPanel gaPanel = new JPanel();
		gaPanel.setLayout(new BoxLayout(gaPanel, BoxLayout.X_AXIS));
		gaPanel.setOpaque(false);
		gaPanel.add(Box.createHorizontalGlue());

		lblGaDi = new JLabel("Ga " + ve.getChuyenTau().getGaKhoiHanh());
		lblGaDi.setFont(fontNormal);
		gaPanel.add(lblGaDi);

		gaPanel.add(Box.createHorizontalStrut(100));

		lblGaDen = new JLabel("Ga " + ve.getChuyenTau().getGaKetThuc());
		lblGaDen.setFont(fontNormal);
		gaPanel.add(lblGaDen);

		gaPanel.add(Box.createHorizontalGlue());

		hang1.add(gaPanel, BorderLayout.CENTER);

		// Hàng 2 - DURATION
		JPanel hang2 = new JPanel();
		hang2.setOpaque(false);
//		lblDuration = new JLabel(getDuration(chuyenTau.getNgayKhoiHanh(), chuyenTau.getNgayKetThuc()));
//		lblDuration.setFont(fontSmallItalic);
//		hang2.add(lblDuration);

		// Hàng 3 - GIỜ
		JPanel hang3 = new JPanel();
		hang3.setLayout(new BoxLayout(hang3, BoxLayout.X_AXIS));
		hang3.setOpaque(false);
		hang3.add(Box.createHorizontalGlue());

		lblGioDi = new JLabel(ve.getChuyenTau().getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("HH:mm")));
		lblGioDi.setFont(fontBold);
		hang3.add(lblGioDi);

		hang3.add(Box.createHorizontalStrut(60));
		hang3.add(new JLabel("⟶"));
		hang3.add(Box.createHorizontalStrut(50));

		lblGioDen = new JLabel(ve.getChuyenTau().getNgayKetThuc().format(DateTimeFormatter.ofPattern("HH:mm")));
		lblGioDen.setFont(fontBold);
		hang3.add(lblGioDen);

		hang3.add(Box.createHorizontalGlue());

		// Hàng 4 - NGÀY
		JPanel hang4 = new JPanel();
		hang4.setLayout(new BoxLayout(hang4, BoxLayout.X_AXIS));
		hang4.setOpaque(false);
		hang4.add(Box.createHorizontalGlue());

		lblNgayDi = new JLabel(ve.getChuyenTau().getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		lblNgayDi.setFont(fontNormal);
		hang4.add(lblNgayDi);

		hang4.add(Box.createHorizontalStrut(70));
		hang4.add(Box.createHorizontalStrut(30));

		lblNgayDen = new JLabel(ve.getChuyenTau().getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		lblNgayDen.setFont(fontNormal);
		hang4.add(lblNgayDen);

		hang4.add(Box.createHorizontalGlue());

		// Thêm các hàng vào center panel
		center.add(hang1);
		center.add(Box.createVerticalStrut(5));
		center.add(hang2);
		center.add(hang3);
		center.add(Box.createVerticalStrut(10));
		center.add(hang4);

		infoPanel.add(center, BorderLayout.CENTER);
		
        // --- BOTTOM ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        // Ngày
        JLabel date = new JLabel(ve.getChuyenTau().getNgayKhoiHanh().toLocalDate().toString());  // Thay ticket.getChuyenTau() bằng ve.getChuyenTau()
        date.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bottomPanel.add(date, BorderLayout.WEST);

        // Giá vé
        JLabel price = new JLabel("Từ " + ve.getLoaiVe().toString() + " VNĐ");  // Adjust to actual price if available
        price.setFont(new Font("SansSerif", Font.BOLD, 14));
        price.setForeground(new Color(0, 0, 0));
        bottomPanel.add(price, BorderLayout.EAST);

        // --- DROPDOWN BUTTON ---
        JButton dropDownBtn = new JButton("▼");
        dropDownBtn.setFocusPainted(false);
        dropDownBtn.setContentAreaFilled(false);
        dropDownBtn.setBorderPainted(false);
        dropDownBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(dropDownBtn, BorderLayout.NORTH);

        infoPanel.add(rightPanel, BorderLayout.EAST);

        add(infoPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        detailsPanel = new TrainSeatBookingPanel();
        detailsPanel.setVisible(false);
        add(detailsPanel, BorderLayout.SOUTH);

        dropDownBtn.addActionListener(e -> {
            isExpanded = !isExpanded;
            detailsPanel.setVisible(isExpanded);
            dropDownBtn.setText(isExpanded ? "▲" : "▼");
            revalidate();
            repaint();
        });
    }
}
