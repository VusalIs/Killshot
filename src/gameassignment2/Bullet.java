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
    public Bullet(int x, int y, ID id, int isFacing, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.isFacing = isFacing;
        velX = 5;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        Bullet tmp = null;
        for(GameObject tmpObj : handler.object){
            if(tmpObj.getId() != ID.Bullet && tmpObj.getBounds().intersects(getBounds())){
                tmpObj.setHealth(tmpObj.getHealth() - 10);
                isHitted = true;
            }
            if(tmpObj.getId() == ID.Bullet){
                tmp = (Bullet) tmpObj;
            }
        }
        if(tmp != null && tmp.isHitted){
            handler.removeObject(tmp);
        }
        x += velX*isFacing;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 16, 16);
    }

    public boolean isHitted() {
        return isHitted;
    }

    public void setIsHitted(boolean isHitted) {
        this.isHitted = isHitted;
    }
    
    
    
}
