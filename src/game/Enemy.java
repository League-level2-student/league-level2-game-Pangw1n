package game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{
	GameObject target;
	int speed = 2;
	public Enemy(int x, int y, int width, int height, GameObject target) {
		super(x, y, width, height);
		
		this.target = target;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
        g.fillRect(x - width / 2, y - height / 2, width, height);
	}
	
	public void update()
	{
		double[] vector = new double[2];
		vector[0] = target.x - x;
		vector[1] = target.y - y;
		
		vector = TempleOfTheDog.Normalize(vector[0], vector[1]);
		x = (int) (x + (vector[0] * speed));
		y = (int) (y + (vector[1] * speed));
	}
}
