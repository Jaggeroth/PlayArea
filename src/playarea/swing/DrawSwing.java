package playarea.swing;

import javax.swing.JFrame;

public class DrawSwing {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, 1000, 200);
		window.getContentPane().add(new MyCone());
		window.setVisible(true);

	}

}
