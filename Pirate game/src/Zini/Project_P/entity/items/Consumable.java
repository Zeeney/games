package Zini.Project_P.entity.items;

import Zini.Project_P.entity.characters.Unit;

public class Consumable extends Items{
    private boolean aoe;

    public static Consumable medKit = new Consumable("Med-kit", 50, 0,1,0,0,"heal", 200, false);

    public Consumable(String name, int health, int attackPwr, int attackSpd, int speed, int defence, String type ,int price, boolean aoe){
        super( name, health,  attackPwr,  attackSpd,  speed,  defence, type, price);
        this.aoe = aoe;
    }



    public int[] use(Unit receiver){
        int[] i = new int[2];
        if (type == "heal"){receiver.setHealth(receiver.getHealth() + this.health * attackSpd);}
        if (type == "offensive"){receiver.setHealth(receiver.getHealth() - attackPwr * attackSpd);}
        i[0] = attackSpd;
        if(type == "heal") i[1] = health;
        else i[1] = attackPwr;
        return i;
    }

    public boolean isAoe() {return aoe;}

}
