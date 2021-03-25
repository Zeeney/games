package Zini.Project_P.entity.items;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by szini on 21/04/2018.
 */
public class Quests {
    public ArrayList<Items> rewards = new ArrayList<Items>();
    public int goldReward;
    public String info;

    public boolean hasTreasure;

    private String name;

    public static Quests blackbeardsTreasure = new Quests("Blackbeards Treasure", new ArrayList<Items>(Arrays.asList(Armour.chainGauntlets, Armour.chainHelm)), 10000,
                                                "Blackbeard is Dead. /" +
                                                        "Some Rookie pirates happened across /" +
                                                        "his treasure in the mountain /" +
                                                        "behind the town. Only problem /" +
                                                        "was that it was guarded by a /" +
                                                        "fierce ape king. To anyone who /" +
                                                        "can retrieve the treasure and /" +
                                                        "return it to the British navy,/ " +
                                                        "a hefty reward is waiting for you. / ");

    public Quests(String name, ArrayList<Items> rewards, int goldReward, String info){
        this.name = name;
        this.rewards = rewards;
        this.goldReward = goldReward;
        this.info = info;
    }

    public String getName() {
        return name;
    }
}
