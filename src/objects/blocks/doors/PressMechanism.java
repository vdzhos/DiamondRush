package objects.blocks.doors;

import maps.Cell;
import objects.blocks.Block;
import objects.blocks.Floor;
import objects.traps.Rock;
import source.Values;

import javax.swing.*;
import java.awt.*;
/**
 * @author Illia Sitkov
 */
public class PressMechanism{

    private Image pressPanelImage = new ImageIcon("doors/pressPanel.png").getImage();
    private Image pressPanelDisImage = new ImageIcon("doors/pressPanelDis.png").getImage();

    private Image openedDoor = new ImageIcon("doors/openedDoorLower.png").getImage();
    private Image closedDoor = new ImageIcon("doors/doubleDoor.png").getImage();

    public PressPanel pressPanel;
    public Door door;
    private Floor floor;

    private int positionOfPressPanelInArrayX;
    private int positionOfPressPanelInArrayY;


    private boolean opened;

    /**
     * constructor with parameters
     * position of the press panel on the map (in the array)
     * @param positionOfPressPanelInArrayX
     * @param positionOfPressPanelInArrayY
     */
    public PressMechanism(int positionOfPressPanelInArrayX, int positionOfPressPanelInArrayY){
        this.positionOfPressPanelInArrayX = positionOfPressPanelInArrayX;
        this.positionOfPressPanelInArrayY = positionOfPressPanelInArrayY;
        door = new Door();
        pressPanel = new PressPanel();
        floor = new Floor();
    }

    /**
     * reacts to the obstacle`s position
     * @param matrix
     * @param positionOfBoyInArrayX
     * @param positionOfBoyInArrayY
     */
    public void interact(Cell[][] matrix, int positionOfBoyInArrayX, int positionOfBoyInArrayY){
        if (positionOfBoyInArrayX == positionOfPressPanelInArrayX && positionOfBoyInArrayY == positionOfPressPanelInArrayY){
            opened = true;
        }
        else if (matrix[positionOfPressPanelInArrayX][positionOfPressPanelInArrayY].getTrapObject() != null){
            if (matrix[positionOfPressPanelInArrayX][positionOfPressPanelInArrayY].getTrapObject() instanceof Rock)
                opened = true;
        }
        else opened = false;
    }






    public class PressPanel implements Block, Resettable {
        /**
         *  reacts to the obstacle`s position
         * @param matrix
         * @param positionOfBoyInArrayX
         * @param positionOfBoyInArrayY
         */
        public void interact(Cell[][] matrix, int positionOfBoyInArrayX, int positionOfBoyInArrayY){
            PressMechanism.this.interact(matrix,positionOfBoyInArrayX,positionOfBoyInArrayY);
        }

        @Override
        public boolean pass() {
            return true;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (opened){
                floor.paintObject(g2,x,y);
                g2.drawImage(pressPanelDisImage, x,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
            else {
                floor.paintObject(g2,x,y);
                g2.drawImage(pressPanelImage, x,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
        }

        @Override
        public void reset() {
            opened = false;
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }
    }


    public class Door implements Block{

        @Override
        public boolean pass() {
            return opened;
        }

        @Override
        public void paintObject(Graphics2D g2, int x, int y) {
            if (opened){
                floor.paintObject(g2,x,y);
                g2.drawImage(openedDoor,x,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
            else {
                g2.drawImage(closedDoor,x,y,Values.CELL_SIZE,Values.CELL_SIZE,null);
            }
        }
    }






}
