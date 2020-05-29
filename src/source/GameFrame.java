package source;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private MainMenu mainMenu;
    private MapPanel mapPanel;
    private PuzzlePanel puzzlePanel;
//    private PlayPanel currentPlayPanel;
    private StatusBarPanel currentStatusBar;



    public GameFrame(){
        super();
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        setLocation(300,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setUndecorated(false);
        //ProgressStorage progressStorage = new ProgressStorage();
//        setVisible(true);
    }

    private void init() {
        mainMenu = new MainMenu(this);
        mapPanel = new MapPanel(this);
        puzzlePanel = new PuzzlePanel(this);
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

    public void showPuzzleMap(){
        getContentPane().removeAll();
        getContentPane().add(puzzlePanel);
        revalidate();
        repaint();
    }

    public void updatePuzzlePanel(int level,boolean artefactIsCollected){
        switch (level){
            case 1:
                if (artefactIsCollected)
                    puzzlePanel.drawFirst = true;
                break;
            case 2:
                if (artefactIsCollected)
                    puzzlePanel.drawSecond = true;
                break;
            case 3:
                if (artefactIsCollected)
                    puzzlePanel.drawThird = true;
                break;
            case 4:
                if (artefactIsCollected)
                    puzzlePanel.drawFourth = true;
                break;
            case 5:
                if (artefactIsCollected)
                    puzzlePanel.drawFifth = true;
                break;
        }
        if (puzzlePanel.drawFirst&&
        puzzlePanel.drawSecond&&
        puzzlePanel.drawThird&&
        puzzlePanel.drawFourth&&
        puzzlePanel.drawFifth)
            puzzlePanel.showCongrats = true;
    }

    public boolean showCongrats(){
        return puzzlePanel.showCongrats;
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
