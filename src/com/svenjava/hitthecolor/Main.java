package com.svenjava.hitthecolor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				showFrame();
			}

		});
	}

	private static void showFrame() {
		JFrame frame = new JFrame("Hit the Color!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel panel = new GamePanel();
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
