package Zini.Project_P.graphics.ui;

import Zini.Project_P.entity.characters.Player;
import Zini.Project_P.entity.items.Armour;
import Zini.Project_P.entity.items.Consumable;
import Zini.Project_P.entity.items.Quests;
import Zini.Project_P.entity.items.Weapon;
import Zini.Project_P.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by szini on 02/01/2018.
 */
public class Shop extends UIStuff {
    public ArrayList<Consumable> items = new ArrayList<Consumable>();
    public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    public ArrayList<Quests> quests = new ArrayList<Quests>();
    public ArrayList<Armour> armours = new ArrayList<Armour>();


    public Shop(Keyboard key, Player player) {
        super(key, player);

        populate();
        cursorMax = 4;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(ff6Font);

        g.drawImage(blueMenu, 90, 20, blueMenu.getWidth(null)*3, blueMenu.getHeight(null)*5/2, null, null);
        if (curMenu == "main")drawMain(g);
        if (curMenu == "buy")drawBuySell(g);
        if (curMenu == "buy items")drawBuyI(g);
        if (curMenu == "buy weapons")drawBuyW(g);
        if (curMenu == "buy armours")drawBuyA(g);
        if (curMenu == "sell")drawBuySell(g);
        if (curMenu == "sell items")drawItems(g);
        if (curMenu == "sell weapons")drawWeapons(g);
        if (curMenu == "sell armours")drawArmour(g);
        if (curMenu == "quests")drawQuest(g);
        if (curMenu == "quest take")drawQuestTake(g);
        if (curMenu == "quest info")drawQuestInfo(g);
        if (curMenu == "quests collection")drawQuestC(g);
        if (curMenu == "reward")drawQuestR(g);
        drawGold(g);
    }

    public void update(){
        if (curMenu == "main"){
            if (key.menter){
                if (cursor == 1) {
                    newMenu("buy", 3);
                }
                if (cursor == 2) {
                    newMenu("sell", 3);
                }
                if (cursor == 3) {
                    newMenu("quests", quests.size());
                }
                if (cursor == 4){
                    newMenu("quests collection", player.quests.size());
                }
            }
            if (key.mback){
                key.enterShop = false;
                key.mback = false;
            }
        }
        if (curMenu == "buy"){
            if (key.menter){
                if (cursor == 1) {
                    newMenu("buy items", items.size());
                }
                if (cursor == 2) {
                    newMenu("buy weapons", weapons.size());
                }
                if (cursor == 3) {
                    newMenu("buy armours", armours.size());
                }
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }
        if (curMenu == "buy items"){
            if (key.menter){
                if (player.gold > items.get(cursor-1).getPrice()){
                    player.items.add(items.get(cursor-1));
                    player.gold -= items.get(cursor-1).getPrice();
                }
                key.menter = false;
            }
            if (key.mback){
                newMenu("buy", 3);
            }
        }
        if (curMenu == "buy weapons"){
            if (key.menter){
                if (player.gold > weapons.get(cursor-1).getPrice()){
                    player.weapons.add(weapons.get(cursor-1));
                    player.gold -= weapons.get(cursor-1).getPrice();
                }
                key.menter = false;
            }
            if (key.mback){
                newMenu("buy", 3);
            }
        }
        if (curMenu == "buy armours"){
            if (key.menter){
                if (player.gold > armours.get(cursor-1).getPrice()){
                    player.armours.add(armours.get(cursor-1));
                    player.gold -= armours.get(cursor-1).getPrice();
                }
                key.menter = false;
            }
            if (key.mback){
                newMenu("buy", 3);
            }
        }

        if (curMenu == "sell"){
            if (key.menter){
                if (cursor == 1) {
                    newMenu("sell items", player.items.size());
                }
                if (cursor == 2) {
                    newMenu("sell weapons", player.weapons.size());
                }
                if (cursor == 3) {
                    newMenu("sell armours", player.armours.size());
                }
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }

        if (curMenu == "sell items"){
            if (key.menter){
                player.gold += player.items.get(cursor-1).getPrice();
                player.items.remove(player.items.get(cursor-1));
            }
            if (key.mback){
                newMenu("sell", 3);
            }
        }
        if (curMenu == "sell weapons"){
            if (key.menter){
                player.gold += player.weapons.get(cursor-1).getPrice();
                player.weapons.remove(player.weapons.get(cursor-1));
            }
            if (key.mback){
                newMenu("sell", 3);
            }
        }
        if (curMenu == "sell armour"){
            if (key.menter){
                player.gold += player.armours.get(cursor-1).getPrice();
                player.armours.remove(player.armours.get(cursor-1));
            }
            if (key.mback){
                newMenu("sell", 3);
            }
        }

        if (curMenu == "quests"){
            if (key.menter){
                if (!quests.isEmpty()) {
                    curQuest = quests.get(cursor - 1);
                    newMenu("quest take", 2);
                }
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }

        if (curMenu == "quest take"){
            if (key.menter){
                if (cursor == 1){
                    player.quests.add(curQuest);
                    quests.remove(curQuest);
                    newMenu("main", 4);
                }
                if (cursor == 2){
                    newMenu("quest info",0);
                }
            }
            if (key.mback){
                newMenu("quests", quests.size());
            }
        }

        if(curMenu == "quest info"){
            if (key.mback){
                newMenu("quest take",player.quests.size());
            }
        }

        if (curMenu == "quests collection"){
            if (key.menter){
                if (!player.quests.isEmpty()){
                    if (player.quests.get(cursor-1).hasTreasure) {
                        curQuest = player.quests.get(cursor - 1);
                        newMenu("reward", 1);
                    }
                }
            }
            if (key.mback){
                newMenu("main", 4);
            }

        }
        if (curMenu == "reward"){
            if (key.menter) {
                if (curQuest.hasTreasure) {
                    for (int i = 0; i < curQuest.rewards.size(); i++) {
                        if (curQuest.rewards.get(i) instanceof Armour)
                            player.armours.add((Armour) curQuest.rewards.get(i));
                        if (curQuest.rewards.get(i) instanceof Weapon)
                            player.weapons.add((Weapon) curQuest.rewards.get(i));
                    }
                    player.gold += curQuest.goldReward;
                    player.quests.remove(curQuest);
                    newMenu("main", 4);
                }
            }
            if (key.mback){
                newMenu("main", 4);
            }
        }
        pointer();
    }

    @Override
    public void drawMain(Graphics g){
        g.drawString("Buy", 300, 67);
        g.drawString("Sell", 300, 107);
        g.drawString("Quests", 300, 147);
        g.drawString("Collect Reward", 300, 187);
        drawPointer(g);
    }

    public void drawBuySell(Graphics g){
        g.drawString("Items", 300, 67);
        g.drawString("Weapons", 300, 107);
        g.drawString("Armour", 300, 147);

        drawPointer(g);
    }

    public void drawBuyI(Graphics g){
        for (int i = 0; i < items.size(); i++){
            g.drawString(items.get(i).getName(), 300, 67 + i*40);
            g.drawString(""+items.get(i).getPrice(), 600, 67 + i*40);
        }

        drawPointer(g);
    }

    public void drawBuyW(Graphics g){
        for (int i = 0; i < weapons.size(); i++){
            g.drawString(weapons.get(i).getName(), 300, 67 + i*40);
            g.drawString(""+weapons.get(i).getPrice(), 600, 67 + i*40);
        }
        drawPointer(g);

    }
    public void drawBuyA(Graphics g){
        for (int i = 0; i < armours.size(); i++){
            g.drawString(armours.get(i).getName(), 300, 67 + i*40);
            g.drawString(""+armours.get(i).getPrice(), 600, 67 + i*40);
        }
        drawPointer(g);

    }

    public void drawQuestTake(Graphics g){
        g.drawString("Take Quest", 300, 67);
        g.drawString("View Quest info", 300, 107);

        drawPointer(g);
    }

    public void drawQuest(Graphics g){
        for (int i = 0; i < quests.size(); i++){
            g.drawString(quests.get(i).getName(), 300, 67 + i*40);
        }

        drawPointer(g);
    }
    public void drawQuestC(Graphics g){
        for (int i = 0; i < player.quests.size(); i++){
            if (player.quests.get(i).hasTreasure) g.drawString(player.quests.get(i).getName(), 300, 67 + i*40);
        }

        drawPointer(g);
    }
    public void drawQuestR(Graphics g){
        g.drawString("Collect reward", 300, 67);

        drawPointer(g);
    }



    public void drawGold(Graphics g){
        g.drawString(""+player.gold, 100, 400);
    }

    public void populate(){
        quests.add(Quests.blackbeardsTreasure);
        items.add(Consumable.medKit);
        weapons.add(Weapon.scimitar);
        weapons.add(Weapon.knife);
        weapons.add(Weapon.broadSword);
        weapons.add(Weapon.musket);
        armours.add(Armour.chainmail);
        armours.add(Armour.chainGauntlets);
        armours.add(Armour.chainHelm);
        armours.add(Armour.chainGreaves);
    }


}
