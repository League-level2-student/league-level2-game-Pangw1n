package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TempleOfTheDog {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	JFrame window;
	GamePanel panel;
	public static void main(String[] args) {
		new TempleOfTheDog().Run();
	}
	
	public TempleOfTheDog()
	{
		window = new JFrame();
		panel = new GamePanel();
	}
	
	void Run()
	{
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));
		window.add(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(panel);
		window.pack();
	}
}
