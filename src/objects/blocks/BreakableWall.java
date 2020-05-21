package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class BreakableWall implements Block{

    private ImageIcon[] images = {new ImageIcon(""), new ImageIcon("")};

    @Override
    public boolean pass() {
//        breakableWall.setIcon(images[1]);
        return true;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.setColor(Color.GREEN);
        g2.fillRect(x,y,70,70);
    }
}
