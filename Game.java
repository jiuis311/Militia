import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public static final int ROWS = Config.GAME_HEIGHT;
    public static final int LINES = Config.GAME_WIDTH;
    public static boolean bracketboo = false;
    public final String TITLE = "Militia";	
    private boolean running = false;
    private Thread thread;
    private TileGrid grid;
    private Background bg = new Background();
    private MenuBackground menuBg;
    private DrawTile bracket;
    public static enum STATE{
        MENU,
        GAME
    }
    public static STATE State = STATE.MENU;
    public static enum PLAYSTATE {
    	HERO,
    	MONSTER
    }
    public static PLAYSTATE Playstate = PLAYSTATE.HERO;
    public static MenuButton menuButton;
    public MapLV1 maplv1;
    public static HeroInterface heroInterface;
    private Hero activeHero;
    private HeroAction heroAction;
    private MonsterInterface monsinter;
    private Monster activeMons;
    
    public void init() {
        grid = new TileGrid(ROWS, LINES);
        this.addMouseListener(new MouseInput());
        menuButton = new MenuButton();
        menuBg = new MenuBackground();
        maplv1 = new MapLV1();
        heroInterface = new HeroInterface();
        heroAction = new HeroAction();
        Playstate = PLAYSTATE.HERO;
        activeHero = null; 
        monsinter = new MonsterInterface();
        bracket = new DrawTile("/bracket.png");
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
	int secs = 0;
	int frames = 0;
	while (running) {
            frames++;
            if (frames % 20 == 0) {
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
            bg.draw(g);
            grid.draw(g);
            menuButton.drawMenuInGame(g);                  
            //draw monster and hero
            
            //Hero action            
            if (Game.Playstate == PLAYSTATE.HERO) {
            	heroAction.update(activeHero, g);
                heroAction.showMoveArea();
                heroAction.showAttackArea();
            }
            
            //End hero action            
            for(Hero hero: maplv1.heros) {
  	        Game.heroInterface.drawHero(g, hero.curPosition.getX()+1, hero.curPosition.getY()+1, hero.getClass().getSimpleName());
            }
         
            //Monster action
            if (Game.Playstate == PLAYSTATE.MONSTER){
                for (Monster mons: maplv1.monsters){
                    int min = 1000;
                    Position a = null;
                    System.out.println(mons.getClass().getSimpleName() + " " + mons.curPosition);
                    for (Hero hero: maplv1.heros){
                        int temppos = mons.curPosition.getDistance(hero.curPosition);
                            if (temppos < min) {
                                min = temppos;
                                a = hero.curPosition;
                            }
                        }
                    mons.move(a, maplv1.monsters);
                    maplv1.update(mons, Map.Event.MONSTER_MOVE, mons.curPosition);
                    System.out.println(mons.getClass().getSimpleName() + " " + mons.curPosition);
                    monsinter.drawMons(g, mons.curPosition.getX()+1, mons.curPosition.getY()+1, mons.getClass().getSimpleName());                  
                }
                System.out.println("Position change");
                Game.Playstate = PLAYSTATE.HERO; 
                maplv1.setUnselectState();
                
            }
            //End monster action
        
            for (Monster mons: maplv1.monsters){
                monsinter.drawMons(g, mons.curPosition.getX()+1, mons.curPosition.getY()+1, mons.getClass().getSimpleName());
            }
            ////////////////////////
            if(bracketboo){
            	bracket.draw(g);
            }
	}
        else if(State == STATE.MENU){
            menuBg.draw(g);
            menuButton.drawButtons(g);
        }			
        //////////////////////////////
	g.dispose();
	bs.show();
    }

    public static void main(String[] args) {
	Game game = new Game();
	game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	JFrame frame = new JFrame(game.TITLE);
	frame.add(game);
	game.addMouseListener(new MouseAdapter() {
	@Override 
	public void mouseClicked(MouseEvent e) {
            if (State == STATE.GAME) {
               	int x = (int)(e.getX()/80) - 2;
               	int y = (int)(e.getY()/80);
               	Hero tmpHero = game.maplv1.getHero(x-1,y-1);
               	System.out.println("Hero: " + game.activeHero);
               	if (tmpHero != null && tmpHero.getState() != Hero.State.DONE && !(game.activeHero != null && game.activeHero.getState() == Hero.State.MOVED)) {
                    System.out.println("Tmp : " + tmpHero.getState());
                    if (game.activeHero != null && game.activeHero.getState() == Hero.State.SELECTING) {
                    	game.activeHero.setState(Hero.State.UNSELECT);
                    }
                    game.activeHero = tmpHero;
                }
                if (game.activeHero != null) {
                    if (Game.Playstate == Game.PLAYSTATE.HERO) {
                    	if (game.activeHero.getState() == Hero.State.MOVED) {
                            System.out.println("MOVED STATE. Ready to attack");
                            if (game.activeHero.attack(new Position(x-1, y-1))) {
                				game.maplv1.update(game.activeHero, Map.Event.HERO_ATTACK, new Position(x-1,y-1));
                    			System.out.println("ATTACKED");
                    			game.activeHero.setState(Hero.State.DONE);
                			}
                    	} else if (game.activeHero.getState() == Hero.State.UNSELECT) {
                            System.out.println("UNSELECT STATE");
                            game.activeHero.setState(Hero.State.SELECTING);
                    	} else if (game.activeHero.getState() == Hero.State.SELECTING) {
                            System.out.println("SELECTING STATE");
                            if (game.activeHero.move(new Position(x-1, y-1))) {
                    		game.maplv1.update(game.activeHero, Map.Event.HERO_MOVE, new Position(x-1,y-1));
                    		game.activeHero.setState(Hero.State.MOVED);
                            }
                    	} else {
                            System.out.println("Done state");
                    	}                    		
                    	////
                    	if (game.maplv1.checkEndTurn())
                            Game.Playstate = PLAYSTATE.MONSTER;
                    }
                }				
		
                //Get click position (not important)
                if (1<=x && 8>=x && 1<=y && 8>=y){
                    bracketboo = true;
                    game.bracket.setX(x);
                    game.bracket.setY(y);
                }
                else {
                    bracketboo = false;
                }
            }
	}
    });
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
