package source;

import javax.swing.*;
import java.awt.*;

public class PuzzlePanel extends JPanel {

    private Image image = new ImageIcon("puzzles/puzzleBack.jpg").getImage();

    private Image puzzle1 = new ImageIcon("puzzles/puzzle1.png").getImage();
    private Image puzzle2 = new ImageIcon("puzzles/puzzle3.png").getImage();
    private Image puzzle3 = new ImageIcon("puzzles/puzzle2.png").getImage();
    private Image puzzle4 = new ImageIcon("puzzles/puzzle4.png").getImage();
    private Image puzzle5 = new ImageIcon("puzzles/puzzle5.png").getImage();

    public boolean drawFirst;
    public boolean drawSecond;
    public boolean drawThird;
    public boolean drawFourth;
    public boolean drawFifth;

    public void paint(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
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

    private void drawFifth(Graphics2D g) {
    }

    private void drawFourth(Graphics2D g) {
    }

    private void drawThird(Graphics2D g) {
    }

    private void drawSecond(Graphics2D g) {
    }

    private void drawFirst(Graphics2D g) {
//        g.drawImage(puzzle1, );
    }


}
