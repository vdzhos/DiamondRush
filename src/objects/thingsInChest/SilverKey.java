package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;

/**
 * @author Iryna Matviienko
 */
public class SilverKey extends ThingInChest{

    /**
     * Default constructor
     */
    public SilverKey(){
        image = new ImageIcon("statusBar/silverKey.png").getImage();
    }

    /**
     * Increase quantity of collected silver keys
     * @param playPanel
     */
    @Override
    public void disappear(PlayPanel playPanel) {
        playPanel.numberOfSilverKeysCollected ++;
        playPanel.updateNumberOfSilverKeysOnStatusBar();
    }
}

