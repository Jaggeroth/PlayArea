package playarea.swing;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class MyGrid extends JComponent {
	
	final static int ISO_WIDTH = 64;
	final static int ISO_HEIGHT = 32;

	public void paint(Graphics g) {
		Rectangle bounds = g.getClipBounds();
		for (int row = bounds.y; row < bounds.height; row += ISO_HEIGHT) {
			int x2 = bounds.height*2;
			int y2 = row+bounds.height;
			g.drawLine(bounds.x, row, x2, y2);
		}
		for (int row = bounds.x; row < bounds.width; row += ISO_WIDTH ) {
			int x2 = row+(bounds.height*2);
			int y2 = bounds.height;
			g.drawLine(row, bounds.y, x2, y2);
		}
		for (int row = bounds.x; row < bounds.width; row += ISO_WIDTH ) {
			int x2 = row-(bounds.height*2);
			int y2 = bounds.height;
			g.drawLine(row, bounds.y, x2, y2);
		}
		int offSet = 0;
		if (bounds.width % ISO_WIDTH>0)
			offSet = ISO_WIDTH - (bounds.width % ISO_WIDTH);
		for (int row = bounds.y; row < bounds.height; row += ISO_HEIGHT) {
			int y2 = row-bounds.height;
			g.drawLine(bounds.width+offSet, row, bounds.width+offSet-(bounds.height*2), row+bounds.height);
			
		}
	}

}
