package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;

public class GoldKey extends ThingInChest{

    public GoldKey(){
        image = new ImageIcon("statusBar/goldKey.png").getImage();
    }

    @Override
    public void disappear(PlayPanel playPanel) {

    }
}
