package objects.traps;

import maps.Cell;
import source.Boy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JLabel implements Trap{

    private Timer timer;
    private Timer check;
    private int x = 0;
    private int y = 0;
    private JLabel snake;
    private boolean side = true; //right - true; left - false
    private Image[] images;
    private Image current;
    private Snake trap;

    private void initImages(){
        Image imageRight = new ImageIcon("snake/snakeRight.png").getImage();
        Image imageLeft = new ImageIcon("snake/snakeLeft.png").getImage();
        Image imageUp = new ImageIcon("snake/snakeUp.png").getImage();
        Image imageDown = new ImageIcon("snake/snakeDown.png").getImage();
        Image[] images = {imageRight, imageLeft,imageUp,imageDown};
        this.images = images;
    }

    public Snake(int width, int height, int coord, boolean horizontal){
        trap = this;
        initImages();
        snake = this;
        setPreferredSize(new Dimension(width,height));
        if(horizontal) {
            this.x = coord;
            timer = new Timer(15, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Timer t = (Timer)e.getSource();
                    t.setDelay(15);
                    if (side) {
                        x += 1;
                        current = images[0];
                        if (x+70 >= width) {
                            side = false;
                            current = images[1];
                            t.setDelay(1000);
                            x++;
                        }
                    } else {
                        x -= 1;
                        current = images[1];
                        if (x <= 0) {
                            side = true;
                            current = images[0];
                            t.setDelay(1000);
                            x--;
                        }
                    }
                    snake.repaint();
                }
            });
        }else{
            this.y = coord;
            timer = new Timer(15, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Timer t = (Timer)e.getSource();
                    t.setDelay(15);
                    if (side) {
                        y += 1;
                        current = images[3];
                        if (y+70 >= height) {
                            side = false;
                            current = images[2];
                            t.setDelay(1000);
                            y++;
                        }
                    } else {
                        y -= 1;
                        current = images[2];
                        if (y <= 0) {
                            side = true;
                            current = images[3];
                            t.setDelay(1000);
                            y--;
                        }
                    }
                    snake.repaint();
                }
            });
        }
        revalidate();
        timer.start();
    }

    public void checkTimerStart(JPanel panel, Boy boy, Cell[][] levelMatrix){
        check = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle boyRect = new Rectangle(boy.getX(),boy.getY(),70,70);
                Rectangle snakeRect = new Rectangle(x+snake.getX(),y+snake.getY(),70,70);
                System.out.println("----------");
                if(boyRect.intersects(snakeRect)){
                    panel.remove(snake);
                    Timer t = (Timer) e.getSource();
                    t.stop();
                    for (int i = 0; i < levelMatrix.length; i++) {
                        for (int j = 0; j < levelMatrix[i].length; j++) {
                            if(levelMatrix[i][j].getTrapObject() instanceof Snake){
                                if(levelMatrix[i][j].getTrapObject().equals(trap)){
                                    levelMatrix[i][j].setTrapObject(null);
                                }
                            }
                        }
                    }
                    panel.repaint();
                }
            }
        });
        check.start();
    }

    public Timer getCheckTimer(){
        return check;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(current,x,y,70,70,null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        Snake snake = new Snake(100,300, 50,true);
        frame.add(snake);
        snake.timer.start();
    }

    @Override
    public void paintObject(Graphics2D g2, int mapX, int mapY) {

    }

    @Override
    public void paintObject(Graphics2D g2) {

    }

    @Override
    public JLabel getLabel() {
        return this;
    }

}
