package com.phase2;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Exit class represents an exit portal in the game. It extends the SuperObject class
 * and includes functionality specific to exits.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class Exit extends SuperObject {

    /** The reference to the GamePanel associated with the exit. */
    GamePanel gp;

    /**
     * Constructs an Exit object, setting its name to "Exit" and loading the portal image.
     */
    public Exit() {
        name = "Exit";

        try {
            // Load the portal image from the specified file path
            image = ImageIO.read(getClass().getResource("sprites/Objects/portal.png"));
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs during image loading
            e.printStackTrace();
        }

        // Enable collision for the exit
        this.collision = true;
    }
}