package objects.blocks.doors;

import objects.blocks.Block;
import source.Values;

import javax.swing.*;
import java.awt.*;

public class BlockedDoor implements Block {


    private Image image = new ImageIcon("doors/doubleDoor.png").getImage();

    @Override
    public boolean pass() {
        return false;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y, Values.CELL_SIZE,Values.CELL_SIZE, null);
    }

}
