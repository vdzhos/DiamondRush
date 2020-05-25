package objects.blocks;

import objects.traps.Snake;
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

    public DoubleDoor(Snake[] snakes){
        this.snakes = snakes;
        floor = new Floor();
        leftDoor = new LeftDoor();
        rightDoor = new RightDoor();
    }

    public class LeftDoor implements Block{

        public void interact(){
            if (opened){
                opened = false;
            }
            else {
                for (Snake snake: snakes){
//                    if (snake.isAlive())
                        return;
                }
                DoubleDoor.this.opened = true;
            }
        }

        @Override
        public boolean pass() {
            return DoubleDoor.this.opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (opened) {
                floor.paintObject(g2,x,y);
//                floor.paintObject(g2,x - Values.CELL_SIZE, y);
                g2.drawImage(openedDoor, x - Values.CELL_SIZE, y, Values.CELL_SIZE, Values.CELL_SIZE, null);
            }
            else {
                floor.paintObject(g2,x,y);
                g2.drawImage(doorImage, x-Values.CELL_SIZE,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }

        }
    }

    public class RightDoor implements Block{


        public void interact(){
            if (opened){
                opened = false;
            }
            else {
                for (Snake snake: snakes){
//                    if (snake.isAlive())
                        return;
                }
                DoubleDoor.this.opened = true;
            }
        }

        @Override
        public boolean pass() {
            return DoubleDoor.this.opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (opened) {
                floor.paintObject(g2,x , y);
                floor.paintObject(g2,x + Values.CELL_SIZE, y);
                g2.drawImage(openedDoor, x + Values.CELL_SIZE, y, Values.CELL_SIZE, Values.CELL_SIZE, null);
            }
            else {
                floor.paintObject(g2,x , y);
                floor.paintObject(g2,x + Values.CELL_SIZE, y);
                g2.drawImage(doorImage, x+Values.CELL_SIZE,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
        }
    }





}
