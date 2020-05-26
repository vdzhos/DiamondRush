package objects.harmless;

import objects.blocks.doors.Resetable;
import source.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Chest implements Harmless, Resetable {

    private Image chestClosed = new ImageIcon("mapImages/chestClosed.png").getImage();
    private Image chestAlmostOpened = new ImageIcon("mapImages/chestAlmostOpened.png").getImage();
    private Image chestOpened = new ImageIcon("mapImages/chestOpened.png").getImage();
    private Image[] open = {chestClosed, chestAlmostOpened, chestAlmostOpened,
            chestOpened, chestOpened, chestOpened, chestOpened};
    private Image currentPicture = chestClosed;
    private PlayPanel playPanel;
    private int i;

    public Chest(){

    }

    public void initVars(PlayPanel playPanel){
        this.playPanel = playPanel;
    }

    public void openChest(){
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPicture = open[i];
                i++;
                playPanel.repaint();
                if (i == 7){
                    i = 0;
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(currentPicture,x,y,70,70,null);
    }


    @Override
    public void paintObject(Graphics2D g2) {
    }

    @Override
    public boolean passable() {
        return true;
    }

    @Override
    public void reset() {

    }
}
