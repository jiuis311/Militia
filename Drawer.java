import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Drawer {
	private Hero activeHero;
	private Map map;
	private DrawTile swordman;
	private DrawTile lancer;
	private DrawTile minion;
	private DrawTile bigMinion;
	private DrawTile moveArea;
	private DrawTile attackArea;
	private DrawTile shield;
	
	private int monsterTotal;
	private int heroTotal;
	private int monsterCount = 0;
	
	Drawer() {
		swordman = new DrawTile("/short_sword.png");
		lancer = new DrawTile("/spear3.png");
		minion = new DrawTile("/monster2.png");
		bigMinion = new DrawTile("/monster3.png");
		moveArea = new DrawTile("/move-tile.png");
		attackArea = new DrawTile("/attack-tile.png");
		shield = new DrawTile("/shield.png");
	}
	
	public void update(Hero activeHero, Map map) {
		this.activeHero = activeHero;
		this.map = map;
	}
	
	public void drawHero(Graphics g) {
		String heroName;
		for(Hero hero: map.heroes) {
        	heroName = hero.getClass().getSimpleName();
        	if (heroName.equals("Swordman")) {
        		swordman.setX(hero.getCurPosition().getX()+1);
        		swordman.setY(hero.getCurPosition().getY()+1);
        		swordman.draw(g);
        	} else if (heroName.equals("Lancer")) {
        		lancer.setX(hero.getCurPosition().getX()+1);
        		lancer.setY(hero.getCurPosition().getY()+1);
        		lancer.draw(g);
        	}
        }
	}

	
	public void drawMonster(Graphics g) {
		String monsterName;
		for(Monster monster: map.monsters) {
        	monsterName = monster.getClass().getSimpleName();
        	if (monsterName.equals("Minion")) {
        		minion.setX(monster.getCurPosition().getX()+1);
        		minion.setY(monster.getCurPosition().getY()+1);
        		minion.draw(g);
        	} else if (monsterName.equals("BigMinion")) {
        		bigMinion.setX(monster.getCurPosition().getX()+1);
        		bigMinion.setY(monster.getCurPosition().getY()+1);
        		bigMinion.draw(g);
        		if (((BigMinion)monster).getShield() == 1) {
        			this.shield.setX(monster.getCurPosition().getX()+1);
        			this.shield.setY(monster.getCurPosition().getY()+1);
        			this.shield.draw(g);
        		}
        	}
        }
	}
	
	public void calMonster() {
		if (map.turns == 0)Game.State = Game.STATE.ENDGAME;
                    this.heroTotal = map.heroes.size();
                    if (heroTotal == 0) Game.State = Game.STATE.ENDGAME;
		else {
			this.monsterTotal = map.monsters.size();
			Monster mons = map.monsters.get(this.monsterCount);
	        mons.move(map.heroes, map.monsters);
	        map.update(mons, Map.Event.MONSTER_MOVE, mons.getCurPosition());
	        if (map.heroes.size() == 0) Game.State = Game.STATE.ENDGAME;
	        Game.secs = 0;
	        this.monsterCount++;
		}
	}
	
	public void drawMoveArea(Graphics g) {
		if (this.activeHero != null && this.activeHero.getState() == Hero.State.SELECTING) {
			this.activeHero.calMoveArea();
			for(Position pos: activeHero.getMoveArea(this.map.heroes)) {
	    		this.moveArea.setX(pos.getX()+1);
	    		this.moveArea.setY(pos.getY()+1);
	    		this.moveArea.draw(g);
	    	}
		}
	}
	
	public void drawAttackArea(Graphics g) {
		if (this.activeHero != null && this.activeHero.getState() == Hero.State.MOVED) {
			this.activeHero.calAttackArea();
			for(Position pos: activeHero.getAttackArea()) {
	    		this.attackArea.setX(pos.getX()+1);
	    		this.attackArea.setY(pos.getY()+1);
	    		this.attackArea.draw(g);
	    	}
		}
	}
	
	public void drawScore(Graphics g) {
		Font fn1 = new Font("sofiapro-light.otf", Font.PLAIN, 40);
        g.setFont(fn1);
        g.setColor(Color.black);
        g.drawString("SCORE: " + map.curScore, (Game.WIDTH / 12) * 19, 150);
        g.drawString("TURN: " + map.turns, (Game.WIDTH / 12) * 19, 250);
	}
	
	public void draw(Graphics g) {
		if (Game.Playstate == Game.PLAYSTATE.HERO) {
            this.drawMoveArea(g);
            this.drawAttackArea(g);
        } else if (Game.Playstate == Game.PLAYSTATE.MONSTER) {
        	if (Game.secs >=1)
        		this.calMonster();
        	if (this.monsterCount >= map.monsters.size()) {
        		Game.Playstate = Game.PLAYSTATE.HERO;
            	map.setUnselectState();
            	this.monsterCount = 0;
        	}
        }
		
		this.drawHero(g);
		this.drawMonster(g);
		this.drawScore(g);
	}
}
