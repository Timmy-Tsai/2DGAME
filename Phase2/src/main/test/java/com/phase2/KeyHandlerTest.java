package com.phase2;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;
import static org.junit.Assert.*;

/**
 * JUnit test class for the KeyHandler class.
 */
public class KeyHandlerTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        keyHandler = new KeyHandler(gamePanel);
    }

    /**
     * Helper method to simulate key press.
     */
    private void pressKey(int keyCode) {
        KeyEvent keyPress = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, (char) keyCode);
        keyHandler.keyPressed(keyPress);
    }

    /**
     * Helper method to simulate key release.
     */
    private void releaseKey(int keyCode) {
        KeyEvent keyRelease = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, keyCode, (char) keyCode);
        keyHandler.keyReleased(keyRelease);
    }

    /**
     * Test case to verify that the game state is updated to play state when the title screen is entered.
     */
    @Test
    public void titleTest() {
        // Simulate key press for starting the game in title state
        pressKey(KeyEvent.VK_ENTER);

        // Check that the game state is updated to play state
        assertEquals(gamePanel.playState, gamePanel.gameState);
    }

    /**
     * Test case to check if the 'upPressed' attribute is set to true when the 'W' key is pressed in play state.
     */
    @Test
    public void keyUpPressed() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key press for moving up in play state
        pressKey(KeyEvent.VK_W);

        // Check that the upPressed attribute is set to true
        assertTrue(keyHandler.upPressed);
    }

    /**
     * Test case to check if the 'downPressed' attribute is set to true when the 'S' key is pressed in play state.
     */
    @Test
    public void keyDownPressed() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key press for moving down in play state
        pressKey(KeyEvent.VK_S);

        // Check that the downPressed attribute is set to true
        assertTrue(keyHandler.downPressed);
    }

    /**
     * Test case to check if the 'leftPressed' attribute is set to true when the 'A' key is pressed in play state.
     */
    @Test
    public void keyLeftPressed() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key press for moving left in play state
        pressKey(KeyEvent.VK_A);

        // Check that the leftPressed attribute is set to true
        assertTrue(keyHandler.leftPressed);
    }

    /**
     * Test case to check if the 'rightPressed' attribute is set to true when the 'D' key is pressed in play state.
     */
    @Test
    public void keyRightPressed() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key press for moving right in play state
        pressKey(KeyEvent.VK_D);

        // Check that the rightPressed attribute is set to true
        assertTrue(keyHandler.rightPressed);
    }

    /**
     * Test case to check if the 'upPressed' attribute is set to false when the 'W' key is released in play state.
     */
    @Test
    public void keyReleased() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key release for moving up in play state
        releaseKey(KeyEvent.VK_W);

        // Check that the upPressed attribute is set to false
        assertFalse(keyHandler.upPressed);
    }

    /**
     * Test case to verify that the game state is updated to pause state when the 'P' key is pressed during the game.
     */
    @Test
    public void gamePause() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key pressed for pausing the game
        pressKey(KeyEvent.VK_P);

        // Check that the game state is updated to pause state
        assertEquals(gamePanel.pauseState, gamePanel.gameState);
    }

    /**
     * Test case to verify that the game state is updated to play state when the 'P' key is pressed again after pausing.
     */
    @Test
    public void gameContinue() {
        // Start the game
        pressKey(KeyEvent.VK_ENTER);

        // Simulate key pressed for pausing the game
        pressKey(KeyEvent.VK_P);

        // Simulate continuing the game when P is pressed again
        pressKey(KeyEvent.VK_P);

        // Check that the game state is updated to play state
        assertEquals(gamePanel.playState, gamePanel.gameState);
    }

    
}
