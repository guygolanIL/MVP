package boot;

import algorithms.mazeGenerators.Maze3d;

public class Test {

	public static void main(String[] args) {
		byte[] b = {3,3,3,
				0,0,0,
				0,0,0,
				
				0,1,1,
				1,1,1,
				1,1,1,
				
				
				1,1,1,
				1,1,1,
				1,1,1,
				
				
				1,1,1,
				1,1,1,
				1,1,1};
		
		Maze3d maze = new Maze3d(b);
		int[][] plainx = maze.getCrossSectionByX(0);
		int[][] plainy = maze.getCrossSectionByY(0);
		int[][] plainz = maze.getCrossSectionByZ(0);
		
		for (int[] is : plainy) {
			for (int i : is) {
				System.out.print(i+" ");
			}
			System.out.println("");
		}
			
	}

}
