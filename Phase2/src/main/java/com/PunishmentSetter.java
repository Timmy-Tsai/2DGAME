package com.phase2;

/**
 * The PunishmentSetter class is responsible for placing punishments and enemies on the game map.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */

public class PunishmentSetter {
    GamePanel gp;

     /**
     * Constructs a PunishmentSetter with the specified GamePanel.
     *
     * @param gp The GamePanel to associate with this PunishmentSetter.
     */
    public PunishmentSetter(GamePanel gp) {
        this.gp = gp;
    }

     /**
     * Places punishments on the game map at specific positions defined by cell column and row.
     * Punishments are added to the GamePanel's object array.
     */
    public void setPunishment() {
        // Setting punishments
        gp.obj[6] = new Punishment();
        gp.obj[6].worldX = 5 * gp.individualTileSize;
        gp.obj[6].worldY = 8 * gp.individualTileSize;
        gp.obj[7] = new Punishment();
        gp.obj[7].worldX = 8 * gp.individualTileSize;
        gp.obj[7].worldY = 5 * gp.individualTileSize;
        gp.obj[13] = new Punishment();
        gp.obj[13].worldX = 10 * gp.individualTileSize;
        gp.obj[13].worldY = 5 * gp.individualTileSize;
    }

    /**
     * Sets up an enemy on the game map using the associated GamePanel.
     * The enemy is added to the GamePanel's enemy array and is initialized to its default position.
     */
    public void setEnemy(){
        gp.enemy[0] = new Enemy(gp);
        gp.enemy[0].set_default_position();
    }
    
}