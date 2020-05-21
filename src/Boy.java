import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Boy extends JComponent implements KeyListener, ActionListener {

    private AnimatableImage walking = new AnimatableImage("boy/walkRight7.png");
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle rect;
    private int cellSide = Values.CELL_SIZE;
    private Timer timer;
    private PlayPanel playPanel;

    public Boy(PlayPanel playPanel, Timer t, int x, int y){
        this.playPanel = playPanel;
        timer = t;
        this.x = x;
        this.y = y;
        this.width = x + 50;
        this.height = y + 70;
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
        //g2d.setColor(Color.blue);
        g2d.scale(0.5, 0.5);
        //g.drawImage(walkRight.image,x,y,width,height,null);
        g2d.drawImage(walking.image,x,y,width,height,null);
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.scale(0.1, 0.1);
        //Rectangle2D re = new Rectangle2D.Double(x, y, width, height);
        //g2d.fill(re);
    }

    private void moveUp() {
        System.out.println("Up");
        y -= cellSide / 2;
        walking.setImage("boy/walkUp1.png");
        playPanel.repaint();
        Timer t = new Timer(200, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y -= cellSide / 2;
                walking.setImage("boy/walkUp0.png");
                playPanel.repaint();
                t.stop();
            }
        });
        t.start();
    }

    private void moveDown() {
        System.out.println("Down");
        y += cellSide / 2;
        walking.setImage("boy/walkDown1.png");
        playPanel.repaint();
        Timer t = new Timer(500, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y += cellSide / 2;
                walking.setImage("boy/walkDown0.png");
                playPanel.repaint();
                t.stop();
            }
        });
        t.start();
    }

    private void moveLeft() {
        System.out.println("Left");
        x -= cellSide / 8;
        walking.setImage("boy/walkLeft0.png");
        System.out.println(0);
        playPanel.repaint();
        Timer t0 = changeImage("boy/walkLeft1.png", false, null);
        Timer t1 = changeImage("boy/walkLeft2.png", false, t0);
        Timer t2 = changeImage("boy/walkLeft3.png", false, t1);
        Timer t3 = changeImage("boy/walkLeft4.png", false, t2);
        Timer t4 = changeImage("boy/walkLeft5.png", false, t3);
        Timer t5 = changeImage("boy/walkLeft6.png", false, t4);
        Timer t6 = changeImage("boy/walkLeft7.png", false, t5);
    }

    private void moveRight() {
        System.out.println("Right");
        x += cellSide / 8;
        walking.setImage("boy/walkRight0.png");
        System.out.println(0);
        playPanel.repaint();
        Timer t0 = changeImage("boy/walkRight1.png", true, null);
        Timer t1 = changeImage("boy/walkRight2.png", true, t0);
        Timer t2 = changeImage("boy/walkRight3.png", true, t1);
        Timer t3 = changeImage("boy/walkRight4.png", true, t2);
        Timer t4 = changeImage("boy/walkRight5.png", true, t3);
        Timer t5 = changeImage("boy/walkRight6.png", true, t4);
        Timer t6 = changeImage("boy/walkRight7.png", true, t5);
    }

    private Timer changeImage(String path, boolean right, Timer prev){
        playPanel.repaint();
        Timer t = new Timer(100, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (right) x += cellSide / 8;
                else x -= cellSide / 8;
                walking.setImage(path);
                playPanel.repaint();
                t.stop();
            }
        });
        if ((prev == null)||(!prev.isRunning())) {
            t.start();
        }
        return t;
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

