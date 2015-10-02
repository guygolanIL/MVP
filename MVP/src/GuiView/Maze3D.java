package GuiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class Maze3D extends MazeDisplayer {
	
	protected Maze2D rightDisplay;
	protected Maze2D leftDisplay;

	public Maze3D(Composite parent, int style) {
		super(parent, style);

		setBackground(new Color(null, 255, 255, 255));
		setLayout(new GridLayout(2,true));
		
		Label title = new Label(this, SWT.TITLE);
		title.setText("3d maze display by cross sections");
		title.setLayoutData(new GridData(SWT.TOP, SWT.TOP, false, false, 2, 1));
		
		rightDisplay = new Maze2D(this, SWT.BORDER);
		rightDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		rightDisplay.setCrossSection('x');
		leftDisplay = new Maze2D(this, SWT.BORDER);
		leftDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		leftDisplay.setCrossSection('y');
		
		
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				}
			
		});
	
	}
	@Override
	public void setMazeData(Maze3d maze)
	{
		this.mazeData = maze;
		leftDisplay.setMazeData(maze);
		rightDisplay.setMazeData(maze);
		widgetsRefresh();
		
	}

	@Override
	public void addKeyListener(org.eclipse.swt.events.KeyListener listener) {
		super.addKeyListener(listener);
		leftDisplay.addKeyListener(listener);
		rightDisplay.addKeyListener(listener);
	}

	@Override
	public void setCharPosition(Position position)
	{
		this.charPosition = position;
		leftDisplay.setCharPosition(position);
		rightDisplay.setCharPosition(position);
		widgetsRefresh();
		
	}
	public void widgetsRefresh()
	{
			if((mazeData !=null)&&(charPosition!=null))
				Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	redraw();
				    }
				});
			
	}
}
	
	
	
	


