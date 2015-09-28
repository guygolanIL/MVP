package GuiView;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PossibleMoves extends MazeDisplayer {

	public PossibleMoves(Composite parent, int style) {
		super(parent, style);
		Canvas canvas = this;
		setBackground(new Color(null, 255, 255, 255));
		
		setLayout(new GridLayout(3,false));
		
		Label title = new Label(canvas, SWT.TITLE);
		title.setText("Possible moves controller");
		title.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));
		
		Arrow lvlUp = new Arrow(canvas,"resources/upGreen.png","resources/upRed.png", SWT.FILL);
		lvlUp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Arrow up= new Arrow(canvas,"resources/upGreen.png","resources/upRed.png", SWT.FILL);
		up.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true, 2, 1));
		
		Arrow lvlDown= new Arrow(canvas,"resources/downGreen.png","resources/downRed.png", SWT.FILL);
		lvlDown.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 2));

		Arrow left= new Arrow(canvas,"resources/leftGreen.png","resources/leftRed.png", SWT.FILL);
		left.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Arrow right= new Arrow(canvas,"resources/rightGreen.png","resources/rightRed.png", SWT.FILL);
		left.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Arrow down= new Arrow(canvas,"resources/downGreen.png","resources/downRed.png", SWT.FILL);
		down.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
		
		ArrayList<String> moves = new ArrayList<String>();
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				System.out.println("PossibleMoves paintControl");
				
				if ((charPosition!=null)&&(mazeData!=null))
				{
				String[] possibleMoves = mazeData.getPossibleMoves(charPosition);
				
					for (String string : possibleMoves) {
						moves.add(string);
					}
								
					if (moves.contains("UP"))
						lvlUp.setState(true);
					else
						lvlUp.setState(false);
	
					if (moves.contains("FORWARD"))
						down.setState(true);
					else
						down.setState(false);
	
					if (moves.contains("DOWN"))
						lvlDown.setState(true);
					else
						lvlDown.setState(false);
					
	
					if (moves.contains("LEFT"))
						right.setState(true);
					else
						right.setState(false);
					
	
					if (moves.contains("RIGHT"))
						left.setState(true); 
					else
						left.setState(false);
					
					if (moves.contains("BACKWARD"))
						up.setState(true);
					else
						up.setState(false);
			
				}
			}
		});
		
	}

}




