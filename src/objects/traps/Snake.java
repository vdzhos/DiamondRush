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
    private boolean side = true; //right - true; left - false
    private Image[] images;
    private Image current;

    private void initImages(){
        Image imageRight = new ImageIcon("snake/snakeRight.png").getImage();
        Image imageLeft = new ImageIcon("snake/snakeLeft.png").getImage();
        Image[] images = {imageRight, imageLeft};
        this.images = images;
    }

    public Snake(int width, int height, boolean horizontal){
        initImages();
        snake = this;
        setPreferredSize(new Dimension(width,height));
        if(horizontal) {
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (side) {
                        x += 1;
                        current = images[0];
                        if (x+70 >= width) {
                            side = false;
                        }
                    } else {
                        x -= 1;
                        current = images[1];
                        if (x <= 0) {
                            side = true;
                        }
                    }
                    snake.repaint();
                }
            });
        }else{
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (side) {
                        y += 1;
                        if (y+70 >= height) {
                            side = false;
                        }
                    } else {
                        y -= 1;
                        if (y <= 0) {
                            side = true;
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
        g2.drawImage(current,x,y,70,70,null);
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
    public JLabel getLabel() {
        return this;
    }

}
