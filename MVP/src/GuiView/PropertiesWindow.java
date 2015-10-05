package GuiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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

	public PropertiesWindow(Shell parent, Properties properties, SelectionListener generateListener) {
		main = new Shell(parent);
		this.properties = properties;

		main.setText("Maze Properties");
		main.setSize(180, 250);
		main.setLayout(new GridLayout(6, true));
		
		
		Label nameTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		nameTitle.setText("Maze name: ");
		nameTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));

		Text nameBox = new Text(main, SWT.BORDER);
		nameBox.setText("" + properties.getName());
		nameBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
		
		
		Label dimensionsTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		dimensionsTitle.setText("Dimensions: ");
		dimensionsTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));

		Label xTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		xTitle.setText(" X");

		Text xTextBox = new Text(main, SWT.BORDER);
		xTextBox.setText("" + properties.getxAxis());
		xTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		xTextBox.setToolTipText("numbers only, greater than 3");
		xTextBox.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				xTextBox.setText("");
				
			}
		});

		Label yTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		yTitle.setText("Y");

		Text yTextBox = new Text(main, SWT.BORDER);
		yTextBox.setText("" + properties.getyAxis());
		yTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		yTextBox.setToolTipText("numbers only, greater than 3");
		yTextBox.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						
					}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						yTextBox.setText("");
						
					}
				});
		
		Label zTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		zTitle.setText("Z");

		Text zTextBox = new Text(main, SWT.BORDER);
		zTextBox.setText("" + properties.getzAxis());
		zTextBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		zTextBox.setToolTipText("numbers only, greater than 3");
		zTextBox.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						
					}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						zTextBox.setText("");
						
					}
				});
		
		Label generateTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		generateTitle.setText("Generate Algorithm: ");
		generateTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		Combo generateBox = new Combo(main, SWT.DROP_DOWN);
		generateBox.setItems(new String[] { "SimpleGenerator", "MyMaze3dGenerator" });
		if (properties.getGenerateAlgorithm().equals("MyMaze3dGenerator"))
			generateBox.select(1);
		else
			generateBox.select(0);

		generateBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));

		generateTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));

		Label searchTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		searchTitle.setText("Solving Algorithm: ");
		searchTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1));
		Combo searchBox = new Combo(main, SWT.DROP_DOWN);
		searchBox.setItems(new String[] { "BFS", "A* manhattan", "A* air distance" });
		switch (properties.getSolveAlgorithm()) {
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
				try {
					if (Integer.parseInt(((Text) arg0.getSource()).getText()) < 3)
						saveButton.setEnabled(false);
					else
						saveButton.setEnabled(true);
				} catch (NumberFormatException e) {
					saveButton.setEnabled(false);
				}
			}
		};

		xTextBox.addModifyListener(checkAxisData);
		yTextBox.addModifyListener(checkAxisData);
		zTextBox.addModifyListener(checkAxisData);

		////////////Save sequence/////////////////////////////////////////////////////////////////////////////
		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				try {
					properties.setxAxis(Integer.parseInt(xTextBox.getText()));
					properties.setyAxis(Integer.parseInt(yTextBox.getText()));
					properties.setzAxis(Integer.parseInt(zTextBox.getText()));
					properties.setName(nameBox.getText());
					properties.setGenerateAlgorithm(generateBox.getText());
					switch (searchBox.getText()) {
					case ("BFS"):
						properties.setSolveAlgorithm("BFS");
					break;
					case ("A* manhattan"):
						properties.setSolveAlgorithm("AstarManhattan");	
					break;
					case ("A* air distance"):
						properties.setSolveAlgorithm("AstarAirDistance");	
					break;
					default:
						properties.setSolveAlgorithm("AstarAirDistance");	
					}
					
					
				} catch (NumberFormatException e) {
					MessageBox err = new MessageBox(main, SWT.ICON_ERROR);
					err.setText("Error ");
					err.setMessage("Invalid parameters");
					err.open();

					if (properties.isDebugMode() == true)
						e.printStackTrace();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		// generateButton.addSelectionListener(generateListener);
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
		main.pack();
	}

	public void open() {
		main.open();

	}

}
