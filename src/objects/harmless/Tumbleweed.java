package objects.harmless;

import source.PlayPanel;

import javax.swing.*;
import java.awt.*;

public class Tumbleweed implements Harmless {

    private PlayPanel playPanel;
    private int xInArray;
    private int yInArray;

    private Image image = new ImageIcon("mapImages/tumbleweed1.png").getImage();

    public void initVars(PlayPanel playPanel, int xInArray, int yInArray){
        this.playPanel = playPanel;
        this.xInArray = xInArray;
        this.yInArray = yInArray;
    }

    @Override
    public void disappear() {

    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    @Override
    public void paintObject(Graphics2D g2) { }

    @Override
    public boolean pass() {
        return true;
    }
}
