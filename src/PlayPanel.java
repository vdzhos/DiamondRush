import maps.Cell;
import maps.Maps;
import objects.traps.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements KeyListener {

    private Boy boy;
    private JPanel panel;
    private Maps maps;
    private Stone stone = new Stone(0, 0);

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
        g2.drawImage(stone.image,stone.x,stone.y,stone.width,stone.height,null);
        //System.out.println(boy.isMoving);
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
                else if (boy.whatMove == 11) boy.attackUp();
                else if (boy.whatMove == 12) boy.attackDown();
                else if (boy.whatMove == 13) boy.attackLeft();
                else if (boy.whatMove == 14) boy.attackRight();
                else if (boy.whatMove == 15) boy.openWithGoldKeyLeft();
                else if (boy.whatMove == 16) boy.openWithGoldKeyRight();
                else if (boy.whatMove == 17) boy.openWithSilverKeyLeft();
                else if (boy.whatMove == 18) boy.openWithSilverKeyRight();
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

    public void moveStone(){
        Timer timer = new Timer(100, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stone.whatMove == 1) stone.stagger();
                else if (stone.whatMove == 2) stone.beShovenLeft();
                else if (stone.whatMove == 3) stone.beShovenRight();
                else if (stone.whatMove == 4) stone.fallLeft();
                else if (stone.whatMove == 5) stone.fallRight();
                else if (stone.whatMove == 6) stone.fallDown();
                repaint();
                if (stone.i == 7){
                    stone.i = 0;
                    stone.isMoving = false;
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode()==KeyEvent.VK_1)&&(stone.isMoving == false)){
            stone.whatMove = 1;
            stone.isMoving = true;
            moveStone();
        }
        if((e.getKeyCode()==KeyEvent.VK_2)&&(stone.isMoving == false)){
            stone.whatMove = 2;
            stone.isMoving = true;
            moveStone();
        }
        if((e.getKeyCode()==KeyEvent.VK_3)&&(stone.isMoving == false)){
            stone.whatMove = 3;
            stone.isMoving = true;
            moveStone();
        }
        if((e.getKeyCode()==KeyEvent.VK_4)&&(stone.isMoving == false)){
            stone.whatMove = 4;
            stone.isMoving = true;
            moveStone();
        }
        if((e.getKeyCode()==KeyEvent.VK_5)&&(stone.isMoving == false)){
            stone.whatMove = 5;
            stone.isMoving = true;
            moveStone();
        }
        if((e.getKeyCode()==KeyEvent.VK_6)&&(stone.isMoving == false)){
            stone.whatMove = 6;
            stone.isMoving = true;
            moveStone();
        }
        if((e.getKeyCode()==KeyEvent.VK_UP)&&(boy.isMoving == false)){
            boy.whatMove = 1;
            boy.isMoving = true;
            moveBoy();
        }
        if((e.getKeyCode()==KeyEvent.VK_DOWN)&&(boy.isMoving == false)){
            boy.whatMove = 2;
            boy.isMoving = true;
            moveBoy();
        }
        if((e.getKeyCode()==KeyEvent.VK_LEFT)&&(boy.isMoving == false)){
            boy.whatMove = 3;
            //if stone is left boy.whatMove = 5;
            //if wall is left boy.whatMove = 6;
            boy.isMoving = true;
            moveBoy();
        }
        if((e.getKeyCode()==KeyEvent.VK_RIGHT)&&(boy.isMoving == false)){
            boy.whatMove = 4;
            //if stone is right boy.whatMove = 7;
            //if wall is right boy.whatMove = 8;
            boy.isMoving = true;
            moveBoy();
        }
        if((e.getKeyCode()==KeyEvent.VK_SPACE)&&(boy.isMoving == false)){
            if (boy.currentPicture == boy.walkUp2) boy.whatMove = 11;
            else if (boy.currentPicture == boy.standClear) boy.whatMove = 12;
            else if ((boy.currentPicture == boy.standLeft)
                    ||(boy.currentPicture == boy.walkLeft6)) boy.whatMove = 13;
            else if ((boy.currentPicture == boy.standRight)
                    ||(boy.currentPicture == boy.walkRight6)) boy.whatMove = 14;
            boy.isMoving = true;
            moveBoy();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
