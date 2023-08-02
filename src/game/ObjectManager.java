package game;

import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Player player;
	ArrayList<Building> buildings;
	ArrayList<Enemy> enemies;
	
	int score = 0;
	
	public ObjectManager(Player player)
	{
		this.player = player;
		buildings = new ArrayList<Building>();
		enemies = new ArrayList<Enemy>();
	}
	
	public void update()
	{
		
	}
	
	public void spawnEnemy()
	{
		int random = new Random().nextInt(TempleOfTheDog.WIDTH);
		enemies.add(new Enemy(TempleOfTheDog.HEIGHT, random, 15, 15));
	}
}
