
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
//            public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 100, 50);
//            public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 100, 50);
            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 +120+274){
                if (my >= 200 && my <= 274){
                    //Press play button
                	System.out.println("Here");
                    MenuButton.startButtonState = true;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 +120+274){
                if (my >= 300 && my <= 374){
                    //Press play button
                    MenuButton.helpButtonState = true;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 +120+274){
                if (my >= 400 && my <= 474){
                    //Press play button
                	MenuButton.quitButtonState = true;
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
//            public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 100, 50);
//            public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 100, 50);
            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 +120+274){
                if (my >= 200 && my <= 274){
                    //Press play button
                    Game.State = Game.STATE.GAME;
                    MenuButton.startButtonState = false;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 +120+274){
                if (my >= 300 && my <= 374){
                    //Press play button
                    MenuButton.helpButtonState = false;
                }
            }
            
            if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 +120+274){
                if (my >= 400 && my <= 474){
                	MenuButton.quitButtonState = false;
                    //Press play button
                    System.exit(1);
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
