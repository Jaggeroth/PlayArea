package playarea.swing;

import javax.swing.JFrame;

public class DrawSwing {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(500, 100, 500, 500);
		window.getContentPane().add(new MyPolygon());
		window.setVisible(true);

	}

}
