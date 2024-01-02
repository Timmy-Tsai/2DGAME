package com.phase2;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The BonusClam class represents a regular clam reward in the game, extending the SuperObject class.
 * It inherits properties such as name, image, collision status, and position from SuperObject.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class BonusClam extends SuperObject{
    GamePanel gp;
        /**
        * Constructs a new BonusClam object and loads the clam image.
        */
        public BonusClam () {
        name = "Clam";
        try {
            image = ImageIO.read(getClass().getResource("sprites/rewards/clam.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
