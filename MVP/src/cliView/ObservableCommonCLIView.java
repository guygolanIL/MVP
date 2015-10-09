package cliView;

import java.io.BufferedReader;
import java.io.PrintWriter;
import view.ObservableCommonView;


/**
 * This abstract class represents the CLI interfaces in the MVP.
 *
 * @author Guy Golan && Amit Sandak.
 */
public abstract class ObservableCommonCLIView extends ObservableCommonView {

	protected BufferedReader in; 
	protected PrintWriter out;
	
	/**
	 * Instantiates a new observable common cli view.
	 *
	 * @param in the BufferedReader
	 * @param out the PrintWriter
	 */
	public ObservableCommonCLIView(BufferedReader in , PrintWriter out){		//Ctor
		this.in = in;
		this.out = out;
	}

}
