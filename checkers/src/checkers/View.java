package checkers;

import java.util.ArrayList;

import processing.core.*;
import checkers.Token.Players;

public class View {
	private PApplet painter;
	private int width; 
	private int height;
	private int frameSize;
	private int fieldSize;
	private float tokenRadius;
	private PFont font;
	
	public View(PApplet painter, int width, int height, int fieldSize, int frameSize, float tokenRadius) {
		this.painter = painter;
		this.width = width;
		this.height = height;
		this.frameSize = frameSize;
		this.fieldSize = fieldSize;
		this.tokenRadius = tokenRadius;
		font = painter.createFont("Arial", 16, true); 
		painter.size(width, height);
		painter.background(0);
	}
	
	/**
	 * draws the frame of the chess board and the board itself - without the
	 * players
	 */
	public void draw_background() {
		int width = this.width- frameSize;
		int height = this.height - frameSize;

		// print the checkers board
		// Begin loop for columns
		for (int x = frameSize; x < width; x += fieldSize) {
			// Begin loop for rows
			for (int y = frameSize; y < height; y += fieldSize) {
				painter.stroke(255);
				if ((x + y) % 20 == 0) {
					painter.fill(0);
				} else {
					painter.fill(255);
				}
				// For every column and row, a rectangle is drawn at an (x,y)
				// location scaled and sized by videoScale.
				painter.rect(x, y, fieldSize, fieldSize);
			}
		}
	}
	
	public void draw_tokens(ArrayList<Token> tokens) {
		for(Token token : tokens)
			token.draw(tokenRadius);
	}
	
	public void writeTurn(Players player) {
		painter.fill(0, 0, 0);
		painter.stroke(0, 0, 0);
		painter.rect(0, 0, width, frameSize);
		painter.fill(255, 255, 255);
		painter.textFont(font, 36);
		painter.textAlign(PApplet.CENTER);
		painter.text(player + "'s turn", width/2, 2*frameSize/3);
	}
	
	public void writeValid(boolean valid) {
		painter.fill(0, 0, 0);
		painter.stroke(0, 0, 0);
		painter.rect(0, height - frameSize, width, frameSize);
		painter.textFont(font, 36);
		painter.textAlign(PApplet.CENTER);
		if(valid) {
			painter.fill(0, 255, 0);
			painter.text("Valid move!", width/2, height - frameSize/3);
		} else {
			painter.fill(255, 0, 0);
			painter.text("Invalid move! Try again!", width/2, height - frameSize/3);
		}
	}
	
	public void writeWin(Players player) {
		if(player == Players.NONE)
			return;
		
		painter.fill(0, 0, 0);
		painter.stroke(0, 0, 0);
		painter.rect(0, 0, width, frameSize);
		painter.fill(0, 255, 0);
		painter.textFont(font, 36);
		painter.textAlign(PApplet.CENTER);
		painter.text(player + "wins!", width/2, 2*frameSize/3);
	}
}
