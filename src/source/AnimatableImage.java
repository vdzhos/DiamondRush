package source;

import maps.Cell;
import maps.Level;
import objects.blocks.Checkpoint;
import objects.blocks.Floor;
import objects.blocks.SecretWall;
import objects.blocks.Wall;
import objects.blocks.doors.DoorWithKeyhole;
import objects.blocks.doors.DoubleDoor;
import objects.blocks.doors.PressMechanism;
import objects.harmless.Chest;
import objects.harmless.Diamond;
import objects.harmless.Tumbleweed;
import objects.thingsInChest.*;
import objects.traps.FireTrap;
import objects.traps.Rock;
import objects.traps.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class AnimatableImage {

    public Image image;
    public String name;
    private int delay = Values.DELAY_FOR_BUTTONS;

    public AnimatableImage(String path){
        File file = new File(path);
        name = file.getName().split("\\.")[0];
        this.image = new ImageIcon(path).getImage();
    }

    public void animate(JComponent component){
        image = new ImageIcon("statusBar/"+name+"Shade.png").getImage();
        component.repaint();
        Timer t = new Timer(delay, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon("statusBar/"+name+".png").getImage();
                component.repaint();
                t.stop();
            }
        });
        t.start();
    }

    public static void update(JComponent component){
        component.repaint();
    }

    public void animate(JComponent component, String directory){
        image = new ImageIcon(directory+"/"+name+"Shade.png").getImage();
        component.repaint();
        Timer t = new Timer(delay, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon(directory+"/"+name+".png").getImage();
                component.repaint();
                t.stop();
            }
        });
        t.start();
    }

    public void animate(JComponent component, String directory, Rectangle2D.Double areaToRepaint){
        image = new ImageIcon(directory+"/"+name+"Shade.png").getImage();
        component.repaint((int)areaToRepaint.x,(int)areaToRepaint.y,(int)areaToRepaint.width,(int)areaToRepaint.height);
        Timer t = new Timer(delay, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon(directory+"/"+name+".png").getImage();
                component.repaint((int)areaToRepaint.x,(int)areaToRepaint.y,(int)areaToRepaint.width,(int)areaToRepaint.height);
                t.stop();
            }
        });
        t.start();
    }

    public void animate(JDialog component){
        image = new ImageIcon("statusBar/"+name+"Shade.png").getImage();
        component.repaint();
        Timer t = new Timer(delay, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon("statusBar/"+name+".png").getImage();
                component.repaint();
                t.stop();
            }
        });
        t.start();
    }

    public void animate(JDialog component, String directory){
        image = new ImageIcon(directory+"/"+name+"Shade.png").getImage();
        component.repaint();
        Timer t = new Timer(delay, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon(directory+"/"+name+".png").getImage();
                component.repaint();
                t.stop();
            }
        });
        t.start();
    }

    public void animate(JDialog component, String directory,Rectangle2D.Double areaToRepaint) {
        image = new ImageIcon(directory + "/" + name + "Shade.png").getImage();
        component.repaint((int) areaToRepaint.x, (int) areaToRepaint.y, (int) areaToRepaint.width, (int) areaToRepaint.height);
        Timer t = new Timer(delay, null);
        t.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image = new ImageIcon(directory + "/" + name + ".png").getImage();
                component.repaint((int) areaToRepaint.x, (int) areaToRepaint.y, (int) areaToRepaint.width, (int) areaToRepaint.height);
                t.stop();
            }
        });
        t.start();
    }

    public void setImage(String path){
        File file = new File(path);
        name = file.getName().split("\\.")[0];
        this.image = new ImageIcon(path).getImage();
    }


//    PlayPanel playPanel;
//    private void initLevel5(){
//
//        PressMechanism pressMechanism1 = new PressMechanism(7,10);
//        PressMechanism pressMechanism2 = new PressMechanism(8,38);
//        DoubleDoor doubleDoor1 = new DoubleDoor(null,true,14,15);
//
//        FireTrap fireTrap1 = new FireTrap(0,true);
//        FireTrap fireTrap2 = new FireTrap(2,false);
//        FireTrap fireTrap3 = new FireTrap(2,false);
//        FireTrap fireTrap4 = new FireTrap(2,false);
//        FireTrap fireTrap5 = new FireTrap(2,true);
//        FireTrap fireTrap6 = new FireTrap(2,true);
//        FireTrap fireTrap7 = new FireTrap(2,true);
//        FireTrap fireTrap8 = new FireTrap(2,true);
//
//        Snake snake1 = new Snake(210,70,0,true,playPanel);
//        Snake snake2 = new Snake(210,70,210,true,playPanel);
//        Snake snake3 = new Snake(350,70,0,true,playPanel);
//        Snake snake4 = new Snake(560,70,560,true,playPanel);
//        Snake snake5 = new Snake(560,70,0,true,playPanel);
//
//        Snake snake6 = new Snake(70,420,0,false,playPanel);
//        Snake snake7 = new Snake(840,420,0,false,playPanel);
//        Snake snake8 = new Snake(840,0,0,false,playPanel);
//        Snake snake9 = new Snake(210,70,0,true,playPanel);
//
//        Checkpoint check1 = new Checkpoint(5,3,15,11,14,3,3,4);
//
//        int[] additionalCheck2 = {5,13,18,20};
//        Checkpoint check2 = new Checkpoint(1,5,4,18,additionalCheck2,13,15,4,4);
//
//        Checkpoint check3 = new Checkpoint(1,24,15,38,13,32,4,4);
//
//
//
//        Cell[][] level5 = new Cell[40][40];
//        level5[1][5] = new Cell(new Floor(), new Chest(null,new RedDiamond(1),null, new GoldKey(),new Energy(40)),null);
//        level5[1][17] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),new RedDiamond(1), new SilverKey(), null, new Energy(10)),null);
//        level5[1][25] = new Cell(new Floor(), null,null);
//        level5[1][26] = new Cell(new Floor(), null,null);
//        level5[1][27] = new Cell(new Floor(), null,null);
//        level5[1][28] = new Cell(new Floor(), null,null);
//        level5[1][29] = new Cell(new Floor(), null,null);
//        level5[1][32] = new Cell(new Floor(), null,null);
//        level5[1][33] = new Cell(new Floor(), null,null);
//        level5[1][34] = new Cell(new Floor(), null,null);
//        level5[1][35] = new Cell(new Floor(), new Diamond(),null);
//        level5[1][37] = new Cell(new Floor(), new Chest(new PurpleDiamond(6),new RedDiamond(1),new SilverKey(),null,null ),null);
//
//        level5[2][17] = new Cell(new Floor(), null,null);
//        level5[2][18] = new Cell(new Floor(), null,null);
//        level5[2][25] = new Cell(new Floor(), null,null);
//        level5[2][29] = new Cell(new Floor(), null,null);
//        level5[2][32] = new Cell(new Floor(), null,null);
//        level5[2][33] = new Cell(new Floor(), null,null);
//        level5[2][34] = new Cell(new Floor(), new Diamond(),null);
//        level5[2][35] = new Cell(new Floor(), new Diamond(),null);
//        level5[2][37] = new Cell(new Floor(), null,null);
//        level5[2][38] = new Cell(new Floor(), new Chest(null,null,null,new GoldKey(),new Energy(20)),null);
//
//        level5[3][17] = new Cell(new Floor(), null,null);
//        level5[3][18] = new Cell(new Floor(), null,null);
//        level5[3][25] = new Cell(new Floor(), null,null);
//        level5[3][26] = new Cell(new Floor(), null,null);
//        level5[3][27] = new Cell(new Floor(), null,null);
//        level5[3][28] = new Cell(new Floor(), null,null);
//        level5[3][29] = new Cell(new Floor(), null,null);
//        level5[3][32] = new Cell(new Floor(), null,null);
//        level5[3][33] = new Cell(new Floor(), null,null);
//        level5[3][34] = new Cell(new Floor(), new Diamond(),null);
//        level5[3][35] = new Cell(new Floor(), new Diamond(),null);
//        level5[3][37] = new Cell(new Floor(), null,null);
//        level5[3][38] = new Cell(new Floor(), new Chest(new PurpleDiamond(10),null,null,null,null),null);
//
//        level5[4][18] = new Cell(doubleDoor1.leftDoor, null,null);
//        level5[4][25] = new Cell(new Floor(), null,null);
//        level5[4][29] = new Cell(new Floor(), null,null);
//        level5[4][32] = new Cell(new Floor(), null,null);
//        level5[4][33] = new Cell(new Floor(), null,null);
//        level5[4][34] = new Cell(new Floor(), new Diamond(),null);
//        level5[4][35] = new Cell(new Floor(), new Diamond(),null);
//        level5[4][37] = new Cell(new Floor(), null,null);
//        level5[4][38] = new Cell(new Floor(), null,null);
//
//        level5[5][3] = new Cell(new Floor(), null,null);
//        level5[5][4] = new Cell(new Floor(), null,fireTrap1);
//        level5[5][15] = new Cell(new Floor(), new Diamond(),null);
//        level5[5][16] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[5][18] = new Cell(new Floor(), null,snake4);
//        level5[5][19] = new Cell(new Floor(), null,fireTrap4);
//        level5[5][20] = new Cell(new Floor(), null,snake5);
//        level5[5][25] = new Cell(new Floor(), null,null);
//        level5[5][26] = new Cell(new Floor(), null,null);
//        level5[5][27] = new Cell(new Floor(), null,null);
//        level5[5][28] = new Cell(new Floor(), null,null);
//        level5[5][29] = new Cell(new Floor(), null,null);
//        level5[5][32] = new Cell(new Floor(), null,null);
//        level5[5][33] = new Cell(new Floor(), null,null);
//        level5[5][34] = new Cell(new Floor(), null,null);
//        level5[5][35] = new Cell(new Floor(), new Diamond(),null);
//        level5[5][38] = new Cell(pressMechanism2.door, null,null);
//
//        level5[6][3] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[6][4] = new Cell(new Floor(), null,fireTrap1);
//        level5[6][7] = new Cell(new Floor(), null,snake1);
//        level5[6][15] = new Cell(new Floor(), null,new Rock());
//        level5[6][16] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[6][17] = new Cell(new Floor(), null,null);
//        level5[6][18] = new Cell(new Floor(), null,snake4);
//        level5[6][19] = new Cell(new Floor(), null,fireTrap4);
//        level5[6][20] = new Cell(new Floor(), null,snake5);
//        level5[6][25] = new Cell(new Floor(), null,null);
//        level5[6][29] = new Cell(new Floor(), null,null);
//        level5[6][32] = new Cell(new Floor(), null,null);
//        level5[6][33] = new Cell(new Floor(), null,null);
//        level5[6][34] = new Cell(new Floor(), null,null);
//        level5[6][35] = new Cell(new Floor(), null,null);
//        level5[6][36] = new Cell(new Floor(), null,null);
//        level5[6][37] = new Cell(new Floor(), null,null);
//        level5[6][38] = new Cell(new Floor(), null,null);
//
//        level5[7][3] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[7][4] = new Cell(new Floor(), null,fireTrap1);
//        level5[7][5] = new Cell(new Floor(), null,null);
//        level5[7][6] = new Cell(new Floor(), null,null);
//        level5[7][7] = new Cell(new Floor(), null,snake1);
//        level5[7][8] = new Cell(new Floor(), null,null);
//        level5[7][9] = new Cell(new Floor(), null,null);
//        level5[7][10] = new Cell(pressMechanism1.pressPanel, null,null);
//        level5[7][17] = new Cell(new Floor(), null,null);
//        level5[7][18] = new Cell(new Floor(), null,snake4);
//        level5[7][19] = new Cell(new Floor(), null,fireTrap4);
//        level5[7][20] = new Cell(new Floor(), null,snake5);
//        level5[7][25] = new Cell(new Floor(), null,new Rock());
//        level5[7][29] = new Cell(new Floor(), null,null);
//        level5[7][32] = new Cell(new Floor(), null,null);
//        level5[7][33] = new Cell(new Floor(), null,null);
//        level5[7][34] = new Cell(new Floor(), null,null);
//        level5[7][35] = new Cell(new Floor(), null,null);
//        level5[7][36] = new Cell(new Floor(), null,null);
//        level5[7][37] = new Cell(new Floor(), null,null);
//        level5[7][38] = new Cell(new Floor(), null,null);
//
//        level5[8][3] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[8][4] = new Cell(new Floor(), null,fireTrap1);
//        level5[8][5] = new Cell(new Floor(), null,null);
//        level5[8][6] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[8][7] = new Cell(new Floor(), new Diamond(),snake1);
//        level5[8][10] = new Cell(pressMechanism1.door, null,null);
//        level5[8][14] = new Cell(new Floor(), new Diamond(),null);
//        level5[8][15] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[8][16] = new Cell(new Floor(), null,snake3);
//        level5[8][17] = new Cell(new Floor(), null,fireTrap3);
//        level5[8][18] = new Cell(new Floor(), null,snake4);
//        level5[8][19] = new Cell(new Floor(), null,fireTrap4);
//        level5[8][20] = new Cell(new Floor(), null,snake5);
//        level5[8][24] = new Cell(new Floor(), new Diamond(),null);
//        level5[8][25] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[8][26] = new Cell(new Floor(), null,null);
//        level5[8][27] = new Cell(new Floor(), null,null);
//        level5[8][28] = new Cell(new Floor(), null,null);
//        level5[8][29] = new Cell(new Floor(), null,null);
//        level5[8][32] = new Cell(new Floor(), null,null);
//        level5[8][33] = new Cell(new Floor(), null,null);
//        level5[8][34] = new Cell(new Floor(), null,null);
//        level5[8][35] = new Cell(new Floor(), null,null);
//        level5[8][36] = new Cell(new Floor(), null,null);
//        level5[8][37] = new Cell(new Floor(), null,null);
//        level5[8][38] = new Cell(pressMechanism2.pressPanel, null,null);
//
//        level5[9][3] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[9][5] = new Cell(new Floor(), null,new Rock(null));
//        level5[9][6] = new Cell(new Floor(), null,new Rock(null));
//        level5[9][10] = new Cell(new Floor(), new Chest(new PurpleDiamond(3),null,new SilverKey(),null,null),null);
//        level5[9][11] = new Cell(new Floor(), new Chest(null,new RedDiamond(1), null, new GoldKey(),new Energy(50)),null);
//        level5[9][14] = new Cell(new Floor(), null,new Rock());
//        level5[9][15] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[9][16] = new Cell(new Floor(), null,snake3);
//        level5[9][17] = new Cell(new Floor(), null,fireTrap3);
//        level5[9][18] = new Cell(new Floor(), null,snake4);
//        level5[9][20] = new Cell(new Floor(), null,snake5);
//        level5[9][24] = new Cell(new Floor(), null,new Rock());
//        level5[9][25] = new Cell(new Floor(), null,new Rock());
//        level5[9][29] = new Cell(new Floor(), null,null);
//        level5[9][32] = new Cell(new Floor(), null,null);
//        level5[9][33] = new Cell(new Floor(), null,null);
//        level5[9][34] = new Cell(new Floor(), null,null);
//        level5[9][35] = new Cell(new Floor(), null,null);
//        level5[9][36] = new Cell(new Floor(), null,null);
//
//        level5[10][1] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[10][3] = new Cell(new Floor(), null,null);
//        level5[10][4] = new Cell(new Floor(), null,fireTrap2);
//        level5[10][5] = new Cell(new Floor(), null,null);
//        level5[10][6] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[10][7] = new Cell(new Floor(), new Diamond(),snake2);
//        level5[10][10] = new Cell(pressMechanism1.door, null,null);
//        level5[10][16] = new Cell(new Floor(), null,snake3);
//        level5[10][17] = new Cell(new Floor(), null,fireTrap3);
//        level5[10][18] = new Cell(new Floor(), null,snake4);
//        level5[10][20] = new Cell(new Floor(), null,snake5);
//        level5[10][25] = new Cell(new Floor(), null,null);
//        level5[10][26] = new Cell(new Floor(), null,null);
//        level5[10][29] = new Cell(new Floor(), null,null);
//        level5[10][32] = new Cell(new Floor(), null,null);
//        level5[10][33] = new Cell(new Floor(), null,null);
//        level5[10][34] = new Cell(new Floor(), null,null);
//        level5[10][35] = new Cell(new Floor(), null,null);
//
//        level5[11][1] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[11][3] = new Cell(new Floor(), null,null);
//        level5[11][4] = new Cell(new Floor(), null,fireTrap2);
//        level5[11][5] = new Cell(new Floor(), null,null);
//        level5[11][6] = new Cell(new Floor(), null,null);
//        level5[11][7] = new Cell(new Floor(), null,snake2);
//        level5[11][8] = new Cell(new Floor(), null,null);
//        level5[11][9] = new Cell(new Floor(), null,null);
//        level5[11][10] = new Cell(new Floor(), null,null);
//        level5[11][13] = new Cell(new Floor(), new Diamond(),null);
//        level5[11][14] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[11][15] = new Cell(new Floor(), null,null);
//        level5[11][16] = new Cell(new Floor(), null,snake3);
//        level5[11][17] = new Cell(new Floor(), null,fireTrap3);
//        level5[11][18] = new Cell(new Floor(), null,snake4);
//        level5[11][20] = new Cell(new Floor(), null,snake5);
//        level5[11][23] = new Cell(new Floor(), null,null);
//        level5[11][24] = new Cell(new Floor(), null,null);
//        level5[11][25] = new Cell(new Floor(), null,null);
//        level5[11][26] = new Cell(new Floor(), new Diamond(),null);
//        level5[11][27] = new Cell(new Floor(), new Diamond(),null);
//        level5[11][28] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[11][29] = new Cell(new Floor(), null,null);
//        level5[11][31] = new Cell(new Floor(), null,null);
//        level5[11][32] = new Cell(new Floor(), null,null);
//        level5[11][33] = new Cell(new Floor(), null,null);
//        level5[11][34] = new Cell(new Floor(), null,null);
//
//        level5[12][1] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[12][3] = new Cell(new Floor(), null,null);
//        level5[12][4] = new Cell(new Floor(), null,fireTrap2);
//        level5[12][7] = new Cell(new Floor(), null,snake2);
//        level5[12][13] = new Cell(new Floor(), null,new Rock());
//        level5[12][14] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[12][15] = new Cell(new Floor(), null,null);
//        level5[12][16] = new Cell(new Floor(), null,snake3);
//        level5[12][18] = new Cell(new Floor(), null,snake4);
//        level5[12][20] = new Cell(new Floor(), null,snake5);
//        level5[12][23] = new Cell(new Floor(), null,null);
//        level5[12][24] = new Cell(new Floor(), null,null);
//        level5[12][25] = new Cell(new Floor(), null,null);
//        level5[12][26] = new Cell(new Floor(), new Diamond(),null);
//        level5[12][27] = new Cell(new Floor(), new Diamond(),null);
//        level5[12][29] = new Cell(new Floor(), null,null);
//        level5[12][30] = new Cell(new Floor(), null,null);
//        level5[12][31] = new Cell(new Floor(), null,null);
//        level5[12][32] = new Cell(new Floor(), null,null);
//        level5[12][33] = new Cell(new Floor(), null,null);
//
//        level5[13][1] = new Cell(new Floor(), null,null);
//        level5[13][3] = new Cell(new Floor(), null,null);
//        level5[13][4] = new Cell(new Floor(), null,fireTrap2);
//        level5[13][15] = new Cell(check2, null,null);
//        level5[13][23] = new Cell(new Floor(), null,null);
//        level5[13][24] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[13][25] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[13][26] = new Cell(new Floor(), new Diamond(),null);
//        level5[13][29] = new Cell(new Floor(), null,null);
//        level5[13][30] = new Cell(new Floor(), null,null);
//        level5[13][31] = new Cell(new Floor(), null,null);
//        level5[13][32] = new Cell(check3, null,null);
//
//        level5[14][1] = new Cell(new Floor(), null,null);
//        level5[14][3] = new Cell(check1, null,null);
//        level5[14][15] = new Cell(doubleDoor1.rightDoor, null,null);
//        level5[14][23] = new Cell(new Floor(), null,null);
//        level5[14][24] = new Cell(new Floor(), null,new Rock());
//        level5[14][25] = new Cell(new Floor(), new Diamond(),null);
//        level5[14][32] = new Cell(new Floor(), null,null);
//
//        level5[15][1] = new Cell(new Floor(), null,null);
//        level5[15][3] = new Cell(new DoorWithKeyhole().silver, null,null);
//        level5[15][15] = new Cell(new DoorWithKeyhole().silver, null,null);
//        level5[15][32] = new Cell(new DoorWithKeyhole().silver, null,null);
//
//
//        level5[16][1] = new Cell(new Floor(), null,null);
//        level5[16][2] = new Cell(new Floor(), null,fireTrap5);
//        level5[16][3] = new Cell(new Floor(), null,snake6);
//        level5[16][4] = new Cell(new Floor(), null,snake6);
//        level5[16][5] = new Cell(new Floor(), null,snake6);
//        level5[16][6] = new Cell(new Floor(), null,snake6);
//        level5[16][7] = new Cell(new Floor(), null,snake6);
//        level5[16][8] = new Cell(new Floor(), null,snake6);
//        level5[16][9] = new Cell(new Floor(), null,fireTrap6);
//        level5[16][10] = new Cell(new Floor(), null,snake7);
//        level5[16][11] = new Cell(new Floor(), null,snake7);
//        level5[16][12] = new Cell(new Floor(), null,snake7);
//        level5[16][13] = new Cell(new Floor(), null,snake7);
//        level5[16][14] = new Cell(new Floor(), null,snake7);
//        level5[16][15] = new Cell(new Floor(), null,snake7);
//        level5[16][16] = new Cell(new Floor(), null,snake7);
//        level5[16][17] = new Cell(new Floor(), null,snake7);
//        level5[16][18] = new Cell(new Floor(), null,snake7);
//        level5[16][19] = new Cell(new Floor(), null,snake7);
//        level5[16][20] = new Cell(new Floor(), null,snake7);
//        level5[16][21] = new Cell(new Floor(), null,snake7);
//        level5[16][22] = new Cell(new Floor(), null,fireTrap7);
//        level5[16][23] = new Cell(new Floor(), null,null);
//        level5[16][24] = new Cell(new Floor(), null,fireTrap8);
//        level5[16][25] = new Cell(new Floor(), null,snake8);
//        level5[16][26] = new Cell(new Floor(), null,snake8);
//        level5[16][27] = new Cell(new Floor(), null,snake8);
//        level5[16][28] = new Cell(new Floor(), null,snake8);
//        level5[16][29] = new Cell(new Floor(), null,snake8);
//        level5[16][30] = new Cell(new Floor(), null,snake8);
//        level5[16][31] = new Cell(new Floor(), null,snake8);
//        level5[16][32] = new Cell(new Floor(), null,snake8);
//        level5[16][33] = new Cell(new Floor(), null,snake8);
//        level5[16][34] = new Cell(new Floor(), null,snake8);
//        level5[16][35] = new Cell(new Floor(), null,snake8);
//        level5[16][36] = new Cell(new Floor(), null,snake8);
//        level5[16][38] = new Cell(new Floor(), null,null);
//
//
//        level5[17][1] = new Cell(new Floor(), null,null);
//        level5[17][2] = new Cell(new Floor(), null,fireTrap5);
//        level5[17][3] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[17][5] = new Cell(new Floor(), new Diamond(),null);
//        level5[17][7] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[17][8] = new Cell(new Floor(), null,null);
//        level5[17][9] = new Cell(new Floor(), null,fireTrap6);
//        level5[17][10] = new Cell(new Floor(), null,null);
//        level5[17][12] = new Cell(new Floor(), new Diamond(),null);
//        level5[17][14] = new Cell(new Floor(), new Diamond(),null);
//        level5[17][16] = new Cell(new Floor(), null,new Rock());
//        level5[17][18] = new Cell(new Floor(), new Diamond(),null);
//        level5[17][20] = new Cell(new Floor(), new Diamond(),null);
//        level5[17][22] = new Cell(new Floor(), null,fireTrap7);
//        level5[17][23] = new Cell(new Floor(), null,null);
//        level5[17][24] = new Cell(new Floor(), null,fireTrap8);
//        level5[17][25] = new Cell(new Floor(), null,null);
//        level5[17][27] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[17][28] = new Cell(new Floor(), null,new Rock());
//        level5[17][29] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[17][31] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[17][32] = new Cell(new Floor(), null,new Rock());
//        level5[17][33] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[17][35] = new Cell(new Floor(), null,null);
//        level5[17][36] = new Cell(new Floor(), null,null);
//        level5[17][37] = new Cell(new Floor(), null,snake9);
//        level5[17][38] = new Cell(new Floor(), null,null);
//
//        level5[18][1] = new Cell(new Floor(), null,null);
//        level5[18][2] = new Cell(new Floor(), null,fireTrap5);
//        level5[18][8] = new Cell(new Floor(), null,null);
//        level5[18][9] = new Cell(new Floor(), null,fireTrap6);
//        level5[18][10] = new Cell(new Floor(), null,null);
//        level5[18][11] = new Cell(new Floor(), new Chest(new PurpleDiamond(3),null,null,null,new Energy(30)),null);
//        level5[18][21] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[18][22] = new Cell(new Floor(), null,fireTrap7);
//        level5[18][23] = new Cell(new Floor(), null,null);
//        level5[18][24] = new Cell(new Floor(), null,fireTrap8);
//        level5[18][25] = new Cell(new Floor(), null,null);
//        level5[18][26] = new Cell(new Floor(), new Diamond(),null);
//        level5[18][28] = new Cell(new Floor(), new Chest(null,null,null,null, new Energy(50)),null);
//        level5[18][30] = new Cell(new Floor(), new Diamond(),null);
//        level5[18][32] = new Cell(new Floor(), null,null);
//        level5[18][34] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[18][35] = new Cell(new Floor(), null,null);
//        level5[18][36] = new Cell(new Floor(), null,null);
//        level5[18][37] = new Cell(new Floor(), null,snake9);
//        level5[18][38] = new Cell(new Floor(), new Chest(null,null, new SilverKey(),null,new Energy(30)),null);
//
//        level5[19][1] = new Cell(new Floor(), null,null);
//        level5[19][2] = new Cell(new Floor(), null,fireTrap5);
//        level5[19][3] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[19][5] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),null,null,null,new Energy(30)),null);
//        level5[19][7] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[19][8] = new Cell(new Floor(), null,null);
//        level5[19][9] = new Cell(new Floor(), null,fireTrap6);
//        level5[19][10] = new Cell(new Floor(), null,null);
//        level5[19][12] = new Cell(new Floor(), new Diamond(),null);
//        level5[19][14] = new Cell(new Floor(), new Diamond(),null);
//        level5[19][16] = new Cell(new Floor(), null,new Rock());
//        level5[19][18] = new Cell(new Floor(), new Diamond(),null);
//        level5[19][20] = new Cell(new Floor(), new Diamond(),null);
//        level5[19][22] = new Cell(new Floor(), null,fireTrap7);
//        level5[19][23] = new Cell(new Floor(), null,null);
//        level5[19][24] = new Cell(new Floor(), null,fireTrap8);
//        level5[19][25] = new Cell(new Floor(), null,null);
//        level5[19][27] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[19][28] = new Cell(new Floor(), null,null);
//        level5[19][29] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[19][31] = new Cell(new Floor(), null,new Rock());
//        level5[19][32] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[19][33] = new Cell(new Floor(), new Tumbleweed(),null);
//        level5[19][35] = new Cell(new Floor(), null,null);
//        level5[19][36] = new Cell(new Floor(), null,null);
//        level5[19][37] = new Cell(new Floor(), null,snake9);
//        level5[19][38] = new Cell(new Floor(), null,null);
//
//
//        level5[1][6] = new Cell(new SecretWall(Wall.Type.FLOOR),null,null);
//        level5[1][7] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[1][11] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[1][12] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[1][13] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[1][14] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[1][15] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//
//        level5[2][7] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[2][10] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[2][11] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[2][15] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//
//        level5[3][7] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[3][8] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[3][10] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[3][13] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[3][14] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[3][15] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//
//        level5[4][8] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[4][9] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[4][10] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[4][12] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[4][13] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//
//        level5[5][12] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//
//        level5[6][12] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[6][13] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
//        level5[7][13] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
//        level5[8][13] = new Cell(new SecretWall(Wall.Type.CEILING),null,null);
//
//
//
//        for (int i = 0; i < level5.length; i++) {
//            for (int j = 0; j < level5[i].length; j++) {
//                if(level5[i][j]==null){
//                    level5[i][j] = new Cell(new Wall(),null,null);
//                }
//            }
//        }
//
//        Checkpoint[] checkpoints = {check1,check2,check3};
//        for (Checkpoint checkpoint: checkpoints){
//            checkpoint.setInitialMatrix(level5);
//        }
//
//        this.level5 = new Level(level5,4,1,12,1, 200, 5);
//        this.level5.setCheckpoints(checkpoints);
//    }

}
