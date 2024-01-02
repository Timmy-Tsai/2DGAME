package com.phase2;

import org.junit.Test;
import static org.junit.Assert.*;

public class CellCollectionTest {

    @Test
    public void testConstructor(){
        GamePanel testGp = new GamePanel();
        CellCollection testCellCollection = new CellCollection(testGp);

        assertEquals(testGp, testCellCollection.panel);
        assertEquals(testCellCollection.cellTypeCount, 3);
        assertEquals(testCellCollection.getCellType(0).getType(), 0);
        assertEquals(testCellCollection.getCellType(1).getType(), 1);
        assertEquals(testCellCollection.getCellType(2).getType(), 2);
    }
}
