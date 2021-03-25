package Zini.Project_P.graphics;

import Zini.Project_P.level.tile.Tile;

import java.awt.*;
import java.util.Random;


public class Screen
{

    public int width, height;
    public int[] pixels;

    public int xOffset, yOffset;


    public Screen(int w, int h)
    {
        height = h;
        width = w;
        pixels = new int[w * h];

    }

    public void clear()
    {
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0;
        }
    }


    public void renderTile(int xp, int yp, Tile t)
    {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < t.sprite.SIZE; y++)
        {
            int ya = y + yp;
            for (int x = 0; x < t.sprite.SIZE; x++)
            {
                int xa = x + xp;
                if (xa < -t.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0 ) xa = 0;
                pixels[xa + ya * width] = t.sprite.pixels[(x & 15) + (y & 15) * t.sprite.SIZE];
            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite s, int flip)
    {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < s.SIZE; y++)
        {
            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) ys = 23 - y;
            for (int x = 0; x < s.SIZE; x++)
            {
                int xa = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) xs = 15 - x;
                if (xa < -s.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0 ) xa = 0;
                int col = s.pixels[(xs & 31) + (ys & 31) * s.SIZE];
                if (col != 0xffE500FF) pixels[xa + ya * width] = col;
            }
        }
    }



    public void setOffset(int x, int y)
    {
        this.xOffset=x;
        this.yOffset=y;
    }
}
