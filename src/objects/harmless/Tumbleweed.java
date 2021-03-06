package objects.harmless;

import objects.Stone;
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
public class Tumbleweed implements Harmless {

    private PlayPanel playPanel;
    private int xInArray;
    private int yInArray;

    private Image image = new ImageIcon("mapImages/tumbleweed1.png").getImage();
    public Clip tumbleweedBreak = Util.getSound("sounds/tumbleweed_break.wav",-10f);

    /**
     * Initialize variables
     * @param playPanel
     * @param xInArray
     * @param yInArray
     */
    public void initVars(PlayPanel playPanel, int xInArray, int yInArray){
        this.playPanel = playPanel;
        this.xInArray = xInArray;
        this.yInArray = yInArray;
    }

    /**
     * Tumbleweed disappears
     */
    @Override
    public void disappear() {
        startTumbleweedBreakSound();
        playPanel.levelMatrix[xInArray][yInArray].setHarmlessObject(null);
        playPanel.disappearFromCell(xInArray, yInArray);
    }

    /**
     * Paint tumbleweed
     * @param g2
     * @param x
     * @param y
     */
    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    /**
     * Paint tumbleweed
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

    private void startTumbleweedBreakSound(){
        if(playPanel.getGameFrame().soundOn){
            tumbleweedBreak.setFramePosition((int)tumbleweedBreak.getMicrosecondLength() / 4000);
            tumbleweedBreak.start();
            Util.wait((int) tumbleweedBreak.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tumbleweedBreak.stop();
                    tumbleweedBreak.setFramePosition(0);
                }
            });
        }
    }
}
