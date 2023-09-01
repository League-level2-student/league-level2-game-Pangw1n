package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public Player(double x, double y, int width, int height, ObjectManager objectManager)
	{
		super(x, y, width, height, objectManager);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
        g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
	}
	
	public void update()
	{
		super.update();
		
		if (up)
		{
			move(0, -2);
		}
		if (down)
		{
			move(0, 2);
		}
		if (left)
		{
			move(-2, 0);
		}
		if (right)
		{
			move(2, 0);
		}
	}
}
