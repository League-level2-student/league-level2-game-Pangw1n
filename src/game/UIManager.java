package game;

import java.awt.Color;
import java.awt.Graphics;

public class UIManager {
	final int NONE = 0;
    final int BUILD = 1;
    final int UPGRADE = 2;
    public int CurrentState = NONE;
    
    public UIManager(int CurrentState)
    {
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
		g.drawRect(0, 0, TempleOfTheDog.WIDTH, 80);
	}
    
    private void drawUpgradeMenu(Graphics g) {
    	g.setColor(Color.GRAY);
		g.drawRect(TempleOfTheDog.WIDTH - 150, 0, 150, TempleOfTheDog.HEIGHT);
	}
}
