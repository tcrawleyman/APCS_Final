import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Line2D.Double;

public class GameFrame
{
    public static void main(String[] args)
    {
        Frame frame = new Mechanics();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	System.exit(0);
            }
        });
    }
}

class Mechanics extends Frame implements MouseListener, MouseMotionListener
{
	protected int mouseX, mouseY;
	protected int appletWidth, appletHeight,elevation;
	protected int height;
	protected static double velocity;
	protected boolean gravity,above;
	protected int platformX,platformY,prevX,prevY,prevPlatY;
	protected Image virtualMem;
	protected Image sprite;
	protected Graphics gBuffer;
	protected static ArrayList<Platform> platforms;
	protected Random generate;

	public Mechanics()
	{
		super("To the Clouds");
		setSize(1010,900);
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);
//		sprite = getImage("sprite.jpeg");
		GameGraphics.init();
		velocity = 25;
		mouseX = 0;
		mouseY = 725;
		prevX = 0;
		prevY = 0;
		height = 0;
		elevation = 0;
		gravity = true;
		above = false;
		generate = new Random();

		try
		{
			appletWidth = getWidth();
			appletHeight = getHeight();
			virtualMem = new BufferedImage(appletWidth,appletHeight,BufferedImage.TYPE_INT_RGB);
			gBuffer = virtualMem.getGraphics();
		}
		catch(NullPointerException e) {}

		Graphics2D g2d = (Graphics2D) gBuffer;
		g2d.translate(8,30);

		int platformX = 0;
		int platformY = 0;
		int prevY = 0;
		platforms = new ArrayList<Platform>();
		for(int c = 0; c < 100; c++)
		{
			platformX = generate.nextInt(900);
			platformY = generate.nextInt(500)-c*250;
			Platform platform = new Platform(platformX,platformY);
			prevPlatY = platformY;
			platforms.add(platform);
		}
	}

	public void paint(Graphics g)
	{
		GameGraphics.background(gBuffer,height);
//		GameGraphics.title(gBuffer);
		GameGraphics.sprite(gBuffer,mouseX,elevation);
		drawPlatforms(gBuffer,height);
		checkBounce(mouseX,elevation,prevX,prevY);
		setVelocity();
		moveScreen();
		g.drawImage(virtualMem,0,0,null);
		delay(33);
		repaint();
	}

	public void update(Graphics g)	{ paint(g); }

	public void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();
	}

	public void setVelocity()
	{
		prevY = elevation+2;
		elevation += velocity;

		if(velocity > -24.5)			//24.5
			velocity -= .5;
		else
			velocity = 25.0;
	}

	public boolean getGravity()		{ return gravity; }

	public void drawPlatforms(Graphics g, int height)
	{
		try
		{
			for(int c = 0; c < platforms.size(); c++)
				platforms.get(c).drawPlatform(gBuffer,height);
		}
		catch (NullPointerException e) {}
	}

	public void checkBounce(int mouseX,double elevation,int prevX,double prevY)
	{
		try
		{
			for(int c = 0; c < platforms.size(); c++)
			{
				if(checkPath(platforms.get(c),mouseX,elevation,prevX,prevY))
				{
					elevation = 700-platforms.get(c).tlY-48;
					velocity = 25.0;
				}
			}
		}
		catch (NullPointerException e) {}
	}

	public boolean checkPath(Platform plat, int mouseX, double elevation, int prevX, double prevY)
	{
		Double line = new Double(mouseX,elevation,prevX,prevY);
		return line.intersects(plat.rect);
	}

	public void moveScreen()
	{
		if(elevation>height+750)
		{
			height+=20*velocity;
//			above = true;
		}
//		if(elevation-750<height)
//		above = false;
		if(elevation<height-950)
			System.out.println("You lose");
	}

	public void mouseEntered(MouseEvent e)  {}
	public void mouseExited(MouseEvent e)	{}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e)  {}
	public void mouseClicked(MouseEvent e)  { prevX = mouseX; mouseX = e.getX(); }
	public void mouseDragged(MouseEvent e)	{ prevX = mouseX; mouseX = e.getX(); }
	public void mouseMoved(MouseEvent e)	{ prevX = mouseX; mouseX = e.getX(); }
}