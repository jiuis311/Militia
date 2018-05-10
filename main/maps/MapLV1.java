package main.maps;
import main.entities.characters.heroes.Hero;
import main.entities.characters.heroes.Lancer;
import main.entities.characters.heroes.Swordman;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Minion;
import main.entities.characters.monsters.Monster;
import main.helpers.Position;

public class MapLV1 extends Map {
     
    public MapLV1() {
        super();
        board[1][1] = Symbol.MINION;
        monsters.add(new Minion(new Position(1, 1)));
        
        board[1][3] = Symbol.MINION;
        monsters.add(new Minion(new Position(1, 3)));
        
        board[1][6] = Symbol.SWORD;
        heroes.add(new Swordman(new Position(1, 6)));
        
        board[5][2] = Symbol.BIG_MINION;
        monsters.add(new BigMinion(new Position(5, 2)));
        
        board[6][4] = Symbol.SPEAR;
        heroes.add(new Lancer(new Position(6, 4)));
        setTurns(4);
        setTargetedMons(3);
        for (Monster mons:monsters) {
        	mons.setTarget();
        }
        random();
    }
    
    @Override
	public void update(Object obj, Event eventType, Position pos) {
    	switch(eventType) {
	        case HERO_MOVE:	
	        	if (obj instanceof Swordman) {
	        		board[pos.getX()][pos.getY()] = Symbol.SWORD;
	        	}
	        	else if (obj instanceof Lancer) {
	        		board[pos.getX()][pos.getY()] = Symbol.SPEAR;
	        	}
	        	for(Monster mons:monsters) {
	        		if (mons.getCurPosition().equals(pos)) {
	        			if (mons.getClass().getSimpleName().equals("Minion")) {
	        				monsters.remove(mons);
	        				setCurScore(getCurScore() + 1);
	        			} else if (mons.getClass().getSimpleName().equals("BigMinion")) {
	        				heroes.remove(obj);
	        			}
	        			break;
	        		}
	        	}             
	            break;
	        case HERO_ATTACK:	  
	            for (Position position: ((Hero) obj).calDamageArea(pos)) {
	            	removeMonster(position);
	            }
                    
	            break;
	        case MONSTER_MOVE:
	        	if (obj instanceof Minion) {
	        		board[pos.getX()][pos.getY()] = Symbol.MINION;
	        	} else if (obj instanceof BigMinion) {
	        		board[pos.getX()][pos.getY()] = Symbol.BIG_MINION;
	        	}	            
	            Swordman sw3 = new Swordman(pos);
	            if (heroes.contains(sw3)) heroes.remove(sw3);
	             
	            break;
 	        default:
	            break;
    	}
    	//draw();
    }

    @Override
    void random() {
        Minion minion = new Minion(new Position(0, 0));
        BigMinion big_minion = new BigMinion(new Position(0, 0));
        randomCharacter(minion, 2);
        randomCharacter(big_minion, 1);
    }    
}
