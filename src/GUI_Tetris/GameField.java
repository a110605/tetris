package GUI_Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameField extends JPanel
{
   private static final long serialVersionUID = 1L;
   private JPanel[][] gameF;
   private static Color[] colorz;
   
   public GameField()
   {        
      GridLayout layout = new GridLayout(20, 10);      
      setLayout(layout);
      
      gameF = new JPanel[20][10];
      initializeGF();
      addGF();

      // writing paths to images
      colorz = new Color[7];
      colorz[0] = Color.BLUE;
      colorz[1] = Color.CYAN;
      colorz[2] = Color.GREEN;
      colorz[3] = Color.MAGENTA;
      colorz[4] = Color.ORANGE;
      colorz[5] = Color.RED;
      colorz[6] = Color.YELLOW;
   }

   private void initializeGF()
   {
      for (int i = 0; i < 20; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            gameF[i][j] = new JPanel() {
				private static final long serialVersionUID = 1L;

				@Override
            	public Dimension getPreferredSize(){
            		return new Dimension(45, 45);
            	}
            };
            //gameF[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            gameF[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // boardline of main panel
         }
      }
   }

   private void addGF()
   {
      for (int i = 0; i < 20; i++){
         for (int j = 0; j < 10; j++){
            add(gameF[i][j]);
         }
      }
   }

   public void colorGFPanelSq(int x, int y, int c)
   {
        this.gameF[x][y].setBackground(colorz[c]);
   }
   
   public void uncolorGFPanelSq(int x, int y) {
        this.gameF[x][y].setBackground(Color.LIGHT_GRAY); // color of main panel 
   }
   
   public static Color[] colors(){
	   return colorz;
   }
}
