/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Vusal
 */
public class IntermediateEnemy extends GameObject{
    private final int WIDTH = 50, HEIGHT = 50;
    private Handler handler;
    private int velX = 3;
    private boolean isJumping = false;
    private boolean isFalling = true;
    private int gravity = 1;
    private int isFacing = 10;
    private boolean isNear = false;
    private int playerX;
    private boolean right = true;
    Texture tex = Game.tex;
    private Animation playerWalkRight;
    private Animation playerWalkLeft;

    public IntermediateEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        health = 30;
        playerWalkLeft = new Animation(2, tex.intermediateEnemyLeft);
        playerWalkRight = new Animation(2, tex.intermediateEnemyRight);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x , y, WIDTH, HEIGHT);
    }

    public Rectangle getTopBounds(){
        return new Rectangle(x + (WIDTH/2) - (WIDTH/4), y, WIDTH/2, HEIGHT/2);
    }
    
    public Rectangle getRightBounds(){
        return new Rectangle(x+ WIDTH -3, y+5, 5, HEIGHT -20);
    }
    
    public Rectangle getLeftBounds(){
        return new Rectangle(x-2, y+5, 5, HEIGHT-20);
    }
    
    @Override
    public void tick() {
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
        for(GameObject tmpObj : handler.object){
            if(tmpObj.getId() == ID.Block){
                if(getRightBounds().intersects(tmpObj.getBounds())){
                    x = tmpObj.getX() - WIDTH;
                    jump();
                }else if(getLeftBounds().intersects(tmpObj.getBounds())){
                    x = tmpObj.getX() + 34;
                    jump();
                }else if(getBounds().intersects(tmpObj.getBounds())){
                    y = tmpObj.getY() - HEIGHT;
                    velY = 0;
                    isFalling = false;
                    isJumping = false;
                }else{
                    isFalling = true;
                }
                if(getTopBounds().intersects(tmpObj.getBounds())){
                    y = tmpObj.getY() + 32;
                    velY = 0;
                }
            }
            if(tmpObj.getId() == ID.Player){
                if((int)Math.sqrt((x-tmpObj.getX())*(x-tmpObj.getX()) + (y-tmpObj.getY())*(y-tmpObj.getY())) < 300){
                    isNear = true;
                }
                playerX = tmpObj.getX();
                break;
            }
        }
        if(isNear){
            if(isFalling || isJumping) velY+=gravity;
             if(velY>=10){
                velY = 9;
            }
            if(velX != 0) isFacing = playerX - x > 0 ? 1 : -1;
            y += velY;
            if(playerX - x >0) {
                right = true;
                x += velX;
            }
            else if(playerX- x <0) {
                right = false;
                x -= velX;
            }
            
        }
    }
    
    private void jump(){
        if(!isJumping){
            velY = -10;
            isFalling = true;
            isJumping = true;
        }
    }

    @Override
    public void render(Graphics g) {
        if(right){
            playerWalkRight.drawAnimation(g, x, y, WIDTH, HEIGHT);
        }else{
            playerWalkLeft.drawAnimation(g, x, y, WIDTH, HEIGHT);
        }
        
    }
}
