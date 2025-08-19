package guiNhanVienBanVe;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class BookingFirstView extends JPanel {
	private RightPanel rightPanel;

	private JDateChooser dateChooserNgayDi;

	private JDateChooser dateChooserNgayVe;

	private JComboBox cboGaDi;

	private JComboBox cboGaDen;

	private static final String[] DS_GA_VIETNAM = { "Sài gòn", "Bình Thuận", "Phan Thiết", "Nha Trang", "Quy Nhơn",
			"Đà Nẵng", "Huế", "Vinh", "Thanh Hóa", "Nam Định", "Hà Nội", "Hải Phòng", "Phú Thọ", "Yên Bái", "Lào Cai" };

	public BookingFirstView(Runnable onSearchClicked) {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		// Tạo RightPanel
		rightPanel = new RightPanel();

		JPanel roundedPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(220, 220, 220));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
			}
		};
		roundedPanel.setOpaque(false);
		roundedPanel.setPreferredSize(new Dimension(720, 360));
		roundedPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 15, 8, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Tiêu đề
		JLabel titleLabel = new JLabel("Đặt vé tàu", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Serif", Font.ITALIC, 26));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		roundedPanel.add(titleLabel, gbc);

		// Ga khởi hành
		gbc.gridwidth = 1;
		gbc.gridy++;
		roundedPanel.add(new JLabel("Ga khởi hành"), gbc);
		gbc.gridx = 1;
		cboGaDi = new JComboBox<>(DS_GA_VIETNAM);
		roundedPanel.add(cboGaDi, gbc);

		// Ga kết thúc
		gbc.gridx = 2;
		roundedPanel.add(new JLabel("Ga kết thúc"), gbc);
		gbc.gridx = 3;
		cboGaDen = new JComboBox<>(DS_GA_VIETNAM);
		roundedPanel.add(cboGaDen, gbc);

		// Một chiều / Khứ hồi
		gbc.gridx = 0;
		gbc.gridy++;
		roundedPanel.add(new JLabel("Loại vé"), gbc);
		gbc.gridx = 1;
		JRadioButton motChieu = new JRadioButton("Một chiều");
		JRadioButton khuHoi = new JRadioButton("Khứ hồi");
		ButtonGroup group = new ButtonGroup();
		group.add(motChieu);
		group.add(khuHoi);
		motChieu.setSelected(true);
		motChieu.setBackground(new Color(220, 220, 220));
		khuHoi.setBackground(new Color(220, 220, 220));

		JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		radioPanel.setOpaque(false);
		radioPanel.add(motChieu);
		radioPanel.add(khuHoi);
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		roundedPanel.add(radioPanel, gbc);

		// Ngày đi
		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		roundedPanel.add(new JLabel("Ngày đi"), gbc);

		gbc.gridx = 1;
		dateChooserNgayDi = new JDateChooser();
		dateChooserNgayDi.setDate(new java.util.Date());
		dateChooserNgayDi.setPreferredSize(new Dimension(100, 25));
		dateChooserNgayDi.setDate(new java.util.Date());
		roundedPanel.add(dateChooserNgayDi, gbc);

		// Ngày về
		gbc.gridx = 2;
		roundedPanel.add(new JLabel("Ngày về"), gbc);
		gbc.gridx = 3;
		dateChooserNgayVe = new JDateChooser();
		dateChooserNgayVe.setPreferredSize(new Dimension(100, 25));
		roundedPanel.add(dateChooserNgayVe, gbc);

		// Ẩn ngày về nếu là một chiều
		Component[] ngayVeComps = dateChooserNgayVe.getComponents();
		motChieu.addActionListener(e -> {
			dateChooserNgayVe.setEnabled(false);
		});
		khuHoi.addActionListener(e -> {
			dateChooserNgayVe.setEnabled(true);
			dateChooserNgayVe.setDate(new java.util.Date());
		});
		dateChooserNgayVe.setEnabled(false);

		// Nút tìm kiếm
		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		JButton searchButton = new JButton("Tìm kiếm");
		searchButton.setBackground(Color.BLACK);
		searchButton.setForeground(Color.WHITE);
		searchButton.setFocusPainted(false);
		searchButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		searchButton.setPreferredSize(new Dimension(140, 40));

		searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				searchButton.setBackground(Color.GRAY);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				searchButton.setBackground(Color.BLACK);
			}
		});

		searchButton.addActionListener(e -> {
			if (onSearchClicked != null)
				onSearchClicked.run();
			// Show RightPanel when search button is clicked
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(BookingFirstView.this);
			frame.add(rightPanel, BorderLayout.EAST);
			rightPanel.setVisible(true);
			frame.revalidate();
			frame.repaint();
		});

		roundedPanel.add(searchButton, gbc);
		add(roundedPanel);
		
		cboGaDi.addActionListener(e -> {
		    String gaDi = getGaDi();
		    System.out.println("Ga đi: " + gaDi);
		});
		
		cboGaDen.addActionListener(e -> {
		    String gaDen = getGaDen();
		    System.out.println("Ga đến: " + gaDen);
		});
		
		
	}

	private JComboBox<String> createStyledComboBox(String[] items) {
		JComboBox<String> combo = new JComboBox<>(items);
		combo.setPreferredSize(new Dimension(60, 28));
		combo.setBackground(new Color(245, 245, 245));
		combo.setForeground(Color.BLACK);
		combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		combo.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
		combo.setFocusable(false);

		combo.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				setHorizontalAlignment(CENTER);
				return c;
			}
		});

		customizeComboBoxArrow(combo);
		return combo;
	}

	private JPanel createDatePicker() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panel.setOpaque(false);

		String[] days = new String[31];
		for (int i = 0; i < 31; i++)
			days[i] = String.format("%02d", i + 1);
		String[] months = new String[12];
		for (int i = 0; i < 12; i++)
			months[i] = String.format("%02d", i + 1);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String[] years = new String[50];
		for (int i = 0; i < 50; i++)
			years[i] = String.valueOf(year + i);

		JComboBox<String> dayCombo = createStyledComboBox(days);
		JComboBox<String> monthCombo = createStyledComboBox(months);
		JComboBox<String> yearCombo = createStyledComboBox(years);

		dayCombo.setSelectedItem("23");
		monthCombo.setSelectedItem("03");
		yearCombo.setSelectedItem("2025");

		panel.add(dayCombo);
		panel.add(monthCombo);
		panel.add(yearCombo);

		return panel;
	}

	private void customizeComboBoxArrow(JComboBox<String> comboBox) {
		comboBox.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton arrowButton = new JButton("\u25BC"); // mũi tên ▼
				arrowButton.setBorder(BorderFactory.createEmptyBorder());
				arrowButton.setForeground(Color.WHITE);
				arrowButton.setBackground(Color.BLACK);
				arrowButton.setFont(new Font("SansSerif", Font.BOLD, 12));
				arrowButton.setFocusable(false);
				return arrowButton;
			}
		});
	}
	
	public String getGaDi() {
		return (String) cboGaDi.getSelectedItem();
	}
	
	public String getGaDen() {
		return (String) cboGaDen.getSelectedItem();
	}
	
	public LocalDateTime getNgayDi() {
		return dateChooserNgayDi.getDate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}
	
//	public LocalDateTime getNgayVe() {
//		return dateChooserNgayVe.getDate().toInstant().atZone(ZoneId.systemDefault())
//				.toLocalDate();
//	}
}
