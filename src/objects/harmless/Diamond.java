package objects.harmless;

import objects.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Diamond extends Stone implements Harmless{

    public Diamond(){
        whatMove = 0;//Stay calm = 0; stagger = 1;
        // fallLeft = 2; fallRight = 3; fallDown = 4;
        image = new ImageIcon("statusBar/diamondHexPurple.png").getImage();
        i = 0;
        isMoving = false;
    }

    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        playPanel.getCurrentLevel().getMatrix()[this.xInArray][this.yInArray].setHarmlessObject(null);
        playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setHarmlessObject(this);
    }

    @Override
    public void paintObject(Graphics2D g2, int newMapX, int newMapY) {
        updateXAndY(newMapX, newMapY);
        paintObject(g2);
    }

    @Override
    public void paintObject(Graphics2D g2) {
        g2.drawImage(image, x, y, WIDTH, HEIGHT,null);
    }

    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public void disappear() {
        if (enabled) {
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

    @Override
    public void interactWithBoy() { }
}
