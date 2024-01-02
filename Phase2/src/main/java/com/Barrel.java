package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Object used to store and manage the barrel graphic background 
 * of the score counter. Object is implemented to extend SuperObject.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class Barrel extends SuperObject{
    
    //stores given instance of GamePanel object
    GamePanel gp;

    /**
     * Constructor of a Barrel object that holds a graphic
     * used as a background of the score board.
     * 
     * @param gp grabs and stores the given instance of a
     *        GamePanel object for access.
     */
    public Barrel(GamePanel gp){

        this.gp = gp; //sets instance of GamePanel

        name = "Barrel"; //Setting name
        
        //try catch used to retrieve the barrel graphic
        try{
             image4 = ImageIO.read(getClass().getResource("sprites/Objects/Score_Barrel.png"));
    
        }catch(IOException e){
            e.printStackTrace();
        }
    } 
}
