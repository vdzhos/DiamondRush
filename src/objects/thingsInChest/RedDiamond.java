package objects.thingsInChest;

import javax.swing.*;

public class RedDiamond extends ThingInChest{

    int quantity;

    public RedDiamond(int quantity){
        image = new ImageIcon("statusBar/diamondHexRed.png").getImage();
        this.quantity = quantity;
    }

    @Override
    void disappear() {

    }
}
