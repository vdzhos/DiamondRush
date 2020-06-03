package maps;

import objects.blocks.doors.*;
import objects.blocks.*;
import objects.harmless.Chest;
import objects.thingsInChest.*;
import objects.traps.FireTrap;
import objects.harmless.Diamond;
import objects.harmless.Tumbleweed;
import objects.traps.Rock;
import objects.traps.Scorpion;
import objects.traps.Snake;
import source.PlayPanel;
import source.Util;

import javax.swing.*;
import java.awt.image.DataBufferFloat;

public class Maps {

//    private Cell[][] level1;
//    private Cell[][] level2;
//    private Cell[][] level3;
//    private Cell[][] level4;
//    private Cell[][] level5;

    private Level level1;
    private Level level2;
    private Level level3;
    private Level level4;
    private Level level5;

    private PlayPanel playPanel;

    public Maps(PlayPanel playPanel) {
        this.playPanel = playPanel;
    }

    public void initLevel(int currentLevel) {
        switch (currentLevel) {
            case 1:
                initLevel1();
                break;
            case 2:
                initLevel2();
                break;
            case 3:
                initLevel3();
                break;
            case 4:
                initLevel4();
                break;
            case 5:
                initLevel5();
                break;
        }
    }


    public Level getLevel(int level) {
        switch (level) {
            case 1:
                return level1;
            case 2:
                return level2;
            case 3:
                return level3;
            case 4:
                return level4;
            case 5:
                return level5;
        }
        return null;
    }

    private void initLevel1() {

        Snake snake1 = new Snake(140, 70, 40, true, playPanel);
        Snake snake2 = new Snake(350, 70, 100, true, playPanel);
        Snake snake3 = new Snake(70, 350, 280, false, playPanel);
        Snake snake4 = new Snake(70, 350, 210, false, playPanel);
        Snake snake5 = new Snake(70, 140, 10, false, playPanel);

        Scorpion scorpion = new Scorpion(210, 210, false, null, null);

        FireTrap fireTrap1 = new FireTrap(0, false);
        FireTrap fireTrap2 = new FireTrap(3, false);
        FireTrap fireTrap3 = new FireTrap(2, false);

        Snake[] snakes = {snake3, snake4};
        DoubleDoor doubleDoor = new DoubleDoor(snakes, true, 20, 13);
        PressMechanism pressMechanism = new PressMechanism(5, 20);
        PressMechanism pressMechanism2 = new PressMechanism(12, 19);

        int[] additionalSegmentCheck1 = {12, 16, 14, 19};
        Checkpoint check1 = new Checkpoint(3, 8, 11, 21, additionalSegmentCheck1, 4, 16, 4, 4);
        int[] additionalSegmentCheck2 = {12, 8, 14, 8};
        Checkpoint check2 = new Checkpoint(6, 1, 18, 7, additionalSegmentCheck2, 8, 7, 4, 4);
        Checkpoint check3 = new Checkpoint(18, 3, 24, 13, 18, 4, 4, 4);
        int[] additionalSegmentCheck4 = {9, 16, 10, 16};
        Checkpoint check4 = new Checkpoint(11, 10, 18, 19, additionalSegmentCheck4, 19, 13, 4, 4);
        int[] additionalSegmentCheck5 = {35, 4, 38, 14};
        Checkpoint check5 = new Checkpoint(25, 4, 34, 7, additionalSegmentCheck5, 25, 5, 4, 4);

        //Floor + (Harmless/Trap)
        Cell[][] level1 = new Cell[40][22];
        level1[0][16] = new Cell(new Floor(), null, null);
        level1[1][16] = new Cell(new BlockedDoor(), null, null);
        level1[2][16] = new Cell(new Floor(), null, null);
        level1[3][16] = new Cell(new Floor(), null, null);
        level1[3][18] = new Cell(new Floor(), null, null);
        level1[3][19] = new Cell(new Floor(), new Diamond(), null);
        level1[4][16] = new Cell(check1, null, null);
        level1[4][18] = new Cell(new Floor(), null, null);
        level1[4][19] = new Cell(new Floor(), null, new Rock(null));
        level1[5][16] = new Cell(new Floor(),null,null);
        level1[5][17] = new Cell(new Floor(), null, null);
        level1[5][18] = new Cell(new Floor(), null, null);
        level1[5][19] = new Cell(new Floor(), null, null);
        level1[5][20] = new Cell(pressMechanism.pressPanel, null, null);
        level1[6][4] = new Cell(new Floor(), null, snake2);
        level1[6][10] = new Cell(new Floor(), new Diamond(), null);
        level1[6][11] = new Cell(new Floor(), new Diamond(), null);
        level1[6][12] = new Cell(new Floor(), new Tumbleweed(), null);
        level1[6][16] = new Cell(new Floor(), new Diamond(), null);
        level1[6][18] = new Cell(new Floor(), null, null);
        level1[6][19] = new Cell(new Floor(), null, null);
        level1[6][20] = new Cell(new Floor(), null, null);
        level1[7][4] = new Cell(new Floor(), null, snake2);
        level1[7][7] = new Cell(new Floor(), null, null);
        level1[7][8] = new Cell(new Floor(), new Diamond(), null);
        level1[7][9] = new Cell(new Floor(), new Diamond(), null);
        level1[7][10] = new Cell(new Floor(), new Diamond(), null);
        level1[7][12] = new Cell(new Floor(), null, null);
        level1[7][13] = new Cell(new Floor(), null, fireTrap1);
        level1[7][19] = new Cell(pressMechanism.door, null, null);
        level1[8][4] = new Cell(new Floor(), null, snake2);
        level1[8][5] = new Cell(new Floor(), null, null);
        level1[8][6] = new Cell(new Floor(), null, null);
        level1[8][7] = new Cell(check2, null, null);
        level1[8][12] = new Cell(new Floor(), null, null);
        level1[8][13] = new Cell(new Floor(), null, fireTrap1);
        level1[8][19] = new Cell(new Floor(), null, null);
        level1[9][4] = new Cell(new Floor(), null, snake2);
        level1[9][12] = new Cell(new Floor(), null, null);
        level1[9][13] = new Cell(new Floor(), null, fireTrap1);
        level1[9][14] = new Cell(new Floor(), null, null);
        level1[9][15] = new Cell(new Floor(), null, null);
        level1[9][16] = new Cell(new Floor(), null, snake1);
        level1[9][17] = new Cell(new Floor(), null, null);
        level1[9][18] = new Cell(new Floor(), null, null);
        level1[9][19] = new Cell(new Floor(), null, null);
        level1[10][3] = new Cell(new Floor(), null, null);
        level1[10][4] = new Cell(new Floor(), null, snake2);
        level1[10][13] = new Cell(new Floor(), null, fireTrap1);
        level1[10][14] = new Cell(new Floor(), null, null);
        level1[10][15] = new Cell(new Floor(), null, null);
        level1[10][16] = new Cell(new Floor(), null, snake1);
        level1[10][17] = new Cell(new Floor(), null, null);
        level1[10][18] = new Cell(new Floor(), null, null);
        level1[11][2] = new Cell(new Floor(), new Diamond(), null);
        level1[11][3] = new Cell(new Floor(), new Diamond(), null);
        level1[11][15] = new Cell(new Floor(), null, new Rock(null));
        level1[11][17] = new Cell(new Floor(), null, null);
        level1[11][18] = new Cell(new Floor(), null, null);
        level1[12][2] = new Cell(new Floor(), null, new Rock(null));//There was diamond
        level1[12][3] = new Cell(new Floor(), new Tumbleweed(), null);
        level1[12][4] = new Cell(new Floor(), new Diamond(), null);
        level1[12][7] = new Cell(new Floor(),null,null);
        level1[12][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                null, null, new Energy(10)),null);
        level1[12][13] = new Cell(new Floor(),null,null);
        level1[12][14] = new Cell(new Floor(),null,null);
        level1[12][15] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][17] = new Cell(new Floor(),null,null);
        level1[12][18] = new Cell(new Floor(),null,null);
        level1[12][19] = new Cell(pressMechanism2.pressPanel,null,null);
        level1[13][1] = new Cell(new Floor(),null,null);//new Rock(null)
        level1[13][2] = new Cell(new Floor(),new Diamond(),null);
        level1[13][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[13][4] = new Cell(new Floor(),new Diamond(),null);
        level1[13][7] = new Cell(new Floor(),null,null);
        level1[13][8] = new Cell(new Floor(),new Diamond(),null);
        level1[13][13] = new Cell(doubleDoor.leftDoor,null,null);
        level1[13][17] = new Cell(new Floor(),null,null);
        level1[13][18] = new Cell(new Floor(),null,null);
        level1[13][19] = new Cell(new Floor(),null,null);
        level1[14][1] = new Cell(new Floor(),null,null);
        level1[14][2] = new Cell(new Floor(),null,new Rock(null));
        level1[14][3] = new Cell(new Floor(),new Diamond(),null);
        level1[14][4] = new Cell(new Floor(),null,new Rock(null));
        level1[14][7] = new Cell(new Floor(),null,null);
        level1[14][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(5), null, null, null, null),null);
        level1[14][11] = new Cell(new Floor(),null,null);
        level1[14][12] = new Cell(new Floor(),null,null);
        level1[14][13] = new Cell(new Floor(),null,null);
        level1[14][14] = new Cell(new Floor(),null,null);
        level1[14][18] = new Cell(pressMechanism2.door,null,null);
        level1[15][2] = new Cell(new Floor(),new Diamond(),null);
        level1[15][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[15][4] = new Cell(new Floor(),new Diamond(),null);
        level1[15][10] = new Cell(new Wall(),null,snake3);
        level1[15][11] = new Cell(new Floor(),null,new Rock(snake3));
        level1[15][12] = new Cell(new Floor(),new Tumbleweed(),snake3);
        level1[15][13] = new Cell(new Floor(),null,snake3);
        level1[15][14] = new Cell(new Floor(),null,snake3);
        level1[15][18] = new Cell(new Floor(),null,null);
        level1[16][2] = new Cell(new Floor(),null,null);
        level1[16][3] = new Cell(new Floor(),null,null);
        level1[16][4] = new Cell(new Floor(),new Diamond(),null);
        level1[16][10] = new Cell(new Wall(),null,snake4);
        level1[16][11] = new Cell(new Floor(),null,snake4);
        level1[16][12] = new Cell(new Floor(),new Tumbleweed(),snake4);
        level1[16][13] = new Cell(new Floor(),null,snake4);
        level1[16][14] = new Cell(new Floor(),null,snake4);
        level1[16][18] = new Cell(new Floor(),null,null);
        level1[17][3] = new Cell(new Floor(),null,null);
        level1[17][4] = new Cell(new Floor(),null,null);
        level1[17][11] = new Cell(new Floor(),null,new Rock(null));
        level1[17][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[17][13] = new Cell(new Floor(),null,null);
        level1[17][14] = new Cell(new Floor(),null,null);
        level1[17][18] = new Cell(new Floor(),null,null);
        level1[18][4] = new Cell(check3,null,null);
        level1[18][7] = new Cell(new Floor(),null,fireTrap2);
        level1[18][11] = new Cell(new Floor(),null,null);
        level1[18][12] = new Cell(new Floor(),null,null);
        level1[18][13] = new Cell(new Floor(),null,null);
        level1[18][18] = new Cell(new Floor(),null,null);
        level1[19][3] = new Cell(new Floor(),null,null);
        level1[19][4] = new Cell(new Floor(),new Diamond(),null);
        level1[19][7] = new Cell(new Floor(),null,fireTrap2);
        level1[19][8] = new Cell(new Floor(),new Chest(null, null, null, new GoldKey(), new Energy(10)),null);
        level1[19][9] = new Cell(new Floor(),null,fireTrap3);
        level1[19][13] = new Cell(check4,null,null);
        level1[19][18] = new Cell(new Floor(),null,null);
        level1[20][3] = new Cell(new Floor(),null,null);
        level1[20][4] = new Cell(new Floor(),new Diamond(),null);
        level1[20][5] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[20][7] = new Cell(new Floor(),null,fireTrap2);
        level1[20][8] = new Cell(new Floor(),null,null);
        level1[20][9] = new Cell(new Floor(),null,fireTrap3);
        level1[20][13] = new Cell(doubleDoor.rightDoor,null,null);
        level1[20][18] = new Cell(new Floor(),null,null);
        level1[21][3] = new Cell(new Floor(),null,null);
        level1[21][4] = new Cell(new Floor(),new Diamond(),null);
        level1[21][5] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[21][6] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[21][7] = new Cell(new Floor(),null,fireTrap2);
        level1[21][8] = new Cell(new Floor(),null,null);
        level1[21][9] = new Cell(new Floor(),null,fireTrap3);
        level1[21][10] = new Cell(new Floor(),null,null);
        level1[21][11] = new Cell(new Floor(),null,null);
        level1[21][12] = new Cell(new Floor(),null,null);
        level1[21][13] = new Cell(new Floor(),null,null);
        level1[21][18] = new Cell(new DoorWithKeyhole().gold,null,null);
        level1[22][3] = new Cell(new Floor(),null,null);
        level1[22][4] = new Cell(new Floor(),null,null);
        level1[22][9] = new Cell(new Floor(),null,fireTrap3);
        level1[22][18] = new Cell(new Floor(),null,null);
        level1[23][17] = new Cell(new Floor(),null,null);
        level1[23][18] = new Cell(new Floor(),null,null);
        level1[24][16] = new Cell(new Floor(),null,null);
        level1[24][17] = new Cell(new Floor(),null,null);
        level1[24][18] = new Cell(new Floor(),null,null);
        level1[25][5] = new Cell(check5,null,null);
        level1[25][6] = new Cell(new Floor(),null,null);
        level1[25][7] = new Cell(new Floor(),new Chest(new PurpleDiamond(7), null, null, null, null),null);
        level1[25][11] = new Cell(new Floor(),new Diamond(),null);
        level1[25][15] = new Cell(new Floor(),null,null);
        level1[25][16] = new Cell(new Floor(),null,null);
        level1[25][17] = new Cell(new Floor(),null,null);
        level1[25][18] = new Cell(new Floor(),null,null);
        level1[26][5] = new Cell(new Floor(),null,null);
        level1[26][6] = new Cell(new Floor(),null,null);
        level1[26][7] = new Cell(new Floor(),null,null);
        level1[26][14] = new Cell(new Floor(),null,null);
        level1[26][15] = new Cell(new Floor(),null,null);
        level1[26][16] = new Cell(new Floor(),null,null);
        level1[26][17] = new Cell(new Floor(),new Chest(null, new RedDiamond(2), new SilverKey(), null, null),null);
        level1[27][5] = new Cell(new Floor(),null,new Rock(null));
        level1[27][15] = new Cell(new Floor(),null,null);
        level1[27][16] = new Cell(new Floor(),null,null);
        level1[27][17] = new Cell(new Floor(),null,null);
        level1[27][18] = new Cell(new Floor(),null,null);
        level1[28][5] = new Cell(new Floor(),new Diamond(),null);
        level1[28][7] = new Cell(new Floor(),null,null);
        level1[28][11] = new Cell(new DoorWithKeyhole().silver,null,null);
        level1[28][16] = new Cell(new Floor(),null,null);
        level1[28][17] = new Cell(new Floor(),null,null);
        level1[28][18] = new Cell(new Floor(),null,null);
        level1[29][5] = new Cell(new Floor(),new Diamond(),null);
        level1[29][7] = new Cell(new Floor(),null,null);
        level1[29][11] = new Cell(new Floor(),null,null);
        level1[29][17] = new Cell(new Floor(),null,null);
        level1[29][18] = new Cell(new Floor(),null,null);
        level1[30][7] = new Cell(new Floor(),null,null);
        level1[30][11] = new Cell(new Floor(),null,null);
        level1[30][18] = new Cell(new Floor(),null,null);
        level1[31][6] = new Cell(new Floor(),null,null);
        level1[31][7] = new Cell(new Floor(),null,null);
        level1[32][6] = new Cell(new Floor(),null,snake5);
        level1[32][7] = new Cell(new Floor(),null,snake5);
        level1[32][12] = new Cell(new Floor(),new Diamond(),null);
        level1[32][13] = new Cell(new Floor(),new Diamond(),null);
        level1[32][14] = new Cell(new Floor(),new Diamond(),null);
        level1[32][17] = new Cell(new Floor(),null,null);
        level1[33][4] = new Cell(new Floor(),null,null);
        level1[33][4] = new Cell(new Floor(),null,null);
        level1[33][5] = new Cell(new Floor(),null,null);
        level1[33][6] = new Cell(new Floor(),null,null);
        level1[33][7] = new Cell(new Floor(),null,null);
        level1[34][4] = new Cell(new Floor(),null,null);
        level1[34][6] = new Cell(new Floor(),null,null);
        level1[34][7] = new Cell(new Floor(),null,null);
        level1[35][4] = new Cell(new Floor(),null,null);
        level1[36][4] = new Cell(new DiamondDoor(30,true),null,null);
        level1[36][11] = new Cell(new Floor(),null,scorpion);
        level1[36][12] = new Cell(new Floor(),null,scorpion);
        level1[36][13] = new Cell(new Floor(),null,scorpion);
        level1[36][14] = new Cell(new Floor(),null,null);
        level1[37][4] = new Cell(new Floor(),null,null);
        level1[37][11] = new Cell(new Floor(),null,scorpion);
        level1[37][12] = new Cell(new Floor(),null,null);
        level1[37][13] = new Cell(new Floor(),null,scorpion);
        level1[37][14] = new Cell(new Floor(),new Diamond(),null);
        level1[38][4] = new Cell(new Floor(),null,null);
        level1[38][11] = new Cell(new Floor(),null,scorpion);
        level1[38][12] = new Cell(new Floor(),null,scorpion);
        level1[38][13] = new Cell(new Floor(),null,scorpion);
        level1[38][14] = new Cell(new Floor(),new Chest(new PurpleDiamond(4), new RedDiamond(1), null, null, new Energy(5)),null);
        level1[39][4] = new Cell(new Floor(),null,null);

        //SecretWalls
        level1[9][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[10][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[11][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[35][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[36][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][8] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][9] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[37][10] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        //BreakableWalls
        level1[22][11] = new Cell(new BreakableWall(22,11),null,null);
        level1[23][3] = new Cell(new BreakableWall(23,3),null,null);
        level1[23][4] = new Cell(new BreakableWall(23,4),null,null);
        level1[23][11] = new Cell(new BreakableWall(23,11),null,null);
        level1[24][3] = new Cell(new BreakableWall(24,3),null,null);
        level1[24][4] = new Cell(new BreakableWall(24,4),null,null);
        level1[24][5] = new Cell(new BreakableWall(24,5),null,null);
        level1[24][11] = new Cell(new BreakableWall(24,11),null,null);
        level1[26][11] = new Cell(new BreakableWall(26,11),null,null);
        level1[27][6] = new Cell(new BreakableWall(27,6),null,null);
        level1[27][7] = new Cell(new BreakableWall(27,7),null,null);
        level1[27][11] = new Cell(new BreakableWall(27,11),null,null);
        level1[28][6] = new Cell(new BreakableWall(28,6),null,null);
        level1[29][6] = new Cell(new BreakableWall(29,6),null,null);
        level1[31][11] = new Cell(new BreakableWall(31,11),null,null);
        level1[31][18] = new Cell(new BreakableWall(31,18),null,null);
        level1[32][11] = new Cell(new BreakableWall(32,11),null,null);
        level1[32][15] = new Cell(new BreakableWall(32,15),null,null);
        level1[32][16] = new Cell(new BreakableWall(32,16),null,null);
        level1[32][18] = new Cell(new BreakableWall(32,18),null,null);


        Util.setWalls(level1);

        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                if(level1[i][j]==null){
                    level1[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level1);
        }

        this.level1 = new Level(level1,2,4,2,16, 200,1);
        this.level1.setCheckpoints(checkpoints);
    }













    private void initLevel2(){
        FireTrap fireTrap1 = new FireTrap(0,false);
        FireTrap fireTrap2 = new FireTrap(3,true);
        FireTrap fireTrap3 = new FireTrap(2,true);
        FireTrap fireTrap4 = new FireTrap(2,true);

        Snake snake1 = new Snake(70,490,490,false,playPanel);
        Snake snake2 = new Snake(70,490,170,false,playPanel);
        Snake snake3 = new Snake(70,560,0,false,playPanel);
        Snake snake4 = new Snake(70,280,140,false,playPanel);
        Snake snake5 = new Snake(70,420,420,false,playPanel);
        Snake snake6 = new Snake(70,420,350,false,playPanel);
        Snake snake7 = new Snake(350,70,0,true,playPanel);
        Snake snake8 = new Snake(490,70,0,true,playPanel);
        Snake snake9 = new Snake(490,70,490,true,playPanel);
        Snake snake10 = new Snake(70,140,0,false,playPanel);

        Scorpion scorpion1 = new Scorpion(210,280,false,null,null);
        Scorpion scorpion2 = new Scorpion(210,280,true,null,null);

        Snake[] snakes = {snake8,snake9};

        PressMechanism pressMechanism1 = new PressMechanism(18,5);
        PressMechanism pressMechanism2 = new PressMechanism(32,21);
        DoubleDoor doubleDoor = new DoubleDoor(snakes,false,20,14);

        int[] additionalSegment1 = {5,16,6,16};
        Checkpoint check1 = new Checkpoint(2,6,12,15,additionalSegment1,4,14,4,4);
        Checkpoint check2 = new Checkpoint(2,1,9,7,2,5,2,4);
        Checkpoint check3 = new Checkpoint(10,1,20,6,10,4,4,4);
        Checkpoint check4 = new Checkpoint(31,2,35,11,31,2,4,2);
        Checkpoint check5 = new Checkpoint(17,7,29,11,30,9,4,4);
        int[] additionalSegment6 = {5,18,6,21};
        Checkpoint check6 = new Checkpoint(1,16,4,21,additionalSegment6,3,16,3,4);
        Checkpoint check7 = new Checkpoint(7,16,15,22,8,21,4,7);
        Checkpoint check8 = new Checkpoint(18,14,26,21,21,14,4,4);
        Checkpoint check9 = new Checkpoint(28,19,37,21,28,20,4,6);
        Checkpoint check10 = new Checkpoint(27,13,38,17,27,14,4,4);



        Cell[][] level2 = new Cell[40][24];
        level2[0][14] = new Cell(new Floor(), null, null);
        level2[1][14] = new Cell(new BlockedDoor(), null, null);
        level2[1][15] = new Cell(new Wall(Wall.Type.HORISONTAL_TONNEL), null, snake1);
        level2[1][16] = new Cell(new Floor(), null, snake1);
        level2[1][17] = new Cell(new Floor(), null, snake1);
        level2[1][18] = new Cell(new Floor(), null, snake1);
        level2[1][19] = new Cell(new Floor(), null, snake1);
        level2[1][20] = new Cell(new Floor(), null, snake1);
        level2[1][21] = new Cell(new Floor(), null, snake1);
        level2[2][3] = new Cell(new Floor(), null, null);
        level2[2][4] = new Cell(new Floor(), null, null);
        level2[2][5] = new Cell(check2, null, null);
        level2[2][6] = new Cell(new Floor(), null, null);
        level2[2][14] = new Cell(new Floor(), null, null);
        level2[2][17] = new Cell(new Floor(), new Diamond(), null);
        level2[2][19] = new Cell(new Floor(), new Diamond(), null);
        level2[2][21] = new Cell(new Floor(), new Diamond(), null);
        level2[3][2] = new Cell(new Floor(), null, null);
        level2[3][3] = new Cell(new Floor(), null, null);
        level2[3][4] = new Cell(new Floor(), null, null);
        level2[3][6] = new Cell(new Floor(), null, null);
        level2[3][8] = new Cell(new Floor(), new Diamond(), null);
        level2[3][11] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[3][14] = new Cell(new Floor(), null, null);
        level2[3][15] = new Cell(new Wall(Wall.Type.HORISONTAL_TONNEL), null, snake2);
        level2[3][16] = new Cell(check6, null, snake2);
        level2[3][17] = new Cell(new Floor(), null, snake2);
        level2[3][18] = new Cell(new Floor(), null, snake2);
        level2[3][19] = new Cell(new Floor(), null, snake2);
        level2[3][20] = new Cell(new Floor(), null, snake2);
        level2[3][21] = new Cell(new Floor(), null, snake2);
        level2[4][1] = new Cell(new Floor(), null, new Rock(null));
        level2[4][2] = new Cell(new Floor(), new Diamond(), null);
        level2[4][4] = new Cell(new Floor(), null, null);
        level2[4][6] = new Cell(new Floor(), null, snake3);
        level2[4][7] = new Cell(new Floor(), null, snake3);
        level2[4][8] = new Cell(new Floor(), null, snake3);
        level2[4][9] = new Cell(new Floor(), null, snake3);
        level2[4][10] = new Cell(new Floor(), null, snake3);
        level2[4][11] = new Cell(new Floor(), null, snake3);
        level2[4][12] = new Cell(new Floor(), null, snake3);
        level2[4][13] = new Cell(new Floor(), null, snake3);
        level2[4][14] = new Cell(check1, null, null);
        level2[4][16] = new Cell(new DiamondDoor(0,false), null, null);
        level2[4][18] = new Cell(new Floor(), new Diamond(), null);
        level2[4][20] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[5][1] = new Cell(new Floor(), null, new Rock(null));
        level2[5][2] = new Cell(new Floor(), new Diamond(), null);
        level2[5][4] = new Cell(new Floor(), null, null);
        level2[5][9] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),null,null,null,null), null);
        level2[5][12] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[5][13] = new Cell(new Floor(), null, null);
        level2[5][14] = new Cell(new Floor(), null, null);
        level2[5][16] = new Cell(new Floor(), null, null);
        level2[5][18] = new Cell(new Floor(), null, new Rock(null));
        level2[5][19] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[5][20] = new Cell(new Floor(), null, new Rock(null));
        level2[5][21] = new Cell(new Floor(), new Diamond(), null);
        level2[6][1] = new Cell(new Floor(), null, new Rock(null));
        level2[6][2] = new Cell(new Floor(), new Diamond(), null);
        level2[6][4] = new Cell(new Floor(), null, null);
        level2[6][12] = new Cell(new Floor(), null, new Rock(null));
        level2[6][13] = new Cell(new Floor(), new Diamond(),null);
        level2[6][14] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[6][15] = new Cell(new Floor(), null, null);
        level2[6][16] = new Cell(new Floor(), null, null);
        level2[6][18] = new Cell(new Floor(), null, null);
        level2[6][19] = new Cell(new Floor(), null, null);
        level2[6][20] = new Cell(new Floor(), null, null);
        level2[6][21] = new Cell(new Floor(), new Diamond(), null);
        level2[7][1] = new Cell(new Floor(), null, null);
        level2[7][2] = new Cell(new Floor(), new Diamond(), null);
        level2[7][4] = new Cell(new Floor(), null, null);
        level2[7][18] = new Cell(new Floor(), new Diamond(), null);
        level2[7][19] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[7][21] = new Cell(new Floor(), null, null);
        level2[8][1] = new Cell(new Floor(), null, new Rock(null));
        level2[8][4] = new Cell(new Floor(), null, null);
        level2[8][5] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[8][16] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[8][17] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[8][20] = new Cell(new Floor(), null, null);
        level2[8][21] = new Cell(check7, null, null);
        level2[9][1] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[9][2] = new Cell(new Floor(), new Diamond(), null);
        level2[9][4] = new Cell(new DoorWithKeyhole().gold, null, null);
        level2[9][16] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),new RedDiamond(1),null,new GoldKey(), new Energy(30)), null);
        level2[9][18] = new Cell(new Floor(), null, new Rock(null));
        level2[9][20] = new Cell(new Floor(), null, null);
        level2[9][21] = new Cell(new Floor(), null, null);
        level2[9][22] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[10][4] = new Cell(check3, null, null);
        level2[10][9] = new Cell(new Floor(), null, scorpion1);
        level2[10][10] = new Cell(new Floor(), null, scorpion1);
        level2[10][11] = new Cell(new Floor(), null, scorpion1);
        level2[10][12] = new Cell(new Floor(), null, scorpion1);
        level2[10][16] = new Cell(new Floor(), new Diamond(), null);
        level2[10][18] = new Cell(new Floor(), null, new Rock(null));
        level2[10][20] = new Cell(new Floor(), null, null);
        level2[10][21] = new Cell(new Floor(), null, null);
        level2[10][22] = new Cell(new Floor(), new Diamond(), null);
        level2[11][4] = new Cell(new Floor(), null, null);
        level2[11][9] = new Cell(new Floor(), null, scorpion1);
        level2[11][10] = new Cell(new Floor(), new Diamond(), null);
        level2[11][12] = new Cell(new Floor(), null, scorpion1);
        level2[11][21] = new Cell(new DiamondDoor(0,false), null, null);
        level2[12][1] = new Cell(new Wall(Wall.Type.RIGHT_LOWER_CORNER), null, snake4);
        level2[12][2] = new Cell(new Floor(), null, snake4);
        level2[12][3] = new Cell(new Floor(), null, snake4);
        level2[12][4] = new Cell(new Floor(), null, snake4);
        level2[12][9] = new Cell(new Floor(), null, scorpion1);
        level2[12][10] = new Cell(new Floor(), null, scorpion1);
        level2[12][11] = new Cell(new Floor(), null, scorpion1);
        level2[12][12] = new Cell(new Floor(), null, scorpion1);
        level2[12][20] = new Cell(new Floor(), null, new Rock(null));
        level2[12][21] = new Cell(new Floor(), new Diamond(), null);
        level2[12][22] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[13][1] = new Cell(new Floor(), null, new Rock(null));
        level2[13][2] = new Cell(new Floor(), null, new Rock(null));
        level2[13][3] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[13][4] = new Cell(new Floor(), null, null);
        level2[13][5] = new Cell(new Floor(), null, null);
        level2[13][6] = new Cell(new Floor(), null, null);
        level2[13][19] = new Cell(new Floor(), null, new Rock(null));
        level2[13][20] = new Cell(new Floor(), new Diamond(), null);
        level2[13][21] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[13][22] = new Cell(new Floor(), null, null);
        level2[14][1] = new Cell(new Floor(), null, new Rock(null));
        level2[14][2] = new Cell(new Floor(), new Diamond(), null);
        level2[14][3] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[14][4] = new Cell(new Floor(), null, null);
        level2[14][5] = new Cell(new Floor(), null, null);
        level2[14][6] = new Cell(new Floor(), null, null);
        level2[14][20] = new Cell(new Floor(), null, new Rock(null));
        level2[14][21] = new Cell(new Floor(), new Diamond(), null);
        level2[14][22] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[15][1] = new Cell(new Floor(), null, new Rock(null));
        level2[15][2] = new Cell(new Floor(), null, new Rock(null));
        level2[15][3] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[15][4] = new Cell(new Floor(), null, null);
        level2[15][5] = new Cell(new Floor(), null, null);
        level2[15][6] = new Cell(new Floor(), null, null);
        level2[15][21] = new Cell(new DoorWithKeyhole().gold, null, null);
        level2[16][1] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[16][2] = new Cell(new Floor(), null, null);
        level2[16][3] = new Cell(new Floor(), null, null);
        level2[16][4] = new Cell(new Floor(), null, null);
        level2[16][5] = new Cell(new Floor(), null, null);
        level2[16][15] = new Cell(new Floor(), new Diamond(), null);
        level2[16][20] = new Cell(new Floor(), null, null);
        level2[16][21] = new Cell(new Floor(), null, null);
        level2[17][1] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[17][2] = new Cell(new Floor(), null, null);
        level2[17][3] = new Cell(new Floor(), null, null);
        level2[17][4] = new Cell(new Floor(), null, null);
        level2[17][9] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[17][10] = new Cell(new Floor(), new Diamond(), null);
        level2[18][1] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[18][2] = new Cell(new Floor(), new Chest(null,null,new SilverKey(),null,new Energy(50)), null);
        level2[18][4] = new Cell(new Floor(), null, null);
        level2[18][5] = new Cell(pressMechanism1.pressPanel, null, null);
        level2[18][9] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[18][10] = new Cell(new Floor(), new Chest(null,new RedDiamond(1),null,null,new Energy(50)), null);
        level2[18][16] = new Cell(new Floor(), new Chest(null,null,new SilverKey(),null, new Energy(25)), null);
        level2[19][1] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[19][2] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[19][4] = new Cell(pressMechanism1.door, null, null);
        level2[19][9] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[19][10] = new Cell(new Floor(), new Diamond(), null);
        level2[19][13] = new Cell(new Floor(), null, null);
        level2[19][14] = new Cell(new Floor(), null, null);
        level2[20][1] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[20][2] = new Cell(new Floor(), null, new Rock(null));
        level2[20][4] = new Cell(new Floor(), null, null);
        level2[20][14] = new Cell(new Floor(), null, null);//doubleDoor.leftDoor
        level2[20][17] = new Cell(new Floor(), new Diamond(), null);
        level2[20][18] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[20][19] = new Cell(new Floor(), null, snake8);
        level2[20][20] = new Cell(new Floor(), null, snake9);
        level2[20][21] = new Cell(new Floor(), new Diamond(), null);
        level2[21][4] = new Cell(new Floor(), null, new Rock(null));
        level2[21][14] = new Cell(check8, null, null);
        level2[21][16] = new Cell(new Floor(), null, snake7);
        level2[21][17] = new Cell(new Floor(), null, new Rock(null));
        level2[21][19] = new Cell(new Floor(), null, snake8);
        level2[21][20] = new Cell(new Floor(), null, snake9);
        level2[21][21] = new Cell(new Floor(), new Diamond(), null);
        level2[22][4] = new Cell(new Floor(), null, null);
        level2[22][5] = new Cell(new Floor(), null, null);
        level2[22][14] = new Cell(new Floor(), null, null);
        level2[22][15] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[22][16] = new Cell(new Floor(), null, snake7);
        level2[22][17] = new Cell(new Floor(), null, null);
        level2[22][18] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[22][19] = new Cell(new Floor(), null, snake8);
        level2[22][20] = new Cell(new Floor(), null, snake9);
        level2[22][21] = new Cell(new Floor(), new Diamond(), null);
        level2[23][2] = new Cell(new Floor(), new Diamond(), null);
        level2[23][4] = new Cell(new Floor(), null, new Rock(null));
        level2[23][14] = new Cell(new Floor(), null, new Rock(null));
        level2[23][16] = new Cell(new Floor(), null, snake7);
        level2[23][17] = new Cell(new Floor(), new Diamond(), null);
        level2[23][19] = new Cell(new Floor(), null, snake8);
        level2[23][20] = new Cell(new Floor(), null, snake9);
        level2[23][21] = new Cell(new Floor(), new Diamond(), null);
        level2[24][4] = new Cell(new Floor(), null, null);
        level2[24][5] = new Cell(new Floor(), null, null);
        level2[24][7] = new Cell(new Floor(), null, new Rock(null));
        level2[24][8] = new Cell(new Floor(), null, new Rock(null));
        level2[24][9] = new Cell(new Floor(), new Diamond(), null);
        level2[24][10] = new Cell(new Floor(), null, new Rock(null));
        level2[24][14] = new Cell(new Floor(), null, null);
        level2[24][15] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[24][16] = new Cell(new Floor(), null, snake7);
        level2[24][17] = new Cell(new Floor(), null, null);
        level2[24][18] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[24][19] = new Cell(new Floor(), null, snake8);
        level2[24][20] = new Cell(new Floor(), null, snake9);
        level2[24][21] = new Cell(new Floor(), new Diamond(), null);
        level2[25][4] = new Cell(new Floor(), null, new Rock(null));
        level2[25][7] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[25][8] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[25][9] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[25][10] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[25][11] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[25][14] = new Cell(new Floor(), null, null);
        level2[25][16] = new Cell(new Floor(), null, snake7);
        level2[25][17] = new Cell(new Floor(), null, new Rock(null));
        level2[25][19] = new Cell(new Floor(), null, snake8);
        level2[25][20] = new Cell(new Floor(), null, snake9);
        level2[25][21] = new Cell(new Floor(), new Diamond(), null);
        level2[26][4] = new Cell(new Floor(), null, null);
        level2[26][5] = new Cell(new Floor(), null, null);
        level2[26][7] = new Cell(new Floor(), null, new Rock(null));
        level2[26][8] = new Cell(new Floor(), null, new Rock(null));
        level2[26][9] = new Cell(new Floor(), new Diamond(), null);
        level2[26][10] = new Cell(new Floor(), null, new Rock(null));
        level2[26][11] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[26][14] = new Cell(new Floor(), null, null);//doubleDoor.rightDoor
        level2[26][17] = new Cell(new Floor(), new Diamond(), null);
        level2[26][18] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[26][19] = new Cell(new Floor(), null, snake8);
        level2[26][20] = new Cell(new Floor(), null, snake9);
        level2[26][21] = new Cell(new Floor(), new Diamond(), null);
        level2[27][4] = new Cell(new Floor(), null, null);
        level2[27][7] = new Cell(new Floor(), null, null);
        level2[27][11] = new Cell(new Floor(), null, null);
        level2[27][14] = new Cell(check10, null, null);
        level2[27][20] = new Cell(new DoorWithKeyhole().silver, null, null);
        level2[28][2] = new Cell(new Floor(), null, null);
        level2[28][3] = new Cell(new Floor(), null, null);
        level2[28][4] = new Cell(new Floor(), null, null);
        level2[28][6] = new Cell(new Wall(Wall.Type.CEILING), null, snake5);
        level2[28][7] = new Cell(new Floor(), null, snake5);
        level2[28][8] = new Cell(new Floor(), null, snake5);
        level2[28][9] = new Cell(new Floor(), null, snake5);
        level2[28][10] = new Cell(new Floor(), null, snake5);
        level2[28][11] = new Cell(new Floor(), null, snake5);
        level2[28][14] = new Cell(new Floor(), null, new Rock(null));
        level2[28][19] = new Cell(new Floor(), null, null);
        level2[28][20] = new Cell(check9, null, null);
        level2[28][21] = new Cell(new Floor(), new Diamond(), null);
        level2[29][2] = new Cell(new Floor(), null, null);
        level2[29][6] = new Cell(new Wall(Wall.Type.CEILING), null, snake6);
        level2[29][7] = new Cell(new Floor(), null, snake6);
        level2[29][8] = new Cell(new Floor(), null, snake6);
        level2[29][9] = new Cell(new Floor(), null, snake6);
        level2[29][10] = new Cell(new Floor(), null, snake6);
        level2[29][11] = new Cell(new Floor(), null, snake6);
        level2[29][14] = new Cell(new Floor(), null, null);
        level2[29][15] = new Cell(new Floor(), null, null);
        level2[29][16] = new Cell(new Floor(), new Diamond(), null);
        level2[29][19] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[29][20] = new Cell(new Floor(), null, new Rock(null));
        level2[29][21] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[30][2] = new Cell(new DoorWithKeyhole().silver, null, null);
        level2[30][9] = new Cell(check5, null, null);
        level2[30][14] = new Cell(new Floor(), null, null);
        level2[30][15] = new Cell(new Floor(), null, new Rock(null));
        level2[30][16] = new Cell(new Floor(), new Diamond(), null);
        level2[30][19] = new Cell(new Floor(), null, fireTrap4);
        level2[30][20] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[30][21] = new Cell(new Floor(), new Diamond(), null);
        level2[31][2] = new Cell(check4, null, null);
        level2[31][4] = new Cell(new Floor(), null, fireTrap1);
        level2[31][6] = new Cell(new Floor(), new Diamond(), null);
        level2[31][9] = new Cell(new DoorWithKeyhole().silver, null, null);
        level2[31][14] = new Cell(new Floor(), null, null);
        level2[31][15] = new Cell(new Floor(), null, new Rock(null));
        level2[31][16] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[31][18] = new Cell(new Floor(), null, null);
        level2[31][19] = new Cell(new Floor(), null, fireTrap4);
        level2[31][20] = new Cell(new Floor(), null, null);
        level2[32][2] = new Cell(new Floor(), null, fireTrap2);
        level2[32][4] = new Cell(new Floor(), null, fireTrap1);
        level2[32][6] = new Cell(new Floor(), null, fireTrap3);
        level2[32][8] = new Cell(new Floor(), null, scorpion2);
        level2[32][9] = new Cell(new Floor(), null, scorpion2);
        level2[32][10] = new Cell(new Floor(), null, scorpion2);
        level2[32][11] = new Cell(new Floor(), null, scorpion2);
        level2[32][14] = new Cell(new Floor(), null, null);
        level2[32][15] = new Cell(new Floor(), null, new Rock(null));
        level2[32][16] = new Cell(new Floor(), new Tumbleweed(), null);
        level2[32][18] = new Cell(new Floor(), null, null);
        level2[32][19] = new Cell(new Floor(), null, fireTrap4);
        level2[32][20] = new Cell(new Floor(), null, null);
        level2[32][21] = new Cell(pressMechanism2.pressPanel, null, null);
        level2[33][2] = new Cell(new Floor(), null, fireTrap2);
        level2[33][3] = new Cell(new Floor(), null, null);
        level2[33][4] = new Cell(new Floor(), null, fireTrap1);
        level2[33][5] = new Cell(new Floor(), null, null);
        level2[33][6] = new Cell(new Floor(), null, fireTrap3);
        level2[33][7] = new Cell(new Floor(), null, null);
        level2[33][8] = new Cell(new Floor(), null, scorpion2);
        level2[33][9] = new Cell(new Floor(), new Chest(new PurpleDiamond(10),new RedDiamond(1), new SilverKey(),null,new Energy(50)), null);
        level2[33][11] = new Cell(new Floor(), null, scorpion2);
        level2[33][14] = new Cell(new Floor(), null, null);
        level2[33][15] = new Cell(new Floor(), null, new Rock(null));
        level2[33][16] = new Cell(new Floor(), new Diamond(), null);
        level2[33][19] = new Cell(new Floor(), null, fireTrap4);
        level2[33][20] = new Cell(pressMechanism2.door, null, null);
        level2[34][2] = new Cell(new Floor(), null, fireTrap2);
        level2[34][4] = new Cell(new Floor(), null, fireTrap1);
        level2[34][6] = new Cell(new Floor(), null, fireTrap3);
        level2[34][8] = new Cell(new Floor(), null, scorpion2);
        level2[34][9] = new Cell(new Floor(), null, scorpion2);
        level2[34][10] = new Cell(new Floor(), null, scorpion2);
        level2[34][11] = new Cell(new Floor(), null, scorpion2);
        level2[34][14] = new Cell(new Floor(), null, null);
        level2[34][15] = new Cell(new Floor(), null, null);
        level2[34][16] = new Cell(new Floor(), new Diamond(), null);
        level2[34][20] = new Cell(new Floor(), null, null);
        level2[35][2] = new Cell(new Floor(), null, fireTrap2);
        level2[35][4] = new Cell(new Floor(), new Diamond(), null);
        level2[35][6] = new Cell(new Floor(), null, fireTrap3);
        level2[35][14] = new Cell(new Floor(), null, null);
        level2[35][19] = new Cell(new Floor(), null, null);
        level2[35][20] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),new RedDiamond(1),null,null,new Energy(30)), null);
        level2[36][14] = new Cell(new Floor(), null, null);
        level2[36][19] = new Cell(new Floor(), null, snake10);
        level2[36][20] = new Cell(new Floor(), null, snake10);
        level2[37][12] = new Cell(new Floor(), null, null);
        level2[37][13] = new Cell(new Floor(), null, null);
        level2[37][14] = new Cell(new Floor(), null, null);
        level2[37][17] = new Cell(new Floor(), new Chest(new PurpleDiamond(5), new RedDiamond(1),null,null,null), null);
        level2[37][19] = new Cell(new Floor(), null, null);
        level2[37][20] = new Cell(new Floor(), new Diamond(), null);
        level2[38][12] = new Cell(new DiamondDoor(10,true), null, null);
        level2[39][12] = new Cell(new Floor(), null, null);

        level2[6][5] = new Cell(new SecretWall(Wall.Type.FLOOR), null, null);
        level2[6][6] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[6][7] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[7][7] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[8][7] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[9][7] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), new Chest(new PurpleDiamond(5), new RedDiamond(1), null,new GoldKey(),new Energy(25)), null);
        level2[6][11] = new Cell(new SecretWall(Wall.Type.CEILING), null, null);
        level2[7][11] = new Cell(new SecretWall(Wall.Type.LEFT_LOWER_BRICK), null, null);
        level2[8][11] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[8][10] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[8][9] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[9][9] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);

        level2[11][14] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[10][14] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[8][14] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[9][14] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[10][15] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);


        level2[12][16] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[12][17] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[12][18] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), new Chest(new PurpleDiamond(10),new RedDiamond(2), null,null,new Energy(50)), null);
        level2[13][14] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[13][15] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[13][16] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[14][14] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[14][16] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), new Diamond(), null);
        level2[14][17] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[15][14] = new Cell(new SecretWall( Wall.Type.RIGHT_SIDE), null, null);



        level2[18][17] = new Cell(new SecretWall( Wall.Type.FLOOR), null, null);
        level2[18][18] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[18][19] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[19][19] = new Cell(new SecretWall(Wall.Type.RIGHT_SIDE), null, null);
        level2[19][7] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[19][8] = new Cell(new SecretWall( Wall.Type.CEILING), null, null);
        level2[20][7] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[21][7] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[21][8] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[21][9] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level2[22][9] = new Cell(new SecretWall( new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level2[23][9] = new Cell(new SecretWall(Wall.Type.RIGHT_SIDE), null, null);
        level2[37][15] = new Cell(new SecretWall( Wall.Type.FLOOR), null, null);
        level2[37][16] = new Cell(new SecretWall( new ImageIcon("mapImages/secretWall.png").getImage()), null, null);



        level2[4][3] = new Cell(new BreakableWall(4,3), null, null);
        level2[5][3] = new Cell(new BreakableWall(5,3), null, null);
        level2[6][3] = new Cell(new BreakableWall(6,3), null, null);
        level2[7][3] = new Cell(new BreakableWall(7,3), null, null);
        level2[8][2] = new Cell(new BreakableWall(8,2), null, null);
        level2[8][3] = new Cell(new BreakableWall(8,3), null, null);
        level2[8][18] = new Cell(new BreakableWall(8,18), null, null);
        level2[8][19] = new Cell(new BreakableWall(8,19), null, null);
        level2[9][19] = new Cell(new BreakableWall(9,19), null, null);
        level2[10][19] = new Cell(new BreakableWall(10,19), null, null);
        level2[24][2] = new Cell(new BreakableWall(24,2), null, null);
        level2[25][2] = new Cell(new BreakableWall(25,2), null, null);
        level2[25][3] = new Cell(new BreakableWall(25,3), null, null);
        level2[16][13] = new Cell(new BreakableWall(16,13), null, null);
        level2[16][14] = new Cell(new BreakableWall(16,14), null, null);
        level2[16][16] = new Cell(new BreakableWall(16,16), null, null);
        level2[16][17] = new Cell(new BreakableWall(16,17), null, null);
        level2[16][18] = new Cell(new BreakableWall(16,18), null, null);
        level2[16][19] = new Cell(new BreakableWall(16,19), null, null);
        level2[17][13] = new Cell(new BreakableWall(17,13), null, null);
        level2[18][13] = new Cell(new BreakableWall(18,13), null, null);
        level2[27][13] = new Cell(new BreakableWall(27,13), null, null);
        level2[28][13] = new Cell(new BreakableWall(28,13), null, null);
        level2[29][13] = new Cell(new BreakableWall(29,13), null, null);
        level2[30][13] = new Cell(new BreakableWall(30,13), null, null);
        level2[31][13] = new Cell(new BreakableWall(31,13), null, null);
        level2[32][13] = new Cell(new BreakableWall(32,13), null, null);
        level2[33][13] = new Cell(new BreakableWall(33,13), null, null);
        level2[34][13] = new Cell(new BreakableWall(34,13), null, null);

        Util.setWalls(level2);

        for (int i = 0; i < level2.length; i++) {
            for (int j = 0; j < level2[i].length; j++) {
                if(level2[i][j]==null){
                    level2[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5,check6,check7,check8,check9,check10};

        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level2);
        }

        this.level2 = new Level(level2,2,4,2,14,500,2);
        this.level2.setCheckpoints(checkpoints);
    }


    private void initLevel3(){
        Snake snake1 = new Snake(210,70,40,true, playPanel);
        Snake snake2 = new Snake(350,70,180,true, playPanel);
        Snake snake3 = new Snake(70,210,20,false, playPanel);
        Snake snake4 = new Snake(350,70,70,true, playPanel);
        Snake snake5 = new Snake(70,910,10,false, playPanel);//840

        Scorpion scorpion1 = new Scorpion(350,490,false,null,null);
        Scorpion scorpion2 = new Scorpion(140,140,true,null,null);

        FireTrap fireTrap1 = new FireTrap(0,false);
        FireTrap fireTrap2 = new FireTrap(2,false);
        FireTrap fireTrap3 = new FireTrap(2,true);
        FireTrap fireTrap4 = new FireTrap(1,false);
        FireTrap fireTrap5 = new FireTrap(3,true);
        FireTrap fireTrap6 = new FireTrap(3,true);
        FireTrap fireTrap7 = new FireTrap(3,true);

        Snake[] snakes = {snake2};
        DoubleDoor doubleDoor = new DoubleDoor(snakes,false,6,7);
        PressMechanism pressMechanism1 = new PressMechanism(20,15);
        PressMechanism pressMechanism2 = new PressMechanism(30,27);

        Checkpoint check1 = new Checkpoint(4,16,9,28,3,25,2,6);
        Checkpoint check2 = new Checkpoint(8,10,14,23,9,15,4,4);
        int[] additionalSegmentCheck3 = {1,5,3,20};
        Checkpoint check3 = new Checkpoint(4,11,6,15, additionalSegmentCheck3, 6,15,4,4);
        Checkpoint check4 = new Checkpoint(6,3,16,8,8,7,4,4);
        int[] additionalSegmentCheck5 = {17,5,25,8};
        Checkpoint check5 = new Checkpoint(18,1,34,4,additionalSegmentCheck5,17,7,4,4);
        int[] additionalSegmentCheck6 = {26,7,32,10};
        Checkpoint check6 = new Checkpoint(28,11,31,13,additionalSegmentCheck6,27,7,4,4);
        Checkpoint check7 = new Checkpoint(16,10,27,16,27,13,4,4);
        int[] additionalSegmentCheck8 = {17,17,22,24};
        Checkpoint check8 = new Checkpoint(13,13,23,28,additionalSegmentCheck8,17,17,4,4);
        Checkpoint check9 = new Checkpoint(24,21,27,7,24,26,4,4);
        int[] additionalSegmentCheck10 = {24,18,27,19};
        Checkpoint check10 = new Checkpoint(28,15,34,18,additionalSegmentCheck10,30,14,4,4);

        //Floor + (Harmless/Trap)
        Cell[][] level3 = new Cell[36][30];

        level3[0][25] = new Cell(new Floor(),null,null);
        level3[1][5] = new Cell(new Floor(),null,null);
        level3[1][6] = new Cell(new Floor(),null,new Rock(null));
        level3[1][7] = new Cell(new Floor(),new Diamond(),null);
        level3[1][8] = new Cell(new Floor(),new Diamond(),null);
        level3[1][19] = new Cell(new Floor(),null,null);
        level3[1][20] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), null, null, null, new Energy(20)),null);
        level3[1][25] = new Cell(new BlockedDoor(),null,null);
        level3[2][5] = new Cell(new Floor(),null,null);
        level3[2][6] = new Cell(new Floor(),null,null);
        level3[2][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[2][8] = new Cell(new Floor(),null,null);
        level3[2][19] = new Cell(new Floor(),null,null);
        level3[2][20] = new Cell(new Floor(),null,null);
        level3[2][25] = new Cell(new Floor(),null,null);
        level3[3][5] = new Cell(new Floor(),null,null);
        level3[3][6] = new Cell(new Floor(),null,null);
        level3[3][7] = new Cell(new Floor(),null,null);
        level3[3][8] = new Cell(new Floor(),null,null);
        level3[3][9] = new Cell(new Floor(),new Diamond(),null);
        level3[3][10] = new Cell(new Floor(),null,new Rock(null));
        level3[3][11] = new Cell(new Floor(),new Diamond(),null);
        level3[3][12] = new Cell(new Floor(),null,new Rock(null));
        level3[3][13] = new Cell(new Floor(),new Diamond(),null);
        level3[3][14] = new Cell(new Floor(),null,new Rock(null));
        level3[3][15] = new Cell(new Floor(),new Diamond(),null);
        level3[3][19] = new Cell(new Floor(),null,null);
        level3[3][20] = new Cell(new Floor(),new Chest(new PurpleDiamond(5), new RedDiamond(1), null, null, null),null);
        level3[3][25] = new Cell(check1,null,null);
        level3[4][7] = new Cell(new Floor(),null,null);
        level3[4][25] = new Cell(new Floor(),null,new Rock(null));
        level3[5][7] = new Cell(new Floor(),null,null);
        level3[5][11] = new Cell(new Floor(),null,null);
        level3[5][12] = new Cell(new Floor(),null,null);
        level3[5][13] = new Cell(new Floor(),null,null);
        level3[5][14] = new Cell(new Floor(),null,null);
        level3[5][15] = new Cell(new Floor(),null,null);
        level3[5][18] = new Cell(new Floor(),null,fireTrap1);//head
        level3[5][22] = new Cell(new Floor(),new Diamond(),null);
        level3[5][23] = new Cell(new Floor(),new Diamond(),null);
        level3[5][24] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[5][25] = new Cell(new Floor(),null,null);
        level3[5][26] = new Cell(new Floor(),null,null);
        level3[6][7] = new Cell(doubleDoor.leftDoor,null,null);
        level3[6][11] = new Cell(new Floor(),null,null);
        level3[6][12] = new Cell(new Floor(),new Chest(null, null, null, null, new Energy(30)),null);
        level3[6][14] = new Cell(new Floor(),null,null);
        level3[6][15] = new Cell(check3,null,null);
        level3[6][17] = new Cell(new Floor(),null,null);
        level3[6][18] = new Cell(new Floor(),null,fireTrap1);
        level3[6][19] = new Cell(new Floor(),null,null);
        level3[6][20] = new Cell(new Floor(),null,null);
        level3[6][22] = new Cell(new Floor(),new Diamond(),null);
        level3[6][23] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[6][24] = new Cell(new Floor(),null,null);
        level3[7][3] = new Cell(new Floor(),null,null);
        level3[7][4] = new Cell(new Floor(),null,null);
        level3[7][5] = new Cell(new Floor(),null,null);
        level3[7][6] = new Cell(new Floor(),null,snake2);
        level3[7][7] = new Cell(new Floor(),null,null);
        level3[7][14] = new Cell(new Floor(),null,null);
        level3[7][15] = new Cell(new Floor(),null,null);
        level3[7][16] = new Cell(new Floor(),null,null);
        level3[7][17] = new Cell(new Floor(),null,null);
        level3[7][18] = new Cell(new Floor(),null,fireTrap1);
        level3[7][19] = new Cell(new Floor(),null,null);
        level3[7][20] = new Cell(new Floor(),null,null);
        level3[7][21] = new Cell(new Floor(),null,new Rock(null));
        level3[7][22] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[7][23] = new Cell(new Floor(),null,null);
        level3[7][24] = new Cell(new Floor(),null,null);
        level3[7][25] = new Cell(new Floor(),null,null);
        level3[7][26] = new Cell(new Floor(),new Diamond(),null);
        level3[7][27] = new Cell(new Floor(),new Diamond(),null);
        level3[8][3] = new Cell(new Floor(),null,null);
        level3[8][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[8][5] = new Cell(new Floor(),null,null);
        level3[8][6] = new Cell(new Floor(),null,snake2);
        level3[8][7] = new Cell(check4,null,null);
        level3[8][10] = new Cell(new Floor(),null,new Rock(null));
        level3[8][11] = new Cell(new Floor(),new Diamond(),null);
        level3[8][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[8][15] = new Cell(new Floor(),null,null);
        level3[8][18] = new Cell(new Floor(),null,fireTrap1);
        level3[8][26] = new Cell(new Floor(),null,null);//there was diamond
        level3[8][27] = new Cell(new Floor(),new Diamond(),null);
        level3[9][3] = new Cell(new Floor(),null,new Rock(null));
        level3[9][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[9][5] = new Cell(new Floor(),null,null);
        level3[9][6] = new Cell(new Floor(),null,snake2);
        level3[9][7] = new Cell(new Floor(),null,null);
        level3[9][10] = new Cell(new Floor(),null,new Rock(null));
        level3[9][11] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[9][12] = new Cell(new Floor(),new Diamond(),null);
        level3[9][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[9][15] = new Cell(check2,null,null);
        level3[9][18] = new Cell(new Floor(),new Diamond(),null);
        level3[9][27] = new Cell(new Floor(),null,null);
        level3[9][28] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), null, null, null, null),null);
        level3[10][3] = new Cell(new Floor(),null,null);
        level3[10][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[10][5] = new Cell(new Floor(),null,null);
        level3[10][6] = new Cell(new Floor(),null,snake2);
        level3[10][7] = new Cell(new Floor(),null,null);
        level3[10][10] = new Cell(new Floor(),null,new Rock(null));
        level3[10][11] = new Cell(new Floor(),new Diamond(),null);
        level3[10][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[10][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[10][15] = new Cell(new Floor(),null,null);
        level3[10][16] = new Cell(new Floor(),null,null);
        level3[11][3] = new Cell(new Floor(),null,null);
        level3[11][4] = new Cell(new Floor(),null,null);
        level3[11][5] = new Cell(new Floor(),null,null);
        level3[11][6] = new Cell(new Floor(),null,snake2);
        level3[11][7] = new Cell(new Floor(),null,null);
        level3[11][10] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[11][11] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[11][12] = new Cell(new Floor(),new Diamond(),null);
        level3[11][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[11][14] = new Cell(new Floor(),null,new Rock(null));
        level3[11][15] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[11][19] = new Cell(new Floor(), null, fireTrap2);//head
        level3[12][7] = new Cell(doubleDoor.rightDoor,null,null);
        level3[12][10] = new Cell(new Floor(),null,new Rock(null));
        level3[12][11] = new Cell(new Floor(),new Diamond(),null);
        level3[12][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[12][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[12][15] = new Cell(new Floor(),null,null);
        level3[12][18] = new Cell(new Floor(),null,null);
        level3[12][19] = new Cell(new Floor(),null,fireTrap2);
        level3[12][20] = new Cell(new Floor(),null,snake1);
        level3[12][21] = new Cell(new Floor(),null,fireTrap3);
        level3[12][22] = new Cell(new Floor(),null,null);
        level3[12][23] = new Cell(new Floor(), new Diamond(), null);
        level3[13][7] = new Cell(new Floor(),null,null);
        level3[13][10] = new Cell(new Floor(),null,new Rock(null));
        level3[13][11] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[13][12] = new Cell(new Floor(),new Diamond(),null);
        level3[13][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[13][15] = new Cell(new Floor(),null,null);
        level3[13][16] = new Cell(new Floor(),null,null);
        level3[13][18] = new Cell(new Floor(),null,null);
        level3[13][19] = new Cell(new Floor(),null,fireTrap2);
        level3[13][20] = new Cell(new Floor(),null,snake1);
        level3[13][21] = new Cell(new Floor(),null,fireTrap3);
        level3[13][22] = new Cell(new Floor(),null,null);
        level3[13][23] = new Cell(new Floor(),new Chest(new PurpleDiamond(5), null, new SilverKey(), null, new Energy(30)),null);
        level3[13][25] = new Cell(new Floor(),null,null);
        level3[13][27] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), null, null, null, null),null);
        level3[14][6] = new Cell(new Floor(),null,new Rock(null));
        level3[14][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[14][10] = new Cell(new Floor(),null,new Rock(null));
        level3[14][11] = new Cell(new Floor(),new Diamond(),null);
        level3[14][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[14][16] = new Cell(new Floor(),null,null);
        level3[14][17] = new Cell(new Floor(),null,null);
        level3[14][18] = new Cell(new Floor(),null,null);
        level3[14][19] = new Cell(new Floor(),null,fireTrap2);
        level3[14][20] = new Cell(new Floor(),null,snake1);
        level3[14][21] = new Cell(new Floor(),null,fireTrap3);
        level3[14][22] = new Cell(new Floor(),null,null);
        level3[14][23] = new Cell(new Floor(), new Diamond(),null);
        level3[14][25] = new Cell(new Floor(),null,new Rock(null));
        level3[14][27] = new Cell(new Floor(),null,null);
        level3[15][7] = new Cell(new Floor(),null,null);
        level3[15][8] = new Cell(new Floor(),null,null);
        level3[15][21] = new Cell(new Floor(),null,fireTrap3);//head
        level3[15][25] = new Cell(new Floor(),null,new Rock(null));
        level3[15][27] = new Cell(new Floor(),null,null);
        level3[16][7] = new Cell(new Floor(),null,null);
        level3[16][10] = new Cell(new Floor(),new Diamond(),null);
        level3[16][25] = new Cell(new Floor(),null,new Rock(null));
        level3[16][27] = new Cell(new Floor(),null,null);
        level3[17][7] = new Cell(check5,null,null);
        level3[17][14] = new Cell(new Floor(),null,null);
        level3[17][15] = new Cell(new Floor(),null,null);
        level3[17][16] = new Cell(new Floor(),null,null);
        level3[17][17] = new Cell(check8,null,null);
        level3[17][20] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), null, null, null, new Energy(30)),null);
        level3[17][26] = new Cell(new Floor(),null,null);
        level3[17][27] = new Cell(new Floor(),null,null);
        level3[18][1] = new Cell(new Floor(),null,null);
        level3[18][2] = new Cell(new Floor(),null,scorpion1);
        level3[18][3] = new Cell(new Floor(),null,scorpion1);
        level3[18][4] = new Cell(new Floor(),null,scorpion1);
        level3[18][5] = new Cell(new Floor(),null,scorpion1);
        level3[18][6] = new Cell(new Floor(),null,scorpion1);
        level3[18][7] = new Cell(new Floor(),null,scorpion1);
        level3[18][8] = new Cell(new Floor(),null,scorpion1);
        level3[18][14] = new Cell(new Floor(),null,null);
        level3[18][16] = new Cell(new Wall(),null,snake5);
        level3[18][17] = new Cell(new Floor(),null,snake5);
        level3[18][18] = new Cell(new Floor(),null,snake5);
        level3[18][19] = new Cell(new Floor(),null,snake5);
        level3[18][20] = new Cell(new Floor(),null,snake5);
        level3[18][21] = new Cell(new Floor(),null,snake5);
        level3[18][22] = new Cell(new Floor(),null,snake5);
        level3[18][23] = new Cell(new Floor(),null,snake5);
        level3[18][24] = new Cell(new Floor(),null,snake5);
        level3[18][25] = new Cell(new Floor(),null,snake5);
        level3[18][26] = new Cell(new Floor(),null,snake5);
        level3[18][27] = new Cell(new Floor(),null,snake5);
        level3[18][28] = new Cell(new Floor(),null,snake5);
        level3[19][1] = new Cell(new Floor(),null,null);
        level3[19][2] = new Cell(new Floor(),null,scorpion1);
        level3[19][5] = new Cell(new Floor(),new Diamond(),null);
        level3[19][8] = new Cell(new Floor(),null,scorpion1);
        level3[19][14] = new Cell(pressMechanism1.door,null,null);
        level3[19][18] = new Cell(new Floor(),null,fireTrap5);
        level3[19][20] = new Cell(new Floor(),null,fireTrap6);
        level3[19][22] = new Cell(new Floor(),null,fireTrap7);
        level3[19][24] = new Cell(new Floor(),null,null);
        level3[19][27] = new Cell(new Floor(),null,null);
        level3[20][1] = new Cell(new Floor(),null,null);
        level3[20][2] = new Cell(new Floor(),null,scorpion1);
        level3[20][5] = new Cell(new Floor(),new Diamond(),null);
        level3[20][8] = new Cell(new Floor(),null,scorpion1);
        level3[20][13] = new Cell(new Floor(),null,null);
        level3[20][14] = new Cell(new Floor(),null,null);
        level3[20][15] = new Cell(pressMechanism1.pressPanel,null,null);
        level3[20][18] = new Cell(new Floor(),null,fireTrap5);
        level3[20][20] = new Cell(new Floor(),null,fireTrap6);
        level3[20][22] = new Cell(new Floor(),null,fireTrap7);
        level3[20][26] = new Cell(new Floor(),null,new Rock(null));
        level3[20][27] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[21][1] = new Cell(new Floor(),null,null);
        level3[21][2] = new Cell(new Floor(),null,scorpion1);
        level3[21][5] = new Cell(new Floor(),new Diamond(),null);
        level3[21][8] = new Cell(new Floor(),null,scorpion1);
        level3[21][11] = new Cell(new Floor(),null,null);
        level3[21][12] = new Cell(new Floor(),null,null);
        level3[21][13] = new Cell(new Floor(),null,null);
        level3[21][14] = new Cell(new Floor(),null,null);
        level3[21][15] = new Cell(new Floor(),null,null);
        level3[21][18] = new Cell(new Floor(),null,fireTrap5);
        level3[21][19] = new Cell(new Floor(),null,null);
        level3[21][20] = new Cell(new Floor(),null,fireTrap6);
        level3[21][21] = new Cell(new Floor(),null,null);
        level3[21][22] = new Cell(new Floor(),null,fireTrap7);
        level3[21][26] = new Cell(new Floor(),null,new Rock(null));
        level3[21][27] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[21][28] = new Cell(new Floor(),new Diamond(),null);
        level3[22][1] = new Cell(new Floor(),null,null);
        level3[22][2] = new Cell(new Floor(),null,scorpion1);
        level3[22][3] = new Cell(new Floor(),null,scorpion1);
        level3[22][4] = new Cell(new Floor(),null,scorpion1);
        level3[22][5] = new Cell(new Floor(),null,null);
        level3[22][6] = new Cell(new Floor(),null,scorpion1);
        level3[22][7] = new Cell(new Floor(),null,scorpion1);
        level3[22][8] = new Cell(new Floor(),null,scorpion1);
        level3[22][11] = new Cell(new Floor(),null,null);
        level3[22][12] = new Cell(new Floor(),null,null);
        level3[22][13] = new Cell(new Floor(),null,null);
        level3[22][14] = new Cell(new Floor(),null,null);
        level3[22][15] = new Cell(new Floor(),null,null);
        level3[22][16] = new Cell(new Floor(),null,null);
        level3[22][18] = new Cell(new Floor(),null,fireTrap5);//head
        level3[22][19] = new Cell(new Floor(),new Diamond(),null);
        level3[22][20] = new Cell(new Floor(),null,fireTrap6);//head
        level3[22][21] = new Cell(new Floor(),new Diamond(),null);
        level3[22][22] = new Cell(new Floor(),null,fireTrap7);//head
        level3[22][27] = new Cell(new Floor(),null,null);
        level3[23][8] = new Cell(new Floor(),null,null);
        level3[23][11] = new Cell(new Floor(),null,null);
        level3[23][12] = new Cell(new Floor(),null,null);
        level3[23][13] = new Cell(new Floor(),null,new Rock(null));
        level3[23][15] = new Cell(new Floor(),null,null);
        level3[23][16] = new Cell(new Floor(),null,null);
        level3[23][27] = new Cell(new Floor(),null,null);
        level3[23][28] = new Cell(new Floor(),null,null);
        level3[24][8] = new Cell(new DoorWithKeyhole().silver,null,null);
        level3[24][11] = new Cell(new Floor(),null,null);
        level3[24][12] = new Cell(new Floor(),null,new Rock(null));
        level3[24][13] = new Cell(new Floor(),new Diamond(),null);
        level3[24][14] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[24][15] = new Cell(new Floor(),null,null);
        level3[24][16] = new Cell(new Floor(),null,null);
        level3[24][18] = new Cell(new Floor(),new Chest(null, new RedDiamond(1), null, null, new Energy(20)),null);
        level3[24][21] = new Cell(new Floor(),null,null);
        level3[24][22] = new Cell(new Floor(),null,null);
        level3[24][26] = new Cell(check9,null,null);
        level3[24][27] = new Cell(new Floor(),null,null);
        level3[25][8] = new Cell(new Floor(),null,null);
        level3[25][11] = new Cell(new Floor(),null,null);
        level3[25][12] = new Cell(new Floor(),null,null);
        level3[25][13] = new Cell(new Floor(),new Diamond(),null);
        level3[25][14] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[25][15] = new Cell(new Floor(),new Diamond(),null);
        level3[25][21] = new Cell(new Floor(),null,new Rock(null));
        level3[25][22] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[25][26] = new Cell(new Floor(),null,null);
        level3[26][7] = new Cell(new Floor(),null,null);
        level3[26][8] = new Cell(new Floor(),null,null);
        level3[26][9] = new Cell(new Floor(),null,fireTrap4);//head
        level3[26][11] = new Cell(new Floor(),null,null);
        level3[26][12] = new Cell(new Floor(),null,null);
        level3[26][13] = new Cell(new Floor(),null,null);
        level3[26][14] = new Cell(new Floor(),null,new Rock(null));
        level3[26][21] = new Cell(new Floor(),null,null);
        level3[26][22] = new Cell(new Floor(),null,null);
        level3[26][23] = new Cell(new Floor(),new Diamond(),null);
        level3[26][26] = new Cell(new Floor(),null,null);
        level3[27][7] = new Cell(check6,null,null);
        level3[27][9] = new Cell(new Floor(),null,fireTrap4);
        level3[27][12] = new Cell(new Floor(),null,null);
        level3[27][13] = new Cell(check7,null,null);
        level3[27][21] = new Cell(new Floor(),null,null);
        level3[27][22] = new Cell(new Floor(),null,null);
        level3[27][23] = new Cell(new Floor(),null,null);
        level3[27][24] = new Cell(new Floor(),new Diamond(),null);
        level3[27][26] = new Cell(new Floor(),new Diamond(),null);
        level3[28][7] = new Cell(new Floor(),null,null);
        level3[28][8] = new Cell(new Floor(),null,snake4);
        level3[28][9] = new Cell(new Floor(),null,fireTrap4);
        level3[28][10] = new Cell(new Floor(),null,null);
        level3[28][13] = new Cell(new Floor(),null,null);
        level3[28][15] = new Cell(new Floor(),null,null);
        level3[28][16] = new Cell(new Floor(),null,null);
        level3[28][17] = new Cell(new Floor(),null,scorpion2);
        level3[28][18] = new Cell(new Floor(),null,scorpion2);
        level3[28][22] = new Cell(new Floor(),null,null);
        level3[28][23] = new Cell(new Floor(),null,null);
        level3[28][24] = new Cell(new Floor(),null,null);
        level3[28][25] = new Cell(new Floor(),null,null);
        level3[28][26] = new Cell(new Floor(),new Diamond(),null);
        level3[28][27] = new Cell(new Floor(),new Diamond(),null);
        level3[29][7] = new Cell(new Floor(),null,null);
        level3[29][8] = new Cell(new Floor(),null,snake4);
        level3[29][9] = new Cell(new Floor(),null,fireTrap4);
        level3[29][10] = new Cell(new Floor(),new Diamond(),null);
        level3[29][13] = new Cell(new Floor(),null,null);
        level3[29][15] = new Cell(new Floor(),null,null);
        level3[29][17] = new Cell(new Floor(),null,scorpion2);
        level3[29][18] = new Cell(new Floor(),null,scorpion2);
        level3[29][23] = new Cell(new Floor(),null,null);
        level3[29][24] = new Cell(new Floor(),null,null);
        level3[29][25] = new Cell(new Floor(),null,null);
        level3[29][26] = new Cell(new Floor(),null,null);
        level3[29][27] = new Cell(new Floor(),new Diamond(),null);
        level3[30][7] = new Cell(new Floor(),null,null);
        level3[30][8] = new Cell(new Floor(),null,snake4);
        level3[30][9] = new Cell(new Floor(),new Diamond(),null);
        level3[30][13] = new Cell(new Floor(),null,null);
        level3[30][14] = new Cell(check10,null,null);
        level3[30][15] = new Cell(new Floor(),null,null);
        level3[30][18] = new Cell(new Floor(),null,null);
        level3[30][24] = new Cell(new Floor(),null,null);
        level3[30][25] = new Cell(new Floor(),null,null);
        level3[30][26] = new Cell(new Floor(),null,null);
        level3[30][27] = new Cell(pressMechanism2.pressPanel,null,null);
        level3[31][7] = new Cell(new Floor(),null,null);
        level3[31][8] = new Cell(new Floor(),null,snake4);
        level3[31][9] = new Cell(new Floor(),null,new Rock(null));
        level3[31][10] = new Cell(new Floor(),new Tumbleweed(),null);
        level3[31][11] = new Cell(new Floor(),null,null);
        level3[31][12] = new Cell(new Floor(),null,null);
        level3[31][13] = new Cell(new Floor(),null,null);
        level3[31][18] = new Cell(new Floor(),null,null);
        level3[31][25] = new Cell(new Floor(),null,null);
        level3[31][26] = new Cell(new Floor(),null,null);
        level3[31][27] = new Cell(new Floor(),null,null);
        level3[32][1] = new Cell(new Floor(),null,null);
        level3[32][2] = new Cell(new Floor(),null,null);
        level3[32][3] = new Cell(new Floor(),null,null);
        level3[32][7] = new Cell(new Floor(),null,null);
        level3[32][8] = new Cell(new Floor(),null,snake4);
        level3[32][9] = new Cell(new Floor(),null,null);
        level3[32][10] = new Cell(new Floor(),null,null);
        level3[32][18] = new Cell(new Floor(),null,null);
        level3[32][27] = new Cell(pressMechanism2.door,null,null);
        level3[33][1] = new Cell(new Floor(),null,snake3);
        level3[33][2] = new Cell(new Floor(),null,snake3);
        level3[33][3] = new Cell(new Floor(),null,snake3);
        level3[33][18] = new Cell(new DoorWithKeyhole().gold,null,null);
        level3[33][22] = new Cell(new Floor(),null,null);
        level3[33][23] = new Cell(new Floor(),new Chest(null, new RedDiamond(1), null, new GoldKey(), null),null);
        level3[33][27] = new Cell(new Floor(),null,null);
        level3[34][1] = new Cell(new Floor(),null,null);
        level3[34][2] = new Cell(new Floor(),new Chest(new PurpleDiamond(5), new RedDiamond(2), null, null, new Energy(20)),null);
        level3[34][18] = new Cell(new DiamondDoor(45,true),null,null);
        level3[34][22] = new Cell(new Floor(),null,null);
        level3[34][23] = new Cell(new Floor(),null,null);
        level3[34][26] = new Cell(new Floor(),null,null);
        level3[34][27] = new Cell(new Floor(),null,null);
        level3[35][18] = new Cell(new Floor(),null,null);

        //SecretWalls
        level3[1][15] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[1][16] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[1][17] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[2][17] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[2][18] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        level3[16][11] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[16][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[17][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[17][13] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);

        level3[23][2] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[24][2] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[24][3] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[24][4] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[25][4] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[26][3] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[26][4] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[27][1] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[27][2] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[27][3] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[28][1] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[29][1] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[29][2] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[30][2] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[31][2] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        level3[25][18] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[25][19] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[26][19] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[27][18] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level3[27][19] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);

        //BreakableWalls
        level3[2][15] = new Cell(new BreakableWall(2,15),null,null);
        level3[4][11] = new Cell(new BreakableWall(4,11),null,null);
        level3[4][12] = new Cell(new BreakableWall(4,12),null,null);
        level3[4][13] = new Cell(new BreakableWall(4,13),null,null);
        level3[4][14] = new Cell(new BreakableWall(4,14),null,null);
        level3[4][15] = new Cell(new BreakableWall(4,15),null,null);
        level3[13][26] = new Cell(new BreakableWall(13,26),null,null);
        level3[14][26] = new Cell(new BreakableWall(14,26),null,null);
        level3[15][26] = new Cell(new BreakableWall(15,26),null,null);
        level3[16][26] = new Cell(new BreakableWall(16,26),null,null);
        level3[34][24] = new Cell(new BreakableWall(34,24),null,null);
        level3[34][25] = new Cell(new BreakableWall(34,25),null,null);


        Util.setWalls(level3);
        for (int i = 0; i < level3.length; i++) {
            for (int j = 0; j < level3[i].length; j++) {
                if(level3[i][j]==null){
                    level3[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level3);
        }

        this.level3 = new Level(level3,2,5,2,25, 300,3);
        this.level3.setCheckpoints(checkpoints);
    }

    private void initLevel4(){
        Snake snake1 = new Snake(140,70,40,true, playPanel);
        Snake snake2 = new Snake(350,70,180,true, playPanel);
        Snake snake3 = new Snake(70,140,20,false, playPanel);
        Snake snake4 = new Snake(70,140,70,false, playPanel);
        Snake snake5 = new Snake(70,140,10,false, playPanel);

        Scorpion scorpion = new Scorpion(210,210,false,null,null);

        FireTrap fireTrap1 = new FireTrap(0,false);
        FireTrap fireTrap2 = new FireTrap(3,false);
        FireTrap fireTrap3 = new FireTrap(2,false);

        Snake[] snakes = {snake3,snake4};
        DoubleDoor doubleDoor = new DoubleDoor(snakes,true,20,13);
        PressMechanism pressMechanism = new PressMechanism(5,20);
        PressMechanism pressMechanism2 = new PressMechanism(12,19);

        int[] additionalSegmentCheck1 = {12,16,14,19};
        Checkpoint check1 = new Checkpoint(3,8,11,21,additionalSegmentCheck1,4,16,4,4);
        int[] additionalSegmentCheck2 = {12,8,14,8};
        Checkpoint check2 = new Checkpoint(6,1,18,7,additionalSegmentCheck2,8,7,4,4);
        Checkpoint check3 = new Checkpoint(18,3,24,13,18,4,4,4);
        int[] additionalSegmentCheck4 = {9,16,10,16};
        Checkpoint check4 = new Checkpoint(11,11,18,19,additionalSegmentCheck4,19,13,4,4);
        int[] additionalSegmentCheck5 = {35,4,38,14};
        Checkpoint check5 = new Checkpoint(25,4,34,7,additionalSegmentCheck5,25,5,4,4);

        //Floor + (Harmless/Trap)
        Cell[][] level1 = new Cell[40][22];
        level1[0][16] = new Cell(new Floor(),null,null);
        level1[1][16] = new Cell(new BlockedDoor(),null,null);
        level1[2][16] = new Cell(new Floor(),null,null);
        level1[3][16] = new Cell(new Floor(),null,null);
        level1[3][18] = new Cell(new Floor(),null,null);
        level1[3][19] = new Cell(new Floor(),new Diamond(),null);
        level1[4][16] = new Cell(check1,null,null);
        level1[4][18] = new Cell(new Floor(),null,null);
        level1[4][19] = new Cell(new Floor(),null,new Rock(null));
//        level1[5][16] = new Cell(new Floor(),null,null);//only for test
        level1[5][16] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                new SilverKey(), new GoldKey(), new Energy(2)),null);//only for test
        level1[5][17] = new Cell(new Floor(),null,null);
        level1[5][18] = new Cell(new Floor(),null,null);
        level1[5][19] = new Cell(new Floor(),null,null);
        level1[5][20] = new Cell(pressMechanism.pressPanel,null,null);
        level1[6][4] = new Cell(new Floor(),null,snake2);
        level1[6][10] = new Cell(new Floor(),new Diamond(),null);
        level1[6][11] = new Cell(new Floor(),new Diamond(),null);
        level1[6][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[6][16] = new Cell(new DiamondDoor(5,true),null,null);
        level1[6][18] = new Cell(new Floor(),null,null);
        level1[6][19] = new Cell(new Floor(),null,null);
        level1[6][20] = new Cell(new Floor(),null,null);
        level1[7][4] = new Cell(new Floor(),null,snake2);
        level1[7][7] = new Cell(new Floor(),null,null);
        level1[7][8] = new Cell(new Floor(),new Diamond(),null);
        level1[7][9] = new Cell(new Floor(),new Diamond(),null);
        level1[7][10] = new Cell(new Floor(),new Diamond(),null);
        level1[7][12] = new Cell(new Floor(),null,null);
        level1[7][13] = new Cell(new Floor(),null,fireTrap1);
        level1[7][19] = new Cell(pressMechanism.door,null,null);
        level1[8][4] = new Cell(new Floor(),null,snake2);
        level1[8][5] = new Cell(new Floor(),null,null);
        level1[8][6] = new Cell(new Floor(),null,null);
        level1[8][7] = new Cell(check2,null,null);
        level1[8][12] = new Cell(new Floor(),null,null);
        level1[8][13] = new Cell(new Floor(),null,fireTrap1);
        level1[8][19] = new Cell(new Floor(),null,null);
        level1[9][4] = new Cell(new Floor(),null,snake2);
        level1[9][12] = new Cell(new Floor(),null,null);
        level1[9][13] = new Cell(new Floor(),null,fireTrap1);
        level1[9][14] = new Cell(new Floor(),null,null);
        level1[9][15] = new Cell(new Floor(),null,null);
        level1[9][16] = new Cell(new Floor(),null,snake1);
        level1[9][17] = new Cell(new Floor(),null,null);
        level1[9][18] = new Cell(new Floor(),null,null);
        level1[9][19] = new Cell(new Floor(),null,null);
        level1[10][3] = new Cell(new Floor(),null,null);
        level1[10][4] = new Cell(new Floor(),null,snake2);
        level1[10][13] = new Cell(new Floor(),null,fireTrap1);
        level1[10][14] = new Cell(new Floor(),null,null);
        level1[10][15] = new Cell(new Floor(),null,null);
        level1[10][16] = new Cell(new Floor(),null,snake1);
        level1[10][17] = new Cell(new Floor(),null,null);
        level1[10][18] = new Cell(new Floor(),null,new Rock(null));//only for test
        level1[11][2] = new Cell(new Floor(),new Diamond(),null);
        level1[11][3] = new Cell(new Floor(),new Diamond(),null);
        level1[11][15] = new Cell(new Floor(),null,new Rock(null));
        level1[11][17] = new Cell(new Floor(),null,null);
        level1[11][18] = new Cell(new Floor(),null,null);
        level1[12][2] = new Cell(new Floor(),new Diamond(),null);
        level1[12][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][4] = new Cell(new Floor(),new Diamond(),null);
        level1[12][7] = new Cell(new Floor(),null,null);
//        level1[12][8] = new Cell(new Floor(),null,null);
        level1[12][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                new SilverKey(), new GoldKey(), new Energy(3)),null);
        level1[12][13] = new Cell(new Floor(),null,null);
        level1[12][14] = new Cell(new Floor(),null,null);
        level1[12][15] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][17] = new Cell(new Floor(),null,null);
        level1[12][18] = new Cell(new Floor(),null,null);
        level1[12][19] = new Cell(pressMechanism2.pressPanel,null,null);
        level1[13][1] = new Cell(new Floor(),null,null);//new Rock(null)
        level1[13][2] = new Cell(new Floor(),new Diamond(),null);
        level1[13][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[13][4] = new Cell(new Floor(),new Diamond(),null);
        level1[13][7] = new Cell(new Floor(),null,null);
        level1[13][8] = new Cell(new Floor(),new Diamond(),null);
        level1[13][13] = new Cell(doubleDoor.leftDoor,null,null);
        level1[13][17] = new Cell(new Floor(),null,null);
        level1[13][18] = new Cell(new Floor(),null,null);
        level1[13][19] = new Cell(new Floor(),null,null);
        level1[14][1] = new Cell(new Floor(),null,null);
        level1[14][2] = new Cell(new Floor(),null,new Rock(null));
        level1[14][3] = new Cell(new Floor(),new Diamond(),null);
        level1[14][4] = new Cell(new Floor(),null,new Rock(null));
        level1[14][7] = new Cell(new Floor(),null,null);
        level1[14][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), null, null, null, new Energy(3)),null);
//        level1[14][8] = new Cell(new Floor(),null,null);
        level1[14][11] = new Cell(new Floor(),null,null);
        level1[14][12] = new Cell(new Floor(),null,null);
        level1[14][13] = new Cell(new Floor(),null,null);
        level1[14][14] = new Cell(new Floor(),null,null);
        level1[14][18] = new Cell(pressMechanism2.door,null,null);
        level1[15][2] = new Cell(new Floor(),new Diamond(),null);
        level1[15][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[15][4] = new Cell(new Floor(),new Diamond(),null);
        level1[15][11] = new Cell(new Floor(),null,new Rock(null));
        level1[15][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[15][13] = new Cell(new Floor(),null,snake3);
        level1[15][14] = new Cell(new Floor(),null,snake3);
        level1[15][18] = new Cell(new Floor(),null,null);
        level1[16][2] = new Cell(new Floor(),null,null);
        level1[16][3] = new Cell(new Floor(),null,null);
        level1[16][4] = new Cell(new Floor(),new Diamond(),null);
        level1[16][11] = new Cell(new Floor(),null,null);
        level1[16][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[16][13] = new Cell(new Floor(),null,snake4);
        level1[16][14] = new Cell(new Floor(),null,snake4);
        level1[16][18] = new Cell(new Floor(),null,null);
        level1[17][3] = new Cell(new Floor(),null,null);
        level1[17][4] = new Cell(new Floor(),null,null);
        level1[17][11] = new Cell(new Floor(),null,new Rock(null));
        level1[17][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[17][13] = new Cell(new Floor(),null,null);
        level1[17][14] = new Cell(new Floor(),null,null);
        level1[17][18] = new Cell(new Floor(),null,null);
        level1[18][4] = new Cell(check3,null,null);
        level1[18][7] = new Cell(new Floor(),null,fireTrap2);
        level1[18][11] = new Cell(new Floor(),null,null);
        level1[18][12] = new Cell(new Floor(),null,null);
        level1[18][13] = new Cell(new Floor(),null,null);
        level1[18][18] = new Cell(new Floor(),null,null);
        level1[19][3] = new Cell(new Floor(),null,null);
        level1[19][4] = new Cell(new Floor(),new Diamond(),null);
        level1[19][7] = new Cell(new Floor(),null,fireTrap2);
//        level1[19][8] = new Cell(new Floor(),null,null);
        level1[19][8] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[19][9] = new Cell(new Floor(),null,fireTrap3);
        level1[19][13] = new Cell(check4,null,null);
        level1[19][18] = new Cell(new Floor(),null,null);
        level1[20][3] = new Cell(new Floor(),null,null);
        level1[20][4] = new Cell(new Floor(),new Diamond(),null);
        level1[20][5] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[20][7] = new Cell(new Floor(),null,fireTrap2);
        level1[20][8] = new Cell(new Floor(),null,null);
        level1[20][9] = new Cell(new Floor(),null,fireTrap3);
        level1[20][13] = new Cell(doubleDoor.rightDoor,null,null);
        level1[20][18] = new Cell(new Floor(),null,null);
        level1[21][3] = new Cell(new Floor(),null,null);
        level1[21][4] = new Cell(new Floor(),new Diamond(),null);
        level1[21][5] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[21][6] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[21][7] = new Cell(new Floor(),null,fireTrap2);
        level1[21][8] = new Cell(new Floor(),null,null);
        level1[21][9] = new Cell(new Floor(),null,fireTrap3);
        level1[21][10] = new Cell(new Floor(),null,null);
        level1[21][11] = new Cell(new Floor(),null,null);
        level1[21][12] = new Cell(new Floor(),null,null);
        level1[21][13] = new Cell(new Floor(),null,null);
        level1[21][18] = new Cell(new BlockedDoor(),null,null);
        level1[22][3] = new Cell(new Floor(),null,null);
        level1[22][4] = new Cell(new Floor(),null,null);
        level1[22][9] = new Cell(new Floor(),null,fireTrap3);
        level1[22][18] = new Cell(new DoorWithKeyhole().silver,null,null);
        level1[23][17] = new Cell(new Floor(),null,null);
        level1[23][18] = new Cell(new Floor(),null,null);
        level1[24][16] = new Cell(new Floor(),null,null);
        level1[24][17] = new Cell(new Floor(),null,null);
        level1[24][18] = new Cell(new Floor(),null,null);
        level1[25][5] = new Cell(check5,null,null);
        level1[25][6] = new Cell(new Floor(),null,null);
//        level1[25][7] = new Cell(new Floor(),null,null);
        level1[25][7] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[25][11] = new Cell(new Floor(),new Diamond(),null);
        level1[25][15] = new Cell(new Floor(),null,null);
        level1[25][16] = new Cell(new Floor(),null,null);
        level1[25][17] = new Cell(new Floor(),null,null);
        level1[25][18] = new Cell(new Floor(),null,null);
        level1[26][5] = new Cell(new Floor(),null,null);
        level1[26][6] = new Cell(new Floor(),null,null);
        level1[26][7] = new Cell(new Floor(),null,null);
        level1[26][14] = new Cell(new Floor(),null,null);
        level1[26][15] = new Cell(new Floor(),null,null);
        level1[26][16] = new Cell(new Floor(),null,null);
//        level1[26][17] = new Cell(new Floor(),null,null);
        level1[26][17] = new Cell(new Floor(),new Chest(null, new RedDiamond(1), null, null, null),null);
        level1[27][5] = new Cell(new Floor(),null,new Rock(null));
        level1[27][15] = new Cell(new Floor(),null,null);
        level1[27][16] = new Cell(new Floor(),null,null);
        level1[27][17] = new Cell(new Floor(),null,null);
        level1[27][18] = new Cell(new Floor(),null,null);
        level1[28][5] = new Cell(new Floor(),new Diamond(),null);
        level1[28][7] = new Cell(new Floor(),null,null);
        level1[28][11] = new Cell(new DoorWithKeyhole().gold,null,null);
        level1[28][16] = new Cell(new Floor(),null,null);
        level1[28][17] = new Cell(new Floor(),null,null);
        level1[28][18] = new Cell(new Floor(),null,null);
        level1[29][5] = new Cell(new Floor(),new Diamond(),null);
        level1[29][7] = new Cell(new Floor(),null,null);
        level1[29][11] = new Cell(new Floor(),null,null);
        level1[29][17] = new Cell(new Floor(),null,null);
        level1[29][18] = new Cell(new Floor(),null,null);
        level1[30][7] = new Cell(new Floor(),null,null);
        level1[30][11] = new Cell(new Floor(),null,null);
        level1[30][18] = new Cell(new DoorWithKeyhole().silver,null,null);
        level1[31][6] = new Cell(new Floor(),null,null);
        level1[31][7] = new Cell(new Floor(),null,null);
        level1[32][6] = new Cell(new Floor(),null,snake5);
        level1[32][7] = new Cell(new Floor(),null,snake5);
        level1[32][12] = new Cell(new Floor(),new Diamond(),null);
        level1[32][13] = new Cell(new Floor(),new Diamond(),null);
        level1[32][14] = new Cell(new Floor(),new Diamond(),null);
        level1[32][17] = new Cell(new Floor(),null,null);
        level1[33][4] = new Cell(new Floor(),null,null);
        level1[33][4] = new Cell(new Floor(),null,null);
        level1[33][5] = new Cell(new Floor(),null,null);
        level1[33][6] = new Cell(new Floor(),null,null);
        level1[33][7] = new Cell(new Floor(),null,null);
        level1[34][4] = new Cell(new Floor(),null,null);
        level1[34][6] = new Cell(new Floor(),null,null);
        level1[34][7] = new Cell(new Floor(),null,null);
        level1[35][4] = new Cell(new Floor(),null,null);
        level1[36][4] = new Cell(new DiamondDoor(20,true),null,null);
        level1[36][11] = new Cell(new Floor(),null,scorpion);
        level1[36][12] = new Cell(new Floor(),null,scorpion);
        level1[36][13] = new Cell(new Floor(),null,scorpion);
        level1[36][14] = new Cell(new Floor(),null,null);
        level1[37][4] = new Cell(new Floor(),null,null);
        level1[37][11] = new Cell(new Floor(),null,scorpion);
        level1[37][12] = new Cell(new Floor(),null,null);
        level1[37][13] = new Cell(new Floor(),null,scorpion);
        level1[37][14] = new Cell(new Floor(),new Diamond(),null);
        level1[38][4] = new Cell(new Floor(),null,null);
        level1[38][11] = new Cell(new Floor(),null,scorpion);
        level1[38][12] = new Cell(new Floor(),null,scorpion);
        level1[38][13] = new Cell(new Floor(),null,scorpion);
//        level1[38][14] = new Cell(new Floor(),null,null);
        level1[38][14] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[39][4] = new Cell(new Floor(),null,null);

        //SecretWalls
        level1[9][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[10][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[11][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[35][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[36][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][8] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][9] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[37][10] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        //BreakableWalls
        level1[22][11] = new Cell(new BreakableWall(22,11),null,null);
        level1[23][3] = new Cell(new BreakableWall(23,3),null,null);
        level1[23][4] = new Cell(new BreakableWall(23,4),null,null);
        level1[23][11] = new Cell(new BreakableWall(23,11),null,null);
        level1[24][3] = new Cell(new BreakableWall(24,3),null,null);
        level1[24][4] = new Cell(new BreakableWall(24,4),null,null);
        level1[24][5] = new Cell(new BreakableWall(24,5),null,null);
        level1[24][11] = new Cell(new BreakableWall(24,11),null,null);
        level1[26][11] = new Cell(new BreakableWall(26,11),null,null);
        level1[27][6] = new Cell(new BreakableWall(27,6),null,null);
        level1[27][7] = new Cell(new BreakableWall(27,7),null,null);
        level1[27][11] = new Cell(new BreakableWall(27,11),null,null);
        level1[28][6] = new Cell(new BreakableWall(28,6),null,null);
        level1[29][6] = new Cell(new BreakableWall(29,6),null,null);
        level1[31][11] = new Cell(new BreakableWall(31,11),null,null);
        level1[31][18] = new Cell(new BreakableWall(31,18),null,null);
        level1[32][11] = new Cell(new BreakableWall(32,11),null,null);
        level1[32][15] = new Cell(new BreakableWall(32,15),null,null);
        level1[32][16] = new Cell(new BreakableWall(32,16),null,null);
        level1[32][18] = new Cell(new BreakableWall(32,18),null,null);


        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                if(level1[i][j]==null){
                    level1[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level1);
        }

        this.level4 = new Level(level1,2,4,2,16, 200,4);
        this.level4.setCheckpoints(checkpoints);
    }

    private void initLevel5(){
        Snake snake1 = new Snake(140,70,40,true, playPanel);
        Snake snake2 = new Snake(350,70,180,true, playPanel);
        Snake snake3 = new Snake(70,140,20,false, playPanel);
        Snake snake4 = new Snake(70,140,70,false, playPanel);
        Snake snake5 = new Snake(70,140,10,false, playPanel);

        Scorpion scorpion = new Scorpion(210,210,false,null,null);

        FireTrap fireTrap1 = new FireTrap(0,false);
        FireTrap fireTrap2 = new FireTrap(3,false);
        FireTrap fireTrap3 = new FireTrap(2,false);

        Snake[] snakes = {snake3,snake4};
        DoubleDoor doubleDoor = new DoubleDoor(snakes,true,20,13);
        PressMechanism pressMechanism = new PressMechanism(5,20);
        PressMechanism pressMechanism2 = new PressMechanism(12,19);

        int[] additionalSegmentCheck1 = {12,16,14,19};
        Checkpoint check1 = new Checkpoint(3,8,11,21,additionalSegmentCheck1,4,16,4,4);
        int[] additionalSegmentCheck2 = {12,8,14,8};
        Checkpoint check2 = new Checkpoint(6,1,18,7,additionalSegmentCheck2,8,7,4,4);
        Checkpoint check3 = new Checkpoint(18,3,24,13,18,4,4,4);
        int[] additionalSegmentCheck4 = {9,16,10,16};
        Checkpoint check4 = new Checkpoint(11,11,18,19,additionalSegmentCheck4,19,13,4,4);
        int[] additionalSegmentCheck5 = {35,4,38,14};
        Checkpoint check5 = new Checkpoint(25,4,34,7,additionalSegmentCheck5,25,5,4,4);

        //Floor + (Harmless/Trap)
        Cell[][] level1 = new Cell[40][22];
        level1[0][16] = new Cell(new Floor(),null,null);
        level1[1][16] = new Cell(new BlockedDoor(),null,null);
        level1[2][16] = new Cell(new Floor(),null,null);
        level1[3][16] = new Cell(new Floor(),null,null);
        level1[3][18] = new Cell(new Floor(),null,null);
        level1[3][19] = new Cell(new Floor(),new Diamond(),null);
        level1[4][16] = new Cell(check1,null,null);
        level1[4][18] = new Cell(new Floor(),null,null);
        level1[4][19] = new Cell(new Floor(),null,new Rock(null));
//        level1[5][16] = new Cell(new Floor(),null,null);//only for test
        level1[5][16] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                new SilverKey(), new GoldKey(), new Energy(2)),null);//only for test
        level1[5][17] = new Cell(new Floor(),null,null);
        level1[5][18] = new Cell(new Floor(),null,null);
        level1[5][19] = new Cell(new Floor(),null,null);
        level1[5][20] = new Cell(pressMechanism.pressPanel,null,null);
        level1[6][4] = new Cell(new Floor(),null,snake2);
        level1[6][10] = new Cell(new Floor(),new Diamond(),null);
        level1[6][11] = new Cell(new Floor(),new Diamond(),null);
        level1[6][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[6][16] = new Cell(new DiamondDoor(5, true),null,null);
        level1[6][18] = new Cell(new Floor(),null,null);
        level1[6][19] = new Cell(new Floor(),null,null);
        level1[6][20] = new Cell(new Floor(),null,null);
        level1[7][4] = new Cell(new Floor(),null,snake2);
        level1[7][7] = new Cell(new Floor(),null,null);
        level1[7][8] = new Cell(new Floor(),new Diamond(),null);
        level1[7][9] = new Cell(new Floor(),new Diamond(),null);
        level1[7][10] = new Cell(new Floor(),new Diamond(),null);
        level1[7][12] = new Cell(new Floor(),null,null);
        level1[7][13] = new Cell(new Floor(),null,fireTrap1);
        level1[7][19] = new Cell(pressMechanism.door,null,null);
        level1[8][4] = new Cell(new Floor(),null,snake2);
        level1[8][5] = new Cell(new Floor(),null,null);
        level1[8][6] = new Cell(new Floor(),null,null);
        level1[8][7] = new Cell(check2,null,null);
        level1[8][12] = new Cell(new Floor(),null,null);
        level1[8][13] = new Cell(new Floor(),null,fireTrap1);
        level1[8][19] = new Cell(new Floor(),null,null);
        level1[9][4] = new Cell(new Floor(),null,snake2);
        level1[9][12] = new Cell(new Floor(),null,null);
        level1[9][13] = new Cell(new Floor(),null,fireTrap1);
        level1[9][14] = new Cell(new Floor(),null,null);
        level1[9][15] = new Cell(new Floor(),null,null);
        level1[9][16] = new Cell(new Floor(),null,snake1);
        level1[9][17] = new Cell(new Floor(),null,null);
        level1[9][18] = new Cell(new Floor(),null,null);
        level1[9][19] = new Cell(new Floor(),null,null);
        level1[10][3] = new Cell(new Floor(),null,null);
        level1[10][4] = new Cell(new Floor(),null,snake2);
        level1[10][13] = new Cell(new Floor(),null,fireTrap1);
        level1[10][14] = new Cell(new Floor(),null,null);
        level1[10][15] = new Cell(new Floor(),null,null);
        level1[10][16] = new Cell(new Floor(),null,snake1);
        level1[10][17] = new Cell(new Floor(),null,null);
        level1[10][18] = new Cell(new Floor(),null,new Rock(null));//only for test
        level1[11][2] = new Cell(new Floor(),new Diamond(),null);
        level1[11][3] = new Cell(new Floor(),new Diamond(),null);
        level1[11][15] = new Cell(new Floor(),null,new Rock(null));
        level1[11][17] = new Cell(new Floor(),null,null);
        level1[11][18] = new Cell(new Floor(),null,null);
        level1[12][2] = new Cell(new Floor(),new Diamond(),null);
        level1[12][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][4] = new Cell(new Floor(),new Diamond(),null);
        level1[12][7] = new Cell(new Floor(),null,null);
//        level1[12][8] = new Cell(new Floor(),null,null);
        level1[12][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                new SilverKey(), new GoldKey(), new Energy(3)),null);
        level1[12][13] = new Cell(new Floor(),null,null);
        level1[12][14] = new Cell(new Floor(),null,null);
        level1[12][15] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][17] = new Cell(new Floor(),null,null);
        level1[12][18] = new Cell(new Floor(),null,null);
        level1[12][19] = new Cell(pressMechanism2.pressPanel,null,null);
        level1[13][1] = new Cell(new Floor(),null,null);//new Rock(null)
        level1[13][2] = new Cell(new Floor(),new Diamond(),null);
        level1[13][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[13][4] = new Cell(new Floor(),new Diamond(),null);
        level1[13][7] = new Cell(new Floor(),null,null);
        level1[13][8] = new Cell(new Floor(),new Diamond(),null);
        level1[13][13] = new Cell(doubleDoor.leftDoor,null,null);
        level1[13][17] = new Cell(new Floor(),null,null);
        level1[13][18] = new Cell(new Floor(),null,null);
        level1[13][19] = new Cell(new Floor(),null,null);
        level1[14][1] = new Cell(new Floor(),null,null);
        level1[14][2] = new Cell(new Floor(),null,new Rock(null));
        level1[14][3] = new Cell(new Floor(),new Diamond(),null);
        level1[14][4] = new Cell(new Floor(),null,new Rock(null));
        level1[14][7] = new Cell(new Floor(),null,null);
        level1[14][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), null, null, null, new Energy(3)),null);
//        level1[14][8] = new Cell(new Floor(),null,null);
        level1[14][11] = new Cell(new Floor(),null,null);
        level1[14][12] = new Cell(new Floor(),null,null);
        level1[14][13] = new Cell(new Floor(),null,null);
        level1[14][14] = new Cell(new Floor(),null,null);
        level1[14][18] = new Cell(pressMechanism2.door,null,null);
        level1[15][2] = new Cell(new Floor(),new Diamond(),null);
        level1[15][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[15][4] = new Cell(new Floor(),new Diamond(),null);
        level1[15][11] = new Cell(new Floor(),null,new Rock(null));
        level1[15][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[15][13] = new Cell(new Floor(),null,snake3);
        level1[15][14] = new Cell(new Floor(),null,snake3);
        level1[15][18] = new Cell(new Floor(),null,null);
        level1[16][2] = new Cell(new Floor(),null,null);
        level1[16][3] = new Cell(new Floor(),null,null);
        level1[16][4] = new Cell(new Floor(),new Diamond(),null);
        level1[16][11] = new Cell(new Floor(),null,null);
        level1[16][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[16][13] = new Cell(new Floor(),null,snake4);
        level1[16][14] = new Cell(new Floor(),null,snake4);
        level1[16][18] = new Cell(new Floor(),null,null);
        level1[17][3] = new Cell(new Floor(),null,null);
        level1[17][4] = new Cell(new Floor(),null,null);
        level1[17][11] = new Cell(new Floor(),null,new Rock(null));
        level1[17][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[17][13] = new Cell(new Floor(),null,null);
        level1[17][14] = new Cell(new Floor(),null,null);
        level1[17][18] = new Cell(new Floor(),null,null);
        level1[18][4] = new Cell(check3,null,null);
        level1[18][7] = new Cell(new Floor(),null,fireTrap2);
        level1[18][11] = new Cell(new Floor(),null,null);
        level1[18][12] = new Cell(new Floor(),null,null);
        level1[18][13] = new Cell(new Floor(),null,null);
        level1[18][18] = new Cell(new Floor(),null,null);
        level1[19][3] = new Cell(new Floor(),null,null);
        level1[19][4] = new Cell(new Floor(),new Diamond(),null);
        level1[19][7] = new Cell(new Floor(),null,fireTrap2);
//        level1[19][8] = new Cell(new Floor(),null,null);
        level1[19][8] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[19][9] = new Cell(new Floor(),null,fireTrap3);
        level1[19][13] = new Cell(check4,null,null);
        level1[19][18] = new Cell(new Floor(),null,null);
        level1[20][3] = new Cell(new Floor(),null,null);
        level1[20][4] = new Cell(new Floor(),new Diamond(),null);
        level1[20][5] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[20][7] = new Cell(new Floor(),null,fireTrap2);
        level1[20][8] = new Cell(new Floor(),null,null);
        level1[20][9] = new Cell(new Floor(),null,fireTrap3);
        level1[20][13] = new Cell(doubleDoor.rightDoor,null,null);
        level1[20][18] = new Cell(new Floor(),null,null);
        level1[21][3] = new Cell(new Floor(),null,null);
        level1[21][4] = new Cell(new Floor(),new Diamond(),null);
        level1[21][5] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[21][6] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[21][7] = new Cell(new Floor(),null,fireTrap2);
        level1[21][8] = new Cell(new Floor(),null,null);
        level1[21][9] = new Cell(new Floor(),null,fireTrap3);
        level1[21][10] = new Cell(new Floor(),null,null);
        level1[21][11] = new Cell(new Floor(),null,null);
        level1[21][12] = new Cell(new Floor(),null,null);
        level1[21][13] = new Cell(new Floor(),null,null);
        level1[21][18] = new Cell(new BlockedDoor(),null,null);
        level1[22][3] = new Cell(new Floor(),null,null);
        level1[22][4] = new Cell(new Floor(),null,null);
        level1[22][9] = new Cell(new Floor(),null,fireTrap3);
        level1[22][18] = new Cell(new DoorWithKeyhole().silver,null,null);
        level1[23][17] = new Cell(new Floor(),null,null);
        level1[23][18] = new Cell(new Floor(),null,null);
        level1[24][16] = new Cell(new Floor(),null,null);
        level1[24][17] = new Cell(new Floor(),null,null);
        level1[24][18] = new Cell(new Floor(),null,null);
        level1[25][5] = new Cell(check5,null,null);
        level1[25][6] = new Cell(new Floor(),null,null);
//        level1[25][7] = new Cell(new Floor(),null,null);
        level1[25][7] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[25][11] = new Cell(new Floor(),new Diamond(),null);
        level1[25][15] = new Cell(new Floor(),null,null);
        level1[25][16] = new Cell(new Floor(),null,null);
        level1[25][17] = new Cell(new Floor(),null,null);
        level1[25][18] = new Cell(new Floor(),null,null);
        level1[26][5] = new Cell(new Floor(),null,null);
        level1[26][6] = new Cell(new Floor(),null,null);
        level1[26][7] = new Cell(new Floor(),null,null);
        level1[26][14] = new Cell(new Floor(),null,null);
        level1[26][15] = new Cell(new Floor(),null,null);
        level1[26][16] = new Cell(new Floor(),null,null);
//        level1[26][17] = new Cell(new Floor(),null,null);
        level1[26][17] = new Cell(new Floor(),new Chest(null, new RedDiamond(1), null, null, null),null);
        level1[27][5] = new Cell(new Floor(),null,new Rock(null));
        level1[27][15] = new Cell(new Floor(),null,null);
        level1[27][16] = new Cell(new Floor(),null,null);
        level1[27][17] = new Cell(new Floor(),null,null);
        level1[27][18] = new Cell(new Floor(),null,null);
        level1[28][5] = new Cell(new Floor(),new Diamond(),null);
        level1[28][7] = new Cell(new Floor(),null,null);
        level1[28][11] = new Cell(new DoorWithKeyhole().gold,null,null);
        level1[28][16] = new Cell(new Floor(),null,null);
        level1[28][17] = new Cell(new Floor(),null,null);
        level1[28][18] = new Cell(new Floor(),null,null);
        level1[29][5] = new Cell(new Floor(),new Diamond(),null);
        level1[29][7] = new Cell(new Floor(),null,null);
        level1[29][11] = new Cell(new Floor(),null,null);
        level1[29][17] = new Cell(new Floor(),null,null);
        level1[29][18] = new Cell(new Floor(),null,null);
        level1[30][7] = new Cell(new Floor(),null,null);
        level1[30][11] = new Cell(new Floor(),null,null);
        level1[30][18] = new Cell(new DoorWithKeyhole().silver,null,null);
        level1[31][6] = new Cell(new Floor(),null,null);
        level1[31][7] = new Cell(new Floor(),null,null);
        level1[32][6] = new Cell(new Floor(),null,snake5);
        level1[32][7] = new Cell(new Floor(),null,snake5);
        level1[32][12] = new Cell(new Floor(),new Diamond(),null);
        level1[32][13] = new Cell(new Floor(),new Diamond(),null);
        level1[32][14] = new Cell(new Floor(),new Diamond(),null);
        level1[32][17] = new Cell(new Floor(),null,null);
        level1[33][4] = new Cell(new Floor(),null,null);
        level1[33][4] = new Cell(new Floor(),null,null);
        level1[33][5] = new Cell(new Floor(),null,null);
        level1[33][6] = new Cell(new Floor(),null,null);
        level1[33][7] = new Cell(new Floor(),null,null);
        level1[34][4] = new Cell(new Floor(),null,null);
        level1[34][6] = new Cell(new Floor(),null,null);
        level1[34][7] = new Cell(new Floor(),null,null);
        level1[35][4] = new Cell(new Floor(),null,null);
        level1[36][4] = new Cell(new DiamondDoor(10,true),null,null);
        level1[36][11] = new Cell(new Floor(),null,scorpion);
        level1[36][12] = new Cell(new Floor(),null,scorpion);
        level1[36][13] = new Cell(new Floor(),null,scorpion);
        level1[36][14] = new Cell(new Floor(),null,null);
        level1[37][4] = new Cell(new Floor(),null,null);
        level1[37][11] = new Cell(new Floor(),null,scorpion);
        level1[37][12] = new Cell(new Floor(),null,null);
        level1[37][13] = new Cell(new Floor(),null,scorpion);
        level1[37][14] = new Cell(new Floor(),new Diamond(),null);
        level1[38][4] = new Cell(new Floor(),null,null);
        level1[38][11] = new Cell(new Floor(),null,scorpion);
        level1[38][12] = new Cell(new Floor(),null,scorpion);
        level1[38][13] = new Cell(new Floor(),null,scorpion);
//        level1[38][14] = new Cell(new Floor(),null,null);
        level1[38][14] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[39][4] = new Cell(new Floor(),null,null);

        //SecretWalls
        level1[9][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[10][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[11][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[35][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[36][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][8] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level1[37][9] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[37][10] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        //BreakableWalls
        level1[22][11] = new Cell(new BreakableWall(22,11),null,null);
        level1[23][3] = new Cell(new BreakableWall(23,3),null,null);
        level1[23][4] = new Cell(new BreakableWall(23,4),null,null);
        level1[23][11] = new Cell(new BreakableWall(23,11),null,null);
        level1[24][3] = new Cell(new BreakableWall(24,3),null,null);
        level1[24][4] = new Cell(new BreakableWall(24,4),null,null);
        level1[24][5] = new Cell(new BreakableWall(24,5),null,null);
        level1[24][11] = new Cell(new BreakableWall(24,11),null,null);
        level1[26][11] = new Cell(new BreakableWall(26,11),null,null);
        level1[27][6] = new Cell(new BreakableWall(27,6),null,null);
        level1[27][7] = new Cell(new BreakableWall(27,7),null,null);
        level1[27][11] = new Cell(new BreakableWall(27,11),null,null);
        level1[28][6] = new Cell(new BreakableWall(28,6),null,null);
        level1[29][6] = new Cell(new BreakableWall(29,6),null,null);
        level1[31][11] = new Cell(new BreakableWall(31,11),null,null);
        level1[31][18] = new Cell(new BreakableWall(31,18),null,null);
        level1[32][11] = new Cell(new BreakableWall(32,11),null,null);
        level1[32][15] = new Cell(new BreakableWall(32,15),null,null);
        level1[32][16] = new Cell(new BreakableWall(32,16),null,null);
        level1[32][18] = new Cell(new BreakableWall(32,18),null,null);


        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                if(level1[i][j]==null){
                    level1[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level1);
        }

        this.level5 = new Level(level1,2,4,2,16, 200, 5);
        this.level5.setCheckpoints(checkpoints);
    }

    public Level getLevel1() {
        return level1;
    }

    public Level getLevel2() {
        return level2;
    }

    public Level getLevel3() {
        return level3;
    }

    public Level getLevel4() {
        return level4;
    }

    public Level getLevel5() {
        return level5;
    }


    //    public Cell[][] getLevel1() {
//        return level1;
//    }
//
//    public Cell[][] getLevel2() {
//        return level2;
//    }
//
//    public Cell[][] getLevel3() {
//        return level3;
//    }
//
//    public Cell[][] getLevel4() {
//        return level4;
//    }
//
//    public Cell[][] getLevel5() {
//        return level5;
//    }


}
