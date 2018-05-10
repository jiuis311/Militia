package main.maps;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import main.entities.characters.heroes.Archer;
import main.entities.characters.heroes.Hero;
import main.entities.characters.heroes.Lancer;
import main.entities.characters.heroes.Swordman;
import main.entities.characters.monsters.BigMinion;
import main.entities.characters.monsters.Ghost;
import main.entities.characters.monsters.Minion;
import main.entities.characters.monsters.Monster;
import main.helpers.Config;
import main.helpers.Position;

public abstract class Map {
    public ArrayList<Hero> heroes;
    public ArrayList<Monster> monsters;
    protected Symbol[][] board;
    private int curScore;
    private int turns;
    private int targetedMons;
    enum Symbol {
        DEFAULT,
        MINION,
        BIG_MINION,
        GHOST,
        SWORD,
        ARROW,
        SPEAR;
    }
    
    public enum Event {
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
        
        heroes = new ArrayList<Hero>();
        monsters = new ArrayList<Monster>();
        setUnselectState();
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
        
        for(Hero hero: heroes) {
            if(hero.getCurPosition().equals(pos))
                return hero;
        }
        return null;
    }
    
    public Monster getMonster(Position pos) {
        for(Monster mons: monsters) {
            if(mons.getCurPosition().equals(pos))
                return mons;
        }
        return null;
    }
    
    private boolean checkHero(Position pos) {
        for(Hero hero: heroes) {
            if(hero.getCurPosition().equals(pos))
                return true;
        }
        return false;
    }
    
    private boolean checkMonster(Position pos) {
        for(Monster monster: monsters) {
            if(monster.getCurPosition().equals(pos))
                return true;
        }
        return false;
    }
    
    private boolean checkCharacter(Position pos) {
        return checkHero(pos) || checkMonster(pos);
    }
    
    private Position calPosition(int number) {
        int y = number%Config.GAME_WIDTH;
        int x = (number - y)/Config.GAME_WIDTH;
        return new Position(x, y);
    }
    
    protected void randomCharacter(Object obj, int num) {
        IntStream stream = new Random().ints(0, Config.GAME_WIDTH*Config.GAME_HEIGHT)
                                    .filter(number->!checkCharacter(calPosition(number)))
                                    .distinct()
                                    .limit(num);
        switch(obj.getClass().getSimpleName()) {
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
            case "Ghost":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                board[pos.getX()][pos.getY()] = Symbol.GHOST;
                                monsters.add(new Ghost(pos));
                              });
                break;
            default:
                break;
        }
    }

    protected boolean removeMonster(Position pos) {
    	for(Monster mons:monsters) {
    		if (mons.getCurPosition().equals(pos)) {
    			if (mons.getClass().getSimpleName().equals("Minion")) {
    				if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
    				monsters.remove(mons);
    				setCurScore(getCurScore() + 1);
    	            board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
    	            return true;
    			} else if (mons.getClass().getSimpleName().equals("BigMinion")) {
    				BigMinion mon = (BigMinion) mons;
    				int shield = mon.getShield();
    	            if (shield == 0) {
    	            	if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
    	            	monsters.remove(mons);
    	            	setCurScore(getCurScore() + 3);
    	            	board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
    	            	return true;
    	            } else {
    	            	mon.lowerShield();
    	            	return false;
    	            } 
    			}
    		}
    	} 
        
        return true;
    }
  
    public void setUnselectState() {
        for(Hero hero: heroes)
            hero.setState(Hero.State.UNSELECT);
    }
    public boolean checkEndTurn() {
        for(Hero hero: heroes)
            if(hero.getState() != Hero.State.DONE)
                return false;
        setTurns(getTurns() - 1);
        return true;
    }
    
    private void HeroMove(Object obj, Event eventType, Position position) {
        
    }
    
    private void HeroAttack(Object obj, Event eventType, Position pos) {
        
    }
    
    public void update(Object obj, Event eventType, Position pos) {
        switch(eventType) {
            case HERO_MOVE:
                
                // this part is only for testing in console
                if (obj instanceof Swordman) {
                        board[pos.getX()][pos.getY()] = Symbol.SWORD;
                }
                else if (obj instanceof Lancer) {
                        board[pos.getX()][pos.getY()] = Symbol.SPEAR;
                }
                // --------------------------------------------------
                
               for(Monster mons:monsters) {
                    if (mons.getCurPosition().equals(pos)) {
                        if (mons instanceof BigMinion) {
                            heroes.remove((Hero) obj);
                        } else {
                            monsters.remove(mons);
                            setCurScore(getCurScore() + 1);
                        }
                        break;
                    }
                }
                break;
            case HERO_ATTACK:
                for (Position position: ((Hero) obj).calDamageArea(pos)) {
                    removeMonster(position);
                }
                break;
            case MONSTER_MOVE:
                
                // this part is only for testing in console
                 if (obj instanceof Minion) {
                        board[pos.getX()][pos.getY()] = Symbol.MINION;
                } else if (obj instanceof BigMinion) {
                        board[pos.getX()][pos.getY()] = Symbol.BIG_MINION;
                }	            
                // -------------------------------------------
                 
                for(Hero hero: heroes) {
                    if(hero.getCurPosition().equals(pos)) {
                        heroes.remove(hero);
                        break;
                    }
                }
                break;
        }
    }
    
    abstract void random();

	public int getTargetedMons() {
		return targetedMons;
	}

	public void setTargetedMons(int targetedMons) {
		this.targetedMons = targetedMons;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public int getCurScore() {
		return curScore;
	}

	public void setCurScore(int curScore) {
		this.curScore = curScore;
	}
}