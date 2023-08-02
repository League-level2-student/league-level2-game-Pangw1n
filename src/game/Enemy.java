package game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
        g.fillRect(x - width / 2, y - height / 2, width, height);
	}
}
