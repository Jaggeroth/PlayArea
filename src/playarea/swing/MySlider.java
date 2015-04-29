package playarea.swing;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class MySlider {
	public static void main(String[] args) {
		final JSlider direction = new JSlider(JSlider.HORIZONTAL, -180, 180, 0);


		class MyCone extends JComponent {
			private static final double ISO_ANGLE = 26.565051177078;

			public void paint(Graphics g) {
				double centreX = direction.getWidth()/2;
				double centreY = 150;
				double coneRadius = 150;
				double discRadius = 30;
				double arc = 90;
				double facing = direction.getValue();
				if (Math.cos(facing)!=0) {
					double v0 = facing;
					double v1 = Math.sin(Math.toRadians(v0))*2;
					double v2 = Math.cos(Math.toRadians(v0));
					double v3 = Math.toDegrees(Math.atan(v1/v2));
					if (facing>90 || facing<-90)
						v3=180+v3;
					facing = Math.floor(v3);
				}

				Graphics2D g2 = (Graphics2D) g;
				Arc2D.Double cone = new Arc2D.Double(centreX - coneRadius, centreY - (coneRadius/2), coneRadius*2, coneRadius, facing-(arc/2), arc, Arc2D.PIE);
				Ellipse2D.Double disc = new Ellipse2D.Double(centreX - discRadius, centreY - (discRadius/2), discRadius*2, discRadius);
				Area shape = new Area(cone);
				shape.add(new Area(disc));
				g2.draw(shape);
			}
		};

		MyCone cone = new MyCone();

		class Updater implements ChangeListener {
			public void stateChanged(ChangeEvent e) {
				cone.repaint();
			}
		};

		Updater ud = new Updater();
		direction.setMinorTickSpacing(5);
		direction.setMajorTickSpacing(45);
		direction.setPaintTicks(true);
		direction.setPaintLabels(true);
		direction.setLabelTable(direction.createStandardLabels(45));
		direction.addChangeListener(ud);
		JFrame fm = new JFrame();
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container ca = fm.getContentPane();
		ca.setLayout(new BorderLayout());
		ca.add(direction, BorderLayout.NORTH);
		ca.add(cone, BorderLayout.CENTER);
		fm.setSize(500,500);
		fm.setVisible(true);
	}

}