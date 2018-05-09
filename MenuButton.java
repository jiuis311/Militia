import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
        private int menuGameX = Game.WIDTH - 600;
        private int menuGameY = 0;
	private static final int BUTTONWIDTH = 274;
	private static final int BUTTONHEIGHT = 74;
	private BufferedImage startButton;
	private BufferedImage helpButton;
	private BufferedImage quitButton;
	private BufferedImage startButton2;
	private BufferedImage helpButton2;
	private BufferedImage quitButton2;
        private BufferedImage exitButton;
        private BufferedImage exitButton2;
	public static boolean startButtonState = false;
	public static boolean helpButtonState = false;
	public static boolean quitButtonState = false;
        public static boolean exitButtonState = false;

	
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
			this.exitButton = loader.loadImage("/exit-2.png");
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
                try {
			this.exitButton2 = loader.loadImage("/exit-1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
                
	}
	
	public void drawButtons(Graphics g) {
        Font fn1 = new Font("Berlin Sans FB Demi", Font.PLAIN, 150);
        g.setFont(fn1);
        g.setColor(Color.black);
        g.drawString("MILITIA", Game.WIDTH / 2, 150);
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
        
        public void drawMenuInGame(Graphics g) {
            if (exitButtonState) {
        	g.drawImage(exitButton2, menuGameX, menuGameY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(exitButton, menuGameX, menuGameY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }
        }
	
        @Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
