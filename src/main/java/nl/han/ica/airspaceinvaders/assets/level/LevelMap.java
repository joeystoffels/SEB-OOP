package nl.han.ica.airspaceinvaders.assets.level;

import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.airspaceinvaders.AirspaceInvadersGame;
import nl.han.ica.airspaceinvaders.assets.config.GameProperties;
import nl.han.ica.airspaceinvaders.state.EndLevelState;
import processing.core.PGraphics;

public class LevelMap extends TileMap {

    private int travelHeight = 0;
    private int visibleTiles;
    private int visibleTilesOffset;
    private Boolean levelEnded = false;
    private int levelNumber;
    private AirspaceInvadersGame game;

    /**
     * Check if the level is ended
     * @return Boolean
     */
    public Boolean getLevelEnded() {
        return this.levelEnded;
    }

    /**
     * Levelmap extends a Tilemap, and only draws the visual part of the map instead of drawing everything.
     * @param tileSize int
     * @param tileTypes TileType[]
     * @param indexMap int[][]
     */
    public LevelMap(AirspaceInvadersGame game, int tileSize, TileType[] tileTypes, int[][] indexMap, int levelNumber) {
        super(tileSize, tileTypes, indexMap);
        this.levelNumber = levelNumber;
        this.game = game;

        // Set the total heigth of the level
        this.travelHeight = super.getTileMap().length * super.getTileSize();

        // Set the amount of tiles visible
        this.visibleTiles = GameProperties.getValueAsInt("worldHeight") / super.getTileSize() + 1;
        this.visibleTilesOffset = super.getTileSize() - (GameProperties.getValueAsInt("worldHeight") % super.getTileSize());
    }

    public LevelMap(int tileSize) {
        super(tileSize);
    }

    /**
     * Draw function that only draws the tiles that are visible of the level
     * @param pGraphics The canvas on which the tiles will be drawn.
     */
    @Override
    public void draw(PGraphics pGraphics) {
        int scrollSpeed = 2;
        Tile[][] tileMap = super.getTileMap();

        int amountOfTilesLeft = this.travelHeight / super.getTileSize() + 1;
        int amountOfPixelsOffset = super.getTileSize() - (this.travelHeight % super.getTileSize());

        if (tileMap != null && super.getIndexMap() != null) {
            for (int indexVertical = 0; indexVertical < tileMap.length; indexVertical++) {

                // Don't render images that fall out of the scope of the state
                int tileOffset = amountOfTilesLeft - this.visibleTiles - 2;
                if (indexVertical > tileOffset) {

                    for (int indexHorizontal = 0; indexHorizontal < tileMap[indexVertical].length; indexHorizontal++) {
                        int horizontalPosition = indexHorizontal * super.getTileSize();
                        int verticalPosition = ((indexVertical - tileOffset) * super.getTileSize()) + amountOfPixelsOffset - (2 * super.getTileSize()) - this.visibleTilesOffset;

                        pGraphics.image(tileMap[indexVertical][indexHorizontal].getSprite().getPImage(), horizontalPosition, verticalPosition);
                    }
                }
            }
            // Set the new travelheight
            this.travelHeight -= scrollSpeed;

            // Check if the level is ended
            if((this.travelHeight - GameProperties.getValueAsInt("worldHeight")) < 0){
                this.game.changeView(new EndLevelState(this.game, this.levelNumber));
            }
        }
    }
}
