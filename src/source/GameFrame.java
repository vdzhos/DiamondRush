package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame {

    private MainMenu mainMenu;
    private MapPanel mapPanel;
//    private PlayPanel currentPlayPanel;
    private StatusBarPanel currentStatusBar;



    public GameFrame(){
        super();
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        setLocation(300,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setUndecorated(false);
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
        level.restart();
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        currentStatusBar = level.getStatusBarPanel();
        getContentPane().add(level.getStatusBarPanel(), BorderLayout.NORTH);
        getContentPane().add(level.getPlayPanel(),BorderLayout.CENTER);
        addKeyListener(level.getPlayPanel());
        revalidate();
        repaint();
    }

    public void updateNumberOfPurpleDiamondsOnStatusBar(int currentNumberOfPurpleDiamonds){
        currentStatusBar.setCurrentNumberOfPurpleDiamonds(currentNumberOfPurpleDiamonds);
    }

    public void updateNumberOfRedDiamondsOnStatusBar(int currentNumberOfRedDiamonds){
        currentStatusBar.setCurrentNumberOfRedDiamonds(currentNumberOfRedDiamonds);
    }

    public void updateNumberOfGoldKeysOnStatusBar(int currentNumberOfGoldKeys){
        currentStatusBar.setCurrentNumberOfGoldKeys(currentNumberOfGoldKeys);
    }

    public void updateNumberOfSilverKeysOnStatusBar(int currentNumberOfSilverKeys){
        currentStatusBar.setCurrentNumberOfSilverKeys(currentNumberOfSilverKeys);
    }

    public void updateEnergyLevelOnStatusBar(int currentEnergyLevel){
        currentStatusBar.setCurrentEnergyLevel(currentEnergyLevel);
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
//                Timer t = new Timer(1000, new AbstractAction() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (gameFrame.currentStatusBar != null) {
//                            gameFrame.currentStatusBar.setCurrentEnergyLevel(gameFrame.currentStatusBar.getCurrentEnergyLevel() - 5);
//                            gameFrame.currentStatusBar.setCurrentNumberOfPurpleDiamonds(gameFrame.currentStatusBar.getCurrentNumberOfGoldKeys() + 1);
//                        }
//                    }
//                });
//                t.start();
            }
        });
    }





}
