/**
 *The class contains a backpack, backpack consists of a two-dimensional array of integers.
 *It also contains a number of actions that can be performed: print, getTiling and insertIntoKnapsack.
 *
 * @author Idan Strovinsky
 */
public class Knapsack {
	//Class Variables
	private int[][] knapsack;
	//Constructor Knapsack size nXm.
	public Knapsack(int n, int m) {
		if (n>0 && m>0) //Input test.
			this.knapsack=new int[n][m];
		else
			System.out.println("bad input"); //In case an error message Invalid Input.
	}
	//Shell function that checks the input, and sends to other function with other parameters.
	//That checks whether you can embed the tiles knapsack.
	public boolean insertIntoKnapsack(Tile[] tiles) {
		if (tiles==null) //Input Test - not null.
			return false;
		if (tiles.length==0) //There is no tiles.
			return true;
		for (int i=0;i<tiles.length;i++)
			if (tiles[i]==null)//Input Test - not Including null.
				return false;
		int[] checkArr=new int[tiles.length]; //New array designed for testing.
		return insertIntoKnapsack(tiles,0,0,checkArr); //Reading recursion to perform the operation.
	}
	//Recursion checks if you could embed an array of tiles in knapsack.
	private boolean insertIntoKnapsack(Tile[] tiles, int x, int y,int[] checkArr) {
		if (stopCheck(checkArr, checkArr.length-1))
			return true; //Base case - true.
		if (x==this.knapsack[y].length)
		{
			x=0; //Downloading line as soon as x exceeds.
			y++;
		}
		if (y==this.knapsack.length)
			return false; //Base case - false.
		if (knapsack[y][x]!=0) 
			return insertIntoKnapsack(tiles,x+1,y,checkArr); //Is the place painted array go to next x.
		for (int i=0;i<tiles.length;i++)
			if (checkArr[i]!=1) //If the checkArr[i] is 1 don't treat him.
				if  (tiles[i].getN()+y<=this.knapsack.length && tiles[i].getM()+x<=this.knapsack[0].length 
				&& insertTile(tiles[i].getM()+x,tiles[i].getN()+y,x,x,y,i+1)) //Checks whether you can put the tile in knapsack.
				{
					checkArr[i]=1; //Writing at test array that tile added to knapsack.
					if (insertIntoKnapsack(tiles,x+1,y,checkArr)) //Read recursion to enter the next tile.
						return true;
					else
					{
						checkArr[i]=0; ////Writing at test array that tile removed to knapsack.
						removeTiles(tiles[i],x,y); //Removes the tile from the knapsack, when there is no right ordering.
					}
				}
		return insertIntoKnapsack(tiles,x+1,y,checkArr);
	}
	//Checking recursive an base case, if the whole array is full with 1 return true else return false.
	private boolean stopCheck(int[] checkArr,int counter) {
		if (checkArr[counter]==0) //Checks whether the tile has not been tested (0).
			return false;
		if (counter==0)
			return true; //If checked all return true.
		return stopCheck(checkArr,counter-1);
	}
	//Remove tile from knapsack by the initial position(x,y).
	private void removeTiles(Tile tile,int x, int y) {
		for (int i=0;i<tile.getN();i++)
			for (int j=0;j<tile.getM();j++)
				this.knapsack[i+y][j+x]=0; //Rewrite 0.
	}
	//Check recursive whether you can put tile in knapsack (by index), if you can put it in position received(x,y).
	private boolean insertTile(int maxX, int maxY,int x,int startX, int y,int index) {
		if (y+1==maxY && x==maxX)
			return true; //Base case - true.
		if (x==maxX)
		{
			x=startX; //Downloading line as soon as x exceeds.
			y++;
		}
		if (this.knapsack[y][x]!=0)
			return false; //Base case - false.
		if (insertTile(maxX,maxY,x+1,startX,y,index))
		{
			this.knapsack[y][x]=index; //If returned true, paint the tile.
			return true;
		}
		return false;
	}
	//Prints the 2D array knapsack.
	public void print() {
		for (int i=0; i<knapsack.length; i++) {
			for (int j=0; j<knapsack[i].length; j++) 
				System.out.print(knapsack[i][j]);
			System.out.println();
		}
	}
	//Return the Knapsack's tiling.
	public int[][] getTiling() {
		return knapsack;
	}
}