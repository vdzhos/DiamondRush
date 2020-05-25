package objects.blocks;

import maps.Level;

import javax.swing.*;
import java.awt.*;

public interface Block {
    boolean pass();
    void paintObject(Graphics2D g2, int x, int y);
}
