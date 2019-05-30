package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Realization of Player GameObject 
 * @author Sayid Akhundov
 */
public class Player extends GameObject {
    private Handler handler;
    private int HEALTH = 100;
    private int Score = 0;
    private int LEVEL =1;
    private boolean isJumping = true;
    private boolean isFalling = true;
    private int gravity = 1;
    private BufferedImageLoader loader;
    private int isFacing = 1;
    private boolean onPlatform = true;
    
    boolean[] keyPressed = new boolean[6];
    Texture tex = Game.tex;
    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerIdle;
    private BufferedImage nextLevel;
    
   
    private int width = 50; 
    private int height = 80; 
  
    
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        playerWalkRight = new Animation(2, tex.playerRunRight);
        playerWalkLeft = new Animation(2, tex.playerRunLeft);
        playerIdle = new Animation(10, tex.playerIdle);
        loader = new BufferedImageLoader();
        nextLevel = loader.loadImage("/level.png");
        
        keyPressed[0] = false;
        keyPressed[1] = false;
        keyPressed[2] = false;
        keyPressed[3] = false;
        keyPressed[4] = false;
        keyPressed[5] = false;
    
    }

    public int getScore() {
        return Score;
    }
    
    public int getHealth() {
        return HEALTH;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void setHealth(int HEALTH) {
        
        this.HEALTH = Game.clamp(HEALTH, 0, 100);
    }
    public void keyboard(){
        if(keyPressed[1]) setVelX(-10); else if (keyPressed[2])setVelX(10); else velX = 0;
//      if(keyPressed[3]) setVelY(-10); else if (keyPressed[4])setVelY(10);else velY = 0;
        if(keyPressed[0] && !isJumping){
            setVelY(-20);
            setIsFalling(true);
            setIsJumping(true);
            keyPressed[0] = false;
        }
        if(keyPressed[5]){
            this.handler.addObject(new Bullet(isFacing >0 ? x+45 : x - 25, y+48, ID.Bullet, isFacing, handler));
            keyPressed[5] = false;
        }
    }
    
    public  void hit(GameObject obj){
    
        if(obj.getId() == ID.Enemy){
            if(getBounds().intersects(obj.getBounds())){
                setHealth(getHealth()-1);
                
            }
        }
      
    }
    public  void eat(){
        setHealth(getHealth()+2);
    }

    public boolean isOnPlatform() {
        return onPlatform;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }
    
    
    
    @Override
    public void tick() {
        if(isJumping || isFalling){
            onPlatform = false;
        }
        
        if(velX != 0){
            isFacing = velX>0?1:-1;
        }
        if(getHealth() <=0){
            System.exit(0);
        }
        int tempY = y;
      
        keyboard();
        x += velX;
        y += velY;
        
        if(isFalling || isJumping) velY+=gravity;
        
        
        x = Game.clamp(x, 0, Game.WIDTH - (width+5));
        y = Game.clamp(y, 0, Game.HEIGHT - (height+18));
     
        
        collision();
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
        playerIdle.runAnimation();
    }
    
    private void collision() {
        for (int i = 0; i< handler.object.size(); i++){
            GameObject tmpObj = handler.object.get(i);
            
            if(tmpObj.getId() == ID.Cherry){
                if(getBounds().intersects(tmpObj.getBounds())){
                    handler.removeObject(tmpObj);
                    setScore(Score + 2);
                }
            }
            
            if(tmpObj.getId() == ID.Enemy){
                if(getBounds().intersects(tmpObj.getBounds()))
                    hit(tmpObj);
            }else if(tmpObj.getId() == ID.Block){
                if(getTopBounds().intersects(tmpObj.getBounds())){
                    y = tmpObj.getY() + 32;
                    velY = 0;
                }if(getBounds().intersects(tmpObj.getBounds())){
                    y = tmpObj.getY() - height;
                    velY = 0;
                    isFalling = false;
                    isJumping = false;
                }else{
                    isFalling = true;
                }
                    
                if(getRightBounds().intersects(tmpObj.getBounds())){
                    x = tmpObj.getX() - width;
                } if(getLeftBounds().intersects(tmpObj.getBounds())){
                    x = tmpObj.getX() + 32;
                }
            }
          
        }
    }

    public boolean isIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean isIsFalling() {
        return isFalling;
    }

    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }
    
    
   
    @Override
    public void render(Graphics g) {
        if(isJumping)
            if(velX<0)
                g.drawImage(tex.playerIsJumpingLeft, x, y,width, height, null);
            else
                g.drawImage(tex.playerIsJumpingRight, x, y,width, height, null);
        else{
            if(velX == 0)
                playerIdle.drawAnimation(g, x, y,width, height);
            else if(velX > 0)
                playerWalkRight.drawAnimation(g, x, y, width, height);
            else if(velX < 0)
                playerWalkLeft.drawAnimation(g, x, y, width, height);
        }
        
        
        
       
    }
    public Rectangle getBounds() {
      return new Rectangle(x + (width/2) - (width/4), y + (height/2), width/2, height/2);
    }
    
    public Rectangle getTopBounds(){
        return new Rectangle(x + (width/2) - (width/4), y, width/2, height/2);
    }
    
    public Rectangle getRightBounds(){
        return new Rectangle(x+ width -5, y+5, 5, height -10);
    }
    
    public Rectangle getLeftBounds(){
        return new Rectangle(x, y+5, 5, height-10);
    }

    int getLevel() {
      return LEVEL;
    }

    void setLevel(int level) {
     this.LEVEL =level;
    }

}
