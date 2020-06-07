package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * @author Illia Sitkov
 */
public class StatusBarPanel extends JPanel implements MouseListener {

    private AnimatableImage image = new AnimatableImage("statusBar/statusBar.jpg");
    private AnimatableImage pauseImage = new AnimatableImage("statusBar/pause.png");
    private AnimatableImage energyLevelImage = new AnimatableImage("statusBar/energyLevelEmpty.png");
    private AnimatableImage goldKeyImage = new AnimatableImage("statusBar/goldKey.png");
    private AnimatableImage silverKeyImage =new AnimatableImage( "statusBar/silverKey.png");
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


    /**
     * constructor with parameters
     * @param gameFrame
     */
    public StatusBarPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setPreferredSize(new Dimension(Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
        pauseMenuDialog = new PauseMenuDialog(gameFrame);
    }

    /**
     * constructor with parameters
     * @param gameFrame
     * @param playPanel
     */
    public StatusBarPanel(GameFrame gameFrame, PlayPanel playPanel) {
        this.gameFrame = gameFrame;
        this.playPanel = playPanel;
        setPreferredSize(new Dimension(Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",17f);
        fontLevel = Util.getFont("fonts/Funhouse-Ke17.ttf",30f);
        pauseMenuDialog = new PauseMenuDialog(gameFrame, playPanel);
    }


    @Override
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


    /**
     * draws energy level bar
     * @param g
     */
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
    /**
     * draws level label
     * @param g
     */
    private void drawLevelLabel(Graphics g) {
        g.setFont(fontLevel);
        g.drawString("LEVEL  "+currentLevel, Values.LEVEL_LABEL_X, Values.LEVEL_LABEL_Y);
        g.setFont(font);
    }
    /**
     * draws diamonds labels
     * @param g
     */
    private void drawDiamonds(Graphics g) {
        g.drawImage(redDiamondImage.image, Values.RED_DIAMOND_X, Values.RED_DIAMOND_Y, Values.RED_DIAMOND_WIDTH, Values.RED_DIAMOND_LENGTH,null);
        g.drawImage(purpleDiamondImage.image, Values.PURPLE_DIAMOND_X, Values.PURPLE_DIAMOND_Y, Values.PURPLE_DIAMOND_WIDTH, Values.PURPLE_DIAMOND_LENGTH,null);
        g.drawString(""+currentNumberOfRedDiamonds+"/"+maxNumberOfRedDiamonds, Values.RED_DIAMOND_LABEL_X, Values.RED_DIAMOND_LABEL_Y);
        g.drawString(""+currentNumberOfPurpleDiamonds+"/"+maxNumberOfPurpleDiamonds, Values.PURPLE_DIAMOND_LABEL_X, Values.PURPLE_DIAMOND_LABEL_Y);
    }
    /**
     * draws keys labels
     * @param g
     */
    private void drawKeys(Graphics g) {
        g.drawImage(goldKeyImage.image, Values.GOLD_KEY_X, Values.GOLD_KEY_Y, Values.GOLD_KEY_WIDTH, Values.GOLD_KEY_LENGTH,null);
        g.drawImage(silverKeyImage.image, Values.SILVER_KEY_X, Values.SILVER_KEY_Y, Values.SILVER_KEY_WIDTH, Values.SILVER_KEY_LENGTH,null);
        g.drawString(""+currentNumberOfGoldKeys+"/"+maxNumberOfGoldKeys, Values.GOLD_KEY_LABEL_X, Values.GOLD_KEY_LABEL_Y);
        g.drawString(""+currentNumberOfSilverKeys+"/"+maxNumberOfSilverKeys, Values.SILVER_KEY_LABEL_X, Values.SILVER_KEY_LABEL_Y);
    }

    /**
     * draws checkpoint label
     * @param g
     */
    private void drawCheckpoint(Graphics g) {
        g.drawImage(checkpointImage.image, Values.CHECKPOINT_BUTTON_X, Values.CHECKPOINT_BUTTON_Y, Values.CHECKPOINT_BUTTON_WIDTH, Values.CHECKPOINT_BUTTON_LENGTH,null);
    }

    /**
     * draws energy level bar
     * @param g
     */
    private void drawEnergyLevel(Graphics g) {
        g.drawImage(energyLevelImage.image, Values.ENERGY_FIELD_X, Values.ENERGY_FIELD_Y, Values.ENERGY_FIELD_WIDTH, Values.ENERGY_FIELD_LENGTH,null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(""+currentEnergyLevel+"/"+maxEnergyLevel, Values.ENERGY_LABEL_X, Values.ENERGY_LABEL_Y);
        g.drawImage(energyImage.image, Values.ENERGY_X, Values.ENERGY_Y, Values.ENERGY_WIDTH, Values.ENERGY_LENGTH,null);
    }
    /**
     * draws pause button
     * @param g
     */
    private void drawPauseButton(Graphics g) {
        g.drawImage(pauseImage.image, Values.PAUSE_BUTTON_X, Values.PAUSE_BUTTON_Y, Values.PAUSE_BUTTON_WIDTH, Values.PAUSE_BUTTON_LENGTH,null);
    }
    /**
     * draws background
     * @param g
     */
    private void drawBackground(Graphics g) {
        g.drawImage(image.image, Values.GAME_STATUS_BAR_X, Values.GAME_STATUS_BAR_Y, Values.GAME_STATUS_BAR_WIDTH, Values.GAME_STATUS_BAR_LENGTH,null);
    }

    public int getCurrentEnergyLevel() {
        return currentEnergyLevel;
    }

    public void setCurrentEnergyLevel(int currentEnergyLevel) {
        this.currentEnergyLevel = currentEnergyLevel;
        repaint();
    }

    public int getCurrentNumberOfGoldKeys() {
        return currentNumberOfGoldKeys;
    }

    public void setCurrentNumberOfGoldKeys(int currentNumberOfGoldKeys) {
        this.currentNumberOfGoldKeys = currentNumberOfGoldKeys;
        repaint();
    }


    public void setCurrentNumberOfSilverKeys(int currentNumberOfSilverKeys) {
        this.currentNumberOfSilverKeys = currentNumberOfSilverKeys;
        repaint();
    }


    public void setCurrentNumberOfPurpleDiamonds(int currentNumberOfPurpleDiamonds) {
        this.currentNumberOfPurpleDiamonds = currentNumberOfPurpleDiamonds;
        repaint();
    }

    public void setCurrentNumberOfRedDiamonds(int currentNumberOfRedDiamonds) {
        this.currentNumberOfRedDiamonds = currentNumberOfRedDiamonds;
        repaint();
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
            Util.click();
            pauseImage.animate(StatusBarPanel.this);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playPanel.pause();
                    pauseMenuDialog.setLocation(gameFrame.getX()+ Values.PAUSE_MENU_SHIFT_X, gameFrame.getY()+ Values.PAUSE_MENU_SHIFT_Y);
                    pauseMenuDialog.setVisible(true);
                }
            });
        }
        else if (checkpoint.contains(point)){
            Util.click();
            checkpointImage.animate(this);
            playPanel.applyCheckpoint();
        }
        else if (energyLevel.contains(point)){
            Util.click();

            energyLevelImage.animate(this);
            energyImage.animate(this);
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
    }