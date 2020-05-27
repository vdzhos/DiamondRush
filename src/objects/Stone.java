package objects;

import objects.traps.Rock;
import source.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Stone {

    public int x;
    public int y;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    protected static final int CELL_SIDE = 70;
    public int whatMove;
    public Image image;
    public int i;
    public boolean isMoving;
    public PlayPanel playPanel;
    public int xInArray;
    public int yInArray;
    public int mapX;
    public int mapY;


    public void initVars(PlayPanel playPanel, int xInArray, int yInArray, int mapX, int mapY){
        this.playPanel = playPanel;
        this.xInArray = xInArray;
        this.yInArray = yInArray;
        this.mapX = mapX;
        this.mapY = mapY;
        this.x = xInArray * 70 + mapX;
        this.y = yInArray * 70 + mapY;
    }

    public void moveStone(){
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (whatMove == 1) stagger();
                else if (whatMove == 2) fallLeft();
                else if (whatMove == 3) fallRight();
                else if (whatMove == 4) fallDown();
                else if (whatMove == 5) beShovenLeft();
                else if (whatMove == 6) beShovenRight();
                playPanel.repaint();
                if (i == 7){
                    i = 0;
                    if (whatMove != 1 && playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 4;
                    }
                    else if (whatMove != 1 && playPanel.itIsStone(xInArray, yInArray + 1)){
                        if ((playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                playPanel.itIsClearForStone(xInArray + 1, yInArray + 1))
                                || (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                playPanel.itIsClearForStone(xInArray + 1, yInArray + 1))){
                            whatMove = 1;
                        }
                    }
                    else if (whatMove == 1){
                        if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                playPanel.itIsClearForStone(xInArray + 1, yInArray + 1)){
                            whatMove = 3;
                        }
                        else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                                playPanel.itIsClearForStone(xInArray - 1, yInArray - 1)){
                            whatMove = 2;
                        }
                    }
                    else{
                        isMoving = false;
                        whatMove = 0;
                        timer.stop();
                    }
                }
            }
        });
        timer.start();
    }

    //хитатися
    public void stagger(){
        if (i != 0){
            if (i % 2 == 0) x += CELL_SIDE / 14;
            else x -= CELL_SIDE / 14;
        }
        i++;
    }

    public void fallLeft(){
        if (i == 0){
            x -= 2 * CELL_SIDE / 7;
        }
        else if ((i == 1)||(i == 2)){
            x -= 1.5 * CELL_SIDE / 7;
            y += 0.5 * CELL_SIDE / 7;
            if (i == 2){
                setStoneToNewPositionInArray(xInArray - 1, yInArray);
                xInArray -= 1;
            }
        }
        else if (i == 3){
            x -= CELL_SIDE / 7;
            y += CELL_SIDE / 7;
        }
        else if ((i == 4)||(i == 5)){
            x -= 0.5 * CELL_SIDE / 7;
            y += 1.5 * CELL_SIDE / 7;
            if (i == 5){
                setStoneToNewPositionInArray(xInArray, yInArray + 1);
                playPanel.disappearFromCell(xInArray + 1, yInArray);
                yInArray++;
            }
        }
        else if (i == 6){
            y += 2 * CELL_SIDE / 7;
        }
        i++;
        if (i == 7) playPanel.disappearFromCell(xInArray, yInArray - 1);
    }

    public void fallRight(){
        if (i == 0){
            x += 2 * CELL_SIDE / 7;
        }
        else if ((i == 1)||(i == 2)){
            x += 1.5 * CELL_SIDE / 7;
            y += 0.5 * CELL_SIDE / 7;
            if (i == 2){
                setStoneToNewPositionInArray(xInArray + 1, yInArray);
                xInArray ++;
            }
        }
        else if (i == 3){
            x += CELL_SIDE / 7;
            y += CELL_SIDE / 7;
        }
        else if ((i == 4)||(i == 5)){
            x += 0.5 * CELL_SIDE / 7;
            y += 1.5 * CELL_SIDE / 7;
            if (i == 5){
                setStoneToNewPositionInArray(xInArray, yInArray + 1);
                playPanel.disappearFromCell(xInArray - 1, yInArray);
                yInArray++;
            }
        }
        else if (i == 6){
            y += 2 * CELL_SIDE / 7;
        }
        i++;
        if (i == 7) playPanel.disappearFromCell(xInArray, yInArray - 1);
    }

    public void fallDown(){
        if (i == 3){
            //Then change
            if (yInArray + 2 < playPanel.getCurrentLevel().getMatrix().length){
                setStoneToNewPositionInArray(xInArray, yInArray + 1);
                yInArray ++;
            }
        }
        y += CELL_SIDE / 7;
        i++;
        if (i == 7) playPanel.disappearFromCell(xInArray, yInArray - 1);
    }

    public abstract void beShovenLeft();
    public abstract void beShovenRight();

    protected void updateXAndY(int newMapX, int newMapY){
        x = x - mapX + newMapX;
        y = y - mapY + newMapY;
        mapX = newMapX;
        mapY = newMapY;
    }

    public void checkSpace(){
        if (playPanel.itIsClearForStone(xInArray, yInArray + 1)){
            this.whatMove = 4;
            this.isMoving = true;
            this.moveStone();
        }
        else if (whatMove != 1 && playPanel.itIsStone(xInArray, yInArray + 1)) {
            if ((playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                    playPanel.itIsClearForStone(xInArray + 1, yInArray + 1))
                    || (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                    playPanel.itIsClearForStone(xInArray + 1, yInArray + 1))) {
                this.whatMove = 1;
                this.isMoving = true;
                this.moveStone();
            }
        }
    }

    public abstract void interactWithBoy();
    protected abstract void setStoneToNewPositionInArray(int xInArray, int yInArray);

}
