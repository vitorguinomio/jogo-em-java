package zeldaminiclone;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullets extends Rectangle {

    public int dir = 1;
    public int speed = 8;

    public int frames = 0;
    public int maxFrames = 5;
    public int index = 0;
    public int maxIndex = 2;

    private BufferedImage[] rightSprites;
    private BufferedImage[] leftSprites;
    private BufferedImage[] upSprites;
    private BufferedImage[] downSprites;

    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public boolean hitting = false;
    public boolean destroy = false;

    public Bullets(int x, int y, int dir) {
        super(x, y, 32, 32);
        this.dir = dir;

        rightSprites = new BufferedImage[3];
        leftSprites = new BufferedImage[3];
        upSprites = new BufferedImage[3];
        downSprites = new BufferedImage[3];

        hitSprites = new BufferedImage[5];

        rightSprites[0] = Game.sheetShoot.getSprite(102, 103, 170, 115);
        rightSprites[1] = Game.sheetShoot.getSprite(338, 110, 170, 115);
        rightSprites[2] = Game.sheetShoot.getSprite(338, 110, 170, 115);

        leftSprites[0] = Game.sheetShoot.getSprite(102, 103, 170, 115);
        leftSprites[1] = Game.sheetShoot2.getSprite(748, 305, 170, 115);
        leftSprites[2] = Game.sheetShoot2.getSprite(504, 300, 170, 115);


        upSprites[0] = Game.sheetShoot2.getSprite(210, 230, 115, 170);
        upSprites[1] = Game.sheetShoot2.getSprite(205, 0, 115, 170);
        upSprites[2] = Game.sheetShoot2.getSprite(715, 0, 115, 170);


        downSprites[0] = Game.sheetShoot.getSprite(205, 590, 115, 170);
        downSprites[1] = Game.sheetShoot.getSprite(710, 325, 115, 170);
        downSprites[2] = Game.sheetShoot.getSprite(715, 580, 115, 170);


        hitSprites[0] = Game.sheetShoot.getSprite(75, 825, 170, 115);
        hitSprites[1] = Game.sheetShoot.getSprite(275, 825, 150, 135);
        hitSprites[2] = Game.sheetShoot.getSprite(460, 830, 150, 135);
        hitSprites[3] = Game.sheetShoot.getSprite(660, 830, 150, 135);
        hitSprites[4] = Game.sheetShoot.getSprite(830, 830,150, 135);
    }

    public void tick() {
        if (hitting) {

            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex) {
                    destroy = true;
                }
            }
        } else {

            if (dir == 0 && !world.isFree(x + speed, y)) hitWall();
            else if (dir == 1 && !world.isFree(x - speed, y)) hitWall();
            else if (dir == 2 && !world.isFree(x, y - speed)) hitWall();
            else if (dir == 3 && !world.isFree(x, y + speed)) hitWall();

            if (!hitting) {
                if (dir == 0) x += speed;
                else if (dir == 1) x -= speed;
                else if (dir == 2) y -= speed;
                else if (dir == 3) y += speed;


                frames++;
                if (frames == maxFrames) {
                    frames = 0;
                    index++;
                    if (index > maxIndex) {
                        index = 0;
                    }
                }
            }
        }
    }

    private void hitWall() {
        hitting = true;
        frames = 0;
        index = 0;
        maxIndex = 4;
    }

    public void render(Graphics g) {
        if (hitting) {

            if(index < hitSprites.length)
                g.drawImage(hitSprites[index], x, y, width, height, null);
        } else {

            if(index < sprites.length)
                g.drawImage(sprites[index], x, y, width, height, null);
        }
    }
}