package main.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Game;

public class HelpMenu implements ImageObserver{
    private int moveButtonX = Game.WIDTH / 2 - 200;
    private int moveButtonY = 150;
    private int attackButtonX = Game.WIDTH / 2 + 100;
    private int attackButtonY = 150;
    private int swordmanX = Game.WIDTH / 2 - 200;
    private int lancerX = Game.WIDTH / 2 + 100;
    private int archerX = Game.WIDTH / 2 + 400;
    private int heroY = 210;
    private int minionX = Game.WIDTH / 2 - 200;
    private int bigminionX = Game.WIDTH / 2 + 100;
    private int ghostX = Game.WIDTH / 2 + 400;
    private int monsterY = 270;
    private int shieldX = Game.WIDTH / 2 - 200;
    private int bombX = Game.WIDTH / 2 + 100;
    private int itemY = 330;
    private int helpLogoX = Game.WIDTH/2 + 230;
    private int helpLogoY = 30;
    private int ruleX = Game.WIDTH / 2 - 200;
    private int ruleY = 420;
    private int quitButtonX = Game.WIDTH / 2 + 700;
    private int quitButtonY = 650;
    private static final int BUTTONWIDTH = 300;
    private static final int BUTTONHEIGHT = 60;
    private BufferedImage moveButton;
    private BufferedImage attackButton;
    private BufferedImage swordman;
    private BufferedImage lancer;
    private BufferedImage archer;
    private BufferedImage minion;
    private BufferedImage bigminion;
    private BufferedImage ghost;
    private BufferedImage bomb;
    private BufferedImage shield;
    private BufferedImage rule;
    private BufferedImage helpLogo;
    private BufferedImage quitButton;
    private BufferedImage quitButton2;
    public static boolean quitButtonState = false;


    public HelpMenu() {
        try {
            moveButton = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo2.png"));
            attackButton = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo1.png"));
            helpLogo = ImageIO.read(DrawTile.class.getResourceAsStream("/help.png"));
            swordman = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo3.png"));
            lancer = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo4.png"));
            archer = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo5.png"));
            minion = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo6.png"));
            bigminion = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo7.png"));
            ghost = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo8.png"));
            shield = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo9.png"));
            bomb = ImageIO.read(DrawTile.class.getResourceAsStream("/helpinfo10.png"));
            rule = ImageIO.read(DrawTile.class.getResource("/rule.png"));
            quitButton = ImageIO.read(DrawTile.class.getResourceAsStream("/nextQuit.png"));
            quitButton2 = ImageIO.read(DrawTile.class.getResourceAsStream("/nextQuit1.png"));
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
	
	public void drawButtons(Graphics g) {
        g.drawImage(helpLogo, helpLogoX, helpLogoY, 170, 107, this);
        g.drawImage(moveButton, moveButtonX, moveButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(attackButton, attackButtonX, attackButtonY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(swordman, swordmanX, heroY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(lancer, lancerX, heroY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(archer, archerX, heroY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(minion, minionX, monsterY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(bigminion, bigminionX, monsterY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(ghost, ghostX, monsterY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(shield, shieldX, itemY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(bomb, bombX, itemY, BUTTONWIDTH, BUTTONHEIGHT, (ImageObserver) this);
        g.drawImage(rule, ruleX, ruleY, 800, 300, (ImageObserver) this);
        if (quitButtonState) {
            g.drawImage(quitButton2, quitButtonX, quitButtonY, 169, 60, (ImageObserver) this);
        } else {
            g.drawImage(quitButton, quitButtonX, quitButtonY, 169, 60, (ImageObserver) this);
        }
	}

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
