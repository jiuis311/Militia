import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class MenuButton implements ImageObserver {
	private int startButtonX = Game.WIDTH / 2 + 120;
	private int startButtonY = 200;
	private int helpButtonX = Game.WIDTH / 2 + 120;
	private int helpButtonY = 300;
	private int quitButtonX = Game.WIDTH / 2 + 120;
	private int quitButtonY = 400;
	private static final int BUTTONWIDTH = 274;
	private static final int BUTTONHEIGHT = 74;
	private BufferedImage startButton;
	private BufferedImage helpButton;
	private BufferedImage quitButton;
	private BufferedImage startButton2;
	private BufferedImage helpButton2;
	private BufferedImage quitButton2;
	public static boolean startButtonState = false;
	public static boolean helpButtonState = false;
	public static boolean quitButtonState = false;

	
	MenuButton() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			this.startButton = loader.loadImage("/start-2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.helpButton = loader.loadImage("/help-2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.quitButton = loader.loadImage("/exit-2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.startButton2 = loader.loadImage("/start-1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.helpButton2 = loader.loadImage("/help-1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.quitButton2 = loader.loadImage("/exit-1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawButtons(Graphics g) {
        Font fn1 = new Font("arial", Font.BOLD, 100);
        g.setFont(fn1);
        g.setColor(Color.black);
        g.drawString("MILITIA", Game.WIDTH / 2 + 50, 150);
//        System.out.println(startButtonState);
        if (startButtonState) {
        	g.drawImage(startButton2, startButtonX, startButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(startButton, startButtonX, startButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }
        
        if (helpButtonState) {
        	g.drawImage(helpButton2, helpButtonX, helpButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(helpButton, helpButtonX, helpButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }
        
        if (quitButtonState) {
        	g.drawImage(quitButton2, quitButtonX, quitButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(quitButton, quitButtonX, quitButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }

	}
	
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
