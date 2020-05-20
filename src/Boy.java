import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class Boy extends JComponent implements KeyListener, ActionListener {

    private Image turnRight;
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle rect;
    private int cellSide = Values.CELL_SIZE;
    private Timer timer;

    public Boy(){
        timer = new Timer(20, this);
        this.x = Values.MAIN_WINDOW_WIDTH / 2;
        this.y = Values.MAIN_WINDOW_LENGTH / 2;
        this.width = x + 50;
        this.height = y + 40;
        String filename = "boy/walk0.png";
        try {
            turnRight = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        //g2d.drawRect(x, y, width, height);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.scale(0.1, 0.1);
        Rectangle2D re = new Rectangle2D.Double(x, y, width, height);
        g2d.fill(re);
    }

    private void moveUp() {
        //rect.setBounds(x, y -= cellSide, width, height);
        System.out.println("Up");
        y -= cellSide;
        repaint();
    }

    private void moveDown() {
        //rect.setBounds(x, y += cellSide, width, height);
        System.out.println("Down");
        y += cellSide;
        repaint();
    }

    private void moveLeft() {
        //rect.setBounds(x -= cellSide, y, width, height);
        System.out.println("Left");
        x -= cellSide;
        repaint();
    }

    private void moveRight() {
        //rect.setBounds(x += cellSide, y, width, height);
        System.out.println("Right");
        x += cellSide;
        repaint();
    }

    public void start() {
        timer.start();
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

