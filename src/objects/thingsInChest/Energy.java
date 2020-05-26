package objects.thingsInChest;

import javax.swing.*;

public class Energy extends ThingInChest{

    public Energy(){
        image = new ImageIcon("statusBar/energy.png").getImage();
    }

    @Override
    void disappear() {

    }
}
