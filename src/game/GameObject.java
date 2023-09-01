package game;

import java.awt.Rectangle;

public class GameObject {
	double x;
	double y;
	int width;
	int height;
	
	public boolean isActive;
	
	ObjectManager objectManager;
	public Rectangle collisionBox; 
	
	public GameObject(double x, double y, int width, int height, ObjectManager objectManager)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.objectManager = objectManager;
		collisionBox = new Rectangle();
		isActive = true;
	}
	
	public void move(int x, int y)
	{
		this.x += x;
		if(this.x < 0 || this.x > TempleOfTheDog.WIDTH)
		{
			this.x -= x;
		}
		this.y += y;
		if(this.y < 0 || this.y > TempleOfTheDog.HEIGHT)
		{
			this.y -= y;
		}
	}
	
	public void update()
	{
		collisionBox.setBounds((int)x - (width / 2), (int)y - (height / 2), width, height);
	}
}
