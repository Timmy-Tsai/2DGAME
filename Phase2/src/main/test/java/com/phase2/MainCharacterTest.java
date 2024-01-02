package com.phase2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainCharacterTest {
    
    private GamePanel tempGamePanel;

    @Before
    public void setUp() {
        tempGamePanel = new GamePanel();
    }

    /**
     * Test #1 Default Position
     */
    @Test
    public void testDefaultPosition(){
        // Default position set with creation
        Assert.assertEquals(48, tempGamePanel.player.mapX);
        Assert.assertEquals(96, tempGamePanel.player.mapY);
    }

    /**
     * Test #2 Movement in the North Direction
     */
    @Test
    public void testMovementNorth(){
        // Assuming the character starts at default position
        tempGamePanel.keyH.upPressed = true;  // Simulate pressing the up key
        tempGamePanel.player.update();  // Update the character's position

        // Check if the character has moved north
        //  y position decreases by 2
        Assert.assertEquals(48, tempGamePanel.player.mapX);  
        Assert.assertEquals(94, tempGamePanel.player.mapY);
    }

    /**
     * Test #3 Movement in the South Direction
     */
    @Test
    public void testMovementSouth(){
        // Assuming the character starts at default position
        tempGamePanel.keyH.downPressed = true;  // Simulate pressing the down key
        tempGamePanel.player.update();  // Update the character's position

        // Check if the character has moved south
        //  y position increases by 2
        Assert.assertEquals(48, tempGamePanel.player.mapX); 
        Assert.assertEquals(98, tempGamePanel.player.mapY);
    }

    /**
     * Test #4 Movement in the East Direction
     */
    @Test
    public void testMovementEast(){
        // Assuming the character starts at default position
        tempGamePanel.keyH.rightPressed = true;  // Simulate pressing the right key
        tempGamePanel.player.update();  // Update the character's position

        // Check if the character has moved east
        // x position increases by 2
        Assert.assertEquals(50, tempGamePanel.player.mapX);  
        Assert.assertEquals(96, tempGamePanel.player.mapY);
    }

    /**
     * Test #5 Movement in the West Direction
     */
    @Test
    public void testMovementWest(){
        // Assuming the character starts at default position
        tempGamePanel.keyH.leftPressed = true;  // Simulate pressing the left key
        tempGamePanel.player.update();  // Update the character's position

        // Check if the character has moved west
        // x position decreases by 2
        Assert.assertEquals(46, tempGamePanel.player.mapX);  
        Assert.assertEquals(96, tempGamePanel.player.mapY);
    }

    /**
     * Test #6 Pick Up Reward
     */
    @Test
    public void testPickUpReward(){
        // Assuming there is a reward at the default position
        tempGamePanel.obj[0] = new RegFish();
        tempGamePanel.obj[0].worldX = 48;
        tempGamePanel.obj[0].worldY = 96;

        // Simulate the update to check for collision and pick up the reward
        tempGamePanel.player.update();

        // Check if the reward is picked up and the score is incremented
        Assert.assertNull(tempGamePanel.obj[0]);  // The reward should be removed from the game
        Assert.assertEquals(1, tempGamePanel.player.score);
        Assert.assertEquals(1, tempGamePanel.player.hasReward);
    }

    /**
     * Test #7: Testing if the main characters oxygen level decreases
     */
    @Test
    public void testOxygenLevelDecreasing(){
        // check default oxygen level, should be 6
        Assert.assertEquals(6, tempGamePanel.player.currentOxygenLevel); 

        // Simulate the update to decrease the oxygen level
        //250 updates per oxygen level drop, so create a for loop that iterates 250 times
        for (int i = 0; i <= 250; i++) { 
            tempGamePanel.player.update();
        }

        // Check if the oxygen level has decreased
        Assert.assertEquals(5, tempGamePanel.player.currentOxygenLevel); 
    }
}