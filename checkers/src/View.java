import processing.core.*;

/**
 * View.java
 * 
 * The view of the checkers board.
 * 
 */
public class View {

	/** The Applet that prints everything */
	private PApplet p;

	/** the size of the black frame */
	public static final int FRAME_SIZE = 30;

	/** The Width of the window */
	public static final int WIDTH = 460;

	/** The height of the window */
	public static final int HEIGHT = 460;

	/** The size of one field of the checkers board */
	public final static int SIZE_FIELD = 50;

	/** The radius of a token */
	public final static float TOKEN_RADIUS = SIZE_FIELD / 2;

	/** The moving token */
	private Token moving_token;

	/** the board that should be drawn by the Applet */
	private Token[][] board;

	/** The Construktor */
	public View(PApplet _p) {
		moving_token = null;
		p = _p;
		p.size(WIDTH, HEIGHT);
		p.background(0);
		System.out.println(" - View constructed");
	}

	/**
	 * Sets the gloabl board variable to the given _t value
	 * 
	 * @param _t
	 *            the new board
	 */
	public void setBoard(Token[][] _t) {
		board = _t;
	}

	/**
	 * Just draws the board
	 * 
	 * @param board
	 *            with tokens on it
	 */
	public void draw_board_and_frame() {
		int _width = WIDTH - FRAME_SIZE;
		int _height = HEIGHT - FRAME_SIZE;

		// print the checkers board
		// Begin loop for columns
		for (int x = FRAME_SIZE; x < _width; x += SIZE_FIELD) {
			// Begin loop for rows
			for (int y = FRAME_SIZE; y < _height; y += SIZE_FIELD) {
				p.stroke(255);
				if ((x + y) % 20 == 0) {
					p.fill(0);
				} else {
					p.fill(255);
				}
				// For every column and row, a rectangle is drawn at an (x,y)
				// location scaled and sized by videoScale.
				p.rect(x, y, SIZE_FIELD, SIZE_FIELD);
			}
		}
	}

	/**
	 * draws the players - they are currently in the int matrix there is no
	 * check, if there are enough payers on the field
	 * 
	 * @param board
	 *            with tokens on it
	 */
	void draw_players() {

		float x = FRAME_SIZE + 25;
		float y = FRAME_SIZE;

		// print the checkers on the checker board
		for (int i = 0; i < 8; i++) {
			y = 5;

			// Begin loop for rows
			for (int j = 0; j < 8; j++) {
				p.stroke(255);
				y += SIZE_FIELD;
				// print((players[i][j]).getPlayer() + " ");

				if ((board[i][j]).getPlayer() != 0 || (board[i][j]).getStatus()) {
					if ((board[i][j]).getPlayer() == Model.PLAYER_ONE)
						p.fill(122, 0, 0);
					else if ((board[i][j]).getPlayer() == Model.PLAYER_TWO)
						p.fill(0, 122, 0);
					else if ((board[i][j]).getStatus())
						p.fill(0, 122, 122);
					p.ellipse((board[i][j]).getX(), (board[i][j]).getY(),
							board[i][j].get_radius(), board[i][j].get_radius());
				}
			}
			// println();
			x += SIZE_FIELD;
		} // println();
	}

	/**
	 * When a token is held with the left mousekey, it should move. So this
	 * method sould display the moving token.
	 * 
	 * @param mouse_x
	 *            the mouse_x coordinate of the mouse
	 * @param mouse_y
	 *            the mouse_y coordinate of the mouse
	 * @param pressed
	 *            determines it the mouse is pressed or not
	 */
	public Token moveToken(int mouse_x, int mouse_y, boolean pressed) {

		// so draw a button at the specified position of the mouse
		if (pressed) {

			// This part finds the moving token - if the moving_token is null
			// there is currently no token beeing moved
			if (moving_token == null) {
				moving_token = null;
				// first of all, we need to check, if it matches a button on the
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (board[i][j].getPlayer() != 0) {
							if (overCircle(board[i][j].getX(), mouse_x,
									board[i][j].getY(), mouse_y,
									board[i][j].get_radius())) {

								moving_token = board[i][j];
								moving_token.setXY(mouse_x, mouse_y);
								moving_token.setPressed(true);
								break;

							}
						}
					}
				}
				if (moving_token == null)
					return null;
			}
			// if we now the moving token, we need to
			else {
				moving_token.setXY(mouse_x, mouse_y);
			}

		} else if (moving_token != null)
			moving_token = null;

		return moving_token;

	}

	/** @return true if its over the circle */
	boolean overCircle(float x, float mouse_x, float y, float mouse_y,
			float diameter) {
		float disX = x - mouse_x;
		float disY = y - mouse_y;

		if (Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2)) < diameter / 2) {
			return true;
		} else {
			return false;
		}
	}

}
