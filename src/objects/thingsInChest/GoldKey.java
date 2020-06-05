package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;

/**
 * @author Iryna Matviienko
 */
public class GoldKey extends ThingInChest{

    /**
     * Default constructor
     */
    public GoldKey(){
        image = new ImageIcon("statusBar/goldKey.png").getImage();
    }

    /**
     * Increase quantity of collected gold keys
     * @param playPanel
     */
    @Override
    public void disappear(PlayPanel playPanel) {
        playPanel.numberOfGoldKeysCollected ++;
        playPanel.updateNumberOfGoldKeysOnStatusBar();
    }
}
