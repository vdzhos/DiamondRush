package objects.traps;

import maps.Maps;
import objects.Stone;

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

    /*private Cell[][] whatLevel(int level){
        if (level == 1) return maps.getLevel1();
        else if (level == 2) return maps.getLevel2();
        else if (level == 3) return maps.getLevel3();
        else if (level == 4) return maps.getLevel4();
        else if (level == 5) return maps.getLevel5();
        else return new Cell[0][0];
    }*/

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
                    if (whatMove == 1) whatMove = 3;
                    else if (whatMove == 3) whatMove = 4;
                    else{
                        isMoving = false;
                        timer.stop();
                    }
                    /*if (whatMove == 1){
                          if (left isClear()) whatMove = 2;
                    *     else if (right isClear()) whatMove = 3;
                    * }
                    * else if (((whatMove == 2)||(whatMove == 3)||(whatMove == 4))&&(down isClear()){
                    *     whatMove = 4;
                    * }
                    * else isMoving = false;
                    * */
                }
            }
        });
        timer.start();
    }

    public void beShovenLeft(){
        if (i == 3){
            maps.getCurrentLevel()[xInArray][yInArray].setTrapObject(null);
            maps.getCurrentLevel()[xInArray - 1][yInArray].setTrapObject(Rock.this);
            xInArray -= 1;
        }
        x -= CELL_SIDE / 7;
        i++;
    }

    public void beShovenRight(){
        if (i == 3){
            maps.getCurrentLevel()[xInArray][yInArray].setTrapObject(null);
            maps.getCurrentLevel()[xInArray + 1][yInArray].setTrapObject(Rock.this);
            xInArray ++;
        }
        x += CELL_SIDE / 7;
        i++;
    }

    @Override
    public void paintObject(Graphics2D g2) {
        g2.drawImage(image, x, y, WIDTH, HEIGHT,null);
    }

    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        maps.getCurrentLevel()[this.xInArray][this.yInArray].setTrapObject(this);
        maps.getCurrentLevel()[xInArray][yInArray].setTrapObject(this);
    }

    @Override
    public void changeState() {

    }

    @Override
    public JLabel getLabel() {
        return null;
    }

    @Override
    public void interactWithBoy() {

    }
}
