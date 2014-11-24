package checkers;

import java.util.ArrayList;
import checkers.Token.Players;

public class Board {
	private Field[][] board;
	
	public Board() {
		board = new Field[8][8];
		initFields();
	}
	
	public Board(int boardSize) {
		board = new Field[boardSize][boardSize];
		initFields();
	}
	
	public Token getToken(int row, int column) {
		return board[row][column].token;
	}
	
	public boolean hasToken(int row, int column) {
		return board[row][column].hasToken;
	}
	
	public boolean hasEnemyToken(Token token, int row, int column) {
		Field field = board[row][column];
		return field.hasToken && 
				field.token.getPlayer() != token.getPlayer();
	}
	
	public void setToken(Token token, int row, int column) {
		board[row][column].token = token;
		board[row][column].hasToken = true;
	}
	
	public void clearToken(int row, int column) {
		board[row][column].hasToken = false;
		board[row][column].token = null;
	}
	
	public void moveToken(int fromRow, int fromColumn, int toRow, int toColumn) {
		board[toRow][toColumn].token = board[fromRow][fromColumn].token;
		board[fromRow][fromColumn].token = null;
		board[toRow][toColumn].hasToken = true;
		board[fromRow][fromColumn].hasToken = false;
	}
	
	public void moveToken(Token token) {
		int fromRow = token.getOldRow(), fromColumn = token.getOldColumn(), toRow = token.getRow(), toColumn = token.getColumn();
		board[toRow][toColumn].token = board[fromRow][fromColumn].token;
		board[fromRow][fromColumn].token = null;
		board[toRow][toColumn].hasToken = true;
		board[fromRow][fromColumn].hasToken = false;
	}
	
	private void initFields() {
		for(int row = 0; row < board.length; row++)
			for(int column = 0; column < board.length; column++)
				board[row][column] = new Field();
	}
	
	public void printBoard() {
		for(int row = 0; row < board.length; row++) {
			System.out.println();
			for(int column = 0; column < board.length; column++)
				if(board[row][column].hasToken)
					if(board[row][column].token.getPlayer() == Players.TOP_PLAYER)
						System.out.print("1 ");
					else
						System.out.print("2 ");
				else
					System.out.print("X ");
		}
		System.out.println();		
	}
	
	// Transform TOKEN into a king
	public void swapKing(Token token, King king) {
		int row = token.getRow(),
				column = token.getColumn();
		board[row][column].token = king;
		board[row][column].hasToken = true;
	}
	
	public ArrayList<Token> getTokens() {
		ArrayList<Token> tokens = new ArrayList<Token>();
		for(int row = 0; row < board.length; row++)
			for(int column = 0; column < board.length; column++)
				if(board[row][column].hasToken)
					tokens.add(board[row][column].token);
		return tokens;
	}
	
	public ArrayList<Token> getNeighbours(Token token) {
		int tRow = token.getRow();
		int tColumn = token.getColumn();
		ArrayList<Token> tokens = new ArrayList<Token>();
		for(int row = tRow - 1; row < tRow + 1; row++)
			for(int column = tColumn - 1; column < tColumn + 1; column++)
				if(board[row][column].hasToken)
					tokens.add(board[row][column].token);
		return tokens;
	}
	
	class Field {
		private boolean hasToken;
		private Token token;
		
		Field() {
			hasToken = false;
			token = null;
		}
		
		Field(Token token) {
			hasToken = true;
			this.token = token;
		}
	}
}
