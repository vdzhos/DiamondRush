package objects.traps;

import maps.Cell;
import source.Boy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireTrap extends JLabel implements Trap{

    private Timer timer;
    private Timer check;
    private Image[] images;
    private boolean[] state = {false,false,false};
    private boolean turningOn = true;
    private JLabel fireTrap;
    private boolean side; //right - true; left - false

    private void initImages(){
        Image imageRight = new ImageIcon("fireTrap/fireballRight1.png").getImage();
        Image imageLeft = new ImageIcon("fireTrap/fireballLeft1.png").getImage();
        Image anubisRight = new ImageIcon("fireTrap/anubisRight.png").getImage();
        Image anubisLeft = new ImageIcon("fireTrap/anubisLeft.png").getImage();
        Image[] images = {imageRight, imageLeft,anubisRight,anubisLeft};
        this.images = images;
    }

    public FireTrap(int initState, boolean side){
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
                    g2.drawImage(images[1], 210-(4-i)*70,0, 70,70,null);
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
    public void checkTimerStart(JPanel panel, Boy boy, Cell[][] levelMatrix){
        int side = 0;
        if(!this.side){
            side = 70;
        }
        int finalSide = side;
        check = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle boyRect = new Rectangle(boy.getX(),boy.getY(),70,70);
                int first = -1;
                int quantity = 0;
                for (int i = 0; i < state.length; i++) {
                    if(state[i]){
                        if(first==-1){
                            first = i;
                        }
                        quantity++;
                    }
                }
                Rectangle fireTrapRect = new Rectangle(finalSide +first*70 + fireTrap.getX(), fireTrap.getY(),quantity*70,70);
                System.out.println("----------");
                boolean intersects = boyRect.intersects(fireTrapRect);
                if(quantity==0){
                    intersects=false;
                }
                if(intersects){
                    panel.remove(fireTrap);
                    Timer t = (Timer) e.getSource();
                    t.stop();
                    for (int i = 0; i < levelMatrix.length; i++) {
                        for (int j = 0; j < levelMatrix[i].length; j++) {
                            if(levelMatrix[i][j].getTrapObject() instanceof FireTrap){
                                if(levelMatrix[i][j].getTrapObject().equals(fireTrap)){
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


}
