package objects.blocks;

import javax.swing.*;
import java.awt.*;

public class Wall implements Block {

    private Image image = new ImageIcon("mapImages/wall.png").getImage();

    public static  Image center = new ImageIcon("mapImages/wall.png").getImage();
    public static  Image ceiling = new ImageIcon("mapImages/wallCeiling.png").getImage();
    public static  Image floor = new ImageIcon("mapImages/wallFloor.png").getImage();
    public static  Image left = new ImageIcon("mapImages/wallLeft.png").getImage();
    public static  Image right = new ImageIcon("mapImages/wallRight.png").getImage();
    public static  Image rightUpperCorner = new ImageIcon("mapImages/wallRightUpperCorner.png").getImage();
    public static  Image leftUpperCorner = new ImageIcon("mapImages/wallLeftUpperCorner.png").getImage();
    public static  Image rightLowerCorner = new ImageIcon("mapImages/wallRightLowerCorner.png").getImage();
    public static  Image leftLowerCorner = new ImageIcon("mapImages/wallLeftLowerCorner.png").getImage();
    public static  Image leftP = new ImageIcon("mapImages/wallLeftP.png").getImage();
    public static  Image rightP = new ImageIcon("mapImages/wallRightP.png").getImage();
    public static  Image upperP = new ImageIcon("mapImages/wallUpperP.png").getImage();
    public static  Image lowerP = new ImageIcon("mapImages/wallLowerP.png").getImage();

    public static  Image leftLowerBrick = new ImageIcon("mapImages/wallLeftLowerBrick.png").getImage();
    public static  Image rightLowerBrick = new ImageIcon("mapImages/wallRightLowerBrick.png").getImage();
    public static  Image rightUpperBrick = new ImageIcon("mapImages/wallRightUpperBrick.png").getImage();
    public static  Image leftUpperBrick = new ImageIcon("mapImages/wallLeftUpperBrick.png").getImage();

    public static  Image rightUpperCornerBrick = new ImageIcon("mapImages/wallRightUpperCornerBrick.png").getImage();
    public static  Image leftUpperCornerBrick = new ImageIcon("mapImages/wallLeftUpperCornerBrick.png").getImage();
    public static  Image rightLowerCornerBrick = new ImageIcon("mapImages/wallRightLowerCornerBrick.png").getImage();
    public static  Image leftLowerCornerBrick = new ImageIcon("mapImages/wallLeftLowerCornerBrick.png").getImage();

    public static  Image leftSideUpperBrick = new ImageIcon("mapImages/wallLeftSideUpperBrick.png").getImage();
    public static  Image rightSideUpperBrick = new ImageIcon("mapImages/wallRightSideUpperBrick.png").getImage();

    public static  Image leftSideLowerBrick = new ImageIcon("mapImages/wallLeftSideLowerBrick.png").getImage();
    public static  Image rightSideLowerBrick = new ImageIcon("mapImages/wallRightSideLowerBrick.png").getImage();

    public static  Image leftSideBothBricks = new ImageIcon("mapImages/wallLeftSideBothBricks.png").getImage();
    public static  Image rightSideBothBricks = new ImageIcon("mapImages/wallRightSideBothBricks.png").getImage();

    public static  Image ceilingLeftBrick = new ImageIcon("mapImages/wallCeilingLeftBrick.png").getImage();
    public static  Image floorLeftBrick = new ImageIcon("mapImages/wallFloorLeftBrick.png").getImage();

    public static  Image ceilingRightBrick = new ImageIcon("mapImages/wallCeilingRightBrick.png").getImage();
    public static  Image floorRightBrick = new ImageIcon("mapImages/wallFloorRightBrick.png").getImage();

    public static  Image ceilingBothBricks = new ImageIcon("mapImages/wallCeilingBothBricks.png").getImage();
    public static  Image floorBothBricks = new ImageIcon("mapImages/wallFloorBothBricks.png").getImage();

    public static  Image verticalTonnel = new ImageIcon("mapImages/wallVerticalTonnel.png").getImage();
    public static  Image horisontalTonnel = new ImageIcon("mapImages/wallHorisontalTonnel.png").getImage();

    public static  Image bothLeftBricks = new ImageIcon("mapImages/wallBothLeftBricks.png").getImage();
    public static  Image bothRightBricks = new ImageIcon("mapImages/wallBothRightBricks.png").getImage();

    public static  Image bothUpperBricks = new ImageIcon("mapImages/wallBothUpperBricks.png").getImage();
    public static  Image bothLowerBricks = new ImageIcon("mapImages/wallBothLowerBricks.png").getImage();

    public static  Image fourBricks = new ImageIcon("mapImages/wallFourBricks.png").getImage();

    public static  Image leftDiagonal = new ImageIcon("mapImages/wallLeftDiagonal.png").getImage();
    public static  Image rightDiagonal = new ImageIcon("mapImages/wallRightDiagonal.png").getImage();

    public static  Image threeLeftLower = new ImageIcon("mapImages/wallThreeBricksLeftLower.png").getImage();
    public static  Image threeRightLower = new ImageIcon("mapImages/wallThreeBricksRightLower.png").getImage();
    public static  Image threeLeftUpper = new ImageIcon("mapImages/wallThreeBricksLeftUpper.png").getImage();
    public static  Image threeRightUpper = new ImageIcon("mapImages/wallThreeBricksRightUpper.png").getImage();

    public static  Image blocked = new ImageIcon("mapImages/wallBlocked.png").getImage();
    public static Image secret = new ImageIcon("mapImages/secretWall.png").getImage();



    public Wall(){

    }

    public enum Type{
        CEILING,FLOOR,LEFT_SIDE,RIGHT_SIDE,LEFT_UPPER_CORNER,LEFT_LOWER_CORNER,
        RIGHT_UPPER_CORNER, RIGHT_LOWER_CORNER, CENTER, LEFT_P,RIGHT_P,UPPER_P,LOWER_P,
        LEFT_UPPER_BRICK,LEFT_LOWER_BRICK, RIGHT_UPPER_BRICK, RIGHT_LOWER_BRICK,
        RIGHT_UPPER_CORNER_BRICK, RIGHT_LOWER_CORNER_BRICK, LEFT_UPPER_CORNER_BRICK,LEFT_LOWER_CORNER_BRICK,
        RIGHT_SIDE_LOWER_BRICK, RIGHT_SIDE_UPPER_BRICK, RIGHT_SIDE_BOTH_BRICKS,
        LEFT_SIDE_LOWER_BRICK, LEFT_SIDE_UPPER_BRICK, LEFT_SIDE_BOTH_BRICKS,
        CEILING_LEFT_BRICK,CEILING_RIGHT_BRICK,CEILING_BOTH_BRICKS,
        FLOOR_LEFT_BRICK,FLOOR_RIGHT_BRICK,FLOOR_BOTH_BRICKS,
        VERTICAL_TONNEL, HORISONTAL_TONNEL,
        BOTH_LEFT_BRICKS, BOTH_RIGHT_BRICKS,BOTH_UPPER_BRICKS,BOTH_LOWER_BRICKS,
        FOUR_BRICKS, LEFT_DIAGONAL_BRICKS, RIGHT_DIAGONAL_BRICKS,
        THREE_BRICKS_LEFT_LOWER,THREE_BRICKS_LEFT_UPPER,
        THREE_BRICKS_RIGHT_LOWER,THREE_BRICKS_RIGHT_UPPER,
        BLOCKED, SECRET
    }

    public Wall(Type type){
        switch (type){
            case CEILING:
                image = ceiling;
                break;
            case FLOOR:
                image = floor;
                break;
            case CENTER:
                image = center;
                break;
            case LEFT_SIDE:
                image = left;
                break;
            case RIGHT_SIDE:
                image = right;
                break;
            case LEFT_UPPER_CORNER:
                image = leftUpperCorner;
                break;
            case LEFT_LOWER_CORNER:
                image = leftLowerCorner;
                break;
            case LEFT_UPPER_BRICK:
                image = leftUpperBrick;
                break;
            case LEFT_LOWER_BRICK:
                image = leftLowerBrick;
                break;
            case RIGHT_UPPER_CORNER:
                image = rightUpperCorner;
                break;
            case RIGHT_LOWER_CORNER:
                image = rightLowerCorner;
                break;
            case RIGHT_UPPER_BRICK:
                image = rightUpperBrick;
                break;
            case RIGHT_LOWER_BRICK:
                image = rightLowerBrick;
                break;
            case LEFT_P:
                image = leftP;
                break;
            case RIGHT_P:
                image = rightP;
                break;
            case UPPER_P:
                image = upperP;
                break;
            case LOWER_P:
                image = lowerP;
                break;
            case  LEFT_UPPER_CORNER_BRICK:
                image = leftUpperCornerBrick;
                break;
            case  RIGHT_UPPER_CORNER_BRICK:
                image = rightUpperCornerBrick;
                break;
            case  LEFT_LOWER_CORNER_BRICK:
                image = leftLowerCornerBrick;
                break;
            case  RIGHT_LOWER_CORNER_BRICK:
                image = rightLowerCornerBrick;
                break;
            case  LEFT_SIDE_UPPER_BRICK:
                image = leftSideUpperBrick;
                break;
            case  LEFT_SIDE_LOWER_BRICK:
                image = leftSideLowerBrick;
                break;
            case  LEFT_SIDE_BOTH_BRICKS:
                image = leftSideBothBricks;
                break;
            case  RIGHT_SIDE_UPPER_BRICK:
                image = rightSideUpperBrick;
                break;
            case  RIGHT_SIDE_LOWER_BRICK:
                image = rightSideLowerBrick;
                break;
            case  RIGHT_SIDE_BOTH_BRICKS:
                image = rightSideBothBricks;
                break;
            case  CEILING_LEFT_BRICK:
                image = ceilingLeftBrick;
                break;
            case  CEILING_RIGHT_BRICK:
                image = ceilingRightBrick;
                break;
            case  CEILING_BOTH_BRICKS:
                image = ceilingBothBricks;
                break;
            case  FLOOR_LEFT_BRICK:
                image = floorLeftBrick;
                break;
            case  FLOOR_RIGHT_BRICK:
                image = floorRightBrick;
                break;
            case  FLOOR_BOTH_BRICKS:
                image = floorBothBricks;
                break;
            case  VERTICAL_TONNEL:
                image = verticalTonnel;
                break;
            case  HORISONTAL_TONNEL:
                image = horisontalTonnel;
                break;
            case  BOTH_LEFT_BRICKS:
                image = bothLeftBricks;
                break;
            case  BOTH_RIGHT_BRICKS:
                image = bothRightBricks;
                break;
            case  BOTH_LOWER_BRICKS:
                image = bothLowerBricks;
                break;
            case  BOTH_UPPER_BRICKS:
                image = bothUpperBricks;
                break;
            case  FOUR_BRICKS:
                image = fourBricks;
                break;
            case  LEFT_DIAGONAL_BRICKS:
                image = leftDiagonal;
                break;
            case  RIGHT_DIAGONAL_BRICKS:
                image = rightDiagonal;
                break;
            case  THREE_BRICKS_LEFT_LOWER:
                image = threeLeftLower;
                break;
            case  THREE_BRICKS_LEFT_UPPER:
                image = threeLeftUpper;
                break;
            case  THREE_BRICKS_RIGHT_LOWER:
                image = threeRightLower;
                break;
            case  THREE_BRICKS_RIGHT_UPPER:
                image = threeRightUpper;
                break;
            case  BLOCKED:
                image = blocked;
                break;
            case  SECRET:
                image = secret;
                break;
        }
    }

    @Override
    public boolean pass() {
        return false;
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }
}
