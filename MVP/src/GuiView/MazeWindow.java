package GuiView;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import abstracts.MazeDisplayer;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import mainMaze.Maze3D;
import mazeCube.MazeCube;
import mazePossibleMoves.PossibleMoves;


public class MazeWindow extends BasicWindow{
	protected Maze3d maze;
	protected Position charPosition;
	protected ArrayList<Position> solution;
	protected String selectedXMLpropertiesFile;
	protected DisposeListener exitListener;
	protected SelectionListener generateListener;
	protected KeyListener keyListener;
	protected SelectionListener solveListener;
	protected SelectionListener clueListener;
	protected ArrayList<MazeDisplayer> widgetsList;
	protected Properties properties;
	protected Button clueButton;
	protected Button solveButton;
	
	

	public MazeWindow( String title, int width, int height , Properties properties) {
		super(title, width, height);
		this.properties= properties;
		selectedXMLpropertiesFile = null;
		widgetsList = new ArrayList<MazeDisplayer>();
		shell.setImage(new Image(display, "resources/pacman.png"));
	}



	@Override
	void initWidgets() {
		shell.addDisposeListener(exitListener);
		shell.setLayout(new GridLayout(2,false));	
		Image image= new Image(display,"resources/background.jpg");
		shell.setBackgroundImage(image);
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		//shell.setCursor(new Cursor(shell.getDisplay(), new ImageData("resources/Cursor_Greylight.png").scaledTo(27, 25), 16, 0));
	
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

		MenuItem fileOpenPropItem = new MenuItem(fileMenu, SWT.PUSH);
		fileOpenPropItem.setText("Open properties file");
		fileOpenPropItem.addSelectionListener(new SelectionListener() {
				
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				String[] filterExt = { ".xml" };
				fd.setFilterExtensions(filterExt);
				selectedXMLpropertiesFile = fd.open();
						// TODO connect to mvp
			}
				
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

		MenuItem fileEditPropItem = new MenuItem(fileMenu, SWT.PUSH);
		fileEditPropItem.setText("Edit properties");
		    
		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("Exit");
		fileExitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//  Auto-generated method stub
				
			}
		});

		MenuItem MazeMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		MazeMenuHeader.setText("Maze");

		Menu MazeMenu = new Menu(shell, SWT.DROP_DOWN);
		MazeMenuHeader.setMenu(MazeMenu);

		MenuItem mazePropItem = new MenuItem(MazeMenu, SWT.PUSH);
		mazePropItem.setText("Maze properties");
		mazePropItem.addSelectionListener(new SelectionListener() {
				
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell mazeProp = new Shell(shell);
				mazeProp.setText("Maze Properties");
				mazeProp.setSize(130,120);
				mazeProp.setLayout(new GridLayout(6, false));
				Label xTitle = new Label(mazeProp, SWT.COLOR_WIDGET_DARK_SHADOW);
				xTitle.setText("Dimensions: ");
				xTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));	
				Label xxTitle = new Label(mazeProp, SWT.COLOR_WIDGET_DARK_SHADOW);
				xxTitle.setText("X");
				Text xTextBox =	new Text(mazeProp, SWT.BORDER);
				xTextBox.setText("  ");
				Label yyTitle = new Label(mazeProp, SWT.COLOR_WIDGET_DARK_SHADOW);
				yyTitle.setText("Y");
				Text yTextBox =	new Text(mazeProp, SWT.BORDER);
				yTextBox.setText("  ");
				Label ZZTitle = new Label(mazeProp, SWT.COLOR_WIDGET_DARK_SHADOW);
				ZZTitle.setText("Z");
				Text zTextBox =	new Text(mazeProp, SWT.BORDER);
				zTextBox.setText("  ");
				Button saveButton =  new Button(mazeProp, SWT.PUSH);
				saveButton.setText(" Save ");
				saveButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 6, 1));	
					
				mazeProp.open();
			}
				
			@Override				
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
		});
		    
		    
		shell.setMenuBar(menuBar);
        
		Button generateButton=new Button(shell, SWT.PUSH);
		generateButton.setText("  Generate new maze  ");
		generateButton.setLayoutData(new GridData(SWT.NONE, SWT.None, false, false, 1, 1));
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PropertiesWindow propwindow = new PropertiesWindow(shell,properties,generateListener);
		    	propwindow.open();
			}
				
			@Override				
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
		});
		
		//Main Maze display widget.
		
		MazeDisplayer mazeWidget=new Maze3D(shell, SWT.NULL);
		widgetsList.add(mazeWidget);
		mazeWidget.setFocus();
		mazeWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,5));
		
		
		clueButton=new Button(shell, SWT.PUSH);
		clueButton.setText("        give a clue         ");
		clueButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		clueButton.setEnabled(false);
		clueButton.addSelectionListener(clueListener);
		

		solveButton=new Button(shell, SWT.PUSH);
		solveButton.setText("     Solve the maze     ");
		solveButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		solveButton.setEnabled(false);
		solveButton.addSelectionListener(solveListener);
		
		// cube widget.
		MazeCube mazeCube = new MazeCube(shell, SWT.BORDER);
		mazeCube.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		widgetsList.add(mazeCube);
		
		// possibleMoves widget.
		MazeDisplayer possibleMoves=new PossibleMoves(shell,SWT.BORDER);
		possibleMoves.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, true, 1, 1));
		widgetsList.add(possibleMoves);
		
		for (MazeDisplayer mazeDisplayer : widgetsList) {
			mazeDisplayer.addKeyListener(keyListener);
		}
		
	}
	
	protected void exitRequest() {
		shell.dispose();

	}
	
	public void widgetsRefresh()
	{
		for (MazeDisplayer canvasWidget : widgetsList) {
			if(maze!=null)
				canvasWidget.setMazeData(maze);
			if(charPosition!=null)
				canvasWidget.setCharPosition(charPosition);
			
			canvasWidget.setSolution(solution);
		}
	}
	
	public void setPositionData(Position charPosition) {
		this.charPosition = charPosition;
		widgetsRefresh();
		
	}
	
	public void setMazeData(Maze3d maze){
		this.maze = maze;
		this.solution = new ArrayList<Position>(); //reset the solution map
		Display.getDefault().syncExec(new Runnable() {
		    public void run() {
		    	solveButton.setEnabled(true);
		    	clueButton.setEnabled(true);
		    }
		});
		
		widgetsRefresh();
	
	}
	
	public void setSolution(Solution<Position> solution) {
		ArrayList<Position> arr= new ArrayList<Position>();
    	for ( State<Position> s: solution.getArr()) {
			arr.add(s.getState());
		}
		this.solution= arr;
		widgetsRefresh();
		
	}
	
	public void displayError(String string) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox errorBox =  new MessageBox(shell, SWT.ICON_ERROR); 
				errorBox.setMessage(string);
				errorBox.setText("Error");
				errorBox.open();				
			}
		});
	}
	public void display(String string) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
		    public void run() {
		    	MessageBox messageBox =  new MessageBox(shell, SWT.ICON_INFORMATION); 
		    	messageBox.setMessage(string);
		    	messageBox.setText("hi");
		    	messageBox.open();		
		    	
		    }
		});
	}
	

	public void setGenerateListener(SelectionListener generateListener) {
		this.generateListener = generateListener;
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
		
	}
	
	public void setSolveListener(SelectionListener selectionListener) {
		this.solveListener = selectionListener;
		
	}

	public void setExitListener(DisposeListener exitListener) {
		this.exitListener = exitListener;
	}



	public void setClueListener(SelectionListener selectionListener) {
		this.clueListener = selectionListener;
		
	}
}