package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIManager {
    Font Font;
    
	final int NONE = 0;
    final int BUILD = 1;
    final int UPGRADE = 2;
    public int CurrentState = NONE;
    
    public int MineCost;
    public int TowerCost;
    public int TrapCost;
    public int HealCost;
    
    public int GoldIncomeCost;
    public int HealthCost;
    
    public UIManager(int CurrentState)
    {
    	Font = new Font("Arial", Font.PLAIN, 16);
    	this.CurrentState = CurrentState;
    }
    
    public void update(Graphics g)
    {
    	// TODO Auto-generated method stub
    	if(CurrentState == BUILD){
    	    drawBuildMenu(g);
    	}else if(CurrentState == UPGRADE){
    	    drawUpgradeMenu(g);
    	}
    }
    
    private void drawBuildMenu(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TempleOfTheDog.WIDTH, 80);
		
		g.setColor(Color.BLACK);
		g.drawString("1) Mine: " + MineCost, 25, 50);
		g.drawString("2) Tower: " + TowerCost, 200, 50);
		g.drawString("3) Trap: " + TrapCost, 400, 50);
	}
    
    private void drawUpgradeMenu(Graphics g) {
    	g.setColor(Color.GRAY);
		g.fillRect(TempleOfTheDog.WIDTH - 150, 0, 150, TempleOfTheDog.HEIGHT);

		g.setColor(Color.BLACK);
		g.drawString("1) Income:", TempleOfTheDog.WIDTH - 140, 25);
		g.drawString("" + GoldIncomeCost, TempleOfTheDog.WIDTH - 100, 60);
		g.drawString("2) Max Health:", TempleOfTheDog.WIDTH - 140, 225);
		g.drawString("" + HealthCost, TempleOfTheDog.WIDTH - 100, 260);
		g.drawString("3) Heal:", TempleOfTheDog.WIDTH - 140, 425);
		g.drawString("" + HealCost, TempleOfTheDog.WIDTH - 100, 460);
	}
}
