package GUI_Tetris;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel text;
	private JLabel level;
	private JLabel textScore;
	private JLabel actualScore;
	private BoxLayout layout;
	
	public ScorePanel(){
		textScore = new JLabel("Score: ");
		actualScore = new JLabel("0");
		text = new JLabel("Level: ");
		level = new JLabel("I");
		
		textScore.setHorizontalAlignment(JLabel.CENTER);
		actualScore.setHorizontalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		level.setHorizontalAlignment(JLabel.CENTER);
		
		textScore.setFont(TetrisFont.getTitleFont());
		actualScore.setFont(TetrisFont.getFont());
		text.setFont(TetrisFont.getTitleFont());
		level.setFont(TetrisFont.getFont());
		
		textScore.setForeground(TetrisFont.getColor());
		actualScore.setForeground(TetrisFont.getColor());
		text.setForeground(TetrisFont.getColor());
		level.setForeground(TetrisFont.getColor());
		
		//layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(new GridLayout(4,1));
		
		add(textScore);
		add(actualScore);
		add(text);
		add(level);
	}
	
	public void setLevel(String level){
		this.level.setText(level);
		this.level.setVisible(false);
		this.level.setVisible(true);
	}
	
	public void refreshScore(int nr){
		actualScore.setText(String.valueOf(nr));
		actualScore.setVisible(false);
		actualScore.setVisible(true);
	}
}
