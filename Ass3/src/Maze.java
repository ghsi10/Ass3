/**
 * Maze is defined by Array of colors maze, and there are plenty of activities which knows how to do.
 * 
 * @author Idan Strovinsky
 */
public class Maze {
	//Class Variables.
	private MazeColor[][] map;
	//Construct of Maze by map, array of MazeColor (n*n).
	public Maze(MazeColor[][] map) {
		boolean input=true;
		if (map==null || map.length==0 || map[0].length==0)  //Input test.
			input=false;
		for (int i=0;i<map.length&&input;i++) {
			if (map[i]==null)
				input=false;
			for (int j=0;j<map.length&&input;j++)
				if (map[i][j]==null)
					input=false;
		}
		if (input) { //If the input is valid.
			this.map = new MazeColor[map.length][map.length];
			for (int i=0;i<map.length;i++)
				for (int j=0;j<map.length;j++)
					this.map[i][j]= new MazeColor(map[i][j]); //Copying cell to cell.

		}
		else {
			this.map=null;
			System.out.println("bad input"); //In case an error message Invalid Input.
		}
	}
	/* Task 1 */
	//Checks whether Mazes is equal returns true if they are equal.
	public boolean equals(Object other) {
		if (other==null || !(other instanceof Maze))
			return false; //If other can't be casted to a Maze.
		Maze otherMaze = (Maze) other; // Cast other to a Maze.
		if (this.map.length != otherMaze.map.length) //Checks whether the lengths are equal.
			return false;
		for (int i=0;i<map.length;i++)
			for (int j=0;j<map[0].length;j++)
				if (this.map[i][j].getTextualRepresentation()!=otherMaze.map[i][j].getTextualRepresentation())
					return false; //If a cell is not worth returns false.
		return true;
	}
	/* Task 2 */
	//Returns the number of different ColorMaze have in the Maze.
	public int numOfColors() {
		if (this.map==null) //Input test.
			return -1;
		int index=0;
		boolean notIn;
		char[] color=new char[this.map.length*this.map.length]; //The maximum size of the array of possible characters.
		for (int i=0;i<this.map.length;i++) 
			for (int j=0;j<this.map.length;j++) //Scanning of the maze.
			{
				notIn=true;
				for (int k=0;k<index && notIn;k++) //Does the test has been performed.
					if (color[k]==this.map[i][j].getTextualRepresentation())
						notIn=false;
				if (notIn) //Other than in the past, you add it to the array.
				{
					color[index]=this.map[i][j].getTextualRepresentation();
					index++; //The sum of the number of elements in the array.
				}
			}
		return index;
	}
	/* Task 3 */
	//Checks the number of different characters that neighbors of a point.
	public int numOfColors(Point p) {
		if (!valid(p)) //Input test.
			return 0;
		int count=1;
		char tmp=this.map[p.getI()][p.getJ()].getTextualRepresentation(); //Save the color of the point and compare to neighbors.
		if (valid(new Point(p.getI()+1,p.getJ())) && tmp!=this.map[p.getI()+1][p.getJ()].getTextualRepresentation())
			count++; //Check all the neighbors.
		if (valid(new Point(p.getI()-1,p.getJ())) && tmp!=this.map[p.getI()-1][p.getJ()].getTextualRepresentation())
			count++;
		if (valid(new Point(p.getI(),p.getJ()+1)) && tmp!=this.map[p.getI()][p.getJ()+1].getTextualRepresentation())
			count++;
		if (valid(new Point(p.getI(),p.getJ()-1)) && tmp!=this.map[p.getI()][p.getJ()-1].getTextualRepresentation())
			count++;
		return count;
	}
	/* Task 4 */
	//Checks whether a valid neighbor.
	public boolean legalNeighbor(Point p1, Point p2) {
		if (!valid(p1) || !valid(p2)) //Input test.
			return false;
		if (!((p1.getI()+1==p2.getI()&&p1.getJ()==p2.getJ())||(p1.getI()-1==p2.getI()&&p1.getJ()==p2.getJ())
				||(p1.getI()==p2.getI()&&p1.getJ()+1==p2.getJ())||(p1.getI()==p2.getI()&&p1.getJ()-1==p2.getJ())))
			return false; //If no neighboring points - returns false.
		if (this.map[p1.getI()][p1.getJ()].getTextualRepresentation()==this.map[p2.getI()][p2.getJ()].getTextualRepresentation())
			return true; //If all the conditions existed up to now, and the colors are equal - returns true.
		return false;
	}
	/* Task 5 */
	//Shell checks and sends input recursion fill.
	public void fill(Point p, MazeColor color) {
		if(valid(p)) //Input tests.
			fill(p,color,this.map[p.getI()][p.getJ()]);
		else
			System.out.println("bad input");
	}
	//Changes the point and its area received color.
	private void fill(Point p, MazeColor color,MazeColor startColor) {
		if (valid(p)&&this.map[p.getI()][p.getJ()].getTextualRepresentation()==startColor.getTextualRepresentation()) //Point integrity check.
		{
				this.map[p.getI()][p.getJ()]=color; //Changes the color to the color received.
				fill(new Point(p.getI()+1,p.getJ()),color,startColor); //Does the action again to all neighbors
				fill(new Point(p.getI(),p.getJ()+1),color,startColor);
				fill(new Point(p.getI()-1,p.getJ()),color,startColor);
				fill(new Point(p.getI(),p.getJ()-1),color,startColor);
		}
	}
	/* Task 6 */
	//Shell function that checks the distance between two points.
	public int distance(Point p1, Point p2) {
		if (!valid(p1)||!valid(p2) //Input test.
				||this.map[p1.getI()][p1.getJ()].getTextualRepresentation()!=this.map[p2.getI()][p2.getJ()].getTextualRepresentation())
			return -1;
		if (p1.getI()==p2.getI() && p1.getJ()==p2.getJ()) //Do the same points.
			return 0;
		return distance(p1,p2,0,-1);
	}
	//Checks the distance between two points.
	private int distance(Point p1, Point p2, int count, int min) {
		if ((count==min && min!=-1)||!valid(p1)||this.map[p1.getI()][p1.getJ()].getTextualRepresentation()!=this.map[p2.getI()][p2.getJ()].getTextualRepresentation())
			return min; //If the point was checked/No use to check if a big counter than the minimum
		if (p1.getI()==p2.getI() && p1.getJ()==p2.getJ()) { //Have you reached the end point.
			if (min==-1 || count<min)
				return count;
			return min;
		}
		MazeColor tmpColor=this.map[p1.getI()][p1.getJ()]; //Creates a temporary color to return it at the end we can test.
		this.map[p1.getI()][p1.getJ()]=null; //Takes the current color.
		min = distance(new Point(p1.getI()+1,p1.getJ()),p2,count+1,min); //Recursive call to all neighbors.
		min = distance(new Point(p1.getI()-1,p1.getJ()),p2,count+1,min);
		min = distance(new Point(p1.getI(),p1.getJ()+1),p2,count+1,min);
		min = distance(new Point(p1.getI(),p1.getJ()-1),p2,count+1,min);
		this.map[p1.getI()][p1.getJ()]=tmpColor; //Returns the color to the place (end of test).
		if (min==this.map.length*this.map.length+1)
			return -1; //If the minimum is equal to the size of the map +1 it means that there was no way to get even so -1 is returned
		return min;
	}
	/* Task 7 */
	//Shell function returns Array of points representing the fastest way between two points.
	public Point[] minPath(Point p1, Point p2) {
		if (!valid(p1)||!valid(p2) //Input test.
				||this.map[p1.getI()][p1.getJ()].getTextualRepresentation()!=this.map[p2.getI()][p2.getJ()].getTextualRepresentation())
			return null;
		if (p1.getI()==p2.getI() && p1.getJ()==p2.getJ()) //Do the same points.
			return new Point[]{p1};
		return minPath(p1,p2,0,new Point[0],null);
	}
	//function returns Array of points representing the fastest way between two points
	private Point[] minPath(Point p1, Point p2, int count, Point[] tmpMin, Point[] min) {
		if (!valid(p1)||this.map[p1.getI()][p1.getJ()].getTextualRepresentation()!=this.map[p2.getI()][p2.getJ()].getTextualRepresentation())
			return min;
		Point[] newMin =new Point[tmpMin.length+1]; //New array with length+1.
		for (int i=0;i<tmpMin.length;i++)
			newMin[i]=tmpMin[i]; //Copy last way.
		newMin[tmpMin.length]=p1; //Add the new Point.
		if (p1.getI()==p2.getI() && p1.getJ()==p2.getJ()) { //Have you reached the end point.
			if (min==null || newMin.length<min.length)
				return newMin;
			return min;
		}
		MazeColor tmpColor=this.map[p1.getI()][p1.getJ()]; //Creates a temporary color to return it at the end we can test.
		this.map[p1.getI()][p1.getJ()]=null; //Takes the current color.
		min=minPath(new Point(p1.getI()+1,p1.getJ()),p2,count+1,newMin,min); //Recursive call to all neighbors.
		min=minPath(new Point(p1.getI()-1,p1.getJ()),p2,count+1,newMin,min);
		min=minPath(new Point(p1.getI(),p1.getJ()+1),p2,count+1,newMin,min);
		min=minPath(new Point(p1.getI(),p1.getJ()-1),p2,count+1,newMin,min);
		this.map[p1.getI()][p1.getJ()]=tmpColor; //Returns the color to the place (end of test).
		return min;
	}
	/* Other functions */
	//Checks if the Point is valid/input test.
	private boolean valid(Point p) {
		return p!=null&&this.map!=null&&p.getI()<this.map.length&&p.getI()>=0&&p.getJ()<this.map.length&&p.getJ()>=0&&this.map[p.getI()]!=null&&this.map[p.getI()][p.getJ()]!=null;
	}
}