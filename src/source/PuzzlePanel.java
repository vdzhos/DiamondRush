package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * @author Illia Sitkov
 */
public class PuzzlePanel extends JPanel implements MouseListener {

    private Image image = new ImageIcon("puzzles/puzzleBack.jpg").getImage();

    private Image puzzle1 = new ImageIcon("puzzles/puzzle1.png").getImage();
    private Image puzzle2 = new ImageIcon("puzzles/puzzle3.png").getImage();
    private Image puzzle3 = new ImageIcon("puzzles/puzzle2.png").getImage();
    private Image puzzle4 = new ImageIcon("puzzles/puzzle4.png").getImage();
    private Image puzzle5 = new ImageIcon("puzzles/puzzle5.png").getImage();
    private Image congrats = new ImageIcon("puzzles/congrats.png").getImage();

    private AnimatableImage goToMap = new AnimatableImage("puzzles/goToMap.png");
    private AnimatableImage menu = new AnimatableImage("puzzles/menu.png");

    public boolean drawFirst = false;
    public boolean drawSecond = false;
    public boolean drawThird = false;
    public boolean drawFourth = false;
    public boolean drawFifth = false;
    public boolean showCongrats = false;
    public boolean hasShownCongrats;
    public boolean showButtonToMap = true;

    private GameFrame gameFrame;

    /**
     * constructor with parameters
     *
     * @param gameFrame
     */
    public PuzzlePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        addMouseListener(this);
    }


    @Override
    public void paint(Graphics gr) {
        if (drawFirst&&
                drawSecond&&
                drawThird&&
                drawFourth&&
                drawFifth)
            showCongrats = true;
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBack(g);
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
        if (showCongrats && !hasShownCongrats)
            drawCongrats(g);
        if (showCongrats && !hasShownCongrats)
            drawMenu(g);
        else drawGoToMap(g);
    }

    /**
     * draws menu button
     *
     * @param g
     */
    private void drawMenu(Graphics2D g) {
        g.drawImage(menu.image, Values.GO_TOMENU_X, Values.GO_TOMENU_Y, Values.GO_TOMENU_WIDTH, Values.GO_TOMENU_HEIGHT, null);
    }

    /**
     * draws congrats
     *
     * @param g
     */
    private void drawCongrats(Graphics2D g) {
        g.drawImage(congrats, Values.CONGRATS_X, Values.CONGRATS_Y, Values.CONGRATS_WIDTH, Values.CONGRATS_HEIGHT, null);
    }

    /**
     * draws go-to-map button
     *
     * @param g
     */
    private void drawGoToMap(Graphics2D g) {
        g.drawImage(goToMap.image, Values.BACK_TOMAP_X, Values.BACK_TOMAP_Y, Values.BACK_TOMAP_WIDTH, Values.BACK_TOMAP_LENGTH, null);
    }

    /**
     * draws background
     *
     * @param g
     */
    private void drawBack(Graphics2D g) {
        g.drawImage(image, 0, 0, Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH, null);
    }

    /**
     * draws 5 piece of puzzle
     *
     * @param g
     */
    private void drawFifth(Graphics2D g) {
        int n = puzzle5.getWidth(null);
        int m = puzzle5.getHeight(null);
        n /= 2.5;
        m /= 2.5;
        g.drawImage(puzzle5, Values.PUZZLE_5_X, Values.PUZZLE_5_Y, n, m, null);
    }

    /**
     * draws 4 piece of puzzle
     *
     * @param g
     */
    private void drawFourth(Graphics2D g) {
        int n = puzzle4.getWidth(null);
        int m = puzzle4.getHeight(null);
        n /= 2.5;
        m /= 2.5;
        g.drawImage(puzzle4, Values.PUZZLE_4_X, Values.PUZZLE_4_Y, n, m, null);
    }

    /**
     * draws 3 piece of puzzle
     *
     * @param g
     */
    private void drawThird(Graphics2D g) {
        int n = puzzle3.getWidth(null);
        int m = puzzle3.getHeight(null);
        n /= 2.5;
        m /= 2.5;
        g.drawImage(puzzle3, Values.PUZZLE_3_X, Values.PUZZLE_3_Y, n, m, null);
    }

    /**
     * method resets values of the object
     */
    public void reset(){
        drawFirst = false;
        drawSecond = false;
        drawThird = false;
        drawFourth = false;
        drawFifth = false;
        showCongrats = false;
        hasShownCongrats = false;
    }

    /**
     * draws 2 piece of puzzle
     *
     * @param g
     */
    private void drawSecond(Graphics2D g) {
        int n = puzzle2.getWidth(null);
        int m = puzzle2.getHeight(null);
        n /= 2.5;
        m /= 2.5;
        g.drawImage(puzzle2, Values.PUZZLE_2_X, Values.PUZZLE_2_Y, n, m, null);
    }

    /**
     * draws 1 piece of puzzle
     *
     * @param g
     */
    private void drawFirst(Graphics2D g) {
        int n = puzzle1.getWidth(null);
        int m = puzzle1.getHeight(null);
        n /= Values.COEF;
        m /= Values.COEF;
        g.drawImage(puzzle1, Values.PUZZLE_1_X, Values.PUZZLE_1_Y, n, m, null);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(), e.getY());
        Rectangle2D.Double backToMap = new Rectangle2D.Double(Values.BACK_TOMAP_X, Values.BACK_TOMAP_Y, Values.BACK_TOMAP_WIDTH, Values.BACK_TOMAP_LENGTH);
        Rectangle2D.Double menu = new Rectangle2D.Double(Values.GO_TOMENU_X, Values.GO_TOMENU_Y, Values.GO_TOMENU_WIDTH, Values.GO_TOMENU_HEIGHT);
        if (backToMap.contains(point) && !(showCongrats && !hasShownCongrats)) {
            Util.click();
            goToMap.animate(PuzzlePanel.this, "puzzles");
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showMap();
                }
            });
        } else if (menu.contains(point) && showCongrats && !hasShownCongrats) {
            Util.click();
            PuzzlePanel.this.menu.animate(PuzzlePanel.this, "puzzles");
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showMainMenu();
                    showCongrats = false;
                    hasShownCongrats = true;
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
