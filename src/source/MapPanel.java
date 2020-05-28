package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MapPanel extends JPanel implements MouseListener {


    private Image mapImage = new ImageIcon("mapPictures/backEmpty.png").getImage();
    private GameFrame gameFrame;

    private LevelUI[] levels;


    private AnimatableImage level1Image = new AnimatableImage("mapPictures/level1.png");
    private AnimatableImage level2Image = new AnimatableImage("mapPictures/level2.png");
    private AnimatableImage level3Image = new AnimatableImage("mapPictures/level3.png");
    private AnimatableImage level4Image = new AnimatableImage("mapPictures/level4.png");
    private AnimatableImage level5Image = new AnimatableImage("mapPictures/level5.png");

    private AnimatableImage level1DisImage = new AnimatableImage("mapPictures/level1Dis.png");
    private AnimatableImage level2DisImage = new AnimatableImage("mapPictures/level2Dis.png");
    private AnimatableImage level3DisImage = new AnimatableImage("mapPictures/level3Dis.png");
    private AnimatableImage level4DisImage = new AnimatableImage("mapPictures/level4Dis.png");
    private AnimatableImage level5DisImage = new AnimatableImage("mapPictures/level5Dis.png");

    private AnimatableImage backToMenuImage = new AnimatableImage("mapPictures/backToMainMenu.png");

    private AnimatableImage level1;
    private AnimatableImage level2;
    private AnimatableImage level3;
    private AnimatableImage level4;
    private AnimatableImage level5;

    private boolean level1Available = true;
    private boolean level2Available = true;
    private boolean level3Available = true;
    private boolean level4Available = true;
    private boolean level5Available = true;

    private int[] x12 = {543, 527, 516, 493, 457, 452, 405, 369, 338, 316, 297};
    private int[] y12 = {185, 187, 187, 186, 180, 180, 178, 184, 197, 206, 218};

    private int[] x23 = { 330, 333, 341, 342, 343, 342, 340, 324, 315, 292, 255, 226, 193, 177, 169};
    private int[] y23 = { 254, 259, 287, 291, 317, 339, 348, 376, 381, 396, 408, 413, 415, 419, 420};

    private int[] x34 = { 125, 127, 133, 150, 167, 177, 200, 220, 257, 290};
    private int[] y34 = { 430, 458, 495, 531, 545, 553, 565, 571, 587, 593};

    private int[] x45 = {  335, 346, 350, 366, 381, 409, 428, 446, 477, 552, 568, 578};
    private int[] y45 = {  571, 554, 545, 526, 519, 515, 512, 502, 502, 480, 470, 455};

    private int[] x5x = {  578,525};
    private int[] y5x = {  455,348};


//    public MapPanel(){
//        setImages();
//        addMouseListener(this);
//
//    }

    private StatusBarPanel statusBarPanel;

    public MapPanel(GameFrame gameFrame){
        setImages();
        addMouseListener(this);
        this.gameFrame = gameFrame;
//        statusBarPanel = new StatusBarPanel(gameFrame);
        initLevels();
    }

    private void initLevels() {
        levels = new LevelUI[5];
        levels[0] = new LevelUI(gameFrame, new PlayPanel(1,gameFrame,this));
        levels[1] = new LevelUI(gameFrame, new PlayPanel(2,gameFrame,this));
        levels[2] = new LevelUI(gameFrame, new PlayPanel(3,gameFrame,this));
        levels[3] = new LevelUI(gameFrame, new PlayPanel(4,gameFrame,this));
        levels[4] = new LevelUI(gameFrame, new PlayPanel(5,gameFrame,this));
    }

    public void paint(Graphics g){
        drawBackground(g);
        drawLines(g);
        drawLevel1(g);
        drawLevel2(g);
        drawLevel3(g);
        drawLevel4(g);
        drawLevel5(g);
        drawMenu(g);

    }

    private void drawMenu(Graphics g) {
        g.drawImage(backToMenuImage.image, Values.MENU_X, Values.MENU_Y, Values.MENU_SIZE, Values.MENU_SIZE,null);
    }

    private void drawLines(Graphics gr) {
        Graphics2D g = (Graphics2D)gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        float[] dash = {15f};
        BasicStroke stroke = new BasicStroke(8,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10f,dash,10);
        g.setStroke(stroke);
        g.setPaint(Color.red);
        if (level2Available)
            drawLine12(g);
        if (level3Available)
            drawLine23(g);
        if (level4Available)
            drawLine34(g);
        if (level5Available) {
            drawLine45(g);
            drawLine5X(g);
        }

    }

    private void drawLine5X(Graphics2D g) {
        g.drawPolyline(x5x,y5x,x5x.length);
    }

    private void drawLine45(Graphics2D g) {
        g.drawPolyline(x45,y45,x45.length);
    }


    private void drawLine34(Graphics2D g) {
        g.drawPolyline(x34,y34,x34.length);
    }

    private void drawLine23(Graphics2D g) {
        g.drawPolyline(x23,y23,x23.length);
    }


    private void drawLine12(Graphics2D g) {
        g.drawPolyline(x12,y12,x12.length);
    }

    private void drawLevel5(Graphics g) {
        g.drawImage(level5.image, Values.LEVEL_5_X, Values.LEVEL_5_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE,null);
    }

    private void drawLevel4(Graphics g) {
        g.drawImage(level4.image, Values.LEVEL_4_X, Values.LEVEL_4_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE,null);
    }

    private void drawLevel3(Graphics g) {
        g.drawImage(level3.image, Values.LEVEL_3_X, Values.LEVEL_3_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE,null);
    }

    private void drawLevel2(Graphics g) {
        g.drawImage(level2.image, Values.LEVEL_2_X, Values.LEVEL_2_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE,null);
    }

    private void drawLevel1(Graphics g) {
        g.drawImage(level1.image, Values.LEVEL_1_X, Values.LEVEL_1_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE,null);
    }

    private void setImages() {
        if (level1Available)
            level1 = level1Image;
        else level1 = level1DisImage;
        if (level2Available)
            level2 = level2Image;
        else level2 = level2DisImage;
        if (level3Available)
            level3 = level3Image;
        else level3 = level3DisImage;
        if (level4Available)
            level4 = level4Image;
        else level4 = level4DisImage;
        if (level5Available)
            level5 = level5Image;
        else level5 = level5DisImage;
    }



    private void drawBackground(Graphics g) {
        g.setColor(new Color(245, 166, 53));
        g.fillRect(0,0, Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        g.drawImage(mapImage,0,0, Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH,null);
    }

//    public static void main(String[] args) {
//        JFrame f = new JFrame();
//        f.setSize(700,820);
//        f.setLocation(300,0);
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f.setUndecorated(true);
//        MapPanel mapPanel = new MapPanel();
//        f.add(mapPanel);
//        f.setVisible(true);
//    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double level1 = new Rectangle2D.Double(Values.LEVEL_1_X, Values.LEVEL_1_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE);
        Rectangle2D.Double level2 = new Rectangle2D.Double(Values.LEVEL_2_X, Values.LEVEL_2_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE);
        Rectangle2D.Double level3 = new Rectangle2D.Double(Values.LEVEL_3_X, Values.LEVEL_3_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE);
        Rectangle2D.Double level4 = new Rectangle2D.Double(Values.LEVEL_4_X, Values.LEVEL_4_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE);
        Rectangle2D.Double level5 = new Rectangle2D.Double(Values.LEVEL_5_X, Values.LEVEL_5_Y, Values.LEVEL_POINT_SIZE, Values.LEVEL_POINT_SIZE);
        Rectangle2D.Double menu = new Rectangle2D.Double(Values.MENU_X, Values.MENU_Y, Values.MENU_SIZE, Values.MENU_SIZE);
        if (menu.contains(point)){
            backToMenuImage.animate(this,"mapPictures",menu);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showMainMenu();
                }
            });
        }
        else if (level1.contains(point)){
            level1Image.animate(this,"mapPictures",level1);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showLevel(levels[0]);
                }
            });
        }
        else if (level2.contains(point)){
            if (level2Available) {
                level2Image.animate(this, "mapPictures", level2);
                Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.showLevel(levels[1]);
                    }
                });
            }
        }
        else if (level3.contains(point)){
            if (level3Available) {
                level3Image.animate(this, "mapPictures", level3);
                Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.showLevel(levels[2]);
                    }
                });
            }
        }
        else if (level4.contains(point)){
            if (level4Available) {
                level4Image.animate(this, "mapPictures", level4);
                Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.showLevel(levels[3]);
                    }
                });
            }
        }
        else if (level5.contains(point)){
            if (level5Available) {
                level5Image.animate(this, "mapPictures", level5);
                Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.showLevel(levels[4]);
                    }
                });
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
