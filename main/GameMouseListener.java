package main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.entities.characters.heroes.Hero;
import main.graphics.DrawTile;
import main.helpers.Position;
import main.maps.Map;
import main.maps.Map.Event;

public class GameMouseListener extends MouseAdapter {
	
	private Game game;
	private Hero activeHero;
	private DrawTile bracket;
	private boolean bracketboo;
	private Map map;
	
	GameMouseListener(Game game) {
		super();
		this.game = game;
	}
	
	@Override 
	public void mouseClicked(MouseEvent e) {
			this.bracket = game.getBracket();
			this.activeHero = game.getActiveHero();
			this.map = game.getMap();
			this.bracketboo = game.getBracketBoolean();
            if (Game.State == Game.STATE.GAME) {
               	int x = (int)(e.getX()/80) - 2;
               	int y = (int)(e.getY()/80);
               	Hero tmpHero = game.map.getHero(x-1,y-1);
               	if (tmpHero != null && tmpHero.getState() != Hero.State.DONE && !(this.activeHero != null && this.activeHero.getState() == Hero.State.MOVED)) {
                    if (this.activeHero != null && this.activeHero.getState() == Hero.State.SELECTING) {
                    	this.activeHero.setState(Hero.State.UNSELECT);
                    }
                    this.activeHero = tmpHero;
                }
                if (this.activeHero != null) {
                    if (Game.Playstate == Game.PLAYSTATE.HERO) {
                    	if (this.activeHero.getState() == Hero.State.MOVED) {
                            if (this.activeHero.attack(new Position(x-1, y-1))) {
                				game.map.update(this.activeHero, Map.Event.HERO_ATTACK, new Position(x-1,y-1));
                    			this.activeHero.setState(Hero.State.DONE);
                    			if (game.map.getTargetedMons() == 0) Game.State = Game.STATE.NEXT; 
                			}
                    	} else if (this.activeHero.getState() == Hero.State.UNSELECT) {
                            this.activeHero.setState(Hero.State.SELECTING);
                    	} else if (this.activeHero.getState() == Hero.State.SELECTING) {                          
                            if (this.activeHero.move(new Position(x-1, y-1), this.map.heroes)) {
	                            this.map.update(this.activeHero, Map.Event.HERO_MOVE, new Position(x-1,y-1));
	                            if (!game.map.heroes.contains(activeHero)) 
	                            	this.activeHero.setState(Hero.State.DONE);
	                            else
	                            	this.activeHero.setState(Hero.State.MOVED);
                            }
                    	} else {
                    	}                    		
                    	if (game.map.checkEndTurn()) {
                            Game.Playstate = Game.PLAYSTATE.MONSTER;
                    	}
                    }
                }				
		
                //Get click position (not important)
                if (1<=x && 8>=x && 1<=y && 8>=y){
                    this.bracketboo = true;
                    this.bracket.setX(x);;
                    this.bracket.setY(y);
                }
                else {
                    this.bracketboo = false;
                }
                game.setBracketBoolean(this.bracketboo);
                game.setActiveHero(this.activeHero);
            }
	}
}
