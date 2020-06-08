package source;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Iryna Matviienko
 */
public class Boy {

    int x;
    int y;
    public int xInArray;
    public int yInArray;
    int width;
    int height;
    int cellSide = Values.CELL_SIZE;
    public int whatMove = 0; //Stand = 0; Up = 1; Down = 2; Left = 3; Right = 4;
    //shoveLeftAndMove = 5; shoveLeftAndStand = 6; shoveRightAndMove = 7; shoveRightAndStand = 8;
    //findInChest = 9; holdARock = 10;
    //attackLeft = 11; attackRight = 12; attackLeft = 13; attackRight = 14;
    //openWithGoldKeyLeft = 15; openWithGoldKeyRight = 16; openWithSilverKeyLeft = 17; openWithSilverKeyRight = 18;
    //UpAnimation = 19; DownAnimation = 20; LeftAnimation = 21; RightAnimation = 22;
    //openChest = 23
    public boolean isMoving = false;
    public boolean gotInTrap = false;
    public int i = 0;
    public Clip hurtSound = Util.getSound("sounds/hurt.wav",-10f);

    Image standLeft = new ImageIcon("boy/standLeft.png").getImage();
    Image standRight = new ImageIcon("boy/standRight.png").getImage();
    public Image standClear = new ImageIcon("boy/standClear.png").getImage();

    Image walkUp0 = new ImageIcon("boy/walkUp0.png").getImage();
    Image walkUp1 = new ImageIcon("boy/walkUp1.png").getImage();
    Image walkUp2 = new ImageIcon("boy/walkUp2.png").getImage();
    Image[] arrayUp = {walkUp0, walkUp0, walkUp1, walkUp1,
            walkUp0, walkUp0, walkUp2};

    Image walkDown0 = new ImageIcon("boy/walkDown0.png").getImage();
    Image[] arrayDown = {walkDown0, walkDown0, standRight, standRight,
            walkDown0, walkDown0, standClear};

    Image walkLeft0 = new ImageIcon("boy/walkLeft0.png").getImage();
    Image walkLeft1 = new ImageIcon("boy/walkLeft1.png").getImage();
    Image walkLeft2 = new ImageIcon("boy/walkLeft2.png").getImage();
    Image walkLeft3 = new ImageIcon("boy/walkLeft3.png").getImage();
    Image walkLeft4 = new ImageIcon("boy/walkLeft4.png").getImage();
    Image walkLeft5 = new ImageIcon("boy/walkLeft5.png").getImage();
    Image walkLeft6 = new ImageIcon("boy/walkLeft6.png").getImage();
    Image[] arrayLeft = {walkLeft0, walkLeft1, walkLeft2, walkLeft3,
            walkLeft4, walkLeft5, walkLeft6};

    Image walkRight0 = new ImageIcon("boy/walkRight0.png").getImage();
    Image walkRight1 = new ImageIcon("boy/walkRight1.png").getImage();
    Image walkRight2 = new ImageIcon("boy/walkRight2.png").getImage();
    Image walkRight3 = new ImageIcon("boy/walkRight3.png").getImage();
    Image walkRight4 = new ImageIcon("boy/walkRight4.png").getImage();
    Image walkRight5 = new ImageIcon("boy/walkRight5.png").getImage();
    Image walkRight6 = new ImageIcon("boy/walkRight6.png").getImage();
    Image[] arrayRight = {walkRight0, walkRight1, walkRight2, walkRight3,
            walkRight4, walkRight5, walkRight6};

    Image shoveLeft = new ImageIcon("boy/shoveLeft.png").getImage();
    Image shoveRight = new ImageIcon("boy/shoveRight.png").getImage();
    Image[] arrayShoveLeft = {shoveLeft, shoveLeft, shoveLeft, standRight, shoveLeft,
            shoveLeft, standLeft};
    Image[] arrayShoveRight = {shoveRight, shoveRight, shoveRight, standRight,
            shoveRight, shoveRight, standRight};

    Image lookInChest = new ImageIcon("boy/lookInChest.png").getImage();
    Image cheer = new ImageIcon("boy/cheer.png").getImage();
    Image[] arrayFindInChest = {lookInChest, lookInChest, cheer, cheer, cheer, cheer, cheer};
    Image[] arrayOpenChest = {walkUp2, walkUp2, lookInChest, lookInChest, lookInChest, lookInChest, walkUp2};

    public Image imHoldARock = new ImageIcon("boy/holdARock.png").getImage();

    Image attackUp = new ImageIcon("boy/attackUp.png").getImage();
    Image[] arrayAttackUp = {walkUp2, attackUp, attackUp, attackUp, attackUp, attackUp, walkUp2};

    Image attackRight1 = new ImageIcon("boy/attackRight1.png").getImage();
    Image attackDown = new ImageIcon("boy/attackDown.png").getImage();
    Image[] arrayAttackDown = {attackRight1, attackRight1, attackDown, attackDown, attackDown, attackRight1, standClear};

    Image attackLeft0 = new ImageIcon("boy/attackLeft0.png").getImage();
    Image attackLeft1 = new ImageIcon("boy/attackLeft1.png").getImage();
    Image attackLeft2 = new ImageIcon("boy/attackLeft2.png").getImage();
    Image[] arrayAttackLeft = {attackLeft0, attackLeft0, attackLeft1, attackLeft1,
            attackLeft2, attackLeft2, standLeft};

    Image attackRight0 = new ImageIcon("boy/attackRight0.png").getImage();
    Image attackRight2 = new ImageIcon("boy/attackRight2.png").getImage();
    Image[] arrayAttackRight = {attackRight0, attackRight0, attackRight1, attackRight1,
            attackRight2, attackRight2, standRight};

    Image holdGoldKeyLeft = new ImageIcon("boy/holdGoldKeyLeft.png").getImage();
    Image[] arrayOpenWithGoldKeyLeft = {holdGoldKeyLeft, holdGoldKeyLeft,
            holdGoldKeyLeft, holdGoldKeyLeft, cheer, cheer, standLeft};

    Image holdGoldKeyRight = new ImageIcon("boy/holdGoldKeyRight.png").getImage();
    Image[] arrayOpenWithGoldKeyRight = {holdGoldKeyRight, holdGoldKeyRight,
            holdGoldKeyRight, holdGoldKeyRight, cheer, cheer, standRight};

    Image holdSilverKeyLeft = new ImageIcon("boy/holdSilverKeyLeft.png").getImage();
    Image[] arrayOpenWithSilverKeyLeft = {holdSilverKeyLeft, holdSilverKeyLeft,
            holdSilverKeyLeft, holdSilverKeyLeft, cheer, cheer, standLeft};

    Image holdSilverKeyRight = new ImageIcon("boy/holdSilverKeyRight.png").getImage();
    Image[] arrayOpenWithSilverKeyRight = {holdSilverKeyRight, holdSilverKeyRight,
            holdSilverKeyRight, holdSilverKeyRight, cheer, cheer, standRight};

    public Image currentPicture = standClear;

    /**
     * @param x
     * @param y
     */
    public Boy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    /**
     * One iteration of moving up
     * @param playPanel
     */
    public void moveUp(PlayPanel playPanel) {
        if (i == 3){
            yInArray --;
            playPanel.disappearFromCell(xInArray, yInArray + 1);
        }
        y -= cellSide / 7;
        currentPicture = arrayUp[i];
        i++;
    }

    /**
     * One iteration of moving up animation
     * @param playPanel
     * @param changeCoordinates
     */
    public void moveUpAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            yInArray --;
            playPanel.disappearFromCell(xInArray, yInArray + 1);
        }
        currentPicture = arrayUp[i];
        i++;
    }

    /**
     * One iteration of moving down
     * @param playPanel
     */
    public void moveDown(PlayPanel playPanel) {
        if (i == 3){
            yInArray ++;
            playPanel.disappearFromCell(xInArray, yInArray - 1);
        }
        y += cellSide / 7;
        currentPicture = arrayDown[i];
        i++;
    }

    /**
     * One iteration of moving down animation
     * @param playPanel
     * @param changeCoordinates
     */
    public void moveDownAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            yInArray ++;
            playPanel.disappearFromCell(xInArray, yInArray - 1);
        }
        currentPicture = arrayDown[i];
        i++;
    }

    /**
     * One iteration of moving left
     * @param playPanel
     */
    public void moveLeft(PlayPanel playPanel) {
        if (i == 3){
            xInArray --;
            if (xInArray != playPanel.levelMatrix.length-1) {
                playPanel.disappearFromCell(xInArray + 1, yInArray);
            }
        }
        x -= cellSide / 7;
        currentPicture = arrayLeft[i];
        i++;
    }

    /**
     * One iteration of moving left animation
     * @param playPanel
     * @param changeCoordinates
     */
    public void moveLeftAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            xInArray --;
            playPanel.disappearFromCell(xInArray + 1, yInArray);
        }
        currentPicture = arrayLeft[i];
        i++;
    }

    /**
     * One iteration of moving right
     * @param playPanel
     */
    public void moveRight(PlayPanel playPanel) {
        if (i == 3){
            xInArray ++;
            playPanel.disappearFromCell(xInArray - 1, yInArray);
        }
        x += cellSide / 7;
        currentPicture = arrayRight[i];
        i++;
    }

    /**
     * One iteration of moving right animation
     * @param playPanel
     * @param changeCoordinates
     */
    public void moveRightAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            xInArray ++;
            playPanel.disappearFromCell(xInArray - 1, yInArray);
        }
        currentPicture = arrayRight[i];
        i++;
    }

    /**
     * One iteration of shoving left and moving
     * @param playPanel
     */
    public void shoveLeftAndMove(PlayPanel playPanel){
        if (i == 3){
            xInArray --;
            playPanel.disappearFromCell(xInArray + 1, yInArray);
        }
        if (i == 0) x -= 3 * cellSide / 7;
        else if (i == 6) x += cellSide / 7;
        else x -= cellSide / 7;
        currentPicture = arrayShoveLeft[i];
        i++;
    }

    /**
     * One iteration of shoving left and standing
     * @param playPanel
     * @param changeCoordinates
     */
    public void shoveLeftAndStand(PlayPanel playPanel, boolean changeCoordinates){
        if (i == 3 && changeCoordinates){
            xInArray --;
            playPanel.disappearFromCell(xInArray + 1, yInArray);
        }
        if (i == 0) x -= 2 * cellSide / 7 ;
        else if (i == 6) x += 2 * cellSide / 7;
        currentPicture = arrayShoveLeft[i];
        i++;
    }

    /**
     * One iteration of shoving right and moving
     * @param playPanel
     */
    public void shoveRightAndMove(PlayPanel playPanel){
        if (i == 3){
            xInArray ++;
            playPanel.disappearFromCell(xInArray - 1, yInArray);
        }
        if (i == 0) x += 3 * cellSide / 7;
        else if (i == 6) x -= cellSide / 7;
        else x += cellSide / 7;
        currentPicture = arrayShoveRight[i];
        i++;
    }

    /**
     * One iteration of shoving right and standing
     * @param playPanel
     * @param changeCoordinates
     */
    public void shoveRightAndStand(PlayPanel playPanel, boolean changeCoordinates){
        if (i == 3 && changeCoordinates){
            xInArray ++;
            playPanel.disappearFromCell(xInArray - 1, yInArray);
        }
        if (i == 0) x += 2 * cellSide / 7 ;
        else if (i == 6) x -= 2 * cellSide / 7;
        currentPicture = arrayShoveRight[i];
        i++;
    }

    /**
     * One iteration of finding in Chest
     */
    public void findInChest(){
        currentPicture = arrayFindInChest[i];
        i++;
    }

    /**
     * One iteration of holding a rock
     */
    public void holdARock(){
        currentPicture = imHoldARock;
        i++;
    }

    /**
     * One iteration of attacking up
     */
    public void attackUp(){
        currentPicture = arrayAttackUp[i];
        i++;
    }

    /**
     * One iteration of attacking down
     */
    public void attackDown(){
        currentPicture = arrayAttackDown[i];
        i++;
    }

    /**
     * One iteration of attacking left
     */
    public void attackLeft(){
        currentPicture = arrayAttackLeft[i];
        i++;
    }

    /**
     * One iteration of attacking right
     */
    public void attackRight(){
        currentPicture = arrayAttackRight[i];
        i++;
    }

    /**
     * One iteration of opening with gold key left
     */
    public void openWithGoldKeyLeft(){
        currentPicture = arrayOpenWithGoldKeyLeft[i];
        i++;
    }

    /**
     * One iteration of opening with gold key right
     */
    public void openWithGoldKeyRight(){
        currentPicture = arrayOpenWithGoldKeyRight[i];
        i++;
    }

    /**
     * One iteration of opening with silver key left
     */
    public void openWithSilverKeyLeft(){
        currentPicture = arrayOpenWithSilverKeyLeft[i];
        i++;
    }

    /**
     * One iteration of opening with silver key right
     */
    public void openWithSilverKeyRight(){
        currentPicture = arrayOpenWithSilverKeyRight[i];
        i++;
    }

    /**
     * One iteration of opening chest
     */
    public void openChest(){
        currentPicture = arrayOpenChest[i];
        i++;
    }

    public void startHurtSound(PlayPanel playPanel){
        if(playPanel.getGameFrame().soundOn){
            hurtSound.start();
            Util.wait((int)hurtSound.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hurtSound.stop();
                    hurtSound.setFramePosition(0);
                }
            });
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}
