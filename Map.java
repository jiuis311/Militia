import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

abstract class Map {
    ArrayList<Hero> heros;
    ArrayList<Monster> monsters;
    protected Symbol[][] board;
    
   enum Symbol {
        DEFAULT,
        MINION,
        BIG_MINION,
        SWORD,
        SPEAR;
    }
    
    enum Event {
    	HERO_MOVE,
        HERO_ATTACK,
        MONSTER_MOVE,
    }
    
    Map() {
        board = new Symbol[Config.GAME_WIDTH][Config.GAME_HEIGHT];
        for(int i = 0; i < Config.GAME_HEIGHT; i++) {
            for(int j = 0; j < Config.GAME_WIDTH; j++) {
                board[i][j] = Symbol.DEFAULT;
            }
        }
        
        heros = new ArrayList<Hero>();
        monsters = new ArrayList<Monster>();
    }
    
    /**
     * Use this function for test
     */
    protected void draw() {
        for(int i = 0; i < Config.GAME_HEIGHT; i++) {
            for(int j = 0; j < Config.GAME_WIDTH; j++) {
                switch(board[j][i]) {
                    case DEFAULT:
                        System.out.print("-   ");
                        break;
                    case MINION:
                        System.out.print("M   ");
                        break;
                    case BIG_MINION:
                        System.out.print("BM  ");
                        break;
                    case SWORD:
                        System.out.print("S   ");
                        break;
                    case SPEAR:
                        System.out.print("SP  ");
                        break;
                    default:
                        System.out.print("-   ");
                        break;
                }
            }
            System.out.println();
        }
    }
    
    public Hero getHero(int x, int y) {
        Position pos = new Position(x, y);
        
        for(Hero hero: heros) {
            if(hero.curPosition.equals(pos))
                return hero;
        }
        return null;
    }
    
    private boolean checkHero(Position pos) {
        for(Hero hero: heros) {
            if(hero.curPosition.equals(pos))
                return true;
        }
        return false;
    }
    
    private boolean checkMonster(Position pos) {
        for(Monster monster: monsters) {
            if(monster.curPosition.equals(pos))
                return true;
        }
        return false;
    }
    
    private boolean checkUtility(Position pos) {
        return checkHero(pos) || checkMonster(pos);
    }
    
    private Position calPosition(int number) {
        int y = number%Config.GAME_WIDTH;
        int x = (number - y)/Config.GAME_WIDTH;
        return new Position(x, y);
    }
    
    protected void randomUtility(Object obj, int num) {
        IntStream stream = new Random().ints(0, Config.GAME_WIDTH*Config.GAME_HEIGHT)
                                    .filter(number->!checkUtility(calPosition(number)))
                                    .distinct()
                                    .limit(num);
        switch(obj.getClass().getSimpleName()) {
            case "Sword":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                board[pos.getX()][pos.getY()] = Symbol.SWORD;
                                heros.add(new Sword(pos));
                              });
                break;
            case "Minion":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                board[pos.getX()][pos.getY()] = Symbol.MINION;
                                monsters.add(new Minion(pos));
                              });
                break;
            case "BigMinion":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                board[pos.getX()][pos.getY()] = Symbol.BIG_MINION;
                                monsters.add(new BigMinion(pos));
                              });
                break;
            default:
                break;
        }
    }
    protected void removeMonster(Position pos) {
    	Minion m2 = new Minion(pos);
        if (monsters.contains(m2)) {
            monsters.remove(m2);
            board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
        }
        BigMinion bm2 = new BigMinion(pos);
        if (monsters.contains(bm2)) {
            int shield = bm2.getShield();
            if (shield == 0) {
            	monsters.remove(bm2);
            	board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
            } else {
            	bm2.lowerShield();
            }                
        }
    }
    abstract void update(Object obj, Event eventType, Position pos);
    abstract void random();
}