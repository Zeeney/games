package Zini.Project_P.entity.characters;

import Zini.Project_P.graphics.Screen;
import Zini.Project_P.graphics.Sprite;
import Zini.Project_P.level.Level;
import Zini.Project_P.level.tile.Tile;

import java.util.ArrayList;

/**
 * Created by szini on 04/12/2017.
 */
public class NPC extends Character{
    protected Sprite sprite;
    protected Level level;

    public static NPC npc1 = new NPC(4*16,10*16, Sprite.npc1);
    public static NPC npc2 = new NPC(10*16,14*16, Sprite.npc2);
    public static NPC npc3 = new NPC(25*16,25*16, Sprite.npc3);
    public static NPC npc4 = new NPC(28*16,27*16, Sprite.npc4);
    public static NPC npc5 = new NPC(2*16,24*16, Sprite.npc5);
    public static NPC npc6 = new NPC(30*16,20*16, Sprite.npc6);
    public static NPC npc7 = new NPC(18*16,25*16, Sprite.npc7);
    public static NPC npc8 = new NPC(7*16,26*16, Sprite.npc8);



    public NPC(int x, int y, Sprite sprite)
    {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void render(Screen s)
    {
        s.renderPlayer(x,y,sprite,0);
    }

}
