package objects.blocks;

import java.awt.*;

public interface Block {
    boolean pass();
    void paintObject(Graphics2D g2, int x, int y);
}
