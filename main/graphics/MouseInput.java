package main.graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MONMON
 */
public class MouseInput implements MouseListener{

    @Override
    public void mousePressed(MouseEvent e) {
    	if (Game.State == Game.STATE.MENU) {
    		int mx = e.getX();
                int my = e.getY();
            if (mx >= Game.WIDTH / 2 + 230 && mx <= Game.WIDTH / 2+230+170){
                if (my >= 350 && my <= 450){
                    //Press play button
                    MenuButton.startButtonState = true;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 230 && mx <= Game.WIDTH / 2 +230+170){
                if (my >= 450 && my <= 550){
                    //Press play button
                    MenuButton.helpButtonState = true;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 230 && mx <= Game.WIDTH / 2 +230+170){
                if (my >= 550 && my <= 650){
                    //Press play button
                	MenuButton.quitButtonState = true;
                }
            }
    	}
        else if(Game.State == Game.STATE.GAME){
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH - 570 && mx <= Game.WIDTH - 570 + 85){
                if (my >= 25 && my <= 99){
                    //Press quit button
                    MenuButton.exitButtonState = true;
                }
            }
        }
        else if (Game.State == Game.STATE.ENDGAME){
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH / 2 + 210 && mx <= Game.WIDTH / 2 +210+250){
                if (my >= 350 && my <= 457){
                    EndMenuButton.reStartButtonState = true;
                }
            }            
            if (mx >= Game.WIDTH / 2 + 210 && mx <= Game.WIDTH / 2 +210+170){
                if (my >= 450 && my <= 557){
                    EndMenuButton.exitButtonState = true;
                }
            }
        }
        else if (Game.State == Game.STATE.NEXT){
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH / 2 + 650 && mx <= Game.WIDTH / 2 + 650 + 210){
                if (my >= 600 && my <= 680){
                    NextLevelMenu.nextButtonState = true;
                }
            }
            if (mx >= Game.WIDTH / 2 - 250 && mx <= Game.WIDTH / 2 - 250 + 210){
                if (my >= 600 && my <= 680){
                    NextLevelMenu.quitButtonState = true;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    	if (Game.State == Game.STATE.MENU) {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH / 2 + 230 && mx <= Game.WIDTH / 2 +230+170){
                if (my >= 350 && my <= 450){
                    //Press play button
                    Game.State = Game.STATE.GAME;
                    MenuButton.startButtonState = false;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 230 && mx <= Game.WIDTH / 2 +230+170){
                if (my >= 450 && my <= 550){
                    //Press play button
                    MenuButton.helpButtonState = false;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 230 && mx <= Game.WIDTH / 2 +230+170){
                if (my >= 550 && my <= 650){
                	MenuButton.quitButtonState = false;
                    //Press play button
                    System.exit(1);
                }
            }
        }
        else if (Game.State == Game.STATE.GAME) {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH - 570 && mx <= Game.WIDTH - 570 + 85){
                if (my >= 25 && my <= 99){
                    //release exit button
                    Game.State = Game.STATE.MENU;
                    MenuButton.exitButtonState = false;
                }
            }
        }
        else if (Game.State == Game.STATE.ENDGAME){
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH / 2 + 210 && mx <= Game.WIDTH / 2 +210+250){
                if (my >= 350 && my <= 450){
                    //Press play button
                    Game.State = Game.STATE.GAME;
                    EndMenuButton.reStartButtonState = false;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 210 && mx <= Game.WIDTH / 2 +210+170){
                if (my >= 450 && my <= 550){
                    //Press play button
                    EndMenuButton.exitButtonState = false;
                    System.exit(1);
                }
            }
        } 
        else if (Game.State == Game.STATE.NEXT){
            int mx = e.getX();
            int my = e.getY();
            if (mx >= Game.WIDTH / 2 + 650 && mx <= Game.WIDTH / 2 + 650 + 210){
                if (my >= 600 && my <= 680){
                    //Press play button
                    Game.State = Game.STATE.LVUP;
                    NextLevelMenu.nextButtonState = false;
                }
            }
            if (mx >= Game.WIDTH / 2 - 250 && mx <= Game.WIDTH / 2 - 250 + 210){
                if (my >= 600 && my <= 680){
                    Game.State = Game.STATE.MENU;
                    NextLevelMenu.quitButtonState = false;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
