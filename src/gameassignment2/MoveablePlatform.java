/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Vusal
 */
public class MoveablePlatform extends GameObject{
    ArrayList<Block> blockList;
    private int velX = 2;
    private int count = 0;

    public MoveablePlatform(int x, int y, ID id) {
        super(x, y, id);
        blockList = new ArrayList<>();
    }
    
    public void addNewBlock(Block block){
        blockList.add(block);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y);
    }

    @Override
    public void tick() {
        if(count>150){
            count = 0;
            velX *= (-1);
        }
        
        for(Block block: blockList){
            block.setX(block.getX() + velX);
        }
        
        count++;
    }

    @Override
    public void render(Graphics g) {
        for(Block block: blockList){
            block.render(g);
        }
    }
    
}
