/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Vusal
 */
public class BossEnemy extends GameObject{
    private final int WIDTH = 200;
    private final int HEIGHT = 150;
    private final Handler handler;
    private final int gravity = 1;
    private boolean isJumping = true;
    private int milliseconds;
    
    public BossEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        health = 300;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void tick() {
        boolean previousValue = isJumping;
        for(GameObject tmpObj : this.handler.object){
            if(tmpObj.id == ID.Block){
                if(tmpObj.getBounds().intersects(getBounds())){
                    y = tmpObj.getY() - HEIGHT;
                    velY = 0;
                    isJumping = false;
                }
            }
            if(tmpObj.id == ID.Player){
                if(isJumping == false && previousValue != isJumping){
                    Player player = (Player) tmpObj;
                    if(player.isOnPlatform()){
                        tmpObj.setHealth(tmpObj.getHealth() - 40);
                    }
                }
            }
        }
        
        if(isJumping){
            velY += gravity;
        }
        
        if(milliseconds == 230){
            velY = -15;
            isJumping = true;
            milliseconds = 0;
        }
        milliseconds++;
        if(milliseconds == 200){
            handler.addObject(new Bullet(x - 32, y+ (int)(Math.random()*HEIGHT), ID.Bullet, -1, handler));
            handler.addObject(new Bullet(x - 32, y+ (int)(Math.random()*HEIGHT), ID.Bullet, -1, handler));
            
        }
        
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
}
