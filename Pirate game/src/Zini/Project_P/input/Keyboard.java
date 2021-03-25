package Zini.Project_P.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Keyboard implements KeyListener
{
    private boolean[] keys = new boolean[180];

    public boolean up, down, left, right;
    public boolean menter;
    public boolean mback;
    public boolean enterMenu = false;
    public boolean enterShop = false;
    public boolean enterBattle = false;
    public boolean godMode;

    public int mdir = -1;
    public int playerchange = 0;

    public long time = System.currentTimeMillis();
    public long ntime;


    public void update() {
        if (keys[KeyEvent.VK_Z]){
            enterMenu = true;
        }

        if (enterMenu || enterShop  || enterBattle){
            ntime = System.currentTimeMillis();
            if (ntime - time > 200) {
                if (keys[KeyEvent.VK_UP]) mdir = 0;
                if (keys[KeyEvent.VK_RIGHT]) mdir = 1;
                if (keys[KeyEvent.VK_DOWN]) mdir = 2;
                if (keys[KeyEvent.VK_LEFT]) mdir = 3;
                menter = keys[KeyEvent.VK_SPACE];
                if (keys[KeyEvent.VK_X]) {
                    mback = true;
                }
                time = System.currentTimeMillis();
            }

        }else {
            up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
            down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
            left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
            right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];


            godMode = (keys[KeyEvent.VK_9]);
            if (keys[KeyEvent.VK_Q]){
                if (playerchange < 3){
                    playerchange++;
                }
                else playerchange = 0;
                keys[KeyEvent.VK_Q] = false;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }


}
