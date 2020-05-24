package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MainMenu extends JPanel implements MouseListener {
    
    
    private Image backgroundImage = new ImageIcon("mainMenu/mainBack.jpg").getImage();
    private GameFrame gameFrame;
    
    private AnimatableImage playImage = new AnimatableImage("mainMenu/play.png");
    private AnimatableImage newGameImage = new AnimatableImage("mainMenu/newGame.png");
    private AnimatableImage exitImage = new AnimatableImage("mainMenu/exit.png");

    public MainMenu(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        addMouseListener(this);
    }




    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBack(g);
        drawPlay(g);
        drawNewGame(g);
        drawExit(g);

    }

    private void drawExit(Graphics2D g) {
        g.drawImage(exitImage.image, Values.EXIT_X, Values.EXIT_Y, Values.EXIT_WIDTH, Values.EXIT_LENGTH,null);
    }

    private void drawNewGame(Graphics2D g) {
        g.drawImage(newGameImage.image, Values.NEW_GAME_X, Values.NEW_GAME_Y, Values.NEW_GAME_WIDTH, Values.NEW_GAME_LENGTH,null);
    }

    private void drawPlay(Graphics2D g) {
        g.drawImage(playImage.image, Values.PLAY_X, Values.PLAY_Y, Values.PLAY_WIDTH, Values.PLAY_LENGTH,null);
    }

    private void drawBack(Graphics2D g) {
        g.drawImage(backgroundImage, 0,0, Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH,null);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double play = new Rectangle2D.Double(Values.PLAY_X, Values.PLAY_Y, Values.PLAY_WIDTH, Values.PLAY_LENGTH);
        Rectangle2D.Double newGame = new Rectangle2D.Double(Values.NEW_GAME_X, Values.NEW_GAME_Y, Values.NEW_GAME_WIDTH, Values.NEW_GAME_LENGTH);
        Rectangle2D.Double exit = new Rectangle2D.Double(Values.EXIT_X, Values.EXIT_Y, Values.EXIT_WIDTH, Values.EXIT_LENGTH);
        if (play.contains(point)){
            playImage.animate(this,"mainMenu",play);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showMap();
                }
            });
        }
        if (newGame.contains(point)){
            newGameImage.animate(this,"mainMenu",newGame);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.setNewMap();
                    gameFrame.showMap();
                }
            });

        }
        if (exit.contains(point)){
            exitImage.animate(this,"mainMenu",exit);
//            saves progress and exits
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.dispose();
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

//    public static void main(String[] args) {
//        JFrame f = new JFrame();
//        f.setSize(700,820);
//        f.setLocation(300,0);
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        f.setUndecorated(true);
//        MainMenu m = new MainMenu(f);
//        f.add(m);
//        f.setVisible(true);
//    }
}