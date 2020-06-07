package objects;

import objects.blocks.doors.Resettable;
import objects.harmless.Diamond;
import objects.traps.Rock;
import objects.traps.Snake;
import source.PlayPanel;
import source.Util;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Iryna Matviienko
 */
public abstract class Stone implements Resettable {

    public int x;
    public int y;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    protected static final int CELL_SIDE = 70;
    public int whatMove;//Stay calm = 0; stagger = 1;
    // fallLeft = 2; fallRight = 3; fallDown = 4;
    //beShovenLeft = 5; beShovenRight = 6;
    public Image image;
    public int i;
    public boolean isMoving;
    public PlayPanel playPanel;
    public int xInArray;
    public int yInArray;
    public int mapX;
    public int mapY;
    public Timer timer;
    public boolean enabled = true;
    public Snake snake;
    private int tempI;
    private int tempWhatMove;
    public Clip rockMove = Util.getSound("sounds/rock_movement.wav",-15f);
    public Clip rockFall = Util.getSound("sounds/rock_falls.wav",-15f);
    private Timer fallSoundTimer;

    /**
     * Default constructor
     * Stone`s animation
     */
    public Stone(){
        initFallSoundTimer();
        whatMove = 0;
        i = 0;
        isMoving = false;
        timer = new Timer(65, null);
        timer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i == 0){
                    if ((whatMove == 4 || whatMove == 1) && !playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 0;
                        if (playPanel.itIsStone(xInArray, yInArray + 1)){
                            setWhatMove1();
                        }
                        if (whatMove == 0) i = 7;
                    }
                    else if (playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 4;
                    }
                    else if (whatMove == 2 || whatMove == 3){
                        if (stoneUnderIsFallingRightOrLeft()){
                            whatMove = 4;
                        }
                        else if (whatMove == 2){
                            Stone stoneLeftLeft = playPanel.getStone(xInArray - 2, yInArray);
                            if (stoneLeftLeft != null && stoneLeftLeft.isMoving && stoneLeftLeft.whatMove == 3){
                                whatMove = 1;
                            }
                            else if (playPanel.boy.xInArray == xInArray && playPanel.boy.yInArray == yInArray + 1){
                                whatMove = 0;
                            }
                            else{
                                Stone stoneLeftUp = playPanel.getStone(xInArray - 1, yInArray - 1);
                                if (stoneLeftUp != null && stoneLeftUp.isMoving && (stoneLeftUp.whatMove == 2 || stoneLeftUp.whatMove == 4)){
                                    whatMove = 0;
                                }
                                if (whatMove != 0){
                                    Stone stoneUp = playPanel.getStone(xInArray, yInArray - 1);
                                    if (stoneUp != null && stoneUp.isMoving && stoneUp.whatMove == 2 ){
                                        whatMove = 0;
                                    }
                                    else if (!playPanel.itIsClearForStone(xInArray - 1, yInArray)){
                                        whatMove = 0;
                                    }
                                }
                            }
                        }
                        else if (whatMove == 3){
                            if (playPanel.boy.xInArray == xInArray && playPanel.boy.yInArray == yInArray + 1) {
                                whatMove = 0;
                            }
                            else{
                                Stone stoneRightUp = playPanel.getStone(xInArray + 1, yInArray - 1);
                                if (stoneRightUp != null && stoneRightUp.isMoving && (stoneRightUp.whatMove == 3 || stoneRightUp.whatMove == 4)){
                                    whatMove = 0;
                                }
                                if (whatMove != 0){
                                    Stone stoneUp = playPanel.getStone(xInArray, yInArray - 1);
                                    if (stoneUp != null && stoneUp.isMoving && stoneUp.whatMove == 3 ){
                                        whatMove = 0;
                                    }
                                    else if (!playPanel.itIsClearForStone(xInArray + 1, yInArray)){
                                        whatMove = 0;
                                    }
                                }
                            }
                        }
                    }
                    else if (whatMove != 1 && whatMove != 2 && whatMove != 3
                            && playPanel.itIsStone(xInArray, yInArray + 1)){
                        setWhatMove1();
                    }
                }
                if (whatMove != 0) isMoving = true;
                if (whatMove == 1) stagger();
                else if (whatMove == 2) fallLeft();
                else if (whatMove == 3) fallRight();
                else if (whatMove == 4) fallDown();
                else if (whatMove == 5) beShovenLeft();
                else if (whatMove == 6) beShovenRight();
                playPanel.repaint();
                if (i == 7){
                    i = 0;
                    if (whatMove != 0 && playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                        whatMove = 4;
                    }
                    else if (whatMove == 2 || whatMove == 3) whatMove = 1;
                    else if (whatMove != 1 && playPanel.itIsStone(xInArray, yInArray + 1)){
                        if (!setWhatMove1()){
                            if (whatMove == 5 || whatMove == 6){
                                isMoving = false;
                                whatMove = 0;
                                timer.stop();
                            }
                        }
                    }
                    else if (whatMove == 1){
                        if (stoneUnderIsFallingRightOrLeft()){
                            whatMove = 4;
                        }
                        else if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1))){
                            whatMove = 3;
                        }
                        else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                                (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1))){
                            whatMove = 2;
                        }
                        else whatMove = 0;
                    }
                    else if (whatMove == 0){
                        isMoving = false;
                        timer.stop();
                    }
                    else{
                        isMoving = false;
                        whatMove = 0;
                        timer.stop();
                    }
                }
            }
        });
    }

    /**
     * Initialize variables
     * @param playPanel
     * @param xInArray
     * @param yInArray
     * @param mapX
     * @param mapY
     */
    public void initVars(PlayPanel playPanel, int xInArray, int yInArray, int mapX, int mapY){
        this.playPanel = playPanel;
        this.xInArray = xInArray;
        this.yInArray = yInArray;
        this.mapX = mapX;
        this.mapY = mapY;
        this.x = xInArray * 70 + mapX;
        this.y = yInArray * 70 + mapY;
    }

    /**
     * Start timer
     */
    public void moveStone(){
        if (!timer.isRunning()) timer.start();
    }

    /**
     * Check space around
     */
    public void checkSpace(){
        if (enabled){
            if (!timer.isRunning()){
                if (playPanel.itIsClearForStone(xInArray, yInArray + 1)){
                    this.whatMove = 4;
                    this.isMoving = true;
                    this.moveStone();
                }
                else if (whatMove != 1 && playPanel.itIsStone(xInArray, yInArray + 1)) {
                    if (setWhatMove1()){
                        this.isMoving = true;
                        this.moveStone();
                    }
                }
            }
        }
    }

    /**
     * Check whether stone under is falling right or left
     * @return
     */
    private boolean stoneUnderIsFallingRightOrLeft(){
        Stone stoneUnder = playPanel.getStone(xInArray, yInArray + 1);
        return (stoneUnder != null && stoneUnder.isMoving && (stoneUnder.whatMove == 2 || stoneUnder.whatMove == 3));
    }

    /**
     * Make whatMove 1
     * @return
     */
    private boolean setWhatMove1() {
        if (playPanel.itIsClearForStone(xInArray + 1, yInArray) &&
                (playPanel.itIsClearForStone(xInArray + 1, yInArray + 1)
                        || (playPanel.boy.xInArray == xInArray + 1 && playPanel.boy.yInArray == yInArray + 1))) {
            whatMove = 1;
        } else if (playPanel.itIsClearForStone(xInArray - 1, yInArray) &&
                (playPanel.itIsClearForStone(xInArray - 1, yInArray + 1)
                        || (playPanel.boy.xInArray == xInArray - 1 && playPanel.boy.yInArray == yInArray + 1))) {
            whatMove = 1;
        }
        return whatMove == 1;
    }

    /**
     * Check if it is snake nearby
     */
    private void checkSnake(){
        if(playPanel.itIsSnake(xInArray, yInArray + 1)){
            System.out.println("found snake");
            if(snake!=null && this.snake.isAlive){
                playPanel.currentLevel.getMatrix()[xInArray][yInArray].setTrapObject(snake);
            }
            Snake newSnake = (Snake)playPanel.currentLevel.getMatrix()[xInArray][yInArray + 1].getTrapObject();
            if(newSnake.getRockCheck()==null || (newSnake.getRockCheck()!=null && !newSnake.getRockCheck().isRunning())
                    || newSnake.rockFalling==null || !newSnake.rockFalling.equals(this)){
                System.out.println("timer started");
                newSnake.checkTimerStart(playPanel,this,playPanel.currentLevel.getMatrix());
                if(snake!=null && !newSnake.equals(snake)){
                    if(snake.getRockCheck()!=null && snake.getRockCheck().isRunning()){
                        snake.getRockCheck().stop();
                    }
                    snake = newSnake;
                }else{
                    snake = newSnake;
                }
            }
        }else{
            if(snake!=null){
                snake.getRockCheck().stop();
                if(snake.isAlive){
                    playPanel.currentLevel.getMatrix()[xInArray][yInArray].setTrapObject(snake);
                }
            }
        }
    }

    /**
     * One iteration of staggering
     */
    public void stagger () {
        if (i != 0) {
            if (i % 2 == 0) x += CELL_SIDE / 14;
            else{
                if (playPanel.itIsClearForStone(xInArray, yInArray + 1)) {
                    whatMove = 4;
                    i = -1;
                }
                else x -= CELL_SIDE / 14;
            }
        }
        i++;
    }

    /**
     * One iteration of falling left
     */
    public void fallLeft(){
        if(!fallSoundTimer.isRunning() && this instanceof Rock){
            fallSoundTimer.start();
        }
        if (i == 0){
            x -= 2 * CELL_SIDE / 7;
        }
        else if ((i == 1)||(i == 2)){
            x -= 1.5 * CELL_SIDE / 7;
            y += 0.5 * CELL_SIDE / 7;
            if (i == 2){
                setStoneToNewPositionInArray(xInArray - 1, yInArray);
                playPanel.disappearFromCell(xInArray, yInArray);
                xInArray -= 1;
            }
        }
        else if (i == 3){
            x -= CELL_SIDE / 7;
            y += CELL_SIDE / 7;
        }
        else if ((i == 4)||(i == 5)){
            x -= 0.5 * CELL_SIDE / 7;
            y += 1.5 * CELL_SIDE / 7;
            if (i == 5){
                checkSnake();
                setStoneToNewPositionInArray(xInArray, yInArray + 1);
                playPanel.disappearFromCell(xInArray, yInArray);
                yInArray++;
            }
        }
        else if (i == 6){
            y += 2 * CELL_SIDE / 7;
        }
        i++;
    }

    /**
     * One iteration of falling right
     */
    public void fallRight(){
        if(!fallSoundTimer.isRunning() && this instanceof Rock){
            fallSoundTimer.start();
        }
        if (i == 0){
            x += 2 * CELL_SIDE / 7;
        }
        else if ((i == 1)||(i == 2)){
            x += 1.5 * CELL_SIDE / 7;
            y += 0.5 * CELL_SIDE / 7;
            if (i == 2){
                setStoneToNewPositionInArray(xInArray + 1, yInArray);
                playPanel.disappearFromCell(xInArray, yInArray);
                xInArray ++;
            }
        }
        else if (i == 3){
            x += CELL_SIDE / 7;
            y += CELL_SIDE / 7;
        }
        else if ((i == 4)||(i == 5)){
            x += 0.5 * CELL_SIDE / 7;
            y += 1.5 * CELL_SIDE / 7;
            if (i == 5){
                checkSnake();
                setStoneToNewPositionInArray(xInArray, yInArray + 1);
                playPanel.disappearFromCell(xInArray, yInArray);
                yInArray++;
            }
        }
        else if (i == 6){
            y += 2 * CELL_SIDE / 7;
        }
        i++;
    }

    /**
     * One iteration of falling down
     */
    public void fallDown(){
        if (i == 3){
            if(!fallSoundTimer.isRunning() && this instanceof Rock){
                fallSoundTimer.start();
            }
            checkSnake();
            setStoneToNewPositionInArray(xInArray, yInArray + 1);
            playPanel.disappearFromCell(xInArray, yInArray);
            yInArray ++;
        }
        y += CELL_SIDE / 7;
        i++;
    }

    public abstract void beShovenLeft();
    public abstract void beShovenRight();

    /**
     * Update x and y
     * @param newMapX
     * @param newMapY
     */
    protected void updateXAndY(int newMapX, int newMapY){
        x = x - mapX + newMapX;
        y = y - mapY + newMapY;
        mapX = newMapX;
        mapY = newMapY;
    }

    /**
     * Reset variables
     */
    @Override
    public void reset() {
        timer.stop();
        whatMove = 0;
        i = 0;
        isMoving = false;
        snake = null;
    }

    /**
     * Variables when game is paused
     */
    @Override
    public void pause() {
        timer.stop();
        tempWhatMove = whatMove;
        tempI = i;
        whatMove = 0;
        i = 0;
        isMoving = false;
    }

    /**
     * Variables when game is resumed
     */
    @Override
    public void resume(){
        timer.start();
        whatMove = tempWhatMove;
        i = tempI;
        isMoving = true;
    }

    protected abstract void setStoneToNewPositionInArray(int xInArray, int yInArray);

    protected void startRockMoveSound(){
        if(playPanel.getGameFrame().soundOn){
            rockMove.start();
            Util.wait((int)rockMove.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rockMove.stop();
                    rockMove.setFramePosition(0);
                }
            });
        }
    }

    private void initFallSoundTimer(){
        fallSoundTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!playPanel.getGameFrame().soundOn){
                    fallSoundTimer.stop();
                }
                if (whatMove == 0) {
                    rockFall.start();
                    Util.wait((int) rockFall.getMicrosecondLength() / 1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rockFall.stop();
                            rockFall.setFramePosition(0);
                        }
                    });
                    fallSoundTimer.stop();
                }
            }
        });
    }

}
