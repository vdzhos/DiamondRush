package maps;

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

    public Level(Cell[][] matrix, int positionOnScreenX, int positionOnScreenY, int positionOnMapX, int positionOnMapY){
        this.matrix = matrix;
        this.positionOnScreenX = positionOnScreenX;
        this.positionOnScreenY = positionOnScreenY;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
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
}
