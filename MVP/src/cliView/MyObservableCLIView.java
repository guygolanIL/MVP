package cliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MyObservableCLIView extends ObservableCommonCLIView {

	boolean exit;
	
	public MyObservableCLIView(BufferedReader in, PrintWriter out) {
		super(in, out);
	}

	@Override
	public void start() {
		exit = false;
		
		new Thread(new Runnable() {					//the user interface runs in an independant thread.
					
				@Override
				public void run() {
					
					try {
						String buffer;
						
						while(!exit){
							buffer = in.readLine();
							setChanged();
							notifyObservers(buffer);	
						}
					} catch (IOException e) {
						e.printStackTrace();
					}				
				}
		}).start();
	}

	@Override
	public void display(String[] strings) {
		for (String string : strings) 
			out.println(string);
		
		out.flush();
	}

	@Override
	public void display(String string) {
		out.println(string);
		out.flush();
	}

	@Override
	public void exit() {
		exit = true;	
	}

	@Override
	public void exitRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(Position charPosition) {
		// TODO Auto-generated method stub
		
	}
	
}
