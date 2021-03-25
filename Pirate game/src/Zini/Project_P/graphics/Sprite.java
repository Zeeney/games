package Zini.Project_P.graphics;


public class Sprite
{
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    //Sprites for the Tiles that will be use in the the Town Map
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.townTiles);
    public static Sprite grassShadow = new Sprite(16, 2, 1, SpriteSheet.townTiles);
    public static Sprite grassHShadow = new Sprite(16, 3, 1, SpriteSheet.townTiles);
    public static Sprite treeBottom = new Sprite(16, 3, 0, SpriteSheet.townTiles);
    public static Sprite treeMid = new Sprite(16, 1, 0, SpriteSheet.townTiles);
    public static Sprite treeTop = new Sprite(16, 2, 0, SpriteSheet.townTiles);
    public static Sprite rock = new Sprite(16, 0, 1, SpriteSheet.townTiles);
    public static Sprite houseBlank = new Sprite(16, 1, 1, SpriteSheet.townTiles);
    public static Sprite houseSWindow = new Sprite(16, 3, 2, SpriteSheet.townTiles);
    public static Sprite houseDWindow = new Sprite(16, 2, 2, SpriteSheet.townTiles);
    public static Sprite houseDoor = new Sprite(16, 4, 2, SpriteSheet.townTiles);
    public static Sprite roofBlank = new Sprite(16, 0, 2, SpriteSheet.townTiles);
    public static Sprite roofTriRight = new Sprite(16, 0, 3, SpriteSheet.townTiles);
    public static Sprite roofTriLeft = new Sprite(16, 1, 3, SpriteSheet.townTiles);
    public static Sprite roofChimney = new Sprite(16, 0, 4, SpriteSheet.townTiles);
    public static Sprite resHouseRoof = new Sprite(16, 4, 1, SpriteSheet.townTiles);
    public static Sprite resHouseRoofT = new Sprite(16, 4, 0, SpriteSheet.townTiles);
    public static Sprite rockWallL = new Sprite(16, 3, 3, SpriteSheet.townTiles);
    public static Sprite rockWallM = new Sprite(16, 4, 3, SpriteSheet.townTiles);
    public static Sprite rockWallD = new Sprite(16, 2, 3, SpriteSheet.townTiles);

    //Sprites for the Tiles that will be use in the the Island Map
    public static Sprite igrass = new Sprite(16, 0, 0, SpriteSheet.islandTiles);
    public static Sprite isand = new Sprite(16, 1, 0, SpriteSheet.islandTiles);
    public static Sprite ishallowwater = new Sprite(16, 2, 0, SpriteSheet.islandTiles);
    public static Sprite imidwater = new Sprite(16, 3, 0, SpriteSheet.islandTiles);
    public static Sprite ideepwater = new Sprite(16, 4, 0, SpriteSheet.islandTiles);
    public static Sprite imounttl = new Sprite(16, 0, 0, SpriteSheet.islandTiles);
    public static Sprite imountt = new Sprite(16, 1, 1, SpriteSheet.islandTiles);
    public static Sprite imounttr = new Sprite(16, 2, 1, SpriteSheet.islandTiles);
    public static Sprite imountml = new Sprite(16, 0, 2, SpriteSheet.islandTiles);
    public static Sprite imountm = new Sprite(16, 1, 2, SpriteSheet.islandTiles);
    public static Sprite imountmr = new Sprite(16, 2, 2, SpriteSheet.islandTiles);
    public static Sprite imountbl = new Sprite(16, 0, 3, SpriteSheet.islandTiles);
    public static Sprite imountb = new Sprite(16, 1, 3, SpriteSheet.islandTiles);
    public static Sprite imountbr = new Sprite(16, 2, 3, SpriteSheet.islandTiles);
    public static Sprite imountpt = new Sprite(16, 3, 1, SpriteSheet.islandTiles);
    public static Sprite imountpb = new Sprite(16, 3, 2, SpriteSheet.islandTiles);
    public static Sprite itownw = new Sprite(16, 4, 1, SpriteSheet.islandTiles);
    public static Sprite itownb = new Sprite(16, 4, 2, SpriteSheet.islandTiles);
    public static Sprite itreestl = new Sprite(16, 0, 4, SpriteSheet.islandTiles);
    public static Sprite itreest = new Sprite(16, 1, 4, SpriteSheet.islandTiles);
    public static Sprite itreestr = new Sprite(16, 2, 4, SpriteSheet.islandTiles);
    public static Sprite itreesml = new Sprite(16, 0, 5, SpriteSheet.islandTiles);
    public static Sprite itreesm = new Sprite(16, 1, 5, SpriteSheet.islandTiles);
    public static Sprite itreesmr = new Sprite(16, 2, 5, SpriteSheet.islandTiles);
    public static Sprite itreesbl = new Sprite(16, 0, 6, SpriteSheet.islandTiles);
    public static Sprite itreesb = new Sprite(16, 1, 6, SpriteSheet.islandTiles);
    public static Sprite itreesbr = new Sprite(16, 2, 6, SpriteSheet.islandTiles);

    //Sprites for the Tiles that will be use in the the forest Map
    public static Sprite fgrass = new Sprite(16, 2, 1, SpriteSheet.forestTiles);
    public static Sprite ftreebl = new Sprite(16, 0, 4, SpriteSheet.forestTiles);
    public static Sprite ftreebm = new Sprite(16, 0, 2, SpriteSheet.forestTiles);
    public static Sprite ftreebr = new Sprite(16, 1, 4, SpriteSheet.forestTiles);
    public static Sprite ftreeml = new Sprite(16, 0, 1, SpriteSheet.forestTiles);
    public static Sprite ftreemm = new Sprite(16, 0, 0, SpriteSheet.forestTiles);
    public static Sprite ftreemr = new Sprite(16, 1, 1, SpriteSheet.forestTiles);
    public static Sprite ftreetl = new Sprite(16, 1, 0, SpriteSheet.forestTiles);
    public static Sprite ftreetm = new Sprite(16, 3, 0, SpriteSheet.forestTiles);
    public static Sprite ftreetr = new Sprite(16, 4, 0, SpriteSheet.forestTiles);

    //Sprites for the Tiles that will be use in the the cave/cliff Map
    public static Sprite cground = new Sprite(16, 0, 0, SpriteSheet.caveTiles);
    public static Sprite cwalld = new Sprite(16, 1, 0, SpriteSheet.caveTiles);
    public static Sprite cwalll = new Sprite(16, 2, 0, SpriteSheet.caveTiles);
    public static Sprite cchest = new Sprite(16, 1, 3, SpriteSheet.caveTiles);
    public static Sprite clground = new Sprite(16, 3, 1, SpriteSheet.caveTiles);
    public static Sprite clwalld = new Sprite(16, 3, 0, SpriteSheet.caveTiles);
    public static Sprite clwalll = new Sprite(16, 4, 0, SpriteSheet.caveTiles);


    //FF6 Dancer player character
    public static Sprite playerDancerD = new Sprite(32, 0, 0, SpriteSheet.characters);
    public static Sprite playerDancerD1 = new Sprite(32, 0, 1, SpriteSheet.characters);
    public static Sprite playerDancerD2 = new Sprite(32, 1, 0, SpriteSheet.characters);

    public static Sprite playerDancerU = new Sprite(32, 3, 0, SpriteSheet.characters);
    public static Sprite playerDancerU1 = new Sprite(32, 3, 1, SpriteSheet.characters);

    public static Sprite playerDancerL = new Sprite(32, 2, 0, SpriteSheet.characters);
    public static Sprite playerDancerL1 = new Sprite(32, 2, 1, SpriteSheet.characters);
    public static Sprite playerDancerL2 = new Sprite(32, 1, 1, SpriteSheet.characters);

    //FF6 Locke player character
    public static Sprite playerLockeD = new Sprite(32, 0, 2, SpriteSheet.characters);
    public static Sprite playerLockeD1 = new Sprite(32, 0, 3, SpriteSheet.characters);

    public static Sprite playerLockeU = new Sprite(32, 1, 2, SpriteSheet.characters);
    public static Sprite playerLockeU1 = new Sprite(32, 1, 3, SpriteSheet.characters);

    public static Sprite playerLockeL = new Sprite(32, 2, 2, SpriteSheet.characters);
    public static Sprite playerLockeL1 = new Sprite(32, 2, 3, SpriteSheet.characters);
    public static Sprite playerLockeL2 = new Sprite(32, 3, 2, SpriteSheet.characters);

    //FF6 Leo player character
    public static Sprite playerLeoD = new Sprite(32, 0, 4, SpriteSheet.characters);
    public static Sprite playerLeoD1 = new Sprite(32, 0, 5, SpriteSheet.characters);

    public static Sprite playerLeoU = new Sprite(32, 1, 4, SpriteSheet.characters);
    public static Sprite playerLeoU1 = new Sprite(32, 1, 5, SpriteSheet.characters);

    public static Sprite playerLeoL = new Sprite(32, 2, 4, SpriteSheet.characters);
    public static Sprite playerLeoL1 = new Sprite(32, 2, 5, SpriteSheet.characters);
    public static Sprite playerLeoL2 = new Sprite(32, 3, 4, SpriteSheet.characters);

    //FF6 Setzer player character
    public static Sprite playerSetzerD = new Sprite(32, 0, 6, SpriteSheet.characters);
    public static Sprite playerSetzerD1 = new Sprite(32, 0, 7, SpriteSheet.characters);

    public static Sprite playerSetzerU = new Sprite(32, 1, 6, SpriteSheet.characters);
    public static Sprite playerSetzerU1 = new Sprite(32, 1, 7, SpriteSheet.characters);

    public static Sprite playerSetzerL = new Sprite(32, 2, 6, SpriteSheet.characters);
    public static Sprite playerSetzerL1 = new Sprite(32, 2, 7, SpriteSheet.characters);
    public static Sprite playerSetzerL2 = new Sprite(32, 3, 6, SpriteSheet.characters);

    //NPC town characters
    public static Sprite npc1= new Sprite(32, 0, 0, SpriteSheet.npc);
    public static Sprite npc2 = new Sprite(32, 0, 1, SpriteSheet.npc);
    public static Sprite npc3 = new Sprite(32, 1, 0, SpriteSheet.npc);
    public static Sprite npc4 = new Sprite(32, 1, 1, SpriteSheet.npc);
    public static Sprite npc5 = new Sprite(32, 2, 0, SpriteSheet.npc);
    public static Sprite npc6 = new Sprite(32, 2, 1, SpriteSheet.npc);
    public static Sprite npc7 = new Sprite(32, 3, 0, SpriteSheet.npc);
    public static Sprite npc8 = new Sprite(32, 3, 1, SpriteSheet.npc);



    public static Sprite voidSprite = new Sprite(16,0);

    public Sprite(int s, int x, int y, SpriteSheet sheet)
    {
        SIZE = s;
        pixels =  new int[SIZE * SIZE];
        this.x = x * s;
        this.y = y * s;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int colour)
    {
        SIZE = size;
        pixels = new int [SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour)
    {
        for (int i = 0; i<SIZE*SIZE; i++)
        {
            pixels[i] = colour;
        }
    }

    private void load()
    {
        for (int y = 0; y < SIZE; y++)
        {
            for (int x = 0; x < SIZE; x++)
            {
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
