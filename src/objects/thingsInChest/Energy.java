package objects.thingsInChest;

import source.PlayPanel;
import source.Util;

import javax.swing.*;
import java.awt.*;

public class Energy extends ThingInChest{

    public int quantity;

    public Energy(int quantity){
        image = new ImageIcon("statusBar/energy.png").getImage();
        this.quantity = quantity;
    }

    @Override
    public void disappear(PlayPanel playPanel) {
        playPanel.currentEnergyLevel += quantity;
        if (playPanel.currentEnergyLevel > playPanel.currentLevel.getMaxEnergyLevel()){
            playPanel.currentEnergyLevel = playPanel.currentLevel.getMaxEnergyLevel();
        }
        playPanel.updateEnergyLevelOnStatusBar();
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
        g2.setFont(font);
        g2.setPaint(Color.WHITE);
        int shift = quantity<10?28:16;
        g2.drawString(""+quantity,x+shift,y+62);
    }

}
