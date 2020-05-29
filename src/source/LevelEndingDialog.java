package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LevelEndingDialog extends JDialog implements MouseListener {

    private GameFrame gameFrame;
    private PlayPanel playPanel;

    private Image backgroundImage1 = new ImageIcon("levelEndingDialog/levelEndingBG1.png").getImage();
    private Image backgroundImage2 = new ImageIcon("levelEndingDialog/levelEndingBG2.png").getImage();
    private Image backgroundImage3 = new ImageIcon("levelEndingDialog/levelEndingBG3.png").getImage();
    private Image backgroundImage4 = new ImageIcon("levelEndingDialog/levelEndingBG4.png").getImage();
    private Image backgroundImage5 = new ImageIcon("levelEndingDialog/levelEndingBG5.png").getImage();

    private Image pieceVisible1 = new ImageIcon("levelEndingDialog/pieceVisible1.png").getImage();
    private Image pieceVisible2 = new ImageIcon("levelEndingDialog/pieceVisible3.png").getImage();
    private Image pieceVisible3 = new ImageIcon("levelEndingDialog/pieceVisible2.png").getImage();
    private Image pieceVisible4 = new ImageIcon("levelEndingDialog/pieceVisible4.png").getImage();
    private Image pieceVisible5 = new ImageIcon("levelEndingDialog/pieceVisible5.png").getImage();

    private Image pieceInvisible1 = new ImageIcon("levelEndingDialog/pieceInvisible1.png").getImage();
    private Image pieceInvisible2 = new ImageIcon("levelEndingDialog/pieceInvisible3.png").getImage();
    private Image pieceInvisible3 = new ImageIcon("levelEndingDialog/pieceInvisible2.png").getImage();
    private Image pieceInvisible4 = new ImageIcon("levelEndingDialog/pieceInvisible4.png").getImage();
    private Image pieceInvisible5 = new ImageIcon("levelEndingDialog/pieceInvisible5.png").getImage();

    private Image diamonds = new ImageIcon("levelEndingDialog/diamonds.png").getImage();
    private Image redDiamonds = new ImageIcon("levelEndingDialog/redDiamonds.png").getImage();
    private Image revivals = new ImageIcon("levelEndingDialog/revivals.png").getImage();
    private AnimatableImage continueB = new AnimatableImage("levelEndingDialog/continue.png");
    private AnimatableImage restartB = new AnimatableImage("levelEndingDialog/restart.png");
    private AnimatableImage goToMapB = new AnimatableImage("levelEndingDialog/goToMap.png");

    private Font font;
    private Color green = new Color(0, 161, 25);
    private Color red = new Color(255, 0, 10);

    public LevelEndingDialog(Frame owner) {
        super(owner);
        setUndecorated(true);
        setSize(500, 700);
        setLocation(90, 75);
        getRootPane().setOpaque(false);
        setBackground (new Color (0, 0, 0, 0));
        addMouseListener(this);
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",24f);
    }

    public LevelEndingDialog(GameFrame gameFrame, PlayPanel playPanel) {
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

        Image artifact = null;
        Image bg = null;
        boolean purpleDiamondsCheck = playPanel.numberOfPurpleDiamondsCollected==playPanel.currentLevel.getMaxNumberOfPurpleDiamonds();
        boolean redDiamondsCheck = playPanel.numberOfRedDiamondsCollected==playPanel.currentLevel.getMaxNumberOfRedDiamonds();
        switch(playPanel.currentLevel.getLevelNumber()){
            case 1:
                if(purpleDiamondsCheck && redDiamondsCheck){
                    artifact = pieceVisible1;
                }else{
                    artifact = pieceInvisible1;
                }
                bg = backgroundImage1;
                break;
            case 2:
                if(purpleDiamondsCheck && redDiamondsCheck){
                    artifact = pieceVisible2;
                }else{
                    artifact = pieceInvisible2;
                }
                bg = backgroundImage2;
                break;
            case 3:
                if(purpleDiamondsCheck && redDiamondsCheck){
                    artifact = pieceVisible3;
                }else{
                    artifact = pieceInvisible3;
                }
                bg = backgroundImage3;
                break;
            case 4:
                if(purpleDiamondsCheck && redDiamondsCheck){
                    artifact = pieceVisible4;
                }else{
                    artifact = pieceInvisible4;
                }
                bg = backgroundImage4;
                break;
            case 5:
                if(purpleDiamondsCheck && redDiamondsCheck){
                    artifact = pieceVisible5;
                }else{
                    artifact = pieceInvisible5;
                }
                bg = backgroundImage5;
                break;
        }
        g2.drawImage(bg,Values.END_LEVEL_BG_X,Values.END_LEVEL_BG_Y,Values.END_LEVEL_BG_WIDTH,
                Values.END_LEVEL_BG_HEIGHT,null);
        g2.drawImage(diamonds,Values.END_LEVEL_DIAMOND_X,Values.END_LEVEL_DIAMOND_Y,Values.END_LEVEL_DIAMOND_WIDTH,
                Values.END_LEVEL_DIAMOND_HEIGHT,null);
        g2.drawImage(redDiamonds,Values.END_LEVEL_RED_DIAMOND_X,Values.END_LEVEL_RED_DIAMOND_Y,
                Values.END_LEVEL_RED_DIAMOND_WIDTH,Values.END_LEVEL_RED_DIAMOND_HEIGHT,null);
        g2.drawImage(revivals,Values.END_LEVEL_REVIVALS_X,Values.END_LEVEL_REVIVALS_Y,
                Values.END_LEVEL_REVIVALS_WIDTH,Values.END_LEVEL_REVIVALS_HEIGHT,null);
        g2.drawImage(artifact,Values.END_LEVEL_ARTIFACT_PIECE_X,Values.END_LEVEL_ARTIFACT_PIECE_Y,
                Values.END_LEVEL_ARTIFACT_PIECE_WIDTH,Values.END_LEVEL_ARTIFACT_PIECE_HEIGHT,null);

        g2.drawImage(continueB.image,Values.END_LEVEL_CONTINUE_X,Values.END_LEVEL_CONTINUE_Y,Values.END_LEVEL_CONTINUE_WIDTH,
                Values.END_LEVEL_CONTINUE_HEIGHT,null);
        g2.drawImage(restartB.image,Values.END_LEVEL_RESTART_X,Values.END_LEVEL_RESTART_Y,Values.END_LEVEL_RESTART_WIDTH,
                Values.END_LEVEL_RESTART_HEIGHT,null);
        g2.drawImage(goToMapB.image,Values.END_LEVEL_GOTOMAP_X,Values.END_LEVEL_GOTOMAP_Y,Values.END_LEVEL_GOTOMAP_WIDTH,
                Values.END_LEVEL_GOTOMAP_HEIGHT,null);

        String diamonds = playPanel.numberOfPurpleDiamondsCollected + "/" + playPanel.currentLevel.getMaxNumberOfPurpleDiamonds();
        String redDiamonds = playPanel.numberOfRedDiamondsCollected + "/" + playPanel.currentLevel.getMaxNumberOfRedDiamonds();

        g2.setFont(font);
        if(purpleDiamondsCheck){
            g2.setColor(green);
        }else{
            g2.setColor(red);
        }
        g2.drawString(diamonds,Values.END_LEVEL_DIAMOND_X+Values.END_LEVEL_RESULT_SHIFT_X-calculateExtraShift(diamonds),
                Values.END_LEVEL_DIAMOND_Y+Values.END_LEVEL_RESULT_SHIFT_Y);
        if(redDiamondsCheck){
            g2.setColor(green);
        }else{
            g2.setColor(red);
        }
        g2.drawString(redDiamonds,Values.END_LEVEL_RED_DIAMOND_X+Values.END_LEVEL_RESULT_SHIFT_X-calculateExtraShift(redDiamonds),
                Values.END_LEVEL_RED_DIAMOND_Y+Values.END_LEVEL_RESULT_SHIFT_Y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double continueB = new Rectangle2D.Double(Values.END_LEVEL_CONTINUE_X,Values.END_LEVEL_CONTINUE_Y,
                Values.END_LEVEL_CONTINUE_WIDTH, Values.END_LEVEL_CONTINUE_HEIGHT);
        Rectangle2D.Double restartB = new Rectangle2D.Double(Values.END_LEVEL_RESTART_X,Values.END_LEVEL_RESTART_Y,
                Values.END_LEVEL_RESTART_WIDTH, Values.END_LEVEL_RESTART_HEIGHT);
        Rectangle2D.Double goToMapB = new Rectangle2D.Double(Values.END_LEVEL_GOTOMAP_X,Values.END_LEVEL_GOTOMAP_Y,
                Values.END_LEVEL_GOTOMAP_WIDTH, Values.END_LEVEL_GOTOMAP_HEIGHT);

        if (continueB.contains(point)){
            this.continueB.animate(this,"levelEndingDialog",continueB);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    LevelEndingDialog.this.dispose();
                    //next level
                }
            });
        }else if (restartB.contains(point)){
            this.restartB.animate(this,"levelEndingDialog",restartB);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LevelEndingDialog.this.dispose();
                    playPanel.restart();
                }
            });
        }else if (goToMapB.contains(point)){
            this.goToMapB.animate(this,"levelEndingDialog",goToMapB);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LevelEndingDialog.this.dispose();
                    playPanel.restart();
                    gameFrame.showMap();
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
        LevelEndingDialog p = new LevelEndingDialog(f);
        f.setVisible(true);
        p.setVisible(true);

    }
}


