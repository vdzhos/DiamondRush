package objects.harmless;

import objects.Stone;

import javax.swing.*;

public class Diamond extends Stone implements Harmless{

    public Diamond(){
        whatMove = 0;//Stay calm = 0; stagger = 1;
        // fallLeft = 2; fallRight = 3; fallDown = 4;
        image = new ImageIcon("statusBar/diamondHexRed.png").getImage();
        i = 0;
        isMoving = false;
    }

    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        playPanel.getCurrentLevel().getMatrix()[this.xInArray][this.yInArray].setHarmlessObject(this);
        playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setHarmlessObject(this);
    }

    @Override
    public void changeState() {

    }

    @Override
    public boolean passable() {
        return false;
    }

    @Override
    public void interactWithBoy() {

    }
}
