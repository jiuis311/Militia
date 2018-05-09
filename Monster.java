import java.awt.Graphics;
import java.util.ArrayList;

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
            return curPosition.equals(((Monster) obj).curPosition);
        return false;
    }
	abstract void calMoveArea(ArrayList<Monster> pos);
	abstract boolean move(ArrayList<Hero> heros, ArrayList<Monster> mons);
}
