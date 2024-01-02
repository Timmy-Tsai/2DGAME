package com.phase2;

/**
 * The RewardSetter class is responsible for placing rewards and other game objects
 * on the map within the context of a specified GamePanel.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class RewardSetter {

    /** The GamePanel instance associated with this RewardSetter. */
    private GamePanel gp;

    /**
     * Constructs a new RewardSetter with the specified GamePanel.
     *
     * @param gp The GamePanel instance to associate with this RewardSetter.
     */
    public RewardSetter(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Places various rewards and game objects on the map according to cell columns and rows.
     * Regular rewards, bonus rewards, oxygen containers, and the game exit are 
     * positioned on the map
     */
    public void setReward() {
        // Setting regular rewards
        gp.obj[0] = new RegCrab();
        gp.obj[0].worldX = 10 * gp.individualTileSize;
        gp.obj[0].worldY = 11 * gp.individualTileSize;

        gp.obj[1] = new RegCrab();
        gp.obj[1].worldX = 5 * gp.individualTileSize;
        gp.obj[1].worldY = 6 * gp.individualTileSize;

        gp.obj[2] = new RegFish();
        gp.obj[2].worldX = 3 * gp.individualTileSize;
        gp.obj[2].worldY = 4 * gp.individualTileSize;

        // Setting bonus rewards
        gp.obj[3] = new BonusClam();
        gp.obj[3].worldX = 14 * gp.individualTileSize;
        gp.obj[3].worldY = 10 * gp.individualTileSize;

        // Setting Oxygen
        gp.obj[4] = new Oxygen();
        gp.obj[4].worldX = 8 * gp.individualTileSize;
        gp.obj[4].worldY = 2 * gp.individualTileSize;

        gp.obj[5] = new Oxygen();
        gp.obj[5].worldX = 1 * gp.individualTileSize;
        gp.obj[5].worldY = 11 * gp.individualTileSize;

        // Setting exit
        gp.obj[8] = new Exit();
        gp.obj[8].worldX = 14 * gp.individualTileSize;
        gp.obj[8].worldY = 1 * gp.individualTileSize;

        gp.obj[9] = new RegFish();
        gp.obj[9].worldX = 5 * gp.individualTileSize;
        gp.obj[9].worldY = 2 * gp.individualTileSize;

        gp.obj[10] = new RegFish();
        gp.obj[10].worldX = 14 * gp.individualTileSize;
        gp.obj[10].worldY = 6 * gp.individualTileSize;

        gp.obj[11] = new RegCrab();
        gp.obj[11].worldX = 11 * gp.individualTileSize;
        gp.obj[11].worldY = 2 * gp.individualTileSize;

        gp.obj[12] = new RegCrab();
        gp.obj[12].worldX = 4 * gp.individualTileSize;
        gp.obj[12].worldY = 11 * gp.individualTileSize;

        gp.obj[14] = new RegFish();
        gp.obj[14].worldX = 8 * gp.individualTileSize;
        gp.obj[14].worldY = 11 * gp.individualTileSize;

        gp.obj[15] = new Oxygen();
        gp.obj[15].worldX = 9 * gp.individualTileSize;
        gp.obj[15].worldY = 6 * gp.individualTileSize;
    }
}