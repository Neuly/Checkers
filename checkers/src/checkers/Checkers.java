package checkers;

import processing.core.*;

public class Checkers extends PApplet {
	private static final long serialVersionUID = 1L;
	private static final int FRAME_SIZE = 50;
	private static final int FIELD_SIZE = 50;
	private static final float TOKEN_RADIUS = FIELD_SIZE/2;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final int BOARD_SIZE = 8;

	private Model model;
	private View view;
	
	/** constructor */
	public void setup() {
		model = new Model(this, BOARD_SIZE, FIELD_SIZE, FRAME_SIZE, TOKEN_RADIUS);
		view = new View(this, WIDTH, HEIGHT, FIELD_SIZE, FRAME_SIZE, TOKEN_RADIUS);
		view.writeTurn(model.getTurn());
	}
	
	/** draws everything */
	public void draw() {
		view.draw_background();
		view.draw_tokens(model.getBoard().getTokens());
		
		if(mousePressed)
			model.mousePressedEvent();
	}
	
	public void mouseReleased() {
		boolean valid = model.mouseReleasedEvent();
		view.writeValid(valid);
		view.writeTurn(model.getTurn());
		view.writeWin(model.checkWinState());
	}
}
