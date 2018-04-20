import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Background extends DrawInterface implements ImageObserver {
    Background(){
        BufferedImageLoader loader = new BufferedImageLoader();
	try {
            this.image = loader.loadImage("/game-background.png");
	} catch (IOException e) {
            e.printStackTrace();
	}
    }

    @Override
    void draw(Graphics g) {
         g.drawImage(image, 0, 0, Game.WIDTH*Game.SCALE+200, Game.HEIGHT*Game.SCALE, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}