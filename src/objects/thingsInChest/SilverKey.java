package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;

public class SilverKey extends ThingInChest{

    public SilverKey(){
        image = new ImageIcon("statusBar/silverKey.png").getImage();
    }

    @Override
    public void disappear(PlayPanel playPanel) {

    }
}

