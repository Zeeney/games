package Zini.Project_P.entity.items;

import Zini.Project_P.entity.characters.PartyMember;
import Zini.Project_P.entity.characters.Unit;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by szini on 21/04/2018.
 */
public class Skills {
    private double baseDMG;
    private double baseHITS;
    private boolean aoe;
    private String name;
    private int stamina;



    public static Skills wolfSlash = new Skills(1.5, 1.5, 20 ,false, "Wolf Slash");
    public static Skills monkeyScratch = new Skills(1.2, 1.8, 15,false, "Monkey Scratch");
    public static Skills snakeBite = new Skills(1.6, 1.3, 15,false ,"Snake Bite");
    public static Skills kingPunch = new Skills(2, 1, 50,true, "King Punch");

    public static Skills slash = new Skills(1.5, 1.5, 20,false, "Slash");
    public static Skills pierce = new Skills(2, 1, 15,false, "Pierce");
    public static Skills stab = new Skills(1, 2, 15,false, "Stab");
    public static Skills spray = new Skills(1.3, 1, 40,true, "Spray");

    public Skills(double baseDMG, double baseHITS, int stamina, boolean aoe, String name){
        this.baseDMG = baseDMG;
        this.baseHITS = baseHITS;
        this.stamina = stamina;
        this.aoe = aoe;
        this.name = name;
    }



    public int[] attack(Unit attacker, Unit attacked){
        int[] i = new int[2];
        int hits = 0;
        int damage = 0;
        if (attacker instanceof PartyMember) {
            hits = (int) (baseHITS * (attacker.getAttackSpd()+((PartyMember) attacker).totalAS()) / 6) ;
            damage = (int)(baseDMG *( (attacker.getAttackPwr() + ((Math.random()*3)-0.5) + ((PartyMember) attacker).totalAP() - (attacked.getDefence() / ((Math.random()*3)+1)))));
        }
        else if (attacked instanceof PartyMember) {
            hits = (int) (baseHITS * attacker.getAttackSpd() / 6);
            damage = (int) (baseDMG * ((attacker.getAttackPwr() + ((Math.random() * 3) - 0.5) - ((attacked.getDefence() / ((Math.random() * 3) + 1) )+ ((PartyMember) attacked).totalD()) )));
        }else {
            hits = (int) (baseHITS * attacker.getAttackSpd() / 6);
            damage = (int) (baseDMG * ((attacker.getAttackPwr() + ((Math.random() * 3) - 0.5) - (attacked.getDefence() / ((Math.random() * 3) + 1)))));
        }

        if (attacked.isDefensive()){
            damage = damage/2;
        }
        for (int j = 0; j < hits; j++){
            if (damage > 0)attacked.setHealth(attacked.getHealth()-damage);
        }
        i[0] = hits;
        i[1] = damage;
        return i;
    }

    public boolean isAoe() {return aoe;}
    public String getName() {return name;}
    public int getStamina() {return stamina;}
}
