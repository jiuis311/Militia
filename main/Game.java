package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import main.entities.characters.heroes.Hero;
import main.graphics.Background;
import main.graphics.DrawTile;
import main.graphics.Drawer;
import main.graphics.EndMenuButton;
import main.graphics.MenuBackground;
import main.graphics.MenuButton;
import main.graphics.MouseInput;
import main.graphics.NextLevelMenu;
import main.graphics.TileGrid;
import main.graphics.VictoryMenu;
import main.helpers.Config;
import main.maps.Map;
import main.maps.MapLV1;
import main.maps.MapLV2;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public static final int ROWS = Config.GAME_HEIGHT;
    public static final int LINES = Config.GAME_WIDTH;
    public static int secs = 0;
    public static boolean bracketboo = false;
    public final String TITLE = "Militia";	
    private boolean running = false;
    private Thread thread;
    private TileGrid grid;
    private Background bg = new Background();
    private MenuBackground menuBg;
    private DrawTile bracket;
    private int currentLv;
    public static enum STATE{
        MENU,
        GAME,
        NEXT,
        LVUP,
        VICTORY,
        ENDGAME
    }
    public static STATE State = STATE.MENU;
    public static enum PLAYSTATE {
    	HERO,
    	MONSTER
    }
    public static PLAYSTATE Playstate = PLAYSTATE.HERO;
    public static MenuButton menuButton;
    public static EndMenuButton endMenuButton;
    public static NextLevelMenu nextLevelMenu;
    public static VictoryMenu victoryMenu;
    public Map map;
    private Hero activeHero;
    private Drawer drawer;
    
    public void init() {
        grid = new TileGrid(ROWS, LINES);
        this.addMouseListener(new MouseInput());
        menuButton = new MenuButton();
        endMenuButton = new EndMenuButton();
        nextLevelMenu = new NextLevelMenu();
        victoryMenu = new VictoryMenu();
        menuBg = new MenuBackground();
        currentLv = 1;
        map = new MapLV1();
        Playstate = PLAYSTATE.HERO;
        activeHero = null; 
        bracket = new DrawTile("/bracket.png");
        drawer = new Drawer();
    }
	
    private synchronized void start() {
        if (running) {
            return;
        }		
        running = true;
        thread = new Thread(this);
        thread.start();
    }
	
    private synchronized void stop() throws InterruptedException {
        if (!running) {
       return;
        }		
        running = false;
        thread.join();
        System.exit(1);
    }

    @Override
    public void run() {
        init();
        int frames = 0;
        while (running) {
            frames++;
            if (frames % 5 == 0) {
                this.secs++;
            }
            render();                         
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
    public void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if (bs == null) {
            createBufferStrategy(3);
            return;
	}		
	Graphics g = bs.getDrawGraphics();
        
	//////////////////////////////
	if (State == STATE.GAME){
            //Draw game background
            bg.draw(g);
            grid.draw(g);
            menuButton.drawMenuInGame(g);               
            //draw monster and hero         
            drawer.update(activeHero, map, this.currentLv);
            if (Game.Playstate == Game.PLAYSTATE.MONSTER) {
            	this.setActiveHero(null);
            }
            drawer.draw(g);        
            if(bracketboo){
            	bracket.draw(g);
            }
	} else if(State == STATE.MENU){
            menuBg.draw(g);
            menuButton.drawButtons(g);
            //this.map.setCurScore(0);
            this.setNewMap();
            System.out.println(this.map.getCurScore());
	} else if (State == STATE.LVUP) {
            System.out.println(currentLv);
            State = STATE.GAME;
            Game.Playstate = Game.PLAYSTATE.HERO;   
            levelUp();
        } else if (State == STATE.ENDGAME) {
            menuBg.draw(g);
            endMenuButton.drawButtons(g);
            Game.Playstate = Game.PLAYSTATE.HERO;
            //this.map.setCurScore(0);
            this.setNewMap();
            System.out.println(this.map.getCurScore());
        } else if(State == STATE.NEXT){
            menuBg.draw(g);
            nextLevelMenu.drawButtons(g);
            if (currentLv >= Config.MAXLEVEL) {
                    State = STATE.VICTORY;
            }
        }
        else if (State == STATE.VICTORY){
            menuBg.draw(g);
            victoryMenu.drawButtons(g);
        }
        //////////////////////////////
	g.dispose();
	bs.show();
    }
    
    public Hero getActiveHero() {
    	return this.activeHero;
    }
    
    public void setActiveHero(Hero activeHero) {
    	this.activeHero = activeHero;
    }
    
    public DrawTile getBracket() {
    	return this.bracket;
    }
    
    public boolean getBracketBoolean() {
    	return this.bracketboo;
    }
    
    public void setBracketBoolean(Boolean bracketboo) {
    	this.bracketboo = bracketboo;
    }
    
    public Map getMap() {
    	return this.map;
    }
    
    public void setNewMap() {
    	this.map = new MapLV1();
    	this.currentLv = 1;
    }
    
    public void levelUp() {
    		switch (currentLv) {
    		case 1:
    			this.map = new MapLV2();
    			this.currentLv++;
    			break;
    		default:
    			break;
    		}
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.addMouseListener(new GameMouseListener(game));
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        // Start game
        game.start();
    }
}
