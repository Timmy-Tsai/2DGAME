package com.phase2;
import java.util.ArrayList;

/**
 * This class will hold the A* search algorithm the enemy will use
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class AStar {

    GamePanel gp;
    //pcells will hold the map for the path finid 'mesh'
    PathCells[][] pcell;
    ArrayList<PathCells> list1 = new ArrayList<>();
    public ArrayList<PathCells> list2 = new ArrayList<>();
    PathCells intialCell, endCell, curentCell;
    boolean enemyReached = false;
    int stepCounter = 0;

    /**
     * 
     * AStar constructor
     * 
     * @param gp
     */
    public AStar(GamePanel gp){
        this.gp = gp;
        createMovementMesh();
    }
    /**
     * this method creates the 2d list of pathcells that make up 'map'
     * that the enemy will use for the path finfing algorithm
     */
    public void createMovementMesh(){
        //add map size to pcell
        pcell = new PathCells[gp.numColTiles][gp.numRowTiles];

        int col = 0;
        int row = 0;
        //create a path cell for each cell on the board
        while (col < gp.numColTiles && row < gp.numRowTiles){
            pcell[col][row] = new PathCells(col, row);
            //increase col
            col++;
            //if we reached the end of the row reset the col counter
            if(col == gp.numColTiles){
                //reset the col to the begining and move to the next row
                col = 0;
                row++;
            }
        }
    }

    /**
     * clears the mesh that was created above
     */
    public void resetMovementMesh(){
        int col = 0;
        int row = 0;
        while (col < gp.numColTiles && row < gp.numRowTiles){
            //reset the bolleans for each cell
            pcell[col][row].open = false;
            pcell[col][row].checked = false;
            pcell[col][row].solid = false;
            
            col++;
            if(col == gp.numColTiles){
                col = 0;
                row++;
            }
        }

        //clear the arraylists
        list1.clear();
        list2.clear();
        //reset the other attributes
        enemyReached = false;
        stepCounter = 0;

    }

    /**
     * 
     * goes through the tiles/cells in the board and determins for each
     * pathCell wether it is a solid (passable) or no, so the enemy shark
     * can use the map to navigate its way to the player.
     * 
     * @param initialCol
     * @param initialRow
     * @param endCol
     * @param endRow
     *
     */
    public void setMovementMesh(int initialCol, int initialRow, int endCol, int endRow){
        //reset the mesh before setting
        resetMovementMesh();

        //get intiall cells from arguments
        intialCell = pcell[initialCol][initialRow];
        curentCell = intialCell;
        //get end cell from arguments
        endCell = pcell[endCol][endRow];
        //add the node to list1
        list1.add(curentCell);

        int col = 0;    
        int row = 0;
        while (col < gp.numColTiles && row < gp.numRowTiles){
            
            //while iterate the mesh, we need to find the cells that are solid (not passable)
            int cellNum = gp.gameBoard.getCellAtCoordinate(row, col);
            if(gp.gameBoard.getCellCollection().getCellType(cellNum).collisionStatus() == true){
                //mark the cell as solid
                pcell[col][row].solid = true;
            }
            
            //get the cost of the current cell
            cellCost(pcell[col][row]);

            //increase col
            col++;
            //if we reached the end of the row reset the col counter
            if(col == gp.numColTiles){
                //reset the col to the begining and move to the next row
                col = 0;
                row++;
            }
        }

    }

    /**
     * 
     * calculates the the gCost, hCost, and fCost of the argument
     * path cell and updates the values
     * 
     * @param cell
     */
    public void cellCost(PathCells cell){
        //get x and y differences for gCost
        int xdifference = Math.abs(cell.col - intialCell.col);
        int ydifference = Math.abs(cell.row - intialCell.row);
        //set gcost of current cell
        cell.gCost = xdifference + ydifference;
        //get x and y differences for hCost
        xdifference = Math.abs(cell.col - endCell.col);
        ydifference = Math.abs(cell.row - endCell.row);
        //set hcost of current cell
        cell.hCost = xdifference + ydifference;
        //set fcost fo current cell
        cell.fCost = cell.gCost - cell.hCost;
    }


    /**
     * 
     * This method finds the next best cell the enetity using it should move to
     * It does this by looking at the 4 cells around the current cell
     * and evaluating their costs
     * 
     * @return boolean
     * 
     */
    public boolean AStarSearch(){

        while(enemyReached == false && stepCounter < 500){
            
            int col = curentCell.col;
            int row = curentCell.row;

            //update the current cell
            curentCell.checked = true;
            list1.remove(curentCell);

            //now we open the 4 cell around the current cell
            if(row - 1 >= 0){
                openCell(pcell[col][row-1]);
            }
            if(col + 1 < gp.numColTiles){
                openCell(pcell[col+1][row]);
            }
            if(row + 1 < gp.numRowTiles){
                openCell(pcell[col][row+1]);
            }
            if(col - 1 >= 0){
                openCell(pcell[col-1][row]);
            }

            //check if list1 is empty
            if(list1.size() == 0){
                break;
            }

            //now we need to find the optimal cell so far
            int optimalCell = 0;
            int optimalCellFCost = 999;

            //iterate list 1
            for(int i = 0; i < list1.size(); i++){
                if(list1.get(i).fCost < optimalCellFCost){
                    //set the cell as the new optimal cell
                    optimalCell = i;
                    //updae the optimal f cost
                    optimalCellFCost = list1.get(i).fCost;
                }
                //if we found a cell thats equal to the optimal f cose
                //we need to check the optimal g cost
                else if(list1.get(i).fCost == optimalCellFCost){
                    if(list1.get(i).gCost < list1.get(optimalCell).gCost){
                        //if the g cost is less set it as optimalcell
                        optimalCell = i;
                    }
                }
            }

            //now we need to set the optimal node as the next step
            curentCell = list1.get(optimalCell);

            //check if we reached enemy
            if(curentCell == endCell){
                enemyReached = true;
                findPath();
            }

            //increment step counter
            stepCounter++;
        }

        return enemyReached;
    }

    /**
     * 
     * This checks if the argument cell has already been opened
     * if not it adds it to list1, the open cell lists.
     * 
     * @param cell
     */
    public void openCell(PathCells cell){

        if(cell.open == false && cell.checked == false && cell.solid == false){
            //set the argument cell to open;
            cell.open = true;
            //set the parent to the current cell
            cell.parent = curentCell;
            //add cell to lis1
            list1.add(cell);
        }
    }

    /**
     * find path works backwards along the shortest path from the
     * goal locaiton to the start location adding the path to list2
     */
    public void findPath(){
        //we will back track from main char to enemy
        PathCells current = endCell;

        while(current != intialCell){
            //add the path into list2
            list2.add(0, current);
            current = current.parent;
        }
    }
}
    

