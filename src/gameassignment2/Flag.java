/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Vusal
 */
public class Flag extends GameObject{
    
    private int width = 40, height = 40;
    private Animation cherry;
    private Texture tex = Game.tex;

    public Flag(int x, int y, ID id) {
        super(x, y, id);
        cherry = new Animation(10, tex.cherryImages);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        cherry.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        cherry.drawAnimation(g, x-3, y-5, width, height);
    }
    
}
