package Zini.Project_P.graphics.ui;

import Zini.Project_P.Game;
import Zini.Project_P.entity.characters.Enemy;
import Zini.Project_P.entity.characters.PartyMember;
import Zini.Project_P.entity.characters.Player;
import Zini.Project_P.entity.characters.Unit;
import Zini.Project_P.entity.items.Consumable;
import Zini.Project_P.entity.items.Quests;
import Zini.Project_P.entity.items.Skills;
import Zini.Project_P.input.Keyboard;
import Zini.Project_P.level.LevelReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GraphicAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by szini on 21/04/2018.
 */
public class Battle extends UIStuff {

    private boolean nextTurn;

    private ArrayList<Unit> turnList = new ArrayList<Unit>();
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private ArrayList<PartyMember> partyList = new ArrayList<PartyMember>();

    private Unit prevAttacked ;
    private Unit prevAttacker;
    private Skills prevSkill;
    private Consumable prevItem;
    private String prevType;
    private int[] prevDamageInfo = {0,0};

    private Game game;

    private int exp;
    private int gold;
    private Skills base = new Skills(1,1,0, false, "base");

    private String fieldArea = "/textures/BattleGround_Field.png";
    private String boatArea = "/textures/BattleGround_Boat.png";
    private String caveArea = "/textures/BattleGround_Cave.png";
    private String beachArea = "/textures/BattleGround_Beach.png";
    private String forestArea = "/textures/BattleGround_Forest.png";

    private BufferedImage image;

    private int waitTime;

    public Battle(Keyboard key, Player player, Game game) {
        super(key, player);
        xpos = 30; ypos = 325;
        pageItems = 4;
        populate();
        try{
            if (player.location == "field") image = ImageIO.read(LevelReader.class.getResource(fieldArea));
            if (player.location == "boat") image = ImageIO.read(LevelReader.class.getResource(boatArea));
            if (player.location == "cave") image = ImageIO.read(LevelReader.class.getResource(caveArea));
            if (player.location == "beach") image = ImageIO.read(LevelReader.class.getResource(beachArea));
            if (player.location == "forest") image = ImageIO.read(LevelReader.class.getResource(forestArea));

        }catch (IOException e){
            System.out.println("Cannot load file");
        }

        this.game = game;

        curMenu = "main";
        prevAttacked = player.party.get(0);
    }

    public void populate(){
        if (!player.boss) {
            int enemyType = (int) (Math.random() * 4 + 1);
            int enemyNo = (int) (Math.random() * 4 + 1);
            createEnemy(enemyType, enemyNo);
            createTurnList();
        }else {
            enemyList.add(new Enemy(Enemy.monkeyKing));
            enemyList.get(0).setX(100); enemyList.get(0).setY(150);
            createTurnList();

        }
    }

    public void createEnemy(int i, int j){
        if (player.location == "boat") i = 4;
        if (i == 1){
            for (int o = 0; o < j; o++){
                enemyList.add(new Enemy(Enemy.monkey));
                enemyList.get(o).setX(100); enemyList.get(o).setY(20+o*75);
                enemyList.get(o).setName("Monkey " + (o+1));
                exp += enemyList.get(o).getExp();
                gold += enemyList.get(o).gold;
            }
        }
        if (i == 2){
            for (int o = 0; o < j; o++){
                enemyList.add(new Enemy(Enemy.snake));
                enemyList.get(o).setX(100); enemyList.get(o).setY(20+o*75);
                enemyList.get(o).setName("Snake " + (o+1));
                gold += enemyList.get(o).gold;
            }
        }
        if (i == 3){
            for (int o = 0; o < j; o++){
                enemyList.add(new Enemy(Enemy.wolf));
                enemyList.get(o).setX(100); enemyList.get(o).setY(20+o*75);
                enemyList.get(o).setName("Wolf " + (o+1));
                gold += enemyList.get(o).gold;
            }
        }
        if (i == 4){
            for (int o = 0; o < j; o++){
                enemyList.add(new Enemy(Enemy.pirate));
                enemyList.get(o).setX(100); enemyList.get(o).setY(12+o*75);
                enemyList.get(o).setName("Pirate " + (o+1));
                gold += enemyList.get(o).gold;
            }
        }
    }

    public void createTurnList(){
        for (int i = 0; i < enemyList.size(); i++){turnList.add(enemyList.get(i));}
        for (int i = 0; i < player.party.size(); i++){
            partyList.add(player.party.get(i));
            partyList.get(i).setX(800);  partyList.get(i).setY(20 + i*75);
        }
        for (int i = 0; i < partyList.size(); i++){turnList.add(partyList.get(i));}
        Collections.sort(turnList, Unit.speedComparator);
    }


    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(ff6Font);
        g.drawImage(image, 0,0, 900, 900/ 16*9, null, null);
        g.drawImage(blueMenu, 0, 320, 900, blueMenu.getHeight(null), null, null);

        for (int i = 0; i < enemyList.size();i++){
            if (enemyList.get(0).getName().contains("Pirate"))g.drawImage(enemyList.get(i).battleSprite, 100, enemyList.get(i).getY(),enemyList.get(i).battleSprite.getWidth()*3,enemyList.get(i).battleSprite.getHeight()*3,null, null);
            else g.drawImage(enemyList.get(i).battleSprite, 100, enemyList.get(i).getY(),enemyList.get(i).battleSprite.getWidth()*3/2,enemyList.get(i).battleSprite.getHeight()*3/2,null, null);
        }
        for (int i = 0; i < partyList.size();i++){
            g.drawImage(partyList.get(i).battleSprite, 800, partyList.get(i).getY(), partyList.get(i).battleSprite.getWidth()*3 ,partyList.get(i).battleSprite .getHeight()*3,null,null);

        }

        if (curMenu == "main")drawMain(g);
        if (curMenu == "attack list")drawEnemies(g);
        if (curMenu == "skill select")drawSkills(g);
        if (curMenu == "item select")drawItems(g);
        if (curMenu == "heal list")drawParty(g);
        if (curMenu == "wait")drawWait(g);
        if (curMenu == "error")drawError(g);

        drawDamage(g);
    }

    public void update(){
        if(player.party.contains(turnList.get(0))){
            if (curMenu == "main"){
                if (key.menter) {
                    if (cursor == 1) {
                        prevSkill = base;
                        prevType = "attack";
                        newMenu("attack list", enemyList.size());
                    }
                    if (cursor == 2) {
                        newMenu("skill select", turnList.get(0).skills.size());
                    }
                    if (cursor == 3) {
                        turnList.get(0).setDefensive(true);
                        prevType = "defend";
                        newMenu("wait",0);
                    }
                    if (cursor == 4) {
                        newMenu("item select", turnList.get(0).items.size());
                    }
                }
            }

            if(curMenu == "attack list"){
                if (key.menter) {
                    prevAttacker = turnList.get(0);
                    prevAttacked = enemyList.get(cursor - 1);
                    prevDamageInfo = prevSkill.attack(prevAttacker, prevAttacked);
                    newMenu("wait", 0);
                }
                if (key.mback){
                    newMenu("main", 4);
                }
            }


            if (curMenu == "skill select"){
                if (key.menter) {
                    prevSkill = turnList.get(0).skills.get(cursor-1);
                    if (turnList.get(0).getStamina() - prevSkill.getStamina() >= 0) {
                        if (prevSkill.isAoe()) {
                            for (int i = 0; i < enemyList.size(); i++) {
                                prevSkill.attack(turnList.get(0), enemyList.get(i));
                            }
                            newMenu("wait", 0);
                            prevType = "aoe";
                        } else {
                            newMenu("attack list", enemyList.size());
                            prevType = "skill";
                        }
                    }else{
                        newMenu("error", 0);
                    }
                }
                if (key.mback){
                    newMenu("main", 4);
                }
            }

            if (curMenu == "item select"){
                if (key.menter) {
                    prevItem = turnList.get(0).items.get(cursor-1);
                    if (prevItem.isAoe()  ){
                        if (prevItem.getType() == "offensive") {
                            for (int i = 0; i < enemyList.size(); i++) {
                                prevItem.use(enemyList.get(i));
                            }

                            newMenu("wait", 0);
                            prevType = "aoe item";
                        }
                        if (prevItem.getType() == "heal"){
                            for (int i = 0; i < player.party.size(); i++) {
                                prevItem.use(player.party.get(i));
                            }
                            newMenu("wait", 0);
                            prevType = "aoe heal";
                        }
                    } else {
                        if (prevItem.getType() == "offensive") {
                            newMenu("item attack", enemyList.size());
                        }
                        if (prevItem.getType() == "heal"){
                            newMenu("heal list", player.party.size());
                        }
                    }
                    turnList.get(0).items.remove(prevItem);
                }
                if (key.mback){
                    newMenu("main", 4);
                }
            }

            if (curMenu == "item attack"){
                if (key.menter) {
                    prevAttacker = turnList.get(0);
                    prevAttacked = enemyList.get(cursor - 1);
                    prevDamageInfo = prevItem.use(prevAttacked);
                    prevType = "item";
                    newMenu("wait", 0);
                }
                if (key.mback){
                    newMenu("main", 4);
                }
            }

            if (curMenu == "heal list"){
                if (key.menter) {
                    prevAttacker = turnList.get(0);
                    prevAttacked = enemyList.get(cursor - 1);
                    prevDamageInfo = prevItem.use(prevAttacked);
                    newMenu("wait", 0);
                    prevType = "heal";
                }
                if (key.mback){
                    newMenu("main", 4);
                }
            }

        }
        else if(enemyList.contains(turnList.get(0)) ){
            if (curMenu != "wait")performAttack();
        }


        if (curMenu == "wait"){
            waitTime++;
            if (waitTime >= 100){
                waitTime = 0;
                curMenu = "main";
                nextTurn = true;
            }
        }

        if (curMenu == "error"){
            waitTime++;
            if (waitTime >= 100){
                waitTime = 0;
                curMenu = "main";
            }
        }



        if (nextTurn){
            for (int i = 0; i < turnList.size(); i ++){
                if (turnList.get(i).getHealth() <= 0){
                    turnList.remove(i);
                }
            }
            for (int i = 0; i < enemyList.size(); i ++){
                if (enemyList.get(i).getHealth() <= 0){
                    enemyList.remove(i);
                }
            }
            for (int i = 0; i < partyList.size(); i ++){
                if (partyList.get(i).getHealth() <= 0){
                    partyList.remove(i);
                }
            }
            turnList.get(0).setDefensive(false);
            turnList.add(turnList.get(0));
            turnList.remove(0);
            nextTurn = false;
        }
        pointer();

        if (enemyList.isEmpty() || partyList.isEmpty()){
            game.inBattle = false;
            game.inExplore = true;
            key.enterBattle = false;

            for (int i = 0; i < player.party.size(); i++){
                player.party.get(i).setExp(player.party.get(i).getExp() + exp);
                if (player.party.get(i).getExp() > player.party.get(i).getExpNeeded()){
                    player.party.get(i).levelUp();
                }
                player.gold += gold;
            }

            if (player.boss) {
                player.boss = false;
                Quests.blackbeardsTreasure.hasTreasure = true;
            }
        }

    }
    public void newMenu(String n, int i){
        cursor = 1;
        cursorMax = i;
        curMenu = n;
        ypos = 325;
        page = 0;
        key.menter =false;
        key.mback = false;
    }
    public void pointer(){
        if (key.mdir == 0){
            if (cursor == 1) {
                ypos = 445;
                cursor = cursorMax;
                key.mdir = -1;
                page = pages;
            }
            else {
                ypos -= 40;
                cursor--;
                key.mdir = -1;
            }
        } else if(key.mdir == 2){
            if (cursor == cursorMax) {
                ypos = 325;
                cursor = 1;
                key.mdir = -1;
                page = 0;
            } else {
                ypos += 40;
                cursor++;
                key.mdir = -1;
            }
        }
    }




    public void drawMain(Graphics g){
        g.drawString("Attack", 70, 350);
        g.drawString("Skills", 70, 390);
        g.drawString("Defend", 70, 430);
        g.drawString("Items", 70, 470);

        g.drawString(turnList.get(0).getName(), 400, 350);
        g.drawString("Health: " + turnList.get(0).getHealth() + "/" + turnList.get(0).getMaxHealth(), 400, 390);
        g.drawString("Stamina: " + turnList.get(0).getStamina() + "/" + turnList.get(0).getMaxStamina(), 400, 430);

        drawPointer(g);
    }


    public void drawEnemies(Graphics g){
        for (int i = 0; i < enemyList.size(); i++){
            g.drawString(enemyList.get(i).getName(), 70, 350 + i*40);
        }

        drawPointer(g);
    }

    public void drawSkills(Graphics g){
        for (int i = 0; i < turnList.get(0).skills.size(); i++){
            g.drawString(turnList.get(0).skills.get(i).getName(), 70, 350 + i*40);
            g.drawString("" + turnList.get(0).skills.get(i).getStamina(), 400, 350 + i*40);
        }

        drawPointer(g);
    }

    public void drawItems(Graphics g){
        for (int i = 0; i < turnList.get(0).items.size(); i++){
            g.drawString(turnList.get(0).items.get(i).getName(), 70, 350 + i*40);
        }

        drawPointer(g);
    }

    public void drawParty(Graphics g){
        for (int i = 0; i < player.party.size(); i++){
            g.drawString(player.party.get(i).getName(), 70, 350 + i*40);
        }

        drawPointer(g);
    }

    public void drawWait(Graphics g){
        if (prevType == "attack") g.drawString(prevAttacker.getName() + " attacked " + prevAttacked.getName(), 70, 350);
        if (prevType == "skill") g.drawString(prevAttacker.getName() + " attacked " + prevAttacked.getName()  + " with " + prevSkill.getName(), 70, 350);
        if (prevType == "item") g.drawString(prevAttacker.getName() + " attacked " + prevAttacked.getName() + " with a " + prevItem.getName(), 70, 350);
        if (prevType == "aoe") g.drawString(prevAttacker.getName() + " attacked everyone with" + prevSkill.getName(), 70, 350);
        if (prevType == "aoe item") g.drawString(prevAttacker.getName() + " attacked everyone with " + prevItem.getName(), 70, 350);
        if (prevType == "heal") g.drawString(prevAttacker.getName() + " healed " + prevAttacked.getName() + " with " + prevItem.getName(), 70, 350);
        if (prevType == "aoe heal") g.drawString(prevAttacker.getName() + " healed everyone with " + prevItem.getName(), 70, 350);
        if (prevType == "defend") g.drawString(turnList.get(0).getName() + " took a defensive stance.", 70, 350);
    }

    public void drawError(Graphics g){
        g.drawString("Not enough stamina for this attack.", 70, 350);
    }

    public void performAttack(){
        int rand = (int)(Math.random()* 4 )+ 1;
        ArrayList<PartyMember> party = createParty();
        if (enemyList.get(0).getName().contains("Monkey King")){
            if (turnList.get(0).getStamina() - Skills.kingPunch.getStamina() >= 0 && ((int)Math.random()*3 + 1) == 1){
                for (int i = 0; i < party.size(); i++){
                    prevSkill = Skills.kingPunch;
                    enemyAttack(party.get(i));
                }
            }else {
                Collections.sort(party, Unit.healthComparator2);
                PartyMember it = party.get(0);
                for (int i = 0; i < party.size(); i++){
                    if (party.get(i).getHealth() < 70){
                        it = party.get(i);
                        break;
                    }
                }
                if (turnList.get(0).getStamina() - Skills.monkeyScratch.getStamina() <= 0)prevSkill = base;
                else {
                    prevSkill = Skills.monkeyScratch;
                    turnList.get(0).setStamina(turnList.get(0).getStamina() - Skills.monkeyScratch.getStamina());
                }
                enemyAttack(it);
            }

        }
        if (enemyList.get(0).getName().contains("Wolf")){
            if (rand == 1){
                Collections.sort(party, Unit.healthComparator);
                if (turnList.get(0).getStamina() - Skills.wolfSlash.getStamina() < 0)prevSkill = base;
                else {
                    prevSkill = Skills.wolfSlash;
                    turnList.get(0).setStamina(turnList.get(0).getStamina() - Skills.wolfSlash.getStamina());
                }
                enemyAttack(party.get(0));
            }else {
                prevSkill = base;
                randomAttack(party);
            }
        }
        if (enemyList.get(0).getName().contains("Monkey")){
            if (rand == 1){
                Collections.sort(party, Unit.attackSpdComparator);
                if (turnList.get(0).getStamina() - Skills.monkeyScratch.getStamina() < 0)prevSkill = base;
                else {
                    prevSkill = Skills.monkeyScratch;
                    turnList.get(0).setStamina(turnList.get(0).getStamina() - Skills.monkeyScratch.getStamina());
                }
                enemyAttack(party.get(0));
            }else {
                prevSkill = base;
                randomAttack(party);
            }
        }
        if (enemyList.get(0).getName().contains("Snake")){
            if (rand == 1){
                Collections.sort(party, Unit.attackComparator);
                if (turnList.get(0).getStamina() - Skills.snakeBite.getStamina() < 0)prevSkill = base;
                else {
                    prevSkill = Skills.snakeBite;
                    turnList.get(0).setStamina(turnList.get(0).getStamina() - Skills.snakeBite.getStamina());
                }
                enemyAttack(party.get(0));
            }else {
                prevSkill = base;
                randomAttack(party);
            }
        }
        if (enemyList.get(0).getName().contains("Pirate")){
            if (turnList.get(0).getStamina() - Skills.spray.getStamina() >= 0 && ((int)(Math.random()*3) + 1) == 1){
                prevSkill = Skills.spray;
                enemyAoe(party);
            }else {
                Collections.sort(party, Unit.healthComparator);
                PartyMember it = party.get(0);
                if ((int)Math.random()*2 + 1 == 1) {
                    if (turnList.get(0).getStamina() - Skills.stab.getStamina() < 0) prevSkill = base;
                    else {
                        prevSkill = Skills.stab;
                        turnList.get(0).setStamina(turnList.get(0).getStamina() - Skills.stab.getStamina());
                    }
                    enemyAttack(it);
                }
                else randomAttack(party);
            }
        }
    }

    public ArrayList<PartyMember> createParty(){
        ArrayList<PartyMember> party = new ArrayList<PartyMember>();
        for (int i = 0; i < partyList.size(); i++) {
            if (partyList.get(i).getHealth() > 0){
                party.add(partyList.get(i));
            }
        }
        return party;
    }

    public void enemyAoe(ArrayList<PartyMember> party){
        for (int i = 0; i < party.size(); i++) {
            prevAttacker = turnList.get(0);
            prevAttacked = party.get(i);
            System.out.println(party.get(i).getName());
            prevDamageInfo = prevSkill.attack(prevAttacker, prevAttacked);
            prevType = "aoe";
        }
        newMenu("wait", 0);
    }

    public void enemyAttack(Unit attacked){
        prevAttacker = turnList.get(0);
        prevAttacked = attacked;
        prevDamageInfo = prevSkill.attack(prevAttacker, prevAttacked);
        if (prevSkill.getName() == "base")prevType = "attack";
        else prevType = "skill";
        newMenu("wait", 0);
    }

    public void randomAttack(ArrayList<PartyMember> party){
        int rand =(int) (Math.random()*party.size());
        prevAttacker = turnList.get(0);
        prevAttacked = party.get(rand);
        prevDamageInfo = prevSkill.attack(prevAttacker, prevAttacked);
        prevType = "attack";
        newMenu("wait", 0);
    }

    public void drawDamage(Graphics g){
        g.drawString(prevDamageInfo[1] + " X " + prevDamageInfo[0], prevAttacked.getX(), prevAttacked.getY()+9);
    }
}
