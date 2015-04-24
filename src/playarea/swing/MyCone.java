package playarea.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class MyCone extends JComponent {

	public void paint(Graphics g) {
		double centreX = 150;
		double centreY = 75;
		double coneRadius = 150;
		double discRadius = 30;
	    Graphics2D g2 = (Graphics2D) g;
		Arc2D.Double cone = new Arc2D.Double(centreX - coneRadius, centreY - (coneRadius/2), coneRadius*2, coneRadius, 0, -60, Arc2D.PIE);
		Ellipse2D.Double disc = new Ellipse2D.Double(centreX - discRadius, centreY - (discRadius/2), discRadius*2, discRadius);
		Area shape = new Area(cone);
		shape.add(new Area(disc));
		g2.draw(shape);
	}

}
