package Zini.Project_P.entity.items;

public class Armour extends Items{

    public static Armour chainmail = new Armour("Chain Mail", 0,0,-2,0,10,"chest", 1000);
    public static Armour chainHelm = new Armour("Chain Helm", 0,0,-1,0,5,"head", 800);
    public static Armour chainGauntlets = new Armour("Chain Gauntlets", 0,0,-1,0,5,"arm", 700);
    public static Armour chainGreaves = new Armour("Chain Greaves ",0,0,-1,0,5,"leg", 700);


    public Armour(String name, int health, int attackPwr, int attackSpd, int speed, int defence, String type, int price){
        super( name, health,  attackPwr,  attackSpd,  speed,  defence, type, price);
    }
}
