package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Oxygen class is responsible to create the oxygen object in the game
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class Oxygen extends SuperObject {
    /**
     * Constructs a new Oxygen object.
     * Sets the object name and loads the corresponding image.
     */
    public Oxygen(){
        name = "Oxygen";
        try{
            image = ImageIO.read(getClass().getResource("sprites/Objects/Oxygen.png"));
        }  
        catch(IOException e){
            e.printStackTrace();
        }
    }
}