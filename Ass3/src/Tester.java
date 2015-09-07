
public class Tester {

	public static void testKnapsack(){
		// Below are some examples to test the correctness of your code.
        // Write additional test!

		// used to specify whether all the tiles were indeed inserted into the knapsack by the insertIntoKnapsack method 
		boolean allTilesInserted;
		
		
		System.out.println("Testing knapsack");
        /* Test 1
         * expected output:
         *  true
         *  12
         *  34
         *  true
         */
        Tile[] test1 = {new Tile(1, 1), new Tile(1, 1), new Tile(1, 1), new Tile(1, 1)};
        Knapsack sack1 = new Knapsack(2,2);
        allTilesInserted = sack1.insertIntoKnapsack(test1);
        System.out.println(allTilesInserted);
        sack1.print();
        
        int[][] tiling1 = sack1.getTiling();
        System.out.println(tiling1[0][0]==1 && tiling1[0][1]==2 && tiling1[1][0]==3 && tiling1[1][1]==4);
        System.out.println();
        
        /* Test 2
         * expected output:
         *  true
         *	1122223400
         *  1122223555
		 */
		Tile[] test2 = {new Tile(2, 2), new Tile(2, 4), new Tile(2, 1), new Tile(1, 1), new Tile(1, 3)};
		Knapsack sack2 = new Knapsack(2,10);
		
		allTilesInserted = sack2.insertIntoKnapsack(test2);
		System.out.println(allTilesInserted);
        sack2.print();
        System.out.println();

        /* Test 3
         * expected output:
         *  true
         *  2223
         *  4113
         *  4113
         *  4555
         */
		Tile[] test3 = {new Tile(2, 2), new Tile(1, 3), new Tile(3, 1), new Tile(3, 1), new Tile(1, 3)};
		Knapsack sack3 = new Knapsack(4,4);
		allTilesInserted = sack3.insertIntoKnapsack(test3);
		System.out.println(allTilesInserted);
        sack3.print();
        System.out.println();

        /* Test 4
         * expected output:
         *  true
         *  11122223
         *  11145503
         *  66666000
         *  77777700
		 */
		Tile[] test4 = {new Tile(2, 3), new Tile(1, 4), new Tile(2, 1), new Tile(1, 1), new Tile(1, 2), new Tile(1, 5), new Tile(1, 6)};
		Knapsack sack4 = new Knapsack(4,8);
		allTilesInserted = sack4.insertIntoKnapsack(test4);
		System.out.println(allTilesInserted);
        sack4.print();
        System.out.println();

        /* Test 5
         * expected output:
         *  true
         *  11134
         *  11130
         *  22220
         *  55088
         *  77788
         *  00088
         *  66666
		 */
		Tile[] test5 = { new Tile(2,3), new Tile(1,4), new Tile(2,1), new Tile(1, 1), new Tile(1, 2),
				new Tile(1,5), new Tile(1,3), new Tile(3, 2) };
		Knapsack sack5 = new Knapsack(7,5);
		allTilesInserted = sack5.insertIntoKnapsack(test5);
		System.out.println(allTilesInserted);
        sack5.print();
        System.out.println();
        
        /* Test 7
         * expected output:
         *  false
         */
        Tile[] test7 = {new Tile(1, 2), new Tile(1, 1), new Tile(1, 1), new Tile(1, 1)};
        Knapsack sack7 = new Knapsack(2,2);
        allTilesInserted = sack7.insertIntoKnapsack(test7);
        System.out.println(allTilesInserted);
        System.out.println();
	}
	
	public static void testMaze(){
		// Add some more tests here
		
		System.out.println("Testing maze");
		// color for every digit
		MazeColor c0 = new MazeColor('0');
		MazeColor c1 = new MazeColor('1');
		MazeColor c2 = new MazeColor('2');
		MazeColor c3 = new MazeColor('3');
		MazeColor c4 = new MazeColor('4');
		// construct maze
		MazeColor[][] map1 = {{c4,c1,c2,c2},
							  {c4,c4,c3,c1},
							  {c1,c4,c4,c4},
							  {c1,c4,c0,c2}};
		MazeColor[][] mapFilled = {{c0,c1,c2,new MazeColor('2')},
				  				   {c0,c0,c3,c1},
				  				   {c1,c0,c0,c0},
				  				   {c1,c0,c0,c2}};
		Maze maze1 = new Maze(map1);
		Maze mazeFilled = new Maze(mapFilled);
		maze1.fill(new Point(1,1), new MazeColor('0'));
		
		/* Expected output for next tests:
		 * 4
		 * 2
		 * true
		 * false
		 * true
		 * -1
		 * 5
		 * (0,0),(1,0),(1,1),(2,1),(2,2),(2,3),
		 */
		System.out.println(maze1.numOfColors());
		System.out.println(maze1.numOfColors(new Point(0,0)));
		System.out.println(maze1.legalNeighbor(new Point(0,0),new Point(1,0)));
		System.out.println(maze1.legalNeighbor(new Point(0,0),new Point(0,1)));
		System.out.println(maze1.equals(mazeFilled));
		System.out.println(maze1.distance(new Point(0,0),new Point(0,3)));
		System.out.println(maze1.distance(new Point(0,0),new Point(2,3)));
		Point[] pathP00P23 = maze1.minPath(new Point(0,0),new Point(2,3));
		for(int i=0;i<pathP00P23.length;i=i+1)
			System.out.print("(" + pathP00P23[i].getI() + "," + pathP00P23[i].getJ() + "),");
		System.out.println();
	}
	
	public static void main(String[] args) {
        testKnapsack();
        testMaze();
    }

}
