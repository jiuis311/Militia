package main.entities.items;

import java.util.ArrayList;
import main.helpers.Position;

public class Bomb extends Item {
    private ArrayList<Position> damageArea;
    
    Bomb(Position position) {
        super(position);
        damageArea = new ArrayList<Position>();
        
        int x = getCurPosition().getX();
        int y = getCurPosition().getY();
        
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) damageArea.add(tmp);
            }
        }
    }
    
    
}
