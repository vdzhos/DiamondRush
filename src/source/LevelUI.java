package source;

public class LevelUI {

    private StatusBarPanel statusBarPanel;
    private GameFrame gameFrame;
//    private MapPanel mapPanel;
    private PlayPanel playPanel;

    //proper constructor
    public LevelUI(GameFrame gameFrame, PlayPanel playPanel){
        this.gameFrame = gameFrame;
//        this.mapPanel = mapPanel;
        this.playPanel = playPanel;
        initStatusBar();
    }

    //demonstrative constructor do not use this constructor!!!
    public LevelUI(GameFrame gameFrame){
        this.gameFrame = gameFrame;
//        initStatusBar();
    }

    private void initStatusBar() {
        // demonstrative version
        statusBarPanel = playPanel.getStatusBarPanel();
        // proper version
//        statusBarPanel = new StatusBarPanel(gameFrame, gamePanel.getLevel(), gamePanel.getEnergyLevel(), gamePanel.getMaxNumberOfGoldKeys, gamePanel.getMaxNumberOfSilverKeys(),gamePanel.getMaxNumberOfPurpleDiamonds().gamePanel.getMaxNumberOfRedDiamonds());
    }


    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    public StatusBarPanel getStatusBarPanel() {
        return statusBarPanel;
    }

    public void setStatusBarPanel(StatusBarPanel statusBarPanel) {
        this.statusBarPanel = statusBarPanel;
    }

//    public GamePanel getGamePanel() {
//        return gamePanel;
//    }
//
//    public void setGamePanel(GamePanel gamePanel) {
//        this.gamePanel = gamePanel;
//    }
}
