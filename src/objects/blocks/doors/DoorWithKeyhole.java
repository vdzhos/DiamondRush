package objects.blocks.doors;

import objects.blocks.Block;
import objects.blocks.Floor;
import source.Values;

import javax.swing.*;
import java.awt.*;

public class DoorWithKeyhole {

    public GoldDoor gold;
    public SilverDoor silver;

    private boolean opened = false;
    private Floor floor;

    /**
     * constructor with no parameters
     */
    public DoorWithKeyhole(){
        floor = new Floor();
        gold = new GoldDoor();
        silver = new SilverDoor();
    }


    public class GoldDoor implements Block, Resettable {

        private Image closedDoorLower = new ImageIcon("doors/closedDoorLower.png").getImage();
        private Image openedDoorLower = new ImageIcon("doors/openedDoorLower.png").getImage();

        /**
         * opens the door
         */
        public void openTheDoor(){
            opened = true;
        }

        @Override
        public boolean pass() {
            return opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (!opened){
                g2.drawImage(closedDoorLower,x,y, Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
            else {
                floor.paintObject(g2,x,y);
                g2.drawImage(openedDoorLower,x,y, Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
        }

        @Override
        public void reset() {
            opened = opened?true:false;
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }
    }


    public class SilverDoor implements Block, Resettable {

        private Image closedDoorLower = new ImageIcon("doors/closedDoorLowerSilver.png").getImage();
        private Image openedDoorLower = new ImageIcon("doors/openedDoorLower.png").getImage();

        /**
         * opens the door
         */
        public void openTheDoor(){
            opened = true;
        }

        @Override
        public boolean pass() {
            return opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (!opened){
                g2.drawImage(closedDoorLower,x,y, Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
            else {
                floor.paintObject(g2,x,y);
                g2.drawImage(openedDoorLower,x,y, Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
        }

        @Override
        public void reset() {
            opened = opened?true:false;
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }
    }




}
