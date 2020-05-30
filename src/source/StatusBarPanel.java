package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class StatusBarPanel extends JPanel implements MouseListener {

    private AnimatableImage image = new AnimatableImage("statusBar/statusBar.jpg");
    private AnimatableImage pauseImage = new AnimatableImage("statusBar/pause.png");
    private AnimatableImage energyLevelImage = new AnimatableImage("statusBar/energyLevelEmpty.png");
    private AnimatableImage goldKeyImage = new AnimatableImage("statusBar/goldKey.png");
    private AnimatableImage silverKeyImage =new AnimatableImage( "statusBar/silverKey.png");
//    private AnimatableImage redDiamondImage = new AnimatableImage("statusBar/redDiamond.png");
//    private AnimatableImage purpleDiamondImage = new AnimatableImage("statusBar/purpleDiamond.png");
    private AnimatableImage redDiamondImage = new AnimatableImage("statusBar/diamondHexRed.png");
    private AnimatableImage purpleDiamondImage = new AnimatableImage("statusBar/diamondHexPurple.png");
    private AnimatableImage checkpointImage = new AnimatableImage("statusBar/checkpoint.png");
    private AnimatableImage energyImage = new AnimatableImage("statusBar/energy.png");

    private Font font;
    private Font fontLevel;
    private PauseMenuDialog pauseMenuDialog;
    private GameFrame gameFrame;




    private int currentEnergyLevel =200;
    private int maxEnergyLevel = 500;
    double unit;

    private int currentNumberOfGoldKeys;
    private int maxNumberOfGoldKeys = 2;

    private int currentNumberOfSilverKeys;
    private int maxNumberOfSilverKeys = 2;

    private int currentNumberOfPurpleDiamonds;
    private int maxNumberOfPurpleDiamonds = 40;

    private int currentNumberOfRedDiamonds;
    private int maxNumberOfRedDiamonds = 2;

    private int currentLevel = 1;

    private double progressBarWidth;
    private Rectangle2D.Double progressBar;

    private PlayPanel playPanel;


//    public StatusBarPanel() {
//        addMouseListener(this);
//        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
//        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
//    }


    public StatusBarPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setPreferredSize(new Dimension(Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
        pauseMenuDialog = new PauseMenuDialog(gameFrame);
    }

    public StatusBarPanel(GameFrame gameFrame, PlayPanel playPanel) {
        this.gameFrame = gameFrame;
        this.playPanel = playPanel;
        setPreferredSize(new Dimension(Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
        pauseMenuDialog = new PauseMenuDialog(gameFrame, playPanel);
    }

//    public void setPlayPanel() {
//        playPanel = gameFrame.getCurrentPlayPanel();
//    }

    //    public StatusBarPanel(GameFrame gameFrame, PlayPanel playPanel) {
//        this.playPanel = playPanel;
//        this.gameFrame = gameFrame;
//        setPreferredSize(new Dimension(Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
//        addMouseListener(this);
//        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
//        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
//        pauseMenuDialog = new PauseMenuDialog(gameFrame);
//    }

//    public StatusBarPanel(GameFrame gameFrame, int level, int maxEnergyLevel, int maxNumberOfGoldKeys, int maxNumberOfSilverKeys, int maxNumberOfPurpleDiamonds, int maxNumberOfRedDiamonds){
//        this.gameFrame = gameFrame;
//        currentLevel = level;
//        this.maxEnergyLevel = maxEnergyLevel;
//        this.maxNumberOfGoldKeys = maxNumberOfGoldKeys;
//        this.maxNumberOfSilverKeys = maxNumberOfSilverKeys;
//        this.maxNumberOfPurpleDiamonds = maxNumberOfPurpleDiamonds;
//        this.maxNumberOfRedDiamonds = maxNumberOfRedDiamonds;
//        addMouseListener(this);
//        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
//        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
//        pauseMenuDialog = new PauseMenuDialog(gameFrame);
//    }


    public void paint(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawBackground(g);
        drawPauseButton(g);
        drawProgressBar(g);
        drawEnergyLevel(g);
        drawLevelLabel(g);
        drawCheckpoint(g);
        drawKeys(g);
        drawDiamonds(g);
    }



    private void drawProgressBar(Graphics g) {
        if (currentEnergyLevel >= unit*4)
            g.setColor(Color.green);
        else if (currentEnergyLevel >= unit*3)
            g.setColor(new Color(183,226,10));
        else if (currentEnergyLevel >= unit*2)
            g.setColor(new Color(255,157,0));
        else if (currentEnergyLevel >= unit)
            g.setColor(new Color(255,90,0));
        else if (currentEnergyLevel < unit)
            g.setColor(new Color(255,14,0));
        progressBarWidth = ((Values.ENERGY_FIELD_WIDTH-5)/(double)(maxEnergyLevel))*currentEnergyLevel;
        progressBar = new Rectangle2D.Double(Values.PROGRESS_BAR_X, Values.PROGRESS_BAR_Y,progressBarWidth, Values.PROGRESS_BAR_LENGTH);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.fill(progressBar);
    }

    private void drawLevelLabel(Graphics g) {
        g.setFont(fontLevel);
        g.drawString("LEVEL  "+currentLevel, Values.LEVEL_LABEL_X, Values.LEVEL_LABEL_Y);
        g.setFont(font);
    }

    private void drawDiamonds(Graphics g) {
        g.drawImage(redDiamondImage.image, Values.RED_DIAMOND_X, Values.RED_DIAMOND_Y, Values.RED_DIAMOND_WIDTH, Values.RED_DIAMOND_LENGTH,null);
        g.drawImage(purpleDiamondImage.image, Values.PURPLE_DIAMOND_X, Values.PURPLE_DIAMOND_Y, Values.PURPLE_DIAMOND_WIDTH, Values.PURPLE_DIAMOND_LENGTH,null);
        g.drawString(""+currentNumberOfRedDiamonds+"/"+maxNumberOfRedDiamonds, Values.RED_DIAMOND_LABEL_X, Values.RED_DIAMOND_LABEL_Y);
        g.drawString(""+currentNumberOfPurpleDiamonds+"/"+maxNumberOfPurpleDiamonds, Values.PURPLE_DIAMOND_LABEL_X, Values.PURPLE_DIAMOND_LABEL_Y);
    }

    private void drawKeys(Graphics g) {
        g.drawImage(goldKeyImage.image, Values.GOLD_KEY_X, Values.GOLD_KEY_Y, Values.GOLD_KEY_WIDTH, Values.GOLD_KEY_LENGTH,null);
        g.drawImage(silverKeyImage.image, Values.SILVER_KEY_X, Values.SILVER_KEY_Y, Values.SILVER_KEY_WIDTH, Values.SILVER_KEY_LENGTH,null);
        g.drawString(""+currentNumberOfGoldKeys+"/"+maxNumberOfGoldKeys, Values.GOLD_KEY_LABEL_X, Values.GOLD_KEY_LABEL_Y);
        g.drawString(""+currentNumberOfSilverKeys+"/"+maxNumberOfSilverKeys, Values.SILVER_KEY_LABEL_X, Values.SILVER_KEY_LABEL_Y);
    }

    private void drawCheckpoint(Graphics g) {
        g.drawImage(checkpointImage.image, Values.CHECKPOINT_BUTTON_X, Values.CHECKPOINT_BUTTON_Y, Values.CHECKPOINT_BUTTON_WIDTH, Values.CHECKPOINT_BUTTON_LENGTH,null);
    }

    private void drawEnergyLevel(Graphics g) {
        g.drawImage(energyLevelImage.image, Values.ENERGY_FIELD_X, Values.ENERGY_FIELD_Y, Values.ENERGY_FIELD_WIDTH, Values.ENERGY_FIELD_LENGTH,null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(""+currentEnergyLevel+"/"+maxEnergyLevel, Values.ENERGY_LABEL_X, Values.ENERGY_LABEL_Y);
        g.drawImage(energyImage.image, Values.ENERGY_X, Values.ENERGY_Y, Values.ENERGY_WIDTH, Values.ENERGY_LENGTH,null);
    }

    private void drawPauseButton(Graphics g) {
        g.drawImage(pauseImage.image, Values.PAUSE_BUTTON_X, Values.PAUSE_BUTTON_Y, Values.PAUSE_BUTTON_WIDTH, Values.PAUSE_BUTTON_LENGTH,null);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(image.image, Values.GAME_STATUS_BAR_X, Values.GAME_STATUS_BAR_Y, Values.GAME_STATUS_BAR_WIDTH, Values.GAME_STATUS_BAR_LENGTH,null);
    }


    public int getCurrentEnergyLevel() {
        return currentEnergyLevel;
    }

    public void setCurrentEnergyLevel(int currentEnergyLevel) {
        this.currentEnergyLevel = currentEnergyLevel;
        repaint();
//        repaint(new Rectangle(Values.ENERGY_FIELD_X, Values.ENERGY_FIELD_Y, Values.ENERGY_FIELD_WIDTH, Values.ENERGY_FIELD_LENGTH));
    }

    public int getCurrentNumberOfGoldKeys() {
        return currentNumberOfGoldKeys;
    }

    public void setCurrentNumberOfGoldKeys(int currentNumberOfGoldKeys) {
        this.currentNumberOfGoldKeys = currentNumberOfGoldKeys;
        repaint();
//        repaint(new Rectangle(Values.GOLD_KEY_LABEL_X, Values.GOLD_KEY_LABEL_Y,200,100));
    }

    public int getCurrentNumberOfSilverKeys() {
        return currentNumberOfSilverKeys;
    }

    public void setCurrentNumberOfSilverKeys(int currentNumberOfSilverKeys) {
        this.currentNumberOfSilverKeys = currentNumberOfSilverKeys;
        repaint();
//        repaint(new Rectangle(Values.SILVER_KEY_LABEL_X, Values.SILVER_KEY_LABEL_Y,200,100));
    }

    public int getCurrentNumberOfPurpleDiamonds() {
        return currentNumberOfPurpleDiamonds;
    }

    public void setCurrentNumberOfPurpleDiamonds(int currentNumberOfPurpleDiamonds) {
        this.currentNumberOfPurpleDiamonds = currentNumberOfPurpleDiamonds;
        repaint();
//        repaint(new Rectangle(Values.PURPLE_DIAMOND_LABEL_X, Values.PURPLE_DIAMOND_LABEL_Y,200,100));
    }

    public int getCurrentNumberOfRedDiamonds() {
        return currentNumberOfRedDiamonds;
    }

    public void setCurrentNumberOfRedDiamonds(int currentNumberOfRedDiamonds) {
        this.currentNumberOfRedDiamonds = currentNumberOfRedDiamonds;
        repaint();
//        repaint(new Rectangle(Values.RED_DIAMOND_LABEL_X, Values.RED_DIAMOND_LABEL_Y,200,100));
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double pause = new Rectangle2D.Double(Values.PAUSE_BUTTON_X, Values.PAUSE_BUTTON_Y, Values.PAUSE_BUTTON_WIDTH, Values.PAUSE_BUTTON_LENGTH);
        Rectangle2D.Double checkpoint = new Rectangle2D.Double(Values.CHECKPOINT_BUTTON_X, Values.CHECKPOINT_BUTTON_Y, Values.CHECKPOINT_BUTTON_WIDTH, Values.CHECKPOINT_BUTTON_LENGTH);
        Rectangle2D.Double energyLevel = new Rectangle2D.Double(Values.ENERGY_FIELD_X, Values.ENERGY_FIELD_Y, Values.ENERGY_FIELD_WIDTH, Values.ENERGY_FIELD_LENGTH);
        if (pause.contains(point)){
            pauseImage.animate(StatusBarPanel.this);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playPanel.pause();
                    pauseMenuDialog.setLocation(gameFrame.getX()+ Values.PAUSE_MENU_SHIFT_X, gameFrame.getY()+ Values.PAUSE_MENU_SHIFT_Y);
                    pauseMenuDialog.setVisible(true);
                }
            });
            System.out.println("Pause button");
        }
        else if (checkpoint.contains(point)){
            checkpointImage.animate(this);
            System.out.println("Checkpoint button");
            playPanel.applyCheckpoint();
//            there should be the method that moves boy to the checkpoint
        }
        else if (energyLevel.contains(point)){
            energyLevelImage.animate(this);
            energyImage.animate(this);
            System.out.println("Energy button");
//             BackpackPanel backPanel = new BackPanel();
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

    public void setImage(AnimatableImage image) {
        this.image = image;
    }

    public void setMaxEnergyLevel(int maxEnergyLevel) {
        this.maxEnergyLevel = maxEnergyLevel;
        unit = maxEnergyLevel/5;
    }

    public void setMaxNumberOfGoldKeys(int maxNumberOfGoldKeys) {
        this.maxNumberOfGoldKeys = maxNumberOfGoldKeys;
    }

    public void setMaxNumberOfSilverKeys(int maxNumberOfSilverKeys) {
        this.maxNumberOfSilverKeys = maxNumberOfSilverKeys;
    }

    public void setMaxNumberOfPurpleDiamonds(int maxNumberOfPurpleDiamonds) {
        this.maxNumberOfPurpleDiamonds = maxNumberOfPurpleDiamonds;
    }

    public void setMaxNumberOfRedDiamonds(int maxNumberOfRedDiamonds) {
        this.maxNumberOfRedDiamonds = maxNumberOfRedDiamonds;
    }

        public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame();
                f.setSize(700,820);

                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.setUndecorated(true);



                PlayPanel playPanel =new PlayPanel(1,new GameFrame(),new MapPanel(new GameFrame()));
                StatusBarPanel statusBarPanel = playPanel.getStatusBarPanel();
                f.setLayout(new BorderLayout());
                f.getContentPane().add(statusBarPanel, BorderLayout.NORTH);
                f.getContentPane().add(playPanel, BorderLayout.CENTER);
                f.setVisible(true);
                Timer t = new Timer(1000, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        statusBarPanel.setCurrentEnergyLevel(statusBarPanel.getCurrentEnergyLevel()-5);
                        statusBarPanel.setCurrentNumberOfPurpleDiamonds(statusBarPanel.getCurrentNumberOfGoldKeys()+1);
                    }
                });
                t.start();
            }
        });








    }


}