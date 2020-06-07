package objects.blocks.doors;

import objects.blocks.Block;
import objects.blocks.Floor;
import source.PlayPanel;
import source.Util;
import source.Values;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Illia Sitkov
 */
public class DoorWithKeyhole {

    public GoldDoor gold;
    public SilverDoor silver;

    private boolean opened = false;
    private Floor floor;

    public Clip doorUnlock = Util.getSound("sounds/door_unlock.wav",-10f);

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
        public void openTheDoor(PlayPanel playPanel){
            startDoorUnlockSound(playPanel);
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
        public void openTheDoor(PlayPanel playPanel){
            startDoorUnlockSound(playPanel);
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

    private void startDoorUnlockSound(PlayPanel playPanel){
        if(playPanel.getGameFrame().soundOn){
            doorUnlock.start();
            Util.wait((int) doorUnlock.getMicrosecondLength() / 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doorUnlock.stop();
                    doorUnlock.setFramePosition(0);
                }
            });
        }
    }


}
