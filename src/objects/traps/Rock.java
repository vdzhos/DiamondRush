package objects.traps;

import maps.Cell;
import maps.Maps;
import objects.Stone;
import source.Boy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Rock extends Stone implements Trap{

    public Rock(){
        whatMove = 0;//Stay calm = 0; stagger = 1;
        // fallLeft = 2; fallRight = 3; fallDown = 4;
        //beShovenLeft = 5; beShovenRight = 6;
        image = new ImageIcon("mapImages/rock.png").getImage();
        i = 0;
        isMoving = false;
    }

    public void moveRock(){
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
                    else if (whatMove != 1 && (playPanel.itIsRock(xInArray, yInArray + 1) || playPanel.itIsDiamond(xInArray, yInArray + 1))){
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

    public void beShovenLeft(){
        if (i == 3){
            setStoneToNewPositionInArray(xInArray - 1, yInArray);
            xInArray -= 1;
        }
        x -= CELL_SIDE / 7;
        i++;
    }

    public void beShovenRight(){
        if (i == 3){
            setStoneToNewPositionInArray(xInArray + 1, yInArray);
            xInArray ++;
        }
        x += CELL_SIDE / 7;
        i++;
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
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        playPanel.getCurrentLevel().getMatrix()[this.xInArray][this.yInArray].setTrapObject(null);
        playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(this);
    }


    @Override
    public JLabel getLabel() {
        return null;
    }

    @Override
    public void interactWithBoy() { }

    @Override
    public void checkTimerStart(JPanel panel, Boy boy, Cell[][] levelMatrix) { }
}