package source;

import maps.Cell;
import maps.Level;
import maps.Maps;
import objects.Stone;
import objects.blocks.Checkpoint;
import objects.blocks.*;
import objects.blocks.doors.*;
import objects.harmless.Chest;
import objects.harmless.Diamond;
import objects.harmless.Tumbleweed;
import objects.traps.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayPanel extends JPanel implements KeyListener {

    public Boy boy;
    private PlayPanel panel;
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

    int panelWidth = Values.GAME_PANEL_WIDTH;
    int panelHeight = Values.GAME_PANEL_WIDTH;


    private boolean mapMovesToRight;
    private boolean boyMovesToRight;

    private boolean mapMovesToLeft;
    private boolean boyMovesToLeft;

    private boolean mapMovesUp;
    private boolean boyMovesUp;

    private boolean mapMovesDown;
    private boolean boyMovesDown;

    public int revivals;


    public Level currentLevel;
    //levelMatrix was private
    public Cell[][] levelMatrix;

    private Checkpoint currentCheckpoint;
    private boolean updated = true;

    public boolean drawMessage;
    public boolean twoLineMessage;
    public String message;
    public String messageLower;

    public int numberOfRedDiamondsCollected;
//    these commented fields are in the currentLevel object
//    private int maxNumberOfRedDiamondsCollected;

    public int numberOfPurpleDiamondsCollected;
//    private int maxNumberOfPurpleDiamondsCollected;

    public int numberOfGoldKeysCollected;
//    private int maxNumberOfGoldKeysCollected;

    public int numberOfSilverKeysCollected;
//    private int maxNumberOfSilverKeysCollected;

    public int currentEnergyLevel;

    private boolean artefactIsCollected;

    private boolean boyCanMove = true;
    private boolean energyIsBeeingTaken  = false;
    private StatusBarPanel statusBarPanel;
    private MapPanel mapPanel;
    private GameFrame gameFrame;
    private int currentLevelInt;
    private Font font = Util.getFont("fonts/Funhouse-Ke17.ttf", 16f);
    private Image image = new ImageIcon("mapImages/thoughtClouds.png").getImage();

    public PlayPanel(int currentLevel) {
        panel = this;
        panel.setLayout(null);
        setPreferredSize(new Dimension(2800, 1540));
        this.boy = new Boy(0,0);
        maps = new Maps();
        initLevel();
        calculateInitialValuesOfMap();
        setCoordinates();
    }

    public PlayPanel(int currentLevel, GameFrame gameFrame, MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        currentLevelInt = currentLevel;
        this.gameFrame = gameFrame;
        panel = this;
        panel.setLayout(null);
        setPreferredSize(new Dimension(2800, 1540));
        this.boy = new Boy(0,0);
        maps = new Maps();
        maps.initLevel(currentLevel);
        initLevel();
        statusBarPanel = new StatusBarPanel(gameFrame, this);
        initStatusBar();
        calculateInitialValuesOfMap();
        setCoordinates();
    }

    private void initStatusBar() {
        statusBarPanel.setCurrentLevel(currentLevelInt);
        statusBarPanel.setMaxNumberOfGoldKeys(currentLevel.getMaxNumberOfGoldKeys());
        statusBarPanel.setMaxEnergyLevel(currentLevel.getMaxEnergyLevel());
        statusBarPanel.setMaxNumberOfSilverKeys(currentLevel.getMaxNumberOfSilverKeys());
        statusBarPanel.setMaxNumberOfPurpleDiamonds(currentLevel.getMaxNumberOfPurpleDiamonds());
        statusBarPanel.setMaxNumberOfRedDiamonds(currentLevel.getMaxNumberOfRedDiamonds());

        numberOfSilverKeysCollected = 0;
        numberOfGoldKeysCollected = 0;
        numberOfRedDiamondsCollected = 0;
        numberOfPurpleDiamondsCollected = 0;
        currentEnergyLevel = currentLevel.getMaxEnergyLevel();
    }


    private void initLevel() {
        currentLevel = maps.getLevel(currentLevelInt);
        levelMatrix = this.currentLevel.getMatrix();
    }

    private void calculateInitialValuesOfMap() {
        mapWidth = currentLevel.getMatrix().length* Values.CELL_SIZE;
        mapHeight = currentLevel.getMatrix()[0].length* Values.CELL_SIZE;

        positionOnScreenX = currentLevel.getPositionOnScreenX();
        positionOnScreenY = currentLevel.getPositionOnScreenY();

        positionOnMapX = currentLevel.getPositionOnMapX();
        positionOnMapY = currentLevel.getPositionOnMapY();
    }

    private void setCoordinates() {
        mapX = 0 - (Math.abs(positionOnMapX  - positionOnScreenX)* Values.CELL_SIZE);
        mapY = 0 - (Math.abs(positionOnMapY  - positionOnScreenY)* Values.CELL_SIZE);
        boy.x = positionOnScreenX* Values.CELL_SIZE;
        boy.y = positionOnScreenY* Values.CELL_SIZE;
        boy.xInArray = positionOnMapX;
        boy.yInArray = positionOnMapY;
        System.out.println(mapX +"   "+mapY);
        System.out.println(boy.x+"   "+boy.y);
        System.out.println(boy.xInArray+"   "+boy.yInArray);
    }

    public void restart() {
        currentLevel = null;
        currentCheckpoint = null;
        stonesAreInited = false;

        drawMessage = false;
        twoLineMessage = false;
        drawn = false;
//        boy = null;
//        boy = new Boy(0,0);

//        mapX = 0;
//        mapY = 0;

        boyCanMove = true;
//
//        positionOnScreenX = 0;
//        positionOnScreenY = 0;
//
//        positionOnMapX = 0;
//        positionOnMapY = 0;

//        mapWidth = 0;
//        mapHeight = 0;
//
//        panelWidth = 700;
//        panelHeight = 700;

        mapMovesToRight = false;
        boyMovesToRight = false;

        mapMovesToLeft = false;
        boyMovesToLeft = false;

        mapMovesUp = false;
        boyMovesUp = false;

        mapMovesDown = false;
        boyMovesDown = false;

//        levelMatrix = null;

//        updated = true;

        numberOfRedDiamondsCollected = 0;

        numberOfPurpleDiamondsCollected = 0;

        numberOfGoldKeysCollected = 0;

        numberOfSilverKeysCollected = 0;

        revivals = 0;

        artefactIsCollected = artefactIsCollected?true:false;
        statusBarIsInitiated = false;

        maps.initLevel(currentLevelInt);
        initLevel();
        initStatusBar();
        calculateInitialValuesOfMap();
        setCoordinates();
        repaint();
    }


    public void updateStatusBar(){
        updateNumberOfPurpleDiamondsOnStatusBar();
        updateEnergyLevelOnStatusBar();
        updateNumberOfRedDiamondsOnStatusBar();
        updateNumberOfSilverKeysOnStatusBar();
        updateNumberOfGoldKeysOnStatusBar();
    }


    public void updateNumberOfRedDiamondsOnStatusBar(){
        gameFrame.updateNumberOfRedDiamondsOnStatusBar(numberOfRedDiamondsCollected);
    }

    public void updateNumberOfPurpleDiamondsOnStatusBar(){
       gameFrame.updateNumberOfPurpleDiamondsOnStatusBar(numberOfPurpleDiamondsCollected);
    }

    public void updateNumberOfGoldKeysOnStatusBar(){
        gameFrame.updateNumberOfGoldKeysOnStatusBar(numberOfGoldKeysCollected);
    }

    public void updateNumberOfSilverKeysOnStatusBar(){
        gameFrame.updateNumberOfSilverKeysOnStatusBar(numberOfSilverKeysCollected);
    }

    public void updateEnergyLevelOnStatusBar(){
        gameFrame.updateEnergyLevelOnStatusBar(currentEnergyLevel);
    }

    private boolean statusBarIsInitiated;

    @Override
    public void paintComponent(Graphics g) {
        if (!statusBarIsInitiated){
            updateStatusBar();
            statusBarIsInitiated = true;
        }
        super.paintComponent(g);
        panel.removeAll();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        ArrayList<SecretWall> secretWalls = new ArrayList<>();
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                if(levelMatrix[i][j].getBlock() instanceof SecretWall){
                    SecretWall secretWall = (SecretWall) levelMatrix[i][j].getBlock();
                    secretWall.setCoordinates(mapX + i * 70,mapY + j * 70);
                    secretWalls.add(secretWall);
                    g2.drawImage(new ImageIcon("mapImages/floor.png").getImage(),
                            mapX + i * 70, mapY + j * 70,70,70,null);
                }else{
                    levelMatrix[i][j].getBlock().paintObject(g2, mapX + i * 70, mapY + j * 70);
                }
            }
        }
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                if (levelMatrix[i][j].getBlock() instanceof PressMechanism.PressPanel){
                    ((PressMechanism.PressPanel) levelMatrix[i][j].getBlock()).interact(levelMatrix,boy.xInArray,boy.yInArray);
                }
                else if (levelMatrix[i][j].getBlock() instanceof DoubleDoor.RightDoor){
                    ((DoubleDoor.RightDoor) levelMatrix[i][j].getBlock()).interact(this,boy);
                }
                else if (levelMatrix[i][j].getBlock() instanceof DiamondDoor){
                    ((DiamondDoor) levelMatrix[i][j].getBlock()).setNumberOfDiamonds(numberOfPurpleDiamondsCollected);
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
                        if (itIsTumbleweed(i, j) && stonesAreInited){
                            ((Tumbleweed)levelMatrix[i][j].getHarmlessObject()).initVars(this, i, j);
                        }
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
        for (SecretWall secretWall:secretWalls) {
            secretWall.paintObject(g2,secretWall.getX(),secretWall.getY());
        }
        if (!updated){
            mapMovesDown = false;
            updated = true;
            boy.isMoving = false;
        }
        if (drawMessage && !boy.isMoving) {
            drawn = true;
            g2.setFont(font);
            g2.drawImage(image,boy.x - 60, boy.y - 70, 185, 95,null);
            g2.setColor(new Color(255, 157, 0));
            if (twoLineMessage){
                g2.drawString(message, boy.x - 50, boy.y - 40);
                g2.drawString(messageLower, boy.x - 50, boy.y - 25);
            }
            else g2.drawString(message, boy.x - 50, boy.y - 30);
        }
        if (drawMessage&&boy.isMoving&&drawn){
            drawn = false;
            drawMessage = false;
            twoLineMessage = false;
        }




//        JLabel label = levelMatrix[9][16].getTrapObject().getLabel();
//        System.out.println(boy.x + " " + boy.y + " | " + label.getX() + " " + label.getY());
    }

    private boolean drawn;

    public void applyCheckpoint(){
        if (currentCheckpoint != null) {
            revivals ++;
            boy.isMoving = true;
            boy.currentPicture = boy.standClear;
            drawMessage = false;
            drawn = false;
            twoLineMessage = false;
            mapMovesDown = true;
            stonesAreInited = false;
            levelMatrix = currentCheckpoint.getRestoredMatrix(levelMatrix);
            updated = false;
            positionOnMapX = currentCheckpoint.positionInArrayX;
            positionOnMapY = currentCheckpoint.positionInArrayY;
            positionOnScreenX = currentCheckpoint.minPositionOnScreenX;
            positionOnScreenY = currentCheckpoint.minPositionOnScreenY;

            numberOfGoldKeysCollected -= currentCheckpoint.numberOfGoldKeysOnTheAreaCollected;
            numberOfSilverKeysCollected -= currentCheckpoint.numberOfSilverKeysOnTheAreaCollected;
            numberOfPurpleDiamondsCollected -= currentCheckpoint.numberOfPurpleDiamondsOnTheAreaCollected;
            numberOfRedDiamondsCollected -= currentCheckpoint.numberOfRedDiamondsOnTheAreaCollected;
            currentEnergyLevel -= 100;
            currentEnergyLevel = currentEnergyLevel<0?0:currentEnergyLevel;
            setCoordinates();
            repaint();
            updateStatusBar();
//            System.out.println("___________________________");
//            System.out.println(numberOfGoldKeysCollected);
//            System.out.println(numberOfSilverKeysCollected);
//            System.out.println(numberOfPurpleDiamondsCollected);
//            System.out.println(numberOfRedDiamondsCollected);
//            System.out.println("___________________________");

        }
    }

    private void setMovementRight() {
        if ((mapX != 0 && boy.x < 350) || (mapX == 0 || mapX == -(mapWidth - panelWidth))) {
            boyMovesToRight = true;
            mapMovesToRight = false;
        }
        if (boy.x >= 350 && mapX != -(mapWidth - panelWidth)) {
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
                if (boy.whatMove == 1 && boyMovesUp) boy.moveUp(PlayPanel.this);
                else if (boy.whatMove == 1 && mapMovesUp) {
                    boy.moveUpAnimation(PlayPanel.this,true);
                    moveMapUp();
                }
                else if (boy.whatMove == 2 && boyMovesDown) boy.moveDown(PlayPanel.this);
                else if (boy.whatMove == 2 && mapMovesDown) {
                    boy.moveDownAnimation(PlayPanel.this,true);
                    moveMapDown();
                }
                else if (boy.whatMove == 3 && boyMovesToLeft) boy.moveLeft(PlayPanel.this);
                else if (boy.whatMove == 3 && mapMovesToLeft) {
                    boy.moveLeftAnimation(PlayPanel.this,true);
                    moveMapToLeft();
                }
                else if (boy.whatMove == 4 && boyMovesToRight) boy.moveRight(PlayPanel.this);
                else if (boy.whatMove == 4 && mapMovesToRight) {
                    boy.moveRightAnimation(PlayPanel.this, true);
                    moveMapToRight();
                }
                else if (boy.whatMove == 5 && boyMovesToLeft) boy.shoveLeftAndMove(PlayPanel.this);
                else if (boy.whatMove == 5 && mapMovesToLeft) {
                    boy.shoveLeftAndStand(PlayPanel.this, true);
                    moveMapToLeft();
                }
                else if (boy.whatMove == 7 && boyMovesToRight) boy.shoveRightAndMove(PlayPanel.this);
                else if (boy.whatMove == 7 && mapMovesToRight) {
                    boy.shoveRightAndStand(PlayPanel.this, true);
                    moveMapToRight();
                }
                else if (boy.whatMove == 6) boy.shoveLeftAndStand(PlayPanel.this, false);
                else if (boy.whatMove == 8) boy.shoveRightAndStand(PlayPanel.this, false);
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
                else if(boy.whatMove == 19) boy.moveUpAnimation(PlayPanel.this, false);
                else if(boy.whatMove == 20) boy.moveDownAnimation(PlayPanel.this, false);
                else if(boy.whatMove == 21) boy.moveLeftAnimation(PlayPanel.this, false);
                else if(boy.whatMove == 22) boy.moveRightAnimation(PlayPanel.this, false);
                else if (boy.whatMove == 23) boy.openChest();
                if (boy.i == 4) checkHarmless(boy.xInArray, boy.yInArray);
                repaint();
                if (boy.i == 7){
                    boy.i = 0;
                    if (itIsChest(boy.xInArray, boy.yInArray) && ((Chest) levelMatrix[boy.xInArray][boy.yInArray].getHarmlessObject()).thingsAreBeeingTaken){
                        boy.isMoving = true;
                    }
                    else boy.isMoving = false;
                    System.out.println(boy.xInArray + ", " + boy.yInArray);
                    //Recently added
//                    boy.whatMove = 0;
                    t.stop();
                    if (!energyIsBeeingTaken) takeEnergy();
                    Checkpoint temp = currentCheckpoint;
                    currentCheckpoint = currentLevel.getCheckpoint(boy.xInArray,boy.yInArray);
                    if (currentCheckpoint == null)
                        currentCheckpoint = temp;
                    else if (!currentCheckpoint.isUsed) {
                        currentCheckpoint.setUsed(true);
                        drawMessage = true;
                        message = "New checkpoint!";
                        repaint();
                    }
                }
            }
        });
        t.start();
    }


    public void takeEnergy(){

        Timer t = new Timer(700, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itIsRock(boy.xInArray, boy.yInArray - 1)){
                    energyIsBeeingTaken = true;
                    if (!boy.isMoving) boy.currentPicture = boy.imHoldARock;
                    System.out.println("take energy");
                    currentEnergyLevel -= 10;
                    if (currentEnergyLevel <= 0){
                        currentEnergyLevel = 0;
                        //It is death
                        System.out.println("Death!");
                    }
                    updateEnergyLevelOnStatusBar();
                }
                else{
                    energyIsBeeingTaken = false;
                    t.stop();
                }
            }
        });
        t.start();
    }

    public void pause(){
        for (byte i = 0; i < levelMatrix.length; i ++){
            for (byte j = 0; j < levelMatrix[0].length; j ++){
                if (levelMatrix[i][j].getTrapObject()!=null){
                    levelMatrix[i][j].getTrapObject().pause();
                }
            }
        }
    }

    public void resume(){
        for (byte i = 0; i < levelMatrix.length; i ++){
            for (byte j = 0; j < levelMatrix[0].length; j ++){
                if (levelMatrix[i][j].getTrapObject()!=null){
                    levelMatrix[i][j].getTrapObject().resume();
                }
            }
        }
    }



//        levelMatrix = currentCheckpoint.getInitialMatrix();
//

//
//        calculateInitialValuesOfMap();
//        setCoordinates();



    private boolean isAllowedUp(){
        return boy.y != 0 && boyCanMove;
    }

    private boolean isAllowedDown(){
        return boy.y != panelHeight- boy.height && boyCanMove;
    }

    private boolean isAllowedRight(){
//        return boy.x != panelWidth- boy.width;
        return boy.xInArray != levelMatrix.length-1 && boyCanMove;
    }

    private boolean isAllowedLeft(){
        return boy.x != 0 && boyCanMove;
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

    private void endLevel(){
        if (numberOfPurpleDiamondsCollected == currentLevel.getMaxNumberOfPurpleDiamonds()
                && numberOfRedDiamondsCollected == currentLevel.getMaxNumberOfRedDiamonds()
                && revivals == 0)
            artefactIsCollected = true;
        pause();
        gameFrame.updatePuzzlePanel(currentLevelInt,artefactIsCollected);
        mapPanel.openNextLevel(currentLevelInt);
        LevelEndingDialog levelEndingDialog = new LevelEndingDialog(gameFrame, panel);
        ProgressStorage.updateContent(currentLevelInt, true, artefactIsCollected);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (updated && !boy.isMoving) {
            int code = e.getKeyCode();
            if ((code == KeyEvent.VK_1) && (boy.isMoving == false)){
                if (itIsChest(boy.xInArray, boy.yInArray)){
                    Chest chest = (Chest) levelMatrix[boy.xInArray][boy.yInArray].getHarmlessObject();
                    if (chest.isClosed){
                        chest.initVars(this);
                        boy.whatMove = 23;
                        boy.isMoving = true;
                        moveBoy();
                        chest.openChest();
                    }
                }
            } else if (code == KeyEvent.VK_2) {
                applyCheckpoint();
            } else if ((code == KeyEvent.VK_UP) && (boy.isMoving == false) && isAllowedUp()) {
                Block block = levelMatrix[boy.xInArray][boy.yInArray - 1].getBlock();
                if (block.pass()) {
                    if (!itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray, boy.yInArray - 1) && !itIsRock(boy.xInArray, boy.yInArray - 1)) {
                        levelMatrix[boy.xInArray][boy.yInArray - 1].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && !itIsTrap(boy.xInArray, boy.yInArray - 1)) {
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray, boy.yInArray - 1) &&
                            (!levelMatrix[boy.xInArray][boy.yInArray].getTrapObject().equals(levelMatrix[boy.xInArray][boy.yInArray - 1].getTrapObject()))) {
                        levelMatrix[boy.xInArray][boy.yInArray - 1].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    }
                }
                if ((block.pass() && !(itIsRock(boy.xInArray, boy.yInArray - 1))) || itIsHarmless(boy.xInArray, boy.yInArray - 1)) {
                    setMovementUp();
                    boy.whatMove = 1;
                    boy.isMoving = true;
                    if(itIsDiamondDoor(boy.xInArray, boy.yInArray - 1)){
                        DiamondDoor diamondDoor = (DiamondDoor) levelMatrix[boy.xInArray][ boy.yInArray - 1].getBlock();
                        if(diamondDoor.isExit()){
                            boyCanMove=false;
                            Util.wait(1000, new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    endLevel();
                                }
                            });
                        }
                    }
                } else if (itIsRock(boy.xInArray, boy.yInArray - 1)) {
                    boy.whatMove = 10;
                    //takeEnergy();
                    boy.isMoving = true;
                } else if (!block.pass()) {
                    boy.whatMove = 19;
                    boy.isMoving = true;
//                    boy.yInArray++;
                }
                moveBoy();
            } else if ((code == KeyEvent.VK_DOWN) && (boy.isMoving == false) && isAllowedDown()) {
                Block block = levelMatrix[boy.xInArray][boy.yInArray + 1].getBlock();
                if (block.pass()) {
                    if (!itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray, boy.yInArray + 1) && !itIsRock(boy.xInArray, boy.yInArray + 1)) {
                        levelMatrix[boy.xInArray][boy.yInArray + 1].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && !itIsTrap(boy.xInArray, boy.yInArray + 1)) {
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray, boy.yInArray + 1) &&
                            (!levelMatrix[boy.xInArray][boy.yInArray].getTrapObject().equals(levelMatrix[boy.xInArray][boy.yInArray + 1].getTrapObject()))) {
                        levelMatrix[boy.xInArray][boy.yInArray + 1].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    }
                }
                if ((block.pass() && !(itIsRock(boy.xInArray, boy.yInArray + 1))) || itIsHarmless(boy.xInArray, boy.yInArray + 1)) {
                    setMovementDown();
                    boy.whatMove = 2;
                    boy.isMoving = true;
                    if(itIsDiamondDoor(boy.xInArray, boy.yInArray + 1)){
                        DiamondDoor diamondDoor = (DiamondDoor) levelMatrix[boy.xInArray][ boy.yInArray + 1].getBlock();
                        if(diamondDoor.isExit()){
                            boyCanMove=false;
                            Util.wait(1000, new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    endLevel();
                                }
                            });
                        }
                    }
                } else if (!block.pass() || itIsRock(boy.xInArray, boy.yInArray + 1)) {
                    boy.whatMove = 20;
                    boy.isMoving = true;
//                    boy.yInArray--;
                }
                moveBoy();
            } else if ((code == KeyEvent.VK_LEFT) && (boy.isMoving == false) && isAllowedLeft()) {
                Block block = levelMatrix[boy.xInArray - 1][boy.yInArray].getBlock();
                if (block.pass()) {
                    if (!itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray - 1, boy.yInArray) && !itIsRock(boy.xInArray - 1, boy.yInArray)) {
                        levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && !itIsTrap(boy.xInArray - 1, boy.yInArray)) {
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray - 1, boy.yInArray) &&
                            (!levelMatrix[boy.xInArray][boy.yInArray].getTrapObject().equals(levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject()))) {
                        levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    }
                }
                if (block instanceof DoorWithKeyhole.GoldDoor && !block.pass()) {
                    if (numberOfGoldKeysCollected != 0) {
                        ((DoorWithKeyhole.GoldDoor) block).openTheDoor();
                        numberOfGoldKeysCollected--;
                        updateNumberOfGoldKeysOnStatusBar();
                        boy.whatMove = 15;
                        boy.isMoving = true;
                    } else if (numberOfGoldKeysCollected == 0){
                        drawMessage = true;
                        twoLineMessage = true;
                        message = "    I need to find";
                        messageLower = "    a gold key!";
                        boy.whatMove = 21;
                        boy.isMoving = true;
                    }
                } else if (block instanceof DoorWithKeyhole.SilverDoor && !block.pass()) {
                    if (numberOfSilverKeysCollected != 0) {
                        ((DoorWithKeyhole.SilverDoor) block).openTheDoor();
                        numberOfSilverKeysCollected--;
                        updateNumberOfSilverKeysOnStatusBar();
                        boy.whatMove = 17;
                        boy.isMoving = true;
                    }
                    else if (numberOfSilverKeysCollected == 0){
                        drawMessage = true;
                        twoLineMessage = true;
                        message = "    I need to find";
                        messageLower = "    a silver key!";
                        boy.whatMove = 21;
                        boy.isMoving = true;
                    }
                }
                else if ((block.pass() && !(itIsRock(boy.xInArray - 1, boy.yInArray))) || itIsHarmless(boy.xInArray - 1, boy.yInArray)) {
                    setMovementLeft();
                    boy.whatMove = 3;
                    boy.isMoving = true;
                    if(itIsDiamondDoor(boy.xInArray-1, boy.yInArray)){
                        DiamondDoor diamondDoor = (DiamondDoor) levelMatrix[boy.xInArray-1][ boy.yInArray].getBlock();
                        if(diamondDoor.isExit()){
                            boyCanMove=false;
                            Util.wait(1000, new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    endLevel();
                                }
                            });
                        }
                    }
                } else if (itIsRock(boy.xInArray - 1, boy.yInArray)) {
                    if (itIsClearForStone(boy.xInArray - 2, boy.yInArray)) {
                        Rock rock = (Rock) levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject();
                        setMovementLeft();
                        boy.whatMove = 5;
                        boy.isMoving = true;
                        if (rock.whatMove != 2 && rock.whatMove != 3){
                            rock.whatMove = 0;
                            rock.isMoving = false;
                            rock.i = 0;
                            rock.timer.stop();
                            rock.whatMove = 5;
                            rock.isMoving = true;
                            rock.moveStone();
                        }
                    }
                    else{
                        boy.whatMove = 6;
                        boy.isMoving = true;
                    }
                } else if (!block.pass()) {
                    if (block instanceof DiamondDoor) {
                            drawMessage = true;
                            twoLineMessage = true;
                            message = "    I need more ";
                            messageLower = "purple diamonds!";
                    }
                    boy.whatMove = 21;
                    boy.isMoving = true;
                }
                moveBoy();
            }
            else if ((code == KeyEvent.VK_RIGHT) && (boy.isMoving == false) && isAllowedRight()) {
                Block block = levelMatrix[boy.xInArray + 1][boy.yInArray].getBlock();
                if (block.pass()) {
                    if (!itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray + 1, boy.yInArray) && !itIsRock(boy.xInArray + 1, boy.yInArray)) {
                        levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && !itIsTrap(boy.xInArray + 1, boy.yInArray)) {
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray + 1, boy.yInArray) &&
                            (!levelMatrix[boy.xInArray][boy.yInArray].getTrapObject().equals(levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject()))) {
                        levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    }
                }
                if (block instanceof DoorWithKeyhole.GoldDoor && !block.pass()) {
                    if (numberOfGoldKeysCollected != 0){
                        ((DoorWithKeyhole.GoldDoor) block).openTheDoor();
                        numberOfGoldKeysCollected--;
                        updateNumberOfGoldKeysOnStatusBar();
                        boy.whatMove = 16;
                        boy.isMoving = true;
                    }
                    else  {
                        boy.whatMove = 22;
                        boy.isMoving = true;
                        drawMessage = true;
                        twoLineMessage = true;
                        message = "    I need to find";
                        messageLower = "    a gold key!";
                    }
                }
                else if (block instanceof DoorWithKeyhole.SilverDoor && !block.pass()) {
                    if (numberOfSilverKeysCollected != 0) {
                        ((DoorWithKeyhole.SilverDoor) block).openTheDoor();
                        numberOfSilverKeysCollected--;
                        updateNumberOfSilverKeysOnStatusBar();
                        boy.whatMove = 18;
                        boy.isMoving = true;
                    }
                    else {
                        boy.whatMove = 22;
                        boy.isMoving = true;
                        drawMessage = true;
                        twoLineMessage = true;
                        message = "    I need to find";
                        messageLower = "    a silver key!";
                    }
                }
                else if ((block.pass() && !(itIsRock(boy.xInArray + 1, boy.yInArray))) || itIsHarmless(boy.xInArray + 1, boy.yInArray)) {
                    setMovementRight();
                    boy.whatMove = 4;
                    boy.isMoving = true;
                    if(itIsDiamondDoor(boy.xInArray+1, boy.yInArray)){
                        DiamondDoor diamondDoor = (DiamondDoor) levelMatrix[boy.xInArray+1][ boy.yInArray].getBlock();
                        if(diamondDoor.isExit()){
                            boyCanMove=false;
                            Util.wait(1000, new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                   endLevel();
                                }
                            });
                        }
                    }
                } else if (itIsRock(boy.xInArray + 1, boy.yInArray)) {
                    if (itIsClearForStone(boy.xInArray + 2, boy.yInArray)) {
                        Rock rock = (Rock) levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject();
                        setMovementRight();
                        boy.whatMove = 7;
                        boy.isMoving = true;
                        if (rock.whatMove != 2 && rock.whatMove != 3){
                            rock.whatMove = 0;
                            rock.isMoving = false;
                            rock.i = 0;
                            rock.timer.stop();
                            rock.whatMove = 6;
                            rock.isMoving = true;
                            rock.moveStone();
                        }
                    }
                    else {
                        boy.whatMove = 8;
                        boy.isMoving = true;
                    }
                } else if (!block.pass()) {
                    if (block instanceof DiamondDoor) {
                        drawMessage = true;
                        drawn = false;
                        twoLineMessage = true;
                        message = "    I need more ";
                        messageLower = "purple diamonds!";
                    }
                    boy.whatMove = 22;
                    boy.isMoving = true;
                }
                moveBoy();
            }
            else if ((code == KeyEvent.VK_SPACE) && (boy.isMoving == false)) {
                if (boy.currentPicture == boy.walkUp2){
                    boy.whatMove = 11;
                    if(levelMatrix[boy.xInArray][boy.yInArray-1].getBlock() instanceof BreakableWall){
                        BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray][boy.yInArray-1].getBlock();
                        bw.breakWall(levelMatrix,this);
                    }
                } else if (boy.currentPicture == boy.standClear){
                    boy.whatMove = 12;
                    if(levelMatrix[boy.xInArray][boy.yInArray+1].getBlock() instanceof BreakableWall){
                        BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray][boy.yInArray+1].getBlock();
                        bw.breakWall(levelMatrix,this);
                    }
                }else if ((boy.currentPicture == boy.standLeft)
                        || (boy.currentPicture == boy.walkLeft6)){
                    boy.whatMove = 13;
                    if(levelMatrix[boy.xInArray-1][boy.yInArray].getBlock() instanceof BreakableWall){
                        BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray-1][boy.yInArray].getBlock();
                        bw.breakWall(levelMatrix,this);
                    }
                }else if ((boy.currentPicture == boy.standRight)
                        || (boy.currentPicture == boy.walkRight6)){
                    if(levelMatrix[boy.xInArray+1][boy.yInArray].getBlock() instanceof BreakableWall){
                        BreakableWall bw = (BreakableWall) levelMatrix[boy.xInArray+1][boy.yInArray].getBlock();
                        bw.breakWall(levelMatrix,this);
                    }
                    boy.whatMove = 14;
                }
                boy.isMoving = true;
                moveBoy();
            }
        }
    }

    public void disappearFromCell(int x, int y){
        if (x != levelMatrix.length-1) {
            if (itIsStone(x, y - 1)) {
                getStone(x, y - 1).checkSpace();
            }
            if (itIsStone(x - 1, y)) {
                getStone(x - 1, y).checkSpace();
            }
            if (itIsStone(x + 1, y)) {
                getStone(x + 1, y).checkSpace();
            }
            if (itIsStone(x - 1, y - 1)){
                getStone(x - 1, y - 1).checkSpace();
            }
            if (itIsStone(x + 1, y - 1)){
                getStone(x + 1, y - 1).checkSpace();
            }
        }
    }

    public void checkHarmless(int x, int y){
        if (itIsDiamond(x, y)) ((Diamond)levelMatrix[x][y].getHarmlessObject()).disappear();
        else if (itIsTumbleweed(x, y)) ((Tumbleweed)levelMatrix[x][y].getHarmlessObject()).disappear();
    }

    public boolean itIsClearForStone(int x, int y){
        if (boy.xInArray == x && boy.yInArray == y) return false;
        if (itIsStone(x, y)) return false;
        if (itIsTrap(x, y)) return true;
        return (!itIsHarmless(x, y)
                && (itIsFloor(x, y) || itIsSecretWall(x, y)
                || itIsPressPanel(x, y) || itIsCheckpoint(x, y)));
    }

    public boolean itIsHarmless ( int x, int y){
        return (levelMatrix[x][y].getHarmlessObject() != null);
    }

    public boolean itIsTrap ( int x, int y){
        return (levelMatrix[x][y].getTrapObject() != null);
    }

    public boolean itIsStone(int x, int y){
        if (!itIsTrap(x, y) && !itIsHarmless(x, y)) return false;
        return (itIsRock(x, y) || itIsDiamond(x, y));
    }

    public Stone getStone(int x, int y){
        if (itIsStone(x, y)){
            if (itIsRock(x, y)) return (Stone)levelMatrix[x][y].getTrapObject();
            else return (Stone)levelMatrix[x][y].getHarmlessObject();
        }
        else return null;
    }

    public boolean itIsBlockedDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof BlockedDoor;
    }

    public boolean itIsDoorWithKeyhole(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof DoorWithKeyhole;
    }

    public boolean itIsPressPanel(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof PressMechanism.PressPanel;
    }

    public boolean itIsBreakableWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Wall;
    }

    public boolean itIsCheckpoint(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Checkpoint;
    }

    public boolean itIsFloor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Floor ||levelMatrix[x][y].getBlock() instanceof PressMechanism.PressPanel;
    }

    public boolean itIsSecretWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof SecretWall;
    }

    public boolean itIsDiamondDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof DiamondDoor;
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

    public boolean itIsScorpion(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Scorpion;
    }

    public boolean itIsSnake(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Snake;
    }


    private void finishTrapCheckTimer(int x, int y){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("finished");
                        if(itIsSnake(x,y)){
                            Snake snake = (Snake)levelMatrix[x][y].getTrapObject();
                            snake.getCheckTimer().stop();
                        }else if(itIsScorpion(x,y)){
                            Scorpion scorpion = (Scorpion)levelMatrix[x][y].getTrapObject();
                            scorpion.getCheckTimer().stop();
                        }else if(itIsFireTrap(x,y)){
                            FireTrap fireTrap = (FireTrap)levelMatrix[x][y].getTrapObject();
                            fireTrap.getCheckTimer().stop();
                        }
                    }
                },
                1000
        );
    }


    @Override
    public void keyReleased (KeyEvent e){

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public StatusBarPanel getStatusBarPanel() {
        return statusBarPanel;
    }

    public void setBoyCanMove(boolean boyCanMove) {
        this.boyCanMove = boyCanMove;
    }
}
