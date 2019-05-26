/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Vusal
 */
public class Animation {
    
    private int speed;
    private int frames;
    
    private int index = 0;
    private int count = 0;
    
    private BufferedImage[] images;
    private BufferedImage currentImg;
    
    public Animation(int speed, BufferedImage... args){
        this.speed= speed;
        images = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i++){
            images[i] = args[i];
        }
        frames =args.length;
    }
    
    public void runAnimation(){
        index++;
        if(index>speed){
            index = 0;
            nextFrame();
        }
    }
    
    private void nextFrame(){
        for(int i = 0; i< frames; i++){
            if(count == i)
                currentImg = images[i];
        }
        
        count++;
        
        if(count>frames)
            count = 0;
    }
    
    public void drawAnimation(Graphics g, int x, int y,int width, int height){
        g.drawImage(currentImg, x, y, width, height, null);
    }
    
}
