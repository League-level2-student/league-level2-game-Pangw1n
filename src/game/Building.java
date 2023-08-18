package game;

import java.awt.Color;
import java.awt.Graphics;

public class Building extends GameObject{
	public int typeID;
	public int Level;
	
	public double Countdown;
	public double CountdownMax;
	
	public Building(int x, int y, int width, int height, int id)
	{
		super(x, y, width, height);
		typeID = id;
		Level = 1;
		
		if (id == 0)
		{
			Countdown = 5000;
			CountdownMax = 5000;
		}
	}
	
	public void draw(Graphics g)
	{
		if (typeID == 0)
		{
			g.setColor(Color.GRAY);
		}
		else if (typeID == 1)
		{
			g.setColor(Color.DARK_GRAY);
		}
		else if (typeID == 2)
		{
			g.setColor(Color.BLACK);
		}
		
        g.fillRect(x - width / 2, y - height / 2, width, height);
	}
	
	public void update()
	{
		Countdown -= 1000/60;
	}
}
