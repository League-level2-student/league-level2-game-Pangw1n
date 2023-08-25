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
	
	static double[] Normalize(double x, double y)
	{
		double[] vector = new double[2];
		vector[0] = x;
		vector[1] = y;
		double magnitude = Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));
		vector[0] = vector[0] / magnitude;
		vector[1] = vector[1] / magnitude;
		
		return vector;
	}
}
