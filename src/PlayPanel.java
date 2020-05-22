import maps.Cell;
import maps.Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements KeyListener {

    private Boy boy;

    public PlayPanel(Boy boy) {
        setPreferredSize(new Dimension(2800, 1540));
        this.boy = boy;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Maps maps = new Maps();
        Cell[][] level1 = maps.getLevel1();
        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                level1[i][j].getBlock().paintObject(g2,i*70,j*70);
            }
        }
        g.drawImage(boy.currentPicture, boy.x, boy.y, boy.width, boy.height, null);
    }

    private void moveUpAndDown(){
        Timer t = new Timer(200, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boy.whatMove == 1){
                    boy.moveUp();
                    repaint();
                    if (boy.iU == 4){
                        boy.currentPicture = boy.walkUp2;
                        repaint();
                        boy.iU = 0;
                        t.stop();
                    }
                }
                else if (boy.whatMove == 2){
                    boy.moveDown();
                    repaint();
                    if (boy.iD == 4){
                        boy.iD = 0;
                        t.stop();
                    }
                }
            }
        });
        t.start();
    }

    private void moveRightAndLeft(){
        Timer t = new Timer(100, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boy.whatMove == 3){
                    boy.moveLeft();
                    repaint();
                    if (boy.iL == 8){
                        boy.iL = 0;
                        t.stop();
                    }
                }
                else if (boy.whatMove == 4){
                    boy.moveRight();
                    repaint();
                    if (boy.iR == 8){
                        boy.iR = 0;
                        t.stop();
                    }
                }
            }
        });
        t.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
            boy.whatMove = 1;
            moveUpAndDown();
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            boy.whatMove = 2;
            moveUpAndDown();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            boy.whatMove = 3;
            moveRightAndLeft();
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            boy.whatMove = 4;
            moveRightAndLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
