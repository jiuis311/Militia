package main.maps;
import main.entities.characters.heroes.Hero;
import main.entities.characters.heroes.Lancer;
import main.entities.characters.heroes.Swordman;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Minion;
import main.entities.characters.monsters.Monster;
import main.entities.items.*;
import main.helpers.Position;

public class MapLV1 extends Map {
     
    public MapLV1() {
        super();
        monsters.add(new Minion(new Position(1, 1)));
        
        monsters.add(new Minion(new Position(1, 3)));
        
        monsters.add(new Minion(new Position(7, 7)));
        
        monsters.add(new Minion(new Position(4, 7)));
        
        monsters.add(new BigMinion(new Position(5, 2)));
        
        monsters.add(new BigMinion(new Position(2, 4)));
        
        items.add(new Bomb(new Position(6, 2)));
        
        items.add(new Shield(new Position(2, 6)));

        heroes.add(new Lancer(new Position(6, 4)));
        
        heroes.add(new Swordman(new Position(1, 6)));
        
        setTurns(4);
        setTargetedMons(6);
        for (Monster mons:monsters) {
        	mons.setTarget();
        }
        random();
    }

    @Override
    void random() {
        Minion minion = new Minion(new Position(0, 0));
        BigMinion big_minion = new BigMinion(new Position(0, 0));
        randomCharacter(minion, 6);
        randomCharacter(big_minion, 3);
    }    
}
