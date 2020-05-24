package objects.traps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JLabel implements Trap{

    private Timer timer;
    private int x = 0;
    private int y = 0;
    private JLabel snake;
    private boolean movingRight = true;

    public Snake(int width, int height, boolean horizontal){
        snake = this;
        setPreferredSize(new Dimension(width,height));
        setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        if(horizontal) {
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (movingRight) {
                        x += 1;
                        if (x+70 >= width) {
                            movingRight = false;
                        }
                    } else {
                        x -= 1;
                        if (x <= 0) {
                            movingRight = true;
                        }
                    }
                    snake.repaint();
                }
            });
        }else{
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (movingRight) {
                        y += 1;
                        if (y+70 >= height) {
                            movingRight = false;
                        }
                    } else {
                        y -= 1;
                        if (y <= 0) {
                            movingRight = true;
                        }
                    }
                    snake.repaint();
                }
            });
        }
        revalidate();
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.fillRect(x,y,70,70);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        Snake snake = new Snake(100,300,true);
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
    public void changeState() {

    }

    @Override
    public JLabel getLabel() {
        return this;
    }

    public Timer getTimer() {
        return timer;
    }
}
