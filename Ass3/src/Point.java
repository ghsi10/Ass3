/**
 * Point, is represented by two numbers, i and j.
 * 
 * @author Idan Strovinsky
 */
public class Point {
	//Class Variables.
	private int i;
	private int j;
	//Construct a Point by getting i,j.
	public Point(int i, int j) {
		this.i=i;
		this.j=j;
	}
	//Get the Point's i.
	int getI() {
		return this.i;
	}
	//Get the Point's j.
	int getJ() {
		return this.j;
	}

}