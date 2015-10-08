package cliView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

public class MyObservableCLIView extends ObservableCommonCLIView {

	boolean exit;
	
	public MyObservableCLIView(BufferedReader in, PrintWriter out) {
		super(in, out);
	}

	@Override
	public void start() {
		exit = false;
		
		new Thread(new Runnable() {					//the user interface runs in an independent thread.
					
				@Override
				public void run() {
					
					try {
						String buffer;
						
						while(!exit){
							out.print("Enter Command >> ");
							out.flush();
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
		if(strings.length > 0 )
		{
			for (String string : strings) 
				out.println(string);
			
			out.flush();
		}
		else
		{
			display("Empty Directory.");
		}
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
	public void display(String string) {
		out.println(string);
		out.flush();
	}
	
	@Override
	public void display(Maze3d maze) {
		out.println(maze);
		out.flush();
	}

	@Override
	public void display(Position charPosition) {
		out.println(charPosition);
		out.flush();
	}

	@Override
	public void displayError(String string) {
		out.println("Error: "+string);
		out.flush();
	}

	@Override
	public void displayCrossSectionByX(int parseInt, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSectionByY(int parseInt, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSectionByZ(int parseInt, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(Solution<Position> result) {
		 out.println(result);
		out.flush();
		
	}

	@Override
	public void setProperties(Properties prop) {
		// TODO Auto-generated method stub
		
	}


	
	
}
