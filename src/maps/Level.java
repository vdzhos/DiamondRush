package maps;

import objects.blocks.Checkpoint;

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

    public Level(Cell[][] matrix, int positionOnScreenX, int positionOnScreenY, int positionOnMapX, int positionOnMapY){
        this.matrix = matrix;
        this.positionOnScreenX = positionOnScreenX;
        this.positionOnScreenY = positionOnScreenY;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
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
}
