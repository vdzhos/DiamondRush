
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class StatusBarPanel extends JPanel implements MouseListener {

    private AnimatableImage image = new AnimatableImage("statusBar/statusBar.jpg");
    private AnimatableImage pauseImage = new AnimatableImage("statusBar/pause.png");
    private AnimatableImage energyLevelImage = new AnimatableImage("statusBar/energyLevelEmpty.png");
    private AnimatableImage goldKeyImage = new AnimatableImage("statusBar/goldKey.png");
    private AnimatableImage silverKeyImage =new AnimatableImage( "statusBar/silverKey.png");
    private AnimatableImage redDiamondImage = new AnimatableImage("statusBar/redDiamond.png");
    private AnimatableImage purpleDiamondImage = new AnimatableImage("statusBar/purpleDiamond.png");
    private AnimatableImage checkpointImage = new AnimatableImage("statusBar/checkpoint.png");
    private AnimatableImage energyImage = new AnimatableImage("statusBar/energy.png");

    private Font font = new Font("Times New Roman",Font.BOLD,26);
    private Font fontLevel = new Font("Times New Roman",Font.BOLD,32);



    private int currentEnergyLevel = 500;
    private int maxEnergyLevel = 500;

    private int currentNumberOfGoldKeys = 0;
    private int maxNumberOfGoldKeys = 2;

    private int currentNumberOfSilverKeys = 0;
    private int maxNumberOfSilverKeys = 2;

    private int currentNumberOfPurpleDiamonds = 40;
    private int maxNumberOfPurpleDiamonds = 40;

    private int currentNumberOfRedDiamonds = 0;
    private int maxNumberOfRedDiamonds = 2;

    private int currentLevel = 1;

    private double progressBarWidth;
    private Rectangle2D.Double progressBar;


    public StatusBarPanel(){
        addMouseListener(this);
    }


    public void paint(Graphics g){
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
        if (currentEnergyLevel >= 400)
            g.setColor(Color.green);
        else if (currentEnergyLevel >= 300)
            g.setColor(new Color(183,226,10));
        else if (currentEnergyLevel >= 200)
            g.setColor(new Color(255,157,0));
        else if (currentEnergyLevel >= 100)
            g.setColor(new Color(255,90,0));
        else if (currentEnergyLevel < 100)
            g.setColor(new Color(255,14,0));
        progressBarWidth = ((Values.ENERGY_FIELD_WIDTH-5)/(double)(maxEnergyLevel))*currentEnergyLevel;
        progressBar = new Rectangle2D.Double(Values.PROGRESS_BAR_X,Values.PROGRESS_BAR_Y,progressBarWidth,Values.PROGRESS_BAR_LENGTH);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.fill(progressBar);
    }

    private void drawLevelLabel(Graphics g) {
        g.setFont(fontLevel);
        g.drawString("Рівень "+currentLevel,Values.LEVEL_LABEL_X,Values.LEVEL_LABEL_Y);
        g.setFont(font);
    }

    private void drawDiamonds(Graphics g) {
        g.drawImage(redDiamondImage.image,Values.RED_DIAMOND_X,Values.RED_DIAMOND_Y,Values.RED_DIAMOND_WIDTH,Values.RED_DIAMOND_LENGTH,null);
        g.drawImage(purpleDiamondImage.image,Values.PURPLE_DIAMOND_X,Values.PURPLE_DIAMOND_Y,Values.PURPLE_DIAMOND_WIDTH,Values.PURPLE_DIAMOND_LENGTH,null);
        g.drawString(""+currentNumberOfRedDiamonds+"/"+maxNumberOfRedDiamonds,Values.RED_DIAMOND_LABEL_X,Values.RED_DIAMOND_LABEL_Y);
        g.drawString(""+currentNumberOfPurpleDiamonds+"/"+maxNumberOfPurpleDiamonds,Values.PURPLE_DIAMOND_LABEL_X,Values.PURPLE_DIAMOND_LABEL_Y);
    }

    private void drawKeys(Graphics g) {
        g.drawImage(goldKeyImage.image,Values.GOLD_KEY_X,Values.GOLD_KEY_Y,Values.GOLD_KEY_WIDTH,Values.GOLD_KEY_LENGTH,null);
        g.drawImage(silverKeyImage.image,Values.SILVER_KEY_X,Values.SILVER_KEY_Y,Values.SILVER_KEY_WIDTH,Values.SILVER_KEY_LENGTH,null);
        g.drawString(""+currentNumberOfGoldKeys+"/"+maxNumberOfGoldKeys,Values.GOLD_KEY_LABEL_X,Values.GOLD_KEY_LABEL_Y);
        g.drawString(""+currentNumberOfSilverKeys+"/"+maxNumberOfSilverKeys,Values.SILVER_KEY_LABEL_X,Values.SILVER_KEY_LABEL_Y);
    }

    private void drawCheckpoint(Graphics g) {
        g.drawImage(checkpointImage.image,Values.CHECKPOINT_BUTTON_X,Values.CHECKPOINT_BUTTON_Y,Values.CHECKPOINT_BUTTON_WIDTH,Values.CHECKPOINT_BUTTON_LENGTH,null);
    }

    private void drawEnergyLevel(Graphics g) {
        g.drawImage(energyLevelImage.image, Values.ENERGY_FIELD_X,Values.ENERGY_FIELD_Y,Values.ENERGY_FIELD_WIDTH,Values.ENERGY_FIELD_LENGTH,null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(""+currentEnergyLevel+"/"+maxEnergyLevel,Values.ENERGY_LABEL_X,Values.ENERGY_LABEL_Y);
        g.drawImage(energyImage.image,Values.ENERGY_X,Values.ENERGY_Y,Values.ENERGY_WIDTH,Values.ENERGY_LENGTH,null);
    }

    private void drawPauseButton(Graphics g) {
        g.drawImage(pauseImage.image,Values.PAUSE_BUTTON_X,Values.PAUSE_BUTTON_Y,Values.PAUSE_BUTTON_WIDTH,Values.PAUSE_BUTTON_LENGTH,null);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(image.image,Values.GAME_STATUS_BAR_X,Values.GAME_STATUS_BAR_Y,Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH,null);
    }


    public int getCurrentEnergyLevel() {
        return currentEnergyLevel;
    }

    public void setCurrentEnergyLevel(int currentEnergyLevel) {
        this.currentEnergyLevel = currentEnergyLevel;
        repaint(new Rectangle(Values.GAME_STATUS_BAR_X,Values.GAME_STATUS_BAR_Y,Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
    }

    public int getCurrentNumberOfGoldKeys() {
        return currentNumberOfGoldKeys;
    }

    public void setCurrentNumberOfGoldKeys(int currentNumberOfGoldKeys) {
        this.currentNumberOfGoldKeys = currentNumberOfGoldKeys;
        repaint(new Rectangle(Values.GAME_STATUS_BAR_X,Values.GAME_STATUS_BAR_Y,Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
    }

    public int getCurrentNumberOfSilverKeys() {
        return currentNumberOfSilverKeys;
    }

    public void setCurrentNumberOfSilverKeys(int currentNumberOfSilverKeys) {
        this.currentNumberOfSilverKeys = currentNumberOfSilverKeys;
        repaint(new Rectangle(Values.GAME_STATUS_BAR_X,Values.GAME_STATUS_BAR_Y,Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
    }

    public int getCurrentNumberOfPurpleDiamonds() {
        return currentNumberOfPurpleDiamonds;
    }

    public void setCurrentNumberOfPurpleDiamonds(int currentNumberOfPurpleDiamonds) {
        this.currentNumberOfPurpleDiamonds = currentNumberOfPurpleDiamonds;
        repaint(new Rectangle(Values.GAME_STATUS_BAR_X,Values.GAME_STATUS_BAR_Y,Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
    }

    public int getCurrentNumberOfRedDiamonds() {
        return currentNumberOfRedDiamonds;
    }

    public void setCurrentNumberOfRedDiamonds(int currentNumberOfRedDiamonds) {
        this.currentNumberOfRedDiamonds = currentNumberOfRedDiamonds;
        repaint(new Rectangle(Values.GAME_STATUS_BAR_X,Values.GAME_STATUS_BAR_Y,Values.GAME_STATUS_BAR_WIDTH,Values.GAME_STATUS_BAR_LENGTH));
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;

    }

    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setSize(700,800);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StatusBarPanel statusBarPanel = new StatusBarPanel();
        f.add(statusBarPanel);
        f.setVisible(true);
        Timer t = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBarPanel.setCurrentEnergyLevel(statusBarPanel.getCurrentEnergyLevel()-5);
            }
        });
        t.start();
    }

    public void animate(AnimatableImage image){
        image.image = new ImageIcon("statusBar/"+image.name+"Shade.png").getImage();
        repaint();
        Timer t = new Timer(150, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.image = new ImageIcon("statusBar/"+image.name+".png").getImage();
                StatusBarPanel.this.repaint();
                t.stop();
            }
        });
        t.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Rectangle2D.Double pause = new Rectangle2D.Double(Values.PAUSE_BUTTON_X,Values.PAUSE_BUTTON_Y,Values.PAUSE_BUTTON_WIDTH,Values.PAUSE_BUTTON_LENGTH);
        Rectangle2D.Double checkpoint = new Rectangle2D.Double(Values.CHECKPOINT_BUTTON_X,Values.CHECKPOINT_BUTTON_Y,Values.CHECKPOINT_BUTTON_WIDTH,Values.CHECKPOINT_BUTTON_LENGTH);
        Rectangle2D.Double energyLevel = new Rectangle2D.Double(Values.ENERGY_FIELD_X,Values.ENERGY_FIELD_Y,Values.ENERGY_FIELD_WIDTH, Values.ENERGY_FIELD_LENGTH);
        if (pause.contains(e.getX(),e.getY())){
            System.out.println("Pause button");
            animate(pauseImage);
//            OptionsPanel optionsPanel = new OptionsPanel();
        }
        if (checkpoint.contains(e.getX(),e.getY())){
            animate(checkpointImage);
            System.out.println("Checkpoint button");
//            there should be the method that moves boy to the checkpoint
        }
        if (energyLevel.contains(e.getX(),e.getY())){
            animate(energyLevelImage);
            animate(energyImage);
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
}