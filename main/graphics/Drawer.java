package main.graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import main.entities.characters.heroes.Hero;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Monster;
import main.helpers.Position;
import main.maps.Map;

public class Drawer {
	private Hero activeHero;
	private Map map;
	private final DrawTile swordman;
	private final DrawTile lancer;
	private final DrawTile minion;
	private final DrawTile bigMinion;
	private final DrawTile moveArea;
	private final DrawTile attackArea;
	private final DrawTile shield;
        private final DrawTile ghost;
        private final DrawTile archer;
        private final DrawTile star;
	
	private int monsterTotal;
	private int heroTotal;
	private int monsterCount = 0;
	
	public Drawer() {
		swordman = new DrawTile("/sword.png");
		lancer = new DrawTile("/spear.png");
		minion = new DrawTile("/mushroom.png");
		bigMinion = new DrawTile("/alien.png");
		moveArea = new DrawTile("/move-tile.png");
		attackArea = new DrawTile("/attack-tile.png");
		shield = new DrawTile("/shield.png");
                ghost = new DrawTile("/ghost.png");
                archer = new DrawTile("/bow.png");
                star = new DrawTile("/star.png");
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
                    if (monster.isTargeted()){
                        this.star.setX(monster.getCurPosition().getX()+1);
                        this.star.setY(monster.getCurPosition().getY()+1);
        		this.star.draw(g);
                    }
                }
	}
	
	public void calMonster() {
        if (map.getTurns() == 0)Game.State = Game.STATE.ENDGAME;
            this.heroTotal = map.heroes.size();
            if (heroTotal == 0) Game.State = Game.STATE.ENDGAME;
        else {
            if(!map.monsters.isEmpty()) {
                this.monsterTotal = map.monsters.size();
                Monster mons = map.monsters.get(this.monsterCount);
                mons.move(map.heroes, map.monsters);
                map.update(mons, Map.Event.MONSTER_MOVE, mons.getCurPosition());
                if (map.heroes.size() == 0) Game.State = Game.STATE.ENDGAME;
                Game.secs = 0;
                this.monsterCount++;
            }
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
		Font fn1 = new Font("Sofia Pro Light", Font.PLAIN, 30);
        g.setFont(fn1);
        Color gameBlue = new Color(127, 191, 191);
        g.setColor(gameBlue);
        g.drawString("Score: " + map.getCurScore(), (Game.WIDTH / 12) * 21, 100);
        g.drawString("Turn: " + map.getTurns(), (Game.WIDTH / 12) * 21, 150);
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
