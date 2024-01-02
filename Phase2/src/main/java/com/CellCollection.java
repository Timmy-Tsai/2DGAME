package com.phase2;

/**
 * This class creates an object that manages the textures during the importing
 * process. It regulates the amount of texture types that are to be imported
 * as well as holding a record of all imported textures to provide information
 * of each type of texture.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class CellCollection {
    //private class variables for encapsulation and data protection
    final int cellTypeCount = 3; //Setting the number of possible textures to import
    private Cell[] cellType; //Storing all the imported cell types (textures)

    GamePanel panel; //Storing instance of a given GamePanel object

    /**
     * Constructor of the CellCollection class. Imports the preset amount/types of 
     * textures of cells and stores them into the cellType class array.
     * 
     * @param gamepanel provides an instance of the GamePanel object for quick
     *        access later on.
     */
    public CellCollection(GamePanel gamepanel){
        this.panel = gamepanel;

        /*initializing an array of type Cell with the size set up by the variable 
         *cellTextureCount (currently set to 3).
         */
        this.cellType = new Cell[cellTypeCount]; 

        //setting textures to each index of the cellType array
        for(int i = 0; i < cellTypeCount; i++){
            this.cellType[i] = new Cell(i);
        }
    }

    /**
     * Getter for an instance of a Cell object
     * that contains the texture image, ID,
     * and collision attributes.
     * 
     * @return a Cell object of the selected type
     */
    Cell getCellType(int index){
        return this.cellType[index];
    }
}