package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Iryna Matviienko
 */
public class RedDiamond extends ThingInChest{

    public int quantity;

    /**
     * @param quantity
     */
    public RedDiamond(int quantity){
        image = new ImageIcon("statusBar/diamondHexRed.png").getImage();
        this.quantity = quantity;
    }

    /**
     * Increase quantity of collected red diamonds
     * @param playPanel
     */
    @Override
    public void disappear(PlayPanel playPanel) {
        playPanel.numberOfRedDiamondsCollected += quantity;
        playPanel.updateNumberOfRedDiamondsOnStatusBar();
    }

    /**
     * Paint red diamond
     * @param g2
     * @param x
     * @param y
     */
    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
        g2.setFont(font);
        g2.setPaint(Color.WHITE);
        int shift = quantity<10?28:16;
        g2.drawString(""+quantity,x+shift,y+62);
    }
}
