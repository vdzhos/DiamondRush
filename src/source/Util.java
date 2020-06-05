package source;

import maps.Cell;
import objects.blocks.SecretWall;
import objects.blocks.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Util {

    /**
     * method returns the font from the file
     * @param fileName
     * @param size
     * @return
     */
    public static Font getFont(String fileName, float size){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }

    /**
     * waits certain time and then performs the action given in ActionListener
     * @param milliseconds
     * @param actionListener
     */
    public static void wait(int milliseconds, ActionListener actionListener){
        Timer t1 = new Timer(milliseconds, actionListener);
        t1.start();
        Timer t2 = new Timer(milliseconds, null);
        t2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.stop();
                t2.stop();
            }
        });
        t2.start();
    }

    /**
     * sets borders to the walls
     * @param level
     */
    public static void setWalls(Cell[][] level){
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == null) {
                    if (i==level.length-1 && j == 0) {
                        if (wallToLeft(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_BRICK), null,null);
                        }
                    }
                    else if (i > 0 && i < level.length - 1 && j > 0 && j < level[0].length-1){
                        if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_LEFT_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_LOWER_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_RIGHT_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_UPPER_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING_BOTH_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING_LEFT_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING_RIGHT_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level)  && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level)  && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR_BOTH_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level)  && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR_RIGHT_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level)  && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR_LEFT_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FOUR_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level)  && !wallToUp(i,j,level)  && wallToRight(i,j,level)  && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.HORISONTAL_TONNEL), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_SIDE), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_DIAGONAL_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_CORNER), null,null);
                        }
                        else  if (!wallToLeft(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_CORNER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && !wallToUp(i,j,level)  && wallToRight(i,j,level) && !wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_P), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_SIDE_BOTH_BRICKS), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_SIDE_LOWER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_SIDE_UPPER_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_UPPER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && !wallToUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_UPPER_CORNER), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && !wallToUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_UPPER_CORNER_BRICK), null,null);
                        }
                        else  if (!wallToLeft(i,j,level) && wallToUp(i,j,level) && !wallToRight(i,j,level) && !wallToRightDown(i,j,level) && !wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LOWER_P), null,null);
                        }
                        else  if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_DIAGONAL_BRICKS), null,null);
                        }
                        else  if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_LOWER_BRICK), null,null);
                        }
                        else  if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRight(i,j,level) && !wallToRightDown(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_LOWER_CORNER), null,null);
                        }
                        else  if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRight(i,j,level) && !wallToRightDown(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_LOWER_CORNER_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level)  && !wallToUp(i,j,level) && !wallToRightUp(i,j,level) && !wallToRight(i,j,level) && !wallToRightDown(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_P), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level)  && !wallToRight(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_SIDE_BOTH_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level)  && !wallToRight(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_SIDE_LOWER_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level)  && !wallToRight(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_SIDE_UPPER_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_UPPER_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level) && !wallToRightUp(i,j,level) && !wallToRight(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_UPPER_CORNER), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level) && !wallToRightUp(i,j,level) && !wallToRight(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_UPPER_CORNER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && !wallToUp(i,j,level) && !wallToRightUp(i,j,level) && !wallToRight(i,j,level)  && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.UPPER_P), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && wallToUp(i,j,level)  && !wallToRight(i,j,level)  && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.VERTICAL_TONNEL), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRight(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_SIDE), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && !wallToUp(i,j,level) && !wallToRightUp(i,j,level) && !wallToRight(i,j,level) && !wallToRightDown(i,j,level) && !wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BLOCKED), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && !wallToUp(i,j,level) &&  !wallToRight(i,j,level) &&  !wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.BLOCKED), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.THREE_BRICKS_LEFT_LOWER), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.THREE_BRICKS_LEFT_UPPER), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.THREE_BRICKS_RIGHT_LOWER), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.THREE_BRICKS_RIGHT_LOWER), null,null);
                        }
                    }
                    else if (i == 0  && j > 0 && j < level[0].length-1){
                        if (wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_RIGHT_BRICKS), null,null);
                        }
                        else if ( wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING), null,null);
                        }
                        else if ( !wallToUp(i,j,level)  && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR), null,null);
                        }
                        else if (!wallToUp(i,j,level)  && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR_RIGHT_BRICK), null,null);
                        }
                        else  if (wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_LOWER_BRICK), null,null);
                        }
                        else if ( wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_UPPER_BRICK), null,null);
                        }
                        else if ( wallToUp(i,j,level) && !wallToRight(i,j,level) && wallToDown(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_SIDE), null,null);
                        }
                    }
                    else if (i == level.length-1 && j > 0 && j < level[0].length-1){
                        if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level)  && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level)  && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING_LEFT_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level)   && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR), null,null);
                        }
                        else if (!wallToLeft(i,j,level)  && wallToUp(i,j,level) && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_SIDE), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && wallToUp(i,j,level) && !wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_CORNER), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level)  && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_UPPER_BRICK), null,null);
                        }
                        else if (!wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && !wallToUp(i,j,level)  && wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_UPPER_CORNER), null,null);
                        }
                    }
                    else if (i > 0  && i < level.length-1 && j == 0 ){
                        if (wallToLeft(i,j,level) && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_LOWER_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToRight(i,j,level) && !wallToDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.CEILING), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToRight(i,j,level) && wallToRightDown(i,j,level) && wallToDown(i,j,level) && !wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_LOWER_BRICK), null,null);
                        }
                        else  if (wallToLeft(i,j,level)  && wallToRight(i,j,level) && !wallToRightDown(i,j,level) && wallToDown(i,j,level) && wallToLeftDown(i,j,level)){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_LOWER_BRICK), null,null);
                        }
                    }
                    else if (i > 0  && i < level.length-1 && j == level[0].length-1 ){
                        if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.BOTH_UPPER_BRICKS), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToUp(i,j,level)  && wallToRight(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.FLOOR), null,null);
                        }
                        else if (wallToLeft(i,j,level) && !wallToLeftUp(i,j,level) && wallToUp(i,j,level) && wallToRightUp(i,j,level) && wallToRight(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.LEFT_UPPER_BRICK), null,null);
                        }
                        else if (wallToLeft(i,j,level) && wallToLeftUp(i,j,level) && wallToUp(i,j,level) && !wallToRightUp(i,j,level) && wallToRight(i,j,level) ){
                            level[i][j] = new Cell (new Wall(Wall.Type.RIGHT_UPPER_BRICK), null,null);
                        }
                    }
                }
            }
        }
    }

    /**
     * checks if it is wall to left
     * @param i
     * @param j
     * @param level
     * @return
     */
    private static boolean wallToLeft(int i, int j, Cell[][] level){
        return (level[i-1][j] == null || (level[i-1][j] != null && (level[i-1][j].getBlock() instanceof Wall || level[i-1][j].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to right
     * @param i
     * @param j
     * @param level
     * @return
     */
    private static boolean wallToRight(int i, int j, Cell[][] level){
        return (level[i+1][j] == null || (level[i+1][j] != null && (level[i+1][j].getBlock() instanceof Wall || level[i+1][j].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to up
     * @param i
     * @param j
     * @param level
     * @return
     */
    private  static boolean wallToUp(int i, int j, Cell[][] level){
        return (level[i][j-1] == null || (level[i][j-1] != null && (level[i][j-1].getBlock() instanceof Wall || level[i][j-1].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to left up
     * @param i
     * @param j
     * @param level
     * @return
     */
    private  static boolean wallToLeftUp(int i, int j, Cell[][] level){
        return (level[i-1][j-1] == null || (level[i-1][j-1] != null && (level[i-1][j-1].getBlock() instanceof Wall || level[i-1][j-1].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to right up
     * @param i
     * @param j
     * @param level
     * @return
     */
    private  static boolean wallToRightUp(int i, int j, Cell[][] level){
        return (level[i+1][j-1] == null || (level[i+1][j-1] != null && (level[i+1][j-1].getBlock() instanceof Wall || level[i+1][j-1].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to down
     * @param i
     * @param j
     * @param level
     * @return
     */
    private  static boolean wallToDown(int i, int j, Cell[][] level){
        return (level[i][j+1] == null || (level[i][j+1] != null && (level[i][j+1].getBlock() instanceof Wall || level[i][j+1].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to left down
     * @param i
     * @param j
     * @param level
     * @return
     */
    private  static boolean wallToLeftDown(int i, int j, Cell[][] level){
        return (level[i-1][j+1] == null || (level[i-1][j+1] != null && (level[i-1][j+1].getBlock() instanceof Wall || level[i-1][j+1].getBlock() instanceof SecretWall)));
    }
    /**
     * checks if it is wall to right down
     * @param i
     * @param j
     * @param level
     * @return
     */
    private  static boolean wallToRightDown(int i, int j, Cell[][] level){
        return (level[i+1][j+1] == null || (level[i+1][j+1] != null && (level[i+1][j+1].getBlock() instanceof Wall || level[i+1][j+1].getBlock() instanceof SecretWall)));
    }









}
