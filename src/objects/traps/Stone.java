package objects.traps;


import maps.Maps;
import source.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    public PlayPanel playPanel;
    public Maps maps;
    public int xInArray;
    public int yInArray;

    public Stone(){

    }

    /*private Cell[][] whatLevel(int level){
        if (level == 1) return maps.getLevel1();
        else if (level == 2) return maps.getLevel2();
        else if (level == 3) return maps.getLevel3();
        else if (level == 4) return maps.getLevel4();
        else if (level == 5) return maps.getLevel5();
        else return new Cell[0][0];
    }*/

    public void initVars(PlayPanel playPanel, Maps maps, int xInArray, int yInArray){
        this.playPanel = playPanel;
        this.maps = maps;
        this.xInArray = xInArray;
        this.yInArray = yInArray;
        this.x = xInArray * 70;
        this.y = yInArray * 70;
    }

    public void moveStone(){
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (whatMove == 1) stagger();
                else if (whatMove == 2) beShovenLeft();
                else if (whatMove == 3) beShovenRight();
                else if (whatMove == 4) fallLeft();
                else if (whatMove == 5) fallRight();
                else if (whatMove == 6) fallDown();
                playPanel.repaint();
                if (i == 7){
                    i = 0;
                    if (whatMove == 1) whatMove = 5;
                    else if (whatMove == 5) whatMove = 6;
                    else{
                        isMoving = false;
                        timer.stop();
                    }
                    /*if (whatMove == 1){
                          if (left isClear()) whatMove = 4;
                    *     else if (right isClear()) whatMove = 5;
                    * }
                    * else if (((whatMove == 4)||(whatMove == 5)||(whatMove == 6))&&(down isClear()){
                    *     whatMove = 6;
                    * }
                    * else isMoving = false;
                    * */
                }
            }
        });
        timer.start();
    }

    //хитатися
    public void stagger(){
        if (i != 0){
            if (i % 2 == 0) x += cellSide / 14;
            else x -= cellSide / 14;
        }
        i++;
    }

    public void beShovenLeft(){
        if (i == 3){
            playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
            playPanel.getCurrentLevel().getMatrix()[xInArray - 1][yInArray].setTrapObject(Stone.this);
            xInArray -= 1;
        }
        x -= cellSide / 7;
        i++;
    }

    public void beShovenRight(){
        if (i == 3){
            playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
            playPanel.getCurrentLevel().getMatrix()[xInArray + 1][yInArray].setTrapObject(Stone.this);
            xInArray ++;
        }
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
            if (i == 2){
                playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
                playPanel.getCurrentLevel().getMatrix()[xInArray - 1][yInArray].setTrapObject(Stone.this);
                xInArray -= 1;
            }
        }
        else if (i == 3){
            x -= cellSide / 7;
            y += cellSide / 7;
        }
        else if ((i == 4)||(i == 5)){
            x -= 0.5 * cellSide / 7;
            y += 1.5 * cellSide / 7;
            if (i == 5){
                playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
                playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray + 1].setTrapObject(Stone.this);
                yInArray++;
            }
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
            if (i == 2){
                System.out.println(playPanel.getCurrentLevel().getMatrix());
                System.out.println(xInArray);
                System.out.println(yInArray);
                playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
                playPanel.getCurrentLevel().getMatrix()[xInArray + 1][yInArray].setTrapObject(Stone.this);
                xInArray ++;
            }
        }
        else if (i == 3){
            x += cellSide / 7;
            y += cellSide / 7;
        }
        else if ((i == 4)||(i == 5)){
            x += 0.5 * cellSide / 7;
            y += 1.5 * cellSide / 7;
            if (i == 5){
                playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
                playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray + 1].setTrapObject(Stone.this);
                yInArray++;
            }
        }
        else if (i == 6){
            y += 2 * cellSide / 7;
        }
        i++;
    }

    public void fallDown(){
        if (i == 3){
            playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(null);
            playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray + 1].setTrapObject(Stone.this);
            yInArray ++;
        }
        y += cellSide / 7;
        i++;
    }

    @Override
    public void paintObject(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height,null);
    }

    @Override
    public void changeState() {

    }

    @Override
    public JLabel getLabel() {
        return null;
    }
}
