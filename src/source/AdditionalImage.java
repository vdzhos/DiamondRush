package source;

import objects.blocks.Block;

import javax.swing.*;
import java.awt.*;

public class AdditionalImage {

    public Image image;
    public int x;
    public int y;
    public int width;
    public int height;


    public AdditionalImage(String path, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new ImageIcon(path).getImage();
    }


    public void paintObject(Graphics2D g2, int mapX, int mapY) {
        g2.drawImage(image, x+mapX,y+mapY,width,height,null);
    }
}
