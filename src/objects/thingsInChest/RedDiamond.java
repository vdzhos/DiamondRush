package objects.thingsInChest;

import source.PlayPanel;

import javax.swing.*;

public class RedDiamond extends ThingInChest{

    public int quantity;

    public RedDiamond(int quantity){
        image = new ImageIcon("statusBar/diamondHexRed.png").getImage();
        this.quantity = quantity;
    }

    @Override
    public void disappear(PlayPanel playPanel) {

    }
}
