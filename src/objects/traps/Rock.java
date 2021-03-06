package objects.traps;

import maps.Cell;
import objects.Stone;
import source.Boy;
import source.PlayPanel;
import source.Util;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Iryna Matviienko
 */
public class Rock extends Stone implements Trap{

    public Clip rockMove = Util.getSound("sounds/rock_movement.wav",-15f);

    /**
     * @param snake
     */
    public Rock(Snake snake) {
        super();
        this.snake = snake;
        image = new ImageIcon("mapImages/rock.png").getImage();
    }

    /**
     * Default constructor
     */
    public Rock() {
        super();
        this.snake = null;
        image = new ImageIcon("mapImages/rock.png").getImage();
    }

    /**
     * Rock moves left when shoving by the boy
     */
    @Override
    public void beShovenLeft(){
        if (enabled) {
            if(i==0){
                startRockMoveSound();
            }
            if (i == 3) {
                if(playPanel.itIsSnake(xInArray-1, yInArray)){
                    if(snake!=null && snake.isAlive){
                        playPanel.currentLevel.getMatrix()[xInArray][yInArray].setTrapObject(snake);
                    }
                    System.out.println("found snake");
                    snake = (Snake)playPanel.currentLevel.getMatrix()[xInArray-1][yInArray].getTrapObject();
                }else{
                    if(snake!=null && snake.isAlive){
                        playPanel.currentLevel.getMatrix()[xInArray][yInArray].setTrapObject(snake);
                    }
                    snake=null;
                }
                setStoneToNewPositionInArray(xInArray - 1, yInArray);
                playPanel.disappearFromCell(xInArray, yInArray);
                xInArray -= 1;
            }
            x -= CELL_SIDE / 7;
            i++;
        }
    }

    /**
     * Rock moves right when shoving by the boy
     */
    @Override
    public void beShovenRight(){
        if (enabled) {
            if(i==0){
                startRockMoveSound();
            }
            if (i == 3) {
                if(playPanel.itIsSnake(xInArray+1, yInArray)){
                    if(snake!=null && this.snake.isAlive){
                        playPanel.currentLevel.getMatrix()[xInArray][yInArray].setTrapObject(snake);
                    }
                    System.out.println("found snake");
                    snake = (Snake)playPanel.currentLevel.getMatrix()[xInArray+1][yInArray].getTrapObject();
                }else{
                    System.out.println("????????");
                    if(snake!=null && this.snake.isAlive){
                        System.out.println("!!!!!!!!!!");
                        playPanel.currentLevel.getMatrix()[xInArray][yInArray].setTrapObject(snake);
                    }
                    snake=null;
                }
                setStoneToNewPositionInArray(xInArray + 1, yInArray);
                playPanel.disappearFromCell(xInArray, yInArray);
                xInArray++;
            }
            x += CELL_SIDE / 7;
            i++;
        }
    }

    /**
     * Paint rock
     * @param g2
     * @param newMapX
     * @param newMapY
     */
    @Override
    public void paintObject(Graphics2D g2, int newMapX, int newMapY) {
        updateXAndY(newMapX, newMapY);
        paintObject(g2);
    }

    /**
     * Paint rock
     * @param g2
     */
    @Override
    public void paintObject(Graphics2D g2) {
        g2.drawImage(image, x, y, WIDTH, HEIGHT,null);
    }

    /**
     * Set rock to new position in array
     * @param xInArray
     * @param yInArray
     */
    @Override
    protected void setStoneToNewPositionInArray(int xInArray, int yInArray){
        if(playPanel.itIsRock(this.xInArray,this.yInArray)){
            playPanel.getCurrentLevel().getMatrix()[this.xInArray][this.yInArray].setTrapObject(null);
        }
        playPanel.getCurrentLevel().getMatrix()[xInArray][yInArray].setTrapObject(this);
    }

    /**
     * Rock hasn`t got a label
     * @return
     */
    @Override
    public JLabel getLabel() {
        return null;
    }

    @Override
    public void checkTimerInit(PlayPanel panel, Cell[][] levelMatrix) {

    }

    @Override
    public Timer getCheckTimer() {
        return null;
    }

    private void startRockMoveSound(){
        if(playPanel.getGameFrame().soundOn){
            rockMove.start();
            Util.wait((int)rockMove.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rockMove.stop();
                    rockMove.setFramePosition(0);
                }
            });
        }
    }

    @Override
    public void setCheckTimer(Timer check) {

    }
}