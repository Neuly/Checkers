package checkers;

import processing.core.*;

public class Button {
	private PApplet painter;
	private PFont font;
	int x;
	int y;
	int width;
	int height;
	int frameSize;
	
	public Button(PApplet painter, int x, int y, int width, int height, int frameSize) {
		this.painter = painter;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frameSize = frameSize;
		font = painter.createFont("Arial", 16, true);
		label();
	}
	
	public boolean isButtonPressed()  {
		  return painter.mouseX >= x && painter.mouseX <= x+width &&
				  painter.mouseY >= y && painter.mouseY <= y+height;
	}
	
	private void label() {
		painter.stroke(0, 0, 0);
		painter.fill(255, 0, 0);
		painter.rect(x, y, width, height);
		painter.fill(0, 255, 0);
		painter.textFont(font, 36);
		painter.textAlign(PApplet.CENTER);
		painter.text("Reset", x + 60, y + 33);
	}
}
