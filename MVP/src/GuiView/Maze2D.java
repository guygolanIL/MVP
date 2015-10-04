package GuiView;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Position;

public class Maze2D extends MazeDisplayer {
	char crossSection;
	protected int state;
	protected int[] goal2d;

	public Maze2D(Composite parent, int style) {
		super(parent, style);
		state = 1;
		crossSection = 'x'; // default
		// set a white background (red, green, blue)
		// setBackground(new Color(null, 0, 0, 0));
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				// System.out.println("Maze2D paintControl");
				e.gc.setForeground(new Color(null, 10, 36, 106));
				e.gc.setBackground(new Color(null, 10, 36, 106));

				int width = getSize().x;
				int height = getSize().y;
				if ((mazeData != null) && (charPosition != null)) {
					Image image;

					int imageWidth;
					int imageHeight;
					int resizeWidth = getSize().x;
					int resizeHeight = getSize().y;
					if (charPosition.equals(mazeData.getExit())) {
						image = new Image(getDisplay(), "resources/winner.jpg");
						imageWidth = image.getBounds().width;
						imageHeight = image.getBounds().height;
						e.gc.drawImage(image, 0, 0, imageWidth, imageHeight, 0, 0, resizeWidth, resizeHeight);

					} 
					else 
					{
						setBackground(new Color(null, 0, 0, 0));

						int[][] maze2d = null;
						int[] position2d = null;
						switch (crossSection) {
						case 'x':
							maze2d = mazeData.getCrossSectionByX(charPosition.getX());							///
							position2d = new int[] { charPosition.getZ(), charPosition.getY() };				///
							goal2d = new int[] { mazeData.getExit().getZ(), mazeData.getExit().getY() };		/// 
							break;																				///
						case 'y':																				///
							maze2d = mazeData.getCrossSectionByY(charPosition.getY());							///	
							position2d = new int[] { charPosition.getZ(), charPosition.getX() };				///
							goal2d = new int[] { mazeData.getExit().getZ(), mazeData.getExit().getX() };		/// NEED FIX!
							break;																				///
						case 'z':																				///
							maze2d = mazeData.getCrossSectionByZ(charPosition.getZ());							///
							position2d = new int[] { charPosition.getY(), charPosition.getX() };				///
							goal2d = new int[] { mazeData.getExit().getY(), mazeData.getExit().getX() };		///
							break;																				///
						default:
							// TODO
						}

						int w = width / maze2d[0].length;
						int h = height / maze2d.length;

						for (int i = 0; i < maze2d.length; i++) {
							for (int j = 0; j < maze2d[i].length; j++) {
								int x = j * w;
								int y = i * h;
								if (maze2d[i][j] != 0)
									e.gc.fillRectangle(x, y, w, h);
								else if (solution != null) {
									int flag = 0;

									switch (crossSection) {
									case 'x':
										if (solution.contains(new Position(charPosition.getX(), i, j))) {
											flag = 1;
										}
										break;
									case 'y':
										if (solution.contains(new Position(i, charPosition.getY(), j))) {
											flag = 1;
										}
										break;
									case 'z':
										if (solution.contains(new Position(i, j, charPosition.getZ()))) {
											flag = 1;
										}
										break;
									}
									if (flag == 1) {
										System.out.println("solution print");
										e.gc.setBackground(new Color(null, 255, 255, 255));
										e.gc.fillOval(x + w / 2, y + h / 2, w / 4, h / 4);// (i,
																							// j,
																							// w,
																							// h);
										// e.gc.fillRectangle(x, y, 8, 8);
										e.gc.setBackground(new Color(null, 10, 36, 106));
									}
								}

							}
						}
						int flag = 0;
						resizeWidth = w;
						resizeHeight = h;
						switch (crossSection) {
						case 'x':
							if (mazeData.getExit().getX() == charPosition.getX())
								flag = 1;
							break;
						case 'y':
							if (mazeData.getExit().getY() == charPosition.getY())
								flag = 1;
							break;
						case 'z':
							if (mazeData.getExit().getZ() == charPosition.getZ())
								flag = 1;
							break;
						}
						if (flag == 1) {
							image = new Image(getDisplay(), "resources/pacmanwoman.png");
							imageWidth = image.getBounds().width;
							imageHeight = image.getBounds().height;
							e.gc.drawImage(image, 0, 0, imageWidth, imageHeight, goal2d[0] * w, goal2d[1] * h,resizeWidth, resizeHeight);

						}

						if (state == 1) {
							image = new Image(getDisplay(), "resources/pacman.png");
							state = 0;
						} else {
							image = new Image(getDisplay(), "resources/closedpacman.png");
							state = 1;
						}
						imageWidth = image.getBounds().width;
						imageHeight = image.getBounds().height;

						e.gc.drawImage(image, 0, 0, imageWidth, imageHeight, position2d[0] * w, position2d[1] * h,resizeWidth, resizeHeight);
					}
				}
			}
		});
	}

	public void setCrossSection(char cross) {
		if ((cross == 'x') || (cross == 'X') || (cross == 'y') || (cross == 'Y') || (cross == 'z') || (cross == 'Z')) {
			this.crossSection = cross;
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					redraw();
					// update();
				}
			});
		}
		// TODO add exeption

	}

}
