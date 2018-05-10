package main.helpers;

public class Position {
    private int x;
    private int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; };
    public int getY() { return y; };
    public void setX(int x) { this.x = x; };
    public void setY(int y) { this.y = y; };
    @Override
    public boolean equals(Object o) {
        if(o instanceof Position) {
            return ((Position) o).getX() == this.x && ((Position) o).getY() == this.y;
        }
        return false;
    }
    public int getDistance(Position pos) {
		return Math.abs(pos.getX() - this.getX()) + Math.abs(pos.getY() - this.getY());
	}
    public boolean valid() {
        return (0 <= x && x < Config.GAME_WIDTH && 0 <= y && y < Config.GAME_HEIGHT);
    }
}