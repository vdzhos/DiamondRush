public class LevelUI {

    private StatusBarPanel statusBarPanel;
    private GameFrame gameFrame;
    private MapPanel mapPanel;
//    private GamePanel gamePanel;

    //proper constructor
//    public Level(GameFrame gameFrame, GamePanel gamePanel, MapPanel mapPanel){
//        this.gameFrame = gameFrame;
//        this.mapPanel = mapPanel;
//        this.gamePanel = gamePanel;
//        initStatusBar();
//    }

    //demonstrative constructor do not use this constructor!!!
    public LevelUI(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        initStatusBar();
    }

    private void initStatusBar() {
        // demonstrative version
        statusBarPanel = new StatusBarPanel(gameFrame);
        // proper version
//        statusBarPanel = new StatusBarPanel(gameFrame, gamePanel.getLevel(), gamePanel.getEnergyLevel(), gamePanel.getMaxNumberOfGoldKeys, gamePanel.getMaxNumberOfSilverKeys(),gamePanel.getMaxNumberOfPurpleDiamonds().gamePanel.getMaxNumberOfRedDiamonds());
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
