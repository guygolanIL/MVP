package GuiView;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

public class Maze2D extends MazeDisplayer{
	char crossSection;
	protected int state;
	
	public Maze2D(Composite parent,int style){
	       super(parent, style);
	       state = 1;
	       crossSection = 'x'; //default
	       // set a white background   (red, green, blue)
	       setBackground(new Color(null, 0, 0, 0));
	       addPaintListener(new PaintListener() {
	    		
				
				@Override
				public void paintControl(PaintEvent e) {
					//System.out.println("Maze2D paintControl");
					e.gc.setForeground(new Color(null,10,36,106));
					e.gc.setBackground(new Color(null,10,36,106));

					int width=getSize().x;
					int height=getSize().y;
					if ((mazeData!=null)&&(charPosition!=null))
					{
						int[][] maze2d = null;
						int[] position2d = null;
						switch (crossSection)
				    	{
					    	case 'x':
					    		maze2d = mazeData.getCrossSectionByX(charPosition.getX());
//					    		for(int i = 0 ; i <maze2d.length;i++)
//					    		{
//					    			for(int j =0 ; j <maze2d[i].length ; j++)
//					    			{
//					    				System.out.print(maze2d[i][j] + " ");
//					    			}
//					    			System.out.println("");
//					    		}
					    		position2d = new int [] {charPosition.getZ(),charPosition.getY()};
					    		break;
					    	case 'y':
					    		maze2d = mazeData.getCrossSectionByY(charPosition.getY());
//					    		for(int i = 0 ; i <maze2d.length;i++)
//					    		{
//					    			for(int j =0 ; j <maze2d[i].length ; j++)
//					    			{
//					    				System.out.print(maze2d[i][j] + " ");
//					    			}
//					    			System.out.println("");
//					    		}
					    		position2d = new int [] {charPosition.getZ(),charPosition.getX()};
					    		break;
					    	case 'z':
					    		maze2d = mazeData.getCrossSectionByZ(charPosition.getZ());
					    		position2d = new int [] {charPosition.getY(),charPosition.getX()};
					    		break;
					    	default:
					    				//TODO
				    	}
			    		
						int w=width/maze2d[0].length;
						int h=height/maze2d.length;
	
						for(int i=0;i<maze2d.length;i++){
							for(int j=0;j<maze2d[i].length;j++){
								int x=j*w;
						        int y=i*h;
						        if(maze2d[i][j]!=0)
						        	e.gc.fillRectangle(x,y,w,h);
						        else if(solution!=null)
						        {
						        	int flag = 0;
						        	
						        	
						        	switch(crossSection)
						        	{
							        	case 'x':
								        	if(solution.contains(new Position(charPosition.getX(),i,j))){
								        		 flag = 1 ;
								        	}
							        		break;
							        	case 'y':
							        		if(solution.contains(new Position(i,charPosition.getY(),j))){
							        			flag = 1;
							        		}
							        		break;
							        	case 'z':
							        		if(solution.contains(new Position(i,j,charPosition.getZ()))){
							        			flag =1;
							        		}
							        		break;
						        	}	
						        	if(flag == 1)
						        	{
						        		System.out.println("soulotion print");
						        		e.gc.setBackground(new Color(null,255,255,255));
						        		e.gc.fillOval(x+w/2, y+h/2, w/4, h/4);//  (i, j, w, h);
						        		//e.gc.fillRectangle(x, y, 8, 8);
						        		e.gc.setBackground(new Color(null,10,36,106));
						        	}
						        }
						        	
						    }
						}
						Image image;
						if (state == 1){
						 image= new Image(getDisplay(),"resources/pacman.png");
						state=0;
						}
						else 
						{
						 image = new Image(getDisplay(),"resources/closedpacman.png");
							state=1;
						}
						int imageWidth = image.getBounds().width;
						int imageHeight = image.getBounds().height;
						int resizeWidth = w;
						int resizeHeight = h;   
						e.gc.drawImage(image,0,0,imageWidth,imageHeight,position2d[0]*w,position2d[1]*h,resizeWidth,resizeHeight);
					  
					}
				}
			});
	 }

	public void setCrossSection(char cross) {
		if ((cross== 'x')||(cross== 'X')||(cross== 'y')||(cross== 'Y')||(cross== 'z')||(cross== 'Z'))
		{
			this.crossSection=cross;
			Display.getDefault().syncExec(new Runnable() {
			    public void run() {
			    	redraw();
			    	//update();
			    }
			});
		}
		//TODO add exeption
		
	}

}
