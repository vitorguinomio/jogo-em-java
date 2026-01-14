package zeldaminiclone;

import java.awt.*;
import java.awt.image.BufferedImage;

public class inimigos extends Rectangle {

    public int spd = 1;
    public int type;
    public int frames = 0, maxFrames = 30, index = 0, maxIndex = 3;
    private BufferedImage[] spritesOrc;
    private BufferedImage[] spritesSlime;
    private BufferedImage[] spritesMago;


    public inimigos(int x, int y, int type) {
        super(x, y, 32, 32);
        this.type = type;
        spritesOrc = new BufferedImage[3];
        spritesMago = new BufferedImage[3];
        spritesSlime = new BufferedImage[3];


        if(type == 1) {

            spritesOrc[0] = Game.sheetNewEnemies.getSprite(0, 15, 85, 100);
            spritesOrc[1] = Game.sheetNewEnemies.getSprite(110, 15, 85, 100);
            spritesOrc[2] = Game.sheetNewEnemies.getSprite(195, 15, 85, 100);
        }
        else if(type == 2) {

            spritesMago[0] = Game.sheetNewEnemies.getSprite(0, 135, 90, 100);
            spritesMago[1] = Game.sheetNewEnemies.getSprite(116, 135, 90, 100);
            spritesMago[2] = Game.sheetNewEnemies.getSprite(215, 135, 90, 100);
        }
        else if(type == 3) {

            spritesSlime[0] = Game.sheetNewEnemies.getSprite(0, 285, 100, 100);
            spritesSlime[1] = Game.sheetNewEnemies.getSprite(100, 285, 100, 100);
            spritesSlime[2] = Game.sheetNewEnemies.getSprite(185, 285, 100, 100);
        }
    }

    public void tick() {

        if(Game.player.x < this.x && world.isFree(x - spd, y)) {
            x -= spd;
        } else if(Game.player.x > this.x && world.isFree(x + spd, y)) {
            x += spd;
        }

        // Persegue no Eixo Y
        if(Game.player.y < this.y && world.isFree(x, y - spd)) {
            y -= spd;
        } else if(Game.player.y > this.y && world.isFree(x, y + spd)) {
            y += spd;
        }

        // --- ANIMAÇÃO ---
        frames++;
        if(frames == maxFrames) {
            frames = 0;
            index++;
            if(index > maxIndex) {
                index = 0;
            }
        }
    }

    public void render(Graphics g) {
        // Se for TIPO 1 (Orc), desenha usando a lista do Orc
        if (type == 1) {
            // Verifica se o index é seguro para evitar erros
            if (index < spritesOrc.length) {
                g.drawImage(spritesOrc[index], x, y, 32, 32, null);
            }
        }
        // Se for TIPO 2 (Mago)
        else if (type == 2) {
            if (index < spritesMago.length) {
                g.drawImage(spritesMago[index], x, y, 32, 32, null);
            }
        }
        // Se for TIPO 3 (Slime)
        else if (type == 3) {
            if (index < spritesSlime.length) {
                g.drawImage(spritesSlime[index], x, y, 32, 32, null);
            }
        }
    }}