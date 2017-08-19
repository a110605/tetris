package GUI_Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ScorePanel scorePanel;
	private NextFigurePanel nextFigPanel;
	private InstructionPanel instr;
	private RestartPanel restart;
	private Dimension panelDimension;
	
	public InfoPanel(){
		setLayout(new GridLayout(4, 1));
		
		scorePanel = new ScorePanel();
		nextFigPanel = new NextFigurePanel();
		instr = new InstructionPanel();	
		restart = new RestartPanel();
		panelDimension = new Dimension(120, 100);
		
		add(restart);
		add(nextFigPanel);
		add(instr);
		add(scorePanel);
	}
	
	public RestartPanel getRestartPanel(){
		return this.restart;
	}
	
	public ScorePanel getScorePanel(){
		return this.scorePanel;
	}
	
	public NextFigurePanel getNextFigurePanel(){
		return this.nextFigPanel;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return this.panelDimension;
	}
}
