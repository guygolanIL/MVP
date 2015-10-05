package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


import model.Model;
import view.View;

public class Presenter implements Observer {
	private Model model;
	private View view;
	private Properties properties;
	
	private HashMap<String, Command> commandMap;
	
	
	public Presenter(Model model, View view) {
		super();
		this.setModel(model);
		this.setView(view);
		
		properties = new Properties();
		model.setProperties(properties);
		
		this.commandMap = new HashMap<String , Command>();		//inserting all the Commands into the map
		commandMap.put("dir", new Dir(this));
		commandMap.put("generate", new Generate(this));
		commandMap.put("display", new Display(this));
		commandMap.put("save", new Save(this));
		commandMap.put("load", new Load(this));
		commandMap.put("maze", new Size(this));
		commandMap.put("file", new FileSize(this));
		commandMap.put("solve", new Solve(this));
		commandMap.put("exit", new Exit(this));
		commandMap.put("completedTask", new CompletedTask(this));
		commandMap.put("movementRequest", new MovmentRequest(this));
						
		
	}

	@Override
	public void update(Observable comp, Object id) {
		String identifier = ((String)id); //TODO check type
		
		Command c = commandMap.get(identifier.split(" ")[0]);
		
		if(c != null)
		{
			if(identifier.split(" ").length > 1)
			{
				c.doCommand(identifier.substring(identifier.indexOf(' ')+1));			//executing the command.
			}
			else if (!identifier.equals("exit"))
			{
				getView().displayError("Missing parameters.");
			}
			else
			{
				c.doCommand("");
			}
		}
		else
		{
			getView().displayError(identifier + " is not a valid command.");	
		}
		
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setDebugMode(boolean b) {
		properties.setDebug(b);
		
	}
					
}
	


