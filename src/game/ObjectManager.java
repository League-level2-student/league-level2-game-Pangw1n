package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	GamePanel gamePanel;
	public GoodDog goodDog;
	public Player player;
	public ArrayList<Building> buildings;
	public ArrayList<Enemy> enemies;
	
	int size;
	
	public ObjectManager(GamePanel gamePanel)
	{
		buildings = new ArrayList<Building>();
		enemies = new ArrayList<Enemy>();
		this.gamePanel = gamePanel;
	}
	
	public void draw(Graphics g)
	{
		for (Building b : buildings)
		{
			b.draw(g);
		}
		for (Enemy e : enemies)
		{
			e.draw(g);
		}
		
		goodDog.draw(g);
		player.draw(g);
	}
	
	public void update()
	{
		purgeObjects();
		
		goodDog = gamePanel.goodDog;
		player = gamePanel.player;
	
		player.update();
		
		goodDog.update();
		
		for (Building b : buildings)
		{
			b.update();
			b.isFiring = false;
			b.target = null;
			if (b.Countdown <= 0)
			{
				if (b.typeID == 0)
				{
					gamePanel.dogFood += 50 * b.Level;
					b.Countdown = b.CountdownMax;
				}
				if (b.typeID == 1)
				{
					for (int i = 0; i < enemies.size(); i++)
					{
						Enemy e = enemies.get(i);
						if (getDist(b, e) <= 100)
						{
							e.health -= 1;
							b.Countdown = b.CountdownMax;
							b.target = e;
							b.isFiring = true;
							break;
						}
					}
				}
				if (b.typeID == 2)
				{
					for (int i = 0; i < enemies.size(); i++)
					{
						Enemy e = enemies.get(i);
						if (getDist(b, e) <= 20)
						{
							e.trapped = 5000;
							b.Countdown = b.CountdownMax;
							break;
						}
					}
				}
			}
		}
		for (Enemy e : enemies)
		{
			e.update();
		}
	}
	
	void purgeObjects()
	{
		for(int i = enemies.size() - 1; i >= 0; i--)
		{
			if (!enemies.get(i).isActive)
			{
				enemies.remove(i);
			}
		}
		for(int i = buildings.size() - 1; i >= 0; i--)
		{
			if (!buildings.get(i).isActive)
			{
				buildings.remove(i);
			}
		}
	}
	
	public void spawnEnemy(int wave)
	{
		int enemyHealth = (wave / 5) + 1;
		int random = new Random().nextInt(TempleOfTheDog.WIDTH);
		enemies.add(new Enemy(random, TempleOfTheDog.HEIGHT, 30, 30, enemyHealth, this, goodDog));
	}
	
	public void Build(int id, int x, int y, int width, int height)
	{
		buildings.add(new Building(x, y, width, height, this, id));
	}
	/*
	 *0 - mine
	 *1 - tower
	 *2 - trap
	 */
	
	public double getDist(GameObject pos1, GameObject pos2)
	{
		double dist;
		dist = Math.sqrt(Math.pow(pos2.x - pos1.x, 2) + Math.pow(pos2.y - pos1.y, 2));
		return dist;
	}
}
