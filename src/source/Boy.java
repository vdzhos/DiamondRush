package source;

import javax.swing.*;
import java.awt.*;

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
    public boolean isMoving = false;
    int i = 0;

    Image standLeft = new ImageIcon("boy/standLeft.png").getImage();
    Image standRight = new ImageIcon("boy/standRight.png").getImage();
    Image standClear = new ImageIcon("boy/standClear.png").getImage();

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
    Image[] arrayFindInChest = {lookInChest, lookInChest, lookInChest, cheer, cheer, cheer, cheer};
    //Question about stand clear

    Image imHoldARock = new ImageIcon("boy/holdARock.png").getImage();

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

    Image currentPicture = standClear;

    public Boy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    public void moveUp(PlayPanel playPanel) {
        if (i == 3){
            yInArray --;
            playPanel.disappearFromCell(xInArray, yInArray + 1);
        }
        y -= cellSide / 7;
        currentPicture = arrayUp[i];
        i++;
    }

    public void moveUpAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            yInArray --;
            playPanel.disappearFromCell(xInArray, yInArray + 1);
        }
        currentPicture = arrayUp[i];
        i++;
    }

    public void moveDown(PlayPanel playPanel) {
        if (i == 3){
            yInArray ++;
            playPanel.disappearFromCell(xInArray, yInArray - 1);
        }
        y += cellSide / 7;
        currentPicture = arrayDown[i];
        i++;
    }

    public void moveDownAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            yInArray ++;
            playPanel.disappearFromCell(xInArray, yInArray - 1);
        }
        currentPicture = arrayDown[i];
        i++;
    }

    public void moveLeft(PlayPanel playPanel) {
        if (i == 3){
            xInArray --;
            playPanel.disappearFromCell(xInArray + 1, yInArray);
        }
        x -= cellSide / 7;
        currentPicture = arrayLeft[i];
        i++;
    }

    public void moveLeftAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            xInArray --;
            playPanel.disappearFromCell(xInArray + 1, yInArray);
        }
        currentPicture = arrayLeft[i];
        i++;
    }

    public void moveRight(PlayPanel playPanel) {
        if (i == 3){
            xInArray ++;
            playPanel.disappearFromCell(xInArray - 1, yInArray);
        }
        x += cellSide / 7;
        currentPicture = arrayRight[i];
        i++;
    }

    public void moveRightAnimation(PlayPanel playPanel, boolean changeCoordinates) {
        if (i == 3 && changeCoordinates){
            xInArray ++;
            playPanel.disappearFromCell(xInArray - 1, yInArray);
        }
        currentPicture = arrayRight[i];
        i++;
    }

    public void shoveLeftAndMove(PlayPanel playPanel){
        if (i == 3){
            xInArray --;
            playPanel.disappearFromCell(xInArray + 1, yInArray);
        }
        if (i == 0) x -= 3 * cellSide / 7;
        else if (i == 6) x += cellSide / 7;
        else x -= cellSide / 7;
        currentPicture = arrayShoveLeft[i];
    }

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

    public void findInChest(){
        currentPicture = arrayFindInChest[i];
        i++;
    }

    public void holdARock(){
        currentPicture = imHoldARock;
        i++;
    }

    public void attackUp(){
        currentPicture = arrayAttackUp[i];
        i++;
    }

    public void attackDown(){
        currentPicture = arrayAttackDown[i];
        i++;
    }

    public void attackLeft(){
        currentPicture = arrayAttackLeft[i];
        i++;
    }

    public void attackRight(){
        currentPicture = arrayAttackRight[i];
        i++;
    }

    public void openWithGoldKeyLeft(){
        currentPicture = arrayOpenWithGoldKeyLeft[i];
        i++;
    }

    public void openWithGoldKeyRight(){
        currentPicture = arrayOpenWithGoldKeyRight[i];
        i++;
    }

    public void openWithSilverKeyLeft(){
        currentPicture = arrayOpenWithSilverKeyLeft[i];
        i++;
    }

    public void openWithSilverKeyRight(){
        currentPicture = arrayOpenWithSilverKeyRight[i];
        i++;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}
