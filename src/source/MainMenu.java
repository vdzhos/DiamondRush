package source;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * @author Illia Sitkov
 */
public class MainMenu extends JPanel implements MouseListener {
    
    
    private Image backgroundImage = new ImageIcon("mainMenu/mainBack.jpg").getImage();
    private GameFrame gameFrame;
    
    private AnimatableImage playImage = new AnimatableImage("mainMenu/playB.png");
    private AnimatableImage newGameImage = new AnimatableImage("mainMenu/restart.png");
    private AnimatableImage exitImage = new AnimatableImage("mainMenu/exitB.png");
    private AnimatableImage infoImage = new AnimatableImage("mainMenu/infoB.png");




    private AnimatableImage soundOnImage = new AnimatableImage("pauseMenu/soundOn.png");
    private AnimatableImage soundOffImage = new AnimatableImage("pauseMenu/soundOff.png");
    private AnimatableImage sound = soundOnImage;

    private AnimatableImage musicOnImage = new AnimatableImage("pauseMenu/musicOn.png");
    private AnimatableImage musicOffImage = new AnimatableImage("pauseMenu/musicOff.png");
    private AnimatableImage music = musicOnImage;

    private boolean soundOn = true;
    private boolean musicOn = true;





    /**
     * constructor with parameters
     * @param gameFrame
     */
    public MainMenu(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        addMouseListener(this);
    }



    @Override
    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBack(g);
        drawPlay(g);
        drawNewGame(g);
        drawInfo(g);
        drawExit(g);
        drawSound(g);
        drawMusic(g);

    }

    /**
     * draws music button
     * @param g
     */
    private void drawMusic(Graphics g) {
        g.drawImage(music.image, Values.MUSIC_MENU_X, Values.MUSIC_MENU_Y, Values.BUTTON_SIZE, Values.BUTTON_SIZE,null);
    }

    /**
     * draws sound button
     * @param g
     */
    private void drawSound(Graphics g) {
        g.drawImage(sound.image, Values.SOUND_MENU_X, Values.SOUND_MENU_Y, Values.BUTTON_SIZE, Values.BUTTON_SIZE,null);
    }








    /**
     * draws info button
     * @param g
     */
    private void drawInfo(Graphics2D g) {
        g.drawImage(infoImage.image, Values.INFO_X, Values.INFO_Y, Values.INFO_WIDTH, Values.INFO_LENGTH,null);
    }

    /**
     * draws exit button
     * @param g
     */
    private void drawExit(Graphics2D g) {
        g.drawImage(exitImage.image, Values.EXIT_X, Values.EXIT_Y, Values.EXIT_WIDTH, Values.EXIT_LENGTH,null);
    }

    /**
     * draws new game button
     * @param g
     */
    private void drawNewGame(Graphics2D g) {
        g.drawImage(newGameImage.image, Values.NEW_GAME_X, Values.NEW_GAME_Y, Values.NEW_GAME_WIDTH, Values.NEW_GAME_LENGTH,null);
    }

    /**
     * draws play button
     * @param g
     */
    private void drawPlay(Graphics2D g) {
        g.drawImage(playImage.image, Values.PLAY_X, Values.PLAY_Y, Values.PLAY_WIDTH, Values.PLAY_LENGTH,null);
    }

    /**
     * draws background
     * @param g
     */
    private void drawBack(Graphics2D g) {
        g.drawImage(backgroundImage, 0,0, Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH,null);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = new Point2D.Double(e.getX(),e.getY());
        Rectangle2D.Double play = new Rectangle2D.Double(Values.PLAY_X, Values.PLAY_Y, Values.PLAY_WIDTH, Values.PLAY_LENGTH);
        Rectangle2D.Double newGame = new Rectangle2D.Double(Values.NEW_GAME_X, Values.NEW_GAME_Y, Values.NEW_GAME_WIDTH, Values.NEW_GAME_LENGTH);
        Rectangle2D.Double exit = new Rectangle2D.Double(Values.EXIT_X, Values.EXIT_Y, Values.EXIT_WIDTH, Values.EXIT_LENGTH);
        Rectangle2D.Double info = new Rectangle2D.Double(Values.INFO_X, Values.INFO_Y, Values.INFO_WIDTH, Values.INFO_LENGTH);
        Rectangle2D.Double music = new Rectangle2D.Double(Values.MUSIC_MENU_X, Values.MUSIC_MENU_Y, Values.BUTTON_SIZE, Values.BUTTON_SIZE);
        Rectangle2D.Double sound = new Rectangle2D.Double(Values.SOUND_MENU_X, Values.SOUND_MENU_Y, Values.BUTTON_SIZE, Values.BUTTON_SIZE);
        if (play.contains(point)){
            Util.click(gameFrame.soundOn);
            playImage.animate(this,"mainMenu",play);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showMap();
                }
            });
        }
        else if (newGame.contains(point)){
            Util.click(gameFrame.soundOn);
            newGameImage.animate(this,"mainMenu",newGame);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProgressStorage.resetContent();
                    gameFrame.setNewMap();
                    gameFrame.showMap();
                }
            });

        }
        else if (exit.contains(point)){
            Util.click(gameFrame.soundOn);
            exitImage.animate(this,"mainMenu",exit);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.stop();
                }
            });
        }
        else if (info.contains(point)){
            Util.click(gameFrame.soundOn);
            infoImage.animate(this, "mainMenu",info);
            Util.wait(Values.TIME_TO_WAIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameFrame.showInfo();
                }
            });
        }
        else if (sound.contains(point)){
            Util.click(gameFrame.soundOn);
            this.sound.animate(this,"pauseMenu",sound);
            if (soundOn){
                soundOn = false;
                gameFrame.soundOn = false;
                this.sound = soundOffImage;
                repaint((int)sound.x,(int)sound.y,(int)sound.width,(int)sound.height);
            }
            else {
                soundOn = true;
                gameFrame.soundOn = true;
                this.sound = soundOnImage;
                repaint((int)sound.x,(int)sound.y,(int)sound.width,(int)sound.height);
            }
        }
        else if (music.contains(point)){
            Util.click(gameFrame.soundOn);
            this.music.animate(this,"pauseMenu",music);
            if (musicOn){
                musicOn = false;
                gameFrame.musicOn = false;
                gameFrame.menuBgClip.stop();
                gameFrame.menuBgClip.setFramePosition(0);
                this.music = musicOffImage;
                repaint((int)music.x,(int)music.y,(int)music.width,(int)music.height);
            }
            else {
                musicOn = true;
                gameFrame.musicOn = true;
                gameFrame.menuBgClip.loop(Clip.LOOP_CONTINUOUSLY);
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

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
        if(soundOn){
            sound = soundOnImage;
        }else{
            sound = soundOffImage;
        }
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
        if(musicOn){
            music = musicOnImage;
        }else{
            music = musicOffImage;
        }
    }

}
