package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents the Oxygen Level object in the game.
 * Extends SuperObject to inherit common attributes for game objects.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class OxygenLevel extends SuperObject{

    GamePanel gp;
    
    /**
     * Constructs a new OxygenLevel object with the specified GamePanel instance.
     *
     * @param gp The GamePanel instance to associate with the OxygenLevel.
     */
    public OxygenLevel(GamePanel gp){

        this.gp = gp;

        name = "Oxygen Level";
        try{
            image = ImageIO.read(getClass().getResource("sprites/OxygenLevel/Oxygen_full.png"));
            image2 = ImageIO.read(getClass().getResource("sprites/OxygenLevel/Oxygen_half.png"));
            image3 = ImageIO.read(getClass().getResource("sprites/OxygenLevel/Oxygen_empty.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    } 
}
