import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

abstract class Hero {
    private Position curPosition;
    private ArrayList<Position> moveArea;
    private ArrayList<Position> attackArea;
    private State state;
    private DrawTile heroImage;
    private DrawTile moveAreaTile;
    private DrawTile attackAreaTile;
    
    //Getter and setter
    public Position getCurPosition() {
		return curPosition;
	}

	public void setCurPosition(Position curPosition) {
		this.curPosition = curPosition;
	}

	public DrawTile getHeroImage() {
		return heroImage;
	}

	public void setHeroImage(DrawTile heroImage) {
		this.heroImage = heroImage;
	}

	public DrawTile getMoveAreaTile() {
		return moveAreaTile;
	}

	public void setMoveAreaTile(DrawTile moveAreaTile) {
		this.moveAreaTile = moveAreaTile;
	}

	public DrawTile getAttackAreaTile() {
		return attackAreaTile;
	}

	public void setAttackAreaTile(DrawTile attackAreaTile) {
		this.attackAreaTile = attackAreaTile;
	}

	public void setMoveArea(ArrayList<Position> moveArea) {
		this.moveArea = moveArea;
	}

	public void setAttackArea(ArrayList<Position> attackArea) {
		this.attackArea = attackArea;
	}
	
	//
	public enum State {
        UNSELECT,
        SELECTING,
        MOVING,
        MOVED,
        ATTACKING,
        DONE
    }
    
    Hero(Position pos) {
        curPosition = new Position(pos.getX(), pos.getY());
        moveArea = new ArrayList<Position> ();
        attackArea = new ArrayList<Position> ();
        state = State.UNSELECT;
        moveAreaTile = new DrawTile("/move-tile.png");
        attackAreaTile = new DrawTile("/attack-tile.png");
    }
    
    public void setState(State state) { this.state = state; }
    public State getState() { return state; }
    
    void getDetail() {
        calMoveArea();
        calAttackArea();
        System.out.println("Vi tri di chuyen");
        for(Position pos: moveArea) {
            System.out.print('('+Integer.toString(pos.getX())+','+pos.getY()+')');
        }
        System.out.println();
        System.out.println("Vi tri tan cong");
        for(Position pos: attackArea) {
            System.out.print('('+Integer.toString(pos.getX())+','+pos.getY()+')');
        }
    }
    
    public ArrayList<Position> getMoveArea() {
       calMoveArea();
       return moveArea;
    }
    
    public ArrayList<Position> getAttackArea() {
        calAttackArea();
        return attackArea;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Hero) 
            return curPosition.equals(((Hero) o).curPosition);
        return false;
    }
    
    abstract void calMoveArea();
    abstract void calAttackArea();
    abstract boolean move(Position pos);
    abstract boolean attack(Position pos);
    abstract ArrayList<Position> calDamageArea(Position pos);
}
