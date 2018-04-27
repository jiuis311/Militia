import java.awt.Graphics;

public class Drawer {
	private Hero activeHero;
	private MapLV1 maplv1;
	private DrawTile sword;
	private DrawTile spear;
	private DrawTile minion;
	private DrawTile bigMinion;
	private DrawTile moveArea;
	private DrawTile attackArea;
	private DrawTile shield;
	
	Drawer() {
		sword = new DrawTile("/short_sword.png");
		spear = new DrawTile("/spear3.png");
		minion = new DrawTile("/monster2.png");
		bigMinion = new DrawTile("/monster3.png");
		moveArea = new DrawTile("/move-tile.png");
		attackArea = new DrawTile("/attack-tile.png");
		shield = new DrawTile("/shield.png");
	}
	
	public void update(Hero activeHero, MapLV1 maplv1) {
		this.activeHero = activeHero;
		this.maplv1 = maplv1;
	}
	
	public void drawHero(Graphics g) {
		String heroName;
		for(Hero hero: maplv1.heros) {
        	heroName = hero.getClass().getSimpleName();
        	if (heroName.equals("Sword")) {
        		sword.setX(hero.getCurPosition().getX()+1);
        		sword.setY(hero.getCurPosition().getY()+1);
        		sword.draw(g);
        	} else if (heroName.equals("Spear")) {
        		spear.setX(hero.getCurPosition().getX()+1);
        		spear.setY(hero.getCurPosition().getY()+1);
        		spear.draw(g);
        	}
        }
	}

	
	public void drawMonster(Graphics g) {
		String monsterName;
		for(Monster monster: maplv1.monsters) {
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
		for (Monster mons: maplv1.monsters){                   
            mons.move(maplv1.heros, maplv1.monsters);
            maplv1.update(mons, Map.Event.MONSTER_MOVE, mons.getCurPosition());
            System.out.println(mons.getClass().getSimpleName() + " " + mons.getCurPosition());
        }
        System.out.println("Position change");
        maplv1.setUnselectState();
	}
	
	public void drawMoveArea(Graphics g) {
		if (this.activeHero != null && this.activeHero.getState() == Hero.State.SELECTING) {
			this.activeHero.calMoveArea();
			for(Position pos: activeHero.getMoveArea()) {
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
	
	public void draw(Graphics g) {
		if (Game.Playstate == Game.PLAYSTATE.HERO) {
            this.drawMoveArea(g);
            this.drawAttackArea(g);
        } else if (Game.Playstate == Game.PLAYSTATE.MONSTER) {
        	this.calMonster();
        	Game.Playstate = Game.PLAYSTATE.HERO;
        }
		
		this.drawHero(g);
		this.drawMonster(g);
	}
}
