import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Mode {
    private Map map;
    
    private boolean checkEndGame() {
        return (map.heros.isEmpty() || map.monsters.isEmpty());
    }
    
    Mode(Map map) {
        this.map = map;
        Scanner reader = new Scanner(System.in);
        int x, y;
        Hero hero_tmp = null;
        Hero activeHero = null;
        Position pos = null;
        Position old_pos = null;
        
        // dat state cua tat ca hero la unselect
        // loop game
        map.draw();
        do {
            do {
                // cho den khi chon duoc 1 hero
                System.out.println("Chon hero");
                do {
                    System.out.println("Chon x: ");
                    x = reader.nextInt();
                    System.out.println("Chon y: ");
                    y = reader.nextInt();
                    hero_tmp = map.getHero(x, y);
                } while(hero_tmp == null || hero_tmp.getState() == Hero.State.DONE);
                
                System.out.println("Chon hero thanh cong");
                
                // draw move Area
                activeHero = hero_tmp;
                
                // vong lap da chon duoc 1 hero
                // chon vi tri de di chuyen
                do {
                    System.out.println("Bat dau move");
                    old_pos = activeHero.getCurPosition();
                    System.out.println("Chon x: ");
                    x = reader.nextInt();
                    System.out.println("Chon y: ");
                    y = reader.nextInt();
                    pos = new Position(x, y);
                    
                    hero_tmp = map.getHero(x, y);   // check xem vi tri day co hero khong
                    
                    if(hero_tmp != null) {
                        if(hero_tmp.getState() == Hero.State.DONE)
                            activeHero = null;
                        else 
                            activeHero = hero_tmp;
                    } else {
                        if (activeHero.move(pos)) {
                            this.map.board[old_pos.getX()][old_pos.getY()] = Map.Symbol.DEFAULT;
                            this.map.update(activeHero, Map.Event.HERO_MOVE, pos);
                            activeHero.setState(Hero.State.MOVED);
                        } else
                            activeHero = null;
                    }
                    // thoat vong lap trong 2 kha nang
                    // khong chon duoc hero nao ca (activeHero == null)
                    // hero da di chuyen thanh cong (activeHero.getState() == MOVED)
                    // neu chon duoc 1 hero chua di chuyen thi quay tro lai
                    // activeHero != null && activeHero.getState() != MOVED
                } while(activeHero != null && activeHero.getState() != Hero.State.MOVED);
                
                if(activeHero != null) {
                    // hero da di chuyen thanh cong
                    
                    // vong lap attack
                    // bat buoc phai attack trung
                    do {
                        System.out.println("Bat dau attack");
                        System.out.println("Chon x: ");
                        x = reader.nextInt();
                        System.out.println("Chon y: ");
                        y = reader.nextInt();
                        pos = new Position(x, y);
                    } while(!activeHero.attack(pos));
                    
                    // attack thanh cong
                    map.update(activeHero, Map.Event.HERO_ATTACK, pos);
                    // set state thanh done
                    activeHero.setState(Hero.State.DONE);
                    System.out.println(activeHero.getState());
                }
            } while(!map.checkEndTurn());   // tat ca hero thuc hien xong
           
           for(Monster monster_tmp: map.monsters) {
                if (!checkEndGame()) {
                    System.out.println("Monster move");
                    old_pos = monster_tmp.getCurPosition();
                    map.board[old_pos.getX()][old_pos.getY()] = Map.Symbol.DEFAULT;
                    monster_tmp.move(map.heros, map.monsters);
                    pos = monster_tmp.getCurPosition();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    map.update(monster_tmp, Map.Event.MONSTER_MOVE, pos);
                }
            }
           
           // set lai state cho hero
           map.setUnselectState();
        } while(!checkEndGame());
    }
}
