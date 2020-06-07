package source;

 import javax.sound.sampled.Clip;
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import java.awt.geom.Point2D;
 import java.awt.geom.Rectangle2D;

public class DeathDialog extends JDialog implements MouseListener {

    private GameFrame gameFrame;
    private PlayPanel playPanel;

    private Image backgroundImage1 = new ImageIcon("DeathDialog/deathDialogBG1.png").getImage();
    private Image backgroundImage2 = new ImageIcon("DeathDialog/deathDialogBG2.png").getImage();
    private Image backgroundImage3 = new ImageIcon("DeathDialog/deathDialogBG3.png").getImage();
    private Image backgroundImage4 = new ImageIcon("DeathDialog/deathDialogBG4.png").getImage();
    private Image backgroundImage5 = new ImageIcon("DeathDialog/deathDialogBG5.png").getImage();

    private Image diamonds = new ImageIcon("DeathDialog/diamonds.png").getImage();
    private Image redDiamonds = new ImageIcon("DeathDialog/redDiamonds.png").getImage();
    private Image revivals = new ImageIcon("DeathDialog/revivals.png").getImage();
    private AnimatableImage restartB = new AnimatableImage("DeathDialog/restart.png");
    private AnimatableImage goToMapB = new AnimatableImage("DeathDialog/goToMap.png");

    private Font font;
    private Color green = new Color(0, 114, 26);
    private Color red = new Color(153, 0, 8);

    public DeathDialog(Frame owner) {
        super(owner);
        setUndecorated(true);
        setSize(500, 700);
        setLocation(90, 75);
        getRootPane().setOpaque(false);
        setBackground (new Color (0, 0, 0, 0));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",24f);
    }

    public DeathDialog(GameFrame gameFrame, PlayPanel playPanel) {
        super(gameFrame, "", true);
        this.playPanel = playPanel;
        this.gameFrame = gameFrame;
        setUndecorated(true);
        setSize(500, 700);
        setLocation(gameFrame.getX()+ 90, gameFrame.getY()+ 75);
        getRootPane().setOpaque(false);
        setBackground (new Color (0, 0, 0, 0));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",24f);
        setVisible(true);
    }

    public void paint(Graphics gr){
        Graphics2D g2 = (Graphics2D) gr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        Image bg = null;
        boolean purpleDiamondsCheck = playPanel.numberOfPurpleDiamondsCollected==playPanel.currentLevel.getMaxNumberOfPurpleDiamonds();
        boolean redDiamondsCheck = playPanel.numberOfRedDiamondsCollected==playPanel.currentLevel.getMaxNumberOfRedDiamonds();
        switch(playPanel.currentLevel.getLevelNumber()){
            case 1:
                bg = backgroundImage1;
                break;
            case 2:
                bg = backgroundImage2;
                break;
            case 3:
                bg = backgroundImage3;
                break;
            case 4:
                bg = backgroundImage4;
                break;
            case 5:
                bg = backgroundImage5;
                break;
        }
        g2.drawImage(bg,Values.END_LEVEL_BG_X,Values.END_LEVEL_BG_Y,Values.END_LEVEL_BG_WIDTH,
                Values.END_LEVEL_BG_HEIGHT,null);
        g2.drawImage(diamonds,Values.END_LEVEL_DIAMOND_X,Values.DEATH_DIALOG_DIAMOND_Y,Values.END_LEVEL_DIAMOND_WIDTH,
                Values.END_LEVEL_DIAMOND_HEIGHT,null);
        g2.drawImage(redDiamonds,Values.END_LEVEL_RED_DIAMOND_X,Values.DEATH_DIALOG_RED_DIAMOND_Y,
                Values.END_LEVEL_RED_DIAMOND_WIDTH,Values.END_LEVEL_RED_DIAMOND_HEIGHT,null);
        g2.drawImage(revivals,Values.END_LEVEL_REVIVALS_X,Values.DEATH_DIALOG_REVIVALS_Y,
                Values.END_LEVEL_REVIVALS_WIDTH,Values.END_LEVEL_REVIVALS_HEIGHT,null);

        g2.drawImage(restartB.image,Values.DEATH_DIALOG_RESTART_X,Values.DEATH_DIALOG_RESTART_Y,
                Values.DEATH_DIALOG_RESTART_WIDTH,Values.DEATH_DIALOG_RESTART_HEIGHT,null);
        g2.drawImage(goToMapB.image,Values.DEATH_DIALOG_GOTOMAP_X,Values.DEATH_DIALOG_GOTOMAP_Y,
                Values.DEATH_DIALOG_GOTOMAP_WIDTH,Values.DEATH_DIALOG_GOTOMAP_HEIGHT,null);

        String diamonds = playPanel.numberOfPurpleDiamondsCollected + "/" + playPanel.currentLevel.getMaxNumberOfPurpleDiamonds();
        String redDiamonds = playPanel.numberOfRedDiamondsCollected + "/" + playPanel.currentLevel.getMaxNumberOfRedDiamonds();
        String revivals = String.valueOf(playPanel.revivals);

        g2.setFont(font);
        if(purpleDiamondsCheck){
            g2.setColor(green);
        }else{
            g2.setColor(red);
        }
        g2.drawString(diamonds,Values.END_LEVEL_DIAMOND_X+Values.END_LEVEL_RESULT_SHIFT_X-calculateExtraShift(diamonds),
                Values.DEATH_DIALOG_DIAMOND_Y+Values.END_LEVEL_RESULT_SHIFT_Y);
        if(redDiamondsCheck){
            g2.setColor(green);
        }else{
            g2.setColor(red);
        }
        g2.drawString(redDiamonds,Values.END_LEVEL_RED_DIAMOND_X+Values.END_LEVEL_RESULT_SHIFT_X-calculateExtraShift(redDiamonds),
                Values.DEATH_DIALOG_RED_DIAMOND_Y+Values.END_LEVEL_RESULT_SHIFT_Y);
        if(playPanel.revivals==0){
            g2.setColor(green);
        }else{
            g2.setColor(red);
        }
        g2.drawString(revivals,Values.END_LEVEL_REVIVALS_X+Values.END_LEVEL_RESULT_SHIFT_X-calculateExtraShift(revivals),
                Values.DEATH_DIALOG_REVIVALS_Y+Values.END_LEVEL_RESULT_SHIFT_Y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double restartB = new Rectangle2D.Double(Values.DEATH_DIALOG_RESTART_X,Values.DEATH_DIALOG_RESTART_Y,
                Values.DEATH_DIALOG_RESTART_WIDTH,Values.DEATH_DIALOG_RESTART_HEIGHT);
        Rectangle2D.Double goToMapB = new Rectangle2D.Double(Values.DEATH_DIALOG_GOTOMAP_X,Values.DEATH_DIALOG_GOTOMAP_Y,
                Values.DEATH_DIALOG_GOTOMAP_WIDTH,Values.DEATH_DIALOG_GOTOMAP_HEIGHT);

        if (restartB.contains(point)){
            Util.click();
            this.restartB.animate(this,"DeathDialog",restartB);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DeathDialog.this.dispose();
                    playPanel.restart();
                }
            });
        }else if (goToMapB.contains(point)){
            Util.click();
            this.goToMapB.animate(this,"DeathDialog",goToMapB);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DeathDialog.this.dispose();
                    playPanel.turnOffTrapObjectsSounds();
                    gameFrame.showMap();
                    gameFrame.startBgMenuClip();
                }
            });
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

    private int calculateExtraShift(String str){
        int ones = str.length()-str.replace("1","").length();
        return (str.length()-1)*8-ones*2;
    }

    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setSize(700,820);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DeathDialog p = new DeathDialog(f);
        f.setVisible(true);
        p.setVisible(true);

    }
}



