package source;

public class LevelUI {

    private StatusBarPanel statusBarPanel;
    private GameFrame gameFrame;
    private PlayPanel playPanel;

    /**
     * constructor with parameters
     * @param gameFrame
     * @param playPanel
     */
    public LevelUI(GameFrame gameFrame, PlayPanel playPanel){
        this.gameFrame = gameFrame;
        this.playPanel = playPanel;
        initStatusBar();
    }


    /**
     * initialises status bar panel
     */
    private void initStatusBar() {
        statusBarPanel = playPanel.getStatusBarPanel();
    }

    /**
     * restarts current level
     */
    public void restart(){
        playPanel.restart();
    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    public StatusBarPanel getStatusBarPanel() {
        return statusBarPanel;
    }

}
