package source;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private MainMenu mainMenu;
    private MapPanel mapPanel;
//    private PlayPanel currentPlayPanel;


    public GameFrame(){
        super();
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        setLocation(300,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setUndecorated(true);
//        setVisible(true);
    }

    private void init() {
        mainMenu = new MainMenu(this);
        mapPanel = new MapPanel(this);
    }

    public void showMainMenu(){
        getContentPane().removeAll();
        getContentPane().add(mainMenu);
        revalidate();
        repaint();
    }

    public void setNewMap(){
        mapPanel = new MapPanel(this);
    }

    public void showMap(){
        getContentPane().removeAll();
        getContentPane().add(mapPanel);
        revalidate();
        repaint();
    }

    public void showLevel(LevelUI level){
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        getContentPane().add(level.getStatusBarPanel(), BorderLayout.NORTH);
        getContentPane().add(level.getPlayPanel(),BorderLayout.CENTER);
        addKeyListener(level.getPlayPanel());
        revalidate();
        repaint();
    }

//    public PlayPanel getCurrentPlayPanel() {
//        return currentPlayPanel;
//    }

    public void start(){
        showMainMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameFrame gameFrame = new GameFrame();
                gameFrame.setVisible(true);
                gameFrame.start();
            }
        });
    }





}
