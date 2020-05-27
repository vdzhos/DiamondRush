package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class PauseMenuDialog extends JDialog implements MouseListener {

    private GameFrame gameFrame;
    private PlayPanel playPanel;

    private Image backgroundImage = new ImageIcon("pauseMenu/pauseBackCut1.png").getImage();
    private Image chestImage = new ImageIcon("pauseMenu/treasureChest.png").getImage();

    private Image pausedImage = new ImageIcon("pauseMenu/paused.png").getImage();

    private AnimatableImage resumeImage = new AnimatableImage("pauseMenu/resume.png");
    private AnimatableImage restartImage = new AnimatableImage("pauseMenu/restart.png");
    private AnimatableImage goToMapImage = new AnimatableImage("pauseMenu/goToMap.png");

    private AnimatableImage soundOnImage = new AnimatableImage("pauseMenu/soundOn.png");
    private AnimatableImage soundOffImage = new AnimatableImage("pauseMenu/soundOff.png");
    private AnimatableImage sound = soundOnImage;

    private AnimatableImage musicOnImage = new AnimatableImage("pauseMenu/musicOn.png");
    private AnimatableImage musicOffImage = new AnimatableImage("pauseMenu/musicOff.png");
    private AnimatableImage music = musicOnImage;

    private boolean soundOn = true;
    private boolean musicOn = true;


    public PauseMenuDialog(GameFrame gameFrame) {
        super(gameFrame, "", true);
        this.gameFrame = gameFrame;
        setLocation(gameFrame.getX()+ Values.PAUSE_MENU_SHIFT_X, gameFrame.getY()+ Values.PAUSE_MENU_SHIFT_Y);
        setSize(Values.PAUSE_MENU_WIDTH, Values.PAUSE_MENU_LENGTH);
        setUndecorated(true);
        addMouseListener(this);
    }

    public PauseMenuDialog(GameFrame gameFrame, PlayPanel playPanel) {
        super(gameFrame, "", true);
        this.playPanel = playPanel;
        this.gameFrame = gameFrame;
        setLocation(gameFrame.getX()+ Values.PAUSE_MENU_SHIFT_X, gameFrame.getY()+ Values.PAUSE_MENU_SHIFT_Y);
        setSize(Values.PAUSE_MENU_WIDTH, Values.PAUSE_MENU_LENGTH);
        setUndecorated(true);
        addMouseListener(this);
    }

    public void paint(Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawBackground(g);
        drawPaused(g);
        drawResume(g);
        drawRestart(g);
        drawChest(g);
        drawGoToMap(g);
        drawSound(g);
        drawMusic(g);
    }

    private void drawChest(Graphics g) {
        g.drawImage(chestImage, Values.CHEST_X, Values.CHEST_Y, Values.CHEST_WIDTH, Values.CHEST_LENGTH,null);
    }

    private void drawMusic(Graphics g) {
        g.drawImage(music.image, Values.MUSIC_X, Values.MUSIC_Y, Values.MUSIC_WIDTH, Values.MUSIC_LENGTH,null);
    }

    private void drawSound(Graphics g) {
        g.drawImage(sound.image, Values.SOUND_X, Values.SOUND_Y, Values.SOUND_WIDTH, Values.SOUND_LENGTH,null);
    }

    private void drawGoToMap(Graphics g) {
        g.drawImage(goToMapImage.image, Values.GOTOMAP_X, Values.GOTOMAP_Y, Values.GOTOMAP_WIDTH, Values.GOTOMAP_LENGTH,null);
    }

    private void drawRestart(Graphics g) {
        g.drawImage(restartImage.image, Values.RESTART_X, Values.RESTART_Y, Values.RESTART_WIDTH, Values.RESTART_LENGTH,null);
    }

    private void drawResume(Graphics g) {
        g.drawImage(resumeImage.image, Values.RESUME_X, Values.RESUME_Y, Values.RESUME_WIDTH, Values.RESUME_LENGTH,null);
    }

    private void drawPaused(Graphics g) {
        g.drawImage(pausedImage, Values.PAUSED_X, Values.PAUSED_Y, Values.PAUSED_WIDTH, Values.PAUSED_LENGTH,null);
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(239,209,137));
        g.fillRect(0,0, Values.PAUSE_MENU_WIDTH, Values.PAUSE_MENU_LENGTH);
        g.drawImage(backgroundImage,0,0, Values.PAUSE_MENU_WIDTH, Values.PAUSE_MENU_LENGTH,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double resume = new Rectangle2D.Double(Values.RESUME_X, Values.RESUME_Y, Values.RESUME_WIDTH, Values.RESUME_LENGTH);
        Rectangle2D.Double restart = new Rectangle2D.Double(Values.RESTART_X, Values.RESTART_Y, Values.RESTART_WIDTH, Values.RESTART_LENGTH);
        Rectangle2D.Double goToMap = new Rectangle2D.Double(Values.GOTOMAP_X, Values.GOTOMAP_Y, Values.GOTOMAP_WIDTH, Values.GOTOMAP_LENGTH);
        Rectangle2D.Double sound = new Rectangle2D.Double(Values.SOUND_X, Values.SOUND_Y, Values.SOUND_WIDTH, Values.SOUND_LENGTH);
        Rectangle2D.Double music = new Rectangle2D.Double(Values.MUSIC_X, Values.MUSIC_Y, Values.MUSIC_WIDTH, Values.MUSIC_LENGTH);
        if (resume.contains(point)){
            resumeImage.animate(this,"pauseMenu",resume);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PauseMenuDialog.this.setVisible(false);
                    playPanel.resume();
                }
            });
//            pause window should be closed here
        }
        else if (restart.contains(point)){
            restartImage.animate(this,"pauseMenu",restart);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PauseMenuDialog.this.setVisible(false);
                    playPanel.restart();
                }
            });
        }
        else if (goToMap.contains(point)){
            goToMapImage.animate(this,"pauseMenu",goToMap);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PauseMenuDialog.this.setVisible(false);
                    revalidate();
                    gameFrame.showMap();
                }
            });
        }
        else if (sound.contains(point)){
            this.sound.animate(this,"pauseMenu",sound);
            if (soundOn){
                soundOn = false;
                this.sound = soundOffImage;
                repaint((int)sound.x,(int)sound.y,(int)sound.width,(int)sound.height);
            }
            else {
                soundOn = true;
                this.sound = soundOnImage;
                repaint((int)sound.x,(int)sound.y,(int)sound.width,(int)sound.height);
            }
        }
        else if (music.contains(point)){
            this.music.animate(this,"pauseMenu",music);
            if (musicOn){
                musicOn = false;
                this.music = musicOffImage;
                repaint((int)music.x,(int)music.y,(int)music.width,(int)music.height);
            }
            else {
                musicOn = true;
                this.music = musicOnImage;
                repaint((int)music.x,(int)music.y,(int)music.width,(int)music.height);
            }
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

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }





//    public static void main(String[] args){
//        JFrame f = new JFrame();
//        f.setSize(700,820);
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        PauseMenuDialog p = new PauseMenuDialog(f);
//
//        f.setVisible(true);
//        p.setVisible(true);
//
//    }
}


