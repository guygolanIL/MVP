package GuiView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Form<T> {//extends BasicWindow {

	protected T created;
	Shell shell;
	
	public Form(Shell parent, T classObject, String title, int width, int height) {
	//	super(title, width, height);
		shell = new Shell(parent.getDisplay());
		created = null;
		shell.setLayout(new GridLayout(2,false));
		Class<? extends Object> format = classObject.getClass();
		Label titel = new Label(shell,SWT.CENTER);
		titel.setText(format.getName());
		titel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		
		Field[] fields = format.getDeclaredFields();
		HashMap<String, Text> hm = new HashMap<String, Text>();
		for (Field field : fields) {
			new Label(shell,SWT.TOP).setText(field.getName());
		hm.put(field.getName(), new Text(shell, SWT.BORDER));
		}
		
		Button saveButton=new Button(shell, SWT.PUSH);
		saveButton.setText("  Save  ");
		saveButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		saveButton.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						try {
							created=(T) format.getConstructor().newInstance();
					
							for (Field field : fields) {
								System.out.println(field.getName());
								String tmp = hm.get(field.getName()).getText();
								
								System.out.println(tmp);
								String type = field.getType().getSimpleName();
								switch (type)
								{
								
								case "int":
									field.set(created,Integer.parseInt(tmp));
									break;
								case "String":
								case "char[]":
									field.set(created,tmp);
									break;
								} 
							}
						}
						catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
						catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});	
		shell.pack();
	}

	
	public T getObject() {
		return created;
		
	}
public void run()
{
	shell.open();
}
}
