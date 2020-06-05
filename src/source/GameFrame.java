package source;

import maps.Level;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private MainMenu mainMenu;
    private MapPanel mapPanel;
    private PuzzlePanel puzzlePanel;
//    private PlayPanel currentPlayPanel;
    private StatusBarPanel currentStatusBar;
    private int currentLevelInt;
    private LevelEndingDialog levelEndingDialog;
    private InfoDialog infoDialog;



    public GameFrame(){
        super();
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        setLocation(300,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        for (int i = 1; i <=5; i++){
            if (ProgressStorage.getLevelIsCompleted(i)) mapPanel.openNextLevel(i);
            updatePuzzlePanel(i, ProgressStorage.getArtifactIsCollected(i));
        }
        setUndecorated(false);
        setResizable(false);
        //ProgressStorage progressStorage = new ProgressStorage();
//        setVisible(true);
    }

    private void init() {
        mainMenu = new MainMenu(this);
        mapPanel = new MapPanel(this);
        puzzlePanel = new PuzzlePanel(this);
        infoDialog = new InfoDialog(this);
    }
    public void showInfo(){
       infoDialog.setVisible(true);
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
        currentLevelInt = level.getPlayPanel().getCurrentLevelInt();
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

//    public void showLevelEndingDialog(PlayPanel playPanel){
//        levelEndingDialog = new LevelEndingDialog(this,playPanel);
//        levelEndingDialog.setVisible(true);
//    }

//    public void restartLevel(){
//        mapPanel.restartLevel(currentLevelInt);
//        showLevel(mapPanel.getLevel(currentLevelInt));
//    }

    public void showNextLevel(){
        if (currentLevelInt < 5){
            showLevel(mapPanel.getLevel(currentLevelInt+1));
        }
    }

    public void showPuzzleMap(){
        getContentPane().removeAll();
        getContentPane().add(puzzlePanel);
        revalidate();
        repaint();
    }

    public void updatePuzzlePanel(int level, boolean artefactIsCollected){
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

    public void stop(){
        mainMenu = null;
        mapPanel = null;
        currentStatusBar = null;
        puzzlePanel = null;
        dispose();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
