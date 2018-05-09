import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Background extends DrawTile implements ImageObserver {

    public Background() {
        super("/menu-background1.png");
    }

    @Override
    public void draw(Graphics g) {
         g.drawImage(image, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
