import java.awt.Graphics;
import java.awt.Color;

public class Cloud
{
	private int tlX,tlY;

	public Cloud(int tlX, int tlY)
	{
		this.tlX = tlX;
		this.tlY = tlY;
	}

	public void drawCloud(Graphics g, int height)
	{
		g.setColor(Color.white);
		g.fillArc(tlX,tlY+10+height,40,40,0,360);
		g.fillArc(tlX+40,tlY+height,50,50,0,360);
		g.fillRect(tlX+20,tlY+10+height,45,40);
		g.fillArc(tlX-5,tlY-5+height,45,45,0,360);
		g.fillArc(tlX+15,tlY-15+height,45,45,0,360);
	}
}