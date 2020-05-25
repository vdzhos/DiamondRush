package objects.harmless;

import java.awt.*;

public interface Harmless {

    //void changeState();
    void paintObject(Graphics2D g2, int mapX, int mapY);
    void paintObject(Graphics2D g2);
    boolean passable();

}
