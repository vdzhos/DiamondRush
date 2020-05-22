import javax.swing.*;
import java.awt.*;

public class BoyNotGraphical {

    int x;
    int y;
    int width;
    int height;
    int cellSide = Values.CELL_SIZE;
    Image walkUp0 = new ImageIcon("boy/walkUp0.png").getImage();
    Image walkUp1 = new ImageIcon("boy/walkUp1.png").getImage();

    public BoyNotGraphical(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 70;
    }

    private void moveUp() {
        y -= cellSide;
    }

    private void moveDown() {
        y += cellSide;
    }

    private void moveLeft() {
        x -= cellSide;
    }

    private void moveRight() {
        x += cellSide;
    }
}
