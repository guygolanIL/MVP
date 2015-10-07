package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Presenter;

public interface View {

	public void display(String[] strings);
	public void display(String string);
	public void display(Maze3d maze);
	public void exit() ;
	public void start();
	void exitRequest();
	public void display(Position charPosition);
	public void displayError(String string);
	public void displayCrossSectionByX(int parseInt, String string);
	public void displayCrossSectionByY(int parseInt, String string);
	public void displayCrossSectionByZ(int parseInt, String string);
	public void display(Solution<Position> result);
	

}
