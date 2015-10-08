package model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;
import presenter.Properties;

public abstract class ObservableCommonModel extends Observable implements Model{
	protected HashMap<String, Maze3d> mazeMap;
	protected HashMap<String, Solution<Position>> solutionMap;
	protected HashMap<String, Position> charPositionMap;
	protected ExecutorService threadPool;
	protected Properties properties;
	
	
	public ObservableCommonModel() {			//Ctor
		mazeMap = new HashMap<String, Maze3d>();
		solutionMap = new HashMap<String, Solution<Position>>();
		charPositionMap= new HashMap<String, Position>();
		threadPool = Executors.newCachedThreadPool(); //default
		properties = new Properties();
		properties.setDefaults();
		
	}


	public Properties getProperties() {
		return properties;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
		ExecutorService bufferdpool = threadPool;
		threadPool = Executors.newFixedThreadPool(properties.getMaxThreads()) ;
		bufferdpool.shutdown();
	}
	
	

}


