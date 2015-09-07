/**
 * Department tile is a class that represents the tile,
 * by n represents the height and m represents the width.
 * 
 * @author Idan Strovinsky
 */
public class Tile {
	//Class Variables.
	private int n;
	private int m;
	//Construct a Tile of size nXm.
	public Tile(int n, int m) { //Input test.
		if (n>=0 && m>=0) {
			this.n=n;
			this.m=m;
		}
		else
			System.out.println("bad input"); //In case an error message Invalid Input.
	}
	//Get the Tile's height.
	public int getN() {
		return this.n;
	}
	//Get the Tile's width.
	public int getM() {
		return this.m;
	}
}