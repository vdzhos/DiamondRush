package source;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
/**
 * @author Illia Sitkov
 */
public class GameFrame extends JFrame {

    private MainMenu mainMenu;
    private MapPanel mapPanel;
    private PuzzlePanel puzzlePanel;
    private StatusBarPanel currentStatusBar;
    private int currentLevelInt;
    private LevelEndingDialog levelEndingDialog;
    private InfoDialog infoDialog;
    public Clip menuBgClip = Util.getSound("sounds/hathor_menu.wav",-30f);
    public Clip levelsBgClip = Util.getSound("sounds/tomb_raiders_levels.wav",-30f);

    /**
     * constructor with no parameters
     */
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
    }

    /**
     * method initialises all the fields
     */
    private void init() {
        mainMenu = new MainMenu(this);
        mapPanel = new MapPanel(this);
        puzzlePanel = new PuzzlePanel(this);
        infoDialog = new InfoDialog(this);
    }

    /**
     * method makes info dialog visible
     */
    public void showInfo(){
       infoDialog.setVisible(true);
    }

    /**
     * method makes main menu visible
     */
    public void showMainMenu(){
        getContentPane().removeAll();
        getContentPane().add(mainMenu);
        revalidate();
        repaint();
    }

    /**
     * method refreshes the map panel
     */
    public void setNewMap(){
        mapPanel = new MapPanel(this);
    }

    /**
     * method shows map panel
     */
    public void showMap(){
        getContentPane().removeAll();
        getContentPane().add(mapPanel);
        revalidate();
        repaint();
    }

    /**
     * method shows level
     * @param level
     */
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

    /**
     * method shows next level
     */
    public void showNextLevel(){
        if (currentLevelInt < 5){
            showLevel(mapPanel.getLevel(currentLevelInt+1));
        }
    }

    /**
     * method shows puzzle map
     */
    public void showPuzzleMap(){
        getContentPane().removeAll();
        getContentPane().add(puzzlePanel);
        revalidate();
        repaint();
    }

    /**
     * method updates puzzle map depending on the level completion
     * @param level
     * @param artefactIsCollected
     */
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

    /**
     * method makes puzzle map show congrats image
     * @return
     */
    public boolean showCongrats(){
        return puzzlePanel.showCongrats;
    }

    /**
     * method updates number of purple diamonds on the status bar
     * @param currentNumberOfPurpleDiamonds
     */
    public void updateNumberOfPurpleDiamondsOnStatusBar(int currentNumberOfPurpleDiamonds){
        currentStatusBar.setCurrentNumberOfPurpleDiamonds(currentNumberOfPurpleDiamonds);
    }

    /**
     * method updates number of red diamonds on the status bar
     * @param currentNumberOfRedDiamonds
     */
    public void updateNumberOfRedDiamondsOnStatusBar(int currentNumberOfRedDiamonds){
        currentStatusBar.setCurrentNumberOfRedDiamonds(currentNumberOfRedDiamonds);
    }

    /**
     * method updates number of gold keys on the status bar
     * @param currentNumberOfGoldKeys
     */
    public void updateNumberOfGoldKeysOnStatusBar(int currentNumberOfGoldKeys){
        currentStatusBar.setCurrentNumberOfGoldKeys(currentNumberOfGoldKeys);
    }

    /**
     * method updates number of silver keys on the status bar
     * @param currentNumberOfSilverKeys
     */
    public void updateNumberOfSilverKeysOnStatusBar(int currentNumberOfSilverKeys){
        currentStatusBar.setCurrentNumberOfSilverKeys(currentNumberOfSilverKeys);
    }

    /**
     * method updates energy level on the status bar
     * @param currentEnergyLevel
     */
    public void updateEnergyLevelOnStatusBar(int currentEnergyLevel){
        currentStatusBar.setCurrentEnergyLevel(currentEnergyLevel);
    }

    /**
     * method starts the game frame
     */
    public void start(){
        showMainMenu();
        menuBgClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * method stops the running of the program
     */
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
            }
        });
    }





}
