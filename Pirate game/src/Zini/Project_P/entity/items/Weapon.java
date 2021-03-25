package Zini.Project_P.entity.items;

public class Weapon extends Items{

    public static Weapon scimitar  = new Weapon("Scimitar", 0,10,2,0,0,"one hand", 1000);
    public static Weapon knife  = new Weapon("Knife", 0,5,5,0,0,"one hand", 800);
    public static Weapon broadSword  = new Weapon("Broadsword", 0,20,-5,0,0,"two hand", 900);
    public static Weapon musket = new Weapon("Musket", 0,13,0,0,0,"two hand", 1200);

    public Weapon(String name, int health, int attackPwr, int attackSpd, int speed, int defence, String type, int price){
        super( name, health,  attackPwr,  attackSpd,  speed,  defence, type, price);
    }

}
