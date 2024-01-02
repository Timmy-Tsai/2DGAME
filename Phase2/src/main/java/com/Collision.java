package com.phase2;

/**
 * The Collision class provides methods for checking collisions between entities, tiles, and objects
 * in the game. It includes functionality to check collisions with the game environment.
 * 
 * @author Rashed Hadi
 * @author Timmy Tsai
 * @author Raymond Chan
 * @author Raymond Zhou
 */
public class Collision {
    GamePanel gamePanel;

    /**
     * Constructs a Collision object with a reference to the GamePanel.
     *
     * @param gamePanel The GamePanel instance to associate with collision checks.
     */
    public Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Checks for collisions with tiles based on the current direction of the entity.
     *
     * @param entity The entity for which collisions are checked.
     */

    public void checkTile(Entities entity) {
        // Character rectangle borders
        int entityLeftMapX = entity.mapX + entity.solidArea.x;
        int entityRightMapX = entity.mapX + entity.solidArea.x + entity.solidArea.width;
        int entityTopMapY = entity.mapY + entity.solidArea.y;
        int entityBottomMapY = entity.mapY + entity.solidArea.y + entity.solidArea.height;

        // Convert map coordinates to tile indices
        int entityLeftCol = entityLeftMapX/gamePanel.individualTileSize;
        int entityRightCol = entityRightMapX/gamePanel.individualTileSize;
        int entityTopRow = entityTopMapY/gamePanel.individualTileSize;
        int entityBottomRow = entityBottomMapY/gamePanel.individualTileSize;

        // Variables to store tile
        int tileL;
        int tileR;

         // Check collisions based on the entity's direction
        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopMapY - entity.speed)/gamePanel.individualTileSize;
                tileL = gamePanel.gameBoard.getCellAtCoordinate(entityTopRow, entityLeftCol);
                tileR = gamePanel.gameBoard.getCellAtCoordinate(entityTopRow, entityRightCol);
                // Check for collisions with the tiles above
                if(gamePanel.gameBoard.getCellCollection().getCellType(tileL).collisionStatus() || gamePanel.gameBoard.getCellCollection().getCellType(tileR).collisionStatus()){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomMapY + entity.speed)/gamePanel.individualTileSize;
                tileL = gamePanel.gameBoard.getCellAtCoordinate(entityBottomRow, entityLeftCol);
                tileR = gamePanel.gameBoard.getCellAtCoordinate(entityBottomRow, entityRightCol);
                // Check for collisions with the tiles below
                if(gamePanel.gameBoard.getCellCollection().getCellType(tileL).collisionStatus() || gamePanel.gameBoard.getCellCollection().getCellType(tileR).collisionStatus()){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftMapX - entity.speed)/gamePanel.individualTileSize;
                tileL = gamePanel.gameBoard.getCellAtCoordinate(entityTopRow, entityLeftCol);
                tileR = gamePanel.gameBoard.getCellAtCoordinate(entityBottomRow, entityLeftCol);
                // Check for collisions with the tiles to the left
                if(gamePanel.gameBoard.getCellCollection().getCellType(tileL).collisionStatus() || gamePanel.gameBoard.getCellCollection().getCellType(tileR).collisionStatus()){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightMapX + entity.speed)/gamePanel.individualTileSize;
                tileL = gamePanel.gameBoard.getCellAtCoordinate(entityTopRow, entityRightCol);
                tileR = gamePanel.gameBoard.getCellAtCoordinate(entityBottomRow, entityRightCol);
                // Check for collisions with the tiles to the right
                if(gamePanel.gameBoard.getCellCollection().getCellType(tileL).collisionStatus() || gamePanel.gameBoard.getCellCollection().getCellType(tileR).collisionStatus()){
                    entity.collisionOn = true;
                }
                break;
        }

}
        /**
         * Checks for collisions with objects based on the current direction of the entity.
         *
         * @param entity The entity for which collisions are checked.
         * @param player A boolean indicating whether the entity is the player.
         * @return The index of the colliding object if found, or 999 if no collision.
         */
        public int checkObject (Entities entity, boolean player) {
            int index = 999;
            for (int i = 0; i < gamePanel.obj.length; i++) {
                if (gamePanel.obj[i] != null ) {
                    // Get entitys solid area position
                    entity.solidArea.x = entity.mapX + entity.solidArea.x;
                    entity.solidArea.y = entity.mapY + entity.solidArea.y;
                    // Get objects solid area position
                    gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x;
                    gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y;

                    switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true ){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true ){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true ){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true ){
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.defaultsolidAreaX;
                entity.solidArea.y = entity.defaultsolidAreaY;
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].defaultsolidAreaX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].defaultsolidAreaY;
                }



            }
            return index;
        }

        /**
         * Checks for collisions with other entities based on the current direction of the entity.
         *
         * @param entity The entity for which collisions are checked.
         * @param target An array of entities to check for collisions.
         * @return The index of the colliding entity if found, or 999 if no collision.
         */
        public int checkEntity(Entities entity, Entities[] target){
            int index = 999;
                for (int i = 0; i < target.length; i++) {
                    if (target[i] != null ) {
                        // Get entitys solid area position
                        entity.solidArea.x = entity.mapX + entity.solidArea.x;
                        entity.solidArea.y = entity.mapY + entity.solidArea.y;
                        // Get objects solid area position
                        target[i].solidArea.x = target[i].mapX + target[i].solidArea.x;
                        target[i].solidArea.y = target[i].mapY + target[i].solidArea.y;
    
                        switch(entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn =  true;
                                index = i;
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                            }
                            break;
                    }
    
                    entity.solidArea.x = entity.defaultsolidAreaX;
                    entity.solidArea.y = entity.defaultsolidAreaY;
                    target[i].solidArea.x = target[i].defaultsolidAreaX;
                    target[i].solidArea.y = target[i].defaultsolidAreaY;
                    }
    
                }
                return index;
        }
    
        /**
         * Checks for collisions with the player entity based on the current direction of the entity.
         *
         * @param entity The entity for which collisions are checked.
         */
        public void checkPlayer(Entities entity){
            // Get entitys solid area position
            entity.solidArea.x = entity.mapX + entity.solidArea.x;
            entity.solidArea.y = entity.mapY + entity.solidArea.y;
            // Get objects solid area position
            gamePanel.player.solidArea.x = gamePanel.player.mapX + gamePanel.player.solidArea.x;
            gamePanel.player.solidArea.y = gamePanel.player.mapY + gamePanel.player.solidArea.y;
    
            switch(entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn =  true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            }
            entity.solidArea.x = entity.defaultsolidAreaX;
            entity.solidArea.y = entity.defaultsolidAreaY;
            gamePanel.player.solidArea.x = gamePanel.player.defaultsolidAreaX;
            gamePanel.player.solidArea.y = gamePanel.player.defaultsolidAreaY;
    }
}
