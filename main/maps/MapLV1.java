package main.maps;
import main.entities.characters.heroes.Hero;
import main.entities.characters.heroes.Lancer;
import main.entities.characters.heroes.Swordman;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Minion;
import main.entities.characters.monsters.Monster;
import main.entities.items.Bomb;
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
        
        items.add(new Bomb(new Position(6, 2)));
        
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
    void random() {
        Minion minion = new Minion(new Position(0, 0));
        BigMinion big_minion = new BigMinion(new Position(0, 0));
        randomCharacter(minion, 10);
        randomCharacter(big_minion, 3);
    }    
}
