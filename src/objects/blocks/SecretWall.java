package objects.blocks;

import maps.Level;

import javax.swing.*;
import java.awt.*;

public class SecretWall implements Block{

    private Image image = new ImageIcon("mapImages/secretWall.png").getImage();
    private int x;
    private int y;

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
