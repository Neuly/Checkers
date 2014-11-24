package checkers;

import processing.core.*;

public class King extends Token {
	
	public King(PApplet painter) {
		super(painter);
	}
	
	public King(PApplet painter, Players player, int x, int y, int fieldSize, int frameSize) {
		super(painter, player, x, y, fieldSize, frameSize);
	}
	
	public boolean movedForward() {
		return true;
	}
	
	public void draw(float radius) {
		painter.stroke(255);
		if(player == Players.TOP_PLAYER)
			painter.fill(255, 0, 0);
		else if(player == Players.BOTTOM_PLAYER)
			painter.fill(0, 0, 255);
		else
			painter.fill(0, 0, 0);  
		painter.ellipse(x, y, radius, radius);
		painter.ellipse(x, y, radius/2, radius/2);
	}
}
