package GuiView;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class Maze3D extends MazeDisplayer {

	protected Maze2D leftDisplay;
	protected Maze2D rightDisplay;

	public Maze3D(Composite parent, int style) {
		super(parent, style);
		// setBackground(new Color(null, 255, 255, 255));
		setLayout(new GridLayout(2, true));

		Label title = new Label(this, SWT.TITLE);
		title.setText("3D maze display by cross sections");
		title.setLayoutData(new GridData(SWT.TOP, SWT.TOP, false, false, 2, 1));

//		Combo leftDisplayOptions = new Combo(this, SWT.DROP_DOWN);
//		leftDisplayOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
//		leftDisplayOptions.setItems(new String[] { "x", "y", "z" });
//		leftDisplayOptions.select(0);
//		leftDisplayOptions.setBackground(new Color(getDisplay(), 255, 255, 255));
//
//		Combo rightDisplayOptions = new Combo(this, SWT.DROP_DOWN);
//		rightDisplayOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
//		rightDisplayOptions.setItems(new String[] { "x", "y", "z" });
//		rightDisplayOptions.select(1);
//		rightDisplayOptions.setBackground(new Color(getDisplay(), 255, 255, 255));

		leftDisplay = new Maze2D(this, SWT.NULL);
		leftDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		leftDisplay.setCrossSection('x');
		rightDisplay = new Maze2D(this, SWT.NULL);
		rightDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		rightDisplay.setCrossSection('z');

		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

			}

		});

	}

	@Override
	public void setMazeData(Maze3d maze) {
		this.mazeData = maze;
		leftDisplay.setMazeData(maze);
		rightDisplay.setMazeData(maze);
		widgetsRefresh();

	}

	@Override
	public void addKeyListener(KeyListener listener) {
		super.addKeyListener(listener);
		leftDisplay.addKeyListener(listener);
		rightDisplay.addKeyListener(listener);
	}

	@Override
	public void setCharPosition(Position position) {
		this.charPosition = position;
		leftDisplay.setCharPosition(position);
		rightDisplay.setCharPosition(position);
		widgetsRefresh();

	}

	@Override
	public void setSolution(ArrayList<Position> solution) {
		this.solution = solution;
		leftDisplay.setSolution(solution);
		rightDisplay.setSolution(solution);
		widgetsRefresh();

	}

	public void widgetsRefresh() {
		if ((mazeData != null) && (charPosition != null))
			Display.getDefault().syncExec(new Runnable() {
				public void run() {

					redraw();
				}
			});

	}
}
