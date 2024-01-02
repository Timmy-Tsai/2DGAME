package com.phase2;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    GamePanel testGp = new GamePanel();
    private boolean compareGameLayout(int[][] layout1, int[][] layout2) {
        //we check the length and width of the array. assuming they are all initialized.
        try {
            if (layout1.length != layout2.length || layout1[0].length != layout2[0].length) {
                return false;
            }

            for (int x = 0; x < layout1.length; x++) {
                for (int y = 0; y < layout1[0].length; y++) {
                    if (layout1[x][y] != layout2[x][y]) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            return false;
        }
    }

    @Test
    public void testPerimeterGeneration(){
        Board testBoard = new Board(this.testGp);
        int expectedLayout[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                                 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};
        testBoard.testPerimGeneration(1,0);
        assertTrue(compareGameLayout(expectedLayout, testBoard.getGameLayout()));
    }

    @Test
    public void testObstacleGeneration(){
        Board testBoard = new Board(this.testGp);
        int expectedLayout[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,2,0,0,0,0,2,0,0,1,0,0,1},
                {1,0,0,0,2,0,2,0,0,2,0,0,1,0,0,1},
                {1,0,0,0,2,0,2,0,0,2,0,0,0,0,0,1},
                {1,0,0,0,2,0,2,0,0,2,0,0,1,1,1,1},
                {1,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1},
                {1,0,2,2,0,0,0,0,0,0,0,2,2,0,2,1},
                {1,0,2,1,0,0,0,0,0,1,0,2,1,0,1,1},
                {1,0,2,1,0,0,2,0,0,1,0,2,1,0,0,1},
                {1,0,2,1,0,0,2,0,0,1,0,2,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};

        testBoard.testBoardGeneration(1,0);
        assertTrue(compareGameLayout(expectedLayout, testBoard.getGameLayout()));
    }
}

