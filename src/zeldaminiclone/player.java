package zeldaminiclone;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class player extends Rectangle {
    public BufferedImage rightPlayer;
    public BufferedImage leftPlayer;
    public BufferedImage upPlayer;
    public BufferedImage downPlayer;
    public int dir = 0;
    public boolean right, up, down, left;
    public int spd = 3;
    public BufferedImage playerImage;
    public boolean shoot = false;

    public List<Bullets> Bullets = new ArrayList<>();

    public player (int x, int y ){
        this.x = x;
        this.y = y;
        rightPlayer = Game.sheetPlayer.getSprite(303, 275, 150, 190);
        leftPlayer = Game.sheetPlayer.getSprite(313, 22, 150, 190);

        upPlayer = Game.sheetPlayer.getSprite(67, 274, 150, 190);

        downPlayer = Game.sheetPlayer.getSprite(72, 35, 150, 190);
    }

    public void tick() {
        if (right && world.isFree(x+spd, y)) {
            x += spd;
            dir =0;
        } else if (left && world.isFree(x -spd, y)) {
            x -= spd;
            dir =1;
        }

        if (up && world.isFree(x, y - spd)) {
            y -= spd;
            dir = 2;
        } else if (down && world.isFree(x, y + spd)) {
            y += spd;
            dir= 3;
        }
        if (shoot){
            shoot = false;
            Bullets.add(new Bullets(x,y, dir));
        }

        for(int i = 0; i<Bullets.size(); i++){
            Bullets.get(i).tick();
        }
    }

    public void render(Graphics g) {
        if(dir == 0) {
            g.drawImage(rightPlayer, x, y, 32, 32, null);
        }
        else if(dir == 1) {
            g.drawImage(leftPlayer, x, y, 32, 32, null);
        }
        else if(dir == 2) {
            g.drawImage(upPlayer, x, y, 32, 32, null);
        }
        else if(dir == 3) {
            g.drawImage(downPlayer, x, y, 32, 32, null);
        }
        for (int i = 0; i<Bullets.size(); i++){
            Bullets.get(i).render(g);
        }
    }

}
