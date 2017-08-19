package GUI_Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.Controler;

public class RestartPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel textScore, picture;
	public Controler contr;
	private BoxLayout layout;
	//private JButton pause, restart;
	
	public RestartPanel()  {
		
		textScore = new JLabel("Tetris Game");
		picture = new JLabel();

		ImageIcon img = new ImageIcon("picture/icon.png");
		img.setImage(img.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		
		picture.setSize(100,100);
		picture.setIcon(img);
		
		textScore.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		textScore.setFont(TetrisFont.getTitleFont());
		textScore.setForeground(TetrisFont.getColor());

		picture.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		add(textScore);
		add(picture);
	}
}
