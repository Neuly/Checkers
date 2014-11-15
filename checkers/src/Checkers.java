import processing.core.*;

/**
 * 
 * Checkers.java
 * 
 * This Project is using processing, you can download it from
 * http://processing.org. There is also a tutorial on how to user it with
 * eclipse or build this program from command line by using the core.jre.
 * 
 * Tutorial for Using Eclipse and Processing:
 * https://processing.org/tutorials/eclipse/
 * 
 * For building it from the command line TODO
 * 
 * 
 * We will also describe how to run it in the Readme on Github.
 * 
 * Why don't we use normale sketches? So when working on that current project, I
 * decided that programming one Checkers game in just one sketch is not a goot
 * idea. Basically because programming everything in one file is not a good
 * idea. So thats why we are doing it like that - it is easier and more
 * comfortable to work with.
 * 
 * So this is the main game class, there are two more
 * 
 * @author Neuly aka Philipp Neulinger
 * @author stefan-delorenzo aka Stefan de Lorenzo
 * @since 15.11.2014
 * 
 */
@SuppressWarnings("serial")
public class Checkers extends PApplet {

	/** The View Object - controlls the interface */
	private View v;

	/** The Model Object - controlls the movement of the tokens */
	private Model m;

	/** The token, that is moved during the game */
	private Token mov;

	/** The setup method of "processing" */
	public void setup() {

		System.out.println("- - - - - - - - - - - - - - - - - - - ");
		System.out.println(" Checkers \n");
		System.out.println(" A Game By Neuly und stefan-delorenzo (c)");
		System.out.println("- - - - - - - - - - - - - - - - - - - ");

		m = new Model();
		v = new View(this);

		System.out.println("Setup done.\n");

	}

	/** The draw method of "processing" */
	public void draw() {

		// start the drawing
		v.setBoard(m.getBoard());
		v.draw_board_and_frame();
		v.draw_players();

		// if the mouse is pressed, then move the token

		if (mousePressed)
			mov = v.moveToken(mouseX, mouseY, mousePressed);
		else if (mov != null) {
			m.chekTokenPosition(mov);
			m = null;
		}
	}
}