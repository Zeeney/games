package Zini.Project_P.level;

import Zini.Project_P.graphics.SpriteSheet;
import Zini.Project_P.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by szini on 02/11/2017.
 */
public class LevelReader extends Level {


    public static Level townLevel = new LevelReader("/textures/LevelMap_Town.png", "Town");
    public static Level islandLevel = new LevelReader("/textures/LevelMap_Island.png", "Island");
    public static Level forestLevel = new LevelReader("/textures/LevelMap_Forest.png", "Forest");
    public static Level caveLevel = new LevelReader("/textures/LevelMap_Cave.png", "Cave");
    public static Level cliffLevel = new LevelReader("/textures/LevelMap_Cliff.png", "Cliff");
    public static Level treasureRoomLevel = new LevelReader("/textures/LevelMap_TreasureRoom.png", "Treasure Room");

    public LevelReader(String path, String name)
    {
        super(path);
        levelName = name;

    }

    protected void loadLevel(String path) {
        try{
            BufferedImage image = ImageIO.read(LevelReader.class.getResource(path));
            int h = height = image.getHeight();
            int w = width = image.getWidth();

            tiles = new int[w*h];
            image.getRGB(0,0,w , h, tiles, 0, w);

        }catch (IOException e){
            System.out.println("Cannot load file");
        }
    }

}
