package game;

import java.awt.Color;
import java.awt.Graphics;

public class GoodDog extends GameObject{
	public int health;
	public int maxHealth;
	
	public GoodDog(double x, double y, int width, int height, ObjectManager objectManager, int health) {
		super(x, y, width, height, objectManager);
		// TODO Auto-generated constructor stub
		this.health = health;
		this.maxHealth = health;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
        g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
        
        double a = health;
        double b = maxHealth;
        double healthPerc = a / b;
        g.setColor(Color.RED);
        g.fillRect((int) (x - ((width * healthPerc)/ 2)), (int)y - 30, (int) (width * healthPerc), 10);
	}
	
	public void update()
	{
		super.update();
		
		for (int i = 0; i < objectManager.enemies.size(); i++)
		{
			if (this.collisionBox.intersects(objectManager.enemies.get(i).collisionBox) && objectManager.enemies.get(i).isActive == true)
			{
				health -= objectManager.enemies.get(i).health;
				objectManager.enemies.get(i).isActive = false;
			}
		}
		if (health >= maxHealth)
		{
			health = maxHealth;
		}
		if (health <= 0)
		{
			health = 0;
		}
	}
}
