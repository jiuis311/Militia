import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class HeroInterface implements ImageObserver {
	private int x;
	private int y;
	private BufferedImage heroInterface1;
    public static final int HEROWIDTH = 80;
	
	HeroInterface(int x, int y) {
		this.x = x;
		this.y = y;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			this.heroInterface1 = loader.loadImage("/short_sword.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
        
        public void setX(int x){
            this.x = x;
        }
        
        public void setY(int y){
            this.y = y;
        }
        
    public void drawHero(Graphics g, int x, int y) {
        g.drawImage(heroInterface1, (x+2)*HEROWIDTH, y*HEROWIDTH, HEROWIDTH, HEROWIDTH, (ImageObserver) this);
	}
        
        @Override
        public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
