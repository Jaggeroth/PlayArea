package playarea.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

public class MyPolygon extends JComponent {

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int x[] = {200, 300, 200, 100};
		int y[] = {100, 150, 200, 150};
		Polygon poly = new Polygon(x,y,4);
		g.drawPolygon(poly);
		//
		Rectangle2D rect = new Rectangle2D.Double(-100, -100, 200, 200);
		AffineTransform rotate = new AffineTransform();
		rotate.rotate(Math.toRadians(45));
		Shape shape = rotate.createTransformedShape(rect);
		AffineTransform scale = new AffineTransform();
		double newHeight = 200/shape.getBounds().getHeight();
		scale.scale(newHeight,newHeight/2);
		//scale.translate(200, 200);
		Shape diamond = scale.createTransformedShape(shape);
		System.out.println(diamond.getBounds().x+ " "+diamond.getBounds().y);
		g2.draw(diamond);
		g2.drawRect(100, 100, 200, 100);
		g2.drawRect(100, 250, 200, 100);
	}

}
