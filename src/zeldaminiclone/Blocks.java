package zeldaminiclone;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blocks extends Rectangle {
    public static BufferedImage wallTile;
    public static BufferedImage grassTile;

    public Blocks(int x, int y){
        super(x,y, 32,32);

    }

    public Blocks(double v) {
    }

    public void render(Graphics g ){
        if(wallTile != null){
            g.drawImage(wallTile, x, y, 32,32,null);
        }else {
            g.setColor(Color.GRAY);
            g.fillRect(x,y,32,32);
        }
     }


}
