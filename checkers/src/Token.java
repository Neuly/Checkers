/** 
 * 
 * Token.java
 * 
 * represents a single Token on the checkers board 
 * 
 * @author Neuly aka Philipp Neulinger
 * 
 */
public class Token {
	
	private float x;
	private float y;
	private int player;
	private boolean pressed;
	private float old_x;
	private float old_y;
	private float radius;

	Token(float _x, float _y, int _player, boolean _pressed, float _r) {
		x = _x;
		y = _y;
		old_x = x;
		old_y = y;
		player = _player;
		pressed = _pressed;
		radius = _r;
	}

	/** @return the radius of a token */
	float get_radius() {
		return radius;
	}
	
	/** @return x position of the token */
	float getX() {
		return x;
	}

	/** @return y position of the token */
	float getY() {
		return y;
	}

	/** @return the current player - is either -1 or 1 */
	int getPlayer() {
		assert(player == Model.PLAYER_ONE || player == Model.PLAYER_TWO);
		return player;
	}

	/** @return true, when someone pressed the token */
	boolean getStatus() {
		return pressed;
	}

	/** sets the pressed variable to the given value */
	void setPressed(boolean _pressed) {
		pressed = _pressed;
	}

	/** sets the x and y coordinates of the token */
	void setXY(float _x, float _y) {
		x = _x;
		y = _y;
	}

	/** sets the previous x and y coordinates of the roken */
	void setoldXY(float _x, float _y) {
		old_x = _x;
		old_y = _y;
	}

	
	/** sets the token to its previous possision */
	void setBack() {
		x = old_x;
		y = old_y;
	}

	/** @value _p sets the token's player to the given value */
	void setPlayer(int _p) {
		player = _p;
	}

}
