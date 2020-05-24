package objects.traps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scorpion extends JLabel implements Trap{

    private Timer timer;
    private int x = 0;
    private int y = 0;
    private JLabel scorpion;
    private boolean hor; // true - horizontal; false - vertical
    private boolean side; //true - right(down); false - left(up)

    public Scorpion(int width, int height, boolean clockwise){
        scorpion = this;
        setPreferredSize(new Dimension(width,height));
        if(clockwise) {
            hor = true;
            side = true;
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(side){
                        if(hor){
                            x+=5;
                            if(x+70>=width){
                                hor = false;
                            }
                        }else{
                            y+=5;
                            if(y+70>=height){
                                hor = true;
                                side = false;
                            }
                        }
                    }else{
                        if(hor){
                            x-=5;
                            if(x<=0){
                                hor = false;
                            }
                        }else{
                            y-=5;
                            if(y<=0){
                                hor = true;
                                side = true;
                            }
                        }
                    }
                    scorpion.repaint();
                }
            });
        }else{
            hor = false;
            side = true;
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(side){
                        if(hor){
                            x+=5;
                            if(x+70>=width){
                                hor = false;
                                side = false;
                            }
                        }else{
                            y+=5;
                            if(y+70>=height){
                                hor = true;
                            }
                        }
                    }else{
                        if(hor){
                            x-=5;
                            if(x<=0){
                                hor = false;
                                side = true;
                            }
                        }else{
                            y-=5;
                            if(y<=0){
                                hor = true;
                            }
                        }
                    }
                    scorpion.repaint();
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
        g2.setColor(Color.BLACK);
        g2.fillRect(x,y,70,70);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        Scorpion scorpion = new Scorpion(350,350,true);
        frame.add(scorpion);
        scorpion.timer.start();
    }

    @Override
    public void paintObject(Graphics2D g2, int mapX, int mapY) {

    }

    @Override
    public JLabel getLabel() {
        return this;
    }

}
