package main.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import main.helpers.Config;

public class TileGrid implements ImageObserver {
    private static final int TILEWIDTH = Config.TILEWIDTH;
    private DrawTile tile;

    public TileGrid(int x, int y) {
    	tile = new DrawTile("/ground-tile.png");
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void draw(Graphics g) {
        for(int i = 0; i < Config.GAME_HEIGHT; i++) 
        	for(int j = 0; j < Config.GAME_WIDTH; j++) {
        		tile.setX(i+1);
        		tile.setY(j+1);
        		tile.draw(g);
        	}
    }
}
