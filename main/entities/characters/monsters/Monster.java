package main.entities.characters.monsters;
import java.util.ArrayList;

import main.entities.characters.Character;
import main.entities.characters.heroes.Hero;
import main.helpers.Position;

public abstract class Monster extends Character{
	private boolean target;
	
	//Getter and setter
	public boolean isTargeted() {
		return target;
	}
	public void setTarget() {
		target = true;
	}
	//
	Monster(Position pos) {
		super(pos);
		target = false;
	}
	public boolean equals(Object obj) {
        if(obj instanceof Monster) 
            return this.getCurPosition().equals(((Monster) obj).getCurPosition());
        return false;
    }
	abstract void calMoveArea(ArrayList<Monster> pos);
	public abstract boolean move(ArrayList<Hero> heros, ArrayList<Monster> mons);
}
