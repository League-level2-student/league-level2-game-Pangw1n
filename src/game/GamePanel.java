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
    
    Player player;
    GoodDog goodDog;
    
    Timer frameDraw;
    
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    
    int gold;
    int dogFood;
    
	float goldCountdownMax;
	float goldCountdown;
    int goldIncome;
    int dogFoodIncome;
    
	public GamePanel()
	{
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	subtitleFont = new Font("Arial", Font.PLAIN, 24);
    	
    	player = new Player(300, 400, 25, 25);
		goodDog = new GoodDog(300, 300, 25, 25);
		
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
    	
    	gold = 100;
    	dogFood = 100;
    	goldCountdownMax = 5000;
    	goldIncome = 100;
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

		goodDog.draw(g);
		player.draw(g);
		
		g.setColor(Color.BLACK);
		g.setFont(subtitleFont);
		g.drawString("GOLD: " + gold, 10, 25);
		g.drawString("DOGFOOD: " + dogFood, 10, 50);
	}

	private void drawEndState(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, TempleOfTheDog.WIDTH, TempleOfTheDog.HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(CurrentState == MENU){
		    updateMenuState();
		}else if(CurrentState == GAME){
		    updateGameState();
		    movePlayer();
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
		goldCountdown -= 1000/60;
		if (goldCountdown <= 0)
		{
			gold += goldIncome;
			goldCountdown = goldCountdownMax;
		}
	}

	private void updateEndState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void movePlayer()
	{
		if (up)
		{
			player.move(0, -5);
		}
		if (down)
		{
			player.move(0, 5);
		}
		if (left)
		{
			player.move(-5, 0);
		}
		if (right)
		{
			player.move(5, 0);
		}
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
		}
		
		if (CurrentState == GAME)
		{
			if (e.getKeyCode()==KeyEvent.VK_UP)
			{
				up = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				down = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				left = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				right = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				double dist = Math.sqrt(Math.pow(goodDog.x - player.x, 2)   +   Math.pow(goodDog.y - player.y, 2));
				if (dist < 50)
				{
					//OpenMenu();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP)
		{
			up = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			down = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			left = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			right = false;
		}
	}
}
