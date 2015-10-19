package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
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
 *
 * Defines what MyObservableModel does.
 * 
 * @author Guy Golan & Amit Sandak.
 *
 */
public class MyObservableModel extends ObservableCommonModel {

	
	Socket theServer ;
	BufferedReader inFromServer;
	PrintWriter outToServer;
	@SuppressWarnings("unchecked")
	
@Override
	public boolean start() {
		try {
			theServer = new Socket(properties.getServerAddress(), properties.getPort());
			inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			outToServer = new PrintWriter(theServer.getOutputStream());
			return true;
		} catch (ConnectException e) {
			setChanged();
			notifyObservers("completedTask error Failed to connect to server");
			return false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 


	}


	@Override
	public void generate(String name, int x, int y, int z) {
		if(theServer.isConnected()){
			try {
			//	name=name+"@"+x+"@"+y+"@"+z;
			String parse;
			outToServer.println("generate new maze");
			outToServer.flush();

			parse = inFromServer.readLine();
			outToServer.println("the name is: " + name);
			outToServer.flush();
			parse = inFromServer.readLine();
			outToServer.println("the Axis x is: " + x);
			outToServer.flush();
			parse = inFromServer.readLine();
			outToServer.println("the Axis y is: " + y);
			outToServer.flush();
			parse = inFromServer.readLine();
			outToServer.println("the Axis z is: " + z);
			outToServer.flush();
			parse = inFromServer.readLine();
			setChanged();
			notifyObservers("completedTask maze generated " + name);
			// byte[] buffer = new
			// byte[properties.getMazeMaxAxisX()*properties.getMazeMaxAxisY()*properties.getMazeMaxAxisZ()+3*3];
			// int mazeByte;
			// int i =0;
			// while((mazeByte=in.read())==(-1))
			// buffer[i++]=(byte) mazeByte;
			//

			//outToServer.println("exit");
			//outToServer.flush();

//			inFromServer.close();
//			outToServer.close();

			//theServer.close();

		} catch (IOException e) {
			// do nothing
		}
		}else
			setChanged();
		notifyObservers("completedTask error Connection Lost\nPlease restart the app");

	}

	//
	// @Override
	// public Maze3d getMaze(String name) {
	// Maze3d temp = mazeMap.get(name);
	//
	// if (temp != null) {
	// return temp;
	// } else {
	// // return("Unavailable maze!");
	// return null;
	// }
	// }

	@Override
	public Maze3d getMaze(String name) {

		try {
			//name=name+"@"+properties.x+"@"+y+"@"+z;
			String parse;
			outToServer.println("get maze");
			outToServer.flush();

			parse = inFromServer.readLine();
			outToServer.println("the name is: " + name + "");
			outToServer.flush();
			parse = inFromServer.readLine();
			if (parse.equals("sending")) { // add else for error
				byte[] buffer = new byte[properties.getMazeMaxAxisX() * properties.getMazeMaxAxisY()
						* properties.getMazeMaxAxisZ() + 3 * 3];
				int mazeByte;
				int i = 0;

				while ((mazeByte = inFromServer.read()) != (127)) {
					System.out.println(mazeByte);

					buffer[i++] = (byte) mazeByte;
				}

				Maze3d maze = new Maze3d(buffer);
				mazeMap.put(name, maze);
				charPositionMap.put(name, maze.getEntrance());
				return maze;
			}
			setChanged();
			notifyObservers("error missing or wrong maze!");
			return null;
		} catch (UnknownHostException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
		}

	}

	@Override
	public void getCrossSectionByX(int index, String name) {
		Maze3d tmpMaze = mazeMap.get(name);

		if (tmpMaze != null) {
			// controller.display(arrIntToString(tmpMaze.getCrossSectionByX(index)));
		} else {
			// controller.display("Unavailable maze!");
		}
	}

	@Override
	public void getCrossSectionByY(int index, String name) {
		Maze3d tmpMaze = mazeMap.get(name);

		if (tmpMaze != null) {
			// controller.display(arrIntToString(tmpMaze.getCrossSectionByY(index)));
		} else {
			// controller.display("Unavailable maze!");
		}

	}

	@Override
	public void getCrossSectionByZ(int index, String name) {
		Maze3d tmpMaze = mazeMap.get(name);

		if (tmpMaze != null) {
			// controller.display(arrIntToString(tmpMaze.getCrossSectionByZ(index)));
		} else {
			// controller.display("Unavailable maze!");
		}

	}

	/**
	 * Converts a dou - dimensional array into a String.
	 * 
	 * @param arr
	 *            - a plain.
	 * @return a string containing the plain.
	 */
	@SuppressWarnings("unused")
	private String arrIntToString(int[][] arr) {
		String s = "";

		for (int[] i : arr) {
			for (int j : i) {
				s = s + j + " ";

			}
			s = s + "\n";
		}

		return s;
	}

	@Override
	public void save(String name, String fileName) {
		Maze3d tmpMaze = mazeMap.get(name);

		if (tmpMaze != null) {
			try {
				MyCompressorOutputStream tmpCompressor = new MyCompressorOutputStream(new FileOutputStream(fileName));
				tmpCompressor.write(tmpMaze.toByteArray());
				tmpCompressor.close(); // compressing the maze into and writing
										// it to the file.
				setChanged();
				notifyObservers("completedTask save"); // notifying the
														// presenter that the
														// save was completed.

			} catch (FileNotFoundException e) {
				setChanged();
				notifyObservers("completedTask error '" + fileName + "' is not a valid file name."); // notifying
																										// the
																										// presenter
																										// that
																										// an
																										// error
																										// has
																										// occurred.

			} catch (IOException e) {
				setChanged();
				notifyObservers("completedTask error IO error."); // notifying
																	// the
																	// presenter
																	// that an
																	// error has
																	// occurred.

			}
		} else {
			setChanged();
			notifyObservers("completedTask error '" + name + "' is an unavailable maze"); // notifying
																							// the
																							// presenter
																							// that
																							// an
																							// error
																							// has
																							// occurred.

		}
	}

	@Override
	public void load(String fileName, String name) {
		try {
			MyDecompressorInputStream tmpDecompressor = new MyDecompressorInputStream(new FileInputStream(fileName));
			byte[] buffer = new byte[35 * 35 * 35];
			if (tmpDecompressor.read(buffer) == -1) {
				Maze3d tmpMaze = new Maze3d(buffer);
				mazeMap.put(name, tmpMaze);
				setChanged();
				notifyObservers("completedTask load " + name); // notifying the
																// presenter
																// that the load
																// was
																// completed.

				tmpDecompressor.close(); // closing resources.
				charPositionMap.put(name, tmpMaze.getEntrance()); // updates the
																	// charpositionMap
																	// for the
																	// entrance
																	// of the
																	// loaded
																	// maze.
			} else {
				setChanged();
				notifyObservers("completedTask error The requsted maze is too big!"); // notifying
																						// the
																						// presenter
																						// that
																						// an
																						// error
																						// has
																						// occurred.

			}
		} catch (FileNotFoundException e) {
			setChanged();
			notifyObservers("completedTask error wrong file path."); // notifying
																		// the
																		// presenter
																		// that
																		// an
																		// error
																		// has
																		// occurred.

		} catch (IOException e) {
			setChanged();
			notifyObservers("completedTask error IO error."); // notifying the
																// presenter
																// that an error
																// has occurred.

		}

	}

	@Override
	public void mazeSize(String name) {

		Maze3d tempMaze = mazeMap.get(name);
		if (tempMaze != null) {
			int size = tempMaze.getxAxis() * tempMaze.getyAxis() * tempMaze.getzAxis() + 9;
			setChanged();
			notifyObservers("completedTask mazeSize " + size); // notifying the
																// presenter
																// that the
																// mazeSize was
																// completed.
		} else {
			setChanged();
			notifyObservers("completedTask error '" + name + "' is unavailable maze"); // notifying
																						// the
																						// presenter
																						// that
																						// an
																						// error
																						// has
																						// occurred.
		}

	}

	@Override
	public void calculateFileSize(String name) {
		Maze3d tmpMaze = mazeMap.get(name);

		if (tmpMaze != null) {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			MyCompressorOutputStream compress = new MyCompressorOutputStream(buffer);
			try {
				compress.write(tmpMaze.toByteArray()); // trying to compress the
														// maze into the buffer.
				setChanged();
				notifyObservers("completedTask fileSize " + name + " " + buffer.size()); // notifying
																							// the
																							// presenter
																							// that
																							// the
																							// mazeSize
																							// was
																							// completed.
			} catch (IOException e) {
				setChanged();
				notifyObservers("completedTask error IO error."); // notifying
																	// the
																	// presenter
																	// that an
																	// error has
																	// occurred.
			} finally {
				try {
					compress.close(); // closing resources.
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			setChanged();
			notifyObservers("completedTask error '" + name + "' is unavailable maze");

		}

	}

	@Override
	public void clue(String name, String algorithm) {
		solve(name, algorithm);
		setChanged();
		notifyObservers("completedTask clue " + name);

	}

	@Override
	public Solution<Position> getSolution(String mazeName) {
		Solution<Position> tmp = solutionMap.get(mazeMap.get(mazeName));

		if (tmp != null) {
			return (tmp);
		} else {

			return null;
		}
	}

	@Override
	public void exit() { // safely terminating the existing threads. Also trying
							// to save and cache all maps.
		
		try {

			if(outToServer!=null)
			{
				outToServer.println("exit");
					outToServer.close();
			}
			if(inFromServer!=null)
				inFromServer.close();		
			if(theServer!=null)
					if(theServer.isConnected())
						theServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			threadPool.shutdown();
			if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
				// presenter.get.display("threads terminated violently!");
			}

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
		String[] moves = mazeMap.get(name).getPossibleMoves(current);
		for (String move : moves) {
			if (move.equals("BACKWARD")) { // checks if up movement is possible.
				current.setY(current.getY() - 1);
				charPositionMap.put(name, current); // updates the new character
													// position.
				setChanged();
				notifyObservers("completedTask movement " + name); // notifying
																	// the
																	// presenter
																	// that the
																	// movement
																	// was
																	// completed.
			}
		}
	}

	@Override
	public void MoveDOWN(String name) {
		Position current = charPositionMap.get(name);
		String[] moves = mazeMap.get(name).getPossibleMoves(current);
		for (String move : moves) {
			if (move.equals("FORWARD")) { // checks if down movement is
											// possible.
				current.setY(current.getY() + 1);
				charPositionMap.put(name, current); // updates the new character
													// position.
				setChanged();
				notifyObservers("completedTask movement " + name); // notifying
																	// the
																	// presenter
																	// that the
																	// movement
																	// was
																	// completed.
			}
		}
	}

	@Override
	public void MoveLEFT(String name) {
		Position current = charPositionMap.get(name);
		String[] moves = mazeMap.get(name).getPossibleMoves(current);
		for (String move : moves) {
			if (move.equals("RIGHT")) { // checks if left movement is possible.
				current.setZ(current.getZ() - 1);
				charPositionMap.put(name, current); // updates the new character
													// position.
				setChanged();
				notifyObservers("completedTask movement " + name); // notifying
																	// the
																	// presenter
																	// that the
																	// movement
																	// was
																	// completed.
			}
		}
	}

	@Override
	public void MoveRIGHT(String name) {
		Position current = charPositionMap.get(name);
		String[] moves = mazeMap.get(name).getPossibleMoves(current);
		for (String move : moves) {
			if (move.equals("LEFT")) { // checks if right movement is possible.
				current.setZ(current.getZ() + 1);
				charPositionMap.put(name, current); // updates the new character
													// position.
				setChanged();
				notifyObservers("completedTask movement " + name); // notifying
																	// the
																	// presenter
																	// that the
																	// movement
																	// was
																	// completed.
			}
		}
	}

	@Override
	public void MoveLVLUP(String name) {

		Position current = charPositionMap.get(name);

		String[] moves = mazeMap.get(name).getPossibleMoves(current);
		for (String move : moves) {
			if (move.equals("UP")) { // checks if level up movement is possible.
				current.setX(current.getX() + 1);
				charPositionMap.put(name, current); // updates the new character
													// position.

				setChanged();
				notifyObservers("completedTask movement " + name); // notifying
																	// the
																	// presenter
																	// that the
																	// movement
																	// was
																	// completed.

			}
		}
	}

	@Override
	public void MoveLVLDOWN(String name) {
		Position current = charPositionMap.get(name);
		String[] moves = mazeMap.get(name).getPossibleMoves(current);
		for (String move : moves) {
			if (move.equals("DOWN")) { // checks if level down movement is
										// possible.
				current.setX(current.getX() - 1);
				charPositionMap.put(name, current); // updates the new character
													// position.
				setChanged();
				notifyObservers("completedTask movement " + name); // notifying
																	// the
																	// presenter
																	// that the
																	// movement
																	// was
																	// completed.
			}
		}
	}

	

	@Override
	public void solve(String name, String algorithm) {
		if (solutionMap.get(name) == null) {
			if(theServer.isConnected()){
			try {
				//Socket theServer = new Socket("localhost", 5400); // TODO add to
																	// properties
				System.out.println("connected to server!");

//				PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
//				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
				String parse;
				outToServer.println("solve maze");
				outToServer.flush();
				parse = inFromServer.readLine();
				outToServer.println("the name is: " + name);
				outToServer.flush();
				parse = inFromServer.readLine();
				outToServer.println("the algorithm is: " + algorithm);
				outToServer.flush();
				parse = inFromServer.readLine();
				// outToServer.println("get solution");
				// outToServer.flush();
				// parse = in.readLine();
				if (parse.equals("sending")) { // TODO add else for error
					byte[] buffer = new byte[properties.getMazeMaxAxisX() * properties.getMazeMaxAxisY()* properties.getMazeMaxAxisZ()];
					int tmpByte;
					int i = 0;

					while ((tmpByte = inFromServer.read()) != (127))
						buffer[i++] = (byte) tmpByte;

					Solution<Position> solution = byteToSolution(buffer, i);
					System.out.println(solution.toString());
					solutionMap.put(mazeMap.get(name), solution);

				}

				//outToServer.println("exit");

				//outToServer.flush();

//				inFromServer.close();
//				outToServer.close();

				//theServer.close();

			} catch (IOException e) {
				// do nothing
			}
		}else
			setChanged();
		notifyObservers("completedTask error Connection Lost\nPlease restart the app");
		}
	}

	private Solution<Position> byteToSolution(byte[] buffer, int length) {
		Solution<Position> solution = new Solution<Position>();
		int i = 0, j = 0;
		for (i = 0; i < length;) {
			solution.add(new State<Position>(new Position(buffer[i], buffer[i + 1], buffer[i + 2])));
			i = i + 3;
		}
		return solution;
	}

	@Override
	public void solution(String name, String algorithm) { // this solution uses a timer and a timertask to move the character to the end of the maze.
		solve(name, algorithm);
		Solution<Position> course = solutionMap.get(mazeMap.get(name));
		System.out.println(course.toString());

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			int i = 0;

			@Override
			public void run() {
				if (i == course.getArr().size() - 1)
					this.cancel();

				charPositionMap.put(name, course.getArr().get(i++).getState()); // setting the new position in the map.
				setChanged();
				notifyObservers("completedTask movement " + name);
			}

		};

		timer.scheduleAtFixedRate(task, 0, 200);

	}
}
