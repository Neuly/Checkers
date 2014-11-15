public class Model {

	/** The internal Value of Player One */
	public final static int PLAYER_ONE = 1;

	/** The internal Value of Player Two */
	public final static int PLAYER_TWO = -1;

	/** A matrix of tokens representing the checkers board */
	private Token board[][];

	/** number of tokens on each side */
	private static final int NUM_PLAYER = 8;

	/** The construktor of the model */
	public Model() {
		board = new Token[8][8];
		// fills the board on each side with board
		// stops if _n is as big as NUM_PLAYER

		float x_pos = View.FRAME_SIZE + 25;
		float y_pos = View.FRAME_SIZE;

		/* sets the coordinates of the board */
		for (int i = 0; i < 8; i++) {
			y_pos = View.FRAME_SIZE + 25;
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Token(x_pos, y_pos, 0, false,
						View.TOKEN_RADIUS);
				y_pos += View.SIZE_FIELD;
			}
			x_pos += View.SIZE_FIELD;
		}

		int _n = 0;
		/* fills one side of the board with tokens */
		for (int y = 0; y < 3 && _n < NUM_PLAYER; y++)
			for (int x = 0; x < 8 && _n < NUM_PLAYER; x++)
				if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
					board[x][y].setPlayer(Model.PLAYER_ONE);
					_n++;
				}

		/* filling the other size */
		_n = 0;
		for (int y = 7; y > 4 && _n < NUM_PLAYER; y--)
			for (int x = 0; x < 8 && _n < NUM_PLAYER; x++)
				if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
					board[x][y].setPlayer(Model.PLAYER_TWO);
					_n++;
				}

		System.out.println(" - Model constructed");

	}

	/** @return the current board */
	public Token[][] getBoard() {
		return board;
	}

	/**
	 * checks, if the current tokens position is valid or not. If not, it sets
	 * the token back to its original position
	 * 
	 * @param mov
	 *            the token that has been moved
	 */
	public void chekTokenPosition(Token mov) {
		System.out.println("Checking new position");
		int i_pos = (int) map(mov.getX(), 0, View.HEIGHT, -1, 8);
		int j_pos = (int) map(mov.getY(), 0, View.WIDTH, -1, 8);
		// if it is in those borders, it is valid
		// it is also not allowed, that theire is a button at this position
		// also the field needs too be at a spedified field
		if (i_pos >= 0
				&& i_pos <= 7
				&& j_pos >= 0
				&& j_pos <= 7
				&& ((i_pos % 2 == 0 && j_pos % 2 == 0) || (i_pos % 2 == 1 && j_pos % 2 == 1))) {

			

			board[i_pos][j_pos].setPressed(false);
		} else {

		}
		System.out.println("validoutside");

	}

	public final float map(float value, float istart, float istop,
			float ostart, float ostop) {
		return ostart + (ostop - ostart)
				* ((value - istart) / (istop - istart));
	}
}
