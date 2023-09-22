package game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{
	GameObject target;
	public int health;
	double speed = 1;
	double baseSpeed;
	int trapped = 0;
	
	public Enemy(double x, double y, int width, int height, int health, ObjectManager objectManager, GameObject target) {
		super(x, y, width, height, objectManager);
		
		this.health = health;
		this.target = target;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
        g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
	}
	
	public void update()
	{
		super.update();
		
		if (health <= 0)
		{
			this.isActive = false;
		}
		
		if (trapped > 0)
		{
			trapped -= 1000/60;
			speed = 0.1;
		}
		else
		{
			speed = 1;
		}
		
		double[] vector = new double[2];
		vector[0] = target.x - x;
		vector[1] = target.y - y;
		
		vector = TempleOfTheDog.Normalize(vector[0], vector[1]);
		x = x + (vector[0] * speed);
		y = y + (vector[1] * speed);
	}
}
