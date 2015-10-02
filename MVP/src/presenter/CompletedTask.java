package presenter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class CompletedTask extends CommonCommand {

	public CompletedTask(Presenter presenter) {
		super(presenter);
		
	}

	@Override
	public void doCommand(String param) {
		String[] s = param.split(" ");
		if ((s[0].equals("maze"))&&(s.length > 2))		//validates if there are no missing parameters
		{
			Maze3d maze = presenter.getModel().getMaze(s[2]);
			if (maze != null)
			{
				presenter.getView().display(s[2]+" maze generated.");
				presenter.getView().display(presenter.getModel().getMaze(s[2]));
				presenter.getView().display(presenter.getModel().getCharPosition(s[2]));
			}
			else
				presenter.getView().displayError("Unavailable maze!");
				 
		}
		else if ((s[0].equals("solution"))&&(s.length > 1))		//checks if the first parameter is "solution"
		{
			Solution<Position> result = presenter.getModel().getSolution(s[1]);
			if (result != null)
				presenter.getView().display(result);
			else
				presenter.getView().displayError("Unavailable solution!");
		}
		else if(s[0].equals("save"))
		{
			presenter.getView().display("Save completed.");
		}
		else if(s[0].equals("load"))
		{
			presenter.getView().display("Load completed.");
		}
		else if((s[0].equals("fileSize"))&&(s.length > 2))
		{
			presenter.getView().display(s[1]+ " file size is: "+s[2]);
		}
		else if((s[0].equals("mazeSize")) && (s.length > 1))
		{
			presenter.getView().display("maze size is: "+s[1]);
		}
		else if(s[0].equals("error") && (s.length > 1))
		{
			presenter.getView().displayError(param.substring(6));
		}
		else if(s[0].equals("movement")&& (s.length > 1))
		{
			presenter.getView().display(presenter.getModel().getCharPosition(s[1]));
			if (presenter.getProperties().isDebug()==true)
				System.out.println("position updated");
		}
	}
}
