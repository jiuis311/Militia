package main.graphics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import main.Game;

public class MenuBackground extends DrawTile implements ImageObserver {

    public MenuBackground() {
        super("/menu-background1.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
