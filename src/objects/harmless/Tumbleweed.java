package objects.harmless;

import javax.swing.*;

public class Tumbleweed implements Harmless {

    private JLabel tumbleweed;
    private ImageIcon[] images = {new ImageIcon(""), null};

    public Tumbleweed(){
        tumbleweed = new JLabel(images[0]);
    }

    public JLabel getTumbleweed() {
        return tumbleweed;
    }

    @Override
    public void changeState() {
        tumbleweed.setIcon(images[1]);
    }

    @Override
    public boolean passable() {
        return true;
    }
}
