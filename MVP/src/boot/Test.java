package boot;



import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Test {
	
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());

		Canvas canvas = new Canvas(shell, SWT.BORDER);
		Listener listener = new Listener() {
			
			int zoomFactor = 50;
			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.MouseWheel:
					zoomFactor = Math.max(0, zoomFactor + event.count);
					Canvas canvas = (Canvas)event.widget;
					canvas.redraw();
					break;
				case SWT.Paint:
					event.gc.drawText("Zoom = "+zoomFactor, 10,10);
					break;
				}
			}
		};
		canvas.addListener(SWT.MouseWheel, listener);
		canvas.addListener(SWT.Paint, listener);
		shell.setSize(400, 300);
		shell.open ();
		while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
		}
			display.dispose ();
		}
}
