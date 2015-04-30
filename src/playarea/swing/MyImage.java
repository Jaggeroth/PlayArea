package playarea.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MyImage extends Component {

	private static final long serialVersionUID = -437160680949871304L;
	Image token;

	public MyImage() {
		try {
			token = ImageIO.read(getClass().getResource("../images/kal.png"));
			token = rotate(token, 45);
			token = resize(token, 200, 100);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static Image rotate(Image img, double angle)
	{
		double sin = Math.abs(Math.sin(Math.toRadians(angle)));
		double cos = Math.abs(Math.cos(Math.toRadians(angle)));

		int w = img.getWidth(null), h = img.getHeight(null);

		int neww = (int) Math.floor(w*cos + h*sin);
		int newh = (int) Math.floor(h*cos + w*sin);

		BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
		Graphics2D g = bimg.createGraphics();

		g.translate((neww-w)/2, (newh-h)/2);
		g.rotate(Math.toRadians(angle), w/2, h/2);
		g.drawRenderedImage(toBufferedImage(img), null);
		g.dispose();

		return toImage(bimg);
	}
	public static Image resize(Image img, int width, int height){
		// Create a null image
		Image image = null;
		// Resize into a BufferedImage
		BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimg.createGraphics();
		bGr.drawImage(img, 0, 0, width, height, null);
		bGr.dispose();
		// Convert to Image and return it
		image = toImage(bimg);
		return image;
	}
	public static BufferedImage toBufferedImage(Image img){
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		// Return the buffered image
		return bimage;
	}
	public static Image getEmptyImage(int width, int height){
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		return toImage(img);
	}

	public static Image toImage(BufferedImage bimage){
		// Casting is enough to convert from BufferedImage to Image
		Image img = (Image) bimage;
		return img;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(token, 0, 0, null);
	}

	@Override
	public Dimension getPreferredSize() {
		if (token == null) {
			return new Dimension(100,100);
		} else {
			return new Dimension(token.getWidth(null), token.getHeight(null));
		}
	}

	public static void main(String[] args) {
		System.out.println("Start");

		JFrame f = new JFrame("Iso Token Image");

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.add(new MyImage());
		f.pack();
		f.setVisible(true);
	}

}
