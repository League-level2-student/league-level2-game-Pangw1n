package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
        g.fillRect(x - width / 2, y - height / 2, width, height);
	}
}
