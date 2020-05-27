package maps;

import objects.blocks.Checkpoint;
import objects.blocks.doors.DoorWithKeyhole;
import objects.harmless.Chest;
import objects.harmless.Diamond;
import objects.thingsInChest.GoldKey;
import objects.thingsInChest.PurpleDiamond;
import objects.thingsInChest.RedDiamond;
import objects.thingsInChest.SilverKey;

public class Level {

    private Cell[][] matrix;

    /**
     * position of boy on the screen (10x10 cells) (NOT coordinates in pixels)
     */
    private int positionOnScreenX;
    /**
     * position of boy on the screen (10x10 cells) (NOT coordinates in pixels)
     */
    private int positionOnScreenY;

    /**
     * position of boy on the map (NOT coordinates in pixels)
     */
    private int positionOnMapX;
    /**
     * position of boy on the map (NOT coordinates in pixels)
     */
    private int positionOnMapY;

    private Checkpoint[] checkpoints;

    private int maxNumberOfRedDiamonds;

    private int maxNumberOfPurpleDiamonds;

    private int maxNumberOfGoldKeys;

    private int maxNumberOfSilverKeys;

    private int maxEnergyLevel;

    public Level(Cell[][] matrix, int positionOnScreenX, int positionOnScreenY, int positionOnMapX, int positionOnMapY, int maxEnergyLevel){
        this.matrix = matrix;
        this.positionOnScreenX = positionOnScreenX;
        this.positionOnScreenY = positionOnScreenY;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
        this.maxEnergyLevel = maxEnergyLevel;
        setAccessories();
    }

    private void setAccessories() {
        for (byte i = 0;i < matrix.length; i++ ){
            for (byte j = 0; j < matrix[0].length; j ++){
                if (matrix[i][j].getHarmlessObject() instanceof Diamond)
                    maxNumberOfPurpleDiamonds ++;
                else if (matrix[i][j].getBlock() instanceof DoorWithKeyhole.GoldDoor){
                    maxNumberOfGoldKeys ++;
                }
                else if (matrix[i][j].getBlock() instanceof DoorWithKeyhole.SilverDoor) {
                    maxNumberOfSilverKeys++;
                }
                else if (matrix[i][j].getHarmlessObject() instanceof Chest) {
                    if (((Chest) matrix[i][j].getHarmlessObject()).things[0] != null) {
//                        if ((((Chest) matrix[i][j].getHarmlessObject()).things[0]) instanceof PurpleDiamond)
                            maxNumberOfPurpleDiamonds += ((PurpleDiamond) (((Chest) matrix[i][j].getHarmlessObject()).things[0])).quantity;
                    }
                    if (((Chest) matrix[i][j].getHarmlessObject()).things[1] != null) {
//                        if ((((Chest) matrix[i][j].getHarmlessObject()).things[1]) instanceof RedDiamond) {
                            maxNumberOfRedDiamonds += ((RedDiamond) (((Chest) matrix[i][j].getHarmlessObject()).things[1])).quantity;
                        }

                    }
                }
            }
        }



    public Checkpoint getCheckpoint(int positionOfBoyInArrayX, int positionOfBoyInArrayY){
        for (Checkpoint checkpoint: checkpoints){
            if (checkpoint.positionInArrayX==positionOfBoyInArrayX && checkpoint.positionInArrayY==positionOfBoyInArrayY){
                return checkpoint.isUsed?null:checkpoint;
            }
        }
        return null;
    }

    public Checkpoint getFirstCheckpoint(){
        return checkpoints[0];
    }



    public Cell[][] getMatrix() {
        return matrix;
    }


    public int getPositionOnScreenX() {
        return positionOnScreenX;
    }

    public int getPositionOnScreenY() {
        return positionOnScreenY;
    }

    public int getPositionOnMapX() {
        return positionOnMapX;
    }

    public int getPositionOnMapY() {
        return positionOnMapY;
    }

    public void setCheckpoints(Checkpoint[] checkpoints) {
        this.checkpoints = checkpoints;
    }

    public int getMaxNumberOfRedDiamonds() {
        return maxNumberOfRedDiamonds;
    }

    public void setMaxNumberOfRedDiamonds(int maxNumberOfRedDiamonds) {
        this.maxNumberOfRedDiamonds = maxNumberOfRedDiamonds;
    }

    public int getMaxNumberOfPurpleDiamonds() {
        return maxNumberOfPurpleDiamonds;
    }

    public void setMaxNumberOfPurpleDiamonds(int maxNumberOfPurpleDiamonds) {
        this.maxNumberOfPurpleDiamonds = maxNumberOfPurpleDiamonds;
    }

    public int getMaxNumberOfGoldKeys() {
        return maxNumberOfGoldKeys;
    }

    public void setMaxNumberOfGoldKeys(int maxNumberOfGoldKeys) {
        this.maxNumberOfGoldKeys = maxNumberOfGoldKeys;
    }

    public int getMaxNumberOfSilverKeys() {
        return maxNumberOfSilverKeys;
    }

    public void setMaxNumberOfSilverKeys(int maxNumberOfSilverKeys) {
        this.maxNumberOfSilverKeys = maxNumberOfSilverKeys;
    }

    public int getMaxEnergyLevel() {
        return maxEnergyLevel;
    }

    public void setMaxEnergyLevel(int maxEnergyLevel) {
        this.maxEnergyLevel = maxEnergyLevel;
    }
}
