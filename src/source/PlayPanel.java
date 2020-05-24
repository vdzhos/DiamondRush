package source;

import maps.Cell;
import maps.Level;
import maps.Maps;
import objects.traps.Rock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements KeyListener {

    private Boy boy;
    private JPanel panel;
    private Maps maps;
    private boolean stonesAreInited = false;

    private int mapX;
    private int mapY;

    private int positionOnScreenX;
    private int positionOnScreenY;

    private int positionOnMapX;
    private int positionOnMapY;


    // map parameters
    int mapWidth;
    int mapHeight;

    int panelWidth = 700;
    int panelHeight = 700;


    private boolean mapMovesToRight;
    private boolean boyMovesToRight;

    private boolean mapMovesToLeft;
    private boolean boyMovesToLeft;

    private boolean mapMovesUp;
    private boolean boyMovesUp;

    private boolean mapMovesDown;
    private boolean boyMovesDown;


    private Level currentLevel;
    private Cell[][] levelMatrix;



    public PlayPanel(Boy boy, int currentLevel) {
        panel = this;
        panel.setLayout(null);
        setPreferredSize(new Dimension(2800, 1540));
        this.boy = boy;
        maps = new Maps();
        initLevel(currentLevel);
        calculateInitialValuesOfMap();
    }

    private void initLevel(int currentLevel) {
        switch (currentLevel){
            case 1:
                this.currentLevel = maps.getLevel1();
                break;
            case 2:
                this.currentLevel = maps.getLevel2();
                break;
            case 3:
                this.currentLevel = maps.getLevel3();
                break;
            case 4:
                this.currentLevel = maps.getLevel4();
                break;
            case 5:
                this.currentLevel = maps.getLevel5();
                break;
        }
        levelMatrix = this.currentLevel.getMatrix();
    }

    private void calculateInitialValuesOfMap() {

        mapWidth = currentLevel.getMatrix().length* Values.CELL_SIZE;
        mapHeight = currentLevel.getMatrix()[0].length* Values.CELL_SIZE;

        positionOnScreenX = currentLevel.getPositionOnScreenX();
        positionOnScreenY = currentLevel.getPositionOnScreenY();

        positionOnMapX = currentLevel.getPositionOnMapX();
        positionOnMapY = currentLevel.getPositionOnMapY();
        mapX = 0;
        mapY = 0 - ((positionOnMapY  - positionOnScreenY)* Values.CELL_SIZE);

        boy.x = positionOnScreenX* Values.CELL_SIZE;
        boy.y = positionOnScreenY* Values.CELL_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                levelMatrix[i][j].getBlock().paintObject(g2,mapX+ i*70,mapY+j*70);
            }
        }
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
               if (levelMatrix[i][j].getTrapObject() != null) {
                   if (levelMatrix[i][j].getTrapObject() instanceof Rock){
                       if (!stonesAreInited) ((Rock)levelMatrix[i][j].getTrapObject()).initVars(this, maps, i, j);
                       levelMatrix[i][j].getTrapObject().paintObject(g2, mapX , mapY);
                   }
                   else{
                       JLabel label = levelMatrix[i][j].getTrapObject().getLabel();
                       if (label.getParent() != panel) {
                           Dimension size = label.getPreferredSize();
                           int n = mapX + i * 70;
                           int m = mapY + j * 70;
                           System.out.println(n + "   " + m);
                           label.setBounds(mapX + i * 70, mapY + j * 70, size.width, size.height);
                           System.out.println(size.width + "   " + size.height);
                           add(label);
                       } else {
                           Dimension size = label.getPreferredSize();
                           label.setBounds(mapX + i * 70, mapY + j * 70, size.width, size.height);
                           //revalidate();
                       }
                   }

                }
            }
        }
        g2.drawImage(boy.currentPicture, boy.x, boy.y, boy.width, boy.height, null);
        //System.out.println(boy.isMoving);
    }


    private void setMovementRight() {
        if ((mapX != 0 && boy.x < 350) || (mapX == 0 || mapX == -(mapWidth - panelWidth))) {
            boyMovesToRight = true;
            mapMovesToRight = false;
        }
        if (boy.x == 350 && mapX != -(mapWidth - panelWidth)) {
            boyMovesToRight = false;
            mapMovesToRight = true;
        }
    }

    private void setMovementLeft() {
        if (mapX == 0 || (boy.x > 280)) {
            mapMovesToLeft = false;
            boyMovesToLeft = true;
        }
        if (boy.x == 280 && mapX != 0) {
            mapMovesToLeft = true;
            boyMovesToLeft = false;
        }
    }

    private void setMovementUp() {
        if ((mapY == 0) || (mapY != 0 && boy.y > 140)) {
            mapMovesUp = false;
            boyMovesUp = true;
        }
        if (mapY != 0 && boy.y == 140) {
            mapMovesUp = true;
            boyMovesUp = false;
        }
    }

    private void setMovementDown() {
        if ((mapY == -(mapHeight - panelHeight)) || (mapY != -(mapHeight - panelHeight) && boy.y < 350)) {
            mapMovesDown = false;
            boyMovesDown = true;
        }
        if (mapY != -(mapHeight - panelHeight) && boy.y == 350) {
            mapMovesDown = true;
            boyMovesDown = false;
        }
    }


    private void moveMapToRight(){
        mapX -= Values.CELL_SIZE/7;
    }

    private void moveMapToLeft(){
        mapX += Values.CELL_SIZE/7;
    }

    private void moveMapUp(){
        mapY += Values.CELL_SIZE/7;
    }

    private void moveMapDown(){
        mapY -= Values.CELL_SIZE/7;
    }



    private void moveBoy(){
        Timer t = new Timer(100, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boy.whatMove == 1 && boyMovesUp) boy.moveUp();
                else if (boy.whatMove == 1 && mapMovesUp) {
                    boy.moveUpAnimation();
                    moveMapUp();
                }
                else if (boy.whatMove == 2 && boyMovesDown) boy.moveDown();
                else if (boy.whatMove == 2 && mapMovesDown) {
                    boy.moveDownAnimation();
                    moveMapDown();
                }
                else if (boy.whatMove == 3 && boyMovesToLeft) boy.moveLeft();
                else if (boy.whatMove == 3 && mapMovesToLeft) {
                    boy.moveLeftAnimation();
                    moveMapToLeft();
                }
                else if (boy.whatMove == 4 && boyMovesToRight) boy.moveRight();
                else if (boy.whatMove == 4 && mapMovesToRight) {
                    boy.moveRightAnimation();
                    moveMapToRight();
                }
                else if (boy.whatMove == 5) boy.shoveLeftAndMove();
                else if (boy.whatMove == 6) boy.shoveLeftAndStand();
                else if (boy.whatMove == 7) boy.shoveRightAndMove();
                else if (boy.whatMove == 8) boy.shoveRightAndStand();
                else if (boy.whatMove == 9) boy.findInChest();
                else if (boy.whatMove == 10) boy.holdARock();
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


    private boolean isAllowedUp(){
        return boy.y != 0;
    }

    private boolean isAllowedDown(){
        return boy.y != panelHeight- boy.height;
    }

    private boolean isAllowedRight(){
        return boy.x != panelWidth- boy.width;
    }

    private boolean isAllowedLeft(){
        return boy.x != 0;
    }




//    private void moveBoy(){
//        Timer t = new Timer(100, null);
//        t.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (boy.whatMove == 1) boy.moveUp();
//                else if (boy.whatMove == 2) boy.moveDown();
//                else if (boy.whatMove == 3) boy.moveLeft();
//                else if (boy.whatMove == 4) boy.moveRight();
//                else if (boy.whatMove == 5) boy.shoveLeftAndMove();
//                else if (boy.whatMove == 6) boy.shoveLeftAndStand();
//                else if (boy.whatMove == 7) boy.shoveRightAndMove();
//                else if (boy.whatMove == 8) boy.shoveRightAndStand();
//                else if (boy.whatMove == 9) boy.findInChest();
//                else if (boy.whatMove == 10) boy.holdAStone();
//                else if (boy.whatMove == 11) boy.attackUp();
//                else if (boy.whatMove == 12) boy.attackDown();
//                else if (boy.whatMove == 13) boy.attackLeft();
//                else if (boy.whatMove == 14) boy.attackRight();
//                else if (boy.whatMove == 15) boy.openWithGoldKeyLeft();
//                else if (boy.whatMove == 16) boy.openWithGoldKeyRight();
//                else if (boy.whatMove == 17) boy.openWithSilverKeyLeft();
//                else if (boy.whatMove == 18) boy.openWithSilverKeyRight();
//                repaint();
//                if (boy.i == 7){
//                    boy.i = 0;
//                    boy.isMoving = false;
//                    t.stop();
//                }
//            }
//        });
//        t.start();
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_UP) && (boy.isMoving == false) && isAllowedUp()) {
            setMovementUp();
            boy.whatMove = 1;
            boy.isMoving = true;
            moveBoy();
        }
        if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (boy.isMoving == false) && isAllowedDown()) {
            setMovementDown();
            boy.whatMove = 2;
            boy.isMoving = true;
            moveBoy();
        }
        if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (boy.isMoving == false) && isAllowedLeft()) {
            setMovementLeft();
            boy.whatMove = 3;
            //if stone is left boy.whatMove = 5;
            //if wall is left boy.whatMove = 6;
            boy.isMoving = true;
            moveBoy();
        }
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (boy.isMoving == false) && isAllowedRight()) {
            setMovementRight();
            boy.whatMove = 4;
            //if stone is right boy.whatMove = 7;
            //if wall is right boy.whatMove = 8;
            boy.isMoving = true;
            moveBoy();
            Rock rock = (Rock)levelMatrix[4][19].getTrapObject();
            rock.whatMove = 1;
            rock.isMoving = true;
            rock.moveRock();
        }
        if ((e.getKeyCode() == KeyEvent.VK_SPACE) && (boy.isMoving == false)) {
            if (boy.currentPicture == boy.walkUp2) boy.whatMove = 11;
            else if (boy.currentPicture == boy.standClear) boy.whatMove = 12;
            else if ((boy.currentPicture == boy.standLeft)
                    || (boy.currentPicture == boy.walkLeft6)) boy.whatMove = 13;
            else if ((boy.currentPicture == boy.standRight)
                    || (boy.currentPicture == boy.walkRight6)) boy.whatMove = 14;
            boy.isMoving = true;
            moveBoy();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
