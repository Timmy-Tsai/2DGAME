package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Punishment class represents a punishment in the game, extending the SuperObject class.
 * It inherits properties such as name, image, collision status, and position from SuperObject.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */


public class Punishment extends SuperObject{
    GamePanel gp;
        /**
        * Constructs a new Punishment object and loads the punishment image.
        */
        public Punishment () {
        name = "Urchin";
        try {
            image = ImageIO.read(getClass().getResource("sprites/Objects/urchin.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}