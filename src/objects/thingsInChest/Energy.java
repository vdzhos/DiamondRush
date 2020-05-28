package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;

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
    }
}
