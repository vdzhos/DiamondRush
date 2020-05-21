package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class BreakableWall implements Block{

    private JLabel breakableWall;
    private ImageIcon[] images = {new ImageIcon(""), new ImageIcon("")};

    public BreakableWall(){
        breakableWall = new JLabel();
        breakableWall.setBackground(Color.green);
        breakableWall.setOpaque(true);
        breakableWall.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public boolean pass() {
        breakableWall.setIcon(images[1]);
        return true;
    }

    @Override
    public JLabel getLabel() {
        return breakableWall;
    }
}
