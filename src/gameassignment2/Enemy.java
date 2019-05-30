package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import static javax.swing.Spring.height;

/**
 * GameObject Object Example for an Enemy.  
 * @author Sayid Akhundov
 */
public class Enemy extends GameObject {
    
    private Handler handler;
    private int width = 60;
    private int height = 30;
    private Animation enemyRunRight;
    private Animation enemyRunLeft;
    private Texture tex = Game.tex;
    
    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX =5;
        health = 10;
        enemyRunRight = new Animation(2, tex.enemyRunRight);
        enemyRunLeft = new Animation(2, tex.enemyRunLeft);
        
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        for(int i = 0; i < handler.object.size(); i++){
            if(getLeftBounds().intersects(handler.object.get(i).getBounds()) && handler.object.get(i).getId() == ID.Block){
                velX = 5;
            }else if(getRightBounds().intersects(handler.object.get(i).getBounds()) && handler.object.get(i).getId() == ID.Block){
                velX = -5;
            }
        }
        
        enemyRunLeft.runAnimation();
        enemyRunRight.runAnimation();
        
    }

    @Override
    public void render(Graphics g) {
       if(velX > 0)
           enemyRunRight.drawAnimation(g, x, y, width, height);
       else if(velX<0)
           enemyRunLeft.drawAnimation(g, x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
      return new Rectangle(x , y, width, height);
    }
    
    public Rectangle getRightBounds(){
        return new Rectangle(x+ width -5, y+5, 5, height -10);
    }
    
    public Rectangle getLeftBounds(){
        return new Rectangle(x, y+5, 5, height-10);
    }
    
}
