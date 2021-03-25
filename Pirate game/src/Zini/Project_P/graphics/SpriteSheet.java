package Zini.Project_P.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class SpriteSheet
{
    private String path;
    public final int SIZE;
    public int[] pixels;


    public static SpriteSheet townTiles = new SpriteSheet("/textures/SpriteSheet_Town.png", 256);
    public static SpriteSheet islandTiles = new SpriteSheet("/textures/SpriteSheet_Beach.png", 160);
    public static SpriteSheet forestTiles = new SpriteSheet("/textures/SpriteSheet_Forest.png",80);
    public static SpriteSheet caveTiles = new SpriteSheet("/textures/SpriteSheet_Cave.png",256);
    public static SpriteSheet characters = new SpriteSheet("/textures/SpriteSheet_Playable.png", 256);
    public static SpriteSheet npc = new SpriteSheet("/textures/SpriteSheet_NPC.png", 160);




    public SpriteSheet(String p,int s)
    {
        path= p;
        SIZE = s;

        pixels = new int[SIZE * SIZE];
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void load() throws IOException {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
