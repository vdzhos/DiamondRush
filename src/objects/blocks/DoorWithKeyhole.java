package objects.blocks;

import source.Values;

import javax.swing.*;
import java.awt.*;

public class DoorWithKeyhole implements Block{

    private Image closedDoorUpper;
    private Image closedDoorLower;

    private Image openedDoorLower = new ImageIcon("doors/openedDoorLower.png").getImage();
    private Image openedDoorUpper = new ImageIcon("doors/openedDoorUpper.png").getImage();


    private boolean opened = false;
    private boolean golden;
    private Floor floor;


    public DoorWithKeyhole(boolean golden){
        this.golden = golden;
        initImages();
        floor = new Floor();
    }

    private void initImages() {
        if (golden){
            closedDoorUpper = new ImageIcon("doors/closedDoorUpper.png").getImage();
            closedDoorLower = new ImageIcon("doors/closedDoorLower.png").getImage();
        }
        else {
//            closedDoorUpper = new ImageIcon("doors/closedDoorUpperSilver.png").getImage();
            closedDoorLower = new ImageIcon("doors/closedDoorLowerSilver.png").getImage();
        }
    }


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
//            g2.drawImage(closedDoorUpper,x,y-70,Values.CELL_SIZE,Values.CELL_SIZE,null);
        }
        else {
            floor.paintObject(g2,x,y);
            g2.drawImage(openedDoorLower,x,y, Values.CELL_SIZE,Values.CELL_SIZE,null);
//            g2.drawImage(openedDoorUpper,x,y-70,Values.CELL_SIZE,Values.CELL_SIZE,null);
        }
    }




}
