package objects.traps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stone implements Trap{

    public int x;
    public int y;
    public int width = 70;
    public int height = 70;
    int cellSide = 70;
    public int whatMove = 0;//Stay calm = 0; stagger = 1; beShovenLeft = 2; beShovenRight = 3;
    // fallLeft = 4; fallRight = 5; fallDown = 6;
    public Image image = new ImageIcon("mapImages/stone.png").getImage();
    public int i = 0;
    public boolean isMoving = false;

    public Stone(int x, int y){
        this.x = x;
        this.y = y;
    }

    //хитатися
    public void stagger(){
        if (i % 2 == 0) x += cellSide / 14;
        else x -= cellSide / 14;
        i++;
    }

    public void beShovenLeft(){
        x -= cellSide / 7;
        i++;
    }

    public void beShovenRight(){
        x += cellSide / 7;
        i++;
    }

    public void fallLeft(){
        if (i == 0){
            x -= 2 * cellSide / 7;
        }
        else if ((i == 1)||(i == 2)){
            x -= 1.5 * cellSide / 7;
            y += 0.5 * cellSide / 7;
        }
        else if (i == 3){
            x -= cellSide / 7;
            y += cellSide / 7;
        }
        else if ((i == 4)||(i == 5)){
            x -= 0.5 * cellSide / 7;
            y += 1.5 * cellSide / 7;
        }
        else if (i == 6){
            y += 2 * cellSide / 7;
        }
        i++;
    }

    public void fallRight(){
        if (i == 0){
            x += 2 * cellSide / 7;
        }
        else if ((i == 1)||(i == 2)){
            x += 1.5 * cellSide / 7;
            y += 0.5 * cellSide / 7;
        }
        else if (i == 3){
            x += cellSide / 7;
            y += cellSide / 7;
        }
        else if ((i == 4)||(i == 5)){
            x += 0.5 * cellSide / 7;
            y += 1.5 * cellSide / 7;
        }
        else if (i == 6){
            y += 2 * cellSide / 7;
        }
        i++;
    }

    public void fallDown(){
        y += cellSide / 7;
        i++;
    }

    @Override
    public void changeState() {

    }

    @Override
    public JLabel getLabel() {
        return null;
    }
}
