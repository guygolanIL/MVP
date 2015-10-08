package presenter;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PropertiesUpdate extends CommonCommand {

	public PropertiesUpdate(Presenter presenter) {
		super(presenter);
		
	}

	@Override
	public void doCommand(String param) {
	String s[] = param.split(" ");
	if(s.length > 1)
		{
		Properties prop ;
		try {
			FileInputStream in = new FileInputStream(s[1]);
			XMLDecoder decoder = new XMLDecoder(in);
			prop = (Properties)decoder.readObject();
			decoder.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("file not found, default properties will be loaded");
			prop = new Properties();
		}
		presenter.setProperties(prop);
		
		}
			else
			{
			presenter.getView().displayError("Missing parameters.");
			}
		
		}
}


	