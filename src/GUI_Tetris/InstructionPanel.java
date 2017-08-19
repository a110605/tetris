package GUI_Tetris;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InstructionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel instr ;
	private JLabel[] text = new JLabel[5];
	
	public InstructionPanel(){
		setLayout(new GridLayout(6, 1));
		
		setFont(TetrisFont.getFont());
		setForeground(TetrisFont.getColor());
		
		instr = new JLabel("Instructions");
		instr.setFont(TetrisFont.getTitleFont());
		instr.setHorizontalAlignment(JLabel.CENTER);
		
		text[0] = new JLabel("→:Right");
		text[1] = new JLabel("←:Left");
		text[2] = new JLabel("↓:Down");
		text[3] = new JLabel("Caps lock:Rotate");
		text[4] = new JLabel("Space:Fast Down");
		
		add(instr);
		for(int i = 0; i < 5 ; i++){
			text[i].setFont(TetrisFont.getFont());
			text[i].setHorizontalAlignment(JLabel.LEFT);
			add(text[i]);
		}
		
	}
}
