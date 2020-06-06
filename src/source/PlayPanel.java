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
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * @author Volodymyr Dzhosan
 * @author Iryna Matviienko
 * @author Illia Sitkov
 */
public class PlayPanel extends JPanel implements KeyListener {

    public Boy boy;
    private PlayPanel panel;
    private Maps maps;
    private boolean stonesAreInited = false;
    private Timer trapTimer;
    public Timer takeEnergyTimer;

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
    public Cell[][] levelMatrix;

    private Checkpoint currentCheckpoint;
    private boolean updated = true;
    private boolean dead;

    public boolean drawMessage;
    public boolean twoLineMessage;
    public boolean threeLineMessage;
    public String message;
    public String messageLower;
    public String messageLowest;
    private boolean drawn;
    private int checkpointCost = 50;

    private int trapDelay = 5000;
    private int step = 200;
    private double angleUnit = 360/((trapDelay/step)-2);
    private double angle = 360+angleUnit;

    public int numberOfRedDiamondsCollected;

    public int numberOfPurpleDiamondsCollected;

    public int numberOfGoldKeysCollected;

    public int numberOfSilverKeysCollected;

    public int currentEnergyLevel;

    private boolean artefactIsCollected;
    private boolean statusBarIsInitiated;
    private LevelEndingDialog levelEndingDialog;
    private DeathDialog deathDialog;

    private boolean boyCanMove = true;
    private boolean energyIsBeeingTaken  = false;
    private StatusBarPanel statusBarPanel;
    private MapPanel mapPanel;
    private GameFrame gameFrame;
    private int currentLevelInt;
    private Font font = Util.getFont("fonts/v_SevenSwordsmen_BB.ttf", 16f);
    private Image image = new ImageIcon("mapImages/thoughtClouds.png").getImage();

    /**
     * constructor with parameters
     * @param currentLevel
     * @param gameFrame
     * @param mapPanel
     */
    public PlayPanel(int currentLevel, GameFrame gameFrame, MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        currentLevelInt = currentLevel;
        this.gameFrame = gameFrame;
        panel = this;
        panel.setLayout(null);
        setPreferredSize(new Dimension(2800, 1540));
        this.boy = new Boy(0,0);
        maps = new Maps(this);
        maps.initLevel(currentLevel);
        initLevel();
        statusBarPanel = new StatusBarPanel(gameFrame, this);
        initStatusBar();
        calculateInitialValuesOfMap();
        setCoordinates();
        trapTimer = new Timer(step,new Counter());
        takeEnergy();
    }

    /**
     * method initialises status bar and sets values to it
     */
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

    /**
     * method initialises current level
     */
    private void initLevel() {
        currentLevel = maps.getLevel(currentLevelInt);
        levelMatrix = this.currentLevel.getMatrix();
    }

    /**
     * method calculates initial values for level to start
     */
    private void calculateInitialValuesOfMap() {
        mapWidth = currentLevel.getMatrix().length* Values.CELL_SIZE;
        mapHeight = currentLevel.getMatrix()[0].length* Values.CELL_SIZE;

        positionOnScreenX = currentLevel.getPositionOnScreenX();
        positionOnScreenY = currentLevel.getPositionOnScreenY();

        positionOnMapX = currentLevel.getPositionOnMapX();
        positionOnMapY = currentLevel.getPositionOnMapY();
    }

    /**
     * method sets coordinates of the map
     */
    private void setCoordinates() {
        mapX = 0 - (Math.abs(positionOnMapX  - positionOnScreenX)* Values.CELL_SIZE);
        mapY = 0 - (Math.abs(positionOnMapY  - positionOnScreenY)* Values.CELL_SIZE);
        boy.x = positionOnScreenX* Values.CELL_SIZE;
        boy.y = positionOnScreenY* Values.CELL_SIZE;
        boy.xInArray = positionOnMapX;
        boy.yInArray = positionOnMapY;
    }

    /**
     * method restarts current level
     */
    public void restart() {

        for (int i = 0; i < levelMatrix.length; i++) {
                for (int j = 0; j < levelMatrix[0].length; j++) {
                    if (levelMatrix[i][j].getHarmlessObject() instanceof Diamond) {
                        ((Diamond) levelMatrix[i][j].getHarmlessObject()).enabled = false;
                        ((Diamond) levelMatrix[i][j].getHarmlessObject()).reset();
                    }
                    if (levelMatrix[i][j].getTrapObject() instanceof Rock) {
                        ((Rock) levelMatrix[i][j].getTrapObject()).enabled = false;
                        ((Rock) levelMatrix[i][j].getTrapObject()).reset();
                    }
                    if(levelMatrix[i][j].getTrapObject() instanceof Snake){
                        Snake snake = (Snake)levelMatrix[i][j].getTrapObject();
                        if(snake.getCheckTimer()!=null){
                            snake.getCheckTimer().stop();
                            snake.setCheckTimer(null);
                        }
                        if(snake.getRockCheck()!=null){
                            snake.getRockCheck().stop();
                            snake.setRockCheck(null);
                        }
                    }
                    if(levelMatrix[i][j].getTrapObject() instanceof FireTrap){
                        FireTrap fireTrap = (FireTrap)levelMatrix[i][j].getTrapObject();
                        if(fireTrap.getCheckTimer()!=null){
                            fireTrap.getCheckTimer().stop();
                            fireTrap.setCheckTimer(null);
                        }
                    }
                    if(levelMatrix[i][j].getTrapObject() instanceof Scorpion){
                        Scorpion scorpion = (Scorpion)levelMatrix[i][j].getTrapObject();
                        if(scorpion.getCheckTimer()!=null){
                            scorpion.getCheckTimer().stop();
                            scorpion.setCheckTimer(null);
                        }
                    }
                }
            }




        currentLevel = null;
        currentCheckpoint = null;
        stonesAreInited = false;
        dead = false;

        drawMessage = false;
        twoLineMessage = false;
        drawn = false;
        boy = null;
        boy = new Boy(0,0);

        boyCanMove = true;

        mapMovesToRight = false;
        boyMovesToRight = false;

        mapMovesToLeft = false;
        boyMovesToLeft = false;

        mapMovesUp = false;
        boyMovesUp = false;

        mapMovesDown = false;
        boyMovesDown = false;

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
        if (!takeEnergyTimer.isRunning())
            takeEnergyTimer.start();
    }

    /**
     * method updates info on the status bar panel
     */
    public void updateStatusBar(){
        updateNumberOfPurpleDiamondsOnStatusBar();
        updateEnergyLevelOnStatusBar();
        updateNumberOfRedDiamondsOnStatusBar();
        updateNumberOfSilverKeysOnStatusBar();
        updateNumberOfGoldKeysOnStatusBar();
    }


    /**
     * method updates number of red diamonds on the status bar panel
     */
    public void updateNumberOfRedDiamondsOnStatusBar(){
        gameFrame.updateNumberOfRedDiamondsOnStatusBar(numberOfRedDiamondsCollected);
    }
    /**
     * method updates number of purple diamonds on the status bar panel
     */
    public void updateNumberOfPurpleDiamondsOnStatusBar(){
       gameFrame.updateNumberOfPurpleDiamondsOnStatusBar(numberOfPurpleDiamondsCollected);
    }
    /**
     * method updates number gold keys on the status bar panel
     */
    public void updateNumberOfGoldKeysOnStatusBar(){
        gameFrame.updateNumberOfGoldKeysOnStatusBar(numberOfGoldKeysCollected);
    }
    /**
     * method updates number of silver keys on the status bar panel
     */
    public void updateNumberOfSilverKeysOnStatusBar(){
        gameFrame.updateNumberOfSilverKeysOnStatusBar(numberOfSilverKeysCollected);
    }
    /**
     * method updates energy level on the status bar panel
     */
    public void updateEnergyLevelOnStatusBar(){
        gameFrame.updateEnergyLevelOnStatusBar(currentEnergyLevel);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!statusBarIsInitiated) {
            updateStatusBar();
            statusBarIsInitiated = true;
        }
        panel.removeAll();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ArrayList<SecretWall> secretWalls = new ArrayList<>();
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                if (levelMatrix[i][j].getBlock() instanceof SecretWall) {
                    SecretWall secretWall = (SecretWall) levelMatrix[i][j].getBlock();
                    secretWall.setCoordinates(mapX + i * 70, mapY + j * 70);
                    secretWalls.add(secretWall);
                    g2.drawImage(new ImageIcon("mapImages/floor.png").getImage(),
                            mapX + i * 70, mapY + j * 70, 70, 70, null);
                } else {
                    levelMatrix[i][j].getBlock().paintObject(g2, mapX + i * 70, mapY + j * 70);
                }
            }
        }
        for (int i = 0; i < levelMatrix.length; i++) {
            for (int j = 0; j < levelMatrix[i].length; j++) {
                if (levelMatrix[i][j].getBlock() instanceof PressMechanism.PressPanel) {
                    ((PressMechanism.PressPanel) levelMatrix[i][j].getBlock()).interact(levelMatrix, boy.xInArray, boy.yInArray);
                } else if (levelMatrix[i][j].getBlock() instanceof DoubleDoor.RightDoor) {
                    ((DoubleDoor.RightDoor) levelMatrix[i][j].getBlock()).interact(this, boy);
                } else if (levelMatrix[i][j].getBlock() instanceof DiamondDoor) {
                    ((DiamondDoor) levelMatrix[i][j].getBlock()).setNumberOfDiamonds(numberOfPurpleDiamondsCollected);
                }
                if (levelMatrix[i][j].getTrapObject() != null) {
                    if (levelMatrix[i][j].getTrapObject() instanceof Rock) {
                        if (!stonesAreInited)
                            ((Rock) levelMatrix[i][j].getTrapObject()).initVars(this, i, j, mapX, mapY);
                        if (mapIsMoving()) levelMatrix[i][j].getTrapObject().paintObject(g2, mapX, mapY);
                        else levelMatrix[i][j].getTrapObject().paintObject(g2);
                    } else {
                        JLabel label = levelMatrix[i][j].getTrapObject().getLabel();
                        if (label.getParent() != panel) {
                            Dimension size = label.getPreferredSize();
                            label.setBounds(mapX + i * 70, mapY + j * 70, size.width, size.height);
                            add(label);
                        }
                    }
                }
                if (levelMatrix[i][j].getHarmlessObject() != null) {
                    if (levelMatrix[i][j].getHarmlessObject() instanceof Diamond) {
                        if (!stonesAreInited)
                            ((Diamond) levelMatrix[i][j].getHarmlessObject()).initVars(this, i, j, mapX, mapY);
                        if (mapIsMoving()) levelMatrix[i][j].getHarmlessObject().paintObject(g2, mapX, mapY);
                        else levelMatrix[i][j].getHarmlessObject().paintObject(g2);
                    } else {
                        levelMatrix[i][j].getHarmlessObject().paintObject(g2, mapX + i * 70, mapY + j * 70);
                        if (itIsTumbleweed(i, j) && stonesAreInited) {
                            ((Tumbleweed) levelMatrix[i][j].getHarmlessObject()).initVars(this, i, j);
                        }
                        if (levelMatrix[i][j].getHarmlessObject() instanceof Chest) {
                            if (((Chest) levelMatrix[i][j].getHarmlessObject()).thingsAreBeeingTaken) {
                                if (((Chest) levelMatrix[i][j].getHarmlessObject()).currentThing != null) {
                                    ((Chest) levelMatrix[i][j].getHarmlessObject()).currentThing.paintObject(g2, mapX + i * 70, mapY + (j - 1) * 70);
                                }
                            }
                        }
                    }
                }
            }
        }
        stonesAreInited = true;
        g2.drawImage(boy.currentPicture, boy.x, boy.y, boy.width, boy.height, null);
        for (SecretWall secretWall : secretWalls) {
            secretWall.paintObject(g2, secretWall.getX(), secretWall.getY());
        }
        if (!updated) {
            mapMovesDown = false;
            updated = true;
            boy.isMoving = false;
        }
        if (drawMessage && !boy.isMoving) {
            drawn = true;
            g2.setFont(font);
            g2.drawImage(image, boy.x - 60, boy.y - 70, 185, 95, null);
            g2.setColor(new Color(255, 157, 0));
            if (twoLineMessage) {
                g2.drawString(message, boy.x - 50, boy.y - 40);
                g2.drawString(messageLower, boy.x - 50, boy.y - 25);
            } else if (threeLineMessage){
                g2.drawString(message, boy.x - 50, boy.y - 40);
                g2.drawString(messageLower, boy.x - 50, boy.y - 25);
                g2.drawString(messageLowest,boy.x - 50, boy.y - 10);
            }
            else g2.drawString(message, boy.x - 50, boy.y - 30);
        }
        if (drawMessage && boy.isMoving && drawn) {
            drawn = false;
            drawMessage = false;
            twoLineMessage = false;
        }
        if (boy.gotInTrap || trapTimer.isRunning()) {
            if (!trapTimer.isRunning()) {
                trapTimer.start();
            }
            trapCounter(g2);
        }
    }


    private class Counter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (angle >0) {
                if (!boy.isMoving)
                    PlayPanel.this.repaint();
                angle -= angleUnit;
            }
            else if (angle<=0){
                trapTimer.stop();
                angle = 360+angleUnit;
            }
        }
    }

    /**
     * draws circle that shows how much time left for the boy to be vulnerable again
     * @param g
     */
    private void trapCounter(Graphics2D g){
        Color color = g.getColor();
        g.setColor(Color.green);
        g.fillArc(boy.x+boy.width,boy.y+20,40,40,90, (int) angle);
        g.setColor(color);
    }

    /**
     * method applies checkpoint
     */
    public void applyCheckpoint(){
        if (currentCheckpoint != null && currentEnergyLevel >= checkpointCost) {
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
            currentEnergyLevel -= checkpointCost;
            currentEnergyLevel = currentEnergyLevel<0?0:currentEnergyLevel;
            numberOfGoldKeysCollected = numberOfGoldKeysCollected < 0?0:numberOfGoldKeysCollected;
            numberOfSilverKeysCollected = numberOfSilverKeysCollected < 0?0:numberOfSilverKeysCollected;
            setCoordinates();
            repaint();
            updateStatusBar();
        }
        else if (currentEnergyLevel < checkpointCost){
            drawMessage = true;
            twoLineMessage = true;
            message = "       Low energy";
            messageLower = "                level!";
            drawn = false;
            repaint();
        }
        if(currentEnergyLevel <= 0){
            dead = true;
        }
    }

    /**
     * method determines whether boy or map should move right
     */
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
    /**
     * method determines whether boy or map should move left
     */
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
    /**
     * method determines whether boy or map should move up
     */
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
    /**
     * method determines whether boy or map should mover down
     */
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


    /**
     * method moves map to right
     */
    private void moveMapToRight(){
        mapX -= Values.CELL_SIZE/7;
    }
    /**
     * method moves map to left
     */
    private void moveMapToLeft(){
        mapX += Values.CELL_SIZE/7;
    }
    /**
     * method moves map up
     */
    private void moveMapUp(){
        mapY += Values.CELL_SIZE/7;
    }
    /**
     * method moves map down
     */
    private void moveMapDown(){
        mapY -= Values.CELL_SIZE/7;
    }


    /**
     * Animation of all boy`s moves
     */
    public void moveBoy(){
        Timer t = new Timer(65, null);
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
                    //System.out.println(boy.xInArray + ", " + boy.yInArray);
                    t.stop();
                    Checkpoint temp = currentCheckpoint;
                    currentCheckpoint = currentLevel.getCheckpoint(boy.xInArray,boy.yInArray);
                    if (currentCheckpoint == null)
                        currentCheckpoint = temp;
                    else if (!currentCheckpoint.isUsed) {
                        currentCheckpoint.setUsed(true);
                        drawMessage = true;
                        message = " Контрольна точка!";
                        repaint();
                    }
                }
            }
        });
        t.start();
    }


    /**
     * Decrease quantity of energy when the rock is on the boy`s head
     */
    public void takeEnergy(){
        takeEnergyTimer = new Timer(700, null);
        takeEnergyTimer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itIsRock(boy.xInArray, boy.yInArray - 1) && !energyIsBeeingTaken){
                    energyIsBeeingTaken = true;
                    if (!boy.isMoving){
                        boy.currentPicture = boy.imHoldARock;
                        repaint();
                    }
                    currentEnergyLevel -= 10;
                    if (currentEnergyLevel <= 0){
                        currentEnergyLevel = 0;
                        takeEnergyTimer.stop();
                        pause();
                        Util.wait(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                endLevel(false);
                            }
                        });
                    }
                    updateEnergyLevelOnStatusBar();
                }
                else if (dead){
                    takeEnergyTimer.stop();
                    pause();
                    Util.wait(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            endLevel(false);
                        }
                    });
                }
                else{
                    energyIsBeeingTaken = false;
                }
            }
        });
        takeEnergyTimer.start();
    }

    public void takeEnergy(int energy){
        currentEnergyLevel -= energy;
        if (currentEnergyLevel <= 0){
            currentEnergyLevel = 0;
            boyCanMove = false;
            takeEnergyTimer.stop();
            pause();
            Util.wait(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    endLevel(false);
                }
            });
        }
        updateEnergyLevelOnStatusBar();
    }

    /**
     * method pauses game
     */
    public void pause(){
        boyCanMove = false;
        trapTimer.stop();
        takeEnergyTimer.stop();
        for (byte i = 0; i < levelMatrix.length; i ++){
            for (byte j = 0; j < levelMatrix[0].length; j ++){
                if (levelMatrix[i][j].getTrapObject()!=null){
                    levelMatrix[i][j].getTrapObject().pause();
                }
                else if (levelMatrix[i][j].getHarmlessObject()!=null){
                    if (itIsDiamond(i, j)) ((Diamond) levelMatrix[i][j].getHarmlessObject()).pause();
                }
            }
        }
    }

    /**
     * method resumes game
     */
    public void resume(){
        boyCanMove = true;
        takeEnergyTimer.start();
        for (byte i = 0; i < levelMatrix.length; i ++){
            for (byte j = 0; j < levelMatrix[0].length; j ++){
                if (levelMatrix[i][j].getTrapObject()!=null){
                    levelMatrix[i][j].getTrapObject().resume();
                }
                if (levelMatrix[i][j].getHarmlessObject() instanceof Diamond){
                    ((Diamond) levelMatrix[i][j].getHarmlessObject()).resume();
                }
            }
        }
    }


    /**
     * method checks if it it possible to move up
     * @return
     */
    private boolean isAllowedUp(){
        return boy.y != 0 && boyCanMove;
    }
    /**
     * method checks if it it possible to move down
     * @return
     */
    private boolean isAllowedDown(){
        return boy.y != panelHeight- boy.height && boyCanMove;
    }
    /**
     * method checks if it it possible to move right
     * @return
     */
    private boolean isAllowedRight(){
        return boy.xInArray != levelMatrix.length-1 && boyCanMove;
    }
    /**
     * method checks if it it possible to move left
     * @return
     */
    private boolean isAllowedLeft(){
        return boy.x != 0 && boyCanMove;
    }
    /**
     * method checks if map is moving
     * @return
     */
    private boolean mapIsMoving(){
        return (mapMovesUp || mapMovesDown || mapMovesToLeft || mapMovesToRight);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * method ends level
     * @param win
     */
    private void endLevel(boolean win){
        if (win) {
            pause();
            if (numberOfPurpleDiamondsCollected == currentLevel.getMaxNumberOfPurpleDiamonds()
                    && numberOfRedDiamondsCollected == currentLevel.getMaxNumberOfRedDiamonds()
                    && revivals == 0)
                artefactIsCollected = true;
            ProgressStorage.updateContent(currentLevelInt, true, artefactIsCollected);
            gameFrame.updatePuzzlePanel(currentLevelInt, artefactIsCollected);
            mapPanel.openNextLevel(currentLevelInt);
            levelEndingDialog = new LevelEndingDialog(gameFrame,this);
        }
        else {
            pause();
            deathDialog = new DeathDialog(gameFrame,this);
        }
    }

    /**
     * Reaction of boy for typing some keys
     * @param e
     */
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
                    if(itIsFireTrap(boy.xInArray, boy.yInArray - 1)) {
                        FireTrap fireTrap = (FireTrap) levelMatrix[boy.xInArray][boy.yInArray - 1].getTrapObject();
                        if (fireTrap.isHead(boy,0)) {
                            Floor floor = (Floor) block;
                            floor.setPassable(false);
                            if(fireTrap.getCheckTimer()!=null && fireTrap.getCheckTimer().isRunning()){
                                fireTrap.getCheckTimer().stop();
                            }
                        } else {
                            if(fireTrap.getCheckTimer()!=null && !fireTrap.getCheckTimer().isRunning()) {
                                fireTrap.checkTimerStart(this, boy, levelMatrix);
                            }
                        }
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
                                    endLevel(true);
                                }
                            });
                        }
                    }
                } else if (itIsRock(boy.xInArray, boy.yInArray - 1)) {
                    boy.whatMove = 10;
                    boy.isMoving = true;
                } else if (!block.pass()) {
                    boy.whatMove = 19;
                    boy.isMoving = true;
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
                    if(itIsFireTrap(boy.xInArray, boy.yInArray + 1)) {
                        FireTrap fireTrap = (FireTrap) levelMatrix[boy.xInArray][boy.yInArray + 1].getTrapObject();
                        if (fireTrap.isHead(boy,0)) {
                            Floor floor = (Floor) block;
                            floor.setPassable(false);
                            if(fireTrap.getCheckTimer()!=null && fireTrap.getCheckTimer().isRunning()){
                                fireTrap.getCheckTimer().stop();
                            }
                        } else {
                            if(fireTrap.getCheckTimer()!=null && !fireTrap.getCheckTimer().isRunning()) {
                                fireTrap.checkTimerStart(this, boy, levelMatrix);
                            }
                        }
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
                                    endLevel(true);
                                }
                            });
                        }
                    }
                } else if (!block.pass() || itIsRock(boy.xInArray, boy.yInArray + 1)) {
                    boy.whatMove = 20;
                    boy.isMoving = true;
                }
                moveBoy();
            } else if ((code == KeyEvent.VK_LEFT) && (boy.isMoving == false) && isAllowedLeft()) {
                Block block = levelMatrix[boy.xInArray - 1][boy.yInArray].getBlock();
                if (block.pass()) {
                    if (!itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray - 1, boy.yInArray) && !itIsRock(boy.xInArray - 1, boy.yInArray)) {
                            levelMatrix[boy.xInArray-1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && !itIsTrap(boy.xInArray - 1, boy.yInArray)) {
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray - 1, boy.yInArray) &&
                            (!levelMatrix[boy.xInArray][boy.yInArray].getTrapObject().equals(levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject()))) {
                        levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    }
                    if(itIsFireTrap(boy.xInArray-1, boy.yInArray)) {
                        FireTrap fireTrap = (FireTrap) levelMatrix[boy.xInArray-1][boy.yInArray].getTrapObject();
                        if (fireTrap.isHead(boy,-70)) {
                            Floor floor = (Floor) block;
                            floor.setPassable(false);
                            if(fireTrap.getCheckTimer()!=null && fireTrap.getCheckTimer().isRunning()){
                                fireTrap.getCheckTimer().stop();
                            }
                        } else {
                            if(fireTrap.getCheckTimer()!=null && !fireTrap.getCheckTimer().isRunning()) {
                                fireTrap.checkTimerStart(this, boy, levelMatrix);
                            }
                        }
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
                        drawn = false;
                        twoLineMessage = true;
                        message = "    Я маю знайти";
                        messageLower = "    золотий ключ!";
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
                        drawn = false;
                        twoLineMessage = true;
                        message = "    Я маю знайти";
                        messageLower = "      срібний ключ!";
                        boy.whatMove = 21;
                        boy.isMoving = true;
                    }
                }
                else if ((block.pass() && !(itIsRock(boy.xInArray - 1, boy.yInArray))) || itIsHarmless(boy.xInArray - 1, boy.yInArray)) {
                    if (rockLeftIsFallingDown()){
                            boy.whatMove = 6;
                            boy.isMoving = true;
                    }
                    else{
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
                                        endLevel(true);
                                    }
                                });
                            }
                        }
                    }
                } else if (itIsRock(boy.xInArray - 1, boy.yInArray)) {
                    Rock rock = (Rock) levelMatrix[boy.xInArray - 1][boy.yInArray].getTrapObject();
                    if (itIsClearForStone(boy.xInArray - 2, boy.yInArray) && rockCheckForSnakes(boy.xInArray - 2, boy.yInArray,rock)) {
                        if (rock.whatMove != 1 && rock.whatMove != 2 && rock.whatMove != 3 && rock.whatMove != 4){
                            setMovementLeft();
                            boy.whatMove = 5;
                            boy.isMoving = true;
                            rock.whatMove = 5;
                            rock.isMoving = true;
                            rock.moveStone();
                        }
                        else{
                            boy.whatMove = 6;
                            boy.isMoving = true;
                        }
                    }
                    else{
                        boy.whatMove = 6;
                        boy.isMoving = true;
                    }
                } else if (!block.pass()) {
                    if (block instanceof DiamondDoor) {
                            drawMessage = true;
                            drawn = false;
                            threeLineMessage = true;
                            message = "Мені треба зібрати ";
                            messageLower = "більше фіолетових";
                            messageLowest = "     діамантів!";
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
                            levelMatrix[boy.xInArray+1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && !itIsTrap(boy.xInArray + 1, boy.yInArray)) {
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    } else if (itIsTrap(boy.xInArray, boy.yInArray) && itIsTrap(boy.xInArray + 1, boy.yInArray) &&
                            (!levelMatrix[boy.xInArray][boy.yInArray].getTrapObject().equals(levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject()))) {
                        levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject().checkTimerStart(this, boy, levelMatrix);
                        finishTrapCheckTimer(boy.xInArray, boy.yInArray);
                    }
                    if(itIsFireTrap(boy.xInArray+1, boy.yInArray)) {
                        FireTrap fireTrap = (FireTrap) levelMatrix[boy.xInArray+1][boy.yInArray].getTrapObject();
                        if (fireTrap.isHead(boy,70)) {
                            Floor floor = (Floor) block;
                            floor.setPassable(false);
                            if(fireTrap.getCheckTimer()!=null && fireTrap.getCheckTimer().isRunning()){
                                fireTrap.getCheckTimer().stop();
                            }
                        } else {
                            if(fireTrap.getCheckTimer()!=null && !fireTrap.getCheckTimer().isRunning()) {
                                fireTrap.checkTimerStart(this, boy, levelMatrix);
                            }
                        }
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
                    else if (numberOfGoldKeysCollected == 0) {
                        boy.whatMove = 22;
                        boy.isMoving = true;
                        drawn = false;
                        drawMessage = true;
                        twoLineMessage = true;
                        message = "    Я маю знайти";
                        messageLower = "    золотий ключ!";
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
                    else if (numberOfSilverKeysCollected == 0){
                        boy.whatMove = 22;
                        boy.isMoving = true;
                        drawn = false;
                        drawMessage = true;
                        twoLineMessage = true;
                        message = "    Я маю знайти";
                        messageLower = "      срібний ключ!";
                    }
                }
                else if ((block.pass() && !(itIsRock(boy.xInArray + 1, boy.yInArray))) || itIsHarmless(boy.xInArray + 1, boy.yInArray)) {
                    if (rockRightIsFallingDown()){
                            boy.whatMove = 8;
                            boy.isMoving = true;
                    }
                    else{
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
                                        endLevel(true);
                                    }
                                });
                            }
                        }
                    }
                } else if (itIsRock(boy.xInArray + 1, boy.yInArray)) {
                    Rock rock = (Rock) levelMatrix[boy.xInArray + 1][boy.yInArray].getTrapObject();
                    if (itIsClearForStone(boy.xInArray + 2, boy.yInArray) && rockCheckForSnakes(boy.xInArray + 2, boy.yInArray,rock)) {
                        //possibleRock = rock;
                        if (rock.whatMove != 1 && rock.whatMove != 2 && rock.whatMove != 3 && rock.whatMove != 4){
                            setMovementRight();
                            boy.whatMove = 7;
                            boy.isMoving = true;
                            rock.whatMove = 6;
                            rock.isMoving = true;
                            rock.moveStone();
                        }
                        else{
                            boy.whatMove = 8;
                            boy.isMoving = true;
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
                        threeLineMessage = true;
                        message = "Мені треба зібрати ";
                        messageLower = "більше фіолетових";
                        messageLowest = "     діамантів!";
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

    /**
     * Check if rock left is falling down
     * @return
     */
    private boolean rockLeftIsFallingDown(){
        if (itIsRock(boy.xInArray - 1, boy.yInArray - 1)) {
            Rock rock = (Rock) levelMatrix[boy.xInArray - 1][boy.yInArray - 1].getTrapObject();
            if (rock.isMoving && rock.whatMove == 4 && rock.i <= 3){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if rock right is falling down
     * @return
     */
    private boolean rockRightIsFallingDown(){
        if (itIsRock(boy.xInArray + 1, boy.yInArray - 1)) {
            Rock rock = (Rock) levelMatrix[boy.xInArray + 1][boy.yInArray - 1].getTrapObject();
            if (rock.isMoving && rock.whatMove == 4 && rock.i <= 3){
                return true;
            }
        }
        return false;
    }

    private boolean rockCheckForSnakes(int xInArray, int yInArray, Rock rock){
        if(itIsSnake(xInArray,yInArray)){
            Snake snake = (Snake) levelMatrix[xInArray][yInArray].getTrapObject();
            Rectangle snakeRect = new Rectangle(snake.x+snake.getLabel().getX(),snake.y+snake.getLabel().getY(),70,70);
            Rectangle rockRect = new Rectangle(rock.x+(xInArray-rock.xInArray)*70,rock.y,70,70);
            if(rockRect.intersects(snakeRect)) return false;
            else return true;
        }
        return true;
    }

    /**
     * When Tumbleweed, Diamond, Rock, BreakableWall or Boy disappears from the cell,
     * all stones around need to check space under them
     * @param x
     * @param y
     */
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

    /**
     * Tumbleweeds and Diamonds need to disappear when the boy is on their Cell
     * @param x
     * @param y
     */
    public void checkHarmless(int x, int y){
        if (itIsDiamond(x, y)) ((Diamond)levelMatrix[x][y].getHarmlessObject()).disappear();
        else if (itIsTumbleweed(x, y)) ((Tumbleweed)levelMatrix[x][y].getHarmlessObject()).disappear();
    }

    /**
     * Check if it is clear for stone in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsClearForStone(int x, int y){
        if (boy.xInArray == x && boy.yInArray == y) return false;
        if (itIsStone(x, y)) return false;
        if(itIsTumbleweed(x,y) || itIsChest(x,y)) return false;
        return ((!itIsHarmless(x, y) || itIsTrap(x, y))
                && (itIsFloor(x, y) || itIsSecretWall(x, y)
                || itIsPressPanel(x, y) || itIsCheckpoint(x, y)
                || itIsOpenedDoor(x, y)));
    }

    /**
     * Check if it is Harmless in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsHarmless ( int x, int y){
        return (levelMatrix[x][y].getHarmlessObject() != null);
    }

    /**
     * Check if it is Trap in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsTrap ( int x, int y){
        return (levelMatrix[x][y].getTrapObject() != null);
    }

    /**
     * Check if it is Diamond or Rock in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsStone(int x, int y){
        if (!itIsTrap(x, y) && !itIsHarmless(x, y)) return false;
        return (itIsRock(x, y) || itIsDiamond(x, y));
    }

    /**
     * Return Stone from the cell
     * @param x
     * @param y
     * @return
     */
    public Stone getStone(int x, int y){
        if (itIsStone(x, y)){
            if (itIsRock(x, y)) return (Stone)levelMatrix[x][y].getTrapObject();
            else return (Stone)levelMatrix[x][y].getHarmlessObject();
        }
        else return null;
    }

    /**
     * Check if it is BlockedDoor in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsBlockedDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof BlockedDoor;
    }

    /**
     * Check if it is DiamondDoor in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsDiamondDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof DiamondDoor;
    }

    /**
     * Check if it is DoorWithKeyhole in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsDoorWithKeyhole(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof DoorWithKeyhole.GoldDoor
                || levelMatrix[x][y].getBlock() instanceof DoorWithKeyhole.SilverDoor;
    }

    /**
     * Check if it is DoubleDoor in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsDoubleDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof DoubleDoor.RightDoor
                || levelMatrix[x][y].getBlock() instanceof DoubleDoor.LeftDoor;
    }

    /**
     * Check if it is PressPanel in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsPressPanel(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof PressMechanism.PressPanel;
    }

    /**
     * Check if it is Door in PressMechanism in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsDoorInPressMechanism(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof PressMechanism.Door;
    }

    /**
     * Check if it is opened door in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsOpenedDoor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return (itIsDiamondDoor(x, y) || itIsDoorWithKeyhole(x, y)
                || itIsDoubleDoor(x, y) || itIsDoorInPressMechanism(x, y))
                && levelMatrix[x][y].getBlock().pass();
    }

    /**
     * Check if it is BreakableWall in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsBreakableWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Wall;
    }

    /**
     * Check if it is Checkpoint in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsCheckpoint(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Checkpoint;
    }

    /**
     * Check if it is Floor in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsFloor(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Floor ||levelMatrix[x][y].getBlock() instanceof PressMechanism.PressPanel;
    }

    /**
     * Check if it is SecretWall in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsSecretWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof SecretWall;
    }

    /**
     * Check if it is Wall in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsWall(int x, int y){
        if (levelMatrix[x][y].getBlock() == null) return false;
        return levelMatrix[x][y].getBlock() instanceof Wall;
    }

    /**
     * Check if it is Chest in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsChest(int x, int y){
        if (levelMatrix[x][y].getHarmlessObject() == null) return false;
        return levelMatrix[x][y].getHarmlessObject() instanceof Chest;
    }

    /**
     * Check if it is Diamond in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsDiamond(int x, int y){
        if (levelMatrix[x][y].getHarmlessObject() == null) return false;
        return levelMatrix[x][y].getHarmlessObject() instanceof Diamond;
    }

    /**
     * Check if it is Tumbleweed in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsTumbleweed(int x, int y){
        if (levelMatrix[x][y].getHarmlessObject() == null) return false;
        return levelMatrix[x][y].getHarmlessObject() instanceof Tumbleweed;
    }

    /**
     * Check if it is FireTrap in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsFireTrap(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof FireTrap;
    }

    /**
     * Check if it is Rock in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsRock(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Rock;
    }

    /**
     * Check if it is Scorpion in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsScorpion(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Scorpion;
    }

    /**
     * Check if it is Snake in the cell
     * @param x
     * @param y
     * @return
     */
    public boolean itIsSnake(int x, int y){
        if (levelMatrix[x][y].getTrapObject() == null) return false;
        return levelMatrix[x][y].getTrapObject() instanceof Snake;
    }


    private void finishTrapCheckTimer(int x, int y){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if(itIsSnake(x,y)){
                            Snake snake = (Snake)levelMatrix[x][y].getTrapObject();
                            if(snake.getCheckTimer()!= null && snake.getCheckTimer().isRunning()){
                                snake.getCheckTimer().stop();
                            }
                        }else if(itIsScorpion(x,y)){
                            Scorpion scorpion = (Scorpion)levelMatrix[x][y].getTrapObject();
                            if(scorpion.getCheckTimer()!= null && scorpion.getCheckTimer().isRunning()){
                                scorpion.getCheckTimer().stop();
                            }
                        }else if(itIsFireTrap(x,y)){
                            FireTrap fireTrap = (FireTrap)levelMatrix[x][y].getTrapObject();
                            if(fireTrap.getCheckTimer()!= null && fireTrap.getCheckTimer().isRunning()){
                                fireTrap.getCheckTimer().stop();
                            }
                        }
                    }
                },
                500
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

    public int getCurrentLevelInt() {
        return currentLevelInt;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }
}
