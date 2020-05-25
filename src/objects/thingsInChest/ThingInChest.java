package objects.thingsInChest;

import java.awt.*;

public abstract class ThingInChest {

    Image image;

    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    abstract void disappear();
}
