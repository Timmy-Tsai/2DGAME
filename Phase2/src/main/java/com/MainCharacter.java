package com.phase2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.RescaleOp;

/**
 * The MainCharacter class represents the main character in the game. It extends the Entities class and contains
 * attributes and methods specific to the main character's behavior, movement, and interactions.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class MainCharacter extends Entities{
    
    //attributes 
    //GamePanel gp;
    KeyHandler keyH;
    public int score;
    int hasReward = 0;
    int oxygen_timer = 0;

    private int playerXUpper;
    private int playerXLower;
    private int playerYUpper;
    private int playerYLower;

    /**
     * Constructs a new MainCharacter instance.
     *
     * @param gp      The GamePanel to which the character is.
     * @param handler The KeyHandler for handling keyboard input.
     * @param playerXUpper The X range upper limit for collision
     * @param playerXLower The X range lower limit for collision
     * @param playerYUpper The Y range upper limit for collision
     * @param playerYLower The Y range lower limit for collision
     *
     */
    public MainCharacter(GamePanel gp, KeyHandler handler, int playerXUpper, int playerXLower, int playerYUpper, int playerYLower) {
        this.gp = gp;
        this.keyH = handler;
        solidArea = new Rectangle(); // Initialize solidArea
        setDefaultPosition();
        getMainCharImage();
        direction = "up";
        setSolidArea();
        setBoundries(playerXUpper, playerXLower, playerYUpper, playerYLower);
    }

    /**
     * method that sets the solid area aka hitbox of the main charac
     */
    public void setSolidArea(){
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32; // Set the width and height of the solidArea
        solidArea.height = 32;
        defaultsolidAreaX = solidArea.x;
        defaultsolidAreaY = solidArea.y;
    }

    /**
     * method that sets the main character sprite's boundries
     */
    public void setBoundries(int playerXUpper, int playerXLower, int playerYUpper, int playerYLower){
        this.playerXUpper = playerXUpper;
        this.playerXLower = playerXLower;
        this.playerYUpper = playerYUpper;
        this.playerYLower = playerYLower;
    }

     /**
     * Sets the default position, speed, score, and oxygen levels for the main character.
     */
    public void setDefaultPosition(){
        mapX = 48;
        mapY = 96;
        speed = 2;
        score = 0;
        maxOxygenLevel = 6;
        currentOxygenLevel = 6;
    }

     /**
     * Applies a blue tint to the given image.
     *
     * @param image The BufferedImage which the blue tint is applied.
     * @return The tinted BufferedImage.
     */
    public BufferedImage applyBlueTint(BufferedImage image) {
        float[] scales = { 1.0f, 1.0f, 1.2f }; // Increase the blue channel (the third element)
        float[] offsets = { 0.0f, 0.0f, 0.0f };
        RescaleOp op = new RescaleOp(scales, offsets, null);
        return op.filter(image, null);
    }

    /**
     * Loads the images for the main character's sprite in different directions and poses.
     */
    public void getMainCharImage(){
        try{
            up1 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_u1.png"));
            up2 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_u2.png"));
            down1 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_d1.png"));
            down2 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_d2.png"));
            left1 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_l1.png"));
            left2 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_l2.png"));
            right1 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_r1.png"));
            right2 = ImageIO.read(getClass().getResource("sprites/main_char_sprites/Ramsey_r2.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates the main character's state, including movement, collision checking, and interactions with rewards and objects.
     */
    public void update(){
        if(keyH.upPressed == true){
            direction = "up";
        }
        else if(keyH.downPressed == true ){
            direction = "down";
        }
        else if(keyH.leftPressed == true ){
            direction = "left";
        }
        else if(keyH.rightPressed == true ){
            direction = "right"; 
        }

        collisionOn = false;
        gp.characterChecker.checkTile(this);

        // Check object collision
        int objIndex = gp.characterChecker.checkObject(this, true);
        pickUpReward(objIndex);

        // Move the character based on the direction
        if(!this.collisionOn){
            switch(direction){
                case "up":
                    if(keyH.upPressed)
                        mapY -= speed;
                    break;
                case "down":
                    if(keyH.downPressed)
                        mapY += speed;
                    break;
                case "left":
                    if(keyH.leftPressed)
                        mapX -= speed;
                    break;
                case "right":
                    if(keyH.rightPressed)
                        mapX += speed;
                    break;
            }
        }

        SpriteCounter++;
        if (SpriteCounter > 10){
            if (SpriteNum == 1){
                SpriteNum = 2;
            }
            else if (SpriteNum == 2){
                SpriteNum = 1;
            }
            SpriteCounter = 0;
        }
        //update gets called 60 times a second
        //after testing we found that 250 updates are reasonable for our game
        if (oxygen_timer < 250){
            oxygen_timer ++;
        }
        else{
            //once we rached 250, decrease the oxygen level and then reset timer
            gp.player.currentOxygenLevel--;
            oxygen_timer = 0;
        }
    }

    /**
     * The pickUpReward method handles the interaction when the player's character picks up a reward or object in the game.
     * It processes the specified index in the game panel's object array to identify the type of reward and execute
     * corresponding actions, including updating the player's score, reward status, and handling oxygen levels.
     *
     * @param i The index representing the position of the object in the game panel's object array.
     *          If i equals 999, the method returns without further processing.
     */
    // method removes rewards and updates score count if player collides with rewards
    public void pickUpReward (int i) {
        if (i != 999) {
             // Retrieve the name of the reward/object at the specified index (i)
            String rewardName = gp.obj[i].name;
             // Switch statement for different reward types
            switch(rewardName) {
                case "Crab":
                    // Regular rewards increment score and hasReward
                    score++;
                    gp.obj[i] = null;
                    hasReward = hasReward += 1;
                    System.out.println("Score: " +hasReward);
                    break;
                case "Fish":
                    score++;
                    gp.obj[i] = null;
                    hasReward = hasReward += 1;
                    System.out.println("Score: " +hasReward);
                    break;
                // Bonus reward increments score by 2 and hasReward by 2
                 case "Clam":
                    score += 2;
                    // Remove object from game
                    gp.obj[i] = null;
                    hasReward = hasReward += 2;
                    System.out.println("Score: " +hasReward);
                    break;
                 // Punishment decreases score by 2 and hasReward by 2
                 case "Urchin":
                    score -= 2;
                    gp.obj[i] = null;
                    hasReward = hasReward -= 2;
                    System.out.println("You hit a punishment! Score: " +hasReward);
                    break;
                case "Oxygen":
                    if(currentOxygenLevel == 6){
                        System.out.println("Your oxygen is at max level!");
                        break;
                    }
                    else if(currentOxygenLevel == 5){
                        currentOxygenLevel += 1;
                        gp.obj[i] = null;
                        System.out.println("Current Oxygen level is: " + currentOxygenLevel);
                        break;
                    }
                    else{
                        currentOxygenLevel += 2;
                        gp.obj[i] = null;
                        System.out.println("Current Oxygen level is: " + currentOxygenLevel);
                        break;
                    }
                case "Empty Oxygen":
                    gp.obj[i] = null;
                    System.out.println("Oops you got a empty oxygen tank!");
                    break;

                case "Exit":
                    // Player must have x amount of rewards to obtain exit
                    if(hasReward >= 5) {
                        gp.obj[i] = null;
                        // Game done!
                        gp.gameState = gp.winState;
                    }
                    else {
                        System.out.println("You don't have enough rewards yet!");
                    }
                    break;
                case "Enemy":
                    if(gp.gameState == gp.playState){
                        if (gp.enemy[0].mapX >= playerXLower && gp.enemy[0].mapX <= playerXUpper && gp.enemy[0].mapY >= playerYLower && gp.enemy[0].mapY <= playerYUpper){
                            gp.gameState = gp.loseState;
                            return;
                        }
                    }
            }
        }
        // Check losing conditions and set the game state to loseState if necessary
        if (score < 0||currentOxygenLevel <= 0) {
            gp.gameState = gp.loseState;
        } 

    }


    /**
    * The draw method is responsible for rendering the character's sprite based on its current direction and sprite number.
    * It uses a Graphics2D object to draw the appropriate image on the screen.
    *
    * @param g2 The Graphics2D object used for rendering.
    */
    public void draw(Graphics2D g2){

        BufferedImage img = null;

        if(direction == "up"){
            if(SpriteNum == 1){
                img = up1;
            }
            if(SpriteNum == 2){
                img = up2;
            }
        }
        else if(direction == "down"){
            if(SpriteNum == 1){
                img = down1;
            }
            if(SpriteNum == 2){
                img = down2;
            }
        }
        else if(direction == "left"){
            if(SpriteNum == 1){
                img = left1;
            }
            if(SpriteNum == 2){
                img = left2;
            }
        }
        else if(direction == "right"){
            if(SpriteNum == 1){
                img = right1;
            }
            if(SpriteNum == 2){
                img = right2;
            }
        }

        g2.drawImage(img, mapX, mapY, gp.individualTileSize, gp.individualTileSize, null);

    }
}
