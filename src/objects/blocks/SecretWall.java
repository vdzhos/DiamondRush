package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class SecretWall implements Block{

    private ImageIcon image = new ImageIcon("");


    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.setColor(Color.MAGENTA);
        g2.fillRect(x,y,70,70);
    }
}
