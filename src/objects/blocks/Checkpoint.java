package objects.blocks;

import maps.Cell;
import objects.Stone;
import objects.blocks.doors.Resettable;
import objects.harmless.Chest;
import objects.harmless.Diamond;
import objects.thingsInChest.GoldKey;
import objects.thingsInChest.PurpleDiamond;
import objects.thingsInChest.RedDiamond;
import objects.thingsInChest.SilverKey;
import objects.traps.Rock;
import objects.traps.Scorpion;
import objects.traps.Snake;
import objects.traps.Trap;
import source.Util;
import source.Values;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * @author Illia Sitkov
 */
public class Checkpoint implements Block {

    private Image image = new ImageIcon("mapImages/checkpoint1.png").getImage();
    private Image imageUsed = new ImageIcon("mapImages/checkpoint.png").getImage();

    public Cell[][] initialMatrix;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private int[] additionalSegment;
    private boolean additionalSegmentBoolean = false;

    public int positionInArrayX;
    public int positionInArrayY;
    public int minPositionOnScreenX;
    public int minPositionOnScreenY;
    public boolean isUsed;

    private int numberOfRedDiamondsOnTheArea;
    public int numberOfRedDiamondsOnTheAreaCollected;
    private int numberOfRedDiamondsOnTheAreaLeft;

    private int numberOfPurpleDiamondsOnTheArea;
    public int numberOfPurpleDiamondsOnTheAreaCollected;
    private int numberOfPurpleDiamondsOnTheAreaLeft;

    private int numberOfGoldKeysOnTheArea;
    public int numberOfGoldKeysOnTheAreaCollected;
    private int numberOfGoldKeysOnTheAreaLeft;


    private int numberOfSilverKeysOnTheArea;
    public int numberOfSilverKeysOnTheAreaCollected;
    private int numberOfSilverKeysOnTheAreaLeft;

    private ArrayList<Chest> chestsOnTheArea = new ArrayList<>();
    private ArrayList<Trap> trapsOnTheArea = new ArrayList<>();

    private Floor floor = new Floor();

    /**
     * constructor with parameters
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param positionInArrayX
     * @param positionInArrayY
     * @param minPositionOnScreenX
     * @param minPositionOnScreenY
     */
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

    /**
     * constructor with parameters
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param additionalSegment
     * @param positionInArrayX
     * @param positionInArrayY
     * @param minPositionOnScreenX
     * @param minPositionOnScreenY
     */
    public Checkpoint(int startX, int startY, int endX, int endY,int[] additionalSegment, int positionInArrayX, int positionInArrayY, int minPositionOnScreenX, int minPositionOnScreenY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.additionalSegment = additionalSegment;
        additionalSegmentBoolean = true;
        this.positionInArrayX = positionInArrayX;
        this.positionInArrayY = positionInArrayY;
        this.minPositionOnScreenX = minPositionOnScreenX;
        this.minPositionOnScreenY = minPositionOnScreenY;
    }

    /**
     * returns the restored matrix
     * @param currentMatrix
     * @return
     */
    public Cell[][] getRestoredMatrix(Cell[][] currentMatrix) {
        numberOfPurpleDiamondsOnTheAreaLeft = 0;
        numberOfRedDiamondsOnTheAreaLeft = 0;
        numberOfSilverKeysOnTheAreaLeft = 0;
        numberOfGoldKeysOnTheAreaLeft = 0;

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (currentMatrix[i][j].getHarmlessObject() instanceof Diamond)
                    numberOfPurpleDiamondsOnTheAreaLeft++;
                else if (currentMatrix[i][j].getHarmlessObject() instanceof Chest && ((Chest) currentMatrix[i][j].getHarmlessObject()).isClosed) {
                    if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[0] != null) {
                            numberOfPurpleDiamondsOnTheAreaLeft += ((PurpleDiamond) (((Chest) currentMatrix[i][j].getHarmlessObject()).things[0])).quantity;
                    }
                    if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[1] != null) {
                            numberOfRedDiamondsOnTheAreaLeft += ((RedDiamond) (((Chest) currentMatrix[i][j].getHarmlessObject()).things[1])).quantity;
                    }
                    if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[2] != null) {
                            numberOfSilverKeysOnTheAreaLeft++;
                    }
                    if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[3] != null) {
                            numberOfGoldKeysOnTheAreaLeft++;
                    }
                }
                currentMatrix[i][j] = initialMatrix[i][j];
            }
        }
        if (additionalSegmentBoolean) {
            for (int i = additionalSegment[0]; i <= additionalSegment[2]; i++) {
                for (int j = additionalSegment[1]; j <= additionalSegment[3]; j++) {
                    if (currentMatrix[i][j].getHarmlessObject() instanceof Diamond)
                        numberOfPurpleDiamondsOnTheAreaLeft++;
                    else if (currentMatrix[i][j].getHarmlessObject() instanceof Chest && ((Chest) currentMatrix[i][j].getHarmlessObject()).isClosed) {
                        if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[0] != null) {
                                numberOfPurpleDiamondsOnTheAreaLeft += ((PurpleDiamond) (((Chest) currentMatrix[i][j].getHarmlessObject()).things[0])).quantity;
                        }
                        if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[1] != null) {
                                numberOfRedDiamondsOnTheAreaLeft += ((RedDiamond) (((Chest) currentMatrix[i][j].getHarmlessObject()).things[1])).quantity;
                        }
                        if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[2] != null) {
                                numberOfSilverKeysOnTheAreaLeft++;
                        }
                        if (((Chest) currentMatrix[i][j].getHarmlessObject()).things[3] != null) {
                                numberOfGoldKeysOnTheAreaLeft++;
                        }
                    }
                    currentMatrix[i][j] = initialMatrix[i][j];
                }
            }
        }
        Cell[][] temp = refreshInitialMatrix(this.initialMatrix);

        this.initialMatrix = temp;
        numberOfPurpleDiamondsOnTheAreaCollected = numberOfPurpleDiamondsOnTheArea - numberOfPurpleDiamondsOnTheAreaLeft;
        numberOfRedDiamondsOnTheAreaCollected = numberOfRedDiamondsOnTheArea - numberOfRedDiamondsOnTheAreaLeft;
        numberOfSilverKeysOnTheAreaCollected = numberOfSilverKeysOnTheArea - numberOfSilverKeysOnTheAreaLeft;
        numberOfGoldKeysOnTheAreaCollected = numberOfGoldKeysOnTheArea - numberOfGoldKeysOnTheAreaLeft;

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

    /**
     * method sets initial matrix and calculates all the initial numbers of items
     * @param initialMatrix
     */
    public void setInitialMatrix(Cell[][] initialMatrix) {
        this.initialMatrix = new Cell[initialMatrix.length][initialMatrix[0].length];
        for (byte i = 0; i < initialMatrix.length; i ++){
            this.initialMatrix[i] = new Cell[initialMatrix[0].length];
            for (byte j = 0; j < initialMatrix[0].length; j++){
                this.initialMatrix[i][j] = initialMatrix[i][j].clone();
            }
        }
        setOriginalQuantity();
    }

    /**
     * calculates numbers of all the items
     */
    private void setOriginalQuantity() {
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (initialMatrix[i][j].getTrapObject() instanceof Snake || initialMatrix[i][j].getTrapObject() instanceof Scorpion){
                    trapsOnTheArea.add(initialMatrix[i][j].getTrapObject());
                }
                if (initialMatrix[i][j].getHarmlessObject() instanceof Diamond) {
                    numberOfPurpleDiamondsOnTheArea++;
                }
                else if (initialMatrix[i][j].getHarmlessObject() instanceof Chest) {
                    chestsOnTheArea.add((Chest) initialMatrix[i][j].getHarmlessObject());
                    if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[0] != null) {
                            numberOfPurpleDiamondsOnTheArea += ((PurpleDiamond) (((Chest) initialMatrix[i][j].getHarmlessObject()).things[0])).quantity;
                    }
                    if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[1] != null) {
                            numberOfRedDiamondsOnTheArea += ((RedDiamond) (((Chest) initialMatrix[i][j].getHarmlessObject()).things[1])).quantity;
                    }
                    if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[2] != null) {
                            numberOfSilverKeysOnTheArea++;
                    }
                    if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[3] != null) {
                            numberOfGoldKeysOnTheArea++;
                    }
                }
            }
        }
        if (additionalSegmentBoolean) {
            for (int i = additionalSegment[0]; i <= additionalSegment[2]; i++) {
                for (int j = additionalSegment[1]; j <= additionalSegment[3]; j++) {
                    if (initialMatrix[i][j].getTrapObject() instanceof Snake || initialMatrix[i][j].getTrapObject() instanceof Scorpion) {
                        trapsOnTheArea.add(initialMatrix[i][j].getTrapObject());
                    }
                    if (initialMatrix[i][j].getHarmlessObject() instanceof Diamond)
                        numberOfPurpleDiamondsOnTheArea++;
                    else if (initialMatrix[i][j].getHarmlessObject() instanceof Chest) {
                        chestsOnTheArea.add((Chest) initialMatrix[i][j].getHarmlessObject());
                        if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[0] != null) {
                            numberOfPurpleDiamondsOnTheArea += ((PurpleDiamond) (((Chest) initialMatrix[i][j].getHarmlessObject()).things[0])).quantity;
                        }
                        if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[1] != null) {
                            numberOfRedDiamondsOnTheArea += ((RedDiamond) (((Chest) initialMatrix[i][j].getHarmlessObject()).things[1])).quantity;
                        }
                        if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[2] != null) {
                            numberOfSilverKeysOnTheArea++;
                        }
                        if (((Chest) initialMatrix[i][j].getHarmlessObject()).things[3] != null) {
                            numberOfGoldKeysOnTheArea++;
                        }
                    }
                }
            }
        }
    }

    /**
     * makes copy of the initial matrix
     * @param initialMatrix
     * @return
     */
    private Cell[][] refreshInitialMatrix(Cell[][] initialMatrix) {
        Cell[][] temp = new Cell[initialMatrix.length][initialMatrix[0].length];
        for (byte i = 0; i < initialMatrix.length; i ++){
            temp[i] = new Cell[initialMatrix[0].length];
            for (byte j = 0; j < initialMatrix[0].length; j++){
                temp[i][j] = initialMatrix[i][j].clone();
                if (temp[i][j].getBlock() instanceof Resettable){
                    ((Resettable) temp[i][j].getBlock()).reset();
                }
                if (temp[i][j].getTrapObject() instanceof Resettable && trapsOnTheArea.contains(temp[i][j].getTrapObject())){
                    ((Resettable) temp[i][j].getTrapObject()).reset();
                }
                if (temp[i][j].getTrapObject() instanceof Rock){
                    ((Rock) temp[i][j].getTrapObject()).reset();
                }
                if (temp[i][j].getHarmlessObject() instanceof Chest && chestsOnTheArea.contains((Chest) temp[i][j].getHarmlessObject()))
                    ((Resettable) temp[i][j].getHarmlessObject()).reset();
                if (temp[i][j].getHarmlessObject() instanceof Diamond)
                    ((Resettable) temp[i][j].getHarmlessObject()).reset();
                }
            }
        return temp;
    }

    public Cell[][] getInitialMatrix() {
        return initialMatrix;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
