package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int CurrentState = MENU;

    Font titleFont;
    Font subtitleFont;
    
    ObjectManager objectManager;
    UIManager uiManager;
    
    Player player;
    GoodDog goodDog;
    
    Timer frameDraw;
    
    int gold;
    int dogFood;
    
    int MineCostBase = 100;
    int MineCostLevel = 0;
    int TowerCostBase = 200;
    int TowerCostLevel = 0;
    int TrapCost = 150;
    
    int GoldIncomeCostBase = 100;
    int GoldIncomeLevel = 0;
    int HealthCostBase = 50;
    int HealthLevel = 5;
    int HealCost = 500;
    
	float goldCountdownMax;
	float goldCountdown;
    int goldIncome;
    
    boolean waveStart;
    int waveNum;
    int spawnedEnemies;
    int totalEnemies;
    int spawnCountdownMax = 2500;
    int spawnCountdown;
    int waveDowntimeCountdownMax = 35000;
    int waveDowntimeCountdown;
    
	public GamePanel()
	{
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	subtitleFont = new Font("Arial", Font.PLAIN, 24);
    	
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
    	
    	startGame();
	}
	
	@Override
	public void paintComponent(Graphics g){
		if (CurrentState == MENU)
		{
		    drawMenuState(g);
		}
		else if(CurrentState == GAME)
		{
		    drawGameState(g);
		}
		else if(CurrentState == END)
		{
		    drawEndState(g);
		}
		uiManager.update(g);
	}

	private void drawMenuState(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TempleOfTheDog.WIDTH, TempleOfTheDog.HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("Temple Of The Dog", 75, 250);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 175, 550);
	}

	private void drawGameState(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, TempleOfTheDog.WIDTH, TempleOfTheDog.HEIGHT);

		objectManager.draw(g);
		
		g.setColor(Color.BLACK);
		g.setFont(subtitleFont);
		g.drawString("GOLD: " + gold, 10, TempleOfTheDog.HEIGHT - 50);
		g.drawString("DOGFOOD: " + dogFood, 10, TempleOfTheDog.HEIGHT - 75);
		if (waveStart)
		{
			g.drawString("WAVE: " + waveNum, 10, TempleOfTheDog.HEIGHT - 25);
		}
		else
		{
			g.drawString("NEXT WAVE IN: " + (int)(waveDowntimeCountdown / 1000), 10, TempleOfTheDog.HEIGHT - 25);
		}
	}

	private void drawEndState(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, TempleOfTheDog.WIDTH, TempleOfTheDog.HEIGHT);

		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 150, 250);
	
		g.setFont(subtitleFont);
		g.drawString("You made it to wave: " + waveNum, 175, 300);
		g.drawString("Press ENTER to continue", 165, 550);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(CurrentState == MENU){
		    updateMenuState();
		}else if(CurrentState == GAME){
		    updateGameState();
		}else if(CurrentState == END){
		    updateEndState();
		}
		repaint();
	}

	private void updateMenuState() {
		// TODO Auto-generated method stub
		
	}

	private void updateGameState() {
		// TODO Auto-generated method stub
    	uiManager.GoldIncomeCost = (int) (GoldIncomeCostBase * Math.pow(2, GoldIncomeLevel));
    	uiManager.HealthCost = (int) (HealthCostBase * Math.pow(2, HealthLevel - 5));
    	uiManager.MineCost = MineCostBase * (MineCostLevel + 1);
    	uiManager.TowerCost = TowerCostBase * (TowerCostLevel + 1);
    	
		goldCountdown -= 1000/60;
		if (goldCountdown <= 0)
		{
			gold += goldIncome;
			goldCountdown = goldCountdownMax;
		}
		
		if (waveStart)
		{
			if (spawnedEnemies < totalEnemies)
			{
				spawnCountdown -= 1000/60;
				if (spawnCountdown <= 0)
				{
					objectManager.spawnEnemy(waveNum);
					spawnCountdown = spawnCountdownMax;
					spawnedEnemies ++;
				}
			}
			else if (objectManager.enemies.size() == 0)
			{
				waveDowntimeCountdown = waveDowntimeCountdownMax;
				waveStart = false;
			}
		}
		else
		{
			waveDowntimeCountdown -= 1000/60;
			if (waveDowntimeCountdown <= 0)
			{
				waveDowntimeCountdownMax = 20000;
				waveStart = true;
				waveNum ++;
				spawnedEnemies = 0;
				totalEnemies = waveNum * 4;
				spawnCountdownMax = getSpawnCooldown(waveNum);
				spawnCountdown = 0;
			}
		}
		
		objectManager.update();
		
		if (goodDog.health <= 0)
		{
			CurrentState = END;
		    uiManager.CurrentState = uiManager.NONE;
		}
	}
	
	public void startGame()
	{
		objectManager = new ObjectManager(this);
    	uiManager = new UIManager(0);
    	
    	player = new Player(300,300, 35, 35, objectManager);
		goodDog = new GoodDog(300, 150, 40, 40, objectManager, 5);

    	uiManager.TrapCost = TrapCost;
    	uiManager.HealCost = HealCost;
    	
    	frameDraw.restart();
    	
    	waveDowntimeCountdownMax = 35000;
    	
    	gold = 100;
    	dogFood = 0;
    	goldCountdownMax = 2500;
    	goldIncome = 0;
        HealthLevel = 5;
        GoldIncomeLevel = 0;
        MineCostLevel = 0;
        TowerCostLevel = 0;
    	
    	waveStart = false;
    	waveNum = 0;
		waveDowntimeCountdown = waveDowntimeCountdownMax;
	}

	private void updateEndState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (CurrentState == END) {
		        CurrentState = MENU;
		    } else {
		        CurrentState++;
		    }
	        if (CurrentState == GAME)
	        {
	        	startGame();
	        }
		    
		    uiManager.CurrentState = uiManager.NONE;
		}
		
		if (CurrentState == GAME)
		{
			if (e.getKeyCode()==KeyEvent.VK_UP)
			{
				player.up = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				player.down = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				player.left = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				player.right = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_E)
			{
				//double dist = Math.sqrt(Math.pow(goodDog.x - player.x, 2)   +   Math.pow(goodDog.y - player.y, 2));
				if (uiManager.CurrentState != uiManager.UPGRADE)
				{
					uiManager.CurrentState = uiManager.UPGRADE;
				}
				else
				{
					uiManager.CurrentState = uiManager.NONE;
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_Q)
			{
				if (uiManager.CurrentState != uiManager.BUILD)
				{
					uiManager.CurrentState = uiManager.BUILD;
				}
				else
				{
					uiManager.CurrentState = uiManager.NONE;
				}
			}
			
			if (uiManager.CurrentState == uiManager.BUILD)
			{
				if (e.getKeyCode() == KeyEvent.VK_1)
				{
					//build dogfood mine
					if (gold >= MineCostBase * (MineCostLevel + 1))
					{
						objectManager.Build(0, (int)player.x, (int)player.y, 50, 50);
						uiManager.CurrentState = uiManager.NONE;
						gold -= MineCostBase * (MineCostLevel + 1);
						MineCostLevel ++;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_2)
				{
					//build tower
					if (gold >= TowerCostBase * (TowerCostLevel + 1))
					{
						objectManager.Build(1, (int)player.x, (int)player.y, 50, 50);
						uiManager.CurrentState = uiManager.NONE;
						gold -= TowerCostBase * (TowerCostLevel + 1);
						TowerCostLevel ++;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_3)
				{
					//build trap
					if (gold >= TrapCost)
					{
						objectManager.Build(2, (int)player.x, (int)player.y, 25, 25);
						uiManager.CurrentState = uiManager.NONE;
						gold -= TrapCost;
					}
				}
			}
			
			if (uiManager.CurrentState == uiManager.UPGRADE)
			{
				if (e.getKeyCode() == KeyEvent.VK_1)
				{
					//upgrade gold income
					if (dogFood >= GoldIncomeCostBase * Math.pow(2, GoldIncomeLevel))
					{
						dogFood -= GoldIncomeCostBase * Math.pow(2, GoldIncomeLevel);
						GoldIncomeLevel ++;
						
						goldIncome = 25 * GoldIncomeLevel;
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_2)
				{
					//upgrade health
					if (dogFood >= HealthCostBase * Math.pow(2, HealthLevel - 5))
					{
						dogFood -= HealthCostBase * Math.pow(2, HealthLevel - 5);
						HealthLevel ++;
						
						goodDog.maxHealth = HealthLevel;
						goodDog.health ++;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_3)
				{
					if (dogFood >= HealCost && goodDog.health < goodDog.maxHealth)
					{
						dogFood -= HealCost;
						
						goodDog.health ++;
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			player.up = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			player.down = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			player.left = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			player.right = false;
		}
	}
	
	int getSpawnCooldown(int wave)
	{
		if (wave > 11)
		{
			wave = 11;
		}
		int y = (int) ((-140 * (wave - 1)) + 2000);
		return y;
	}
}
