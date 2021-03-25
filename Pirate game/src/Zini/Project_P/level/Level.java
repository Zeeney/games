package Zini.Project_P.level;

import Zini.Project_P.graphics.Screen;
import Zini.Project_P.level.tile.Tile;


public class Level
{
    protected int width, height;
    protected int[] tiles;

    public String levelName;


    public Level(String path)
    {
        loadLevel(path);
    }


    protected void loadLevel(String path)
    {

    }


    public void render(int xScroll, int yScroll, Screen screen)
    {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y =y0; y < y1; y++)
        {
            for (int x = x0; x < x1; x++)
            {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        //tiles for town map
        if (tiles[x + y * width] == 0xff22B14C) return Tile.grass;
        if (tiles[x + y * width] == 0xff092D13) return Tile.treeTop;
        if (tiles[x + y * width] == 0xff0C3D1A) return Tile.treeMid;
        if (tiles[x + y * width] == 0xff0D441D) return Tile.treeBottom;
        if (tiles[x + y * width] == 0xff003F0F) return Tile.grassShadow;
        if (tiles[x + y * width] == 0xff004C11) return Tile.grassHShadow;
        if (tiles[x + y * width] == 0xff87870E) return Tile.rock;
        if (tiles[x + y * width] == 0xffC19F24) return Tile.houseDoor;
        if (tiles[x + y * width] == 0xff725E15) return Tile.roofChimney;
        if (tiles[x + y * width] == 0xffFFD147) return Tile.roofBlank;
        if (tiles[x + y * width] == 0xffFFE8A0) return Tile.houseBlank;
        if (tiles[x + y * width] == 0xffFFFDDD) return Tile.houseDWindow;
        if (tiles[x + y * width] == 0xff808080) return Tile.rockWallL;
        if (tiles[x + y * width] == 0xff404040) return Tile.rockWallM;
        if (tiles[x + y * width] == 0xff303030) return Tile.rockWallD;

        //tiles for island map
        if (tiles[x + y * width] == 0xff20B218) return Tile.igrass;
        if (tiles[x + y * width] == 0xffF5FF49) return Tile.isand;
        if (tiles[x + y * width] == 0xff3D8DFF) return Tile.ishallowwater;
        if (tiles[x + y * width] == 0xff365C96) return Tile.imidwater;
        if (tiles[x + y * width] == 0xff162897) return Tile.ideepwater;
        if (tiles[x + y * width] == 0xff633C15) return Tile.imounttl;
        if (tiles[x + y * width] == 0xffB58226) return Tile.imountt;
        if (tiles[x + y * width] == 0xffB56E27) return Tile.imounttr;
        if (tiles[x + y * width] == 0xff633003) return Tile.imountml;
        if (tiles[x + y * width] == 0xffB59F10) return Tile.imountm;
        if (tiles[x + y * width] == 0xffFF7C0A) return Tile.imountmr;
        if (tiles[x + y * width] == 0xffFFA100) return Tile.imountbl;
        if (tiles[x + y * width] == 0xff8E5A00) return Tile.imountb;
        if (tiles[x + y * width] == 0xffE59100) return Tile.imountbr;
        if (tiles[x + y * width] == 0xff603D00) return Tile.imountpt;
        if (tiles[x + y * width] == 0xff22B14C) return Tile.imountpb;
        if (tiles[x + y * width] == 0xff874904) return Tile.itownw;
        if (tiles[x + y * width] == 0xffCE7106) return Tile.itownb;
        if (tiles[x + y * width] == 0xff228E07) return Tile.itreestl;
        if (tiles[x + y * width] == 0xff02E52B) return Tile.itreest;
        if (tiles[x + y * width] == 0xff37E50B) return Tile.itreestr;
        if (tiles[x + y * width] == 0xff135104) return Tile.itreesml;
        if (tiles[x + y * width] == 0xff00440D) return Tile.itreesm;
        if (tiles[x + y * width] == 0xff135104) return Tile.itreesmr;
        if (tiles[x + y * width] == 0xff1EA517) return Tile.itreesbl;
        if (tiles[x + y * width] == 0xff14700F) return Tile.itreesb;
        if (tiles[x + y * width] == 0xff0E510B) return Tile.itreesbr;

        //tiles for forest map
        if (tiles[x + y * width] == 0xff85620d) return Tile.fgrass;
        if (tiles[x + y * width] == 0xff1b923e) return Tile.ftreebl;
        if (tiles[x + y * width] == 0xff14672d) return Tile.ftreebm;
        if (tiles[x + y * width] == 0xff1c7035) return Tile.ftreebr;
        if (tiles[x + y * width] == 0xff0d8b32) return Tile.ftreeml;
        if (tiles[x + y * width] == 0xff0bc341) return Tile.ftreemm;
        if (tiles[x + y * width] == 0xff008126) return Tile.ftreemr;
        if (tiles[x + y * width] == 0xff229f47) return Tile.ftreetl;
        if (tiles[x + y * width] == 0xff106016) return Tile.ftreetm;
        if (tiles[x + y * width] == 0xff09812c) return Tile.ftreetr;

        //tiles for cliff and cave map
        if (tiles[x + y * width] == 0xffEF540B) return Tile.cground;
        if (tiles[x + y * width] == 0xff822D06) return Tile.cwalld;
        if (tiles[x + y * width] == 0xffC14309) return Tile.cwalll;
        if (tiles[x + y * width] == 0xffDD48DF) return Tile.cchest;
        if (tiles[x + y * width] == 0xffD8A50D) return Tile.clground;
        if (tiles[x + y * width] == 0xff7E610C) return Tile.clwalld;
        if (tiles[x + y * width] == 0xff9F822B) return Tile.clwalll;

        //tiles to teleport
        if (tiles[x + y * width] == 0xffB213E5) return Tile.townToIsland;
        if (tiles[x + y * width] == 0xffADE34B) return Tile.townToForest;
        if (tiles[x + y * width] == 0xffD69C13) return Tile.forestToTown;
        if (tiles[x + y * width] == 0xffC7900D) return Tile.forestToCliff;
        if (tiles[x + y * width] == 0xff05FAB0) return Tile.cliffToForest;
        if (tiles[x + y * width] == 0xffFF0080) return Tile.cliffToCave;
        if (tiles[x + y * width] == 0xff91C96A) return Tile.caveToCliff;
        if (tiles[x + y * width] == 0xff6EE818) return Tile.caveToTreasure;
        if (tiles[x + y * width] == 0xff4896DF) return Tile.treasureToCave;

        if (tiles[x + y * width] == 0xff0E427A) return Tile.shop;


        return Tile.voidTile;
    }
}
