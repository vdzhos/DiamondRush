package objects.harmless;

import javax.swing.*;
import java.awt.*;

public class Tumbleweed implements Harmless {

    private JLabel tumbleweed;
    private ImageIcon[] images = {new ImageIcon(""), null};

    public Tumbleweed(){
        tumbleweed = new JLabel(images[0]);
    }

    public JLabel getTumbleweed() {
        return tumbleweed;
    }

    @Override
    public void changeState() {
        tumbleweed.setIcon(images[1]);
    }

    @Override
    public void paintObject(Graphics2D g2, int mapX, int mapY) {

    }

    @Override
    public void paintObject(Graphics2D g2) {

    }

    @Override
    public boolean passable() {
        return true;
    }
}
