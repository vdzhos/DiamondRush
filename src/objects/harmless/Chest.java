package objects.harmless;

import objects.blocks.doors.Resettable;
import objects.thingsInChest.*;
import source.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Chest implements Harmless, Resettable {

    private Image chestClosed = new ImageIcon("mapImages/chestClosed.png").getImage();
    private Image chestAlmostOpened = new ImageIcon("mapImages/chestAlmostOpened.png").getImage();
    private Image chestOpened = new ImageIcon("mapImages/chestOpened.png").getImage();
    private Image[] open = {chestClosed, chestAlmostOpened, chestAlmostOpened,
            chestOpened, chestOpened, chestOpened, chestOpened};
    private Image currentPicture = chestClosed;
    private PlayPanel playPanel;
    private int i;
    public ThingInChest[] things = new ThingInChest[5];
    public ThingInChest currentThing = null;
    private int index = 0;
    public boolean isClosed = true;
    public boolean thingsAreBeeingTaken = false;

    public Chest(PurpleDiamond purpleDiamond, RedDiamond redDiamond,
                 SilverKey silverKey, GoldKey goldKey, Energy energy){
        things[0] = purpleDiamond;
        things[1] = redDiamond;
        things[2] = silverKey;
        things[3] = goldKey;
        things[4] = energy;
    }

    public void initVars(PlayPanel playPanel){
        this.playPanel = playPanel;
    }

    public void openChest(){
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isClosed) open();
                else if (thingsAreBeeingTaken) takeThings();
                playPanel.repaint();
                if (i == 7){
                    i = 0;
                    playPanel.boy.whatMove = 9;
                    playPanel.boy.isMoving = true;
                    playPanel.moveBoy();
                    if (thingsAreBeeingTaken == false) thingsAreBeeingTaken = true;
                    if (index >= 5){
                        thingsAreBeeingTaken = false;
                        timer.stop();
                    }
                }
            }
        });
        timer.start();
    }

    private void open(){
        currentPicture = open[i];
        i++;
        if (i == 7) isClosed = false;
    }

    private void takeThings(){
        if ((index < 5)&&(things[index] != null)) currentThing = things[index];
        else if ((index < 5)&&(things[index] == null)){
            index++;
            if ((index < 5)&&(things[index] != null)) currentThing = things[index];
        }
        i++;
        if (i == 7) index++;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(currentPicture,x,y,70,70,null);
    }


    @Override
    public void paintObject(Graphics2D g2) {
    }

    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public void reset() {

    }

    @Override
    public void disappear() {

    }
}
