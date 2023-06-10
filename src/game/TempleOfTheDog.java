package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TempleOfTheDog {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	JFrame window;
	GamePanel panel;
	public static void main(String[] args) {
		
	}
	
	public TempleOfTheDog()
	{
		window = new JFrame();
		panel = new GamePanel();
	}
	
	void Run()
	{
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(panel);
		window.pack();
	}
}
