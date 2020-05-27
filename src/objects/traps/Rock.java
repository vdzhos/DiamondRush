package objects.traps;

import maps.Maps;
import objects.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Rock extends Stone implements Trap{

    public Rock(){
        whatMove = 0;
        image = new ImageIcon("mapImages/rock.png").getImage();
        i = 0;
        isMoving = false;
    }

    @Override
    public void beShovenLeft(){
        if (i == 3){
            setStoneToNewPositionInArray(xInArray - 1, yInArray);
            playPanel.disappearFromCell(xInArray, yInArray);
            xInArray -= 1;
        }
        x -= CELL_SIDE / 7;
        i++;
    }

    @Override
    public void beShovenRight(){
        if (i == 3){
            setStoneToNewPositionInArray(xInArray + 1, yInArray);
            playPanel.disappearFromCell(xInArray, yInArray);
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
    public void interactWithBoy() {

    }
}