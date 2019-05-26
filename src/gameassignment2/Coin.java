package gameassignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * GameObject Example for Coin.
 * @author Sayid Akhundov
 */
public class Coin extends GameObject{
      
 public Coin(int x, int y, ID id) {
        super(x, y, id);
        
        velX =3;
        velY =3;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if( y >= Game.HEIGHT-40 || y <= 0 ) velY *= -1; 
        if( x >= Game.WIDTH-16 || x <= 0) velX *= -1; 
    }

    @Override
    public void render(Graphics g) {
       if (id == ID.Player)g.setColor(Color.red);
       else g.setColor(Color.yellow);
       g.fillOval(x, y, 16, 16);
       
    }

    @Override
    public Rectangle getBounds() {
      return new Rectangle (x,y,16,16);
    }
    
}
