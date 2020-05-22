package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class Wall implements Block {

    private Image image = new ImageIcon("mapImages/wall.png").getImage();

    @Override
    public boolean pass() {
        return false;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }
}
