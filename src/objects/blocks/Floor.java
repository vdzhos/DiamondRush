package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class Floor implements Block{

    private JLabel floor;
    private ImageIcon image = new ImageIcon("");

    public Floor(){
        floor = new JLabel();
        floor.setBackground(Color.ORANGE);
        floor.setOpaque(true);
        floor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public boolean pass() {
            return true;
    }

    @Override
    public JLabel getLabel() {
        return floor;
    }
}
