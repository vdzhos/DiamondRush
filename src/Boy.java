import javax.swing.*;
import java.awt.*;

public class Boy {

    int x;
    int y;
    int width;
    int height;
    int cellSide = Values.CELL_SIZE;
    int whatMove = 0; //Stand = 0; Up = 1; Down = 2; Left = 3; Right = 4;

    int iU = 0;
    int iD = 0;
    int iR = 0;
    int iL = 0;

    Image walkUp0 = new ImageIcon("boy/walkUp0.png").getImage();
    Image walkUp1 = new ImageIcon("boy/walkUp1.png").getImage();
    Image walkUp2 = new ImageIcon("boy/walkUp2.png").getImage();
    Image[] arrayUp = {walkUp0, walkUp1, walkUp0, walkUp1, walkUp2};

    Image walkDown0 = new ImageIcon("boy/walkDown0.png").getImage();
    Image walkDown1 = new ImageIcon("boy/walkDown1.png").getImage();
    Image[] arrayDown = {walkDown0, walkDown1, walkDown0, walkDown1};

    Image walkLeft0 = new ImageIcon("boy/walkLeft0.png").getImage();
    Image walkLeft1 = new ImageIcon("boy/walkLeft1.png").getImage();
    Image walkLeft2 = new ImageIcon("boy/walkLeft2.png").getImage();
    Image walkLeft3 = new ImageIcon("boy/walkLeft3.png").getImage();
    Image walkLeft4 = new ImageIcon("boy/walkLeft4.png").getImage();
    Image walkLeft5 = new ImageIcon("boy/walkLeft5.png").getImage();
    Image walkLeft6 = new ImageIcon("boy/walkLeft6.png").getImage();
    Image walkLeft7 = new ImageIcon("boy/walkLeft7.png").getImage();
    Image[] arrayLeft = {walkLeft0, walkLeft1, walkLeft2, walkLeft3,
            walkLeft4, walkLeft5, walkLeft6, walkLeft7};

    Image walkRight0 = new ImageIcon("boy/walkRight0.png").getImage();
    Image walkRight1 = new ImageIcon("boy/walkRight1.png").getImage();
    Image walkRight2 = new ImageIcon("boy/walkRight2.png").getImage();
    Image walkRight3 = new ImageIcon("boy/walkRight3.png").getImage();
    Image walkRight4 = new ImageIcon("boy/walkRight4.png").getImage();
    Image walkRight5 = new ImageIcon("boy/walkRight5.png").getImage();
    Image walkRight6 = new ImageIcon("boy/walkRight6.png").getImage();
    Image walkRight7 = new ImageIcon("boy/walkRight7.png").getImage();
    Image[] arrayRight = {walkRight0, walkRight1, walkRight2, walkRight3,
            walkRight4, walkRight5, walkRight6, walkRight7};

    Image shoveRight = new ImageIcon("boy/shoveRight.png").getImage();
    Image shoveLeft = new ImageIcon("boy/shoveLeft.png").getImage();

    Image currentPicture = walkRight7;

    public Boy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    public void moveUp() {
        y -= cellSide / 4;
        currentPicture = arrayUp[iU];
        iU++;
    }

    public void moveDown() {
        y += cellSide / 4;
        currentPicture = arrayDown[iD];
        iD++;
    }

    public void moveLeft() {
        x -= cellSide / 7;
        currentPicture = arrayLeft[iL];
        iL++;
    }

    public void moveRight() {
        x += cellSide / 7;
        currentPicture = arrayRight[iR];
        iR++;
    }
}
