package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class Wall implements Block {

    private JLabel wall;
    private ImageIcon image = new ImageIcon("");

    public Wall(){
        wall = new JLabel();
        wall.setBackground(Color.GRAY);
        wall.setOpaque(true);
        wall.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public boolean pass() {
        return false;
    }

    @Override
    public JLabel getLabel() {
        return wall;
    }
}
