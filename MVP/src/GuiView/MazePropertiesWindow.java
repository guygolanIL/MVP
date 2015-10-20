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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class collecting all the data of the current maze in a GUI window.
 * 
 *  @author Guy Golan && Amit Sandak.
 */
 
public class MazePropertiesWindow {

	/** The form window. */
	protected Shell main;
	
	/** The maze properties. */
	protected MazeProperties properties;

	/**
	 * Instantiates a new maze properties window.
	 *
	 * @param parent the parent shell
	 * @param properties the current properties. used to show the current values in the form.
	 * @param generateListener the listener that define the behavior after the new properties created.
	 */
	public MazePropertiesWindow(Shell parent, MazeProperties properties, SelectionListener generateListener) {
		main = new Shell(parent);
		this.properties = properties;

		main.setText("Maze Properties");
		main.setSize(200, 250);
		main.setLayout(new GridLayout(6, true));
		
		
		Label nameTitle = new Label(main, SWT.COLOR_WIDGET_DARK_SHADOW);
		nameTitle.setText("Maze name: ");
		nameTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));

		Text nameBox = new Text(main, SWT.BORDER);
		
		//splitting the received string from it's name[x][y][z] format.
		nameBox.setText("" + properties.getName().split("\\[")[0]);
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
		xTextBox.setTextLimit(2);
		xTextBox.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {}
			
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
		yTextBox.setTextLimit(2);
		yTextBox.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {}
					
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
		zTextBox.setTextLimit(2);
		zTextBox.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent arg0) {}
					
					@Override
					public void focusGained(FocusEvent arg0) {
						zTextBox.setText("");
						
					}
				});
		
		Button saveButton = new Button(main, SWT.PUSH);
		saveButton.setText(" Save ");
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));

		Button generateButton = new Button(main, SWT.PUSH);
		generateButton.setText(" Generate ");
		generateButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1));
		generateButton.setFocus();

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
					
				} catch (NumberFormatException e) {
					MessageBox err = new MessageBox(main, SWT.ICON_ERROR);
					err.setText("Error ");
					err.setMessage("Invalid parameters");
					err.open();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		generateButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generateListener.widgetSelected(arg0);
				main.dispose();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		main.pack();
	}

	/**
	 * Open the form window.
	 */
	public void open() {
		main.open();

	}

}
