package objects.blocks;

import maps.Cell;
import objects.blocks.doors.Resetable;
import source.Values;

import javax.swing.*;
import java.awt.*;

public class Checkpoint implements Block {

    private Image image = new ImageIcon("mapImages/checkpoint1.png").getImage();
    private Image imageUsed = new ImageIcon("mapImages/checkpoint.png").getImage();

    public Cell[][] initialMatrix;

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    public int positionInArrayX;
    public int positionInArrayY;
    public int minPositionOnScreenX;
    public int minPositionOnScreenY;
    public boolean isUsed;

//    public int numberOfDiamondsOnTheArea;
//    public int amountOfEnergy

    private Floor floor = new Floor();


    public Checkpoint(int startX, int startY, int endX, int endY, int positionInArrayX, int positionInArrayY, int minPositionOnScreenX, int minPositionOnScreenY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.positionInArrayX = positionInArrayX;
        this.positionInArrayY = positionInArrayY;
        this.minPositionOnScreenX = minPositionOnScreenX;
        this.minPositionOnScreenY = minPositionOnScreenY;
    }

    public Cell[][] getRestoredMatrix(Cell[][] currentMatrix) {
        Cell[][] temp = refreshInitialMatrix(this.initialMatrix);
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                currentMatrix[i][j] = initialMatrix[i][j];
            }
        }
        this.initialMatrix = temp;
        return currentMatrix;
    }


    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        floor.paintObject(g2, x, y);
        g2.drawImage(isUsed?imageUsed:image,x,y, Values.CELL_SIZE,Values.CELL_SIZE,null);
    }

    public void setInitialMatrix(Cell[][] initialMatrix) {
        this.initialMatrix = new Cell[initialMatrix.length][initialMatrix[0].length];
        for (byte i = 0; i < initialMatrix.length; i ++){
            this.initialMatrix[i] = new Cell[initialMatrix[0].length];
            for (byte j = 0; j < initialMatrix[0].length; j++){
                this.initialMatrix[i][j] = initialMatrix[i][j].clone();
            }
        }
    }

    public Cell[][] refreshInitialMatrix(Cell[][] initialMatrix) {
        Cell[][] temp = new Cell[initialMatrix.length][initialMatrix[0].length];
        for (byte i = 0; i < initialMatrix.length; i ++){
            temp[i] = new Cell[initialMatrix[0].length];
            for (byte j = 0; j < initialMatrix[0].length; j++){
                temp[i][j] = initialMatrix[i][j].clone();
                if (temp[i][j].getBlock() instanceof Resetable){
                    ((Resetable) temp[i][j].getBlock()).reset();
                }
            }
        }
        return temp;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
