package com.phase2;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

/**
 * The central class representing the game panel where the game is displayed.
 * Extends JPanel and implements Runnable
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class GamePanel extends JPanel implements Runnable{
    
    //create the games screen settings

    // each tile is 48x48 pixels
    public int individualTileSize = 48;
    // 16x12 game tiles
    int numColTiles = 16;
    int numRowTiles = 13;
    // screen size
    int screenWidth = numColTiles * individualTileSize;
    int screenHight = numRowTiles * individualTileSize;

    // Get Fps 
    int FPS = 60;

    //bounds for enemy player collision checking
    int playerXUpper;
    int playerXLower;
    int playerYUpper;
    int playerYLower;

    //Creates the cellCollection class to handle cells and textures
    Board gameBoard = new Board(this);
    //Collision Check for each tile
    public Collision checker = new Collision(this);
    // Create keyH
    KeyHandler keyH = new KeyHandler(this);
    //create a gamethread attribute
    Thread gameThread;
    //create a new main character
    MainCharacter player = new MainCharacter(this, keyH, playerXUpper, playerXLower, playerYUpper, playerYLower);
    //create an enemy array
    public Entities enemy[] = new Entities[1];

    //create UI
    public Ui ui = new Ui(this);

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public RewardSetter aSetter = new RewardSetter(this);
    public Collision characterChecker = new Collision(this);
    public PunishmentSetter aSetter2 = new PunishmentSetter(this);
    // Array that holds all the objects placed on the map
    public SuperObject obj[] = new SuperObject[20];

    //Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int winState = 3;
    public final int loseState = 4;

    //create a A star path finder
    public AStar pathFinder = new AStar(this);

    /**
     * Constructs a new GamePanel with default settings.
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHight));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Sets up the initial game state, including rewards, punishments, enemies, and sets the game state to the title screen.
     */
    public void setupGame() {
        aSetter.setReward();
        aSetter2.setPunishment();
        aSetter2.setEnemy();
        gameState = titleState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Main game loop responsible for updating game information and rendering at a constant frame rate.
     */
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime; 

            if (delta >= 1){
                //1. Update information such as character position 
                update();
                //2. Draw the screen with updated information
                repaint();
                delta--;
            }
        }                
    }

    /**
     * Updates game-related information: player and Enemy positions.
     */
    public void update(){
        playerXUpper = player.mapX + 24;
        playerXLower = player.mapX - 24;
        playerYUpper = player.mapY + 24;
        playerYLower = player.mapY - 24;
        if(gameState == playState){
            if (enemy[0].mapX >= playerXLower && enemy[0].mapX <= playerXUpper && enemy[0].mapY >= playerYLower && enemy[0].mapY <= playerYUpper){
                gameState = loseState;
                return;
            }
            player.update();
            //update enemy 
            enemy[0].update();
            repaint();
        }
        if(gameState == pauseState){
            //Nothing updates because pause
        }
    }

    /**
     * Paints the game components, including the board, characters, and UI.
     *
     * @param g The Graphics object to paint on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
         // Create Title Screen
        if (gameState == titleState){
            ui.draw(g2);
        }
        //Others
        else{
            // Create the main character
            gameBoard.generateBoard(1, 0, g2);

            // Draw a semi-transparent blue rectangle over the entire board
            g2.setColor(new Color(0, 0, 255, 100)); // Blue color with alpha (transparency)
            g2.setComposite(AlphaComposite.SrcOver);
            g2.fillRect(0, 0, screenWidth, screenHight);

            // Draw objects and characters on top of the blue tint
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this, i);
                }
            }
            player.draw(g2);
            //Draw enemy
            enemy[0].draw(g2);
            // Draw UI, keep it after drawing the rest since it needs to be on top
            ui.draw(g2);
         }
        g2.dispose();
    }
}