package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	GoodDog goodDog;
	Player player;
	ArrayList<Building> buildings;
	ArrayList<Enemy> enemies;
	
	int size;
	
	public ObjectManager(GoodDog goodDog, Player player)
	{
		this.goodDog = goodDog;
		this.player = player;
		buildings = new ArrayList<Building>();
		enemies = new ArrayList<Enemy>();
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
	}
	
	public void spawnEnemy()
	{
		int random = new Random().nextInt(TempleOfTheDog.WIDTH);
		enemies.add(new Enemy(random, TempleOfTheDog.HEIGHT, 15, 15));
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
