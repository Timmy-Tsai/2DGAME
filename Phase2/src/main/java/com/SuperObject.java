package com.phase2;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.RescaleOp;

/**
 * The SuperObject class represents a generic game object. It provides methods for drawing
 * the object with color tinting.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class SuperObject {
    public BufferedImage image, image2, image3, image4, image5, image6, image7;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int defaultsolidAreaX = 0;
    public int defaultsolidAreaY = 0;

     /**
     * Draws the game object on the screen using the specified graphics context,
     * game panel, and array index. Applies a blue tint to the object.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param gp The GamePanel instance associated with the game object.
     * @param i  The array index representing the position of the object in the game panels object array
     */
    
    public void draw(Graphics2D g2, GamePanel gp, int i) {
        // Create a blue tint by applying a color filter
        float[] scales = { 0.5f, 0.5f, 1.0f, 1.0f }; // Adjust the last value (1.0f) to control the blue intensity
        float[] offsets = { 0.0f, 0.0f, 0.0f, 0.0f };
        RescaleOp op = new RescaleOp(scales, offsets, null);

        // Apply the color filter to the image
        BufferedImage tintedImage = op.filter(image, null);

        // Draw the tinted image
        g2.drawImage(tintedImage, gp.obj[i].worldX, gp.obj[i].worldY, gp.individualTileSize, gp.individualTileSize, null);
    }

}