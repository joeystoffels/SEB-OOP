package nl.han.ica.airspaceinvaders.level;

import nl.han.ica.OOPDProcessingEngineHAN.Logger.DefaultLogger;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.LogFactory;
import nl.han.ica.OOPDProcessingEngineHAN.Logger.Logger;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.airspaceinvaders.config.GameProperties;
import processing.core.PGraphics;

public class LevelMap extends TileMap {

    private int travelHeight = 0;
    private int travelWidth = 0;
    private int scrollSpeed = 6 ;

    private int visibleTiles;
    private int visibleTilesOffset;


    private Logger logger = LogFactory.getLogger();

    public LevelMap(int tileSize, TileType[] tileTypes, int[][] indexMap) {
        super(tileSize, tileTypes, indexMap);

        // Set the total heigth of the level
        this.travelHeight = super.getTileMap().length * super.getTileSize();

        // Set the amount of tiles visible
        this.visibleTiles = GameProperties.getValue("worldHeight", true) / super.getTileSize() + 1;
        this.visibleTilesOffset = super.getTileSize() - (GameProperties.getValue("worldHeight", true) % super.getTileSize());
        System.out.println(" d");
    }

    public LevelMap(int tileSize) {
        super(tileSize);
    }

    @Override
    public void draw(PGraphics pGraphics) {
        Tile[][] tileMap = super.getTileMap();

        int amountOfTilesLeft = this.travelHeight / super.getTileSize() + 1;
        int amountOfPixelsOffset = super.getTileSize() - (this.travelHeight % super.getTileSize());

        if (tileMap != null && super.getIndexMap() != null) {


            for (int indexVertical = 0; indexVertical < tileMap.length; indexVertical++) {

                // Don't render images that fall out of the scope of the view
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
            this.travelHeight -= this.scrollSpeed;
        }
    }
}
