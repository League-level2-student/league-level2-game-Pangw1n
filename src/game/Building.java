package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Building extends GameObject{
	public int typeID;
	public int Level;
	
	public double Countdown;
	public double CountdownMax;
	
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;
	
	boolean closed = false;
	boolean isFiring = false;
	Enemy target;
	
	public Building(double x, double y, int width, int height, ObjectManager objectManager, int id)
	{
		super(x, y, width, height, objectManager);
		typeID = id;
		Level = 1;
		
		if (id == 0)
		{
			Countdown = 2500;
			CountdownMax = 2500;
			
			if (needImage) {
			    loadImage ("goldmine.png");
			}
		}
		else if (id == 1)
		{
			Countdown = 4000;
			CountdownMax = 4000;
			
			if (needImage) {
			    loadImage ("tower.png");
			}
		}
		else if (id == 2)
		{
			Countdown = 10000;
			CountdownMax = 10000;
			
			if (needImage) {
			    loadImage ("trap.png");
			}
		}
	}
	
	public void draw(Graphics g)
	{
		if (gotImage) {
        	g.drawImage(image, (int)x - width / 2, (int)y - height / 2, width, height, null);
        } else {
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
		
		if (isFiring && target != null)
		{
			g.setColor(Color.YELLOW);
			g.drawLine((int)x, (int)y, (int)target.x, (int)target.y);
		}
	}
	
	public void update()
	{
		super.update();
		
		Countdown -= 1000/60;
		
		if (typeID == 2)
		{
			if (Countdown > 0)
			{
				if (closed == false)
				{
					needImage = true;
					loadImage("closedtrap.png");
					closed = true;
				}
			}
			else
			{
				if (closed == true)
				{
					needImage = true;
					loadImage("trap.png");
					closed = false;
				}
			}
		}
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
