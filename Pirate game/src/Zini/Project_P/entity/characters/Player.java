package Zini.Project_P.entity.characters;

import Zini.Project_P.entity.items.Armour;
import Zini.Project_P.entity.items.Consumable;
import Zini.Project_P.entity.items.Quests;
import Zini.Project_P.entity.items.Weapon;
import Zini.Project_P.graphics.Sprite;
import Zini.Project_P.graphics.Screen;
import Zini.Project_P.input.Keyboard;
import Zini.Project_P.level.Level;

import java.util.ArrayList;


public class Player extends Character
{

    public int character = 0;
    public int gold = 10000;


    public ArrayList<PartyMember> party = new ArrayList<PartyMember>();
    public ArrayList<Consumable> items = new ArrayList<Consumable>();
    public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    public ArrayList<Armour> armours = new ArrayList<Armour>();
    public ArrayList<Quests> quests = new ArrayList<Quests>();

    private int aCount=0;


    public Player(int x, int y, Keyboard input)
    {

        sprite = Sprite.playerDancerD;
        this.x = x;
        this.y = y;
        this.input = input;
        party.add(PartyMember.dancer);
        party.add(PartyMember.leo);
        party.add(PartyMember.locke);
        party.add(PartyMember.setzer);
    }

    public boolean alive(){
        int x = 0;
        for (int i = 0; i < party.size(); i++){
            if (party.get(i).getHealth() <= 0)x++;
        }
        if (x == 4)return false;
        else return true;
    }

    public void update()
    {
        int xa = 0, ya = 0;

        if (aCount<10000) aCount++;
        else aCount=0;

        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;
        character = input.playerchange;

        if (xa != 0 || ya !=  0) {
            move(xa, ya);
            moving = true;
        }else{
            moving = false;
        }

        if (input.godMode){
            for (int i = 0; i < party.size(); i++){
                party.get(i).setMaxHealth(20000);party.get(i).setHealth(20000);
                party.get(i).setMaxStamina(20000);party.get(i).setStamina(20000);
                party.get(i).setAttackPwr(20000);party.get(i).setAttackSpd(20000);
                party.get(i).setSpeed(20000);party.get(i).setDefence(20000);
            }
        }

    }

    public void init(Level level){
        this.level = level;
    }

    public void render(Screen s)
    {
        int flip = 0;
        if (dir == 0) {
            if (character == 0) sprite = Sprite.playerDancerU;
            if (character == 1) sprite = Sprite.playerLockeU;
            if (character == 2) sprite = Sprite.playerLeoU;
            if (character == 3) sprite = Sprite.playerSetzerU;
            if (moving) {
                if (aCount % 40 > 30) {
                    if (character == 0) sprite = Sprite.playerDancerU1;
                    if (character == 1) sprite = Sprite.playerLockeU1;
                    if (character == 2) sprite = Sprite.playerLeoU1;
                    if (character == 3) sprite = Sprite.playerSetzerU1;
                } else if(aCount % 40 > 20){
                    if (character == 0) sprite = Sprite.playerDancerU;
                    if (character == 1) sprite = Sprite.playerLockeU;
                    if (character == 2) sprite = Sprite.playerLeoU;
                    if (character == 3) sprite = Sprite.playerSetzerU;
                }else if(aCount % 40 > 10){
                    if (character == 0) sprite = Sprite.playerDancerU1;
                    if (character == 1) sprite = Sprite.playerLockeU1;
                    if (character == 2) sprite = Sprite.playerLeoU1;
                    if (character == 3) sprite = Sprite.playerSetzerU1;
                    flip = 1;
                }else{
                    if (character == 0) sprite = Sprite.playerDancerU;
                    if (character == 1) sprite = Sprite.playerLockeU;
                    if (character == 2) sprite = Sprite.playerLeoU;
                    if (character == 3) sprite = Sprite.playerSetzerU;
                }
            }
        }
        if (dir == 1){
            if (character == 0) sprite = Sprite.playerDancerL;
            if (character == 1) sprite = Sprite.playerLockeL;
            if (character == 2) sprite = Sprite.playerLeoL;
            if (character == 3) sprite = Sprite.playerSetzerL;
            if (moving) {
                if (aCount % 40 > 30) {
                    if (character == 0) sprite = Sprite.playerDancerL1;
                    if (character == 1) sprite = Sprite.playerLockeL1;
                    if (character == 2) sprite = Sprite.playerLeoL1;
                    if (character == 3) sprite = Sprite.playerSetzerL1;
                } else if(aCount % 40 > 20){
                    if (character == 0) sprite = Sprite.playerDancerL;
                    if (character == 1) sprite = Sprite.playerLockeL;
                    if (character == 2) sprite = Sprite.playerLeoL;
                    if (character == 3) sprite = Sprite.playerSetzerL;
                }else if(aCount % 40 > 10){
                    if (character == 0) sprite = Sprite.playerDancerL2;
                    if (character == 1) sprite = Sprite.playerLockeL2;
                    if (character == 2) sprite = Sprite.playerLeoL2;
                    if (character == 3) sprite = Sprite.playerSetzerL2;
                }else{
                    if (character == 0) sprite = Sprite.playerDancerL;
                    if (character == 1) sprite = Sprite.playerLockeL;
                    if (character == 2) sprite = Sprite.playerLeoL;
                    if (character == 3) sprite = Sprite.playerSetzerL;
                }
            }
            flip = 1;
        }
        if (dir == 2){
            if (character == 0) sprite = Sprite.playerDancerD;
            if (character == 1) sprite = Sprite.playerLockeD;
            if (character == 2) sprite = Sprite.playerLeoD;
            if (character == 3) sprite = Sprite.playerSetzerD;
            if (moving) {
                if (aCount % 40 > 30) {
                    if (character == 0) sprite = Sprite.playerDancerD1;
                    if (character == 1) sprite = Sprite.playerLockeD1;
                    if (character == 2) sprite = Sprite.playerLeoD1;
                    if (character == 3) sprite = Sprite.playerSetzerD1;
                } else if(aCount % 40 > 20){
                    if (character == 0) sprite = Sprite.playerDancerD;
                    if (character == 1) sprite = Sprite.playerLockeD;
                    if (character == 2) sprite = Sprite.playerLeoD;
                    if (character == 3) sprite = Sprite.playerSetzerD;
                }else if(aCount % 40 > 10) {
                    if (character == 1) sprite = Sprite.playerLockeD1;
                    if (character == 2) sprite = Sprite.playerLeoD1;
                    if (character == 3) sprite = Sprite.playerSetzerD1;
                    flip = 1;
                    if (character == 0) {
                        sprite = Sprite.playerDancerD2;
                        flip = 0;
                    }
                }else{
                    if (character == 0) sprite = Sprite.playerDancerD;
                    if (character == 1) sprite = Sprite.playerLockeD;
                    if (character == 2) sprite = Sprite.playerLeoD;
                    if (character == 3) sprite = Sprite.playerSetzerD;
                }
            }
        }
        if (dir == 3){
            if (character == 0) sprite = Sprite.playerDancerL;
            if (character == 1) sprite = Sprite.playerLockeL;
            if (character == 2) sprite = Sprite.playerLeoL;
            if (character == 3) sprite = Sprite.playerSetzerL;
            if (moving) {
                if (aCount % 40 > 30) {
                    if (character == 0) sprite = Sprite.playerDancerL1;
                    if (character == 1) sprite = Sprite.playerLockeL1;
                    if (character == 2) sprite = Sprite.playerLeoL1;
                    if (character == 3) sprite = Sprite.playerSetzerL1;
                } else if(aCount % 40 > 20){
                    if (character == 0) sprite = Sprite.playerDancerL;
                    if (character == 1) sprite = Sprite.playerLockeL;
                    if (character == 2) sprite = Sprite.playerLeoL;
                    if (character == 3) sprite = Sprite.playerSetzerL;
                }else if(aCount % 40 > 10){
                    if (character == 0) sprite = Sprite.playerDancerL2;
                    if (character == 1) sprite = Sprite.playerLockeL2;
                    if (character == 2) sprite = Sprite.playerLeoL2;
                    if (character == 3) sprite = Sprite.playerSetzerL2;
                }else{
                    if (character == 0) sprite = Sprite.playerDancerL;
                    if (character == 1) sprite = Sprite.playerLockeL;
                    if (character == 2) sprite = Sprite.playerLeoL;
                    if (character == 3) sprite = Sprite.playerSetzerL;
                }
            }
        }

        s.renderPlayer(x, y, sprite, flip);
    }
}
