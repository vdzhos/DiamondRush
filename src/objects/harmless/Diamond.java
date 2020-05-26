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

    public void moveDiamond(){
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (whatMove == 1) stagger();
                else if (whatMove == 2) fallLeft();
                else if (whatMove == 3) fallRight();
                else if (whatMove == 4) fallDown();
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

    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        playPanel.getCurrentLevel().getMatrix()[this.xInArray][this.yInArray].setHarmlessObject(this);
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
    public void interactWithBoy() {

    }
}
