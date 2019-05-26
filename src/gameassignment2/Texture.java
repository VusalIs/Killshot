/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.image.BufferedImage;

/**
 *
 * @author Vusal
 */
public class Texture {
    
    
    public BufferedImage block = null;
    public BufferedImage playerIsJumpingRight = null;
    public BufferedImage playerIsJumpingLeft = null;
    public BufferedImage playerIsFalling = null;
    public BufferedImage[] playerIdle = null;
    public BufferedImage[] playerRunRight = null;
    public BufferedImage[] playerRunLeft = null;
    public BufferedImage[] enemyRunLeft = null;
    public BufferedImage[] enemyRunRight = null;
    public BufferedImage[] cherryImages = null;
    
    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            setTextures(loader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void setTextures(BufferedImageLoader loader){
        block = loader.loadImage("/big-crate.png");
        playerIsJumpingRight = loader.loadImage("/player-jump-1.png");
        playerIsJumpingLeft = loader.loadImage("/player-jump-left-1.png");
        playerIsFalling = loader.loadImage("/player-jump-2.png");
        
        cherryImages = setImageArray("/cherry-", 7, loader);
        enemyRunLeft = setImageArray("/opossum-", 6, loader);
        enemyRunRight = setImageArray("/opossum-right-", 6, loader);
        playerIdle = setImageArray("/player-idle-", 4, loader);
        playerRunRight = setImageArray("/player-run-", 6, loader);
        playerRunLeft = setImageArray("/player-run-left-", 6, loader);
    }
    
    private BufferedImage[] setImageArray(String mainPath, int count, BufferedImageLoader loader){
        BufferedImage[] temp = new BufferedImage[count];
        for(int i = 0; i < count; i ++){
            temp[i] = loader.loadImage(mainPath+(i+1)+".png");
        }
        return temp;
    }
    
}
