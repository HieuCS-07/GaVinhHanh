package guiNhanVIenQuanLy;

import javax.swing.*;
import javax.swing.border.Border;

import entity.ChuyenTau;
import entity.Tau;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChuyenDiCard extends JPanel {
	private boolean isSelected = false;
	private ChuyenTau chuyenTau;
	private JLabel lblTenTau;
	private JLabel lblMaTau;
	private JLabel lblGheTrong;
	private JLabel lblGaDi;
	private JLabel lblGaDen;
	private JLabel lblDuration;
	private JLabel lblGioDi;
	private JLabel lblGioDen;
	private JLabel lblNgayDi;
	private JLabel lblNgayDen;

	public ChuyenDiCard(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
		
		// === MAIN CONTENT (THEO MẪU GIAO DIỆN) ===
		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.setOpaque(false);

		// LEFT - TÊN TÀU VÀ SỐ CHỖ
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.setOpaque(false);
		left.add(Box.createHorizontalGlue());

		lblTenTau = new JLabel(chuyenTau.getTau().getTenTau());
		lblTenTau.setFont(new Font("SansSerif", Font.PLAIN, 14));
		left.add(lblTenTau);
		left.add(Box.createVerticalStrut(5));

		lblMaTau = new JLabel(chuyenTau.getTau().getMaTau());
		lblMaTau.setFont(new Font("SansSerif", Font.BOLD, 20));
		left.add(lblMaTau);
		left.add(Box.createVerticalStrut(5));

		lblGheTrong = new JLabel("Còn " + chuyenTau.getSoGheTrong() + " chỗ");
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

		lblGaDi = new JLabel("Ga " + chuyenTau.getGaKhoiHanh());
		lblGaDi.setFont(fontNormal);
		gaPanel.add(lblGaDi);

		gaPanel.add(Box.createHorizontalStrut(100));

		lblGaDen = new JLabel("Ga " + chuyenTau.getGaKetThuc());
		lblGaDen.setFont(fontNormal);
		gaPanel.add(lblGaDen);

		gaPanel.add(Box.createHorizontalGlue());

		hang1.add(gaPanel, BorderLayout.CENTER);

		// Hàng 2 - DURATION
		JPanel hang2 = new JPanel();
		hang2.setOpaque(false);
		lblDuration = new JLabel(getDuration(chuyenTau.getNgayKhoiHanh(), chuyenTau.getNgayKetThuc()));
		lblDuration.setFont(fontSmallItalic);
		hang2.add(lblDuration);

		// Hàng 3 - GIỜ
		JPanel hang3 = new JPanel();
		hang3.setLayout(new BoxLayout(hang3, BoxLayout.X_AXIS));
		hang3.setOpaque(false);
		hang3.add(Box.createHorizontalGlue());

		lblGioDi = new JLabel(chuyenTau.getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("HH:mm")));
		lblGioDi.setFont(fontBold);
		hang3.add(lblGioDi);

		hang3.add(Box.createHorizontalStrut(60));
		hang3.add(new JLabel("⟶"));
		hang3.add(Box.createHorizontalStrut(50));

		lblGioDen = new JLabel(chuyenTau.getNgayKetThuc().format(DateTimeFormatter.ofPattern("HH:mm")));
		lblGioDen.setFont(fontBold);
		hang3.add(lblGioDen);

		hang3.add(Box.createHorizontalGlue());

		// Hàng 4 - NGÀY
		JPanel hang4 = new JPanel();
		hang4.setLayout(new BoxLayout(hang4, BoxLayout.X_AXIS));
		hang4.setOpaque(false);
		hang4.add(Box.createHorizontalGlue());

		lblNgayDi = new JLabel(chuyenTau.getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		lblNgayDi.setFont(fontNormal);
		hang4.add(lblNgayDi);

		hang4.add(Box.createHorizontalStrut(70));
		hang4.add(Box.createHorizontalStrut(30));

		lblNgayDen = new JLabel(chuyenTau.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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

		add(infoPanel, BorderLayout.CENTER);

	}

	private String getDuration(LocalDateTime start, LocalDateTime end) {
		Duration duration = Duration.between(start, end);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		return hours + "h" + minutes;
	}

	public void setSelected(boolean selected) {
		this.isSelected = selected;
		repaint(); // Vẽ lại để đổi viền
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isSelected) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.RED); // viền đỏ khi được chọn
			g2.setStroke(new BasicStroke(2));
			g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}

	public void capNhatLaiUI(ChuyenTau chuyenTau) {
		lblTenTau.setText(chuyenTau.getTau().getTenTau());
		lblMaTau.setText(chuyenTau.getTau().getMaTau());
		lblGheTrong.setText("Còn " + 12 + " chỗ"); //chuyenTau.getSoGheTrong()
		lblGaDi.setText("Ga " + chuyenTau.getGaKhoiHanh());
		lblGaDen.setText("Ga " + chuyenTau.getGaKetThuc());
		lblDuration.setText(getDuration(chuyenTau.getNgayKhoiHanh(), chuyenTau.getNgayKetThuc()));
		lblGioDi.setText(chuyenTau.getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("HH:mm")));
		lblGioDen.setText(chuyenTau.getNgayKetThuc().format(DateTimeFormatter.ofPattern("HH:mm")));
		lblNgayDi.setText(chuyenTau.getNgayKhoiHanh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		lblNgayDen.setText(chuyenTau.getNgayKetThuc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	public ChuyenTau getChuyenTau() {
	    return chuyenTau;
	}
	
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			// Frame chính
//			JFrame frame = new JFrame("Test ChuyenDiCard");
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setSize(600, 300);
//			frame.setLocationRelativeTo(null);
//
//			// Tạo dữ liệu mẫu
//			ChuyenTau chuyenTau = new entity.ChuyenTau();
//			chuyenTau.setTau(new Tau("SE4", "Tàu Tốc Hành", 300, 12, true));
//			chuyenTau.setGaKhoiHanh("Hà Nội");
//			chuyenTau.setGaKetThuc("Sài Gòn");
//			chuyenTau.setNgayKhoiHanh(LocalDateTime.of(2025, 4, 12, 6, 30));
//			chuyenTau.setNgayKetThuc(LocalDateTime.of(2025, 4, 13, 8, 15));
//
//			// Thêm Card vào frame
//			ChuyenDiCard card = new ChuyenDiCard(chuyenTau);
//			frame.add(card);
//
//			frame.setVisible(true);
//		});
//	}
}
