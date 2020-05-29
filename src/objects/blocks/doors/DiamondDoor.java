package objects.blocks.doors;

import objects.blocks.Block;
import objects.blocks.Floor;
import objects.harmless.Diamond;
import source.Util;
import source.Values;

import javax.swing.*;
import java.awt.*;

public class DiamondDoor implements Block {

    private Image openedImage = new ImageIcon("doors/openedDoorLower.png").getImage();
    private Image closedImage = new ImageIcon("doors/closedDoorDiamond.png").getImage();

    private boolean opened;
    private int numberOfDiamonds;
    private int numberOfDiamondsLeft;
    private Font font;
    private Floor floor;
    private boolean exit;

    public DiamondDoor(int numberOfDiamondsToPass, boolean exit){
        this.exit = exit;
        numberOfDiamonds = numberOfDiamondsToPass;
        numberOfDiamondsLeft = numberOfDiamondsToPass;
        font = Util.getFont("fonts/Funhouse-Ke17.ttf",27f);
        floor = new Floor();
    }

    public void setNumberOfDiamonds(int currentNumberOfPurpleDiamonds){
//        if (!opened) {
           numberOfDiamondsLeft = numberOfDiamonds - currentNumberOfPurpleDiamonds;
           if (numberOfDiamondsLeft <= 0)
               opened = true;
           else opened = false;
//        }
    }

    @Override
    public boolean pass() {
        return opened;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        if (!opened) {
            g2.drawImage(closedImage, x, y, Values.CELL_SIZE, Values.CELL_SIZE,null);
            g2.setFont(font);
            g2.setPaint(Color.RED);
            int shift = numberOfDiamondsLeft<10?28:16;
            g2.drawString(""+numberOfDiamondsLeft,x+shift,y+62);
        }
        else {
            floor.paintObject(g2,x,y);
            g2.drawImage(openedImage,x,y,Values.CELL_SIZE, Values.CELL_SIZE,null);
        }
    }

    public boolean isExit() {
        return exit;
    }
}
