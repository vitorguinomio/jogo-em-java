package zeldaminiclone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

    public String[] options = {"Começar", "Carregar Fase", "Sair"};
    public int currentOption = 0;
    public int maxOption = options.length - 1;

    public boolean up, down, enter;

    public void tick() {
        // Lógica de navegação
        if (up) {
            up = false;
            currentOption--;
            if (currentOption < 0) {
                currentOption = maxOption;
            }
        }
        if (down) {
            down = false;
            currentOption++;
            if (currentOption > maxOption) {
                currentOption = 0;
            }
        }
        if (enter) {
            enter = false;
            if (options[currentOption] == "Começar") {
                Game.gameState = "NORMAL";
            } else if (options[currentOption] == "Sair") {
                System.exit(1);
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

        // Título do Jogo        g.setColor(Color.RED);
        g.setFont(new Font("arial", Font.BOLD, 36));
        g.drawString("> Zelda Clone <", (Game.WIDTH * Game.SCALE) / 2 - 110, (Game.HEIGHT * Game.SCALE) / 2 - 160);

        // Opções de Menu
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 24));

        for (int i = 0; i < options.length; i++) {
            if (i == currentOption) {
                g.setColor(Color.YELLOW); // Opção selecionada fica amarela
                g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 90, ((Game.HEIGHT * Game.SCALE) / 2) + (i * 40));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (Game.WIDTH * Game.SCALE) / 2 - 50, ((Game.HEIGHT * Game.SCALE) / 2) + (i * 40));
        }
    }
}