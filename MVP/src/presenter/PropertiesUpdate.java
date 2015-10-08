package presenter;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PropertiesUpdate extends CommonCommand {

	public PropertiesUpdate(Presenter presenter) {
		super(presenter);
		
	}

	@Override
	public void doCommand(String param) {
		System.out.println(param);
	String s[] = param.split(" ");
	if(s.length > 0)
		{
		Properties prop ;
		try {
			System.out.println(s[0]);
			FileInputStream in = new FileInputStream(s[0]);
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
			prop = (Properties)decoder.readObject();
			decoder.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("file not found, default properties will be loaded");
			prop = new Properties();
			prop.setDefaults();
		}
		presenter.setProperties(prop);
		
		}
			else
			{
			presenter.getView().displayError("Missing parameters.");
			}
		
		}
}


	