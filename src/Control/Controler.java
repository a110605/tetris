package Control;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Board_Tetris.Board;
import Board_Tetris.CleanLines;
import Figure.Figure;
import GUI_Tetris.MainGraph;
import GameData.GameData;
import GameData.RandomFigureGenerator;

public class Controler {
	private MainGraph MG;
	private Board BD;
	private boolean loose;
	private RandomFigureGenerator fig;
	private Figure[] twoFigures;
	private GameData GD;
	private int a;
	private int b;
	private int c;
	private long nowTime;
	private long nexTime;
	private KeyListener keyListener;
	private int keyCode;

	public Controler() {
		this.MG = new MainGraph();
		this.BD = new Board();
		this.loose = false;
		this.fig = new RandomFigureGenerator();

		twoFigures = new Figure[2];

		GD = new GameData();

		twoFigures[0] = fig.giveRandomFigure();
		twoFigures[1] = fig.giveRandomFigure();

		// ADDING A NEW FIGURE TO THE BOARD
		BD.addFigure(twoFigures[0]);
		displayBoard();

		// SET TO DISPLAY NEXT FIGURE - twoFigures[1];
		MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[1]);

		// KEY LISTENERS
		keyListener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_LEFT) {
					if (!BD.freshFigureMustStopLeft()) {
						BD.freshFigureLeft();
						displayBoard();
					}
				}

				if (keyCode == KeyEvent.VK_RIGHT) {
					if (!BD.freshFigureMustStopRight()) {
						BD.freshFigureRight();
						displayBoard();
					}
				}

				if (keyCode == KeyEvent.VK_DOWN) {
					if (!BD.freshFigureMustStopDown()) {
						BD.freshFigureDown();
						displayBoard();
					}
				}

				if (keyCode == KeyEvent.VK_SPACE) {
					while (!BD.freshFigureMustStopDown()) {
						BD.freshFigureDown();
					}
					displayBoard();
				}

				if (keyCode == KeyEvent.VK_CAPS_LOCK) {
					BD.getFreshFigure().rotate(Board.getBlackWhite());
					displayBoard();
				}
				e.consume();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		};
		MG.addKeyListener(keyListener);

		// TIMER
		nowTime = 0;
		nexTime = 0;
		a = 0;
		b = 0;
		c = 0;

		do // MAIN GAME LOOP
		{
			nowTime = System.currentTimeMillis();
			while (nexTime - GD.getMiliseconds() < nowTime) {
				nexTime = System.currentTimeMillis();
			}

			// determine if figure touch the bottom lines
			if (!BD.freshFigureMustStopDown()) {
				BD.freshFigureDown();
			} else {
				BD.cleanLinesIfNeeded(); // clean line if it full
				BD.putDownBlocksIfNeeded();

				// SCORE!
				if (CleanLines.getScoreNr() > 0) {
					for (int i = 0; i < CleanLines.getScoreNr(); i++) {
						GD.addPoints();
					}
					MG.getInfoPanel().getScorePanel().refreshScore(GD.getPoints());
				}

				twoFigures[0] = twoFigures[1];
				twoFigures[1] = fig.giveRandomFigure();

				// determine if user is lose
				if (BD.verifyLose()) {
					loose = true; // break while
					ImageIcon img = new ImageIcon("picture/lose.png");
					img.setImage(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

					int result = JOptionPane.showConfirmDialog(MG,
							"Your score is: " + GD.getPoints() + "\nYour level is: " + GD.getLevel()
									+ "\nRestart a new game ?",
							"You Lose", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img);

					if (result == JOptionPane.NO_OPTION) {
						System.exit(0);
					} else { // restart a new game
						GD.resetData();
						BD.renewFigure();

						for (int i = 0; i < 20; i++) {
							for (int v = 0; v < 10; v++) {
								MG.unColorGFPanel(i, v);
							}
						}

						nowTime = 0;
						nexTime = 0;

						twoFigures[0] = fig.giveRandomFigure();
						twoFigures[1] = fig.giveRandomFigure();
						// ADDING A NEW FIGURE TO THE BOARD
						BD.addFigure(twoFigures[0]);

						displayBoard();

						// SET TO DISPLAY NEXT FIGURE - twoFigures[0];
						MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[1]);

						// set score and level to 0 & I
						MG.getInfoPanel().getScorePanel().refreshScore(GD.getPoints());
						MG.getInfoPanel().getScorePanel().setLevel(GD.getLevel());

						loose = false;
					}
				} else {

					//System.out.println("add new figure");
					BD.addFigure(twoFigures[0]);
					MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[1]);
					GD.dataRefresh();

					// change level
					if (GD.changedLevel())
						MG.getInfoPanel().getScorePanel().setLevel(GD.getLevel());
				}
			}

			displayBoard();

			// System.out.println("printXRay");
			 Board.printXRay();
			System.out.println("\n");
		} while (loose == false);// end while
	}

	private void displayBoard() {
		// COLORING AND UNCOLORING THE BOXES
		// GET THE SPECIFIC BLOCK
		a = 0;
		b = 0;
		c = 0;

		for (int j = 0; j < BD.nrOfFigures(); j++) {
			for (int k = 0; k < BD.getFigureFromIndex(j).getNrOfBlocks(); k++) {
				a = BD.getFigureFromIndex(j).getBlockX(k);
				b = BD.getFigureFromIndex(j).getBlockY(k);
				c = BD.getFigureFromIndex(j).getBlockImg(k);

				// COLORING THE SPECIFIC BLOCK TO IT'S COLOR
				MG.colorGFPanel(b, a, c);
			}
		}

		for (int i = 0; i < 20; i++) {
			for (int v = 0; v < 10; v++) {
				if (!Board.xRay(i, v)) { // IF THERE IS NO BLOCK; NEEDS TO BE
											// (re)PAINTED IN WHITE
					MG.unColorGFPanel(i, v);
				}
			}
		}

	}// end controler construct
}