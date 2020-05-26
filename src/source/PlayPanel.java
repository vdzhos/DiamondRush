package source;

import maps.Cell;
import maps.Level;
import maps.Maps;
import objects.blocks.*;
import objects.blocks.doors.*;
import objects.harmless.Chest;
import objects.harmless.Diamond;
import objects.harmless.Harmless;
import objects.harmless.Tumbleweed;
import objects.traps.FireTrap;
import objects.traps.Rock;
import objects.traps.Scorpion;
import objects.traps.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements KeyListener {

    public Boy boy;
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
    private int numberOfKeys = 2;



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
        boy.xInArray = positionOnMapX;
        boy.yInArray = positionOnMapY;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        panel.removeAll();
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                levelMatrix[i][j].getBlock().paintObject(g2, mapX + i * 70, mapY + j * 70);
            }
        }
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                if (levelMatrix[i][j].getBlock() instanceof PressMechanism.PressPanel){
                    ((PressMechanism.PressPanel) levelMatrix[i][j].getBlock()).interact(levelMatrix,boy.xInArray,boy.yInArray);
                }
                else if (levelMatrix[i][j].getBlock() instanceof DoubleDoor.RightDoor){
                    ((DoubleDoor.RightDoor) levelMatrix[i][j].getBlock()).interact(boy.xInArray,boy.yInArray);
                }
                if (levelMatrix[i][j].getTrapObject() != null) {
                    if (levelMatrix[i][j].getTrapObject() instanceof Rock){
                        if (!stonesAreInited) ((Rock)levelMatrix[i][j].getTrapObject()).initVars(this, i, j, mapX, mapY);
                        if (mapIsMoving()) levelMatrix[i][j].getTrapObject().paintObject(g2, mapX, mapY);
                        else levelMatrix[i][j].getTrapObject().paintObject(g2);
                    }else{
                        JLabel label = levelMatrix[i][j].getTrapObject().getLabel();
                        if (label.getParent() != panel) {
                            Dimension size = label.getPreferredSize();
                            label.setBounds(mapX + i * 70, mapY + j * 70, size.width, size.height);
                            add(label);
                        }
                    }
                }else if(levelMatrix[i][j].getHarmlessObject() != null) {
                    if (levelMatrix[i][j].getHarmlessObject() instanceof Diamond) {
                        if (!stonesAreInited)
                            ((Diamond)levelMatrix[i][j].getHarmlessObject()).initVars(this, i, j, mapX, mapY);
                        if (mapIsMoving()) levelMatrix[i][j].getHarmlessObject().paintObject(g2, mapX, mapY);
                        else levelMatrix[i][j].getHarmlessObject().paintObject(g2);
                    }
                    else{
                        levelMatrix[i][j].getHarmlessObject().paintObject(g2,mapX+ i*70,mapY+j*70);
                        if (levelMatrix[i][j].getHarmlessObject() instanceof Chest) {
                            if (((Chest)levelMatrix[i][j].getHarmlessObject()).thingsAreBeeingTaken){
                                if (((Chest)levelMatrix[i][j].getHarmlessObject()).currentThing != null){
                                    ((Chest)levelMatrix[i][j].getHarmlessObject()).currentThing.paintObject(g2, mapX+ i*70, mapY+(j-1)*70);
                                }
                            }
                        }
                    }
                }
            }
        }
        stonesAreInited = true;
        g2.drawImage(boy.currentPicture, boy.x, boy.y, boy.width, boy.height, null);
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



    public void moveBoy(){
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
                else if(boy.whatMove == 19) boy.moveUpAnimation();
                else if(boy.whatMove == 20) boy.moveDownAnimation();
                else if(boy.whatMove == 21) boy.moveLeftAnimation();
                else if(boy.whatMove == 22) boy.moveRightAnimation();
                repaint();
                if (boy.i == 7){
                    boy.i = 0;
                    boy.isMoving = false;
                    System.out.println(boy.xInArray + ", " + boy.yInArray);
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

    private boolean mapIsMoving(){
        return (mapMovesUp || mapMovesDown || mapMovesToLeft || mapMovesToRight);
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
        //You can test a chest
        if (e.getKeyCode() == KeyEvent.VK_1){
            Chest chest = (Chest)levelMatrix[5][16].getHarmlessObject();
            if (chest != null){
                chest.initVars(this);
                boy.whatMove = 9;
                boy.isMoving = true;
                moveBoy();
                chest.openChest();
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_UP) && (boy.isMoving == false) && isAllowedUp()) {
            Block block = levelMatrix[boy.xInArray][boy.yInArray-1].getBlock();
            if ((block.pass()&&!(itIsRock(boy.xInArray, boy.yInArray-1)))||itIsHarmless(boy.xInArray, boy.yInArray-1)){
                setMovementUp();
                boy.whatMove = 1;
                boy.isMoving = true;
            }else if (!block.pass() || itIsRock(boy.xInArray, boy.yInArray-1)){
                boy.whatMove = 19;
                boy.isMoving = true;
                boy.yInArray++;
            }
            moveBoy();
        }
        if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (boy.isMoving == false) && isAllowedDown()) {
            Block block = levelMatrix[boy.xInArray][boy.yInArray+1].getBlock();
           if ((block.pass()&&!(itIsRock(boy.xInArray, boy.yInArray+1)))||itIsHarmless(boy.xInArray, boy.yInArray+1)){
                setMovementDown();
                boy.whatMove = 2;
                boy.isMoving = true;
           }else if (!block.pass() || itIsRock(boy.xInArray, boy.yInArray+1)){
                boy.whatMove = 20;
                boy.isMoving = true;
                boy.yInArray--;
           }
            moveBoy();
        }
        if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (boy.isMoving == false) && isAllowedLeft()) {
            Block block = levelMatrix[boy.xInArray-1][boy.yInArray].getBlock();
            if (block instanceof DoorWithKeyhole && numberOfKeys != 0){
                ((DoorWithKeyhole) block).openTheDoor();
                numberOfKeys --;
                boy.whatMove = 15;
                boy.isMoving = true;
            }
            else if ((block.pass()&&!(itIsRock(boy.xInArray - 1, boy.yInArray)))||itIsHarmless(boy.xInArray - 1, boy.yInArray)){
                setMovementLeft();
                boy.whatMove = 3;
                //if stone is left boy.whatMove = 5;
                //if wall is left boy.whatMove = 6;
                boy.isMoving = true;
            }
            else if (itIsRock(boy.xInArray - 1, boy.yInArray)){
                boy.whatMove = 5;
                boy.isMoving = true;
            }
            else if (!block.pass()){
                boy.whatMove = 21;
                boy.isMoving = true;
                boy.xInArray++;
            }
            moveBoy();
        }
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (boy.isMoving == false) && isAllowedRight()) {
            Block block = levelMatrix[boy.xInArray+1][boy.yInArray].getBlock();
            if (block instanceof DoorWithKeyhole && numberOfKeys != 0){
                ((DoorWithKeyhole) block).openTheDoor();
                numberOfKeys --;
                boy.whatMove = 16;
                boy.isMoving = true;
            }
            else if ((block.pass()&&!(itIsRock(boy.xInArray + 1, boy.yInArray)))||itIsHarmless(boy.xInArray + 1, boy.yInArray)){
                setMovementRight();
                boy.whatMove = 4;
                //if stone is left boy.whatMove = 5;
                //if wall is left boy.whatMove = 6;
                boy.isMoving = true;
            }
            else if (itIsRock(boy.xInArray + 1, boy.yInArray)){
                boy.whatMove = 7;
                boy.isMoving = true;
            }
            else if (!block.pass()){
                boy.whatMove = 22;
                boy.isMoving = true;
                boy.xInArray--;
            }
            moveBoy();
            //You can test a stone
            /*Rock rock = (Rock)levelMatrix[4][19].getTrapObject();
            if (rock != null){
                rock.whatMove = 1;
                rock.isMoving = true;
                rock.moveRock();
            }*/
        }
        if ((e.getKeyCode() == KeyEvent.VK_SPACE) && (boy.isMoving == false)) {
            if (boy.currentPicture == boy.walkUp2){
                boy.whatMove = 11;
                if(levelMatrix[boy.xInArray][boy.yInArray-1].getBlock() instanceof BreakableWall){
                    BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray][boy.yInArray-1].getBlock();
                    bw.breakWall(levelMatrix,panel);
                }
            } else if (boy.currentPicture == boy.standClear){
                boy.whatMove = 12;
                if(levelMatrix[boy.xInArray][boy.yInArray+1].getBlock() instanceof BreakableWall){
                    BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray][boy.yInArray+1].getBlock();
                    bw.breakWall(levelMatrix,panel);
                }
            }else if ((boy.currentPicture == boy.standLeft)
                    || (boy.currentPicture == boy.walkLeft6)){
                boy.whatMove = 13;
                if(levelMatrix[boy.xInArray-1][boy.yInArray].getBlock() instanceof BreakableWall){
                    BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray-1][boy.yInArray].getBlock();
                    bw.breakWall(levelMatrix,panel);
                }
            }else if ((boy.currentPicture == boy.standRight)
                    || (boy.currentPicture == boy.walkRight6)){
                if(levelMatrix[boy.xInArray+1][boy.yInArray].getBlock() instanceof BreakableWall){
                    BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray+1][boy.yInArray].getBlock();
                    bw.breakWall(levelMatrix,panel);
                }
                boy.whatMove = 14;
            }
            boy.isMoving = true;
            moveBoy();
        }
    }

    public boolean itIsHarmless(int x, int y){
        return (levelMatrix[x][y].getHarmlessObject() != null);
    }

    public boolean itIsTrap(int x, int y){
        return (levelMatrix[x][y].getTrapObject() != null);
    }

    public boolean itIsBlockedDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof BlockedDoor;
    }

    public boolean itIsDoorWithKeyhole(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof DoorWithKeyhole;
    }

    public boolean itIsBreakableWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Wall;
    }

    public boolean itIsFloor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Floor;
    }

    public boolean itIsSecretWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof SecretWall;
    }

    public boolean itIsWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Wall;
    }

    public boolean itIsChest(int x, int y){
        if (levelMatrix[x][y].getHarmlessObject() == null) return false;
        return levelMatrix[x][y].getHarmlessObject() instanceof Chest;
    }

    public boolean itIsDiamond(int x, int y){
        if (levelMatrix[x][y].getHarmlessObject() == null) return false;
        return levelMatrix[x][y].getHarmlessObject() instanceof Diamond;
    }

    public boolean itIsTumbleweed(int x, int y){
        if (levelMatrix[x][y].getHarmlessObject() == null) return false;
        return levelMatrix[x][y].getHarmlessObject() instanceof Tumbleweed;
    }

    public boolean itIsFireTrap(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof FireTrap;
    }

    public boolean itIsRock(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Rock;
    }

    public boolean itIsScrpion(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Scorpion;
    }

    public boolean itIsSnake(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Snake;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

}
