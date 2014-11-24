package checkers;

import processing.core.*;
import checkers.Token.Direction;
import checkers.Token.Players;

public class Model {
	private PApplet painter;
	private int boardSize;
	private int fieldSize;
	private int frameSize;
	private float tokenRadius;
	private Board board;
	private Players turn;
	private Token activeToken;
	private int topPlayerTokens;
	private int bottomPlayerTokens;
	
	public Model(PApplet painter, int boardSize, int fieldSize, int frameSize, float tokenRadius) {
		this.painter = painter;
		this.boardSize = boardSize;
		this.fieldSize = fieldSize;
		this.frameSize = frameSize;
		this.tokenRadius = tokenRadius;
		turn = Players.TOP_PLAYER;
		board = new Board(boardSize);
		activeToken = null;
		topPlayerTokens = 0;
		bottomPlayerTokens = 0;
		initTokens();
	}
	
	public Players getTurn() {
		return turn;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Players checkWinState() {
		if(topPlayerTokens <= 0)
			return Players.BOTTOM_PLAYER;
		else if(bottomPlayerTokens <= 0)
			return Players.TOP_PLAYER;
		else
			return Players.NONE;
	}
	
	/** allows the movement of the buttons */
	public void mousePressedEvent() {
		if(activeToken == null) {
			for(Token token : board.getTokens()) {
				if(isOverCircle(token.getX(), token.getY(), tokenRadius)) {
					activeToken = token;
					activeToken.savePosition();
					activeToken.setXY(painter.mouseX, painter.mouseY);
				}
			}
		} else
			activeToken.setXY(painter.mouseX, painter.mouseY);
	}
	
	public boolean mouseReleasedEvent() {
		if(activeToken == null)
			return true;
			
		int row = activeToken.getRow();
		int column = activeToken.getColumn();
		boolean valid = true;
		
		// Case 1 token moved one field
		if(activeToken.movedLessThan(2) && 
				activeToken.isOverBlackField() && 
				activeToken.movedForward() && 
				!activeToken.movedStraight() &&
				!board.hasToken(row, column) &&
				activeToken.getPlayer() == turn) {
			board.moveToken(activeToken);
			toggleTurn();
			if(activeToken.reachedEnd(boardSize)) {
				King king = new King(painter, activeToken.getPlayer(), activeToken.getX(), activeToken.getY(), fieldSize, frameSize);
				board.swapKing(activeToken, king);
			}
		// Case 2 token moved two field (only occurs when there is an enemy token in front)
		} else if(activeToken.movedLessThan(3) && 
				activeToken.isOverBlackField() && 
				activeToken.movedForward() && 
				!activeToken.movedStraight() &&
				!board.hasToken(row, column) &&
				activeToken.getPlayer() == turn) {
			Direction direction = activeToken.getDirection();
			
			switch(direction)
			{
			case NW:
				valid = moveTo(row + 1, column + 1);
				break;
			case NE:
				valid = moveTo(row + 1, column - 1);
				break;
			case SW:
				valid = moveTo(row - 1, column + 1);
				break;
			case SE:
				valid = moveTo(row - 1, column - 1);
				break;
			default:
				break;
			}
		} else { // Restore Position if the move was invalid
			activeToken.restorePosition();
			valid = false;
		}
		board.printBoard();
		activeToken = null;
		
		return valid;
	}
	
	// Need for case 2
	private boolean moveTo(int row, int column) {
		if(board.hasEnemyToken(activeToken, row, column)) {
			board.moveToken(activeToken);
			board.clearToken(row, column);
			captureToken();
			toggleTurn();
			// If the token reaches to top/bottom of the board transform it into a king
			if(activeToken.reachedEnd(boardSize)) {
				King king = new King(painter, activeToken.getPlayer(), activeToken.getX(), activeToken.getY(), fieldSize, frameSize);
				board.swapKing(activeToken, king);
			}
		} else {
			// Restore Position if there there isn't a enemy in out path
			activeToken.restorePosition();
			return false;
		}
		return true;
	}
	
	private void captureToken() {
		if(turn == Players.TOP_PLAYER)
			bottomPlayerTokens--;
		else
			topPlayerTokens--;
	}
	
	private void initTokens() {
		for(int row = 0; row < boardSize; row++)
			for(int column = 0; column < boardSize; column++)
				if(row < (boardSize/2 - 1) && isBlack(row, column)) {
					int y = (row + 1) * fieldSize + 25;
					int x = (column + 1) * fieldSize + 25;
					Token token = new Token(painter, Players.TOP_PLAYER, x, y, fieldSize, frameSize);
					board.setToken(token, row, column);
					topPlayerTokens++;
				} else if(row > (boardSize/2) && isBlack(row, column)) {
					int y = (row + 1) * fieldSize + 25;
					int x = (column + 1) * fieldSize + 25;
					Token token = new Token(painter, Players.BOTTOM_PLAYER, x, y, fieldSize, frameSize);
					board.setToken(token, row, column);
					bottomPlayerTokens++;
				}
	}
	
	private boolean isBlack(int row, int column) {
		return (row + column) % 2 == 0;
	}

	/** true if its over the circle */
	private boolean isOverCircle(float x, float y, float diameter) {
		float disX = x - painter.mouseX;
		float disY = y - painter.mouseY;
		return PApplet.sqrt(PApplet.sq(disX) + PApplet.sq(disY)) < diameter / 2;
	}
	
	private void toggleTurn() {
		turn = turn == Players.TOP_PLAYER ? Players.BOTTOM_PLAYER : Players.TOP_PLAYER;
	}
}
