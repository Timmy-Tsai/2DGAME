package com.phase2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameStartTest {

    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
    }

    @Test
    public void testGameStartsInTitleState() {
        // Call setupGame to initialize game state
        gamePanel.setupGame();

        // Check if the game starts in title state
        assertEquals(gamePanel.titleState, gamePanel.gameState);
    }
}