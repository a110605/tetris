package GUI_Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Figure.Fig_I;
import Figure.Fig_J;
import Figure.Fig_L;
import Figure.Fig_O;
import Figure.Fig_S;
import Figure.Fig_T;
import Figure.Fig_Z;
import Figure.Figure;

public class NextFigurePanel extends JPanel {
	private JLabel next;
	private JPanel[][] boxes;
	private JPanel bigBox;
	private Color color;

	public NextFigurePanel() {
		color = new Color(333);
		bigBox = new JPanel();
		boxes = new JPanel[4][4];
		next = new JLabel("Next Block");
		next.setFont(TetrisFont.getTitleFont());
		
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[0].length; j++) {
				boxes[i][j] = new JPanel() {
					private static final long serialVersionUID = 1L;
					@Override
					public Dimension getPreferredSize() {
						return new Dimension(20, 20);
					}
				};
				//boxes[i][j].setBackground(Color.);
			}
		}

		bigBox.setLayout(new GridLayout(5, 6));

		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[0].length; j++) {
				bigBox.add(boxes[i][j]);
			}
		}
		add(next);
		add(bigBox);
	}

	public void refreshLineBoarder(){
		for(int i = 0 ; i < boxes.length ; i++){
			for (int j = 0; j < boxes[0].length; j++){
				boxes[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
			}
		}
	}
	
	public void refreshNextFigure(Figure figure) {
		color = GameField.colors()[figure.getBlock(0).getTextureImage()];
		
		 //fresh 4*4 boxes background color
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[0].length; j++) {
				boxes[i][j].setBackground(new Color(0,0,0,0));
				
			}
		}
		
		if (figure instanceof Fig_I) {
			boxes[2][0].setBackground(color);
			boxes[2][1].setBackground(color);
			boxes[2][2].setBackground(color);
			boxes[2][3].setBackground(color);
			refreshLineBoarder();
			boxes[2][0].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][3].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		if (figure instanceof Fig_J) {
			boxes[1][2].setBackground(color);
			boxes[2][2].setBackground(color);
			boxes[3][2].setBackground(color);
			boxes[3][1].setBackground(color);
			refreshLineBoarder();
			boxes[1][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		if (figure instanceof Fig_L) {
			boxes[1][1].setBackground(color);
			boxes[2][1].setBackground(color);
			boxes[3][1].setBackground(color);
			boxes[3][2].setBackground(color);
			refreshLineBoarder();
			boxes[1][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		if (figure instanceof Fig_O) {
			boxes[2][1].setBackground(color);
			boxes[2][2].setBackground(color);
			boxes[3][1].setBackground(color);
			boxes[3][2].setBackground(color);
			refreshLineBoarder();
			boxes[2][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		if (figure instanceof Fig_S) {
			boxes[1][1].setBackground(color);
			boxes[2][1].setBackground(color);
			boxes[2][2].setBackground(color);
			boxes[3][2].setBackground(color);
			refreshLineBoarder();
			boxes[1][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		if (figure instanceof Fig_T) {
			boxes[1][2].setBackground(color);
			boxes[2][1].setBackground(color);
			boxes[2][2].setBackground(color);
			boxes[3][2].setBackground(color);
			refreshLineBoarder();
			boxes[1][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		if (figure instanceof Fig_Z) {
			boxes[1][2].setBackground(color);
			boxes[2][1].setBackground(color);
			boxes[2][2].setBackground(color);
			boxes[3][1].setBackground(color);
			refreshLineBoarder();
			boxes[1][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[2][2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			boxes[3][1].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		}
		setVisible(false);
		setVisible(true);
	}

}
