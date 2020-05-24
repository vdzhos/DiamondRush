package objects.traps;

import javax.swing.*;
import java.awt.*;

public interface Trap {

    void changeState();
    void paintObject(Graphics2D g2, int mapX, int mapY);
    JLabel getLabel();

}
