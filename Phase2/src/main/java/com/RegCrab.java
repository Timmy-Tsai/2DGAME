package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The RegCrab class represents a regular crab reward in the game, extending the SuperObject class.
 * It inherits properties such as name, image, collision status, and position from SuperObject.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class RegCrab extends SuperObject{
    GamePanel gp;
        /**
        * Constructs a new RegCrab object and loads the crab image.
        */
        public RegCrab () {
        name = "Crab";
        try {
            image = ImageIO.read(getClass().getResource("sprites/rewards/crab.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
