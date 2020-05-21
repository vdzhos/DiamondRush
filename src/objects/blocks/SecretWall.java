package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class SecretWall implements Block{

    private JLabel secretWall;
    private ImageIcon image = new ImageIcon("");

    public SecretWall(){
        secretWall = new JLabel();
        secretWall.setBackground(Color.MAGENTA);
        secretWall.setOpaque(true);
        secretWall.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public JLabel getLabel() {
        return secretWall;
    }
}
