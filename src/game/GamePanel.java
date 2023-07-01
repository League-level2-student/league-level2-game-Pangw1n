package game;

import java.awt.Color;
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
    
    Player player;
    
    Timer frameDraw;
    
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    
	public GamePanel()
	{
		player = new Player(300, 300, 25, 25);
		
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
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
	}

	private void drawGameState(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, TempleOfTheDog.WIDTH, TempleOfTheDog.HEIGHT);
		
		player.draw(g);
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
