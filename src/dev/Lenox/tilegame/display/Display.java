package dev.Lenox.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Display {

	private String title;
	private int height;
	private int width;
	private Canvas canvas;
	private JFrame frame;

	public Display(String title, int width, int height) {
		this.title = title;
		this.height = height;
		this.width = width;
		createDisplay();
	}

	public void createDisplay() {
		// Make frame
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// canvas to draw graphics
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		// Display canvas through jframe
		frame.add(canvas);
		frame.pack();
	}
	public Canvas getCanvas(){
		return canvas;
	}
	public JFrame getFrame(){
		return frame;
	}
}
