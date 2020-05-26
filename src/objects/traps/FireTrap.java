package objects.traps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireTrap extends JLabel implements Trap{

    private Timer timer;
    private Image[] images;
    private boolean[] state = {false,false,false};
    private boolean turningOn = true;
    private JLabel fireTrap;
    private boolean side; //right - true; left - false

    private void initImages(){
        Image imageRight = new ImageIcon("fireTrap/fireballRight1.png").getImage();
        Image imageDown = new ImageIcon("fireTrap/fireballLeft1.png").getImage();
        Image[] images = {imageRight, imageDown};
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
        g2.setColor(Color.BLACK);
        if(side){ //right
            g2.fillRect(210,0,70,70);
            g2.setColor(Color.RED);
            for (int i = 3; i > 0; i--) {
                if(state[i-1]){
                    g2.drawImage(images[1], 210-(4-i)*70,0, 70,70,null);
                }
            }
        }else{ //left
            g2.fillRect(0,0,70,70);
            g2.setColor(Color.RED);
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

}
