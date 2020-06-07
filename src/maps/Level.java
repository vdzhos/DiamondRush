package maps;

import objects.blocks.Checkpoint;
import objects.blocks.doors.DoorWithKeyhole;
import objects.harmless.Chest;
import objects.harmless.Diamond;
import objects.thingsInChest.GoldKey;
import objects.thingsInChest.PurpleDiamond;
import objects.thingsInChest.RedDiamond;
import objects.thingsInChest.SilverKey;
import source.AdditionalImage;

public class Level {

    private Cell[][] matrix;
    private AdditionalImage[] additionalImages;

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

    private int levelNumber;

    /**
     * constructor with parameters
     * @param matrix
     * @param positionOnScreenX
     * @param positionOnScreenY
     * @param positionOnMapX
     * @param positionOnMapY
     * @param maxEnergyLevel
     * @param levelNumber
     */
    public Level(Cell[][] matrix, int positionOnScreenX, int positionOnScreenY, int positionOnMapX, int positionOnMapY, int maxEnergyLevel, int levelNumber){
        this.matrix = matrix;
        this.positionOnScreenX = positionOnScreenX;
        this.positionOnScreenY = positionOnScreenY;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
        this.maxEnergyLevel = maxEnergyLevel;
        this.levelNumber = levelNumber;
        setAccessories();
    }

    /**
     * calculates numbers of different items
     */
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


    /**
     * returns a checkpoint on the specific position
     * @param positionOfBoyInArrayX
     * @param positionOfBoyInArrayY
     * @return
     */
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

    public void setAdditionalImages(AdditionalImage[] additionalImages) {
        this.additionalImages = additionalImages;
    }

    public AdditionalImage[] getAdditionalImages() {
        return additionalImages;
    }

    public int getMaxNumberOfPurpleDiamonds() {
        return maxNumberOfPurpleDiamonds;
    }

    public int getMaxNumberOfGoldKeys() {
        return maxNumberOfGoldKeys;
    }


    public int getMaxNumberOfSilverKeys() {
        return maxNumberOfSilverKeys;
    }


    public int getMaxEnergyLevel() {
        return maxEnergyLevel;
    }


    public int getLevelNumber() {
        return levelNumber;
    }
}
