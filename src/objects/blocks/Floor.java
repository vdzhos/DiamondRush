package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class Floor implements Block{

    private Image image = new ImageIcon("mapImages/floor.png").getImage();
    private boolean passable = true;

    @Override
    public boolean pass() {
        return passable;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    public void setPassable(boolean passable){
        this.passable = passable;
    }
}
