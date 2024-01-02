package com.phase2;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

/**
 * General class declaring entity atributes to be used as a parent class
 * for playable/non playable characters in the game.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class Entities {
    //general position and speed atribitues for all entities
    public int mapX, mapY;
    public int speed;

    //add sprite buffered images
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int SpriteCounter = 0;
    public int SpriteNum = 1;

    // Collision
    public Rectangle solidArea;
    public int defaultsolidAreaX, defaultsolidAreaY;
    public boolean collisionOn = false;

    //add character oxygen level
    public int currentOxygenLevel;
    public int maxOxygenLevel;

    //set ocunter for enemy movement
    public int movementCounter = 0;

    //path finder boolean
    public boolean walkingPath;

    public GamePanel gp;


    /**
     * uses the collision checkers in the collision file to check the
     * entities collision with objects, players and tiles
     */
    public void checkCollision(){
        collisionOn = false;
        gp.characterChecker.checkTile(this);
        gp.characterChecker.checkObject(this, false);
        gp.characterChecker.checkEntity(this, gp.enemy);
        gp.characterChecker.checkPlayer(this);
    }

    /**
     * 
     * This method changes the direction of the current entity based on
     * the direction it has to go towards to follow the A* shortest path
     * algorithm.
     * 
     * @param goalCol
     * @param goalRow
     */
    public void findPath(int goalCol, int goalRow){
        // get the sarting location of entity 
        int initialCol = (mapX + solidArea.x)/gp.individualTileSize;
        int initialRow = (mapY + solidArea.y)/gp.individualTileSize;

        //set the mesh based on the new starting location and input goal location
        gp.pathFinder.setMovementMesh(initialCol, initialRow, goalCol, goalRow);

        if(gp.pathFinder.AStarSearch() == true){

            //find the next position entity has to go to 
            int nextCellx = gp.pathFinder.list2.get(0).col * gp.individualTileSize;
            int nextCelly = gp.pathFinder.list2.get(0).row * gp.individualTileSize;

            //find the areas around the entity using solidarea
            int entityLX = mapX + solidArea.x;
            int entityTY = mapY + solidArea.y;
            int entityRX = mapX + solidArea.x + solidArea.width;
            int entityBY = mapY + solidArea.y + solidArea.height;

            //the following if statement will determing the direction the
            //entity has to move to base no the path from AStar search
            //they compare the location of the next call and the spaces around the entity 
            //to decide which direction to make the entity face

            if(entityTY > nextCelly && entityLX  >= nextCellx && entityRX < nextCellx + gp.individualTileSize){
                direction = "up";
            }
            else if(entityTY < nextCelly && entityLX >= nextCellx && entityRX < nextCellx + gp.individualTileSize){
                direction = "down";
            }
            else if(entityTY >= nextCelly && entityBY < nextCelly + gp.individualTileSize){
                //this means entitiy can go left or right
                if(entityLX > nextCellx){
                    direction = "left";
                }
                if(entityLX < nextCellx){
                    direction = "right";
                }
            }
            //the following will cover the expection situatoins
            //these are when there is an object in the path so we have to go around
            else if(entityTY > nextCelly && entityLX > nextCellx){
                //up or left because below and on its right
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(entityTY > nextCelly && entityLX < nextCellx){
                //up or right because below and on its left
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(entityTY < nextCelly && entityLX > nextCellx){
                //down or left because above and on its right
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(entityTY < nextCelly && entityLX < nextCellx){
                //down or right because above and on its left
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }

        }
    }

    /**
     * empty method to be overwritten
     */
    public void set_default_position(){}

    /**
     * empty method to be overwritten
     */
    public void movement(){
    }

    /**
     * the update method called movement which calls findPath above
     * updating the direction the entitiy is facing, then moves the entitiy
     * in that direction
     */
    public void update(){
        movement();
        checkCollision(); 
        if (collisionOn == true){
            switch(direction){
                case "up":
                    mapY -= speed;
                    break;
                case "down":
                    mapY += speed;
                    break;
                case "left":
                    mapX -= speed;
                    break;
                case "right":
                    mapX += speed;
                    break;
            }
        }
    }

    /**
     * 
     * this method draws the correct sprite image based on the direction it facing
     * 
     * @param g2
     */
    public void draw(Graphics2D g2){

        BufferedImage img = null;

        if(direction == "up"){
            img = left1;
        }
        else if(direction == "down"){
            img = left1;
        }
        else if(direction == "left"){
            img = left1;
        }
        else if(direction == "right"){
            img = right1;
        }

        g2.drawImage(img, mapX, mapY, gp.individualTileSize, gp.individualTileSize, null);

    }

}
