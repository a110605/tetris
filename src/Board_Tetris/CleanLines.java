package Board_Tetris;

public class CleanLines {
	private static int elemNr;
	private static int[] cleanLines;
	private static int cleanLineIndex;
	private static boolean[][] xRayPointer;
	private static int y;
	private static int scored;

	public static int[] giveCleanedLines() {
		return cleanLines;
	}

	public static void cleanLinesIfNeeded(Board board) {
		elemNr = 0;
		cleanLines = new int[4];
		cleanLines[0] = 100;
		cleanLines[1] = 100;
		cleanLines[2] = 100;
		cleanLines[3] = 100;
		cleanLineIndex = 0;
		xRayPointer = Board.getBlackWhite();

		scored = 0;

		// STORING THE NEEDED ROWS
		for (int i = 0; i < 20; i++) { // scan all rows
			for (elemNr = 0; elemNr < 10; elemNr++) { // scan all columns
				if (xRayPointer[i][elemNr] == false)  // if one is empty -
					elemNr = 100;
			}

			if (elemNr == 10) { // if 10 elements are true in a row, store the
								// number of the row;
				cleanLines[cleanLineIndex] = i;
				cleanLineIndex++;
				// score
				scored++;
			}
		}

		// DELETE BLOCKS AND REMOVE FROM X-RAY
		for (int i = 0; i < 4; i++) {
			
			if (cleanLines[i] != 100) { // cleanLines[i] - number of the line
										// that is full
				deleteBlocks(cleanLines[i], board);

				for (int j = 0; j < 10; j++) {
					Board.getBlackWhite()[cleanLines[i]][j] = false;
				}
			}
		}

		// REMOVE FIGURES IF THEY HAVE NO BLOCKS
		for (int i = 0; i < board.getFiguresOnBoard().size(); i++) {
			if (board.getFiguresOnBoard().get(i).getNrOfBlocks() == 0) {
				board.getFiguresOnBoard().remove(i);
				i--; // restart the index;
			}
		}

	}

	public static void deleteBlocks(int lineNr, Board board) {
		y = lineNr;

		for (int i = 0; i < board.getFiguresOnBoard().size(); i++) {
			// all figures on board

			for (int j = 0; j < board.getFiguresOnBoard().get(i)
					.getNrOfBlocks(); j++) {
				// all figure's blocks

				if (y == board.getFiguresOnBoard().get(i).getBlock(j).getPosY()) {
					// if a block has it's y coordinate as the line that needs
					// to be clean
					
					board.getFiguresOnBoard().get(i).removeBlock(j);
					// restart j/nrOfBlocks has changed and their index!
					j = -1;
				}

			}
		}
	}
	
	public static void putDownBlocksIfNeeded(Board board) {
		for (int i = 0; i < 4; i++) {

			if (cleanLines[i] != 100) {
				for (int j = 0; j < board.getFiguresOnBoard().size(); j++) { // scan
																				// all
																				// figures

					for (int j2 = 0; j2 < board.getFiguresOnBoard().get(j)
							.getNrOfBlocks(); j2++) { // scan all blocks

						int currentYofBlock = board.getFiguresOnBoard().get(j)
								.getBlock(j2).getPosY();

						if (currentYofBlock < cleanLines[i]) {// INCREASE Y
																// POSITION OF
																// EACH BLOCK
																// HIGHER THAN
																// DELETED LINE
							board.getFiguresOnBoard().get(j).getBlock(j2)
									.setPosY((currentYofBlock + 1));
						}
					}
				}
				// HERE WE'LL HAVE TO CLEAR THE X-RAY AND SET IT UP AGAIN!
				board.refreshX_Ray();
			}

		}
	}

	public static int getScoreNr() {
		return scored;
	}
}
