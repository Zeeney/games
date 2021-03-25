package Zini.Project_P.graphics.ui;



import Zini.Project_P.entity.characters.PartyMember;
import Zini.Project_P.entity.characters.Player;
import Zini.Project_P.entity.items.*;
import Zini.Project_P.input.Keyboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class UIStuff extends JComponent{


    protected int pageItems = 8;

    protected int cursorMax = 4;
    protected int cursor = 1;

    protected int pages;
    protected int page = 0;

    protected PartyMember curMember;
    protected Armour curArmour;
    protected Weapon curWeapon;
    protected Consumable curItem;
    protected Quests curQuest;
    protected Skills curSkill;

    protected String curMenu;

    protected static String pathMenu = "/textures/UI_BlueMenu.png";
    protected static String pathPointer = "/textures/UI_Pointer.png";
    protected Font ff6Font;

    protected int xpos = 250; protected int ypos = 40, wscale, hscale;

    protected Image blueMenu;
    protected Image pointer;


    protected Player player;
    protected Keyboard key;

    protected ArrayList<Skills> skills = new ArrayList<Skills>();

    public UIStuff(Keyboard key, Player player) {
        this.key = key;
        this.player = player;
        skillList();
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("text/FF6_Font.ttf");
            ff6Font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
            blueMenu = ImageIO.read(UIStuff.class.getResource(pathMenu));
            pointer = ImageIO.read(UIStuff.class.getResource(pathPointer));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        curMenu = "main";
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(ff6Font);

        g.drawImage(blueMenu, 90, 20, blueMenu.getWidth(null)*3, blueMenu.getHeight(null)*5/2, null, null);
        if (curMenu == "main")drawMain(g);
        if (curMenu == "player select")drawPlayerS(g);
        if (curMenu == "player allocation")drawPlayerA(g);
        if (curMenu == "player stats")drawPlayerSt(g);
        if (curMenu == "item select")drawItems(g);
        if (curMenu == "item allocation")drawItemsA(g);
        if (curMenu == "equip characters")drawPlayerS(g);
        if (curMenu == "equip type")drawType(g);
        if (curMenu == "equip weapons")drawWeapons(g);
        if (curMenu == "equip weapon allocation")drawWeaponsA(g);
        if (curMenu == "choose hand")drawHand(g);
        if (curMenu == "equip armour")drawArmour(g);
        if (curMenu == "equip armour allocation")drawArmourA(g);
        if (curMenu == "equip skills")drawSkill(g);
        if (curMenu == "change skills")drawSkillC(g);
        if (curMenu == "quest select")drawQuest(g);
        if (curMenu == "quest info")drawQuestInfo(g);
    }

    public void update(){
        if (curMenu == "main"){
            if (key.menter){
                if (cursor == 1) {
                    newMenu("player select", player.party.size());
                }
                if (cursor == 2) {
                    newMenu("item select", player.items.size());
                }
                if (cursor == 3) {
                    newMenu("equip characters", player.party.size());
                }
                if (cursor == 4) {
                    newMenu("quest select", player.quests.size());
                }

                key.menter = false;
            }
            if (key.mback){
                key.enterMenu = false;
                key.mback = false;
            }
        }
        if (curMenu == "player select"){
            if (key.menter){
                curMember = player.party.get(cursor-1);
                newMenu("player allocation", 2);
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }
        if (curMenu == "player allocation"){
            if (key.menter){
                if (cursor == 1){
                    player.character = cursor;
                    newMenu("main", 4);
                }
                if (cursor == 2){
                    newMenu("player stats", 0);
                }
            }
            if (key.mback){
                newMenu("player select", player.party.size());
            }
        }
        if (curMenu == "player stats"){
            if (key.mback){
                newMenu("player allocation", 2);
            }
        }

        if (curMenu == "item select") {
            if (key.menter && !player.items.isEmpty()){
                curItem = player.items.get(cursor-1);
                newMenu("item allocation", 2);
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }

        if (curMenu == "item allocation"){
            if (key.menter){
                if (cursor == 1){
                    newMenu("item use",player.items.size());
                }
                if (cursor == 2){
                    player.items.remove(curItem);
                    newMenu("item select", player.items.size());
                }
            }
            if (key.mback){
                newMenu("item select", player.items.size());
            }
        }

        if (curMenu == "item use"){
            if (key.menter){
                curItem.use(player.party.get(cursor-1));
                player.items.remove(curItem);
            }
            if (key.mback){
                newMenu("item select", player.items.size());
            }
        }

        if (curMenu == "equip characters"){
            if (key.menter){
                curMember = player.party.get(cursor-1);
                newMenu("equip type", 3);
            }
            if(key.mback){
                newMenu("main", 4);
            }
        }

        if (curMenu == "equip type"){
            if (key.menter){
                if (cursor == 1){
                    newMenu("equip weapons", player.weapons.size());
                }
                if (cursor == 2){
                    newMenu("equip armour", player.armours.size());
                }
                if (cursor == 3) {
                    newMenu("equip skills", skills.size());
                }
            }
            if(key.mback){
                newMenu("equip characters", player.party.size());
            }
        }

        if (curMenu == "equip weapons"){
            if (key.menter && !player.weapons.isEmpty()){
                curWeapon = player.weapons.get(cursor-1);
                newMenu("equip weapon allocation",2);
            }
            if(key.mback){
                newMenu("equip type",3);
            }
        }
        if (curMenu == "equip weapon allocation"){
            if (key.menter){
                if (cursor == 1){
                    if (curWeapon.getType() == "one hand") newMenu("choose hand",2);
                    if (curWeapon.getType() == "two hand") {
                        if (curMember.getLeftHand() != null )player.weapons.add(curMember.getLeftHand());
                        if (curMember.getRightHand() != null )player.weapons.add(curMember.getRightHand());
                        curMember.setLeftHand(curWeapon);curMember.setRightHand(curWeapon);
                        player.weapons.remove(curWeapon);
                        newMenu("equip weapons", player.weapons.size());

                    }
                }
                if (cursor == 2){
                    player.weapons.remove(curWeapon);
                    newMenu("equip weapons", player.weapons.size());
                }
            }
            if(key.mback){
                newMenu("equip weapons", player.weapons.size());
            }
        }

        if (curMenu == "choose hand"){
            if (key.menter){
                if (cursor == 1){
                    if (curMember.getLeftHand() != null )player.weapons.add(curMember.getLeftHand());
                    curMember.setLeftHand(curWeapon);
                    player.weapons.remove(curWeapon);
                }
                if (cursor == 2){
                    if (curMember.getRightHand() != null )player.weapons.add(curMember.getRightHand());
                    curMember.setRightHand(curWeapon);
                    player.weapons.remove(curWeapon);
                }
                newMenu("equip weapons", player.weapons.size());
            }
            if(key.mback){
                newMenu("equip weapons" , player.weapons.size());
            }
        }

        if (curMenu == "equip armour"){
            if (key.menter && !player.armours.isEmpty()){
                curArmour = player.armours.get(cursor-1);
                newMenu("equip armour allocation", 2);
            }
            if(key.mback){
                newMenu("equip type", player.armours.size());
            }
        }

        if (curMenu == "equip armour allocation"){
            if (key.menter){
                if (cursor == 1){
                    if (curArmour.getType() == "head"){
                        if (curMember.getHelmet() != null)player.armours.add(curMember.getHelmet());
                        curMember.setHelmet(curArmour);
                    }
                    if (curArmour.getType() == "body"){
                        if (curMember.getChestArmour() != null)player.armours.add(curMember.getChestArmour());
                        curMember.setChestArmour(curArmour);
                    }
                    if (curArmour.getType() == "arm"){
                        if (curMember.getArmArmour() != null)player.armours.add(curMember.getArmArmour());
                        curMember.setArmArmour(curArmour);
                    }
                    if (curArmour.getType() == "leg"){
                        if (curMember.getLegArmour() != null)player.armours.add(curMember.getLegArmour());
                        curMember.setLegArmour(curArmour);
                    }
                    player.armours.remove(curArmour);
                    newMenu("equip armour", player.armours.size());
                }
                if (cursor == 2){
                    player.armours.remove(curWeapon);
                    newMenu("equip armour", player.armours.size());
                }
            }
            if(key.mback){
                newMenu("equip armour", player.armours.size());
            }
        }
        if (curMenu == "equip skills") {
            if (key.menter) {
                if (curMember.skills.size() >= 4) {
                    curSkill = skills.get(cursor-1);
                    newMenu("change skills", 4);
                } else {
                    curMember.skills.add(skills.get(cursor - 1));
                    newMenu("equip type", 3);
                }
            }
            if (key.mback){
                newMenu("equip type", 3);
            }
        }

        if(curMenu == "change skills"){
            if (key.menter){
                curMember.skills.remove(cursor-1);
                curMember.skills.add(curSkill);
                newMenu("equip skills", 4);
            }
            if (key.mback){
                newMenu("equip skills", 4);
            }
        }





        if (curMenu == "quest select"){
            if (key.menter){
                curQuest = player.quests.get(cursor-1);
                newMenu("quest info",0);
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }

        if(curMenu == "quest info"){
            if (key.mback){
                newMenu("quest select",player.quests.size());
            }
        }



        pointer();

    }

    public void newMenu(String n, int i){
        cursor = 1;
        cursorMax = i;
        curMenu = n;
        ypos = 40;
        page = 0;
        key.menter =false;
        key.mback = false;
    }

    public void drawMain(Graphics g){
        g.drawString("Choose Character", 300, 67);
        g.drawString("Items", 300, 107);
        g.drawString("Equip Characters", 300, 147);
        g.drawString("View Quests", 300, 187);
        drawPointer(g);
    }

    public void drawPlayerS(Graphics g){
        for (int i = 0; i < 4; i++){
            g.drawString(player.party.get(i).getName(), 300, 67+ (i%8 * 40));
        }
        drawPointer(g);
    }

    public void drawPlayerA(Graphics g){
        g.drawString("Make party leader", 300, 67);
        g.drawString("View character stats", 300, 107);
        drawPointer(g);
    }

    public void drawPlayerSt(Graphics g){
        g.drawString("Max Health: "+curMember.getMaxHealth(), 300, 57);
        g.drawString("Health: "+curMember.getHealth(), 300, 97);
        g.drawString("Max Stamina: "+curMember.getMaxStamina(), 300, 137);
        g.drawString("Stamina: "+curMember.getStamina(), 300, 177);
        g.drawString("Attack Power: "+curMember.getAttackPwr(), 300, 217);
        g.drawString("Attack Speed: "+curMember.getAttackSpd(), 300, 257);
        g.drawString("Speed: "+curMember.getSpeed(), 300, 297);
        g.drawString("Defense: "+curMember.getDefence(), 300, 337);

    }

    public void drawItems(Graphics g){
        if (!player.items.isEmpty()) {
            int it = player.items.size();
            if (it % 8 == 0) pages = (it / 8) - 1;
            else pages = (it / 8);
            if (page == pages && it % 8 != 0) it = it % 8;
            else it = 8;

            for (int i = page * 8; i < (page * 8) + it; i++) {
                g.drawString(player.items.get(i).getName(), 300, 67 + (i % 8 * 40));
            }

            drawPointer(g);
        }
    }

    public void drawItemsA(Graphics g){
        g.drawString("Use", 300, 67);
        g.drawString("Toss", 300, 107);
        drawPointer(g);
    }

    public void drawType(Graphics g){
        g.drawString("Weapons", 300, 67);
        g.drawString("Armours", 300, 107);
        g.drawString("Skills", 300, 147);
        drawPointer(g);
    }

    public void drawWeapons(Graphics g){
        if (!player.weapons.isEmpty()) {
            int it = player.weapons.size();
            if (it % 8 == 0) pages = (it / 8) - 1;
            else pages = (it / 8);
            if (page == pages && it % 8 != 0) it = it % 8;
            else it = 8;
            for (int i = page * 8; i < (page * 8) + it; i++) {
                g.drawString(player.weapons.get(i).getName(), 300, 67 + (i % 8 * 40));
            }


            drawPointer(g);
        }
    }

    public void drawWeaponsA(Graphics g){
        g.drawString("Equip", 300, 67);
        g.drawString("Toss", 300, 107);
        g.drawString("Selected character: " + curMember.getName(), 200, 227);
        if (curMember.getLeftHand() == null )g.drawString("Left Hand: ---", 250, 267);
        else g.drawString("Left Hand: " + curMember.getLeftHand().getName(),250, 267 );
        if (curMember.getRightHand() == null )g.drawString("Right Hand: ---", 250, 307);
        else g.drawString("Right Hand: " + curMember.getRightHand().getName(),250, 307 );

        drawPointer(g);
    }

    public void drawHand(Graphics g){
        g.drawString("Left", 300, 67);
        g.drawString("Right", 300, 107);

        g.drawString("Selected character: " + curMember.getName(), 200, 227);
        if (curMember.getLeftHand() == null )g.drawString("Left Hand: ---", 250, 267);
        else g.drawString("Left Hand: " + curMember.getLeftHand().getName(),250, 267 );
        if (curMember.getRightHand() == null )g.drawString("Right Hand: ---", 250, 267);
        else g.drawString("Right Hand: " + curMember.getRightHand().getName(),250, 267 );

        drawPointer(g);
    }

    public void drawArmour(Graphics g){
        if (!player.armours.isEmpty()) {
            int it = player.armours.size();
            if (it % 8 == 0) pages = (it / 8) - 1;
            else pages = (it / 8);
            if (page == pages && it % 8 != 0) it = it % 8;
            else it = 8;

            for (int i = page * 8; i < (page * 8) + it; i++) {
                g.drawString(player.armours.get(i).getName(), 300, 67 + (i % 8 * 40));
            }


            drawPointer(g);
        }
    }

    public void drawArmourA(Graphics g){
        g.drawString("Equip", 300, 67);
        g.drawString("Toss", 300, 107);

        g.drawString("Selected character: " + curMember.getName(), 200, 227);
        if (curMember.getHelmet() == null )g.drawString("Helmet: ---", 250, 267);
        else g.drawString("Helmet: " + curMember.getHelmet().getName(),250, 267 );
        if (curMember.getChestArmour() == null )g.drawString("Chest Armour: ---", 250, 307);
        else g.drawString("Chest Armour: " + curMember.getChestArmour().getName(),250, 307 );
        if (curMember.getArmArmour() == null )g.drawString("Gauntlets: ---", 250, 347);
        else g.drawString("Gauntlets: " + curMember.getArmArmour().getName(),250, 347 );
        if (curMember.getLegArmour() == null )g.drawString("Greaves: ---", 250, 387);
        else g.drawString("Greaves: " + curMember.getLegArmour().getName(),250, 387 );

        drawPointer(g);
    }

    public void drawSkill(Graphics g){
        for (int i = 0; i < skills.size(); i++){
            g.drawString(skills.get(i).getName(), 300, 67 + i*40);
        }

        drawPointer(g);
    }

    public void drawSkillC(Graphics g){
        for (int i = 0; i < curMember.skills.size(); i++){
            g.drawString(curMember.skills.get(i).getName(), 300, 67 + i*40);
        }

        drawPointer(g);
    }


    public void drawQuest(Graphics g){
        if (player.quests.isEmpty())g.drawString("No quests at current time.", 300, 67);
        else {
            int it = player.quests.size();
            if (it%8 == 0)pages = (it/8)-1;
            else pages = (it/8);
            if (page == pages && it%8 != 0) it = it%8;
            else it = 8;

            for (int i = page*8; i < (page*8)+ it; i++){
                g.drawString(player.quests.get(i).getName(), 300, 67+ (i%8 * 40));
            }


            drawPointer(g);
        }
    }

    public void drawQuestInfo(Graphics g){
        String[] s = curQuest.info.split("/");

        int it = s.length;
        if (it%8 == 0)pages = (it/8);
        else pages = (it/8)+1;
        if (page == pages-1 && it%8 != 0) it = it%8;
        else it = 8;



        if (page == pages){
            g.drawString("Rewards: ", 150, 57);
            for (int i = 0; i < curQuest.rewards.size(); i++){
                g.drawString(curQuest.rewards.get(i).getName(), 150, 127 + (i * 40));
            }
            g.drawString("Gold: ", 600, 57);
            g.drawString(""+curQuest.goldReward, 600, 127);

        }else {
            for (int i = page*8; i < (page*8)+ it; i++){
                g.drawString(s[i], 120, 67+ (i%8 * 40));
            }
        }
    }



    public void draw(Graphics g, Image img, int x, int y, int h, int w){
        xpos = x;
        ypos = y;
        wscale = img.getWidth(null)*w;
        hscale = img.getHeight(null)*h;
        g.drawImage(img, xpos, ypos, wscale, hscale, null, null);
    }


    public void pointer(){
        if (key.mdir == 0){
            if (cursor == 1) {
                ypos = (cursorMax%pageItems)* 40;
                cursor = cursorMax;
                key.mdir = -1;
                page = pages;
            } else if (cursor%pageItems == 1){
                ypos = pageItems* 40;
                cursor--;
                key.mdir = -1;
                page--;
            }else {
                ypos -= 40;
                cursor--;
                key.mdir = -1;
            }
        }else if (key.mdir == 1){
            if (page == pages) page = 0;
            else page++;
            cursor = page*pageItems + 1;
            key.mdir = -1;
        } else if(key.mdir == 2){
            if (cursor == cursorMax) {
                ypos = 40;
                cursor = 1;
                key.mdir = -1;
                page = 0;
            } else if (cursor%pageItems == 0){
                ypos = 40;
                cursor++;
                key.mdir = -1;
                page++;
            } else {
                ypos += 40;
                cursor++;
                key.mdir = -1;
            }
        }if (key.mdir == 3) {
            if (page == 0) page = pages;
            else page--;
            cursor = page*pageItems + 1;
            key.mdir = -1;

        }
    }

    public void drawPointer(Graphics g){
        draw(g, pointer, xpos, ypos ,2 ,2);
    }


    public void skillList(){
        skills.add(Skills.pierce);
        skills.add(Skills.stab);
        skills.add(Skills.spray);
        skills.add(Skills.slash);
    }
}
