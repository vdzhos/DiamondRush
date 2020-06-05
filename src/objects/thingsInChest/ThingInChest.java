package objects.thingsInChest;

import source.PlayPanel;
import source.Util;

import java.awt.*;

/**
 * @author Iryna Matviienko
 */
public abstract class ThingInChest {

    protected Image image;
    protected Font font = Util.getFont("fonts/Funhouse-Ke17.ttf",27f);

    /**
     * Paint object
     * @param g2
     * @param x
     * @param y
     */
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    /**
     * Actions when disappearing
     * @param playPanel
     */
    public abstract void disappear(PlayPanel playPanel);
}
