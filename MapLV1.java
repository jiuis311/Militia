
public class MapLV1 extends Map {
     
    MapLV1() {
        super();
        board[1][1] = Symbol.MINION;
        monsters.add(new Minion(new Position(1, 1)));
        
        board[1][3] = Symbol.MINION;
        monsters.add(new Minion(new Position(1, 3)));
        
        board[1][6] = Symbol.SWORD;
        heros.add(new Swordman(new Position(1, 6)));
        
        board[5][2] = Symbol.BIG_MINION;
        monsters.add(new BigMinion(new Position(5, 2)));
        
        board[6][4] = Symbol.SPEAR;
        heros.add(new Lancer(new Position(6, 4)));
        turns = 4;
        targetedMons = 3;
        for (Monster mons:monsters) {
        	mons.setTarget();
        }
        random();
    }
    
    @Override
    void update(Object obj, Event eventType, Position pos) {
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
	        			if (mons.getClass().getSimpleName() == "Minion") {
	        				monsters.remove(mons);
	        				curScore++;
	        			} else if (mons.getClass().getSimpleName() == "BigMinion") {
	        				heros.remove(obj);
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
	            if (heros.contains(sw3)) heros.remove(sw3);
	            Lancer sp3 = new Lancer(pos);
	            if (heros.contains(sp3)) heros.remove(sp3);
	            break;
 	        default:
	            break;
    	}
    	draw();
    }

    @Override
    void random() {
        Minion minion = new Minion(new Position(0, 0));
        BigMinion big_minion = new BigMinion(new Position(0, 0));
        randomUtility(minion, 5);
        randomUtility(big_minion, 1);
    }    
}
