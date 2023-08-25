package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	GamePanel gamePanel;
	GoodDog goodDog;
	Player player;
	ArrayList<Building> buildings;
	ArrayList<Enemy> enemies;
	
	int size;
	
	public ObjectManager(GoodDog goodDog, Player player, GamePanel gamePanel)
	{
		this.goodDog = goodDog;
		this.player = player;
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
		player.update();
		
		for (Building b : buildings)
		{
			b.update();
			if (b.Countdown <= 0)
			{
				if (b.typeID == 0)
				{
					gamePanel.dogFood += 50 * b.Level;
				}
				b.Countdown = b.CountdownMax;
			}
		}
		for (Enemy e : enemies)
		{
			e.update();
		}
	}
	
	public void spawnEnemy()
	{
		int random = new Random().nextInt(TempleOfTheDog.WIDTH);
		enemies.add(new Enemy(random, TempleOfTheDog.HEIGHT, 15, 15, goodDog));
	}
	
	public void Build(int id, int x, int y, int width, int height)
	{
		buildings.add(new Building(x, y, width, height, id));
	}
	/*
	 *0 - mine
	 *1 - tower
	 *2 - trap
	 */
}
