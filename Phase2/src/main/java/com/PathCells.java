package com.phase2;


/**
 * this class with allow out to split our map into cells that will
 * be used during the A* pathfinding algorithim the enemy will use
 * to find the main character.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class PathCells {
    
    PathCells parent;
    public int col;
    public int row;
    boolean solid;
    boolean open;
    boolean checked;
    public int gCost;
    public int hCost;
    public int fCost;

    public PathCells(int col, int row){
        this.col = col;
        this.row = row;
    }
}
