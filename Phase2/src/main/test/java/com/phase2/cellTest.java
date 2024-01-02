package com.phase2;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class cellTest{

    //private method used to compare every pixel of 2 given images within their dimension.
    private boolean compareImage(BufferedImage img1, BufferedImage img2){
        //compare sizes first. If sizes are not equal, the program will default to returning false
        if(img1.getHeight() == img2.getHeight() && img1.getWidth() == img2.getWidth()){
            //if both images are the same size, individual pixels of both images will be compared with each other
            //By comparing the pixels, we are able to determine whether if the colour of the pixel at the given x, y
            //position has the same RGB colour code as the other to determine if they are similar.
            for(int x = 0; x < img1.getWidth(); x++){
                for(int y = 0; y < img1.getHeight(); y++){
                    if(img1.getRGB(x,y) != img2.getRGB(x,y)){
                        return false; //return false when there is a mismatch of pixels
                    }
                }
            }
            return true; //returns true when all pixels are compared and deemed to be the same
        }
        return false; //returns false when the overall dimensions are different between the two images
    }

    @Test
    public void testSandTexure(){
        try {
            Cell test = new Cell(0);
            BufferedImage expectedTexture = ImageIO.read(getClass().getResource("sprites/cellTextures/blueSand2.png"));
            assertTrue(compareImage(test.getCellImage(), expectedTexture));
            assertEquals(test.getType(), 0);
            assertFalse(test.collisionStatus());
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @Test
    public void testRockTexure(){
        try {
            Cell test = new Cell(1);
            BufferedImage expectedTexture = ImageIO.read(getClass().getResource("sprites/cellTextures/Rock1.png"));
            assertTrue(compareImage(test.getCellImage(), expectedTexture));
            assertEquals(test.getType(), 1);
            assertTrue(test.collisionStatus());
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @Test
    public void testSeaweedTexure(){
        try {
            Cell test = new Cell(2);
            BufferedImage expectedTexture = ImageIO.read(getClass().getResource("sprites/cellTextures/seaweed.png"));
            assertTrue(compareImage(test.getCellImage(), expectedTexture));
            assertEquals(test.getType(), 2);
            assertTrue(test.collisionStatus());
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @Test
    public void invalidTextureSelection(){
        System.out.println("\n**NOTE** Expect ERROR: Invalid Selection message to indicate that the default switch case has been chosen.");
        Cell test = new Cell(25);
        assertNull(test.getCellImage());
        assertFalse(test.collisionStatus());
    }

}
