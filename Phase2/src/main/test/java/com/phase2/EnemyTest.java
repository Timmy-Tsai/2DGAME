package com.phase2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class EnemyTest {
    
    //create a gamepanel for the tests
    private GamePanel tempGamePanel;

    /**
     * before testnig we need to initiate the game panel
     */
    @Before
    public void setUp() {
        tempGamePanel = new GamePanel();
    }

    /**
     * Test #1: test enemyes default settings
     */
    @Test
    public void testDefaultSettings() {
        Enemy enemy = new Enemy(tempGamePanel);
        enemy.set_default_position();

        // Check if the setup is set correctly
        Assert.assertEquals(48 * 13, enemy.mapX);
        Assert.assertEquals(48 * 6, enemy.mapY);
        Assert.assertEquals(1, enemy.speed);
        Assert.assertEquals(7, enemy.solidArea.x);
        Assert.assertEquals(15, enemy.solidArea.y);
        Assert.assertEquals(33, enemy.solidArea.width);
        Assert.assertEquals(19, enemy.solidArea.height);
    }

    /**
     * Test #2 Enemy Image Loading
     */
    @Test
    public void testEnemyImageLoading() {
        Enemy enemy = new Enemy(tempGamePanel);
        enemy.getEnemyImage();

        // Check if the enemy images are loaded successfully
        Assert.assertNotNull(enemy.left1);
        Assert.assertNotNull(enemy.right1);
    }

    //no need to test the enemies movement because it is based on A star search which is tested seperatly 
    
}
