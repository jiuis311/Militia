public class MapLV1 extends Map {
     
    MapLV1() {
        super();
        board[1][1] = Symbol.MINION;
        monsters.add(new Minion(new Position(1, 1)));
        
        board[1][3] = Symbol.MINION;
        monsters.add(new Minion(new Position(1, 3)));
        
        board[1][6] = Symbol.SWORD;
        heros.add(new Sword(new Position(1, 6)));
        
        board[5][2] = Symbol.BIG_MINION;
        monsters.add(new BigMinion(new Position(5, 2)));
        
        board[6][4] = Symbol.SPEAR;
        heros.add(new Spear(new Position(6, 4)));
        
        random();
    }
    
    @Override
    void update(Object obj, Event eventType, Position pos) {
    	switch(eventType) {
	        case HERO_MOVE:		        	
	        	BigMinion bm1 = new BigMinion(pos);
	        	if (obj instanceof Sword)
	        		board[pos.getX()][pos.getY()] = Symbol.SWORD;
	        		Sword sw1 = new Sword(pos);
	        		if (monsters.contains(bm1)) heros.remove(sw1);
	        	else if (obj instanceof Spear) {
	        		board[pos.getX()][pos.getY()] = Symbol.SPEAR;
	        		Spear sp1 = new Spear(pos);
	        		if (monsters.contains(bm1)) heros.remove(sp1);
	        	}
	        	Minion m1 = new Minion(pos);
	            if (monsters.contains(m1)) monsters.remove(m1);	            
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
	            Sword sw3 = new Sword(pos);
	            if (heros.contains(sw3)) heros.remove(sw3);
	            Spear sp3 = new Spear(pos);
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
