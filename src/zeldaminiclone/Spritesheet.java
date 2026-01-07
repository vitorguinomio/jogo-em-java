package zeldaminiclone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    private BufferedImage sheet;

    public Spritesheet(String path) {
        try {

            sheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return sheet.getSubimage(x,y,width,height);
    }
}