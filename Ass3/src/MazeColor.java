/**
 * Color of the maze, maze cell is represented by a character.
 * 
 * @author Idan Strovinsky
 */
public class MazeColor {
	////Class Variables.
	private char textualRepresentation;
	//Construct a color represented as textualRepresentation.
	public MazeColor(char textualRepresentation) {
			this.textualRepresentation = textualRepresentation;
	}
	//Construct a color by other MazeColor.
	public MazeColor(MazeColor other) {
		this(other.textualRepresentation); 
	}
	//Get MazeColor's textual representation.
	public char getTextualRepresentation(){
		return this.textualRepresentation;
	}

}