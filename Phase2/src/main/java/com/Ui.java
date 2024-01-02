package com.phase2;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * The Ui class is responsible for drawing out all the required items and instruction 
 * on the title screen and in the game
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class Ui {
    
    //Initialize
    GamePanel gp;
    Graphics2D g2;
    Font score_font, pause_font;
    public int Control_Num = 0;
    BufferedImage oxygenFull, oxygenHalf, oxygenEmpty, barrel, crab, fish, clam;

    /**
     * Construct Oxygen Ui and barrel image 
     * @param gp gamePanel for the game
     */
    public Ui(GamePanel gp){
        this.gp = gp;
        //Create font for score and pause
        score_font = new Font("Arial", Font.PLAIN, 40);
        pause_font = new Font("Arial", Font.BOLD, 50);  

        //call create object methods
        createOxygenObjects(gp);
        createBarrelObjects(gp);
        createRewardObjects(gp);
    }

    /**
     * 
     * method that creates the objects and images for the oxygen bubbles
     * 
     * @param gp
     */
    public void createOxygenObjects(GamePanel gp){
        //create oxygen objects
        SuperObject oxygenBubble = new OxygenLevel(gp);
        oxygenFull = oxygenBubble.image;
        oxygenHalf = oxygenBubble.image2;
        oxygenEmpty = oxygenBubble.image3;
    }

    /**
     * 
     * method that creates the objects and images for the score barrel
     * 
     * @param gp
     */
    public void createBarrelObjects(GamePanel gp){
        //create barrel
        SuperObject scoreBarrel = new Barrel(gp);
        barrel = scoreBarrel.image4;
    }

    /**
     * 
     * method that creates the objects and images for the rewards
     * 
     * @param gp
     */
    public void createRewardObjects(GamePanel gp){
        //create all rewards 
        SuperObject RegCrab = new RegCrab();
        crab = RegCrab.image5;
        SuperObject RegFish = new RegFish();
        fish = RegFish.image6;
        SuperObject BonusClam = new BonusClam();
        clam = BonusClam.image7;
    }

    /**
     * Draws Title, Game, Pause, Win, and Lose states.
     * @param g2 The Graphics2D object
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(pause_font);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        } else if (gp.gameState == gp.playState) {
            drawPlayState();
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        } else if (gp.gameState == gp.winState) {
            drawWinScreen();
        } else if (gp.gameState == gp.loseState) {
            drawLoseScreen();
        }
    }

    private void drawPlayState() {
        String scoreText = String.valueOf(gp.player.score);

        // Draw the score, barrel and oxygen
        g2.drawImage(barrel, gp.individualTileSize * 15, 5, gp.individualTileSize, gp.individualTileSize, null);
        g2.setFont(score_font);
        g2.setColor(Color.WHITE);
        g2.drawString(scoreText, 733, 42);
        drawOxygenLevel(g2);
    }


    /**
     * Method draws the pause screen and instructions with a gray rectangle background 
     */
    public void drawPauseScreen() {
        String[] texts = {"GAME PAUSED", "Press on R to Restart", "Press on P to Continue"};
    
        int x = getCenteredX(texts[0]);
        int y = gp.screenHight / 2;
    
        // Draw a light gray rectangle behind the instruction words
        int rectangleWidth = 640;
        int rectangleHeight = 280;
        int rectangleX = (x - rectangleWidth / 2) + 190;
        int rectangleY = y - 90;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
    
        // Draw instructions using for loop
        g2.setColor(Color.BLACK);
        for (int i = 0; i < texts.length; i++) {
            g2.drawString(texts[i], (i == 0) ? x : x - 70, y + 70 * i);
        }
    }

    /**
     * Method draws the win screen and instructions with a yellow rectangle background 
     */
    public void drawWinScreen(){
        String text = "YOU WIN!!!";
        String text2 = "Press on R to Play Again!";
        String text3 = "Press on Q to Quit";
        int x = getCenteredX(text);
        int y = gp.screenHight/2;

        // Draw a light yellow rectangle behind the instruction words
        int rectangleWidth = 620;
        int rectangleHeight = 280;
        int rectangleX = (x - rectangleWidth / 2) + 140 ;
        int rectangleY = y - 90;
        g2.setColor(new Color(255, 255, 153)); // light yellow
        g2.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);

        //Draw instruction
        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);
        g2.drawString(text2, x - 160, y + 70);
        g2.drawString(text3,x - 80 , y + 140);
    }

    /**
     * Method draws the lose screen and instructions with a light red rectangle background 
     */
    public void drawLoseScreen(){
        String text = "YOU LOST!";
        String text2 = "Press on R to Restart";
        String text3 = "Press on Q to Quit";
        int x = getCenteredX(text);
        int y = gp.screenHight/2;
    
        // Draw a red rectangle behind the instruction words
        int rectangleWidth = 600;
        int rectangleHeight = 280;
        int rectangleX = (x - rectangleWidth / 2) + 140 ;
        int rectangleY = y - 90;
        g2.setColor(new Color(255, 51, 51)); // light red
        g2.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
    
        // Draw instructions
        g2.setColor(Color.BLACK);
        g2.drawString(text, x, y);
        g2.drawString(text2, x - 120, y + 70);
        g2.drawString(text3, x - 90, y + 140);
    }

    /**
     * Function returns the center x value for the text
     * @param text The text that we want to find center x
     * @return The text's center x
     */
    public int getCenteredX(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    /**
     * Method draws the title screen with game options with game instructions
     */
    public void drawTitleScreen(){
        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String text = "IT'S RAW!";
        int x = getCenteredX(text);
        int y = gp.individualTileSize*3;

        //Shadow for the word
        g2.setColor(Color.white);
        g2.drawString(text,x+2,y+2);
        //Word Color
        g2.setColor(Color.black);
        g2.drawString(text,x,y);

        //Draw 3 Gordon Ramsay picutres
        int numImages = 3; 
        int imageSize = gp.individualTileSize * 2;
        y += gp.individualTileSize*2; 

        //Use For Loop to reduce repetition 
        for (int i = 0; i < numImages; i++) {
            int xOffset = i * 200; 
            int currentX = (gp.screenWidth / 2 - imageSize / 2) - 200 + xOffset;
            g2.drawImage(gp.player.down1, currentX, y, imageSize, imageSize, null);
        }

        // Define x and y coordinates for 3 bubbles
        int[] bubbleX = {200, 400, 600};
        int[] bubbleY = {200, 200, 200};

        // Draw the bubbles on titlescreen
        for (int i = 0; i < 3; i++) {
            int B_x = bubbleX[i];
            int B_y = bubbleY[i];
            g2.drawImage(oxygenFull, B_x, B_y, gp.individualTileSize, gp.individualTileSize, null);
        }


        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "START COOKING!";
        x  = getCenteredX(text);
        y += gp.individualTileSize*3;
        g2.drawString(text,x,y);
        if (Control_Num == 0){
            g2.drawString("=>",x-gp.individualTileSize,y);
        }

        text = "QUIT";
        x  = getCenteredX(text);
        y += gp.individualTileSize;
        g2.drawString(text,x,y);
        if (Control_Num == 1){
            g2.drawString("=>",x-gp.individualTileSize,y);
        }

        String text1 = "Instruction:";
        String text2 = "Collect 5 points from crabs, fishes, clams";
        String text3 = "Urchins will damage you!";
        String text4 = "Take the Oxygen tanks before oxygen runs out";
        String text5 = "Run to the portal when you have enough points";
        String text6 = "Let's cook!";

        //Store texts into Arrays
        String[] texts = { text2, text3, text4, text5, text6};
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));

        //Draw game instruction
        int yOffset = 30;
        int new_y = y+30;
        g2.setColor(Color.RED);
        g2.drawString(text1,10,new_y);
        g2.setColor(Color.BLUE);

        //Use for loop to draw out all the text 
        for (int i = 0; i < texts.length; i++) {
            g2.drawString(texts[i], 10, new_y + yOffset * (i + 1));
        }
    }

    /**
     * Method draws the oxygenlevel Ui in the game
     */
    public void drawOxygenLevel(Graphics2D g2){
        //set variables
        int x = 5;
        int y = 0;
        int i = 0;

        //each hald oxygen bubble = 1 oxygen level
        //start by drawing the max oxygen level (3 oxygen bubblges)
        while (i < gp.player.maxOxygenLevel/2){
            g2.drawImage(oxygenEmpty, x, y, gp.individualTileSize, gp.individualTileSize, null);
            i++;
            x += gp.individualTileSize;
        }
        //rest variables
        x = 5;
        i = 0;

        //then draw the current oxygen level
        while(i < gp.player.currentOxygenLevel){
            g2.drawImage(oxygenHalf, x, y, gp.individualTileSize, gp.individualTileSize, null);
            i++;
            if(i < gp.player.currentOxygenLevel){
                g2.drawImage(oxygenFull, x, y, gp.individualTileSize, gp.individualTileSize, null);
            }
            i++;
            x += gp.individualTileSize;
        }
    }
}

        




