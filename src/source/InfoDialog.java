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
 * @author Volodymyr Dzhosan
 */
public class InfoDialog extends JDialog implements MouseListener {

    private Image background = new ImageIcon("infoDialog/background.png").getImage();
    private Image infoLabel = new ImageIcon("infoDialog/infoLabel.png").getImage();
    private AnimatableImage closeImage = new AnimatableImage("infoDialog/close1.png");

    private Info info;
    private JScrollPane scrollPane;
    private GameFrame gameFrame;

    /**
     * constructor with parameters
     * @param gameFrame
     */
    public InfoDialog(GameFrame gameFrame){
        super(gameFrame,"",true);
        this.gameFrame = gameFrame;
        setUndecorated(true);
        setSize(700, 850);
        setLocation(gameFrame.getLocation());
        getRootPane().setOpaque(false);
        setBackground (new Color(0, 0, 0, 0));
        init();
        addMouseListener(this);
    }


    /**
     * method initialises all the fields
     */
    private void init() {
        info = new Info();
        scrollPane = new JScrollPane(info);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void paint(Graphics gr) {
        Graphics2D g2 = (Graphics2D) gr;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(background, 0, 0, 700, 830, null);
        addContent();
        drawLabel(g2);
        drawClose(g2);
    }

    /**
     * draws close button
     * @param g2
     */
    private void drawClose(Graphics2D g2) {
        g2.drawImage(closeImage.image,Values.CLOSE_X, Values.CLOSE_Y, Values.CLOSE_WIDTH, Values.CLOSE_HEIGHT,null);
    }

    /**
     * draws label of the dialog window
     * @param g2
     */
    private void drawLabel(Graphics2D g2) {
        g2.drawImage(infoLabel, Values.INFO_LABEL_X, Values.INFO_LABEL_Y, Values.INFO_LABEL_WIDTH, Values.INFO_LABEL_HEIGHT, null);
    }

    /**
     * method adds inner panels to the dialog window
     */
    private void addContent() {
        info.setBounds(0,0,100,1200);
        scrollPane.setBounds(59,110,586,605);
        add(scrollPane);
        scrollPane.revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double close = new Rectangle2D.Double(Values.CLOSE_X, Values.CLOSE_Y, Values.CLOSE_WIDTH, Values.CLOSE_HEIGHT);
        if (close.contains(point)){
            Util.click(gameFrame.soundOn);
            closeImage.animate(this, "infoDialog",close);
            Util.wait(300, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InfoDialog.this.setVisible(false);
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


    private class Info extends JLabel{
        /**
         * constructor with no parameters
         */
        public Info(){
            setPreferredSize(new Dimension(586,6248));
        }

        @Override
        public void paint(Graphics gr){
            super.paintComponent(gr);
            Graphics2D g2 = (Graphics2D) gr;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            g2.drawImage(new ImageIcon("infoDialog/instruction1.png").getImage(), 0,0,586,6248,null);
        }
    }



}
