package game;

import java.awt.Color;
import java.awt.Graphics;

public class Building extends GameObject{
	public int typeID;
	public int Level;
	
	public double Countdown;
	public double CountdownMax;
	
	public Building(double x, double y, int width, int height, ObjectManager objectManager, int id)
	{
		super(x, y, width, height, objectManager);
		typeID = id;
		Level = 1;
		
		if (id == 0)
		{
			Countdown = 2500;
			CountdownMax = 2500;
		}
		else if (id == 1)
		{
			Countdown = 4000;
			CountdownMax = 4000;
		}
		else if (id == 2)
		{
			Countdown = 10000;
			CountdownMax = 10000;
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
		
        g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
	}
	
	public void update()
	{
		super.update();
		
		Countdown -= 1000/60;
	}
}
