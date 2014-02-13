import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class GameGraphics
{
	private static Random generate;
	private static ArrayList<Cloud> clouds;

	public static void init()
	{
		generate = new Random();

		clouds = new ArrayList<Cloud>();
		for(int c = 0; c < 100; c++)
		{
			int x = generate.nextInt(950);
			int y = generate.nextInt(23000)-22500;
			clouds.add(new Cloud(x,y));
		}
	}

	public static void title(Graphics g)
	{
		g.setColor(Color.black);
		g.drawString("Title page test",100,100);
	}

	public static void background(Graphics g, int height)
	{
		try
		{
			g.setColor(new Color(138,205,255));
			g.fillRect(0,0,1000,900);
			ground(g,height);
			for(int c = 0; c < clouds.size(); c++)
				clouds.get(c).drawCloud(g,height);
		}
		catch (NullPointerException e) {}
	}

	public static void ground(Graphics g, int height)
	{
		g.setColor(new Color(25,75,25));
		g.fillRect(0,750+height,1000,150);
	}

	public static void sprite(Graphics g, int mouseX, double elevation)
	{
		try
		{
			g.setColor(Color.red);
			g.fillArc(mouseX-24,700-(int)elevation,48,48,0,360);
		}
		catch (NullPointerException e) {}
	}
}