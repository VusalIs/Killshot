package gameassignment2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Our best friend in Game which will handle updating and rendering all GameObjects existing in the game scene and bunch of other functions. 
 * @author Sayid Akhundov
 */
public class Handler implements Serializable{
    ArrayList<GameObject> object = new ArrayList();

    public Handler() {
    }
  
    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            if(tempObject.getHealth() < -10) removeObject(tempObject);
            if(tempObject != null) tempObject.tick();
        }
    }
    
    public void clearLevel(){
        object.clear();
    }
    
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
             if(tempObject != null)tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
