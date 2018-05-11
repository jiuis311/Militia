package main.maps;

import main.entities.characters.heroes.Archer;
import main.entities.characters.heroes.Lancer;
import main.entities.characters.heroes.Swordman;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Ghost;
import main.entities.characters.monsters.Minion;
import main.entities.characters.monsters.Monster;
import main.helpers.Position;
import main.maps.Map.Symbol;

public class MapLV2 extends Map {
	public MapLV2() {
        super();
        //board[1][6] = Symbol.SPEAR;
        heroes.add(new Lancer(new Position(1, 6)));
        
        //board[4][5] = Symbol.ARROW;
        heroes.add(new Archer(new Position(4, 5)));
        
        //board[7][5] = Symbol.SWORD;
        heroes.add(new Swordman(new Position(7, 5)));
        
        //board[1][2] = Symbol.BIG_MINION;
        monsters.add(new BigMinion(new Position(1, 2)));
        
        //board[2][4] = Symbol.BIG_MINION;
        monsters.add(new BigMinion(new Position(2, 4)));
        
        //board[4][2] = Symbol.MINION;
        monsters.add(new Minion(new Position(4, 2)));
        
        //board[6][1] = Symbol.GHOST;
        monsters.add(new Ghost(new Position(6, 1)));
        
        setTurns(4);
        setTargetedMons(4);
        for (Monster mons:monsters) {
        	mons.setTarget();
        }
        random();
    }

	@Override
	void random() {
		Minion minion = new Minion(new Position(0, 0));
        BigMinion big_minion = new BigMinion(new Position(0, 0));
        Ghost ghost = new Ghost(new Position(0, 0));
        randomCharacter(minion, 8);
        randomCharacter(big_minion, 2);
        randomCharacter(ghost, 1);
	}
}
