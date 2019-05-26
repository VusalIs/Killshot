/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameassignment2;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author VusalIs
 */
public class BufferedImageLoader {
    private BufferedImage bufferedImage;

    public BufferedImage loadImage(String path){
        try {
            bufferedImage = ImageIO.read(getClass().getResource(path));
        }catch (Exception e){
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
