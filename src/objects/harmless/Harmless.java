package objects.harmless;

import java.awt.*;

/**
 * @author Iryna Matviienko
 */
public interface Harmless {

    /**
     * Paint object
     * @param g2
     * @param mapX
     * @param mapY
     */
    void paintObject(Graphics2D g2, int mapX, int mapY);

    /**
     * Paint object
     * @param g2
     */
    void paintObject(Graphics2D g2);

    /**
     * Whether boy can go through the object
     * @return
     */
    boolean pass();

    /**
     * Actions when disappearing
     */
    void disappear();

}
