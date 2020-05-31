package objects.traps;

import maps.Cell;
import objects.Stone;
import objects.blocks.doors.Resettable;
import source.Boy;
import source.PlayPanel;
import source.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JLabel implements Trap, Resettable {

    private Timer timer;
    private Timer check;
    public int x = 0;
    public int y = 0;
    private JLabel snake;
    private boolean side = true; //right - true; left - false
    private Image[] images;
    private Image current;
    private int energy = 20;
    public boolean isAlive = true;
    private PlayPanel playPanel;
    private int width;
    private int height;

    private void initImages(){
        Image imageRight = new ImageIcon("snake/snakeRight.png").getImage();
        Image imageLeft = new ImageIcon("snake/snakeLeft.png").getImage();
        Image imageUp = new ImageIcon("snake/snakeUp.png").getImage();
        Image imageDown = new ImageIcon("snake/snakeDown.png").getImage();
        Image[] images = {imageRight, imageLeft,imageUp,imageDown};
        this.images = images;
    }

    public Snake(int width, int height, int coord, boolean horizontal, PlayPanel playPanel){
        this.width = width;
        this.height = height;
        this.playPanel = playPanel;
        initImages();
        snake = this;
        setPreferredSize(new Dimension(width,height));
        if(horizontal) {
            this.x = coord;
            timer = new Timer(15, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] diapason = obstacleCheckX();
                    int start = 0;
                    int end = width;
                    if(diapason[0]!=-1){
                        start = diapason[0]+70;
                    }
                    if(diapason[1]!=-1){
                        end = diapason[1];
                    }
                    Timer t = (Timer)e.getSource();
                    t.setDelay(15);
                    if (side) {
                        x += 1;
                        current = images[0];
                        if (x+70 >= end) {
                            side = false;
                            current = images[1];
                            t.setDelay(1000);
                            x++;
                        }
                    } else {
                        x -= 1;
                        current = images[1];
                        if (x <= start) {
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
                    int[] diapason = obstacleCheckY();
                    int start = 0;
                    int end = height;
                    if(diapason[0]!=-1){
                        start = diapason[0]+70;
                    }
                    if(diapason[1]!=-1){
                        end = diapason[1];
                    }
                    Timer t = (Timer)e.getSource();
                    t.setDelay(15);
                    if (side) {
                        y += 1;
                        current = images[3];
                        if (y+70 >= end) {
                            side = false;
                            current = images[2];
                            t.setDelay(1000);
                            y++;
                        }
                    } else {
                        y -= 1;
                        current = images[2];
                        if (y <= start) {
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

    @Override
    public void checkTimerStart(PlayPanel panel, Object object, Cell[][] levelMatrix){
        check = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rectangle snakeRect = new Rectangle(x+snake.getX(),y+snake.getY(),70,70);

                if(object instanceof Boy){
                    System.out.println("------------");
                    Boy boy = (Boy)object;
                    Rectangle boyRect = new Rectangle(boy.getX(),boy.getY(),70,70);
                    if(boyRect.intersects(snakeRect) && !boy.gotInTrap){
                        panel.takeEnergy(energy);
                        boy.gotInTrap = true;
                        Util.wait(5000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boy.gotInTrap = false;
                                if(snake.equals(levelMatrix[boy.xInArray][boy.yInArray].getTrapObject())){
                                    check.start();
                                }
                            }
                        });
                        check.stop();
                    }
                }else if(object instanceof Stone){
                    System.out.println("+!+!+!+!+!+!");
                    Rock rock = (Rock)object;
                    Rectangle rockRect = new Rectangle(rock.x,rock.y,70,70);
                    if(rockRect.intersects(snakeRect)){
                        panel.remove(snake);
                        Timer t = (Timer) e.getSource();
                        t.stop();
                        for (int i = 0; i < levelMatrix.length; i++) {
                            for (int j = 0; j < levelMatrix[i].length; j++) {
                                if(levelMatrix[i][j].getTrapObject() instanceof Snake){
                                    if(levelMatrix[i][j].getTrapObject().equals(snake)){
                                        levelMatrix[i][j].setTrapObject(null);
                                        isAlive = false;
                                    }
                                }
                            }
                        }
                        panel.repaint();
                    }
                }

//                Rectangle boyRect = new Rectangle(boy.getX(),boy.getY(),70,70);
//                Rectangle snakeRect = new Rectangle(x+snake.getX(),y+snake.getY(),70,70);
//                System.out.println("----------");
//                if(boyRect.intersects(snakeRect)){
//                    panel.remove(snake);
//                    Timer t = (Timer) e.getSource();
//                    t.stop();
//                    for (int i = 0; i < levelMatrix.length; i++) {
//                        for (int j = 0; j < levelMatrix[i].length; j++) {
//                            if(levelMatrix[i][j].getTrapObject() instanceof Snake){
//                                if(levelMatrix[i][j].getTrapObject().equals(snake)){
//                                    levelMatrix[i][j].setTrapObject(null);
//                                    isAlive = false;
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

    public int[] obstacleCheckX(){
        int[] diapason = {-1,-1};
        if(!(snake.getX()==0&&snake.getY()==0)){
            int labelIX = (snake.getX()-playPanel.getMapX())/70;
            int labelIY = (snake.getY()-playPanel.getMapY())/70;
            for (int i = 0; i < width/70; i++) {
                Cell cell = playPanel.currentLevel.getMatrix()[labelIX+i][labelIY];
                if(cell.getTrapObject() instanceof Rock || cell.getHarmlessObject()!=null){
                    if((labelIX+i)*70>=labelIX*70+x){
                        diapason[1] = i*70;
                    }else{
                        diapason[0] = i*70;
                    }
                }
            }
        }
        return diapason;
    }

    public int[] obstacleCheckY(){
        int[] diapason = {-1,-1};
        if(!(snake.getX()==0&&snake.getY()==0)){
            int labelIX = (snake.getX()-playPanel.getMapX())/70;
            int labelIY = (snake.getY()-playPanel.getMapY())/70;
            for (int i = 0; i < height/70; i++) {
                Cell cell = playPanel.currentLevel.getMatrix()[labelIX][labelIY+i];
                if(cell.getTrapObject() instanceof Rock || cell.getHarmlessObject()!=null){
                    if((labelIY+i)*70>=labelIY*70+y){
                        diapason[1] = i*70;
                    }else{
                        diapason[0] = i*70;
                    }
                }
            }
        }
        return diapason;
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

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,500);
//        frame.setVisible(true);
//        Snake snake = new Snake(100,300, 50,true);
//        frame.add(snake);
//        snake.timer.start();
//    }

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
    public void reset() {
        isAlive = true;
    }

}
