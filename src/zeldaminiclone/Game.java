package zeldaminiclone;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

// Renomeei para Game para combinar com o seu construtor
public class Game extends Canvas implements Runnable, KeyListener { 
    public static int WIDTH = 640, HEIGHT = 680, SCALE = 3;
    public enum State {
        CHAR_SELECT,
        CREDITS,
        EDITOR,
        GAME,
        LEVEL_SELECTION,
        MENU

    }
    public  static State state = State.MENU;
    public static player player;
    public world world;
    public Menu menu;
    public static String gameState = "MENU";
    public static Spritesheet sheetPlayer;
    public static Spritesheet sheetBlocks;
    public static Spritesheet sheetEnemies;
    public static Spritesheet sheetShoot;
    public static Spritesheet sheetShoot2;
    public static Spritesheet sheetNewEnemies;
    public static java.util.List<inimigos> enemies = new java.util.ArrayList<>();
    private BufferedImage playerImage;
    private BufferedImage grassImage;
    private BufferedImage enemyImage;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        sheetPlayer = new Spritesheet("/spritesheet.png");
        sheetBlocks = new Spritesheet("/Spresheet_blocs.png");
        sheetEnemies = new Spritesheet("/spritesheet_floresta.png");
        sheetShoot = new Spritesheet("/spreisheet_tiro.png");
        sheetShoot2 = new Spritesheet("/spreisheet_tiro2.png");
        sheetNewEnemies = new Spritesheet("/enemies.png");
        Blocks.wallTile = sheetBlocks.getSprite(9, 146, 3, 3);
        Blocks.grassTile = sheetEnemies.getSprite(225, 0, 40, 40);
        player = new  player(32,32);
        player.playerImage= sheetPlayer.getSprite(69,36,145,187);
        enemies.add(new inimigos(100, 100, 1));
        enemies.add(new inimigos(200, 200, 2));
        enemies.add(new inimigos(300, 300, 3));
        world  = new world();
        menu = new Menu();
                                
    }

    public void tick(){
        switch (gameState){
            case "MENU":
                menu.tick();
                break;
            case "GAME":
                player.tick();
                break;
            case "LEVEL_SELECTION":

                break;
            case "EDITOR":

                break;
            case "CREDITS":

                break;
            case "CHAR_SELECT":

                break;
        }
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        if (g == null){
            return;
        }
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        switch (gameState){
            case "MENU":
                menu.render(g);
                break;
            case "GAME":
                g2.scale(SCALE, SCALE);
                if(Blocks.grassTile != null){
                        for (int x = 0; x < WIDTH; x += 32) {
                            for (int y = 0; y < HEIGHT; y += 32) {
                                g.drawImage(Blocks.grassTile, x, y, 32, 32, null);
                            }
                        }
                    }
                world.render(g);
                player.render(g);
                break;
            case "LEVEL_SELECTION":

                break;
            case "EDITOR":

                break;
            case "CREDITS":

                break;
            case "CHAR_SELECT":

                break;
        }
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("MiniZelda");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(game).start();
    }

    @Override
    public void run() {
        while(true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameState == "NORMAL"){
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.right = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.left = true;

            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                player.up = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.down = true;

            }
            if (e.getKeyCode() == KeyEvent.VK_Z){
                player.shoot= true;
            }
        } else if (gameState == "MENU") {
            if(e.getKeyCode() == KeyEvent.VK_UP){
                menu.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                menu.down = true;
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                menu.enter = true;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}