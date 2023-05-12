package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TempleOfTheDog extends JPanel implements KeyListener, ActionListener{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final int GUIHEIGHT = 200;
	
	public static void main(String[] args) {
		new TempleOfTheDog().run();
	}
	
	JFrame window;
	Timer timer;
	
	Dog goodDog;

	public void run()
	{
		window = new JFrame();
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT + GUIHEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.addKeyListener(this);
		window.setVisible(true);
		window.pack();
		
		goodDog = new Dog(400, 400, 10, 10, 5, Color.CYAN);

		timer = new Timer(1000 / 30, this);
		timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			goodDog.up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			goodDog.down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			goodDog.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			goodDog.right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{
			goodDog.up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			goodDog.down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
			goodDog.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			goodDog.right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		goodDog.update();
		
		repaint();
	}
	

	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, HEIGHT, WIDTH, GUIHEIGHT);
		
		goodDog.draw(g);
	}
}










class Dog {
	int x;
	int y;
	int w;
	int h;
	
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	int vertVel;
	int horVel;
	int speed;
	
	Color color;
	
	private Rectangle cBox = new Rectangle();
	
	public Dog(int x, int y, int w, int h, int speed, Color color)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.speed = speed;
		this.color = color;
		
		cBox.setBounds(x - w/2, y - h/2, w, h);
	}
	
	public void update()
	{
		vertVel = 0;
		horVel = 0;
		
		if (up)
		{
			vertVel -= speed;
		}
		if (down)
		{
			vertVel += speed;
		}
		if (left)
		{
			horVel -= speed;
		}
		if (right)
		{
			horVel += speed;
		}
		move(horVel, vertVel);
	}
	
	void move(int x, int y)
	{
		this.x += x;
		if (this.x < 0 || this.x > TempleOfTheDog.WIDTH)
		{
			this.x -= x;
		}
		this.y += y;
		if (this.y < 0 || this.y > TempleOfTheDog.HEIGHT)
		{
			this.y -= y;
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x - (w / 2), y - (h / 2), w, h);
	}
}

