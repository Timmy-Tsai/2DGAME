package com.phase2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The KeyHandler class is responsible for handling keyboard input in the game.
 * It implements the KeyListener interface to listen for key events.
 */
public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    /**
     * Constructs a KeyHandler with a reference to the GamePanel.
     *
     * @param gp The GamePanel instance that the KeyHandler belongs to.
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not used in the game
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Handle input based on the game state
        if (gp.gameState == gp.titleState) {
            handleTitleState(code);
        } else if (gp.gameState == gp.playState) {
            handlePlayState(code);
        }

        // Common input handling for multiple game states
        handleGameState(code);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // Update the state of directional keys based on the released key
        updateDirection(code);
    }

    /**
     * Handles keyboard input in the title state.
     *
     * @param code The key code of the pressed key.
     */
    private void handleTitleState(int code) {
        // Logic for handling input in title state
        if (code == KeyEvent.VK_W) {
            gp.ui.Control_Num--;
            if (gp.ui.Control_Num < 0) {
                gp.ui.Control_Num = 2;
            }
        } else if (code == KeyEvent.VK_S) {
            gp.ui.Control_Num++;
            if (gp.ui.Control_Num > 1) {
                gp.ui.Control_Num = 0;
            }
        } else if (code == KeyEvent.VK_ENTER) {
            EnterKeyTitleState();
        }
    }

    /**
     * Handles the Enter key press in the title state.
     */
    private void EnterKeyTitleState() {
        if (gp.ui.Control_Num == 0) {
            gp.gameState = gp.playState;
        } else if (gp.ui.Control_Num == 1) {
            System.exit(0);
        }
    }

    /**
     * Handles keyboard input in the play state.
     *
     * @param code The key code of the pressed key.
     */
    private void handlePlayState(int code) {
        // Logic for handling input in play state
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    /**
     * Handles common keyboard input for multiple game states.
     *
     * @param code The key code of the pressed key.
     */
    private void handleGameState(int code) {
        // Common input handling for multiple game states
        if (code == KeyEvent.VK_P) {
            handlePauseAndContinue();
        } else if (code == KeyEvent.VK_Q) {
            handleQuitGame();
        } else if (code == KeyEvent.VK_R) {
            handleRestartGame();
        }
    }

    /**
     * Handles the Pause and Continue functionality.
     */
    private void handlePauseAndContinue() {
        if (gp.gameState == gp.playState) {
            gp.gameState = gp.pauseState;
        } else if (gp.gameState == gp.pauseState) {
            gp.gameState = gp.playState;
        }
    }

    /**
     * Handles quitting the game.
     */
    private void handleQuitGame() {
        if (gp.gameState == gp.winState || gp.gameState == gp.loseState) {
            System.exit(0);
        }
    }

    /**
     * Handles restarting the game.
     */
    private void handleRestartGame() {
        if (gp.gameState == gp.winState || gp.gameState == gp.loseState || gp.gameState == gp.pauseState) {
            restartGame();
        }
    }

    /**
     * Restarts the game by creating a new GamePanel and resetting the game state.
     */
    private void restartGame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("It's Raw!");
        SwingUtilities.getWindowAncestor(gp).dispose();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    /**
     * Updates the state of directional keys based on the released key.
     *
     * @param code The key code of the released key.
     */
    private void updateDirection(int code) {
        // Update the state of directional keys based on the released key
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } else if (code == KeyEvent.VK_S) {
            downPressed = false;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
