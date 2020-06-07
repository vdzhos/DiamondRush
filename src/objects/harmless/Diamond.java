package objects.harmless;

import objects.Stone;
import source.Util;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Iryna Matviienko
 */
public class Diamond extends Stone implements Harmless{

    public Clip diamondCollected = Util.getSound("sounds/diamond_collect.wav",-15f);

    /**
     * Default constructor
     */
    public Diamond(){
        super();
        image = new ImageIcon("statusBar/diamondHexPurple.png").getImage();
    }

    /**
     * Set diamond to new position in array
     * @param xInArray
     * @param yInArray
     */
    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        playPanel.getCurrentLevel().getMatrix()[this.xInArray][this.yInArray].setHarmlessObject(null);
        playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setHarmlessObject(this);
    }

    /**
     * Paint diamond
     * @param g2
     * @param newMapX
     * @param newMapY
     */
    @Override
    public void paintObject(Graphics2D g2, int newMapX, int newMapY) {
        updateXAndY(newMapX, newMapY);
        paintObject(g2);
    }

    /**
     * Paint diamond
     * @param g2
     */
    @Override
    public void paintObject(Graphics2D g2) {
        g2.drawImage(image, x, y, WIDTH, HEIGHT,null);
    }

    /**
     * Boy can go through it
     * @return
     */
    @Override
    public boolean pass() {
        return true;
    }

    /**
     * Stop diamond in all senses when collecting it
     */
    @Override
    public void disappear() {
        if (enabled) {
            startDiamondCollectedSound();
            whatMove = 0;
            isMoving = false;
            i = 0;
            timer.stop();
            playPanel.levelMatrix[xInArray][yInArray].setHarmlessObject(null);
            playPanel.numberOfPurpleDiamondsCollected++;
            playPanel.updateNumberOfPurpleDiamondsOnStatusBar();
            playPanel.disappearFromCell(xInArray, yInArray);
        }
    }

    @Override
    public void beShovenLeft() { }

    @Override
    public void beShovenRight() { }

    /**
     * Like reset in Stone
     */
    public void pause() {
        reset();
    }

    private void startDiamondCollectedSound(){
        if(playPanel.getGameFrame().soundOn){
            diamondCollected.start();
            Util.wait((int) diamondCollected.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    diamondCollected.stop();
                    diamondCollected.setFramePosition(0);
                }
            });
        }
    }
}
