package game;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	
	public GameObject(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void move(int x, int y)
	{
		this.x += x;
		this.y += y;
	}
	
	public void update()
	{
		
	}
}
