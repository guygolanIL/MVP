package model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public abstract class ObservableCommonModel extends Observable implements Model{
	protected HashMap<String, Maze3d> mazeMap;
	protected HashMap<String, Solution<Position>> solutionMap;
	protected HashMap<String, Position> charPositionMap;
	protected ExecutorService threadPool;
	
	
	public ObservableCommonModel() {			//Ctor
		mazeMap = new HashMap<String, Maze3d>();
		solutionMap = new HashMap<String, Solution<Position>>();
		charPositionMap= new HashMap<String, Position>();
		threadPool = Executors.newCachedThreadPool();
	}

}


