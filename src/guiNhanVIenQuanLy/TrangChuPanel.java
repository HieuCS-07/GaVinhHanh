package guiNhanVIenQuanLy;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TrangChuPanel extends JPanel{
	private MenuPanel menu_GUI;
	private TopBarPanel topBar_GUI;
	
	public TrangChuPanel() {
		setSize(1000, 700);
		setLayout(new BorderLayout());

	}
}
