import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PlayPanel extends JPanel {

    public PlayPanel() {
        //setSize(500, 500);
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
