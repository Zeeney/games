package Zini.Project_P.entity.characters;

import Zini.Project_P.entity.items.Armour;
import Zini.Project_P.entity.items.Items;
import Zini.Project_P.entity.items.Weapon;
import Zini.Project_P.level.LevelReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class PartyMember extends Unit{


    private Armour chestArmour;
    private Armour legArmour;
    private Armour armArmour;
    private Armour helmet;
    private Weapon rightHand;
    private Weapon leftHand;

    private int expNeeded = 500;

    private BufferedImage image;

    public static PartyMember locke = new PartyMember("Locke", 175, 175, 300,300,25,20,20,15,1,0);
    public static PartyMember setzer = new PartyMember("Setzer", 200, 200, 200,200, 30,17,20,20,1,0);
    public static PartyMember leo = new PartyMember("Leo", 300, 300, 150,150, 30,10,15,30,1,0);
    public static PartyMember dancer = new PartyMember("Dancer", 150, 150, 250,250, 25,30,30,12,1,0);

    public PartyMember(String name, int maxHealth, int health, int maxStamina, int stamina, int attackPwr, int attackSpd, int speed, int defence, int level, int exp){
        super(name, maxHealth, health, maxStamina, stamina, attackPwr, attackSpd, speed, defence, level, exp);
        try {
            image = ImageIO.read(LevelReader.class.getResource("/textures/SpriteSheet_PlayableBattle.png"));
        }catch (IOException e){
            System.out.println("Cannot load file");
        }
        if (name == "Locke")battleSprite = image.getSubimage(2*32, 2*32, 32,32);
        if (name == "Setzer")battleSprite = image.getSubimage(2*32, 6*32, 32,32);
        if (name == "Leo")battleSprite = image.getSubimage(2*32, 4*32, 32,32);
        if (name == "Dancer")battleSprite = image.getSubimage(2*32, 0, 32,32);
    }

    public int totalD(){
        int d = 0;
        if (chestArmour != null) d += chestArmour.getDefence();
        if (helmet != null) d += helmet.getDefence();
        if (legArmour != null) d += legArmour.getDefence();
        if (armArmour != null) d += armArmour.getDefence();
        return d;
    }

    public int totalAS(){
        int d = 0;
        if (chestArmour != null) d += chestArmour.getAttackSpd();
        if (helmet != null) d += helmet.getAttackSpd();
        if (legArmour != null) d += legArmour.getAttackSpd();
        if (armArmour != null) d += armArmour.getAttackSpd();
        if (leftHand != null && rightHand != null){
            if (leftHand.getType() == "two hand") d += leftHand.getAttackSpd();
            else {
                d += leftHand.getAttackSpd();
                d += rightHand.getAttackSpd();
            }
        }else if (leftHand != null)d += leftHand.getAttackSpd();
        else if (rightHand!= null)d += rightHand.getAttackSpd();
        return d;
    }

    public int totalAP(){
        int d = 0;
        if (leftHand != null && rightHand != null){
            if (leftHand.getType() == "two hand") d += leftHand.getAttackPwr();
            else {
                d += leftHand.getAttackPwr();
                d += rightHand.getAttackPwr();
            }
        }else if (leftHand != null)d += leftHand.getAttackPwr();
        else if (rightHand!= null)d += rightHand.getAttackPwr();
        return d;
    }

    public void levelUp(){
        if (this == locke){
            maxHealth += 25; health += 25;
            maxStamina += 50; stamina += 50;
            attackPwr += 3; attackSpd += 2;
            speed += 2; defence +=2;

        }
        if (this == setzer){
            maxHealth += 30; health += 30;
            maxStamina += 30; stamina += 30;
            attackPwr += 4; attackSpd += 3;
            speed += 1; defence +=2;
        }
        if (this == leo){
            maxHealth += 40; health += 40;
            maxStamina += 40; stamina += 40;
            attackPwr += 5; attackSpd += 2;
            speed += 2; defence +=5;
        }
        if (this == dancer){
            maxHealth += 20; health += 20;
            maxStamina += 30; stamina += 30;
            attackPwr += 2; attackSpd += 7;
            speed += 5; defence +=2;
        }
        level++;
        expNeeded =(int) (500*level + (500*0.1* level));
    }

    public Armour getChestArmour() {
        return chestArmour;
    }

    public void setChestArmour(Armour chestArmour) {
        this.chestArmour = chestArmour;
    }

    public Armour getLegArmour() {
        return legArmour;
    }

    public void setLegArmour(Armour legArmour) {
        this.legArmour = legArmour;
    }

    public Armour getArmArmour() {
        return armArmour;
    }

    public void setArmArmour(Armour armArmour) {
        this.armArmour = armArmour;
    }

    public Armour getHelmet() {
        return helmet;
    }

    public void setHelmet(Armour helmet) {
        this.helmet = helmet;
    }

    public Weapon getRightHand() {
        return rightHand;
    }

    public void setRightHand(Weapon rightHand) {
        this.rightHand = rightHand;
    }

    public Weapon getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Weapon leftHand) {
        this.leftHand = leftHand;
    }

    public int getExpNeeded() {return expNeeded;}

    public void setExpNeeded(int expNeeded) {this.expNeeded = expNeeded;}

}
