package presenter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class CompletedTask extends CommonCommand implements Command {

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
				//presenter.getView().display(s[2]+" maze generated.");
				presenter.getView().display(presenter.getModel().getMaze(s[2]));
				presenter.getView().display(presenter.getModel().getCharPosition(s[2]));
			}
			else
				presenter.getView().display("Unavailable maze!");
				 
		}
		else if ((s[0].equals("solution"))&&(s.length > 3))		//checks if the first parameter is "solution"
		{
			Solution<Position> result = presenter.getModel().getSolution(s[1]);
			if (result != null)
				presenter.getView().display(result.toString());
			else
				presenter.getView().display("Unavailable solution!");
		}
		
	}

	

}
