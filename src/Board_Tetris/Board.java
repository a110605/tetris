package Board_Tetris;

import Figure.*;
import java.util.ArrayList;

public class Board {
	private ArrayList<Figure> figuresOnBoard;
	private Figure freshFigure;
	private static boolean[][] blackWhite;
	private ArrayList<Block> stopBlocks;

	private ArrayList<Block> uniqueY0;
	private ArrayList<Block> uniqueY1;
	private ArrayList<Block> uniqueY2;
	private ArrayList<Block> uniqueY3;

	private int x;
	private int y;

	private int currentPos;

	public Board() {
		figuresOnBoard = new ArrayList<Figure>();
		freshFigure = new Figure();
		blackWhite = new boolean[20][10];
		stopBlocks = new ArrayList<Block>();
		x = 0;
		y = 0;
		currentPos = 0;

		uniqueY0 = new ArrayList<Block>();
		uniqueY1 = new ArrayList<Block>();
		uniqueY2 = new ArrayList<Block>();
		uniqueY3 = new ArrayList<Block>();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				blackWhite[i][j] = false;
			}
		}
	}

	public void addFigure(Figure fig) {
		this.freshFigure = fig;
		figuresOnBoard.add(fig);
		System.out.println("addFigure:figureOnBoard size=" + figuresOnBoard.size());
		// INFORM THE x-Ray
		for (int i = 0; i < 4; i++) {
			blackWhite[fig.getBlockY(i)][fig.getBlockX(i)] = true;
		}
	}

	public static boolean xRay(int Y, int X) {
		return blackWhite[Y][X];
	}

	public static void printXRay() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				if (blackWhite[i][j]) {
					System.out.print("*");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}

	public Figure getFreshFigure() {
		return this.freshFigure;
	}

	public Figure getFigureFromIndex(int index) {
		return figuresOnBoard.get(index);
	}

	public int nrOfFigures() {
		return figuresOnBoard.size();
	}

	public void renewFigure() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				blackWhite[i][j] = false;
			}
		}
		figuresOnBoard.clear();

	}

	public void freshFigureDown() {
		// GETTING THE COORDINATES that are now, AND REMOVE THEM FROM X-RAY
		for (int i = 0; i < 4; i++) {
			blackWhite[this.freshFigure.getBlockY(i)][this.freshFigure.getBlockX(i)] = false;
		}

		// SETTING THE NEW COORDINATES FOR THE FIGURE

		for (int j = 0; j < 4; j++) {
			currentPos = this.freshFigure.getBlockY(j);
			this.freshFigure.setBlockY(currentPos + 1, j);
		}

		// RE-INFORMING THE X-RAY ABOUT THE NEW POSSITION

		for (int i = 0; i < 4; i++) {
			blackWhite[this.freshFigure.getBlockY(i)][this.freshFigure.getBlockX(i)] = true;
		}
	}

	public void freshFigureLeft() {
		// GETTING THE COORDINATES that are now, AND REMOVE THEM FROM X-RAY
		for (int i = 0; i < 4; i++) {
			blackWhite[this.freshFigure.getBlockY(i)][this.freshFigure.getBlockX(i)] = false;
		}

		// SETTING THE NEW COORDINATES FOR THE FIGURE

		for (int j = 0; j < 4; j++) {
			currentPos = this.freshFigure.getBlockX(j);
			this.freshFigure.setBlockX(currentPos - 1, j);
		}

		// RE-INFORMING THE X-RAY ABOUT THE NEW POSSITION

		for (int i = 0; i < 4; i++) {
			blackWhite[this.freshFigure.getBlockY(i)][this.freshFigure.getBlockX(i)] = true;
		}
	}

	public void freshFigureRight() {
		// GETTING THE COORDINATES that are now, AND REMOVE THEM FROM X-RAY
		for (int i = 0; i < 4; i++) {
			blackWhite[this.freshFigure.getBlockY(i)][this.freshFigure.getBlockX(i)] = false;
		}

		// SETTING THE NEW COORDINATES FOR THE FIGURE

		for (int j = 0; j < 4; j++) {
			currentPos = this.freshFigure.getBlockX(j);
			this.freshFigure.setBlockX(currentPos + 1, j);
		}

		// RE-INFORMING THE X-RAY ABOUT THE NEW POSSITION

		for (int i = 0; i < 4; i++) {
			blackWhite[this.freshFigure.getBlockY(i)][this.freshFigure.getBlockX(i)] = true;
		}
	}

	public boolean freshFigureMustStopLeft() {
		return FreshStopSides.mustStopL(true, stopBlocks, uniqueY0, uniqueY1, uniqueY2, uniqueY3, blackWhite,
				freshFigure, x, y);
	}

	public boolean freshFigureMustStopRight() {
		return FreshStopSides.mustStopL(false, stopBlocks, uniqueY0, uniqueY1, uniqueY2, uniqueY3, blackWhite,
				freshFigure, x, y);

	}

	public boolean freshFigureMustStopDown() {
		return FreshStopDown.mustStopDown(stopBlocks, uniqueY0, uniqueY1, uniqueY2, uniqueY3, blackWhite, freshFigure,
				x, y);
	}

	public static boolean[][] getBlackWhite() {
		return blackWhite;
	}

	public void cleanLinesIfNeeded() {
		CleanLines.cleanLinesIfNeeded(this);
	}

	public ArrayList<Figure> getFiguresOnBoard() {
		return this.figuresOnBoard;
	}

	public void putDownBlocksIfNeeded() {
		CleanLines.putDownBlocksIfNeeded(this);
	}

	public void refreshX_Ray() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				blackWhite[i][j] = false;
			}
		}

		for (int i = 0; i < figuresOnBoard.size(); i++) {
			for (int j = 0; j < figuresOnBoard.get(i).getNrOfBlocks(); j++) {
				blackWhite[figuresOnBoard.get(i).getBlockY(j)][figuresOnBoard.get(i).getBlockX(j)] = true;
			}
		}
	}

	// if any figure have getBlock == 0, should lose
	public boolean verifyLose() {
		System.out.println("verifyLose():figuresOnBoard.size()=" + figuresOnBoard.size());
		for (int i = 0; i < figuresOnBoard.size(); i++) {

			// if(!figuresOnBoard.get(i).isEqualTo(freshFigure)){
			for (int j = 0; j < figuresOnBoard.get(i).getNrOfBlocks(); j++) {
				System.out.println("figure " + i + ".getBlockY=" + figuresOnBoard.get(i).getBlockY(j));
				if (figuresOnBoard.get(i).getBlockY(j) == 0) {
					return true;
				}
			}
			// }
		}
		return false;
	}
}
