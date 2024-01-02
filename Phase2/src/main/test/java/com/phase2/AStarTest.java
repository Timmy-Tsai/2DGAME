package com.phase2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AStarTest {

    private GamePanel tempGamePanel;
    private AStar aStar;


    /**
     * before testnig we need to initiate the game panels and the search algorithm
     */
    @Before
    public void setUp() {
        tempGamePanel = new GamePanel();
        aStar = new AStar(tempGamePanel);
    }

    /**
     * Test #1: Testing A star search with a regular end goal
     */
    @Test
    public void testAStarRegularSearch() {
        // Set up a simple map with open paths
        aStar.setMovementMesh(0, 0, 5, 5);

        // Perform A* search
        boolean result = aStar.AStarSearch();

        // The result should be true since the end cell is reachable
        assertTrue(result);

        // Add more assertions if needed
    }

    /**
     * Test #2: testing A star search when the whoal board is blocked
     */
    @Test
    public void testAStarNoPath() {
        // Set up a map with no direct path to the end
        aStar.setMovementMesh(0, 0, 5, 5);

        // Block all cells
        for (int col = 0; col < tempGamePanel.numColTiles; col++) {
            for (int row = 0; row < tempGamePanel.numRowTiles; row++) {
                aStar.pcell[col][row].solid = true;
            }
        }

        // Perform A* search
        boolean result = aStar.AStarSearch();

        // The result should be false since there is no path
        assertFalse(result);
    }

    /**
     * Test #3: Testing the cell cost method
     */
    @Test
    public void testCellCost() {
        // Set up initial and end cells
        aStar.intialCell = new PathCells(0, 0);
        aStar.endCell = new PathCells(5, 5);

        // Create a path cell that we will check the costs of
        PathCells testCell = new PathCells(2, 2);

        // Call the cellCost method
        aStar.cellCost(testCell);

        // Assert the calculated costs
        assertEquals(4, testCell.gCost); // (2 - 0) + (2 - 0) = 4
        assertEquals(6, testCell.hCost); // (5 - 2) + (5 - 2) = 6
        assertEquals(-2, testCell.fCost); // gCost - hCost = 4 - 6 = -2
    }
    
}
