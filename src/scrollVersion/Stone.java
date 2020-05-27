//package scrollVersion;
//
//import maps.Maps;
//
//import java.awt.*;
//
//public abstract class Stone {
//
//    public int x;
//    public int y;
//    public static final int WIDTH = 70;
//    public static final int HEIGHT = 70;
//    protected static final int CELL_SIDE = 70;
//    public int whatMove;
//    public Image image;
//    public int i;
//    public boolean isMoving;
//    public PlayPanel playPanel;
//    public Maps maps;
//    public int xInArray;
//    public int yInArray;
//
//    public void initVars(PlayPanel playPanel, Maps maps, int xInArray, int yInArray){
//        this.playPanel = playPanel;
//        this.maps = maps;
//        this.xInArray = xInArray;
//        this.yInArray = yInArray;
//        this.x = xInArray * 70;
//        this.y = yInArray * 70;
//    }
//
//    //хитатися
//    public void stagger(){
//        if (i != 0){
//            if (i % 2 == 0) x += CELL_SIDE / 14;
//            else x -= CELL_SIDE / 14;
//        }
//        System.out.println("stagger ");
//        i++;
//    }
//
//    public void fallLeft(){
//        if (i == 0){
//            x -= 2 * CELL_SIDE / 7;
//        }
//        else if ((i == 1)||(i == 2)){
//            x -= 1.5 * CELL_SIDE / 7;
//            y += 0.5 * CELL_SIDE / 7;
//            if (i == 2){
//                setStoneToNewPositionInArray(xInArray - 1, yInArray);
//                xInArray -= 1;
//            }
//        }
//        else if (i == 3){
//            x -= CELL_SIDE / 7;
//            y += CELL_SIDE / 7;
//        }
//        else if ((i == 4)||(i == 5)){
//            x -= 0.5 * CELL_SIDE / 7;
//            y += 1.5 * CELL_SIDE / 7;
//            if (i == 5){
//                setStoneToNewPositionInArray(xInArray, yInArray + 1);
//                yInArray++;
//            }
//        }
//        else if (i == 6){
//            y += 2 * CELL_SIDE / 7;
//        }
//        i++;
//    }
//
//    public void fallRight(){
//        if (i == 0){
//            x += 2 * CELL_SIDE / 7;
//        }
//        else if ((i == 1)||(i == 2)){
//            x += 1.5 * CELL_SIDE / 7;
//            y += 0.5 * CELL_SIDE / 7;
//            if (i == 2){
//                setStoneToNewPositionInArray(xInArray + 1, yInArray);
//                xInArray ++;
//            }
//        }
//        else if (i == 3){
//            x += CELL_SIDE / 7;
//            y += CELL_SIDE / 7;
//        }
//        else if ((i == 4)||(i == 5)){
//            x += 0.5 * CELL_SIDE / 7;
//            y += 1.5 * CELL_SIDE / 7;
//            if (i == 5){
//                setStoneToNewPositionInArray(xInArray, yInArray + 1);
//                yInArray++;
//            }
//        }
//        else if (i == 6){
//            y += 2 * CELL_SIDE / 7;
//        }
//        System.out.println("fallRight ");
//        i++;
//    }
//
//    public void fallDown(){
//        if (i == 3){
//            setStoneToNewPositionInArray(xInArray, yInArray + 1);
//            yInArray ++;
//        }
//        y += CELL_SIDE / 7;
//        System.out.println("fallDown ");
//        i++;
//    }
//
//    public abstract void interactWithBoy();
//    protected abstract void setStoneToNewPositionInArray(int xInArray, int yInArray);
//
//}
