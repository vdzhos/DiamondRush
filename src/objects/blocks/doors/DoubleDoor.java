package objects.blocks.doors;

import objects.blocks.Block;
import objects.blocks.Floor;
import objects.traps.Snake;
import source.Boy;
import source.PlayPanel;
import source.Values;

import javax.swing.*;
import java.awt.*;

public class DoubleDoor{


    private Image doorImage = new ImageIcon("doors/doubleDoor.png").getImage();
    private Image openedDoor = new ImageIcon("doors/openedDoorLower.png").getImage();

    public LeftDoor leftDoor;
    public RightDoor rightDoor;

    private Snake[] snakes;
    private boolean opened = true;
    private Floor floor;
    private boolean toLeft;
    private int positionInArrayX;
    private int positionInArrayY;
    private int n = 0;

    /**
     *
     * @param snakes array of snakes - snakes that must be killed for the door to open
     */
    public DoubleDoor(Snake[] snakes, boolean boyEntersTheDoorToLeft, int positionOfEntranceDoorInArrayX, int positionOfEntranceDoorInArrayY){
        this.snakes = snakes;
        leftDoor = new LeftDoor();
        rightDoor = new RightDoor();
        floor = new Floor();
        toLeft = boyEntersTheDoorToLeft;
        positionInArrayX = positionOfEntranceDoorInArrayX;
        positionInArrayY = positionOfEntranceDoorInArrayY;
    }

    private boolean snakesAreAlive(){
        for (Snake snake:snakes){
            if (snake.isAlive){
                return true;
            }
        }
        return false;
    }

    public void interact(PlayPanel playPanel, Boy boy){
        int positionOfBoyInArrayX = boy.xInArray;
        int positionOfBoyInArrayY = boy.yInArray;
        if (toLeft){
            if (positionOfBoyInArrayX == positionInArrayX - 1 && positionOfBoyInArrayY == positionInArrayY && n == 0&&snakesAreAlive()){
                n = 1;
                opened = false;
                playPanel.drawMessage = true;
                playPanel.twoLineMessage = true;
                playPanel.message = "      I have to";
                playPanel.messageLower = "    Kill them all!";
                boy.isMoving = true;
            }
            else if (!opened){
                for (Snake snake: snakes){
                    if (snake.isAlive)
                        return;
                }
                opened = true;
            }
//            else if (opened){
//                for (Snake snake: snakes){
//                    if (snake.isAlive) {
//                        opened = false;
//                        break;
//                    }
//                }
//            }
        }
        else {
            if (positionOfBoyInArrayX == positionInArrayX + 1 && positionOfBoyInArrayY == positionInArrayY && n == 0&&snakesAreAlive()){
                n = 1;
                opened = false;
                playPanel.drawMessage = true;
                playPanel.twoLineMessage = true;
                playPanel.message = "      I have to";
                playPanel.messageLower = "    Kill them all!";
                boy.isMoving = true;
            }
            else if (!opened){
                for (Snake snake: snakes){
                    if (snake.isAlive)
                        return;
                }
                opened = true;
            }
//            else if (opened){
//                for (Snake snake: snakes){
//                    if (snake.isAlive) {
//                        opened = false;
//                        break;
//                    }
//                }
//            }
        }
    }

    public class LeftDoor implements Block, Resettable {

        public void reset(){
            DoubleDoor.this.opened = DoubleDoor.this.opened?true:false;
            n =0;
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public boolean pass() {
            return DoubleDoor.this.opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (opened) {
//                floor.paintObject(g2,x - Values.CELL_SIZE, y);
                floor.paintObject(g2,x,y);
                g2.drawImage(openedDoor, x, y, Values.CELL_SIZE, Values.CELL_SIZE, null);
            }
            else {
                g2.drawImage(doorImage, x,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }

        }
    }

    public class RightDoor implements Block, Resettable {

        public void reset(){
            DoubleDoor.this.opened = DoubleDoor.this.opened?true:false;
            n = 0;
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        public void interact(PlayPanel playPanel, Boy boy){
            DoubleDoor.this.interact(playPanel,boy);
        }

        @Override
        public boolean pass() {
            return DoubleDoor.this.opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (opened) {
                floor.paintObject(g2,x , y);
                g2.drawImage(openedDoor, x, y, Values.CELL_SIZE, Values.CELL_SIZE, null);
            }
            else {
                g2.drawImage(doorImage, x,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
        }
    }





}
