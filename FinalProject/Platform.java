import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

class Platform
{
	int tlX,tlY;
	Rectangle rect;

	public Platform(int tlX, int tlY)
	{
		this.tlX = tlX;
		this.tlY = tlY;
		rect = new Rectangle(0,0,1,1);
	}

	public void drawPlatform(Graphics g, int height)
	{
		g.setColor(Color.orange);
		g.fillArc(tlX,tlY+75+height,6,25,0,360);
		g.fillRect(tlX+3,tlY+75+height,100,25);
		g.fillArc(tlX+100,tlY+75+height,6,25,0,360);
		setRect(height);
	}
	
	public void setRect(int height)	{ rect = new Rectangle(tlX-48,700-tlY-48-height,154,1); }
	public void move(int mod) {}
}