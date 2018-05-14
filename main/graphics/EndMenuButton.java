package main.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Game;

public class EndMenuButton implements ImageObserver{
    private int reStartButtonX = Game.WIDTH / 2 + 210;
    private int reStartButtonY = 350;
    private int exitButtonX = Game.WIDTH / 2 + 210;
    private int exitButtonY = 450;
    private int endLogoX = Game.WIDTH/2 + 60;
    private int endLogoY = 100;
    private static final int BUTTONWIDTH = 250;
    private static final int BUTTONHEIGHT = 107;
    private BufferedImage reStartButton;
    private BufferedImage exitButton;
    private BufferedImage reStartButton2;
    private BufferedImage exitButton2;
    private BufferedImage endLogo;
    public static boolean reStartButtonState = false;
    public static boolean exitButtonState = false;
    private int score = 0;


    public EndMenuButton() {
        try {
            reStartButton = ImageIO.read(DrawTile.class.getResourceAsStream("/replay.png"));
            exitButton = ImageIO.read(DrawTile.class.getResourceAsStream("/exit.png"));
            reStartButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/replay1.png"));
            exitButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/exit1.png"));
            endLogo = ImageIO.read(DrawTile.class.getResourceAsStream("/endLogo.png"));
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    public void updateScore(int score) {
    	this.score = score ;
    }
    
    public void drawScore(Graphics g) {
    	Font fn1 = new Font("Sofia Pro Light", Font.PLAIN, 50);
        g.setFont(fn1);
        Color gameBlue = new Color(127, 191, 191);
        g.setColor(gameBlue);
        g.drawString("Score: " + this.score, (Game.WIDTH / 12) * 19, 150);    
    }
	
	
	public void drawButtons(Graphics g) {
        g.drawImage(endLogo, endLogoX, endLogoY, 499, 205, this);
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
        drawScore(g);
	}

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
