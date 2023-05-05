package game;

import processing.core.PApplet;

public class GameRunner extends PApplet{
	public static void main(String[] args) {
		PApplet.main(GameRunner.class.getName());
	}
	
	Dog goodDog;
	
	Dog target;
	
	@Override
	public void setup()
	{
		goodDog = new Dog(400, 400, 0);
	}
	
    @Override
    public void settings() {
        setSize(800, 800);
    }
    
    @Override
    public void draw()
    {
    	background(0);
    	drawDog(goodDog);
    }
    
	@Override
	public void keyPressed()
	{
		
	}
	
	@Override
	public void mouseClicked() 
	{
		if (Math.abs(goodDog.x - mouseX) < 8 && Math.abs(goodDog.y - mouseY) < 8)
		{
			target = goodDog;
		}
	}
	
	public void drawDog(Dog drawdog)
	{
		if (drawdog.type == 0)
		{
			fill(0, 255, 255);
		}
		rect(drawdog.x - 8, drawdog.y - 8, 16, 16);
	}
}
