package Zini.Project_P.entity.items;

/**
 * Created by szini on 21/04/2018.
 */
public class Items {



    protected String name;
    protected int health;
    protected int attackPwr;
    protected int attackSpd;
    protected int speed;
    protected int defence;
    protected String type;


    protected int price;


    public Items(String name, int health, int attackPwr, int attackSpd, int speed, int defence, String type, int price){
        this.name = name;
        this.health = health;
        this.attackPwr = attackPwr;
        this.attackSpd = attackSpd;
        this.speed = speed;
        this.defence = defence;
        this.type = type;
        this.price = price;
    }

    public String getName() {return name;}

    public int getHealth() {return health;}

    public int getAttackPwr() {return attackPwr;}

    public int getAttackSpd() {return attackSpd;}

    public int getSpeed() {return speed;}

    public int getDefence() {return defence;}

    public String getType() {return type;}

    public int getPrice() {return price;}

}
