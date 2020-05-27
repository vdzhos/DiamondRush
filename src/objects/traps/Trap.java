package objects.traps;

import maps.Cell;
import source.Boy;
import source.PlayPanel;

import javax.swing.*;
import java.awt.*;

public interface Trap {

    void paintObject(Graphics2D g2, int mapX, int mapY);
    void paintObject(Graphics2D g2);
    JLabel getLabel();
    void checkTimerStart(PlayPanel panel, Boy boy, Cell[][] levelMatrix);

}
