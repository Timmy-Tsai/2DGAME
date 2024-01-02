package com.phase2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * JUnit test class for the SuperObject class.
 */
public class SuperObjectTest {

    @Test
    public void CreateSuperOBJ() {
        SuperObject superObject = new SuperObject();
        assertNotNull(superObject);
    }

    @Test
    public void Drawingtest() {
        SuperObject superObject = new SuperObject();
        superObject.image = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);

        // Create a BufferedImage for testing
        BufferedImage testImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = testImage.createGraphics();

        GamePanel gamePanel = new GamePanel();
        gamePanel.obj[0] = superObject;

        superObject.draw(g2, gamePanel, 0);

        // Ensure that the draw method does not throw any exceptions
        assertTrue(true);
    }

    @Test
    public void DefaultCollision() {
        SuperObject superObject = new SuperObject();
        assertFalse(superObject.collision);
    }

    @Test
    public void CollisionAfter() {
        SuperObject superObject = new SuperObject();
        superObject.collision = true;
        assertTrue(superObject.collision);
    }

    @Test
    public void DefaultSolidArea() {
        SuperObject superObject = new SuperObject();
        assertEquals(0, superObject.solidArea.x);
        assertEquals(0, superObject.solidArea.y);
        assertEquals(48, superObject.solidArea.width);
        assertEquals(48, superObject.solidArea.height);
    }

    @Test
    public void WorldCoordinatesDefault() {
        SuperObject superObject = new SuperObject();
        assertEquals(0, superObject.worldX);
        assertEquals(0, superObject.worldY);
    }
}
