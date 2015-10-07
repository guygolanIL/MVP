package presenter;

public class Clue extends CommonCommand {

	public Clue(Presenter presenter) {		//Ctor
		super(presenter);
	}

	/**
	 * Using the Controller to solve a Maze3d.
	 * @param param - parameters.
	 */
	@Override
	public void doCommand(String param) {
		
		String s[] = param.split(" ");
		
		if(s.length > 1)
		{
			presenter.getModel().clue(s[0],s[1]);
		}
		else
		{
			presenter.getView().displayError("Missing parameters.");
		}
	}


}
