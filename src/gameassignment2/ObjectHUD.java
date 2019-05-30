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
 * @author vusalis
 */
public class ObjectHUD extends GameObject{
    private GameObject gameObject;

    public ObjectHUD(GameObject gameObject) {
        super(gameObject.x, gameObject.y - 10, ID.Hud);
        this.gameObject = gameObject;
    }
    
    
    public void tick() {
         }

  
    public void render(Graphics g) {
        int HEALTH = gameObject.getHealth();
        g.setColor(Color.gray);
        g.fillRect(x, y , 50, 16);
        g.setColor(new Color(255,155,0));
        g.fillRect(x, y, HEALTH *2, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 16);
    }
}
