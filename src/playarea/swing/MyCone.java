package playarea.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class MyCone extends JComponent {

	public void paint(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
		Arc2D.Double cone = new Arc2D.Double(0, 0, 300, 150, 0, -60, Arc2D.PIE);
		Ellipse2D.Double disc = new Ellipse2D.Double(120, 60, 60, 30);
		Area shape = new Area(cone);
		shape.add(new Area(disc));
		g2.draw(shape);
	}

}
