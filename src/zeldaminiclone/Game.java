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
    public player player;
    public world world;
    public static Spritesheet sheetPlayer;
    public static Spritesheet sheetBlocks;
    public static Spritesheet sheetEnemies;
    public static Spritesheet sheetShoot;
    public static Spritesheet sheetShoot2;
    public static Spritesheet sheetNewEnemies;

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
        world  = new world();
    }

    public void tick(){
        player.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,WIDTH*SCALE, HEIGHT*SCALE);
        g2.scale(SCALE,SCALE);
        if(Blocks.grassTile != null) {
            for (int x = 0; x < WIDTH; x += 32) {
                for (int y = 0; y < HEIGHT; y += 32) {
                    g.drawImage(Blocks.grassTile, x, y, 32, 32, null);
                }
            }
        }
        player.render(g);
        world.render(g);
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
        // TODO Auto-generated method stub
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