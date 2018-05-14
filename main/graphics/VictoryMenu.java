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
import main.maps.Map;

public class VictoryMenu implements ImageObserver{
    private int quitButtonX = Game.WIDTH / 2 - 250;
    private int quitButtonY = 600;
    private int vicLogoX = Game.WIDTH/2 + 60;
    private int vicLogoY = 100;
    private int trophyX = Game.WIDTH/2 + 200;
    private int trophyY = 400;
    private static final int BUTTONWIDTH = 225;
    private static final int BUTTONHEIGHT = 80;
    private BufferedImage quitButton;
    private BufferedImage quitButton2;
    private BufferedImage victory;
    private BufferedImage trophy;
    public static boolean nextButtonState = false;
    public static boolean quitButtonState = false;
    private int score = 0;
    public VictoryMenu() {
        try {
            quitButton = ImageIO.read(DrawTile.class.getResourceAsStream("/nextQuit.png"));
            quitButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/nextQuit1.png"));
            victory = ImageIO.read(DrawTile.class.getResourceAsStream("/finalVictory.png"));
            trophy = ImageIO.read(DrawTile.class.getResourceAsStream("/trophy.png"));
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
        g.drawString("Score: " + this.score, trophyX + 10, trophyY-40);
    }
	
    public void drawButtons(Graphics g) {
        g.drawImage(victory, vicLogoX, vicLogoY, 499, 205, this);
        g.drawImage(trophy, trophyX, trophyY, 200, 200, this);
        if (quitButtonState) {
            g.drawImage(quitButton2, quitButtonX, quitButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        } else {
            g.drawImage(quitButton, quitButtonX, quitButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        }
        drawScore(g);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
