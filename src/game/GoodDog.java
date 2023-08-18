package game;

import java.awt.Color;
import java.awt.Graphics;

public class GoodDog extends GameObject{
	public int health;
	public int maxHealth;
	
	public GoodDog(int x, int y, int width, int height, int health) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.health = health;
		this.maxHealth = health;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
        g.fillRect(x - width / 2, y - height / 2, width, height);
        
        double healthPerc = health / maxHealth;
        g.setColor(Color.RED);
        g.fillRect((int) (x - ((width * healthPerc)/ 2)), y - 30, (int) (width * healthPerc), 10);
	}
	
	public void update()
	{
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
