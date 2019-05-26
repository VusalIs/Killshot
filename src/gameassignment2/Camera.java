/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

/**
 *
 * @author VusalIs
 */
public class Camera {
    int x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void tick(GameObject player){
        x = player.getX() - Game.WIDTH/4;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
