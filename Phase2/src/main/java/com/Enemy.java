package com.phase2;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Enemy object implemented by extending Entities.
 * Created enemy will be responsible for chasing
 * the player in the game.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class Enemy extends Entities{
    
    /**
     * 
     * enemy constuctor
     * 
     * @param gp
     */
    public Enemy(GamePanel gp){

        this.gp = gp;
        direction = "left";
        solidArea = new Rectangle();
        getEnemyImage();
        walkingPath = true;

    }

    /**
     * sets the default potion, speed, and solid area of enemy
     */
    public void set_default_position(){
        this.mapX = 48 * 13;
        this.mapY = 48 * 6;
        this.speed = 1;
        solidArea.x = 7;
        solidArea.y = 15;
        solidArea.width = 33;
        solidArea.height = 19;
    } 

    /**
     * try catch statment that loads the enemy sprites
     */
    public void getEnemyImage(){
        try{
            left1 = ImageIO.read(getClass().getResource("sprites/shark/Shark_L.png"));
            right1 = ImageIO.read(getClass().getResource("sprites/shark/Shark_R.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * checks if the enemy is still walking the shortest path,
     * if yes it yes it updates the players location the calls
     * find path again with the updated location
     */
    public void movement(){
        if(walkingPath == true){
            int mainCharRow = (gp.player.mapY + gp.player.solidArea.y)/(gp.individualTileSize);
            int mainCharCol = (gp.player.mapX + gp.player.solidArea.x)/(gp.individualTileSize);
            findPath(mainCharCol, mainCharRow);
        }
    }
}
