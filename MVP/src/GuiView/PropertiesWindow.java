package GuiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PropertiesWindow {

	protected Shell main;
	protected Properties properties;
	
	public PropertiesWindow(Shell parent, Properties properties , SelectionListener generateListener)
	{
		 main = new Shell(parent);
		 this.properties = properties;
		main.setText("Maze Properties");
		main.setSize(250, 250);
		main.setLayout(new GridLayout(6, false));
		Label nameTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		nameTitle.setText("Maze name: ");
		nameTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		Text nameBox = new Text(main, SWT.BORDER);
		nameBox.setText(""+properties.getName());
		nameBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		
		Label xTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		xTitle.setText("Dimensions: ");
		xTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		Label xxTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		xxTitle.setText(" X");
		Text xTextBox = new Text(main, SWT.BORDER);
		xTextBox.setText(""+properties.getxAxis());
		Label yyTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		yyTitle.setText("Y");
		Text yTextBox = new Text(main, SWT.BORDER);
		yTextBox.setText(""+properties.getyAxis());
		Label ZZTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		ZZTitle.setText("Z");
		Text zTextBox = new Text(main, SWT.BORDER);
		zTextBox.setText(""+properties.getzAxis());
		zTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		zTextBox.setToolTipText("numbers only, greater than 3");
		xTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		xTextBox.setToolTipText("numbers only, greater than 3");
		yTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		yTextBox.setToolTipText("numbers only, greater than 3");
		
		Label generateTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		generateTitle.setText("Generate Algorithm: ");
		generateTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		Combo generateBox = new Combo(main, SWT.DROP_DOWN);
		generateBox.setItems(new String [] {"SimpleGenerator","MyMaze3dGenerator"});
		if (properties.getGenerateAlgorithm().equals("MyMaze3dGenerator"))
		generateBox.select(1);
		else
			generateBox.select(0);

		generateBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));

		generateTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		
		Label searchTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		searchTitle.setText("Generate Algorithm: ");
		searchTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		Combo searchBox = new Combo(main, SWT.DROP_DOWN);
		searchBox.setItems(new String [] {"BFS","A* manhattan", "A* air distance"});
		switch (properties.getSolveAlgorithm())
		{
		case ("BFS"):
			searchBox.select(0);
		break;
		case ("AstarManhattan"):
			searchBox.select(1);
		break;
		case ("AstarAirDistance"):
			searchBox.select(2);
		}
		searchTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));

		searchBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		
		
		Button saveButton = new Button(main, SWT.PUSH);
		saveButton.setText(" Save ");
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));	
		
		Button generateButton = new Button(main, SWT.PUSH);
		generateButton.setText(" Generate ");
		generateButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1));
		
		
		
		
		
				
				ModifyListener checkAxisData = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				try
				{
				if (Integer.parseInt(((Text)arg0.getSource()).getText())<3)
					saveButton.setEnabled(false);
				else
					saveButton.setEnabled(true);
				}catch (NumberFormatException e)
				{
					saveButton.setEnabled(false);
				}
			}
		};
				
		xTextBox.addModifyListener(checkAxisData);
		yTextBox.addModifyListener(checkAxisData);
		zTextBox.addModifyListener(checkAxisData);

		saveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				try {
					properties.setxAxis(Integer.parseInt(xTextBox.getText()));
					properties.setyAxis(Integer.parseInt(yTextBox.getText()));
					properties.setzAxis(Integer.parseInt(zTextBox.getText()));
					properties.setName(nameBox.getText());
				} catch (NumberFormatException e) {
					MessageBox err = new MessageBox(main,SWT.ICON_ERROR);
					err.setText("Error " );
					err.setMessage("Invalid parameters");
					err.open();
					
				if(properties.isDebugMode()==true)
					e.printStackTrace();
				}
				

	
		
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	//generateButton.addSelectionListener(generateListener);
		generateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generateListener.widgetSelected(arg0);
				main.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public void open() {
		main.open();
		
	}

}