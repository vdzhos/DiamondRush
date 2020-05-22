import javax.swing.*;
import java.awt.*;

public class Boy {

    int x;
    int y;
    int width;
    int height;
    int cellSide = Values.CELL_SIZE;
    int whatMove = 0; //Stand = 0; Up = 1; Down = 2; Left = 3; Right = 4;
    //shoveLeftAndMove = 5; shoveLeftAndStand = 6; shoveRightAndMove = 7; shoveRightAndStand = 8;
    //findInChest = 9; holdAStone = 10; attackLeft = 11; attackRight = 12
    boolean isMoving = false;
    int i = 0;

    Image walkUp0 = new ImageIcon("boy/walkUp0.png").getImage();
    Image walkUp1 = new ImageIcon("boy/walkUp1.png").getImage();
    Image walkUp2 = new ImageIcon("boy/walkUp2.png").getImage();
    Image[] arrayUp = {walkUp0, walkUp0, walkUp1, walkUp1,
            walkUp0, walkUp0, walkUp2};

    Image walkDown0 = new ImageIcon("boy/walkDown0.png").getImage();
    Image standRight = new ImageIcon("boy/standRight.png").getImage();
    Image[] arrayDown = {walkDown0, walkDown0, standRight, standRight,
            walkDown0, walkDown0, standRight};

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
            shoveLeft, standRight};
    Image[] arrayShoveRight = {shoveRight, shoveRight, shoveRight, standRight,
            shoveRight, shoveRight, standRight};

    Image lookInChest = new ImageIcon("boy/lookInChest.png").getImage();
    Image cheer = new ImageIcon("boy/cheer.png").getImage();
    Image[] arrayFindInChest = {lookInChest, lookInChest, lookInChest, cheer, cheer, cheer, standRight};

    Image imHoldAStone = new ImageIcon("boy/holdAStone.png").getImage();

    Image attackLeft0 = new ImageIcon("boy/attackLeft0.png").getImage();
    Image attackLeft1 = new ImageIcon("boy/attackLeft1.png").getImage();
    Image attackLeft2 = new ImageIcon("boy/attackLeft2.png").getImage();
    Image[] arrayAttackLeft = {attackLeft0, attackLeft0, attackLeft1, attackLeft1,
            attackLeft2, attackLeft2, standRight};

    Image attackRight0 = new ImageIcon("boy/attackRight0.png").getImage();
    Image attackRight1 = new ImageIcon("boy/attackRight1.png").getImage();
    Image attackRight2 = new ImageIcon("boy/attackRight2.png").getImage();
    Image standLeft = new ImageIcon("boy/standLeft.png").getImage();
    Image[] arrayAttackRight = {attackRight0, attackRight0, attackRight1, attackRight1,
            attackRight2, attackRight2, standLeft};

    Image currentPicture = walkRight6;

    public Boy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    public void moveUp() {
        y -= cellSide / 7;
        currentPicture = arrayUp[i];
        i++;
    }

    public void moveDown() {
        y += cellSide / 7;
        currentPicture = arrayDown[i];
        i++;
    }

    public void moveLeft() {
        x -= cellSide / 7;
        currentPicture = arrayLeft[i];
        i++;
    }

    public void moveRight() {
        x += cellSide / 7;
        currentPicture = arrayRight[i];
        i++;
    }

    public void shoveLeftAndMove(){
        x -= cellSide / 7;
        currentPicture = arrayShoveLeft[i];
        i++;
    }

    public void shoveLeftAndStand(){
         currentPicture = arrayShoveLeft[i];
         i++;
    }

    public void shoveRightAndMove(){
        x += cellSide / 7;
        currentPicture = arrayShoveRight[i];
        i++;
    }

    public void shoveRightAndStand(){
        currentPicture = arrayShoveRight[i];
        i++;
    }

    public void findInChest(){
        currentPicture = arrayFindInChest[i];
        i++;
    }

    public void holdAStone(){
        currentPicture = imHoldAStone;
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
}
