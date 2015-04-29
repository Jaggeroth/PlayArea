package playarea.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MyImage extends Component {
	
	Image token;
	
	public MyImage() {
		try {
			token = ImageIO.read(getClass().getResource("../images/kal.png"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
 
    public Dimension getPreferredSize() {
		System.out.println("getPreferredSize");
        if (token == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(token.getWidth(null), token.getHeight(null));
       }
    }

	public static void main(String[] args) {
		System.out.println("Start");
 
        JFrame f = new JFrame("Load Token Image");
             
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
