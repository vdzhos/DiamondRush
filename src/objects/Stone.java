package objects;

import objects.blocks.doors.Resettable;
import source.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Stone implements Resettable {

    public int x;
    public int y;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    protected static final int CELL_SIDE = 70;
    public int whatMove;//Stay calm = 0; stagger = 1;
    // fallLeft = 2; fallRight = 3; fallDown = 4;
    //beShovenLeft = 5; beShovenRight = 6;
    public Image image;
    public int i;
    public boolean isMoving;
    public PlayPanel playPanel;
    public int xInArray;
    public int yInArray;
    public int mapX;
    public int mapY;
    public Timer timer;


    public void initVars(PlayPanel playPanel, int xInArray, int yInArray, int mapX, int mapY){
        this.playPanel = playPanel;
        this.xInArray = xInArray;
        this.yInArray = yInArray;
        this.mapX = mapX;
        this.mapY = mapY;
        this.x = xInArray * 70 + mapX;
        this.y = yInArray * 70 + mapY;
        timer = new Timer(100, null);
    }

    public void moveStone(){
        //timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i == 0){
                    //if (whatMove != 0) System.out.println("0StartStone " + xInArray + ", " + yInArray + " whatMove " + whatMove);
                    if ((whatMove == 4 || whatMove == 1) && !playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 0;
                        if (playPanel.itIsStone(xInArray, yInArray + 1)){
                            if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                    (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1)
                                            || (playPanel.boy.xInArray == xInArray + 1 && playPanel.boy.yInArray == yInArray + 1))){
                                whatMove = 1;
                            }
                            else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                                    (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1)
                                            ||(playPanel.boy.xInArray == xInArray - 1 && playPanel.boy.yInArray == yInArray + 1))){
                                whatMove = 1;
                            }
                        }
                        if (whatMove == 0) i = 7;
                    }
                    else if (whatMove == 2 || whatMove == 3){
                        //System.out.println("(whatMove == 2 || whatMove == 3)" + whatMove);
                        Stone stoneUnder = playPanel.getStone(xInArray, yInArray + 1);
                        if (stoneUnder != null && stoneUnder.isMoving && (stoneUnder.whatMove == 2 || stoneUnder.whatMove == 3)){
                            whatMove = 4;
                        }
                        else if (whatMove == 2){
                            Stone stoneLeftLeft = playPanel.getStone(xInArray - 2, yInArray);
                            if (stoneLeftLeft != null && stoneLeftLeft.isMoving && stoneLeftLeft.whatMove == 3){
                                whatMove = 1;
                            }
                            else if (playPanel.boy.xInArray == xInArray && playPanel.boy.yInArray == yInArray + 1){// && playPanel.boy.whatMove == 4){
                                //System.out.println("yes 1");
                                whatMove = 0;
                            }
                            //System.out.println("1Stone " + (xInArray) + ", " + (yInArray + 1));
                            //System.out.println("1Boy " + playPanel.boy.xInArray + ", " + playPanel.boy.yInArray);
                        }
                        else if (whatMove == 3 && playPanel.boy.xInArray == xInArray && playPanel.boy.yInArray == yInArray + 1){// && playPanel.boy.whatMove == 3){
                            //System.out.println("yes 2");
                            whatMove = 0;
                        }
                        //System.out.println("2Stone " + (xInArray) + ", " + (yInArray + 1));
                        //System.out.println("2Boy " + playPanel.boy.xInArray + ", " + playPanel.boy.yInArray);
                    }
                    else if (playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 4;
                    }
                    else if (whatMove != 1 && whatMove != 2 && whatMove != 3
                            && playPanel.itIsStone(xInArray, yInArray + 1)){
                        if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1)
                                        || (playPanel.boy.xInArray == xInArray + 1 && playPanel.boy.yInArray == yInArray + 1))){
                            whatMove = 1;
                        }
                        else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1)
                                        ||(playPanel.boy.xInArray == xInArray - 1 && playPanel.boy.yInArray == yInArray + 1))){
                            whatMove = 1;
                        }
                    }
                    //if (whatMove != 0) System.out.println("0FinishStone " + xInArray + ", " + yInArray + " whatMove " + whatMove);
                }
                if (whatMove != 0) isMoving = true;
                if (whatMove == 1) stagger();
                else if (whatMove == 2) fallLeft();
                else if (whatMove == 3) fallRight();
                else if (whatMove == 4) fallDown();
                else if (whatMove == 5){
                    //if (playPanel.boy.i != 0) i = playPanel.boy.i;
                    beShovenLeft();
                }
                else if (whatMove == 6){
                    //if (playPanel.boy.i != 0) i = playPanel.boy.i;
                    beShovenRight();
                }
                playPanel.repaint();
                if (i == 7){
                    //System.out.println("7StartStone " + xInArray + ", " + yInArray + " whatMove " + whatMove);
                    i = 0;
                    //There was && whatMove != 1
                    if (whatMove != 0 && playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 4;
                    }
                    else if (whatMove == 2 || whatMove == 3) whatMove = 1;
                    else if (whatMove != 1 && playPanel.itIsStone(xInArray, yInArray + 1)){
                        if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1)
                                        || (playPanel.boy.xInArray == xInArray + 1 && playPanel.boy.yInArray == yInArray + 1))){
                            whatMove = 1;
                        }
                        else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1)
                                        ||(playPanel.boy.xInArray == xInArray - 1 && playPanel.boy.yInArray == yInArray + 1))){
                            whatMove = 1;
                        }
                        else if (whatMove == 5 || whatMove == 6){
                            isMoving = false;
                            whatMove = 0;
                            timer.stop();
                        }
                    }
                    else if (whatMove == 1){
                        Stone stoneUnder = playPanel.getStone(xInArray, yInArray + 1);
                        if (stoneUnder != null && stoneUnder.isMoving && (stoneUnder.whatMove == 2 || stoneUnder.whatMove == 3)){
                            whatMove = 4;
                        }
                        else if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1))){
                            whatMove = 3;
                        }
                        else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                                    (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1))){
                            whatMove = 2;
                        }
                        else whatMove = 0;
                    }
                    else if (whatMove == 0){
                        isMoving = false;
                        timer.stop();
                    }
                    else{
                        isMoving = false;
                        whatMove = 0;
                        timer.stop();
                    }
                    //System.out.println("7FinishStone " + xInArray + ", " + yInArray + " whatMove " + whatMove);
                }
            }
        });
        timer.start();
    }

    public void checkSpace(){
        if (!isMoving){
            if (playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                this.whatMove = 4;
                this.isMoving = true;
                this.moveStone();
            }
            else if (whatMove != 1 && playPanel.itIsStone(xInArray, yInArray + 1)) {
                if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                        (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1)
                                || (playPanel.boy.xInArray == xInArray + 1 && playPanel.boy.yInArray == yInArray + 1))){
                    this.whatMove = 1;
                    this.isMoving = true;
                    this.moveStone();
                }
                else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                        (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1)
                                ||(playPanel.boy.xInArray == xInArray - 1 && playPanel.boy.yInArray == yInArray + 1))){
                    this.whatMove = 1;
                    this.isMoving = true;
                    this.moveStone();
                }
            }
        }
    }

    //хитатися
    public void stagger(){
        if (i != 0){
            if (playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                whatMove = 4;
                i = -1;
            }
            else if (i % 2 == 0) x += CELL_SIDE / 14;
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
                playPanel.disappearFromCell(xInArray, yInArray);
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
                playPanel.disappearFromCell(xInArray, yInArray);
                yInArray++;
            }
        }
        else if (i == 6){
            y += 2 * CELL_SIDE / 7;
        }
        i++;
        //For example 4. Might be not 2
        //whatMove = 4;
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
                playPanel.disappearFromCell(xInArray, yInArray);
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
                playPanel.disappearFromCell(xInArray, yInArray);
                yInArray++;

            }
        }
        else if (i == 6){
            y += 2 * CELL_SIDE / 7;
        }
        i++;
        //For example 4. Might be not 3
        //whatMove = 4;
    }

    public void fallDown(){
        //if (i == 0){
            //System.out.println("Fall down " + xInArray + ", " + yInArray);
            //System.out.println("It is stone down: " + playPanel.itIsStone(xInArray, yInArray + 1));
        //}
        if (i == 3){
            //Then change
            //if (yInArray + 1 < playPanel.getCurrentLevel().getMatrix()[0].length){
                setStoneToNewPositionInArray(xInArray, yInArray + 1);
                playPanel.disappearFromCell(xInArray, yInArray);
                yInArray ++;

           //}
        }
        y += CELL_SIDE / 7;
        i++;
    }

    public abstract void beShovenLeft();
    public abstract void beShovenRight();

    protected void updateXAndY(int newMapX, int newMapY){
        x = x - mapX + newMapX;
        y = y - mapY + newMapY;
        mapX = newMapX;
        mapY = newMapY;
    }

    @Override
    public void reset() {
        timer.stop();
        whatMove = 0;
        i = 0;
        isMoving = false;
    }

    public abstract void interactWithBoy();
    protected abstract void setStoneToNewPositionInArray(int xInArray, int yInArray);

}
