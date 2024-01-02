package com.phase2;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * The FileSystemTest class contains unit tests for reading images and sprites from the file system.
 * It includes tests for reading images of different game characters and their corresponding sprites.
 * Each test checks whether the image or sprite was loaded successfully.
 * @author Raymond Chan
 */

public class FileSystemTest {

    /**
     * Tests the successful reading of the RegCrab image.
     */
    @Test
    public void testRegCrabImageRead() {
        // Create a RegCrab instance
        RegCrab regCrab = new RegCrab();

        // Check if the image was read successfully
        assertNotNull("RegCrab image not loaded successfully", regCrab.image);
    }

    /**
     * Tests the successful reading of the RegFish image.
     */
    @Test
    public void testRegFishImageRead() {
        // Create a RegCrab instance
        RegFish regFish = new RegFish();

        // Check if the image was read successfully
        assertNotNull("RegFish image not loaded successfully", regFish.image);
    }

    /**
     * Tests the successful reading of the BonusClam image.
     */
    @Test
    public void testBonusImageRead() {
        // Create a RegCrab instance
        BonusClam bonus = new BonusClam();

        // Check if the image was read successfully
        assertNotNull("BonusClam image not loaded successfully", bonus.image);
    }

    /**
     * Tests the successful reading of the MainCharacter up1 sprite.
     */
    @Test
    public void testMainCharacterUp1SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the up1 sprite was read successfully
        assertNotNull("Up1 sprite not loaded successfully", mainCharacter.up1);
    }

     /**
     * Tests the successful reading of the MainCharacter up2 sprite.
     */
    @Test
    public void testMainCharacterUp2SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the up2 sprite was read successfully
        assertNotNull("Up2 sprite not loaded successfully", mainCharacter.up2);
    }

    /**
     * Tests the successful reading of the MainCharacter down1 sprite.
     */

    @Test
    public void testMainCharacterDown1SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the down1 sprite was read successfully
        assertNotNull("Down1 sprite not loaded successfully", mainCharacter.down1);
    }

    /**
     * Tests the successful reading of the MainCharacter down2 sprite.
     */

    @Test
    public void testMainCharacterDown2SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the down2 sprite was read successfully
        assertNotNull("Down2 sprite not loaded successfully", mainCharacter.down2);
    }

    /**
     * Tests the successful reading of the MainCharacter left1 sprite.
     */
    @Test
    public void testMainCharacterLeft1SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the left1 sprite was read successfully
        assertNotNull("Left1 sprite not loaded successfully", mainCharacter.left1);
    }

    /**
     * Tests the successful reading of the MainCharacter left2 sprite.
     */
    @Test
    public void testMainCharacterLeft2SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the left2 sprite was read successfully
        assertNotNull("Left2 sprite not loaded successfully", mainCharacter.left2);
    }

     /**
     * Tests the successful reading of the MainCharacter right1 sprite.
     */

    @Test
    public void testMainCharacterRight1SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the right1 sprite was read successfully
        assertNotNull("Right1 sprite not loaded successfully", mainCharacter.right1);
    }

     /**
     * Tests the successful reading of the MainCharacter right2 sprite.
     */
    @Test
    public void testMainCharacterRight2SpriteRead() {
        // Create a MainCharacter instance
        MainCharacter mainCharacter = new MainCharacter(null, null, 0, 0, 0, 0);

        // Call the method to load the sprites
        mainCharacter.getMainCharImage();

        // Check if the right2 sprite was read successfully
        assertNotNull("Right2 sprite not loaded successfully", mainCharacter.right2);
    }

}