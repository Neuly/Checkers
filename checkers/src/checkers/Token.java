package checkers;

import processing.core.*;

public class Token {
	protected int x, y;
	protected int oldX, oldY;
	protected Players player;
	protected PApplet painter;
	protected int fieldSize;
	protected int frameSize;
	
	public enum Players {
		NONE, TOP_PLAYER, BOTTOM_PLAYER
	}
	
	public enum Direction {
		NONE, NW, NE, SW, SE
	}
	
	public Token(PApplet painter) {
		x = 0;
		y = 0;
		oldX = x;
		oldY = y;
		player = Players.NONE;
		this.painter = painter;
	}
	
	public Token(PApplet painter, Players player, int x, int y, int fieldSize, int frameSize) {
		this.x = x;
		this.y = y;
		oldX = x;
		oldY = y;
		this.player = player;
		this.painter = painter;
		this.fieldSize = fieldSize;
		this.frameSize = frameSize;
	}
	
	public void savePosition() {
		oldX = x;
		oldY = y;
	}
	
	public void restorePosition() {
		x = oldX;
		y = oldY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Players getPlayer() {
		return player;
	}

	public void setPlayer(Players player) {
		this.player = player;
	}
	
	public int getRow() {
		return (y - frameSize) / fieldSize;
	}
	
	public int getColumn() {
		return (x - frameSize) / fieldSize;
	}
	
	public int getOldRow() {
		return (oldY - frameSize) / fieldSize;
	}
	
	public int getOldColumn() {
		return (oldX - frameSize) / fieldSize;
	}
	
	public boolean isOverBlackField() {
		int row = getRow();
		int column = getColumn();
		return (row + column) % 2 == 0;
	}

	public boolean movedLessThan(int n) {
		int row = getRow();
		int column = getColumn();
		int oldRow = getOldRow();
		int oldColumn = getOldColumn();
		return Math.abs(row - oldRow) < n && Math.abs(column - oldColumn) < n;
	}
	
	public Direction getDirection() {
		int row = getRow();
		int column = getColumn();
		int oldRow = getOldRow();
		int oldColumn = getOldColumn();
		if(column < oldColumn && row < oldRow)
			return Direction.NW;
		else if(column > oldColumn && row < oldRow)
			return Direction.NE;
		else if(column < oldColumn && row > oldRow)
			return Direction.SW;
		else if(column > oldColumn && row > oldRow)
			return Direction.SE;
		return Direction.NONE;
	}
	
	public boolean movedStraight() {
		int row = getRow();
		int column = getColumn();
		int oldRow = getOldRow();
		int oldColumn = getOldColumn();
		return row == oldRow || column == oldColumn;
	}
	
	public boolean movedForward() {
		int row = getRow();
		int oldRow = getOldRow();
		
		switch(player)
		{
		case TOP_PLAYER:
			return row >= oldRow;
		case BOTTOM_PLAYER:
			return row <= oldRow;
		case NONE:
		default:
			return false;
		}
	}
	
	public boolean reachedEnd(int boardSize) {
		int row = getRow();
		switch(player) {
		case TOP_PLAYER:
			return row == boardSize - 1;
		case BOTTOM_PLAYER:
			return row == 0;
		default:
			return false;
		}
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
	}
}
