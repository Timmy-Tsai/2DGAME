package com.phase2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This class is used to manage and store game board data of=
 * the generated/currently running game. It holds all the cell
 * data of the game board in a 2D array and it is used to verify
 * collision of each cell.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class Board {
    //
    private CellCollection cells;   //CellCollection object used to manage the various types of cell textures
    private int[][] gameLayout; //2D array storing the cell type layout of the game board
    private int tileSize;
    private int boardRows;
    private int boardColumns;

    
    /**
     * Default constructor of a Board object
     */
    public Board(){
        this.cells = null;
        this.gameLayout = null;
        this.tileSize = -1;
        this.boardColumns = -1;
        this.boardRows = -1;
    }
   
    /**
     * Constructor of Board that is used to store texture/cell information
     * on the layout of the game board.
     * 
     * @param gp  the game panel object of the created game
     */
    public Board(GamePanel gp){
        this.cells = new CellCollection(gp);
        this.boardColumns = cells.panel.numColTiles;
        this.boardRows = cells.panel.numRowTiles;
        this.gameLayout = new int[this.boardRows+1][this.boardColumns];
        this.tileSize = cells.panel.individualTileSize;
    }

     /**
     * gets the CellCollection object to access other 
     * getter methods for checking cell types and other
     * attributes.
     * 
     * @return the created CellCollection object created
     *      in this class (Board.java).
     */
    public CellCollection getCellCollection(){
        return this.cells;
    }

    /**
     * gets the 2D array that stores the current layout
     * and state of the game board.
     * 
     * @return int value from 0-2 to indicate its type
     */
    public int[][] getGameLayout(){
        return this.gameLayout;
    }

     /**
     * gets the cell ID value at a given (x,y) coordinate
     * 
     * @return the cell ID
     */
    public int getCellAtCoordinate(int x, int y){
        return this.gameLayout[x][y];
    }

    /**
     * Generates a basic perimeter of the map with the desired textures
     * for the border and floor.
     * 
     * @param perimTexture  takes in the ID of the chosen texture to be
     *        used for the perimeter.
     * @param floorTexture  takes in the ID of the chosen texture to be
     *        used for the floor.
     * @param g  Graphics2D object used to draw graphical elements of
     *        the game.   
     */
    public void generateBoard(int perimTexture, int floorTexture, Graphics2D g){
        //imgorary variables used to store the cell images (textures).
        BufferedImage temp = this.cells.getCellType(perimTexture).getCellImage();
        BufferedImage temp2 = this.cells.getCellType(floorTexture).getCellImage();
        
        //refactored to utilise helper functions for less clustered code
        generateTopBottom(perimTexture, g, temp);
        generateSides(perimTexture, g, temp);
        generateFloor(floorTexture, g, temp2);

        //calls another generate function to create and draw maze obstacles onto the board.
        generateMazeWall(g);
    }

    /**
     * Generates the obstacles of the maze on the generated board.
     * 
     * @param g  Graphics2D object used to draw graphical elements of
     *        the game. 
     */
    public void generateMazeWall(Graphics2D g){
        
        //imgorary variables used to store the needed cell images (texures).
        BufferedImage temp = this.cells.getCellType(1).getCellImage();
        BufferedImage temp2 = this.cells.getCellType(2).getCellImage();
        //refactored to utilise helper functions for less clustered code
        generateStone(g, temp);
        generateSeaweed(g, temp2);
        
    }

    //HELPER FUNCTIONS

    /**
     * A helper function that generates the top and bottom row of the game board's perimeter. 
     * 
     * @param perimTexture  numeric selection for the desired cell 
     *        texture (valid choices: 0-2)
     * @param g Graphics2D object used to draw the textures into
     *        the game window
     * @param img  Image of the chosen texture
     */
    private void generateTopBottom(int perimTexture, Graphics2D g, BufferedImage img){
        //generating top and bottom rows of the perimeter
        for(int i = 0; i < this.boardColumns * this.tileSize; i += this.tileSize){
            //drawing 
            g.drawImage(img, i, 48, this.tileSize, this.tileSize, null);
            g.drawImage(img, i, (this.boardRows - 1) * this.tileSize, this.tileSize, this.tileSize, null);
            this.gameLayout[1][i/this.tileSize] = perimTexture;
            this.gameLayout[(this.boardRows - 1)][i/this.tileSize] = perimTexture;
        }
    }

    /**
     * A helper function that generates the left and right sides of the game board's perimeter. 
     * 
     * @param perimTexture  numeric selection for the desired cell 
     *        texture (valid choices: 0-2)
     * @param g Graphics2D object used to draw the textures into
     *        the game window
     * @param img  Image of the chosen texture
     */
    private void generateSides(int perimTexture, Graphics2D g, BufferedImage img){
        //generating both sides of the perimeter
        for(int i = 96; i < (this.boardRows - 1) * this.tileSize; i += this.tileSize){
            //Drawing sides
            g.drawImage(img, 0, i, this.tileSize, this.tileSize, null);
            g.drawImage(img, (this.boardColumns - 1) * this.tileSize, i, this.tileSize, this.tileSize, null);
            this.gameLayout[i/this.tileSize][0] = perimTexture;
            this.gameLayout[i/this.tileSize][(this.boardColumns - 1)] = perimTexture;
        }
    }

    /**
     * A helper function that generates the floor of the game board. 
     * 
     * @param perimTexture  numeric selection for the desired cell 
     *        texture (valid choices: 0-2)
     * @param g Graphics2D object used to draw the textures into
     *        the game window
     * @param img  Image of the chosen texture
     */
    private void generateFloor(int floorTexture, Graphics2D g, BufferedImage img){
        //drawing floor cell textures
        for(int i = 96; i < (this.boardRows - 1) * this.tileSize; i += this.tileSize){
            for(int j = 48; j < (this.boardColumns - 1) * this.tileSize; j += this.tileSize){
                g.drawImage(img, j, i, this.tileSize, this.tileSize, null);
                this.gameLayout[i/this.tileSize][j/this.tileSize] = floorTexture;
            }
        }
    }

    /**
     * A helper function that generates obstacles with the Stone cell texture. 
     * 
     * @param g Graphics2D object used to draw the textures into
     *        the game window
     * @param img  Image of the chosen texture
     */
    private void generateStone(Graphics2D g, BufferedImage img){
        //stone walls
        for(int i = 9; i < 12; i++){
            g.drawImage(img, 12*48, i*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i][12] = 1;
            g.drawImage(img, 9*48, i*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i][9] = 1;
            g.drawImage(img, 3*48, i*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i][3] = 1;
        }

        for(int i = 8; i < 11; i++){
            g.drawImage(img, (i+4)*48, 5*48, this.tileSize, this.tileSize, null);
            this.gameLayout[5][i+4] = 1;
            g.drawImage(img, 12*48, (i-7)*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i-7][12] = 1;
        }
    
        g.drawImage(img, 14*48, 9*48, this.tileSize, this.tileSize, null);
        this.gameLayout[9][14] = 1;
    }

     /**
     * A helper function that generates obstacles with the Seaweed cell texture. 
     * 
     * @param g Graphics2D object used to draw the textures into
     *        the game window
     * @param img  Image of the chosen texture
     */
    private void generateSeaweed(Graphics2D g, BufferedImage img){
        //seaweed
        for(int i = 2; i < 6; i++){
            g.drawImage(img, 4*48, i*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i][4] = 2;
            g.drawImage(img, 9*48, i*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i][9] = 2;
            g.drawImage(img, 11*48, (i+6)*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i+6][11] = 2;
            g.drawImage(img, 2*48, (i+6)*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i+6][2] = 2;
        }

        for(int i = 3; i<8; i++){
            g.drawImage(img, 6*48, i*48, this.tileSize, this.tileSize, null);
            this.gameLayout[i][6] = 2;
        }
        g.drawImage(img, 3*48, 8*48, this.tileSize, this.tileSize, null);
        this.gameLayout[8][3] = 2;

        g.drawImage(img, 6*48, 10*48, this.tileSize, this.tileSize, null);
        this.gameLayout[10][6] = 2;
        g.drawImage(img, 6*48, 11*48, this.tileSize, this.tileSize, null);
        this.gameLayout[11][6] = 2;

        g.drawImage(img, 12*48, 8*48, this.tileSize, this.tileSize, null);
        this.gameLayout[8][12] = 2;
        g.drawImage(img, 14*48, 8*48, this.tileSize, this.tileSize, null);
        this.gameLayout[8][14] = 2;
    }


    /*
    THE FUNCTIONS BELOW UNTIL THE END OF THE FILE ARE ONLY USED FOR TESTING. 
    FUNCTIONS HAVE SIMILAR CODE MINUS THE ABILITY TO DRAW THE CELLS.
    */
    //Test Function used for test cases as the original functions require a Graphics2D object for a parameter (Graphics2D object not accessible in test cases).
    public void testBoardGeneration(int perimTexture, int floorTexture){
        //generating top and bottom rows of the perimeter
        topBottomPerimGenTest(perimTexture);
        sideFloorGenTest(perimTexture, floorTexture);
        testAddingObstacles();
    }

    public void testPerimGeneration(int perimTexture, int floorTexture){
        topBottomPerimGenTest(perimTexture);
        sideFloorGenTest(perimTexture, floorTexture);
    }


    //test helper functions
    private void sideFloorGenTest(int perimTexture, int floorTexture){
        //generating both sides of the perimeter and floor
        for(int i = 96; i < (this.boardRows - 1) * this.tileSize; i += this.tileSize){
            //Drawing sides
            this.gameLayout[i/this.tileSize][0] = perimTexture;
            this.gameLayout[i/this.tileSize][(this.boardColumns - 1)] = perimTexture;

            //drawing floor cell textures
            for(int j = 48; j < (this.boardColumns - 1) * this.tileSize; j += this.tileSize){
                this.gameLayout[i/this.tileSize][j/this.tileSize] = floorTexture;
            }
        }
    }

    private void topBottomPerimGenTest(int perimTexture){
        //generating top and bottom rows of the perimeter
        for(int i = 0; i < this.boardColumns * this.tileSize; i += this.tileSize){
            this.gameLayout[1][i/this.tileSize] = perimTexture;
            this.gameLayout[(this.boardRows - 1)][i/this.tileSize] = perimTexture;
        }
    }

    private void testAddingObstacles(){
        stoneWallGenTest();
        seaweedWallGenTest();
    }

    private void stoneWallGenTest(){
        for(int i = 9; i < 12; i++){
            this.gameLayout[i][12] = 1;
            this.gameLayout[i][9] = 1;
            this.gameLayout[i][3] = 1;
        }

        for(int i = 8; i < 11; i++){
            this.gameLayout[5][i+4] = 1;
            this.gameLayout[i-7][12] = 1;
        }

        this.gameLayout[9][14] = 1;
    }

    private void seaweedWallGenTest(){
        //seaweed
        for(int i = 2; i < 6; i++){
            this.gameLayout[i][4] = 2;
            this.gameLayout[i][9] = 2;;
            this.gameLayout[i+6][11] = 2;
            this.gameLayout[i+6][2] = 2;
        }

        for(int i = 3; i<8; i++){
            this.gameLayout[i][6] = 2;
        }

        this.gameLayout[8][3] = 2;
        this.gameLayout[10][6] = 2;
        this.gameLayout[11][6] = 2;
        this.gameLayout[8][12] = 2;
        this.gameLayout[8][14] = 2;
    }
}