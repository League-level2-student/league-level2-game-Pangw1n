package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
        g.fillRect(x - width / 2, y - height / 2, width, height);
	}
	
	public void update()
	{
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
