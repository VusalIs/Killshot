package gameassignment2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Game Engine  
 * @author Sayid Akhundov
 */
public class Game extends Canvas implements Runnable{

    public static final int WIDTH =  1200 , HEIGHT = 700; // Your game Canvas dimensions.
    private Window gameWindow;
    private Thread thread;
    private boolean running = false;
    private Random rand;
    private Handler handler;
    private HUD hud;
    private Player player;
    private BufferedImage level;
    private BufferedImage background;
    private Camera cam;
    public static boolean newLevel = false;
    private BufferedImage lastLevel;
    public static Texture tex;
    public static int countOfLevel = 1;

    private boolean mouseState = false;
   
     /*
    *You will not use this enum in our project, however its very common for game Menues .
    */
    private enum STATE {
        MENU,
        GAME,
        HELP,
        OPTIONS;
    };
    private STATE gameState = STATE.GAME;
    /**
     * @param args the command line arguments
     */
    
    public Game(){
        rand = new Random();
        tex = new Texture();
        resetLevel();
        
        gameWindow = new Window(WIDTH,HEIGHT,"Game assignment 2",this);
        
        
       
    }

    
    public static void main(String[] args) {
        new Game();
    }

    public synchronized void start() {
       thread = new Thread(this);
       thread.start();
       running =true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            running =false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void loadImageLevel(BufferedImage bufferedImage){
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        MoveablePlatform moveable = new MoveablePlatform(0, 0, ID.Block);
        handler.addObject(moveable);
        int xx =0,yy=0;
        for(int x = 0; x < w; x++){
            for(int y = 0; y < h; y++){
                int pixel = bufferedImage.getRGB(x,y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 255 & blue == 255) handler.addObject(new Block(x*32, y*32, ID.Block));
                else if(red == 250 && green == 100 && blue == 100){
                    Block tmp = new Block(x*32, y*32, ID.Block);
                    moveable.addNewBlock(tmp);
                    handler.addObject(tmp);
                }else if(red == 150 && green == 150 && blue == 150) {
                    GameObject tmp = new Enemy(x*32, y*32, ID.Enemy, handler);
                    handler.addObject(tmp);
                }
                else if(red == 10 && green == 150 && blue == 150) {
                    handler.addObject(new Cherry(x*32, y*32, ID.Cherry));
                }
                else if(red == 10 && green == 250 && blue == 150){
                    xx = x;
                    yy = y;
                }else if(red == 255 && green == 51 && blue == 153){
                    GameObject tmp = new BossEnemy(x*32, y*32, ID.Enemy, handler);
                    handler.addObject(tmp);
                }else if(red ==150 && green == 150 && blue ==250){
                    handler.addObject(new IntermediateEnemy(x*32, y*32, ID.Enemy, handler));
                }else if(red == 200 && green == 150 && blue == 50){
                    handler.addObject(new AdvancedEnemy(x*32, y*32, ID.Enemy, handler));
                }else if(red == 255 && green == 215 && blue == 10){
                    handler.addObject(new Flag(x*32, y*32, ID.Flag));
                }
                
            }
        }
        player = new Player(xx,yy,ID.Player,handler);
    }
    
    
    public void resetLevel(){
        handler = new Handler();
        cam = new Camera(0, 0);
        BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
        background = bufferedImageLoader.loadImage("/back.png");
        level = bufferedImageLoader.loadImage("/level" + countOfLevel + ".png");
        
        loadImageLevel(level);
        countOfLevel++;
        hud = new HUD(player);
        this.addKeyListener(new KeyInput(this,player));
        if(gameState == STATE.GAME){
            
            handler.addObject(player);
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;
    
        long timer = System.currentTimeMillis();
        int frames = 0;
    
        while(running){
            long now = System.nanoTime();
            delta +=(now - lastTime)/ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
                frames++;
            }
            if ((System.currentTimeMillis()- timer)>1000){
                timer += 1000;
              //  System.out.println("FPS :"+frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick() {
        if(newLevel){
            resetLevel();
            cam.setX(0);
            newLevel = false;
            return;
        }
      
        handler.tick();
        for(int i=0; i< handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player) cam.tick(handler.object.get(i));
        }
        
        if(gameState == STATE.GAME){
          
            hud.tick();
        
        }
       
       
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(background,0, 0, WIDTH, HEIGHT, null);
        Graphics2D g2D = (Graphics2D) g;
        g2D.translate(-cam.getX(), -cam.getY());
        
        handler.render(g);
        g2D.translate(cam.getX(), cam.getY());
        
        if(gameState == STATE.GAME){
            
            hud.render(g);
        }
        g.dispose();
        bs.show();
        
    }

    
}
