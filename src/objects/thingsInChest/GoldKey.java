package objects.thingsInChest;

import javax.swing.*;

public class GoldKey extends ThingInChest{

    public GoldKey(){
        image = new ImageIcon("statusBar/goldKey.png").getImage();
    }

    @Override
    void disappear() {

    }
}
