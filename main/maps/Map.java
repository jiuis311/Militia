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
    private static int curScore;
    private int turns;
    private int targetedMons;
    private boolean heroDied;
    
    public enum Event {
    	HERO_MOVE,
        HERO_ATTACK,
        MONSTER_MOVE,
    }
    
    Map() {
        heroes = new ArrayList<Hero>();
        monsters = new ArrayList<Monster>();
        items = new ArrayList<Item>();
        setHeroDied(false);
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
    
    private boolean checkItem(Position pos) {
        for(Item item: items) {
            if(item.getCurPosition().equals(pos))
                return true;
        }
        return false;
    }

    private boolean checkEntity(Position pos) {
        return checkHero(pos) || checkMonster(pos) || checkItem(pos);
    }
    
    private Position calPosition(int number) {
        int y = number%Config.GAME_WIDTH;
        int x = (number - y)/Config.GAME_WIDTH;
        return new Position(x, y);
    }

    protected void randomCharacter(Object obj, int num) {
        IntStream stream = new Random().ints(0, Config.GAME_WIDTH*Config.GAME_HEIGHT)
                                    .filter(number->!checkEntity(calPosition(number)))
                                    .distinct()
                                    .limit(num);
        switch(obj.getClass().getSimpleName()) {
            case "Minion":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                monsters.add(new Minion(pos));
                              });
                break;
            case "BigMinion":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                monsters.add(new BigMinion(pos));
                              });
                break;
            case "Ghost":
                stream.forEach(number->
                              {
                                Position pos = calPosition(number);
                                monsters.add(new Ghost(pos));
                              });
                break;
            default:
                break;
        }
    }

    private boolean removeMonster(Position pos) {
    	for(Monster mons:monsters) {
    		if (mons.getCurPosition().equals(pos)) {
    			if (mons.getClass().getSimpleName().equals("Minion")) {
                            if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
                            monsters.remove(mons);
                            setCurScore(getCurScore() + 1);
                            return true;
    			} else if (mons.getClass().getSimpleName().equals("BigMinion")) {
                            BigMinion mon = (BigMinion) mons;
                            int shield = mon.getShield();
                            if (shield == 0) {
                                if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
                                monsters.remove(mons);
                                setCurScore(getCurScore() + 3);
                                return true;
                            } else {
                                mon.lowerShield();
                                return false;
                            } 
    			} else if (mons.getClass().getSimpleName().equals("Ghost")) {
                            if (mons.isTargeted()) setTargetedMons(getTargetedMons() - 1);
                            monsters.remove(mons);
                            setCurScore(getCurScore() + 2);
                            return true;
    			}
    		}
    	}       
        return false;
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
                
                // check for position of monster
               for(Monster mons:monsters) {
                    if (mons.getCurPosition().equals(pos)) {
                        if (mons instanceof BigMinion) {
                            heroes.remove((Hero) obj);
                            setHeroDied(true);
                        } else if(mons instanceof Ghost) {
                            if(((Hero) obj).getShield() == true) {
                                removeMonster(pos);
                                ((Hero) obj).setShield(false);
                                setHeroDied(false);
                            } else {
                                heroes.remove((Hero) obj);
                                setHeroDied(true);
                            }
                                   
                        } else if(mons instanceof Minion) {
                            removeMonster(pos);
                            setHeroDied(false);
                        }
                        break;
                    }
                }
                
                // check for position of item
               for(Item item:items) {
                   if (item.getCurPosition().equals(pos)) {
                        if(item instanceof Bomb) {
                             for(Position position: ((Bomb) item).getDamageArea())
                                 removeMonster(position);
                            break;
                        }
                        else if(item instanceof Shield) {
                            ((Hero) obj).setShield(true);
                            break;
                        }
                        items.remove(item);
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
                            items.remove(item);
                        }
                    }
                }
                
                for (Position position: damageArea) {
                    removeMonster(position);
                }
                break;
            case MONSTER_MOVE:
                 
                for(Hero hero: heroes) {
                    if(hero.getCurPosition().equals(pos)) {
                        if(obj.getClass().getSimpleName().equals("BigMinion")) {
                            heroes.remove(hero);
                            setHeroDied(true);
                        } else {
                            if(hero.getShield() == true) {
                                hero.setShield(false);
                                removeMonster(pos);
                                setHeroDied(false);
                            } else {
                                heroes.remove(hero);
                                setHeroDied(true);
                            }
                        }
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

	private void setHeroDied(boolean heroDied) {
		this.heroDied = heroDied;
	}
}