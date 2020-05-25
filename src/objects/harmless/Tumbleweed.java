package objects.harmless;

import javax.swing.*;
import java.awt.*;

public class Tumbleweed implements Harmless {

    private Image image = new ImageIcon("mapImages/tumbleweed.png").getImage();

    public void disappear(){

    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    @Override
    public void paintObject(Graphics2D g2) { }

    @Override
    public boolean passable() {
        return true;
    }
}
