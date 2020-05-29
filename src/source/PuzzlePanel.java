package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.security.Key;

public class PuzzlePanel extends JPanel implements MouseListener {

    private Image image = new ImageIcon("puzzles/puzzleBack.jpg").getImage();

    private Image puzzle1 = new ImageIcon("puzzles/puzzle1.png").getImage();
    private Image puzzle2 = new ImageIcon("puzzles/puzzle3.png").getImage();
    private Image puzzle3 = new ImageIcon("puzzles/puzzle2.png").getImage();
    private Image puzzle4 = new ImageIcon("puzzles/puzzle4.png").getImage();
    private Image puzzle5 = new ImageIcon("puzzles/puzzle5.png").getImage();

    private AnimatableImage goToMap = new AnimatableImage("puzzles/goToMap.png");

    public boolean drawFirst = true;
    public boolean drawSecond= true;
    public boolean drawThird= false;
    public boolean drawFourth= true;
    public boolean drawFifth= false;

    private GameFrame gameFrame;

    public PuzzlePanel(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        addMouseListener(this);
    }










    public void paint(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawBack(g);
        drawGoToMap(g);
        if (drawFirst)
            drawFirst(g);
        if (drawSecond)
            drawSecond(g);
        if (drawThird)
            drawThird(g);
        if (drawFourth)
            drawFourth(g);
        if (drawFifth)
            drawFifth(g);

    }

    private void drawGoToMap(Graphics2D g) {
        g.drawImage(goToMap.image, Values.BACK_TOMAP_X,Values.BACK_TOMAP_Y,Values.BACK_TOMAP_WIDTH,Values.BACK_TOMAP_LENGTH,null);
    }

    private void drawBack(Graphics2D g) {
        g.drawImage(image, 0,0,Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH,null);
    }

    private void drawFifth(Graphics2D g) {
        int n = puzzle5.getWidth(null);
        int m = puzzle5.getHeight(null);
        n /=2.5;
        m /= 2.5;
        g.drawImage(puzzle5,Values.PUZZLE_5_X, Values.PUZZLE_5_Y,n,m,null );
    }

    private void drawFourth(Graphics2D g) {
        int n = puzzle4.getWidth(null);
        int m = puzzle4.getHeight(null);
        n /=2.5;
        m /= 2.5;
        g.drawImage(puzzle4,Values.PUZZLE_4_X, Values.PUZZLE_4_Y,n,m,null );
    }

    private void drawThird(Graphics2D g) {
        int n = puzzle3.getWidth(null);
        int m = puzzle3.getHeight(null);
        n /=2.5;
        m /= 2.5;
        g.drawImage(puzzle3,Values.PUZZLE_3_X, Values.PUZZLE_3_Y,n,m,null );
    }

    private void drawSecond(Graphics2D g) {
        int n = puzzle2.getWidth(null);
        int m = puzzle2.getHeight(null);
        n /=2.5;
        m /= 2.5;
        g.drawImage(puzzle2,Values.PUZZLE_2_X, Values.PUZZLE_2_Y,n, m,null );
    }

    private void drawFirst(Graphics2D g) {
        int n = puzzle1.getWidth(null);
        int m = puzzle1.getHeight(null);
        n /= Values.COEF;
        m /= Values.COEF;
        g.drawImage(puzzle1,Values.PUZZLE_1_X, Values.PUZZLE_1_Y,n, m,null );
    }







    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame();
                f.setSize(700,820);

                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.setLayout(new BorderLayout());
                f.add(new PuzzlePanel(new GameFrame()));
                f.setVisible(true);
            }
        });
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double backToMap = new Rectangle2D.Double(Values.BACK_TOMAP_X, Values.BACK_TOMAP_Y, Values.BACK_TOMAP_WIDTH, Values.BACK_TOMAP_LENGTH);
        if (backToMap.contains(point)){
            goToMap.animate(PuzzlePanel.this,"puzzles");
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
}
