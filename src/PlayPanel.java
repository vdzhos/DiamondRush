import maps.Cell;
import maps.Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements KeyListener {

    private Boy boy;
    private JPanel panel;
    private Maps maps;

    public PlayPanel(Boy boy) {
        panel = this;
        panel.setLayout(null);
        setPreferredSize(new Dimension(2800, 1540));
        this.boy = boy;
        maps = new Maps();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Cell[][] level1 = maps.getLevel1();
        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                level1[i][j].getBlock().paintObject(g2,i*70,j*70);
                if(level1[i][j].getTrapObject()!=null){
                    JLabel label = level1[i][j].getTrapObject().getLabel();
                    if(label.getParent()!=panel){
                        Dimension size = label.getPreferredSize();
                        label.setBounds(i*70,j*70,size.width,size.height);
                        add(label);
                    }
                }
            }
        }
        g2.drawImage(boy.currentPicture, boy.x, boy.y, boy.width, boy.height, null);
    }

    private void moveBoy(){
        Timer t = new Timer(100, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boy.whatMove == 1) boy.moveUp();
                else if (boy.whatMove == 2) boy.moveDown();
                else if (boy.whatMove == 3) boy.moveLeft();
                else if (boy.whatMove == 4) boy.moveRight();
                else if (boy.whatMove == 5) boy.shoveLeftAndMove();
                else if (boy.whatMove == 6) boy.shoveLeftAndStand();
                else if (boy.whatMove == 7) boy.shoveRightAndMove();
                else if (boy.whatMove == 8) boy.shoveRightAndStand();
                else if (boy.whatMove == 9) boy.findInChest();
                else if (boy.whatMove == 10) boy.holdAStone();
                else if (boy.whatMove == 11) boy.attackLeft();
                else if (boy.whatMove == 12) boy.attackRight();
                repaint();
                if (boy.i == 7){
                    boy.i = 0;
                    boy.isMoving = false;
                    t.stop();
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
            if (boy.isMoving == false){
                boy.isMoving = true;
                moveBoy();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            boy.whatMove = 2;
            if (boy.isMoving == false){
                boy.isMoving = true;
                moveBoy();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            boy.whatMove = 3;
            //if stone is left boy.whatMove = 5;
            //if wall is left boy.whatMove = 6;
            if (boy.isMoving == false){
                boy.isMoving = true;
                moveBoy();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            boy.whatMove = 4;
            //if stone is right boy.whatMove = 7;
            //if wall is right boy.whatMove = 8;
            if (boy.isMoving == false){
                boy.isMoving = true;
                moveBoy();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
