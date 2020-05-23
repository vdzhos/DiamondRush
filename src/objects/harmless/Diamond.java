package objects.harmless;

import objects.Stone;

import javax.swing.*;

public class Diamond extends Stone implements Harmless{

    public Diamond(){
        whatMove = 0;//Stay calm = 0; stagger = 1;
        // fallLeft = 2; fallRight = 3; fallDown = 4;
        image = new ImageIcon("").getImage();
        i = 0;
        isMoving = false;
    }

    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        maps.getCurrentLevel()[this.xInArray][this.yInArray].setHarmlessObject(this);
        maps.getCurrentLevel()[xInArray][yInArray].setHarmlessObject(this);
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
