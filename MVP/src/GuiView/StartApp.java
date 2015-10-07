package GuiView;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import presenter.Properties;

public class StartApp{ //extends BasicWindow {
	
	String selectedXMLpropertiesFile;
	Shell shell;
	public StartApp() {
		 shell= new Shell();
		

//		super("initialization", 250, 250);
		
		shell.setImage(new Image(shell.getDisplay(), "resources/pacman.png"));
		shell.setLayout(new GridLayout(2,false));	
		Image image= new Image(shell.getDisplay(),"resources/background.jpg");
		shell.setBackgroundImage(image);
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setCursor(new Cursor(shell.getDisplay(), new ImageData("resources/Cursor_Greylight.png").scaledTo(27, 25), 16, 0));
	
		Label label1 =new Label(shell,SWT.NULL);
		label1.setText("To start the the program we need some properties:");
		label1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		
		Label label2 =new Label(shell,SWT.NULL);
		label2.setText("please choose the right option:");
		label2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		
		Button generate = new Button(shell, SWT.PUSH);
		generate.setText(" Generate new Properties ");
		
		Button load = new Button(shell, SWT.PUSH);
		load.setText(" load XML Properties file ");
		
		load.addSelectionListener(new SelectionListener() {
			
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			FileDialog fd=new FileDialog(shell,SWT.OPEN);
			fd.setText("open XML properties file");
			String[] filterExt = { ".xml" };
			fd.setFilterExtensions(filterExt);
			selectedXMLpropertiesFile = fd.open();
		}
			
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
	});

		generate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Form<Properties> generateXml = new Form<Properties>(shell,new Properties(), "Generate new Properties", 250, 300)	;
				generateXml.run();
				Properties prop = generateXml.getObject();	
				FileOutputStream file;
				try {
					file = new FileOutputStream("properties.xml");
					java.beans.XMLEncoder xe1 = new java.beans.XMLEncoder(file);
					xe1.writeObject(prop);
					xe1.flush();
					xe1.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				selectedXMLpropertiesFile = "properties.xml";
						
			
			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	public void start() {
		shell.pack();
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!shell.getDisplay().readAndDispatch()) {
	    	  shell.getDisplay().sleep();
	      }
	    }
		
	}

}
