package main.maps;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import main.entities.characters.heroes.*;
import main.entities.characters.monsters.*;
import main.entities.items.*;
import main.helpers.*;

public abstract class Map {
    public ArrayList<Hero> heroes;
    public ArrayList<Monster> monsters;
    public ArrayList<Item> items;
    //protected Symbol[][] board;
    private int curScore;
    private int turns;
    private int targetedMons;
    private boolean heroDied;
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
//        board = new Symbol[Config.GAME_WIDTH][Config.GAME_HEIGHT];
//        for(int i = 0; i < Config.GAME_HEIGHT; i++) {
//            for(int j = 0; j < Config.GAME_WIDTH; j++) {
//                board[i][j] = Symbol.DEFAULT;
//            }
//        }
        
        heroes = new ArrayList<Hero>();
        monsters = new ArrayList<Monster>();
        items = new ArrayList<Item>();
        setHeroDied(false);
        //setUnselectState();
    }
    
    /**
     * Use this function for test
     */
//    protected void draw() {
//        for(int i = 0; i < Config.GAME_HEIGHT; i++) {
//            for(int j = 0; j < Config.GAME_WIDTH; j++) {
//                switch(board[j][i]) {
//                    case DEFAULT:
//                        System.out.print("-   ");
//                        break;
//                    case MINION:
//                        System.out.print("M   ");
//                        break;
//                    case BIG_MINION:
//                        System.out.print("BM  ");
//                        break;
//                    case SWORD:
//                        System.out.print("S   ");
//                        break;
//                    case SPEAR:
//                        System.out.print("SP  ");
//                        break;
//                    default:
//                        System.out.print("-   ");
//                        break;
//                }
//            }
//            System.out.println();
//        }
//    }
    
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
                                //board[pos.getX()][pos.getY()] = Symbol.MINION;
                                monsters.add(new Minion(pos));
                              });
                break;
            case "BigMinion":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                //board[pos.getX()][pos.getY()] = Symbol.BIG_MINION;
                                monsters.add(new BigMinion(pos));
                              });
                break;
            case "Ghost":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                //board[pos.getX()][pos.getY()] = Symbol.GHOST;
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
                                //board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
    	            return true;
    			} else if (mons.getClass().getSimpleName().equals("BigMinion")) {
    				BigMinion mon = (BigMinion) mons;
    				int shield = mon.getShield();
    	            if (shield == 0) {
    	            	if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
    	            	monsters.remove(mons);
    	            	setCurScore(getCurScore() + 3);
    	            	//board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
    	            	return true;
    	            } else {
    	            	mon.lowerShield();
    	            	return false;
    	            } 
    			} else if (mons.getClass().getSimpleName().equals("Ghost")) {
    				if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
    				monsters.remove(mons);
    				setCurScore(getCurScore() + 2);
                                //board[pos.getX()][pos.getY()] = Symbol.DEFAULT;
    	            return true;
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
    
    public void update(Object obj, Event eventType, Position pos) {
        switch(eventType) {
            case HERO_MOVE:
                
                // this part is only for testing in console
//                if (obj instanceof Swordman) {
//                        board[pos.getX()][pos.getY()] = Symbol.SWORD;
//                }
//                else if (obj instanceof Lancer) {
//                        board[pos.getX()][pos.getY()] = Symbol.SPEAR;
//                }
                // --------------------------------------------------
                
                // check for position of monster
               for(Monster mons:monsters) {
                    if (mons.getCurPosition().equals(pos)) {
                        if (mons instanceof BigMinion || mons instanceof Ghost) {
                            heroes.remove((Hero) obj);
                            setHeroDied(true);
                        } else {
                            monsters.remove(mons);
                            if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
                            setCurScore(getCurScore() + 1);
                        }
                        break;
                    }
                }
                
                // check for position of item
               for(Item item:items) {
                   if (item.getCurPosition().equals(pos)) {
                        if(item instanceof Bomb) {
                             heroes.remove((Hero) obj);
                             for(Position position: ((Bomb) item).getDamageArea())
                                 removeMonster(position);
                        }
                        else if(item instanceof Shield) {
                            break;
                        }
                   }
               }
                break;
            case HERO_ATTACK:
                ArrayList<Position> damageArea = new ArrayList<Position>();
                
                for (Position position: ((Hero) obj).calDamageArea(pos)) {
                    damageArea.add(position);
                }
                
                for(Item item: items) {
                    if(item.getCurPosition().equals(pos)) {
                        if(item instanceof Bomb) {
                            for(Position position: ((Bomb) item).getDamageArea())
                                damageArea.add(position);
                        }
                    }
                }
                
                for (Position position: damageArea) {
                    removeMonster(position);
                }
                break;
            case MONSTER_MOVE:
                
                // this part is only for testing in console
//                 if (obj instanceof Minion) {
//                        board[pos.getX()][pos.getY()] = Symbol.MINION;
//                } else if (obj instanceof BigMinion) {
//                        board[pos.getX()][pos.getY()] = Symbol.BIG_MINION;
//                }	            
                // -------------------------------------------
                 
                for(Hero hero: heroes) {
                    if(hero.getCurPosition().equals(pos)) {
                        heroes.remove(hero);
                        setHeroDied(true);
                        break;
                    }
                }
                
                for(Item item: items) {
                    if(item.getCurPosition().equals(pos)) {
                        items.remove(item);
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

	public boolean isHeroDied() {
		return heroDied;
	}

	public void setHeroDied(boolean heroDied) {
		this.heroDied = heroDied;
	}
}