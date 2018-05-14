package main.maps;

import main.entities.characters.heroes.Archer;
import main.entities.characters.heroes.Lancer;
import main.entities.characters.heroes.Swordman;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Ghost;
import main.entities.characters.monsters.Minion;
import main.entities.characters.monsters.Monster;
import main.helpers.Position;

public class MapLV2 extends Map {
	public MapLV2() {
        super();;
        heroes.add(new Lancer(new Position(1, 6)));

        heroes.add(new Archer(new Position(4, 5)));

        heroes.add(new Swordman(new Position(7, 5)));

        monsters.add(new BigMinion(new Position(1, 2)));

        monsters.add(new BigMinion(new Position(2, 4)));

        monsters.add(new BigMinion(new Position(3, 6)));

        monsters.add(new Minion(new Position(4, 2)));

        monsters.add(new Minion(new Position(7, 1)));

        monsters.add(new Ghost(new Position(5, 2)));
        
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
        Ghost ghost = new Ghost(new Position(0, 0));
        randomCharacter(minion, 9);
        randomCharacter(big_minion, 3);
        randomCharacter(ghost, 2);
	}
}
