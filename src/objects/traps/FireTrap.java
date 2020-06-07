package objects.traps;

import maps.Cell;
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

public class FireTrap extends JLabel implements Trap{

    private Timer timer;
    private Timer check;
    private Image[] images;
    private boolean[] state = {false,false,false};
    private boolean turningOn = true;
    private JLabel fireTrap;
    private int energy = 25;
    private boolean side; //right - true; left - false
    private  boolean paused;
    public static boolean fireTrapSound = false;
    public static ArrayList<FireTrap> fireTrapsObserved;
    private static Clip fireTrapClip = Util.getSound("sounds/fire_burning.wav",-35f);
    public static Timer fireTrapClipTimer;

    private void initTimer(){
        fireTrapClipTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eTimer) {
                Timer t = (Timer)eTimer.getSource();
                if(!fireTrapSound){
                    fireTrapClip.stop();
                    fireTrapClip.setFramePosition(0);
                    t.stop();
                }else{
                    boolean atLeastOneWorking = false;
                    for (FireTrap fireTrap:fireTrapsObserved) {
                        if(!FireTrap.notWorking(fireTrap)){
                            atLeastOneWorking = true;
                            break;
                        }
                    }
                    if(!atLeastOneWorking){
                        fireTrapClip.stop();
                        fireTrapClip.setFramePosition(0);
                    }else if(!fireTrapClip.isActive()){
                        fireTrapClip.start();
                    }
                }
            }
        });
    }

    private void initImages(){
        Image imageRight = new ImageIcon("fireTrap/fireballRight1.png").getImage();
        Image imageLeft = new ImageIcon("fireTrap/fireballLeft1.png").getImage();
        Image anubisRight = new ImageIcon("fireTrap/anubisRight.png").getImage();
        Image anubisLeft = new ImageIcon("fireTrap/anubisLeft.png").getImage();
        Image[] images = {imageRight, imageLeft,anubisRight,anubisLeft};
        this.images = images;
    }

    public FireTrap(int initState, boolean side){
        initTimer();
        initImages();
        fireTrap = this;
        setPreferredSize(new Dimension(280,70));
        this.side = side;
        for (int i = 0; i < initState-1; i++) {
            state[i] = true;
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireTrap.repaint();
                if(turningOn){
                    for (int i = 0; i < 3; i++) {
                        if(!state[i]){
                            state[i] = true;
                            if(i==2){
                                turningOn=false;
                            }
                            break;
                        }
                    }
                }else{
                    for (int i = 0; i < 3; i++) {
                        if(state[i]){
                            state[i] = false;
                            if(i==2){
                                turningOn=true;
                            }
                            break;
                        }
                    }
                }
            }
        });
        revalidate();
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(side){ //beginning right
            g2.drawImage(images[3],210,0,70,70,null);
            for (int i = 3; i > 0; i--) {
                if(state[i-1]){
                    g2.drawImage(images[1], 210-i*70,0, 70,70,null);
                }
            }
        }else{ //beginning left
            g2.drawImage(images[2],0,0,70,70,null);
            for (int i = 1; i < 4; i++) {
                if(state[i-1]){
                    g2.drawImage(images[0], i*70,0, 70,70,null);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
        FireTrap fireTrap = new FireTrap(2,true);
        frame.add(fireTrap);
        fireTrap.timer.start();
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
        paused = true;
        timer.stop();
        if (check!=null)
            check.stop();
    }

    @Override
    public void resume() {
        paused = false;
        timer.start();
    }

    @Override
    public void checkTimerStart(PlayPanel panel, Boy boy, Cell[][] levelMatrix){
        int side = 0;
        if(!this.side){
            side = 70;
        }
        int finalSide = side;
        check = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle boyRect = new Rectangle(boy.getX(),boy.getY(),70,70);
                int first;
                int quantity = 0;
                if(finalSide == 70){
                    first = -1;
                    for (int i = 0; i < state.length; i++) {
                        if(state[i]){
                            if(first==-1){
                                first = i;
                            }
                            quantity++;
                        }
                    }
                }else{
                    first = -1;
                    for (int i = 2; i >=0; i--) {
                        if(state[i]){
                            if(first==-1){
                                first = 2-i;
                            }
                            quantity++;
                        }
                    }
                }
                Rectangle fireTrapRect = new Rectangle(finalSide +first*70 + fireTrap.getX(), fireTrap.getY(),quantity*70,70);
                System.out.println("-------------");
                boolean intersects = boyRect.intersects(fireTrapRect);
                if(quantity==0){
                    intersects=false;
                }
                if(intersects && !boy.gotInTrap){
                    panel.takeEnergy(energy);
                    boy.startHurtSound();
                    boy.gotInTrap = true;
                    if(panel.currentEnergyLevel>0) {
                        Util.wait(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boy.gotInTrap = false;
                                if (fireTrap.equals(levelMatrix[boy.xInArray][boy.yInArray].getTrapObject())) {
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

    public boolean isHead(Boy boy, int shift){
        if(side){
            if(boy.getX()+shift==fireTrap.getX()+210){
                System.out.println((boy.getX()+shift)+ " | " + (fireTrap.getX()+210));
                return true;
            }else{
                System.out.println((boy.getX()+shift)+ " | " + (fireTrap.getX()+210));
                return false;
            }
        }else{
            if(boy.getX()+shift==fireTrap.getX()){
                System.out.println((boy.getX()+shift)+ " | " + (fireTrap.getX()));
                return true;
            }else{
                System.out.println((boy.getX()+shift)+ " | " + (fireTrap.getX()));
                return false;
            }
        }
    }

    public void setCheckTimer(Timer check) {
        this.check = check;
    }

    private static boolean notWorking(FireTrap fireTrap){
        for (int i = 0; i < 3; i++) {
            if(fireTrap.state[i]){
                return false;
            }
        }
        return true;
    }

}
