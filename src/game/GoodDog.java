package game;

import java.awt.Color;
import java.awt.Graphics;

public class GoodDog extends GameObject{
	public GoodDog(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
        g.fillRect(x - width / 2, y - height / 2, width, height);
	}
}
