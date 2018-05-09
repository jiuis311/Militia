
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class EndMenuButton implements ImageObserver{
        private int reStartButtonX = Game.WIDTH / 2 + 120;
	private int reStartButtonY = 200;
	private int exitButtonX = Game.WIDTH / 2 + 120;
	private int exitButtonY = 300;
        private int menuGameX = Game.WIDTH - 600;
        private int menuGameY = 0;
	private static final int BUTTONWIDTH = 274;
	private static final int BUTTONHEIGHT = 74;
	private BufferedImage reStartButton;
	private BufferedImage exitButton;
	private BufferedImage reStartButton2;
	private BufferedImage exitButton2;
	public static boolean reStartButtonState = false;
	public static boolean exitButtonState = false;

	
	EndMenuButton() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			this.reStartButton = loader.loadImage("/start-2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
                try {
			this.exitButton = loader.loadImage("/exit-2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			this.reStartButton2 = loader.loadImage("/start-1.png");
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
        Font fn1 = new Font("Berlin Sans FB Demi", Font.PLAIN, 120);
        g.setFont(fn1);
        g.setColor(Color.black);
        g.drawString("GAME OVER", Game.WIDTH / 2 - 60, 150);
//        System.out.println(startButtonState);
        if (reStartButtonState) {
        	g.drawImage(reStartButton2, reStartButtonX, reStartButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(reStartButton, reStartButtonX, reStartButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }
        
        if (exitButtonState) {
        	g.drawImage(exitButton2, exitButtonX, exitButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(exitButton, exitButtonX, exitButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }
	}

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
