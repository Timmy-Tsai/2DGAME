package com.phase2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
/**
 * The ScoreTest class contains unit tests for scoring logic in the context of a game scenario involving
 * Main Character and different types of rewards and punishments. It tests the scoring mechanism
 * when the character picks up various objects, including regular crabs, regular fish, bonus clams, and punishments.
 * @author Raymond Chan
 * 
 */

public class ScoreTest {

    private GamePanel gamePanel;
    private MainCharacter mainCharacter;

    /**
     * Sets up the initial conditions for each test case by creating a new GamePanel
     * a new main character, and associating the character with the game panel.
     */
    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        mainCharacter = new MainCharacter(gamePanel, new KeyHandler(gamePanel), 0, 0, 0, 0);
        gamePanel.player = mainCharacter;
    }

    /**
     * Tests the scoring logic when the character picks up a regular crab object.
     * Verifies that the score increases by 1 after picking up a regular crab.
     */
    @Test
    public void testPickUpRegCrab() {
        // Setting up RegCrab object
        RegCrab regCrab = new RegCrab();
        gamePanel.obj[0] = regCrab;
        regCrab.worldX = mainCharacter.mapX;
        regCrab.worldY = mainCharacter.mapY;

        // Simulating character picking up RegCrab
        mainCharacter.pickUpReward(0);

        // Checking if the score increased by 1
        assertEquals(1, mainCharacter.score);
    }

    /**
     * Tests the scoring logic when the character picks up a regular fish object.
     * Verifies that the score increases by 1 after picking up a regular fish.
     */
    @Test
    public void testPickUpRegFish() {
        RegFish regFish = new RegFish();
        gamePanel.obj[0] = regFish;
        regFish.worldX = mainCharacter.mapX;
        regFish.worldY = mainCharacter.mapY;

        // Simulating character picking up RegFish
        mainCharacter.pickUpReward(0);

        // Checking if the score increased by 1
        assertEquals(1, mainCharacter.score);
    }


    /**
     * Tests the scoring logic when the character picks up a bonus clam object.
     * Verifies that the score increases by 2 after picking up a bonus clam.
     */
    @Test
    public void testPickUpBonus() {
        BonusClam bonus = new BonusClam();
        gamePanel.obj[0] = bonus;
        bonus.worldX = mainCharacter.mapX;
        bonus.worldY = mainCharacter.mapY;

        // Simulating character picking up bonus
        mainCharacter.pickUpReward(0);

        // Checking if the score increased by 2
        assertEquals(2, mainCharacter.score);
    }

     /**
     * Tests the scoring logic when the character picks up a punishment object.
     * Verifies that the score decreases by 2 after picking up a punishment.
     */
    
    @Test
    public void testPickUpPunishment() {
        // Setting up Punishment object
        Punishment punishment = new Punishment();
        gamePanel.obj[0] = punishment;
        punishment.worldX = mainCharacter.mapX;
        punishment.worldY = mainCharacter.mapY;

        // Simulating character picking up Punishment
        mainCharacter.pickUpReward(0);

        // Checking if the score decreased by 2
        assertEquals(-2, mainCharacter.score);
    }
}