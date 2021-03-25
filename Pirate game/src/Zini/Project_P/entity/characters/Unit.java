package Zini.Project_P.entity.characters;

import Zini.Project_P.entity.items.Consumable;
import Zini.Project_P.entity.items.Items;
import Zini.Project_P.entity.items.Skills;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by szini on 21/04/2018.
 */
public class Unit{




    protected String name;
    protected int maxHealth;
    protected int health;
    protected int maxStamina;
    protected int stamina;
    protected int attackPwr;
    protected int attackSpd;
    protected int speed;



    protected int defence;
    protected int level;
    protected int exp;

    protected int x;
    protected int y;

    public BufferedImage battleSprite;
    protected boolean defensive;
    public ArrayList<Consumable> items = new ArrayList<Consumable>();

    public ArrayList<Skills> skills = new ArrayList<Skills>();

    public Unit(String name, int maxHealth, int health, int maxStamina, int stamina, int attackPwr, int attackSpd, int speed, int defence, int level, int exp){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = health;
        this.maxStamina = maxStamina;
        this.stamina = stamina;
        this.attackPwr = attackPwr;
        this.attackSpd = attackSpd;
        this.speed = speed;
        this.defence = defence;
        this.level = level;
        this.exp = exp;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getMaxStamina() {return maxStamina;}

    public void setMaxStamina(int maxStamina) {this.maxStamina = maxStamina;}

    public int getStamina() {return stamina;}

    public void setStamina(int stamina) {this.stamina = stamina;}

    public int getAttackPwr() {
        return attackPwr;
    }

    public void setAttackPwr(int attackPwr) {
        this.attackPwr = attackPwr;
    }

    public int getAttackSpd() {
        return attackSpd;
    }

    public void setAttackSpd(int attackSpd) {
        this.attackSpd = attackSpd;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public int getExp() {return exp;}

    public void setExp(int exp) {this.exp = exp;}

    public int getX() {return x;}

    public void setX(int x) {this.x = x;}

    public int getY() {return y;}

    public void setY(int y) {this.y = y;}

    public boolean isDefensive() {return defensive;}

    public void setDefensive(boolean defensive) {this.defensive = defensive;}

    public static Comparator<Unit> speedComparator = new Comparator<Unit>() {

        public int compare(Unit u1, Unit u2) {
            int speed1 = u1.getSpeed();
            int speed2 = u2.getSpeed();

            return speed2-speed1;
        }
    };


    public static Comparator<Unit> healthComparator = new Comparator<Unit>() {

        public int compare(Unit u1, Unit u2) {
            int health1 = u1.getHealth();
            int health2 = u2.getHealth();

            return health2-health1;
        }
    };

    public static Comparator<Unit> healthComparator2 = new Comparator<Unit>() {

        public int compare(Unit u1, Unit u2) {
            int health1 = u1.getHealth();
            int health2 = u2.getHealth();

            return health1-health2;
        }
    };

    public static Comparator<Unit> attackSpdComparator = new Comparator<Unit>() {

        public int compare(Unit u1, Unit u2) {
            int attackSpd1 = u1.getAttackSpd();
            int attackSpd2 = u2.getAttackSpd();

            return attackSpd2-attackSpd1;
        }
    };

    public static Comparator<Unit> attackComparator = new Comparator<Unit>() {

        public int compare(Unit u1, Unit u2) {
            int attack1 = u1.getAttackPwr();
            int attack2 = u2.getAttackPwr();

            return attack2-attack1;
        }
    };


}
