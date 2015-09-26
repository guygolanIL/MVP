package GuiView;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class MazeCube extends MazeDisplayer {

	Color borderColor;
	
	public MazeCube(Composite parent, int style) {
		super(parent, style);
		borderColor = new Color(getDisplay(), new RGB(0, 0, 0));
		
		setBackground(new Color(null, 255, 255, 255));
//		GridData data = new GridData();
//		data.widthHint = 200;
//		data.heightHint = 200;
//		setLayoutData(data);
		//setLayout(new GridLayout());
		MazeCube canvas = this;
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				Label title = new Label(canvas,SWT.TITLE);
				title.setText("Your location");
				//title.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));
				int canvasWidth = getSize().x;
				int canvasHeight = getSize().y;
				
				int[] points = {(int)(canvasWidth*0.5),0 ,canvasWidth,(int)(canvasHeight*0.2),(int)(canvasWidth*0.6),(int)(canvasHeight*0.4),0,(int)(canvasHeight*0.2)};
				//int[] x = {(int)(canvasWidth*0.5),0,canvasWidth,(int)(canvasWidth*0.5)} ;
				//int[] y = {0,(int)(canvasHeight*0.2),(int)(canvasHeight*0.2),(int)(canvasHeight*0.4)};
				e.gc.drawPolygon(points);
				int[] points2 = {0,(int)(canvasHeight*0.2),0,(int)(canvasHeight*0.8),(int)(canvasWidth*0.6),canvasHeight,canvasWidth,(int)(canvasHeight*0.8),(int)(canvasWidth*0.5),(int)(canvasHeight*0.6),0,(int)(canvasHeight*0.8),(int)(canvasWidth*0.5),(int)(canvasHeight*0.6),(int)(canvasWidth*0.5),0,(int)(canvasWidth*0.6),(int)(canvasHeight*0.4),(int)(canvasWidth*0.5),0,(int)(canvasHeight*0.8)};
				e.gc.drawLine(canvasWidth, (int)(canvasHeight*0.2), canvasWidth, (int)(canvasHeight*0.8));
				e.gc.drawLine((int)(canvasWidth*0.6), canvasHeight, (int)(canvasWidth*0.6), (int)(canvasHeight*0.4));
				e.gc.drawPolygon(points2);
				
				Image image = new Image(getDisplay(),"resources/locationIcon.png");
				int imageWidth = image.getBounds().width;
				int imageHeight = image.getBounds().height;
				int width = (int) Math.round(getSize().x*0.1);
				int height = (int) Math.round(getSize().y*0.2);
				e.gc.drawImage(image,0,0,imageWidth,imageHeight,(int)Math.round(getSize().x*0.5),(int)Math.round(getSize().y*0.5),width,height);
				
			}
		});
	}
}
