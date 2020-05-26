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

import javax.swing.*;

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

    public Maps(){
        initLevel1();
        initLevel2();
        initLevel3();
        initLevel4();
        initLevel5();
    }

    private void initLevel1(){



        Snake snake1 = new Snake(140,70,40,true);
        Snake snake2 = new Snake(350,70,180,true);
        Snake snake3 = new Snake(70,140,20,false);
        Snake snake4 = new Snake(70,140,70,false);
        Snake snake5 = new Snake(70,140,10,false);

        Scorpion scorpion = new Scorpion(210,210,false,null,null);

        FireTrap fireTrap1 = new FireTrap(0,false);
        FireTrap fireTrap2 = new FireTrap(3,false);
        FireTrap fireTrap3 = new FireTrap(2,false);

        Snake[] snakes = {snake3,snake4};
        DoubleDoor doubleDoor = new DoubleDoor(snakes,true,20,13);
        PressMechanism pressMechanism = new PressMechanism(5,20);
        PressMechanism pressMechanism2 = new PressMechanism(12,19);

        Checkpoint check1 = new Checkpoint(3,8,13,20,4,16,4,4);
        Checkpoint check2 = new Checkpoint(11,1,19,8,8,7,4,4);
        Checkpoint check3 = new Checkpoint(18,3,24,13,18,4,4,4);
        Checkpoint check4 = new Checkpoint(9,11,18,19,18,13,4,4);
        Checkpoint check5 = new Checkpoint(25,4,38,14,25,5,4,4);

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
        level1[4][19] = new Cell(new Floor(),null,new Rock());
        level1[5][16] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                new SilverKey(), new GoldKey(), new Energy()),null);//only for test
        level1[5][17] = new Cell(new Floor(),null,null);
        level1[5][18] = new Cell(new Floor(),null,null);
        level1[5][19] = new Cell(new Floor(),null,null);
        level1[5][20] = new Cell(pressMechanism.pressPanel,null,new Rock());
        level1[6][4] = new Cell(new Floor(),null,snake2);
        level1[6][10] = new Cell(new Floor(),new Diamond(),null);
        level1[6][11] = new Cell(new Floor(),new Diamond(),null);
        level1[6][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[6][16] = new Cell(new Floor(),null,null);
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
        level1[10][18] = new Cell(new Floor(),null,null);
        level1[11][2] = new Cell(new Floor(),new Diamond(),null);
        level1[11][3] = new Cell(new Floor(),new Diamond(),null);
        level1[11][15] = new Cell(new Floor(),null,new Rock());
        level1[11][17] = new Cell(new Floor(),null,null);
        level1[11][18] = new Cell(new Floor(),null,null);
        level1[12][2] = new Cell(new Floor(),new Diamond(),null);
        level1[12][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][4] = new Cell(new Floor(),new Diamond(),null);
        level1[12][7] = new Cell(new Floor(),null,null);
        level1[12][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                new SilverKey(), new GoldKey(), new Energy()),null);
        level1[12][13] = new Cell(new Floor(),null,null);
        level1[12][14] = new Cell(new Floor(),null,null);
        level1[12][15] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[12][17] = new Cell(new Floor(),null,null);
        level1[12][18] = new Cell(new Floor(),null,null);
        level1[12][19] = new Cell(pressMechanism2.pressPanel,null,null);
        level1[13][1] = new Cell(new Floor(),null,new Rock());
        level1[13][2] = new Cell(new Floor(),new Diamond(),null);
        level1[13][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[13][4] = new Cell(new Floor(),new Diamond(),null);
        level1[13][7] = new Cell(new Floor(),null,null);
        level1[13][8] = new Cell(new Floor(),null,null);
        level1[13][13] = new Cell(doubleDoor.leftDoor,null,null);
        level1[13][17] = new Cell(new Floor(),null,null);
        level1[13][18] = new Cell(new Floor(),null,null);
        level1[13][19] = new Cell(new Floor(),null,null);
        level1[14][1] = new Cell(new Floor(),null,null);
        level1[14][2] = new Cell(new Floor(),null,new Rock());
        level1[14][3] = new Cell(new Floor(),new Diamond(),null);
        level1[14][4] = new Cell(new Floor(),null,new Rock());
        level1[14][7] = new Cell(new Floor(),null,null);
        level1[14][8] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[14][11] = new Cell(new Floor(),null,null);
        level1[14][12] = new Cell(new Floor(),null,null);
        level1[14][13] = new Cell(new Floor(),null,null);
        level1[14][14] = new Cell(new Floor(),null,null);
        level1[14][18] = new Cell(pressMechanism2.door,null,null);
        level1[15][2] = new Cell(new Floor(),new Diamond(),null);
        level1[15][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[15][4] = new Cell(new Floor(),new Diamond(),null);
        level1[15][11] = new Cell(new Floor(),null,new Rock());
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
        level1[17][11] = new Cell(new Floor(),null,new Rock());
        level1[17][12] = new Cell(new Floor(),new Tumbleweed(),null);
        level1[17][13] = new Cell(new Floor(),null,null);
        level1[17][14] = new Cell(new Floor(),null,null);
        level1[17][18] = new Cell(new Floor(),null,null);
        level1[18][4] = new Cell(check3,null,null);
        level1[18][7] = new Cell(new Floor(),null,fireTrap2);
        level1[18][11] = new Cell(new Floor(),null,null);
        level1[18][12] = new Cell(new Floor(),null,null);
        level1[18][13] = new Cell(check4,null,null);
        level1[18][18] = new Cell(new Floor(),null,null);
        level1[19][3] = new Cell(new Floor(),null,null);
        level1[19][4] = new Cell(new Floor(),new Diamond(),null);
        level1[19][7] = new Cell(new Floor(),null,fireTrap2);
        level1[19][8] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[19][9] = new Cell(new Floor(),null,fireTrap3);
        level1[19][13] = new Cell(new Floor(),null,null);
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
        level1[22][18] = new Cell(new Floor(),null,null);
        level1[23][17] = new Cell(new Floor(),null,null);
        level1[23][18] = new Cell(new Floor(),null,null);
        level1[24][16] = new Cell(new Floor(),null,null);
        level1[24][17] = new Cell(new Floor(),null,null);
        level1[24][18] = new Cell(new Floor(),null,null);
        level1[25][5] = new Cell(check5,null,null);
        level1[25][6] = new Cell(new Floor(),null,null);
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
        level1[26][17] = new Cell(new Floor(),new Chest(null, null, null, null, null),null);
        level1[27][5] = new Cell(new Floor(),null,new Rock());
        level1[27][15] = new Cell(new Floor(),null,null);
        level1[27][16] = new Cell(new Floor(),null,null);
        level1[27][17] = new Cell(new Floor(),null,null);
        level1[27][18] = new Cell(new Floor(),null,null);
        level1[28][5] = new Cell(new Floor(),new Diamond(),null);
        level1[28][7] = new Cell(new Floor(),null,null);
        level1[28][11] = new Cell(new Floor(),null,null);
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
        level1[36][4] = new Cell(new Floor(),null,null);
        level1[36][11] = new Cell(new Floor(),null,scorpion);
        level1[36][12] = new Cell(new Floor(),null,scorpion);
        level1[36][13] = new Cell(new Floor(),null,scorpion);
        level1[36][14] = new Cell(new Floor(),null,null);
        level1[37][4] = new Cell(new Floor(),null,null);
        level1[37][11] = new Cell(new Floor(),null,scorpion);
        level1[37][12] = new Cell(new Floor(),null,null);
        level1[37][13] = new Cell(new Floor(),null,scorpion);
        level1[37][14] = new Cell(new Floor(),null,null);
        level1[38][4] = new Cell(new Floor(),null,null);
        level1[38][11] = new Cell(new Floor(),null,scorpion);
        level1[38][12] = new Cell(new Floor(),null,scorpion);
        level1[38][13] = new Cell(new Floor(),null,scorpion);
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

        this.level1 = new Level(level1,2,4,2,16);
        this.level1.setCheckpoints(checkpoints);
    }

    private void initLevel2(){
        //TODO Create a level 2 matrix
    }

    private void initLevel3(){
        //TODO Create a level 3 matrix
    }

    private void initLevel4(){
        //TODO Create a level 4 matrix
    }

    private void initLevel5(){
        //TODO Create a level 5 matrix
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
