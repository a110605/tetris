package GUI_Tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGraph extends JFrame {

	private static final long serialVersionUID = 1;
	private GameField GF;
	private InfoPanel INF;

	public MainGraph() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(420, 625);
		setTitle("Tetris Game");
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		GF = new GameField();
		add(GF, BorderLayout.CENTER);

		INF = new InfoPanel();
		add(INF, BorderLayout.EAST);

		setFocusable(true);
		setVisible(true);
	}

	public GameField getMyGF() {
		return this.GF;
	}

	public void colorGFPanel(int x, int y, int i) {
		GF.colorGFPanelSq(x, y, i);
	}

	public void unColorGFPanel(int x, int y) {
		GF.uncolorGFPanelSq(x, y);
	}

	public InfoPanel getInfoPanel() {
		return this.INF;
	}

}
