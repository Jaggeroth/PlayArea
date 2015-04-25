package playarea.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class MyCone extends JComponent {
	private static final double ISO_ANGLE = 26.565051177078;

	public void paint(Graphics g) {
		double centreX = 150;
		double centreY = 75;
		double coneRadius = 150;
		double discRadius = 30;
		double facing = ISO_ANGLE;
		//double facing = -180-ISO_ANGLE;
		//double facing = -ISO_ANGLE;
		if (Math.cos(facing)!=0) {
			double extra = 0;
			if (facing>90)
				extra=90;
			if (facing<-90)
				extra=-90;
			double v1 = Math.sin(Math.toRadians(facing))*2;
			double v2 = Math.cos(Math.toRadians(facing));
			double v3 = Math.toDegrees(Math.atan(v1/v2));
			facing = v3+extra;
			//System.out.println(Math.round(v3+extra));
		}
		System.out.println("New Facing="+facing);
		double arc = 90;
	    Graphics2D g2 = (Graphics2D) g;
		Arc2D.Double cone = new Arc2D.Double(centreX - coneRadius, centreY - (coneRadius/2), coneRadius*2, coneRadius, facing-(arc/2), arc, Arc2D.PIE);
		Ellipse2D.Double disc = new Ellipse2D.Double(centreX - discRadius, centreY - (discRadius/2), discRadius*2, discRadius);
		Area shape = new Area(cone);
		shape.add(new Area(disc));
		g2.draw(shape);
	}

}
