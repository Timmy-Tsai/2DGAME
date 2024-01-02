package com.phase2;

import javax.swing.JFrame;

/**
 * The Main class creates a JFrame window, sets up the game panel,
 * and starts the game thread to start the game.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class Main {
     /**
     * The main method
     *
     * @param args 
     */
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("IT'S RAW!");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
