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

public class MenuButton implements ImageObserver {
    private int startButtonX = Game.WIDTH / 2 + 230;
    private int startButtonY = 350;
    private int helpButtonX = Game.WIDTH / 2 + 230;
    private int helpButtonY = 450;
    private int quitButtonX = Game.WIDTH / 2 + 230;
    private int quitButtonY = 550;
    private int menuGameX = Game.WIDTH - 570;
    private int menuGameY = 25;
    private int logoX = Game.WIDTH / 2 + 60;
    private int logoY = 100;
    private static final int BUTTONWIDTH = 170;
    private static final int BUTTONHEIGHT = 107;
    private static final int GAMEBUTTONWIDTH = 85;
    private static final int GAMEBUTTONHEIGHT = 53;
    private BufferedImage startButton;
    private BufferedImage helpButton;
    private BufferedImage quitButton;
    private BufferedImage startButton2;
    private BufferedImage helpButton2;
    private BufferedImage quitButton2;
    private BufferedImage exitButton;
    private BufferedImage exitButton2;
    private BufferedImage logo;
    public static boolean startButtonState = false;
    public static boolean helpButtonState = false;
    public static boolean quitButtonState = false;
    public static boolean exitButtonState = false;


    public MenuButton() {
        try {
            startButton = ImageIO.read(DrawTile.class.getResourceAsStream("/play.png"));
            helpButton = ImageIO.read(DrawTile.class.getResourceAsStream("/help.png"));
            quitButton = ImageIO.read(DrawTile.class.getResourceAsStream("/quit.png"));
            exitButton = ImageIO.read(DrawTile.class.getResourceAsStream("/exit.png"));
            startButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/play1.png"));
            helpButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/help1.png"));
            quitButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/quit1.png"));
            exitButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/quit1.png"));
            logo = ImageIO.read(DrawTile.class.getResourceAsStream("/militiaLogo.png"));
            //System.out.println("Call");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void drawButtons(Graphics g) {
//        Font fn1 = new Font("Berlin Sans FB Demi", Font.PLAIN, 150);
//        g.setFont(fn1);
//        g.setColor(Color.black);
//        g.drawString("MILITIA", Game.WIDTH / 2, 150);
//        System.out.println(startButtonState);
        g.drawImage(logo, logoX, logoY, 499, 205, this);
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
        	g.drawImage(exitButton2, menuGameX, menuGameY, GAMEBUTTONWIDTH, GAMEBUTTONHEIGHT, (ImageObserver) this);
        } else {
    		g.drawImage(exitButton, menuGameX, menuGameY, GAMEBUTTONWIDTH, GAMEBUTTONHEIGHT, (ImageObserver) this);
        }
        }
	
        @Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
