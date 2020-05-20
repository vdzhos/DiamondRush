import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Boy implements KeyListener {

    private Image turnRight;
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle rect;
    private int cellSide = 80;

    public Boy(){
        String filename = "...";
        try {
            Image image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_UP){
            moveUp();
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            moveDown();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            moveLeft();
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }


    private void moveUp() {
        rect.setBounds(x, y -= cellSide, width, height);
        //rect.repaint();
    }

    private void moveDown() {
        rect.setBounds(x, y += cellSide, width, height);
        //rect.repaint();
    }

    private void moveLeft() {
        rect.setBounds(x -= cellSide, y, width, height);
        //rect.repaint();
    }

    private void moveRight() {
        rect.setBounds(x += cellSide, y, width, height);
        //rect.repaint();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}

