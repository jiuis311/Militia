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
    private int score = Map.curScore;

    public EndMenuButton() {
        try {
            exitButton = ImageIO.read(DrawTile.class.getResourceAsStream("/exit.png"));
            exitButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/exit1.png"));
            endLogo = ImageIO.read(DrawTile.class.getResourceAsStream("/endLogo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void drawButtons(Graphics g) {
        Font fn1 = new Font("Sofia Pro Light", Font.PLAIN, 30);
        g.setFont(fn1);
        Color gameBlue = new Color(127, 191, 191);
        g.setColor(gameBlue);
        g.drawImage(endLogo, endLogoX, endLogoY, 499, 205, this);
        g.drawString("Score: " + Map.curScore, (Game.WIDTH / 2) + 245, 350);        
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
