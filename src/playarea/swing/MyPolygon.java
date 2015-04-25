package playarea.swing;

import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JComponent;

public class MyPolygon extends JComponent {

	public void paint(Graphics g) {
		int x[] = {200, 300, 200, 100};
		int y[] = {100, 150, 200, 150};
		Polygon poly = new Polygon(x,y,4);
		g.drawPolygon(poly);
	}

}
