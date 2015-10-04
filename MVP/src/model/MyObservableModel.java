package model;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeDomain;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
	 * Defines what MyModel does.
	 * @author Guy Golan & Amit Sandak.
	 *
	 */
public class MyObservableModel extends ObservableCommonModel {


		public MyObservableModel() {			//Ctor
			super();
		}
		
		@Override
		public void generate(String name, int x, int y, int z) {
			
			threadPool.execute( new Runnable() {			//generating process occurs in a thread.
				
				@Override
				public void run() {
					Maze3d maze = new MyMaze3dGenerator().generate(x, y, z);
					System.out.println(maze);
					mazeMap.put(name, maze);
					charPositionMap.put(name, maze.getEntrance());
					setChanged();
					notifyObservers("completedTask maze generated " + name);
					
				}
			});
			
		}


		@Override
		public Maze3d getMaze(String name) {
			Maze3d  temp = mazeMap.get(name);
			
			if(temp != null)
			{
				return temp;	
			}
			else
			{
				//return("Unavailable maze!");
				return null;
			}
		}

		@Override
		public void getCrossSectionByX(int index, String name) {
			Maze3d  tmpMaze = mazeMap.get(name);
			
			if(tmpMaze != null)
			{
				//controller.display(arrIntToString(tmpMaze.getCrossSectionByX(index)));	
			}
			else
			{
				//controller.display("Unavailable maze!");
			}		
		}


		@Override
		public void getCrossSectionByY(int index, String name) {
			Maze3d  tmpMaze = mazeMap.get(name);
			
			if(tmpMaze != null)
			{
				//controller.display(arrIntToString(tmpMaze.getCrossSectionByY(index)));	
			}
			else
			{
				//controller.display("Unavailable maze!");
			}		
			
		}

		@Override
		public void getCrossSectionByZ(int index, String name) {
			Maze3d  tmpMaze = mazeMap.get(name);
			
			if(tmpMaze != null)
			{
				//controller.display(arrIntToString(tmpMaze.getCrossSectionByZ(index)));	
			}
			else
			{
				//controller.display("Unavailable maze!");
			}		
			
		}
		
		/**
		 * Converts a dou - dimensional array into a String.
		 * @param arr - a plain.
		 * @return a string containing the plain.
		 */
		private String arrIntToString(int[][] arr) {
			String s = "";
			
			for (int[] i : arr)
			{
				for(int j : i)
				{
					s = s + j + " ";
					
				}
				s = s+ "\n";
			}
			
			return s;
		}

		@Override
		public void save(String name, String fileName) {
			Maze3d  tmpMaze = mazeMap.get(name);
			
			if(tmpMaze != null)
			{
				try {
					MyCompressorOutputStream tmpCompressor = new MyCompressorOutputStream(new FileOutputStream(fileName));
					tmpCompressor.write(tmpMaze.toByteArray());
					tmpCompressor.close();								//compressing the maze into and writing it to the file.
					setChanged();
					notifyObservers("completedTask save");
					//controller.display(name + " maze saved to " + fileName + ".");
				} catch (FileNotFoundException e) {
					setChanged();
					notifyObservers("completedTask error '"+fileName+"' is not a valid file name.");
					//controller.display("wrong file path");
				} catch (IOException e)
				{
					setChanged();
					notifyObservers("completedTask error IO error.");
					//controller.display("general error");
				}
			}
			else
			{
				setChanged();
				notifyObservers("completedTask error '"+name+"' is unavailable maze");
				//controller.display("Unavailable maze!");
			}				
		}

		@Override
		public void load(String fileName, String name) {
			try {
					MyDecompressorInputStream tmpDecompressor = new MyDecompressorInputStream(new FileInputStream(fileName));
					byte [] buffer = new byte[35*35*35]; 	//35*35*35 is the maximum supported maze in the compressor
					if (tmpDecompressor.read(buffer)==-1)
					{
						Maze3d  tmpMaze = new Maze3d(buffer);
						mazeMap.put(name, tmpMaze);
						setChanged();
						notifyObservers("completedTask load");
						//controller.display(name + " maze loaded.");
						tmpDecompressor.close();
					}
					else
					{
						setChanged();
						notifyObservers("completedTask error The requsted maze is too big!");
						//controller.display("the requsted maze is too big!");
					}
				} 
				catch (FileNotFoundException e) 
				{
					setChanged();
					notifyObservers("completedTask error wrong file path.");
					//controller.display("wrong file path");
				} 
				catch (IOException e)
				{
					setChanged();
					notifyObservers("completedTask error IO error.");
					//controller.display("general error");
				}
				
		}

		@Override
		public void mazeSize(String name) {
			
			
			Maze3d tempMaze = mazeMap.get(name);
			if(tempMaze!=null)
			{
				int size = tempMaze.getxAxis()* tempMaze.getyAxis()*tempMaze.getzAxis()+9;
				setChanged();
				notifyObservers("completedTask mazeSize "+size);
			}
			else
			{
				setChanged();
				notifyObservers("completedTask error '"+name+"' is unavailable maze");
			}
			
		}

		@Override
		public void calculateFileSize(String name) {
			Maze3d  tmpMaze = mazeMap.get(name);
			
			if(tmpMaze != null)
			{
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				MyCompressorOutputStream compress = new MyCompressorOutputStream(buffer);
				try 
				{
					compress.write(tmpMaze.toByteArray());    //trying to compress the maze into the buffer.
					setChanged();
					notifyObservers("completedTask fileSize "+name+" "+buffer.size());
				} 
				catch (IOException e) {
					//controller.display("general error");
				}
				finally {
					try 
					{
						compress.close();		//closing resources.
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else
			{
				setChanged();
				notifyObservers("completedTask error '"+name+"' is unavailable maze");
				//controller.display("Unavailable maze!");
			}	
			
		}

		@Override
		public void solve(String name, String algorithm) {
			threadPool.execute(new Runnable() {			//solving the maze in a new thread
				
				@Override
				public void run() {
					
					Maze3d tmpMaze = mazeMap.get(name);
					if(tmpMaze!=null)
					{
						Searcher<Position> alg;
						switch(algorithm)							//generating a Searcher according to the parameters.
						{
						case "BFS":
							alg = new BFS<Position>();
							break;
						case "AstarManhattan":
							alg = new AStar<Position>(new MazeManhattanDistance(new State<Position>(tmpMaze.getExit())));
							break;
						case "AstarAirDistance":
							alg = new AStar<Position>(new MazeAirDistance(new State<Position>(tmpMaze.getExit())));
							break;
							
						default :
							setChanged();
							notifyObservers("completedTask error "+algorithm+" is not a valid algorithm!\n Valid algorithms are: <BFS>, <AstarManhattan>, <AstarAirDistance>. ");
							//controller.display(algorithm+" is not a valid algorithm! \nValid algorithms are : <BFS>, <AstarManhattan>, <AstarAirDistance>.");
							return;
						}
						solutionMap.put(name,alg.search(new MazeDomain(tmpMaze)));		//inserting the Solution into the solution map.
						setChanged();
						notifyObservers("completedTask solution "+name);
						//controller.display("solution for " +name+ " is ready");
					}
					else
					{
						setChanged();
						notifyObservers("completedTask error '"+name+"' is unavailable maze");
						//controller.display(name+ " maze is unavailable!");
					}
					
								
				}
			});
		}

		@Override
		public Solution<Position> getSolution(String mazeName) {
			Solution<Position>  tmp = solutionMap.get(mazeName);
			
			if(tmp != null)
			{
				return(tmp);	
			}
			else
			{
				//return("Unavailable solution!");
				return null;
			}	
		}

		@Override
		public void exit() {			//safely terminating the existing threads.
			try {
				threadPool.shutdown();
				if (!threadPool.awaitTermination(5,TimeUnit.SECONDS ))
				{
					threadPool.shutdownNow();
					//presenter.get.display("threads terminated violently!");
				}
				//else
					//controller.display("all threads terminated!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
				
		}

		@Override
		public Position getCharPosition(String name) {
			return charPositionMap.get(name);
		}

		@Override
		public void MoveUP(String name) {
			Position current = charPositionMap.get(name);
			String [] moves = mazeMap.get(name).getPossibleMoves(current);
			for (String move : moves) {
				if (move.equals("BACKWARD"))
				{
					current.setY(current.getY()-1);
					charPositionMap.put(name,current );
					setChanged();
					notifyObservers("completedTask movement " + name);
				}
			}
		}

		@Override
		public void MoveDOWN(String name) {
			Position current = charPositionMap.get(name);
			String [] moves = mazeMap.get(name).getPossibleMoves(current);
			for (String move : moves) {
				if (move.equals("FORWARD"))
				{
					current.setY(current.getY()+1);
					charPositionMap.put(name,current );
					setChanged();
					notifyObservers("completedTask movement " + name);
				}
			}
		}

		@Override
		public void MoveLEFT(String name) {
			Position current = charPositionMap.get(name);
			String [] moves = mazeMap.get(name).getPossibleMoves(current);
			for (String move : moves) {
				if (move.equals("RIGHT"))
				{
					current.setZ(current.getZ()-1);
					charPositionMap.put(name,current );
					setChanged();
					notifyObservers("completedTask movement " + name);
				}
			}
		}

		@Override
		public void MoveRIGHT(String name) {
			Position current = charPositionMap.get(name);
			String [] moves = mazeMap.get(name).getPossibleMoves(current);
			for (String move : moves) {
				if (move.equals("LEFT"))
				{
					current.setZ(current.getZ()+1);
					charPositionMap.put(name,current );
					setChanged();
					notifyObservers("completedTask movement " + name);
				}
			}			
		}

		@Override
		public void MoveLVLUP(String name) {
			System.out.println("1");
			Position current = charPositionMap.get(name);
			System.out.println("2 "  + current);
			String [] moves = mazeMap.get(name).getPossibleMoves(current);
			for (String move : moves) {
				if (move.equals("UP"))
				{
					current.setX(current.getX()+1);
					charPositionMap.put(name,current );
					System.out.println("3 "  + current);
					setChanged();
					notifyObservers("completedTask movement " + name);
					System.out.println("4 " );
				}
			}			
		}

		@Override
		public void MoveLVLDOWN(String name) {
			Position current = charPositionMap.get(name);
			String [] moves = mazeMap.get(name).getPossibleMoves(current);
			for (String move : moves) {
				if (move.equals("DOWN"))
				{
					current.setX(current.getX()-1);
					charPositionMap.put(name,current );
					setChanged();
					notifyObservers("completedTask movement " + name);
				}
			}			
		}
	}

	
	
	
	
	

