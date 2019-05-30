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
    private final int WIDTH = 50, HEIGHT = 80;
    private Handler handler;
    private int velX = 3;
    private boolean isJumping = false;
    private boolean isFalling = true;
    private int gravity = 1;
    private int isFacing = 10;
    private boolean isNear = false;
    private int playerX;
    

    public IntermediateEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        health = 30;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x , y, WIDTH, HEIGHT);
    }

    public Rectangle getTopBounds(){
        return new Rectangle(x + (WIDTH/2) - (WIDTH/4), y, WIDTH/2, HEIGHT/2);
    }
    
    public Rectangle getRightBounds(){
        return new Rectangle(x+ WIDTH -3, y+5, 5, HEIGHT -10);
    }
    
    public Rectangle getLeftBounds(){
        return new Rectangle(x-2, y+5, 5, HEIGHT-10);
    }
    
    @Override
    public void tick() {
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
        System.out.println(velY);
        if(isNear){
            if(isFalling || isJumping) velY+=gravity;
            if(velX != 0) isFacing = playerX - x > 0 ? 1 : -1;
            y += velY;
            if(playerX - x >0) x += velX;
            else if(playerX- x <0) x -= velX;
            
        }
        
        x = Game.clamp(x, 0, Game.WIDTH - (WIDTH+5));
        y = Game.clamp(y, 0, Game.HEIGHT - (HEIGHT+18));
    }
    
    private void jump(){
        System.out.println(isJumping);
        if(!isJumping){
            velY = -10;
            isFalling = true;
            isJumping = true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, WIDTH, HEIGHT);
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.blue);
        g2d.draw(getBounds());
        g2d.draw(getLeftBounds());
        g2d.draw(getRightBounds());
        g2d.draw(getTopBounds());
    }
    
    public static int clamp(int number,int min,int max){
        if (number >= max)return number = max;
        else if (number <= min) return number = min;
        return number;
    }
}
