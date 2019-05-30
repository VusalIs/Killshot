package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Creating a simple information panel for our game to display.
 * @author Sayid Akhundov
 */
public class HUD {
    
    private  Player player;

    public HUD(Player player) {
        this.player = player;
    }
    
    
    public void tick() {
         }

  
    public void render(Graphics g) {
        int HEALTH = player.getHealth();
        int score = player.getScore();
        int level = player.getLevel();
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        try{
           g.setColor(new Color(255-HEALTH*2,HEALTH*2,0)); 
        }catch(Exception e){
            g.setColor(Color.RED);
        }
        
        g.fillRect(15, 15, HEALTH *2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        
        g.drawString(""+score, 300, 20);
        g.drawString(""+level, 400, 20);
      }

    
}
