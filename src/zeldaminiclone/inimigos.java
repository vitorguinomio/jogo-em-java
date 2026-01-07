package zeldaminiclone;

import java.awt.*;
import java.awt.image.BufferedImage;

public class inimigos extends Rectangle {

    public int spd = 1; // Inimigo geralmente é mais lento que o player
    public int type;    // 1 = Orc, 2 = Mago, 3 = Slime

    // Animação
    public int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
    private BufferedImage[] sprites;

    public inimigos(int x, int y, int type) {
        super(x, y, 32, 32);
        this.type = type;
        sprites = new BufferedImage[4]; // Assumindo 4 frames de animação

        // --- AQUI VOCÊ CARREGA OS SPRITES BASEADO NO TIPO ---
        // Você precisará pegar o X e Y exato no Paint para cada linha da imagem

        for(int i = 0; i < 4; i++) {
            if(type == 1) {
                // ORC (Primeira linha da imagem)
                // Exemplo: X muda a cada 32px (ou tamanho do seu sprite)
                sprites[i] = Game.sheetNewEnemies.getSprite(i * 32, 0, 32, 32);
            }
            else if(type == 2) {
                // MAGO (Segunda linha)
                sprites[i] = Game.sheetNewEnemies.getSprite(i * 32, 32, 32, 32);
            }
            else if(type == 3) {
                // SLIME (Terceira linha)
                sprites[i] = Game.sheetNewEnemies.getSprite(i * 32, 64, 32, 32);
            }
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
        // Desenha o sprite atual da animação
        if(index < sprites.length) {
            g.drawImage(sprites[index], x, y, 32, 32, null);
        }
    }
}