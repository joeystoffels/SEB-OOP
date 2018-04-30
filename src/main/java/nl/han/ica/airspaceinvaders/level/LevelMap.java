package nl.han.ica.airspaceinvaders.level;

import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import processing.core.PGraphics;

public class LevelMap extends TileMap {

    private int[][] indexMap;
    private Tile[][] tileMap;

    public LevelMap(int tileSize, TileType[] tileTypes, int[][] indexMap) {
        super(tileSize, tileTypes, indexMap);
        this.indexMap = indexMap;

    }

    public LevelMap(int tileSize) {
        super(tileSize);
    }

    @Override
    public void draw(PGraphics pGraphics) {
        Tile[][] tileMap = super.getTileMap();
        if (tileMap != null && indexMap != null) {
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    pGraphics.image(tileMap[i][j].getSprite().getPImage(), j * super.getTileSize(), i * super.getTileSize());
                }
            }
        }
    }
}
