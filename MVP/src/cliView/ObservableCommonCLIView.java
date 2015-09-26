package cliView;

import java.io.BufferedReader;
import java.io.PrintWriter;
import view.ObservableCommonView;

public abstract class ObservableCommonCLIView extends ObservableCommonView {

	protected BufferedReader in;
	protected PrintWriter out;
	
	public ObservableCommonCLIView(BufferedReader in , PrintWriter out){		//Ctor
		this.in = in;
		this.out = out;
	}

}
