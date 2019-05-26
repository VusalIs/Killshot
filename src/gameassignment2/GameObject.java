package gameassignment2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * An abstract class for realization of our Game Objects.
 * @author Sayid Akhundov
 */
public abstract class GameObject implements Serializable {
    protected int x,y ;
    protected ID id;
    protected int velX,velY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }
    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public ID getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public abstract Rectangle getBounds();
    public abstract void tick();
    public abstract void render(Graphics g);
}
