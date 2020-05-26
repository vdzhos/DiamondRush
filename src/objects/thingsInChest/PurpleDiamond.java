package objects.thingsInChest;

import javax.swing.*;

public class PurpleDiamond extends ThingInChest{

    public int quantity;

    public PurpleDiamond(int quantity){
        image = new ImageIcon("statusBar/diamondHexPurple.png").getImage();
        this.quantity = quantity;
    }

    @Override
    void disappear() {

    }
}

