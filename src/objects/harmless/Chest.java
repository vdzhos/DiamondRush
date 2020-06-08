package objects.harmless;

import objects.blocks.doors.Resettable;
import objects.thingsInChest.*;
import source.PlayPanel;
import source.Util;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Iryna Matviienko
 */
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
    public Clip chestOpeningSound = Util.getSound("sounds/treasure_chest.wav",-15f);

    /**
     * Constructor with 5 thingsInChest
     * @param purpleDiamond
     * @param redDiamond
     * @param silverKey
     * @param goldKey
     * @param energy
     */
    public Chest(PurpleDiamond purpleDiamond, RedDiamond redDiamond,
                 SilverKey silverKey, GoldKey goldKey, Energy energy){
        things[0] = purpleDiamond;
        things[1] = redDiamond;
        things[2] = silverKey;
        things[3] = goldKey;
        things[4] = energy;
    }

    /**
     * Initialize variables
     * @param playPanel
     */
    public void initVars(PlayPanel playPanel){
        this.playPanel = playPanel;
    }

    /**
     * Animation of chest`s opening
     */
    public void openChest(){
        startDiamondCollectedSound();
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isClosed) open();
                else if (thingsAreBeeingTaken) takeThings();
                playPanel.repaint();
                playPanel.boy.isMoving = true;
                if (i == 7){
                    i = 0;
                    if (index < 5 && things[index] != null){
                        playPanel.boy.whatMove = 9;
                        playPanel.boy.isMoving = true;
                        playPanel.moveBoy();
                    }
                    if (thingsAreBeeingTaken == false) thingsAreBeeingTaken = true;
                    if (index == 5){
                        thingsAreBeeingTaken = false;
                        playPanel.boy.currentPicture = playPanel.boy.standClear;
                        playPanel.boy.isMoving = false;
                        timer.stop();
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * One iteration of opening
     */
    private void open(){
        currentPicture = open[i];
        i++;
        if (i == 7) {
            isClosed = false;
        }
    }

    /**
     * One iteration of taking things
     */
    private void takeThings(){
        if ((index < 5)&&(things[index] != null)){
            currentThing = things[index];
            i++;
            if (i == 7){
                things[index].disappear(playPanel);
                index++;
            }
        }
        else if ((index < 5)&&(things[index] == null)){
            i = 7;
            index++;
        }
    }

    /**
     * Paint chest
     * @param g2
     * @param x
     * @param y
     */
    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(currentPicture,x,y,70,70,null);
    }

    /**
     * Paint chest
     * @param g2
     */
    @Override
    public void paintObject(Graphics2D g2) { }

    /**
     * Boy can go through it
     * @return
     */
    @Override
    public boolean pass() {
        return true;
    }

    /**
     * Reset variables
     */
    @Override
    public void reset() {
        currentPicture = chestClosed;
        i = 0;
        currentThing = null;
        index = 0;
        isClosed = true;
        thingsAreBeeingTaken = false;
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void disappear() { }

    private void startDiamondCollectedSound(){
        if(playPanel.getGameFrame().soundOn){
            chestOpeningSound.start();
            Util.wait((int) chestOpeningSound.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chestOpeningSound.stop();
                    chestOpeningSound.setFramePosition(0);
                }
            });
        }
    }

}
