package com.phase2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.swing.JFrame;
/**
 * The RewardSetterTest class contains a unit test for the {@code setReward} method,
 * which is responsible for setting up reward positions in a {@link GamePanel}.
 * It tests the correctness of reward positions after the method execution.
 * @author Raymond Chan
 */

public class RewardSetterTest {

    @Test
    public void testSetReward() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Test Rewards");

        GamePanel mockGamePanel = new GamePanel();
        window.add(mockGamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        mockGamePanel.setupGame();
        mockGamePanel.startGameThread();

        // Test the positions of the rewards
        assertEquals(10 * mockGamePanel.individualTileSize, mockGamePanel.obj[0].worldX);
        assertEquals(11 * mockGamePanel.individualTileSize, mockGamePanel.obj[0].worldY);

        assertEquals(5 * mockGamePanel.individualTileSize, mockGamePanel.obj[1].worldX);
        assertEquals(6 * mockGamePanel.individualTileSize, mockGamePanel.obj[1].worldY);

        assertEquals(3 * mockGamePanel.individualTileSize, mockGamePanel.obj[2].worldX);
        assertEquals(4 * mockGamePanel.individualTileSize, mockGamePanel.obj[2].worldY);

        assertEquals(14 * mockGamePanel.individualTileSize, mockGamePanel.obj[3].worldX);
        assertEquals(10 * mockGamePanel.individualTileSize, mockGamePanel.obj[3].worldY);

        assertEquals(8 * mockGamePanel.individualTileSize, mockGamePanel.obj[4].worldX);
        assertEquals(2 * mockGamePanel.individualTileSize, mockGamePanel.obj[4].worldY);

    }
}