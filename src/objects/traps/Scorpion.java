package objects.traps;

import maps.Cell;
import objects.Direction;
import objects.Stone;
import source.Boy;
import source.PlayPanel;
import source.Util;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Scorpion extends JLabel implements Trap{

    private Timer timer;
    private Timer check;
    private int x = 0;
    private int y = 0;
    private JLabel scorpion;
    private boolean hor; // true - horizontal; false - vertical
    private boolean side; //true - right(down); false - left(up)
    private Direction direction;
    private int energy = 30;
    private Image[] images;
    private boolean paused;
    public static boolean scorpionSound = false;
    private static Clip scorpionClip = Util.getSound("sounds/scorpion_crawl.wav",-35f);
    public static Timer scorpionClipTimer;

    private void initTimer(){
        scorpionClipTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eTimer) {
                Timer t = (Timer)eTimer.getSource();
                if(!scorpionSound){
                    scorpionClip.stop();
                    scorpionClip.setFramePosition(0);
                    t.stop();
                }else{
                    if(!scorpionClip.isActive()){
                        scorpionClip.start();
                    }
                }
            }
        });
    }

    private void initImages(){
        Image imageRight = new ImageIcon("scorpion/right0.png").getImage();
        Image imageDown = new ImageIcon("scorpion/down0.png").getImage();
        Image imageLeft = new ImageIcon("scorpion/left0.png").getImage();
        Image imageUp = new ImageIcon("scorpion/up0.png").getImage();
        Image[] images = {imageRight, imageDown, imageLeft, imageUp};
        this.images = images;
    }

    public Scorpion(int width, int height, boolean clockwise, Point point, Direction dir){
        initTimer();
        initImages();
        scorpion = this;
        setPreferredSize(new Dimension(width,height));
        if(point != null && dir != null){
            x = point.x;
            y = point.y;
            if(point.x==0 || point.x==width-70){
                hor = false;
            }else{
                hor = true;
            }
            if(dir==Direction.RIGHT||dir==Direction.DOWN){
                side = true;
            }else{
                side = false;
            }
            direction = dir;
        }
        if(clockwise) {
            if(point == null && direction == null){
                direction = Direction.RIGHT;
                hor = true;
                side = true;
            }
            timer = new Timer(12, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(side){
                        if(hor){
                            x+=2;
                            if(x+70>=width){
                                hor = false;
                                direction = Direction.DOWN;
                            }
                        }else{
                            y+=2;
                            if(y+70>=height){
                                hor = true;
                                side = false;
                                direction = Direction.LEFT;
                            }
                        }
                    }else{
                        if(hor){
                            x-=2;
                            if(x<=0){
                                hor = false;
                                direction = Direction.UP;
                            }
                        }else{
                            y-=2;
                            if(y<=0){
                                hor = true;
                                side = true;
                                direction = Direction.RIGHT;
                            }
                        }
                    }
                    scorpion.repaint();
                }
            });
        }else{
            if(point == null && direction == null) {
                direction = Direction.DOWN;
                hor = false;
                side = true;
            }
            timer = new Timer(12, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(side){
                        if(hor){
                            x+=2;
                            if(x+70>=width){
                                hor = false;
                                side = false;
                                direction = Direction.UP;
                            }
                        }else{
                            y+=2;
                            if(y+70>=height){
                                hor = true;
                                direction = Direction.RIGHT;
                            }
                        }
                    }else{
                        if(hor){
                            x-=2;
                            if(x<=0){
                                hor = false;
                                side = true;
                                direction = Direction.DOWN;
                            }
                        }else{
                            y-=2;
                            if(y<=0){
                                hor = true;
                                direction = Direction.LEFT;
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
        if(direction == Direction.RIGHT){
            g2.drawImage(images[0],x,y,70,70,null);
        }else if(direction == Direction.DOWN){
            g2.drawImage(images[1],x,y,70,70,null);
        }else if(direction == Direction.LEFT){
            g2.drawImage(images[2],x,y,70,70,null);
        }else {
            g2.drawImage(images[3],x,y,70,70,null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        Scorpion scorpion = new Scorpion(350,350,true, new Point(280,200), Direction.DOWN);
        JLabel label = scorpion.getLabel();
        label.setBounds(0,0,350,350);
        frame.add(label);
        scorpion.timer.start();
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

    @Override
    public void pause() {
        paused=true;
        timer.stop();
        if (check!=null)
            check.stop();
    }

    @Override
    public void resume() {
        paused=false;
        timer.start();
    }

    @Override
    public void checkTimerStart(PlayPanel panel, Boy boy, Cell[][] levelMatrix){
        check = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("-----------");
                    Rectangle boyRect = new Rectangle(boy.getX(), boy.getY(), 70, 70);
                    Rectangle scorpionRect = new Rectangle(x + scorpion.getX(), y + scorpion.getY(), 70, 70);

                    if (boyRect.intersects(scorpionRect) && !boy.gotInTrap) {
                        panel.takeEnergy(energy);
                        boy.startHurtSound();
                        boy.gotInTrap = true;
                        if(panel.currentEnergyLevel>0) {
                            Util.wait(5000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    boy.gotInTrap = false;
                                    if (scorpion.equals(levelMatrix[boy.xInArray][boy.yInArray].getTrapObject())) {
                                        if(!check.isRunning()){
                                            check.start();
                                        }
                                    }
                                }
                            });
                        }
                        check.stop();
                }
            }
        });
        check.start();
    }

    public Timer getCheckTimer(){
        return check;
    }

    public void setCheckTimer(Timer check) {
        this.check = check;
    }

}
