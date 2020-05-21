import maps.Cell;
import maps.Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PlayPanel extends JPanel {

    public PlayPanel() {
        setPreferredSize(new Dimension(2800, 1540));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Maps maps = new Maps();
        Cell[][] level1 = maps.getLevel1();
        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                level1[i][j].getBlock().paintObject(g2,i*70,j*70);
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(600, 600);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PlayPanel playPanel = new PlayPanel();
        Timer t = new Timer(20, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playPanel.repaint();
            }
        });
        Boy boy = new Boy(playPanel, t, 200, 200);
        f.addKeyListener(boy);
        boy.start();
        playPanel.add(boy);
        f.getContentPane().add(playPanel);
        f.setVisible(true);
        t.start();
        ///
    }
}
