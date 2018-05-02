
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class DrawTile implements ImageObserver  {
    protected int x = 0;
    protected int y = 0;
    protected BufferedImage image;
    protected String imageLink;
    public static final int WIDTH = Config.TILEWIDTH;
    
    DrawTile(String imageLink){	
    	BufferedImageLoader loader = new BufferedImageLoader();
    	try {
            this.image = loader.loadImage(imageLink);
            //System.out.println("Call");
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
    
    public BufferedImage getSprite() {
        return image;
    }
    
    public void draw(Graphics g){
        g.drawImage(image, (this.x+2)*WIDTH, this.y*WIDTH, WIDTH, WIDTH, (ImageObserver) this);
    }

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	};
}
