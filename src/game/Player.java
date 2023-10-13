package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;
	
	public Player(double x, double y, int width, int height, ObjectManager objectManager)
	{
		super(x, y, width, height, objectManager);
		
		if (needImage) {
		    loadImage ("player.png");
		}
	}
	
	public void draw(Graphics g)
	{
		if (gotImage) {
        	g.drawImage(image, (int)x - width / 2, (int)y - height / 2, width, height, null);
        } else {
    		g.setColor(Color.BLUE);
            g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
        }
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
