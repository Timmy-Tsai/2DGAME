package com.phase2;

import java.awt.image.BufferedImage;
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
public class Cell {
    //private variables to encapsulate in class and prevent unwanted access/modification
    private BufferedImage cellImage; //cell texture graphic
    private boolean collision = false; //boolean to indicate whether the cell has collision or not
    private int type; //numeric ID to represent each texture type. ID 0-2 is assigned to their respective texture types

    /**
     * Default constructor of a singular Cell object
     */
    public Cell(){
        this.cellImage = null;
    }

    /**
     * Constructor of a 48x48 pixel Cell object that sets collision
     * and texture type. Choices are given (temp 0-2) to select the 
     * desired cell texture. Sets the collision property to true or
     * false depending on the texture that was chosen.
     * 
     * @param choice integer value from 0-2 to select the texture
     *        type of the cell. (0 = Sand, 1 = Rock, 2 = Seaweed)
     */
    public Cell(int choice){
        try{
            switch (choice){
             case 0:
                    this.cellImage = ImageIO.read(getClass().getResource("sprites/cellTextures/blueSand2.png"));
                    this.type = 0;
                    this.collision = false;
                    break;
                case 1:
                    this.cellImage = ImageIO.read(getClass().getResource("sprites/cellTextures/Rock1.png"));
                    this.type = 1;
                    this.collision = true;
                    break;
                case 2:
                    this.cellImage = ImageIO.read(getClass().getResource("sprites/cellTextures/seaweed.png"));
                    this.type = 2;
                    this.collision = true;
                    break;
                default:
                    System.out.println("ERROR: Invalid Selection. Please Choose a Value from 0-2");
                    break;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * gets information of the current Cell object to 
     * indicate its type of cell/texture.
     * 
     * @return int value from 0-2 to indicate its type
     */
    public int getType(){
        return this.type;
    }

    /**
     * gets cell texture image
     * 
     * @return cell texture image
     */
    public BufferedImage getCellImage(){
        return this.cellImage;
    }
    
    /**
     * gets the collision status of the cell
     * 
     * @return a boolean (true/false) denoting
     *      the collision status of the block
     *      (true = yes collision
     *       false = no collsion)
     */
    public boolean collisionStatus(){
        return this.collision;
    }
}