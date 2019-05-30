/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Vusal
 */
public class Bullet extends GameObject{
    private int isFacing;
    private Handler handler;
    private boolean isHitted = false;
    Texture tex = Game.tex;
    public Bullet(int x, int y, ID id, int isFacing, Handler handler,int velX) {
        super(x, y, id);
        this.handler = handler;
        this.isFacing = isFacing;
        this.velX = velX;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        for(GameObject tmpObj : handler.object){
            if((tmpObj.getId() != ID.Bullet && tmpObj.getId() != ID.Block) && tmpObj.getBounds().intersects(getBounds())){
                tmpObj.setHealth(tmpObj.getHealth() - 10);
                isHitted = true;
            }
        }
        if(isHitted){
            handler.removeObject(this);
        }
        
        x += velX*isFacing;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.fire, x, y, 24,24, null);
    }

    public boolean isHitted() {
        return isHitted;
    }

    public void setIsHitted(boolean isHitted) {
        this.isHitted = isHitted;
    }
    
    
    
}
