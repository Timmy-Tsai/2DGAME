package com.phase2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * The OxygenLevelTest class contains unit tests for the oxygen level behavior
 * in the context of a game scenario involving a {@link MainCharacter} and game panel interactions.
 * It includes tests for the decrease of oxygen level over time, picking up oxygen, and transitioning
 * to a lose state when the oxygen level reaches zero.
 * @author Raymond Chan
 */

public class OxygenLevelTest {

    private GamePanel gamePanel;
    private MainCharacter mainCharacter;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        mainCharacter = new MainCharacter(gamePanel, new KeyHandler(gamePanel), 0, 0, 0, 0);
        gamePanel.player = mainCharacter;
    }

    @Test
    public void testOxygenLevelDecrease() {
        // Simulate the game running for a certain number of frames
        int framesToSimulate = 300; // 60 frames per second, this is 5 seconds

        for (int i = 0; i < framesToSimulate; i++) {
            mainCharacter.update();
        }

        // Check if the oxygen level decreased over time
        assertEquals(6 - framesToSimulate / 180, mainCharacter.currentOxygenLevel);
    }

    @Test
    public void testPickUpOxygen() {
        // Setting up Oxygen object
        Oxygen oxygen = new Oxygen();
        gamePanel.obj[0] = oxygen;
        oxygen.worldX = mainCharacter.mapX;
        oxygen.worldY = mainCharacter.mapY;

        // Simulating character picking up Oxygen
        mainCharacter.pickUpReward(0);

        // Checking if the oxygen level increased by 2 (as specified in the MainCharacter class)
        assertEquals(6, mainCharacter.currentOxygenLevel);
    }

    @Test
    public void testLoseStateWhenOxygenZero() {
        // Set currentOxygenLevel to 0
        mainCharacter.currentOxygenLevel = 0;

        // Simulate the update to check if the game state changes to loseState
        mainCharacter.update();

        // Checking if the game state changes to loseState
        assertEquals(gamePanel.loseState, gamePanel.gameState);
    }
}