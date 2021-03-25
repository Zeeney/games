package Zini.Project_P.entity.characters;

import Zini.Project_P.graphics.Sprite;
import Zini.Project_P.input.Keyboard;
import Zini.Project_P.level.Level;
import Zini.Project_P.level.LevelReader;
import Zini.Project_P.level.tile.Tile;


public abstract class Character
{
    public boolean boss;
    public int x, y;
    protected Sprite sprite;
    protected int dir = 0;
    public boolean moving = false;
    public Level level;
    protected Keyboard input;
    public String location;

    public void move(int xc, int yc)
    {
        if (xc > 0) dir = 1;
        else if (xc < 0) dir = 3;
        else if (yc > 0) dir = 2;
        else if (yc < 0) dir = 0;

        if (!collision(xc, 0))
        {
            x += xc;
        }

        if (!collision(0, yc))
        {
            y += yc;
        }

        changelevel(xc, yc);
        curLocation(xc, yc);
    }


    private void changelevel(int xc, int yc)
    {
        for (int i = 0; i < 4; i++){
            int xa = ((x+xc) + i % 2 *11+2) /16;
            int ya = ((y+yc) + i / 2 *13 + 10) /16;
            Tile tile = level.getTile(xa, ya);
            if (tile.changer(tile) == "town entrance"){
                level = LevelReader.townLevel;
                x = 16*16;
                y = 29*16;
            }
            if (tile.changer(tile) == "town exit"){
                level = LevelReader.townLevel;
                x = 16*16;
                y = 5*16;
            }
            if (tile.changer(tile) == "island"){
                level = LevelReader.islandLevel;
                x = 33*16;
                y = 36*16;
            }
            if (tile.changer(tile) == "forest entrance"){
                level = LevelReader.forestLevel;
                x = 23*16;
                y = 29*16;
            }
            if (tile.changer(tile) == "forest exit"){
                level = LevelReader.forestLevel;
                x = 3*16;
                y = 2*16;
            }
            if (tile.changer(tile) == "cliff entrance"){
                level = LevelReader.cliffLevel;
                x = 24*16;
                y = 29*16;
            }
            if (tile.changer(tile) == "cliff exit"){
                level = LevelReader.cliffLevel;
                x = 1*16;
                y = 2*16;
            }
            if (tile.changer(tile) == "cave entrance"){
                level = LevelReader.caveLevel;
                x = 14*16;
                y = 29*16;
            }
            if (tile.changer(tile) == "cave exit"){
                level = LevelReader.caveLevel;
                x = 1*16;
                y = 24*16;
            }
            if (tile.changer(tile) == "treasure"){
                level = LevelReader.treasureRoomLevel;
                x = 30*16;
                y = 16*16;
            }
            if (tile.changer(tile) == "shop"){
                input.enterShop = true;
            }
            if (tile.changer(tile) == "chest"){
                boss = true;
            }
        }

    }
    private void curLocation(int xc, int yc)
    {
        for (int i = 0; i < 4; i++){
            int xa = ((x+xc) + i % 2 *11+2) /16;
            int ya = ((y+yc) + i / 2 *13 + 10) /16;
            Tile tile = level.getTile(xa, ya);
            location = tile.location(tile);
        }

    }

    private boolean collision(int xc, int yc)
    {
        boolean ret = false;
        for (int i = 0; i < 4; i++){
            int xa = ((x+xc) + i % 2 *11+2) /16;
            int ya = ((y+yc) + i / 2 *13 + 10) /16;
            Tile tile = level.getTile(xa, ya);
            if (tile.solid(tile)) ret =  true;
        }
        return ret;
    }



}
