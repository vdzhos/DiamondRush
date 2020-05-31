package objects.traps;

import maps.Cell;
import objects.Direction;
import objects.Stone;
import source.Boy;
import source.PlayPanel;
import source.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private void initImages(){
        Image imageRight = new ImageIcon("scorpion/right0.png").getImage();
        Image imageDown = new ImageIcon("scorpion/down0.png").getImage();
        Image imageLeft = new ImageIcon("scorpion/left0.png").getImage();
        Image imageUp = new ImageIcon("scorpion/up0.png").getImage();
        Image[] images = {imageRight, imageDown, imageLeft, imageUp};
        this.images = images;
    }

    public Scorpion(int width, int height, boolean clockwise, Point point, Direction dir){
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
        timer.stop();
    }

    @Override
    public void resume() {
        timer.start();
    }

    @Override
    public void checkTimerStart(PlayPanel panel, Object object, Cell[][] levelMatrix){
        check = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("-----------");
                if(object instanceof Boy){
                    Boy boy = (Boy)object;
                    Rectangle boyRect = new Rectangle(boy.getX(), boy.getY(), 70, 70);
                    Rectangle scorpionRect = new Rectangle(x + scorpion.getX(), y + scorpion.getY(), 70, 70);

                    if (boyRect.intersects(scorpionRect) && !boy.gotInTrap) {
                        panel.takeEnergy(energy);
                        boy.gotInTrap = true;
                        Util.wait(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boy.gotInTrap = false;
                                if(scorpion.equals(levelMatrix[boy.xInArray][boy.yInArray].getTrapObject())){
                                    check.start();
                                }
                            }
                        });
                        check.stop();
                    }
                }else if(object instanceof Stone){

                }

//                Rectangle boyRect = new Rectangle(boy.getX(),boy.getY(),70,70);
//                Rectangle scorpionRect = new Rectangle(x+scorpion.getX(),y+scorpion.getY(),70,70);
//                System.out.println("----------");
//                if(boyRect.intersects(scorpionRect)){
//                    panel.remove(scorpion);
//                    Timer t = (Timer) e.getSource();
//                    t.stop();
//                    for (int i = 0; i < levelMatrix.length; i++) {
//                        for (int j = 0; j < levelMatrix[i].length; j++) {
//                            if(levelMatrix[i][j].getTrapObject() instanceof Scorpion){
//                                if(levelMatrix[i][j].getTrapObject().equals(scorpion)){
//                                    levelMatrix[i][j].setTrapObject(null);
//                                }
//                            }
//                        }
//                    }
//                    panel.repaint();
//                }
            }
        });
        check.start();
    }

    public Timer getCheckTimer(){
        return check;
    }

}
