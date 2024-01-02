package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The RegFish class represents a regular fish reward in the game, extending the SuperObject class.
 * It inherits properties such as name, image, collision status, and position from SuperObject.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class RegFish extends SuperObject{
    GamePanel gp;

        /**
        * Constructs a new RegFish object and loads the fish image.
        */
        public RegFish () {
        name = "Fish";
        try {
            image = ImageIO.read(getClass().getResource("sprites/rewards/fish.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
