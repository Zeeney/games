package Zini.Project_P.entity.characters;

import Zini.Project_P.level.LevelReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by szini on 22/04/2018.
 */
public class Enemy extends Unit {

    public BufferedImage battleSprite;
    public int gold;

    public static Enemy monkeyKing = new Enemy("Monkey King", 800, 800, 300, 300, 60, 55, 40, 60, 10, 1000, 5000);
    public static Enemy monkey = new Enemy("Monkey", 150, 150, 150, 150, 15, 20, 16, 9, 4, 75, 100);
    public static Enemy wolf = new Enemy("Wolf", 200, 200, 100, 100, 20, 15, 15, 10, 4, 75, 120);
    public static Enemy snake = new Enemy("Snake", 180, 180, 200, 200, 10, 20, 10, 20, 4, 100, 80);
    public static Enemy pirate = new Enemy("Pirate", 200, 200, 200, 200, 20, 15, 10, 20, 4, 75, 200);

    public Enemy(String name, int maxHealth, int health, int maxStamina, int stamina, int attackPwr, int attackSpd, int speed, int defence, int level, int exp, int gold){
        super(name, maxHealth, health, maxStamina, stamina, attackPwr, attackSpd, speed, defence, level, exp);
        this.gold = gold;
    }

    public Enemy (Enemy enemy){
        super(enemy.name, enemy.maxHealth, enemy.health, enemy.maxStamina, enemy.stamina, enemy.attackPwr, enemy.attackSpd, enemy.speed, enemy.defence,enemy.level, enemy.exp);
        try {
            if (name == "Monkey King") battleSprite = ImageIO.read(LevelReader.class.getResource("/textures/Enemy_MonkeyKing.png"));
            if (name == "Monkey") battleSprite = ImageIO.read(LevelReader.class.getResource("/textures/Enemy_monkey.png"));
            if (name == "Wolf") battleSprite = ImageIO.read(LevelReader.class.getResource("/textures/Enemy_wolf.png"));
            if (name == "Snake") battleSprite = ImageIO.read(LevelReader.class.getResource("/textures/Enemy_Snake.png"));
            if (name == "Pirate") battleSprite = ImageIO.read(LevelReader.class.getResource("/textures/Enemy_pirate.png"));
        }catch (IOException e){
            System.out.println("Cannot load file");
        }
    }
}
