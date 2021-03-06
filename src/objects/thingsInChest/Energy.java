package objects.thingsInChest;

import source.PlayPanel;
import source.Util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Iryna Matviienko
 */
public class Energy extends ThingInChest{

    public int quantity;

    /**
     * @param quantity
     */
    public Energy(int quantity){
        image = new ImageIcon("statusBar/energy.png").getImage();
        this.quantity = quantity;
    }

    /**
     * Increase quantity of boy`s energy
     * @param playPanel
     */
    @Override
    public void disappear(PlayPanel playPanel) {
        playPanel.currentEnergyLevel += quantity;
        if (playPanel.currentEnergyLevel > playPanel.currentLevel.getMaxEnergyLevel()){
            playPanel.currentEnergyLevel = playPanel.currentLevel.getMaxEnergyLevel();
        }
        playPanel.updateEnergyLevelOnStatusBar();
    }

    /**
     * Paint energy
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
