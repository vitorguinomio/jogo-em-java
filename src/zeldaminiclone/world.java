package zeldaminiclone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class world {
    public static List<Blocks> blocks = new ArrayList<Blocks>();

    public world() {
        int blocksX = Game.WIDTH / 32;
        int blocksY = Game.HEIGHT / 32;

        for (int xx = 0; xx < blocksX; xx++) {
            blocks.add(new Blocks(xx * 32, 0));
            blocks.add(new Blocks(xx * 32, Game.HEIGHT - 32));
        }

        for (int yy = 0; yy < blocksY; yy++) {
            blocks.add(new Blocks(0, yy * 32)); // Esquerda
            blocks.add(new Blocks(Game.WIDTH - 32, yy * 32)); // Direita
        }
    }

    public static boolean isFree(int x, int y) {
        for (int i = 0; i < blocks.size(); i++) {
            Blocks blocoatual = blocks.get(i);
            if (blocoatual.intersects(new Rectangle(x, y, 32, 32))) {
                return false;
            }
        }
        return true;
    }

    public void render(Graphics g) {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }
    }
}