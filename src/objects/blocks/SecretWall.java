package objects.blocks;

import java.awt.*;

public class SecretWall implements Block{

    private Image image;
    private int x;
    private int y;

    public SecretWall(Image image) {
        this.image = image;
    }

    public SecretWall(Wall.Type type) {
        switch (type){
            case CEILING:
                image = Wall.ceiling;
                break;
            case FLOOR:
                image = Wall.floor;
                break;
            case CENTER:
                image = Wall.center;
                break;
            case LEFT_SIDE:
                image = Wall.left;
                break;
            case RIGHT_SIDE:
                image = Wall.right;
                break;
            case LEFT_UPPER_CORNER:
                image = Wall.leftUpperCorner;
                break;
            case LEFT_LOWER_CORNER:
                image = Wall.leftLowerCorner;
                break;
            case LEFT_UPPER_BRICK:
                image = Wall.leftUpperBrick;
                break;
            case LEFT_LOWER_BRICK:
                image = Wall.leftLowerBrick;
                break;
            case RIGHT_UPPER_CORNER:
                image = Wall.rightUpperCorner;
                break;
            case RIGHT_LOWER_CORNER:
                image = Wall.rightLowerCorner;
                break;
            case RIGHT_UPPER_BRICK:
                image = Wall.rightUpperBrick;
                break;
            case RIGHT_LOWER_BRICK:
                image = Wall.rightLowerBrick;
                break;
            case LEFT_P:
                image = Wall.leftP;
                break;
            case RIGHT_P:
                image = Wall.rightP;
                break;
            case UPPER_P:
                image = Wall.upperP;
                break;
            case LOWER_P:
                image = Wall.lowerP;
                break;
            case  LEFT_UPPER_CORNER_BRICK:
                image = Wall.leftUpperCornerBrick;
                break;
            case  RIGHT_UPPER_CORNER_BRICK:
                image = Wall.rightUpperCornerBrick;
                break;
            case  LEFT_LOWER_CORNER_BRICK:
                image = Wall.leftLowerCornerBrick;
                break;
            case  RIGHT_LOWER_CORNER_BRICK:
                image = Wall.rightLowerCornerBrick;
                break;
            case  LEFT_SIDE_UPPER_BRICK:
                image = Wall.leftSideUpperBrick;
                break;
            case  LEFT_SIDE_LOWER_BRICK:
                image = Wall.leftSideLowerBrick;
                break;
            case  LEFT_SIDE_BOTH_BRICKS:
                image = Wall.leftSideBothBricks;
                break;
            case  RIGHT_SIDE_UPPER_BRICK:
                image = Wall.rightSideUpperBrick;
                break;
            case  RIGHT_SIDE_LOWER_BRICK:
                image = Wall.rightSideLowerBrick;
                break;
            case  RIGHT_SIDE_BOTH_BRICKS:
                image = Wall.rightSideBothBricks;
                break;
            case  CEILING_LEFT_BRICK:
                image = Wall.ceilingLeftBrick;
                break;
            case  CEILING_RIGHT_BRICK:
                image = Wall.ceilingRightBrick;
                break;
            case  CEILING_BOTH_BRICKS:
                image = Wall.ceilingBothBricks;
                break;
            case  FLOOR_LEFT_BRICK:
                image = Wall.floorLeftBrick;
                break;
            case  FLOOR_RIGHT_BRICK:
                image = Wall.floorRightBrick;
                break;
            case  FLOOR_BOTH_BRICKS:
                image = Wall.floorBothBricks;
                break;
            case  VERTICAL_TONNEL:
                image = Wall.verticalTonnel;
                break;
            case  HORISONTAL_TONNEL:
                image = Wall.horisontalTonnel;
                break;
            case  BOTH_LEFT_BRICKS:
                image = Wall.bothLeftBricks;
                break;
            case  BOTH_RIGHT_BRICKS:
                image = Wall.bothRightBricks;
                break;
            case  BOTH_LOWER_BRICKS:
                image = Wall.bothLowerBricks;
                break;
            case  BOTH_UPPER_BRICKS:
                image = Wall.bothUpperBricks;
                break;
            case  FOUR_BRICKS:
                image = Wall.fourBricks;
                break;
            case  LEFT_DIAGONAL_BRICKS:
                image = Wall.leftDiagonal;
                break;
            case  RIGHT_DIAGONAL_BRICKS:
                image = Wall.rightDiagonal;
                break;
            case  THREE_BRICKS_LEFT_LOWER:
                image = Wall.threeLeftLower;
                break;
            case  THREE_BRICKS_LEFT_UPPER:
                image = Wall.threeLeftUpper;
                break;
            case  THREE_BRICKS_RIGHT_LOWER:
                image = Wall.threeRightLower;
                break;
            case  THREE_BRICKS_RIGHT_UPPER:
                image = Wall.threeRightUpper;
                break;
            case  BLOCKED:
                image = Wall.blocked;
                break;
            case  SECRET:
                image = Wall.secret;
                break;
        }
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean pass() {
        return true;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
