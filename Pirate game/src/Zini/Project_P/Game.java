package Zini.Project_P;

import Zini.Project_P.entity.characters.NPC;
import Zini.Project_P.entity.characters.Player;
import Zini.Project_P.entity.items.Quests;
import Zini.Project_P.graphics.Screen;
import Zini.Project_P.graphics.ui.Battle;
import Zini.Project_P.graphics.ui.Shop;
import Zini.Project_P.graphics.ui.UIStuff;
import Zini.Project_P.input.Keyboard;
import Zini.Project_P.level.Level;
import Zini.Project_P.level.LevelReader;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;


public class Game extends Canvas implements Runnable
{

    public static int width = 300;
    public static int height = width/ 16*9;
    public static int scale = 3;
    public static String title = "Project P";

    public boolean inBattle;
    public boolean inExplore;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;
    private Keyboard key;
    private Screen screen;
    private Level level;
    private Player player;
    private UIStuff ui;
    private Shop shop;
    private Battle battle;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    private ArrayList<NPC> npcList = new ArrayList<NPC>();



    public Game()
    {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        npcList.add(NPC.npc1); npcList.add(NPC.npc2);
        npcList.add(NPC.npc3); npcList.add(NPC.npc4);
        npcList.add(NPC.npc5); npcList.add(NPC.npc6);
        npcList.add(NPC.npc7); npcList.add(NPC.npc8);

        screen = new Screen(width, height);
        key = new Keyboard();
        frame = new JFrame();
        level = LevelReader.islandLevel;
        player = new Player(5*16,5*16,key);
        player.init(level);
        ui = new UIStuff(key, player);
        shop = new Shop(key, player);
        addKeyListener(key);
        inExplore = true;
        inBattle = false;
    }


    public synchronized void start()
    {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void tick()
    {
        key.update();
        if (inExplore){
            player.update();
            if (key.enterMenu)ui.update();
            if (key.enterShop)shop.update();
            level = player.level;
            if (((player.moving && Math.random()*50000000 < 1 && level != LevelReader.townLevel ) || player.boss) && player.quests.contains(Quests.blackbeardsTreasure)&& player.alive()){
                inExplore = false;
                inBattle = true;
                battle = new Battle(key, player, this);
                key.enterBattle = true;
            }
        }
        if (inBattle){
            battle.update();
        }
    }

    public void render()
    {


        BufferStrategy buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        if (inBattle )battle.render(g);
        else {
            screen.clear();
            level.render(player.x - screen.width / 2, player.y - screen.height / 2, screen);
            player.render(screen);
            if (level.levelName == "Town") {
                for (int i = 0; i < npcList.size(); i++) {
                    npcList.get(i).render(screen);
                }
            }


            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }


            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            if (key.enterMenu) ui.render(g);
            if (key.enterShop) shop.render(g);


        }
        g.dispose();
        buffer.show();
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int fps = 0;
        int ups = 0;
        requestFocus();
        while (running)
        {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                ups++;
                delta--;
            }
            render();
            fps++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frame.setTitle(title + " | " + " ups: " + ups + " fps: " + fps);
                ups = 0;
                fps = 0;
            }
        }
        stop();
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}

