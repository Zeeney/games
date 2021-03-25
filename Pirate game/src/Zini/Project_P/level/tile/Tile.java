package Zini.Project_P.level.tile;

import Zini.Project_P.graphics.Screen;
import Zini.Project_P.graphics.Sprite;

import java.util.ArrayList;


public class Tile
{
    public int x, y;
    public Sprite sprite;

    //Tiles That will be use in the the Town Map
    public static Tile grass = new Tile(Sprite.grass);
    public static Tile rock = new Tile(Sprite.rock);
    public static Tile treeMid = new Tile(Sprite.treeMid);
    public static Tile treeTop = new Tile(Sprite.treeTop);
    public static Tile treeBottom = new Tile(Sprite.treeBottom);
    public static Tile grassShadow = new Tile(Sprite.grassShadow);
    public static Tile grassHShadow = new Tile(Sprite.grassHShadow);
    public static Tile houseBlank = new Tile(Sprite.houseBlank);
    public static Tile houseSWindow = new Tile(Sprite.houseSWindow);
    public static Tile houseDWindow = new Tile(Sprite.houseDWindow);
    public static Tile houseDoor = new Tile(Sprite.houseDoor);
    public static Tile roofBlank = new Tile(Sprite.roofBlank);
    public static Tile roofTriRight = new Tile(Sprite.roofTriRight);
    public static Tile roofTriLeft = new Tile(Sprite.roofTriLeft);
    public static Tile roofChimney= new Tile(Sprite.roofChimney);
    public static Tile resHouseRoof = new Tile(Sprite.resHouseRoof);
    public static Tile resHouseRoofT = new Tile(Sprite.resHouseRoofT);
    public static Tile rockWallL = new Tile(Sprite.rockWallL);
    public static Tile rockWallM = new Tile(Sprite.rockWallM);
    public static Tile rockWallD = new Tile(Sprite.rockWallD);

    //Tiles that will be used in the island map
    public static Tile igrass = new Tile(Sprite.igrass);
    public static Tile isand = new Tile(Sprite.isand);
    public static Tile ishallowwater = new Tile(Sprite.ishallowwater);
    public static Tile imidwater = new Tile(Sprite.imidwater);
    public static Tile ideepwater = new Tile(Sprite.ideepwater);
    public static Tile imounttl = new Tile(Sprite.imounttl);
    public static Tile imountt = new Tile(Sprite.imountt);
    public static Tile imounttr = new Tile(Sprite.imounttr);
    public static Tile imountml = new Tile(Sprite.imountml);
    public static Tile imountm = new Tile(Sprite.imountm);
    public static Tile imountmr = new Tile(Sprite.imountmr);
    public static Tile imountbl = new Tile(Sprite.imountbl);
    public static Tile imountb = new Tile(Sprite.imountb);
    public static Tile imountbr = new Tile(Sprite.imountbr);
    public static Tile imountpt = new Tile(Sprite.imountpt);
    public static Tile imountpb = new Tile(Sprite.imountpb);

    public static Tile itreestl = new Tile(Sprite.itreestl);
    public static Tile itreest = new Tile(Sprite.itreest);
    public static Tile itreestr = new Tile(Sprite.itreestr);
    public static Tile itreesml = new Tile(Sprite.itreesml);
    public static Tile itreesm = new Tile(Sprite.itreesm);
    public static Tile itreesmr = new Tile(Sprite.itreesmr);
    public static Tile itreesbl = new Tile(Sprite.itreesbl);
    public static Tile itreesb = new Tile(Sprite.itreesb);
    public static Tile itreesbr = new Tile(Sprite.itreesbr);

    //Tiles that will be used in the island map
    public static Tile fgrass = new Tile(Sprite.fgrass);
    public static Tile ftreebl = new Tile(Sprite.ftreebl);
    public static Tile ftreebm = new Tile(Sprite.ftreebm);
    public static Tile ftreebr = new Tile(Sprite.ftreebr);
    public static Tile ftreeml = new Tile(Sprite.ftreeml);
    public static Tile ftreemm = new Tile(Sprite.ftreemm);
    public static Tile ftreemr = new Tile(Sprite.ftreemr);
    public static Tile ftreetl = new Tile(Sprite.ftreetl);
    public static Tile ftreetm = new Tile(Sprite.ftreetm);
    public static Tile ftreetr = new Tile(Sprite.ftreetr);

    //Tiles that will be used in the cave and cliff map
    public static Tile cground = new Tile(Sprite.cground);
    public static Tile cwalld = new Tile(Sprite.cwalld);
    public static Tile cwalll = new Tile(Sprite.cwalll);
    public static Tile cchest = new Tile(Sprite.cchest);
    public static Tile clground = new Tile(Sprite.clground);
    public static Tile clwalld = new Tile(Sprite.clwalld);
    public static Tile clwalll = new Tile(Sprite.clwalll);

    //teleporters
    public static Tile itownw = new Tile(Sprite.itownw);
    public static Tile itownb = new Tile(Sprite.itownb);
    public static Tile townToIsland = new Tile(Sprite.grass);
    public static Tile townToForest = new Tile(Sprite.treeBottom);
    public static Tile forestToTown = new Tile(Sprite.fgrass);
    public static Tile forestToCliff = new Tile(Sprite.fgrass);
    public static Tile cliffToForest = new Tile(Sprite.clground);
    public static Tile cliffToCave = new Tile(Sprite.clground);
    public static Tile caveToCliff = new Tile(Sprite.cground);
    public static Tile caveToTreasure = new Tile(Sprite.cground);
    public static Tile treasureToCave = new Tile(Sprite.cground);

    public static Tile shop = new Tile(Sprite.houseDoor);

    public static Tile voidTile = new Tile(Sprite.voidSprite);



    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen)
    {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid(Tile tile)
    {
        if (tile == grass || tile == grassHShadow ||tile == grassShadow || tile == rock
                || tile == igrass || tile == isand || tile  == ishallowwater ||tile == imidwater
                || tile == ideepwater || tile == fgrass || tile == cground || tile == clground) return false;
        else return true;
    }

    public String location(Tile tile){
        if (tile == igrass)return "field";
        if (tile == isand)return "beach";
        if (tile == ishallowwater || tile == imidwater || tile == ideepwater)return "boat";
        if (tile == fgrass) return "forest";
        if (tile == cground || tile == clground)return "cave";

        return "";
    }

    public String changer(Tile tile){
        if (tile == itownb || tile == itownw ){
            return "town entrance";
        }
        if (tile == townToIsland) {
            return "island";
        }
        if (tile == townToForest) {
            return "forest entrance";
        }
        if (tile == forestToTown) {
            return "town exit";
        }
        if (tile == forestToCliff) {
            return "cliff entrance";
        }
        if (tile == cliffToForest) {
            return "forest exit";
        }
        if (tile == cliffToCave) {
            return "cave entrance";
        }
        if (tile == caveToCliff) {
            return "cliff exit";
        }
        if (tile == caveToTreasure) {
            return "treasure";
        }
        if (tile == treasureToCave) {
            return "cave exit";
        }
        if (tile == shop){
            return "shop";
        }
        if (tile == cchest){
            return "chest";
        }

        return "";
    }


}
