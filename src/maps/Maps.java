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
import source.AdditionalImage;
import source.PlayPanel;
import source.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.DataBufferFloat;

/**
 * @author Volodymyr Dzhosan
 * @author Iryna Matviienko
 * @author Illia Sitkov
 */
public class Maps {



    private Level level1;
    private Level level2;
    private Level level3;
    private Level level4;
    private Level level5;

    private PlayPanel playPanel;

    /**
     * constructor with parameters
     * @param playPanel
     */
    public Maps(PlayPanel playPanel) {
        this.playPanel = playPanel;
    }

    /**
     * method initialises cpecific level
     * @param currentLevel
     */
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

    /**
     * method returnes specific level
     * @param level
     * @return
     */
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

    /**
     * method initialises level 1
     */
    private void initLevel1() {
        AdditionalImage[] additionalImages = {new AdditionalImage("additionalImages/picture.png",0,700,6*70-17,6*70-17),
                new AdditionalImage("additionalImages/column3.png", 575,565,70,260),
                new AdditionalImage("additionalImages/wall2.png", 1055+10,575,240,170),
                new AdditionalImage("additionalImages/wall3.png", 485,0,210,184),
                new AdditionalImage("additionalImages/wall4BC.png", 1210,-11,551,204),
                new AdditionalImage("additionalImages/wall6.png", 1690,570,400,187),
                new AdditionalImage("additionalImages/wall4B.png", 1020,1065,560,184),
                new AdditionalImage("additionalImages/column3.png", 910,997,70,176),
                new AdditionalImage("additionalImages/column3.png", 540,996,70,319),
                };

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
        level1[15][10] = new Cell(new Wall(Wall.Type.CEILING),null,snake3);
        level1[15][11] = new Cell(new Floor(),null,new Rock(snake3));
        level1[15][12] = new Cell(new Floor(),new Tumbleweed(),snake3);
        level1[15][13] = new Cell(new Floor(),null,snake3);
        level1[15][14] = new Cell(new Floor(),null,snake3);
        level1[15][18] = new Cell(new Floor(),null,null);
        level1[16][2] = new Cell(new Floor(),null,null);
        level1[16][3] = new Cell(new Floor(),null,null);
        level1[16][4] = new Cell(new Floor(),new Diamond(),null);
        level1[16][10] = new Cell(new Wall(Wall.Type.CEILING),null,snake4);
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
        level1[26][17] = new Cell(new Floor(),new Chest(null, new RedDiamond(2), new SilverKey(), null, new Energy(10)),null);
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

        level1[9][7] = new Cell(new SecretWall(Wall.Type.LEFT_SIDE), null, null);
        level1[10][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level1[11][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level1[36][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level1[37][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level1[37][8] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()), null, null);
        level1[37][9] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level1[37][10] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()), null, null);
        level1[10][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level1[11][7] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        level1[35][7] = new Cell(new SecretWall(Wall.Type.LEFT_SIDE),null,null);
        level1[36][7] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);

        //BreakableWalls
        level1[22][11] = new Cell(new BreakableWall(22, 11), null, null);
        level1[23][3] = new Cell(new BreakableWall(23, 3), null, null);
        level1[23][4] = new Cell(new BreakableWall(23, 4), null, null);
        level1[23][11] = new Cell(new BreakableWall(23, 11), null, null);
        level1[24][3] = new Cell(new BreakableWall(24, 3), null, null);
        level1[24][4] = new Cell(new BreakableWall(24, 4), null, null);
        level1[24][5] = new Cell(new BreakableWall(24, 5), null, null);
        level1[24][11] = new Cell(new BreakableWall(24, 11), null, null);
        level1[26][11] = new Cell(new BreakableWall(26, 11), null, null);
        level1[27][6] = new Cell(new BreakableWall(27, 6), null, null);
        level1[27][7] = new Cell(new BreakableWall(27, 7), null, null);
        level1[27][11] = new Cell(new BreakableWall(27, 11), null, null);
        level1[28][6] = new Cell(new BreakableWall(28, 6), null, null);
        level1[29][6] = new Cell(new BreakableWall(29, 6), null, null);
        level1[31][11] = new Cell(new BreakableWall(31, 11), null, null);
        level1[31][18] = new Cell(new BreakableWall(31, 18), null, null);
        level1[32][11] = new Cell(new BreakableWall(32, 11), null, null);
        level1[32][15] = new Cell(new BreakableWall(32, 15), null, null);
        level1[32][16] = new Cell(new BreakableWall(32, 16), null, null);
        level1[32][18] = new Cell(new BreakableWall(32, 18), null, null);


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
        this.level1.setAdditionalImages(additionalImages);
    }


    /**
     * method initialises level 2
     */
    private void initLevel2(){

        AdditionalImage[] additionalImages = {new AdditionalImage("additionalImages/wall5B.png", -10,765,200,200),
                new AdditionalImage("additionalImages/wall1.png", 367,437,270,190),
                new AdditionalImage("additionalImages/wall11.png", 820,920,280,200),
                new AdditionalImage("additionalImages/column1.png", 1383,1067,70,106),
                new AdditionalImage("additionalImages/column1.png", 1837,1067,70,106),
                new AdditionalImage("additionalImages/picture.png", 2467,490+17,350,350-34),
                new AdditionalImage("additionalImages/column1.png", 193,857,70,106),
                new AdditionalImage("additionalImages/column1.png", 193,647,70,106),
                new AdditionalImage("additionalImages/column1.png", 367,717,70,106),
                new AdditionalImage("additionalImages/column1.png", 123,0,70,193),
                new AdditionalImage("additionalImages/column1.png", 193,0,70,123),
                new AdditionalImage("additionalImages/column1.png", 753,0,70,263),
                new AdditionalImage("additionalImages/column1.png", 823,0,70,123),
                new AdditionalImage("additionalImages/column1.png", 30*70-17,17*70+17,70,106),
                new AdditionalImage("additionalImages/column1.png", 33*70+17,17*70+17,70,106),
                new AdditionalImage("additionalImages/wall8.png", 20*70+17,11*70+17,5*70-34,3*70-34),
                new AdditionalImage("additionalImages/wall2.png", 17*70+17,17*70+17,210-34,5*70)};


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
        level2[4][16] = new Cell(new DiamondDoor(30,false), null, null);
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
        level2[11][21] = new Cell(new DiamondDoor(46,false), null, null);
        level2[12][2] = new Cell(new Wall(Wall.Type.RIGHT_LOWER_CORNER), null, snake4);
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
        level2[20][14] = new Cell(doubleDoor.leftDoor, null, null);//doubleDoor.leftDoor
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
        level2[26][14] = new Cell(doubleDoor.rightDoor, null, null);//doubleDoor.rightDoor
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
        level2[38][12] = new Cell(new DiamondDoor(70,true), null, null);
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
        this.level2.setAdditionalImages(additionalImages);
    }

    /**
     * Initialize level 3
     */
    private void initLevel3(){
        AdditionalImage[] additionalImages = {new AdditionalImage("additionalImages/wall5.png",13*70,70,280,280),
                new AdditionalImage("additionalImages/wall10.png", 2*70,2*70,5*70-17,3*70-17),
                new AdditionalImage("additionalImages/wall12.png", 18*70,10*70,3*70-17,2*70),
                new AdditionalImage("additionalImages/wall7.png", 32*70,14*70,3*70,3*70),
                new AdditionalImage("additionalImages/wall11.png", 0*70,21*70+17,5*70-17,4*70-17*2),
                new AdditionalImage("additionalImages/wall6.png", 20*70+17,23*70+17,6*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column3.png", 17*70+17,21*70+17,1*70-17*2,5*70-17*2),
                new AdditionalImage("additionalImages/column1.png", 28*70+17,19*70,1*70-17,3*70-17*2),
                new AdditionalImage("additionalImages/column1.png", 29*70+17,20*70,1*70-17,3*70-17),
                new AdditionalImage("additionalImages/column1.png", 30*70+17,21*70,1*70-17,3*70-17),
                new AdditionalImage("additionalImages/column1.png", 31*70+17,22*70,1*70-17,3*70-17),
                new AdditionalImage("additionalImages/column1.png", 14*70+17,13*70+17,1*70-17,3*70-17*2),
                new AdditionalImage("additionalImages/column2.png", 8*70+17,22*70,1*70-17,4*70-17),
                new AdditionalImage("additionalImages/column2.png", 9*70+17,23*70,1*70-17,4*70-17),
                new AdditionalImage("additionalImages/column2.png", 30*70+17,10*70+17,1*70-17*2,3*70-17*2)};


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

        int[] additionalSegmentCheck2 = {11,17,15,23};
        Checkpoint check2 = new Checkpoint(8,10,14,16, additionalSegmentCheck2, 9,15,4,4);

        int[] additionalSegmentCheck3 = {1,5,3,20};
        Checkpoint check3 = new Checkpoint(4,11,6,15, additionalSegmentCheck3, 6,15,4,4);

        Checkpoint check4 = new Checkpoint(6,3,16,8,8,7,4,4);

        int[] additionalSegmentCheck5 = {17,5,25,8};
        Checkpoint check5 = new Checkpoint(18,1,34,3,additionalSegmentCheck5,17,7,4,4);

        int[] additionalSegmentCheck6 = {26,7,32,10};
        Checkpoint check6 = new Checkpoint(28,11,31,13,additionalSegmentCheck6,27,7,4,4);

        Checkpoint check7 = new Checkpoint(16,10,27,16,27,13,4,4);

        int[] additionalSegmentCheck8 = {17,17,22,24};
        Checkpoint check8 = new Checkpoint(13,25,24,28,additionalSegmentCheck8,17,17,4,4);

        Checkpoint check9 = new Checkpoint(24,21,34,27,24,26,4,4);

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
        level3[30][14] = new Cell(new Floor(),null,null);
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
        level3[1][15] = new Cell(new SecretWall(Wall.Type.RIGHT_SIDE),null,null);
        level3[1][16] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[1][17] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[2][17] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[2][18] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        level3[16][11] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[16][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[17][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level3[17][13] = new Cell(new SecretWall(Wall.Type.CEILING),null,null);

        level3[23][2] = new Cell(new SecretWall(Wall.Type.LEFT_SIDE),null,null);
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
        level3[27][18] = new Cell(new SecretWall(Wall.Type.RIGHT_SIDE),null,null);
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

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5,check6,check7,check8,check9};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level3);
        }

        this.level3 = new Level(level3,2,5,2,25, 300,3);
        this.level3.setCheckpoints(checkpoints);
        this.level3.setAdditionalImages(additionalImages);
    }

    private void initLevel4(){

        AdditionalImage[] additionalImages = {new AdditionalImage("additionalImages/wall7.png", 5*70,0,3*70-17,3*70-17),
                new AdditionalImage("additionalImages/column2.png", 7*70+17,5*70+17,70-34,3*70-34+2),
                new AdditionalImage("additionalImages/column2.png", 8*70+17,9*70+17,70-34,4*70-34+2),
                new AdditionalImage("additionalImages/wall4.png", 2*70,5*70+17,4*70-17,2*70-34),
                new AdditionalImage("additionalImages/wall5.png", 0,19*70+17,4*70-17,3*70),
                new AdditionalImage("additionalImages/wall6.png", 5*70+17,20*70+17,5*70-34,2*70),
                new AdditionalImage("additionalImages/picture.png", 11*70+17,18*70+17,4*70-34,4*70-34),
                new AdditionalImage("additionalImages/column2.png", 8*70-17,25*70+17,70,2*70-34+2),
                new AdditionalImage("additionalImages/column2.png", 14*70+17,25*70+17,70,3*70-34+2),
                new AdditionalImage("additionalImages/wall10.png", 17*70+17,25*70+17,8*70-34,5*70),
                new AdditionalImage("additionalImages/wall4BC.png", 18*70+17,0*70,5*70-34,3*70-17),
                new AdditionalImage("additionalImages/column2.png", 22*70,0*70,53,3*70-17+2),
                new AdditionalImage("additionalImages/column2.png", 24*70+17,0*70,53,3*70-17+2),
                new AdditionalImage("additionalImages/column2.png", 25*70,0*70,53,3*70-17+2),
                new AdditionalImage("additionalImages/column2.png", 29*70+17,0*70,53,3*70-17+2),
                new AdditionalImage("additionalImages/wall1.png", 30*70,0*70,53,3*70-17+2),
                new AdditionalImage("additionalImages/column2.png", 32*70+17,0*70,53,3*70-17+2),
                new AdditionalImage("additionalImages/wall2.png", 33*70+17,0*70,490,490-17+2),
                new AdditionalImage("additionalImages/column2.png", 25*70-17,9*70+17,70,3*70-34+2),
                new AdditionalImage("additionalImages/column2.png", 27*70+17,9*70+17,70,3*70-34+2),
                new AdditionalImage("additionalImages/wall8.png", 30*70+17,22*70-17,3*70-34,3*70),
                new AdditionalImage("additionalImages/wall9.png", 34*70+17,19*70+17,5*70,3*70-34)};


        Snake snake1 = new Snake(70,420,170,false, playPanel);
        Snake snake2 = new Snake(70,560,450,false, playPanel);
        Snake snake3 = new Snake(70,210,70,false, playPanel);
        Snake snake4 = new Snake(350,70,210,true, playPanel);
        Snake snake5 = new Snake(70,840,715,false, playPanel);
        Snake snake6 = new Snake(350,70,50,true, playPanel);
        Snake snake7 = new Snake(280,70,80,true, playPanel);
        Snake snake8 = new Snake(70,700,490,false, playPanel);
        Snake snake9 = new Snake(70,700,140,false, playPanel);
        Snake snake10 = new Snake(420,70,100,true, playPanel);
        Snake snake11 = new Snake(420,70,340,true, playPanel);
        Snake snake12 = new Snake(280,70,90,true, playPanel);
        Snake snake13 = new Snake(70,700,350,false, playPanel);
        Snake snake14 = new Snake(70,700,215,false, playPanel);
        Snake snake15 = new Snake(70,420,140,false, playPanel);
        Snake snake16 = new Snake(70,700,475,false, playPanel);
        Snake snake17 = new Snake(70,700,240,false, playPanel);
        Snake snake18 = new Snake(70,350,250,false, playPanel);
        Snake snake19 = new Snake(70,420,220,false, playPanel);
        Snake snake20 = new Snake(70,280,75,false, playPanel);


        Scorpion scorpion1 = new Scorpion(280,280,true,null,null);
        Scorpion scorpion2 = new Scorpion(210,280,true,null,null);
        Scorpion scorpion3 = new Scorpion(210,280,false,null,null);
        Scorpion scorpion4 = new Scorpion(560,210,true,null,null);

        FireTrap fireTrap1 = new FireTrap(0,true);
        FireTrap fireTrap2 = new FireTrap(1,false);
        FireTrap fireTrap3 = new FireTrap(2,true);
        FireTrap fireTrap4 = new FireTrap(1,true);
        FireTrap fireTrap5 = new FireTrap(1,false);
        FireTrap fireTrap6 = new FireTrap(2,true);
        FireTrap fireTrap7 = new FireTrap(0,false);
        FireTrap fireTrap8 = new FireTrap(2,true);

        PressMechanism pressMechanism1 = new PressMechanism(10,11);
        PressMechanism pressMechanism2 = new PressMechanism(25,26);
        PressMechanism pressMechanism3 = new PressMechanism(32,3);
        PressMechanism pressMechanism4 = new PressMechanism(36,12);
        PressMechanism pressMechanism5 = new PressMechanism(37,26);

        int[] additionalSegmentCheck1 = {8,6,19,12};
        Checkpoint check1 = new Checkpoint(2,1,10,5, additionalSegmentCheck1, 3,4,2,4);
        int[] additionalSegmentCheck2 = {1,13,11,20};
        Checkpoint check2 = new Checkpoint(1,6,7,12, additionalSegmentCheck2, 7,8,4,4);
        int[] additionalSegmentCheck3 = {7,27,16,28};
        Checkpoint check3 = new Checkpoint(2,20,13,26, additionalSegmentCheck3, 4,20,4,4);
        Checkpoint check4 = new Checkpoint(14,15,25,26,14,24,4,4);
        int[] additionalSegmentCheck5 = {23,9,23,13};
        Checkpoint check5 = new Checkpoint(21,1,33,5, additionalSegmentCheck5, 20,3,4,3);
        int[] additionalSegmentCheck6 = {27,21,29,25};
        Checkpoint check6 = new Checkpoint(24,9,38,20, additionalSegmentCheck6, 24,14,4,4);
        Checkpoint check7 = new Checkpoint(32,22,37,26,28,26,4,6);


        Cell[][] level4 = new Cell[40][30];
        //Floor + (Harmless/Trap)
        level4[0][4] = new Cell(new Floor(),null,null);
        level4[1][4] = new Cell(new BlockedDoor(),null,null);
        level4[1][9] = new Cell(new Floor(),null,new Rock());
        level4[1][10] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[1][11] = new Cell(new Floor(),null,null);
        level4[1][12] = new Cell(new Floor(),null,null);
        level4[1][14] = new Cell(new Floor(),null,new Rock());
        level4[1][16] = new Cell(new Floor(),null,null);
        level4[1][17] = new Cell(new Floor(),null,null);
        level4[1][18] = new Cell(new Floor(),new Chest(new PurpleDiamond(15),null,null,null,new Energy(25)),null);
        level4[2][4] = new Cell(new Floor(),null,null);
        level4[2][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[2][8] = new Cell(new Floor(),null,new Rock());
        level4[2][9] = new Cell(new Floor(),new Diamond(),null);
        level4[2][11] = new Cell(new Floor(),null,null);
        level4[2][12] = new Cell(new Floor(),null,null);
        level4[2][16] = new Cell(new Floor(),null,null);
        level4[2][17] = new Cell(new Floor(),null,null);
        level4[2][18] = new Cell(new Floor(),new Diamond(),null);
        level4[3][4] = new Cell(check1,null,null);
        level4[3][7] = new Cell(new Floor(),new Diamond(),null);
        level4[3][8] = new Cell(new Floor(),null,new Rock());
        level4[3][9] = new Cell(new Floor(),new Diamond(),null);
        level4[3][11] = new Cell(new Floor(),null,null);
        level4[3][12] = new Cell(new Floor(),null,null);
        level4[3][16] = new Cell(new Floor(),null,null);
        level4[3][17] = new Cell(new Floor(),null,null);
        level4[3][18] = new Cell(new Floor(),null,new Rock());
        level4[4][4] = new Cell(new Floor(),null,null);
        level4[4][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[4][8] = new Cell(new Floor(),null,new Rock());
        level4[4][9] = new Cell(new Floor(),new Diamond(),null);
        level4[4][10] = new Cell(new Wall(Wall.Type.HORISONTAL_TONNEL),null,snake3);
        level4[4][11] = new Cell(new Floor(),null,snake3);
        level4[4][12] = new Cell(new Floor(),null,snake3);
        level4[4][14] = new Cell(new Floor(),null,fireTrap2);
        level4[4][15] = new Cell(new Wall(Wall.Type.RIGHT_P),null,snake5);
        level4[4][16] = new Cell(new Floor(),null,snake5);
        level4[4][17] = new Cell(new Floor(),null,snake5);
        level4[4][18] = new Cell(new Floor(),null,snake5);
        level4[4][19] = new Cell(new Floor(),null,snake5);
        level4[4][20] = new Cell(check3,null,snake5);
        level4[4][22] = new Cell(new Floor(),new Diamond(),snake5);
        level4[4][25] = new Cell(new Floor(),null,snake5);
        level4[4][26] = new Cell(new Floor(),null,snake5);
        level4[5][3] = new Cell(new Floor(),null,new Rock());
        level4[5][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[5][7] = new Cell(new Floor(),null,new Rock());
        level4[5][8] = new Cell(new Floor(),null,new Rock());
        level4[5][9] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[5][11] = new Cell(new Floor(),null,null);
        level4[5][12] = new Cell(new Floor(),null,null);
        level4[5][13] = new Cell(new Floor(),null,fireTrap1);
        level4[5][14] = new Cell(new Floor(),null,fireTrap2);
        level4[5][15] = new Cell(new Floor(),null,fireTrap3);
        level4[5][16] = new Cell(new Floor(),null,null);
        level4[5][17] = new Cell(new Floor(),null,new Rock());
        level4[5][18] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[5][19] = new Cell(new Floor(),null,new Rock());
        level4[6][3] = new Cell(new Floor(),null,new Rock());
        level4[6][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[6][5] = new Cell(new Floor(),new Diamond(),null);
        level4[6][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[6][8] = new Cell(new Floor(),null,null);
        level4[6][9] = new Cell(new Floor(),new Diamond(),null);
        level4[6][11] = new Cell(new Floor(),null,null);
        level4[6][12] = new Cell(new Floor(),null,null);
        level4[6][13] = new Cell(new Floor(),null,fireTrap1);
        level4[6][14] = new Cell(new Floor(),null,fireTrap2);
        level4[6][15] = new Cell(new Floor(),null,fireTrap3);
        level4[6][16] = new Cell(new Floor(),null,null);
        level4[6][17] = new Cell(new Floor(),null,new Rock());
        level4[6][18] = new Cell(new Wall(Wall.Type.UPPER_P),null,snake4);
        level4[6][24] = new Cell(new Floor(),null,null);
        level4[7][4] = new Cell(new Floor(),null,null);
        level4[7][8] = new Cell(check2,null,null);
        level4[7][9] = new Cell(new Floor(),null,null);
        level4[7][10] = new Cell(new Floor(),null,null);
        level4[7][11] = new Cell(new Floor(),null,null);
        level4[7][12] = new Cell(new Floor(),null,null);
        level4[7][13] = new Cell(new Floor(),null,fireTrap1);
        level4[7][14] = new Cell(new Floor(),null,fireTrap2);
        level4[7][15] = new Cell(new Floor(),null,fireTrap3);
        level4[7][17] = new Cell(new Floor(),null,null);
        level4[7][18] = new Cell(new Floor(),null,snake4);
        level4[7][19] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[7][24] = new Cell(new Floor(),null,null);
        level4[7][27] = new Cell(new Floor(),null,fireTrap5);
        level4[8][1] = new Cell(new Wall(Wall.Type.CEILING),null,snake1);
        level4[8][2] = new Cell(new Floor(),null,snake1);
        level4[8][3] = new Cell(new Floor(),null,snake1);
        level4[8][4] = new Cell(new Floor(),null,snake1);
        level4[8][5] = new Cell(new Floor(),null,snake1);
        level4[8][6] = new Cell(new Floor(),null,snake1);
        level4[8][8] = new Cell(pressMechanism1.door,null,null);
        level4[8][13] = new Cell(new Floor(),null,fireTrap1);
        level4[8][15] = new Cell(new Floor(),null,fireTrap3);
        level4[8][17] = new Cell(new Floor(),null,fireTrap4);
        level4[8][18] = new Cell(new Floor(),null,snake4);
        level4[8][19] = new Cell(new Floor(),new Diamond(),null);
        level4[8][24] = new Cell(new Floor(),null,null);
        level4[8][27] = new Cell(new Floor(),new Diamond(),fireTrap5);
        level4[9][1] = new Cell(new Wall(Wall.Type.CEILING),null,snake2);
        level4[9][2] = new Cell(new Floor(),null,new Rock(snake2));
        level4[9][3] = new Cell(new Floor(),new Tumbleweed(),snake2);
        level4[9][4] = new Cell(new Floor(),new Tumbleweed(),snake2);
        level4[9][5] = new Cell(new Floor(),null,snake2);
        level4[9][6] = new Cell(new Floor(),null,snake2);
        level4[9][7] = new Cell(new Floor(),null,snake2);
        level4[9][8] = new Cell(new Floor(),null,snake2);
        level4[9][9] = new Cell(new Floor(),null,new Rock());
        level4[9][11] = new Cell(new Floor(),new Diamond(),null);
        level4[9][17] = new Cell(new Floor(),null,fireTrap4);
        level4[9][18] = new Cell(new Floor(),null,snake4);
        level4[9][19] = new Cell(new Floor(),new Diamond(),null);
        level4[9][23] = new Cell(new Floor(),null,null);
        level4[9][24] = new Cell(new Floor(),null,null);
        level4[9][25] = new Cell(new Floor(),null,snake6);
        level4[9][26] = new Cell(new Wall(Wall.Type.RIGHT_P),null,snake7);
        level4[9][27] = new Cell(new Floor(),new Diamond(),fireTrap5);
        level4[10][3] = new Cell(new Floor(),null,null);
        level4[10][6] = new Cell(new Floor(),null,new Rock());
        level4[10][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[10][8] = new Cell(new Floor(),null,null);
        level4[10][9] = new Cell(new Floor(),null,null);
        level4[10][10] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[10][11] = new Cell(pressMechanism1.pressPanel,null,null);
        level4[10][17] = new Cell(new Floor(),null,fireTrap4);
        level4[10][18] = new Cell(new Floor(),null,snake4);
        level4[10][19] = new Cell(new Floor(),new Diamond(),null);
        level4[10][22] = new Cell(new Floor(),null,new Rock());
        level4[10][24] = new Cell(new Floor(),null,null);
        level4[10][25] = new Cell(new Floor(),null,snake6);
        level4[10][26] = new Cell(new Floor(),null,snake7);
        level4[10][27] = new Cell(new Floor(),new Diamond(),fireTrap5);
        level4[11][3] = new Cell(new DoorWithKeyhole().silver,null,null);
        level4[11][8] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[11][9] = new Cell(new Floor(),new Diamond(),null);
        level4[11][17] = new Cell(new Floor(),null,fireTrap4);
        level4[11][22] = new Cell(new Floor(),new Diamond(),null);
        level4[11][24] = new Cell(new Floor(),null,null);
        level4[11][25] = new Cell(new Floor(),null,snake6);
        level4[11][26] = new Cell(new Floor(),null,snake7);
        level4[11][27] = new Cell(new Floor(),new Chest(new PurpleDiamond(5),null,new SilverKey(),null,null),null);
        level4[12][1] = new Cell(new Floor(),null,snake10);
        level4[12][2] = new Cell(new Floor(),null,snake11);
        level4[12][3] = new Cell(new Floor(),null,null);
        level4[12][6] = new Cell(new Floor(),null,new Rock());
        level4[12][8] = new Cell(new Floor(),new Diamond(),null);
        level4[12][9] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[12][22] = new Cell(new Floor(),null,new Rock());
        level4[12][24] = new Cell(new Floor(),null,null);
        level4[12][25] = new Cell(new Floor(),null,snake6);
        level4[12][26] = new Cell(new Floor(),null,snake7);
        level4[12][27] = new Cell(new Floor(),null,null);
        level4[12][28] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[13][1] = new Cell(new Floor(),null,snake10);
        level4[13][2] = new Cell(new Floor(),null,snake11);
        level4[13][6] = new Cell(new Floor(),new Diamond(),null);
        level4[13][8] = new Cell(new Floor(),new Diamond(),null);
        level4[13][9] = new Cell(new Floor(),null,new Rock());
        level4[13][24] = new Cell(new Floor(),null,null);
        level4[13][25] = new Cell(new Floor(),null,snake6);
        level4[13][28] = new Cell(new Floor(),new Diamond(),fireTrap6);
        level4[14][1] = new Cell(new Floor(),null,snake10);
        level4[14][2] = new Cell(new Floor(),null,snake11);
        level4[14][24] = new Cell(check4,null,null);
        level4[14][28] = new Cell(new Floor(),new Diamond(),fireTrap6);
        level4[15][1] = new Cell(new Floor(),null,snake10);
        level4[15][2] = new Cell(new Floor(),null,snake11);
        level4[15][15] = new Cell(new Wall(Wall.Type.CEILING),null,snake8);
        level4[15][16] = new Cell(new Floor(),null,snake8);
        level4[15][17] = new Cell(new Floor(),null,snake8);
        level4[15][18] = new Cell(new Floor(),null,snake8);
        level4[15][19] = new Cell(new Floor(),null,snake8);
        level4[15][20] = new Cell(new Floor(),null,snake8);
        level4[15][21] = new Cell(new Floor(),null,snake8);
        level4[15][22] = new Cell(new Floor(),null,snake8);
        level4[15][23] = new Cell(new Floor(),null,snake8);
        level4[15][24] = new Cell(new Floor(),null,snake8);
        level4[15][28] = new Cell(new Floor(),new Diamond(),fireTrap6);
        level4[16][1] = new Cell(new Floor(),null,snake10);
        level4[16][2] = new Cell(new Floor(),null,snake11);
        level4[16][7] = new Cell(new Floor(),null,scorpion1);
        level4[16][8] = new Cell(new Floor(),null,scorpion1);
        level4[16][9] = new Cell(new Floor(),null,scorpion1);
        level4[16][10] = new Cell(new Floor(),null,scorpion1);
        level4[16][18] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[16][28] = new Cell(new Floor(),null,fireTrap6);
        level4[17][1] = new Cell(new Floor(),null,snake10);
        level4[17][2] = new Cell(new Floor(),null,snake11);
        level4[17][3] = new Cell(new Floor(),null,null);
        level4[17][4] = new Cell(new Floor(),null,null);
        level4[17][7] = new Cell(new Floor(),null,scorpion1);
        level4[17][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(10),null,null,null,new Energy(25)),null);
        level4[17][10] = new Cell(new Floor(),null,scorpion1);
        level4[17][15] = new Cell(new Wall(Wall.Type.CEILING),null,snake9);
        level4[17][16] = new Cell(new Floor(),null,snake9);
        level4[17][17] = new Cell(new Floor(),null,snake9);
        level4[17][18] = new Cell(new Floor(),null,snake9);
        level4[17][19] = new Cell(new Floor(),null,snake9);
        level4[17][20] = new Cell(new Floor(),null,snake9);
        level4[17][21] = new Cell(new Floor(),null,snake9);
        level4[17][22] = new Cell(new Floor(),null,snake9);
        level4[17][23] = new Cell(new Floor(),null,snake9);
        level4[17][24] = new Cell(new Floor(),null,snake9);
        level4[18][3] = new Cell(new DoorWithKeyhole().silver,null,null);
        level4[18][7] = new Cell(new Floor(),null,scorpion1);
        level4[18][8] = new Cell(new Floor(),new Chest(new PurpleDiamond(5),null,null,null,new Energy(20)),null);
        level4[18][10] = new Cell(new Floor(),null,scorpion1);
        level4[18][21] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[19][3] = new Cell(new Floor(),null,null);
        level4[19][7] = new Cell(new Floor(),null,scorpion1);
        level4[19][8] = new Cell(new Floor(),null,scorpion1);
        level4[19][9] = new Cell(new Floor(),null,scorpion1);
        level4[19][10] = new Cell(new Floor(),null,scorpion1);
        level4[19][15] = new Cell(new Floor(),null,scorpion2);
        level4[19][16] = new Cell(new Floor(),null,scorpion2);
        level4[19][17] = new Cell(new Floor(),null,scorpion2);
        level4[19][18] = new Cell(new Floor(),null,scorpion2);
        level4[19][19] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[19][20] = new Cell(new Floor(),null,scorpion3);
        level4[19][21] = new Cell(new Floor(),null,scorpion3);
        level4[19][22] = new Cell(new Floor(),null,scorpion3);
        level4[19][23] = new Cell(new Floor(),null,scorpion3);
        level4[19][24] = new Cell(new Floor(),new Diamond(),null);
        level4[20][3] = new Cell(check5,null,null);
        level4[20][15] = new Cell(new Floor(),null,scorpion2);
        level4[20][18] = new Cell(new Floor(),null,scorpion2);
        level4[20][19] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[20][20] = new Cell(new Floor(),null,scorpion3);
        level4[20][23] = new Cell(new Floor(),null,scorpion3);
        level4[20][24] = new Cell(new Floor(),new Diamond(),null);
        level4[21][3] = new Cell(new Floor(),null,null);
        level4[21][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[21][5] = new Cell(new Floor(),new Diamond(),null);
        level4[21][15] = new Cell(new Floor(),null,scorpion2);
        level4[21][16] = new Cell(new Floor(),null,scorpion2);
        level4[21][17] = new Cell(new Floor(),null,scorpion2);
        level4[21][18] = new Cell(new Floor(),null,scorpion2);
        level4[21][19] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[21][20] = new Cell(new Floor(),null,scorpion3);
        level4[21][21] = new Cell(new Floor(),null,scorpion3);
        level4[21][22] = new Cell(new Floor(),null,scorpion3);
        level4[21][23] = new Cell(new Floor(),null,scorpion3);
        level4[21][24] = new Cell(new Floor(),new Diamond(),null);
        level4[22][3] = new Cell(new Floor(),null,null);
        level4[22][16] = new Cell(pressMechanism2.door,null,null);
        level4[22][24] = new Cell(new Floor(),null,null);
        level4[23][2] = new Cell(new Floor(),null,new Rock());
        level4[23][3] = new Cell(new Floor(),new Diamond(),null);
        level4[23][4] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[23][5] = new Cell(new Floor(),null,null);
        level4[23][6] = new Cell(new Floor(),null,null);
        level4[23][7] = new Cell(new Floor(),null,null);
        level4[23][8] = new Cell(new Floor(),null,null);
        level4[23][16] = new Cell(new Floor(),null,null);
        level4[23][17] = new Cell(new Floor(),new Chest(null,new RedDiamond(1),new SilverKey(),null,new Energy(35)),null);
        level4[24][3] = new Cell(new Floor(),new Diamond(),null);
        level4[24][7] = new Cell(new Floor(),null,null);
        level4[24][13] = new Cell(new Floor(),null,null);
        level4[24][14] = new Cell(check6,null,null);
        level4[25][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[25][6] = new Cell(new Floor(),null,scorpion4);
        level4[25][7] = new Cell(new Floor(),null,scorpion4);
        level4[25][8] = new Cell(new Floor(),null,scorpion4);
        level4[25][12] = new Cell(new Floor(),null,new Rock());
        level4[25][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[25][14] = new Cell(new Floor(),new Diamond(),null);
        level4[25][26] = new Cell(pressMechanism2.pressPanel,null,null);
        level4[26][2] = new Cell(new Floor(),null,new Rock());
        level4[26][3] = new Cell(new Floor(),new Diamond(),null);
        level4[26][6] = new Cell(new Floor(),null,scorpion4);
        level4[26][8] = new Cell(new Floor(),null,scorpion4);
        level4[26][9] = new Cell(new Floor(),null,null);
        level4[26][12] = new Cell(new Floor(),null,new Rock());
        level4[26][13] = new Cell(new Floor(),new Diamond(),null);
        level4[26][14] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[26][17] = new Cell(new Floor(),null,fireTrap7);
        level4[27][2] = new Cell(new Floor(),null,new Rock());
        level4[27][3] = new Cell(new Floor(),new Diamond(),null);
        level4[27][6] = new Cell(new Floor(),null,scorpion4);
        level4[27][7] = new Cell(new Floor(),null,snake12);
        level4[27][8] = new Cell(new Floor(),null,scorpion4);
        level4[27][12] = new Cell(new Floor(),null,new Rock());
        level4[27][13] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[27][14] = new Cell(new Floor(),new Diamond(),null);
        level4[27][17] = new Cell(new Floor(),null,fireTrap7);
        level4[27][18] = new Cell(new Floor(),null,fireTrap8);
        level4[27][19] = new Cell(new Floor(),null,null);
        level4[27][20] = new Cell(new Floor(),new Diamond(),null);
        level4[27][21] = new Cell(new Floor(),null,new Rock());
        level4[27][22] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[27][23] = new Cell(new Floor(),null,null);
        level4[27][24] = new Cell(new Floor(),null,null);
        level4[27][25] = new Cell(new Floor(),null,null);
        level4[27][26] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[28][2] = new Cell(new Floor(),null,new Rock());
        level4[28][3] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[28][6] = new Cell(new Floor(),null,scorpion4);
        level4[28][7] = new Cell(new Floor(),null,snake12);
        level4[28][8] = new Cell(new Floor(),null,scorpion4);
        level4[28][13] = new Cell(new Floor(),null,null);
        level4[28][16] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[28][17] = new Cell(new Floor(),null,fireTrap7);
        level4[28][18] = new Cell(new Floor(),null,fireTrap8);
        level4[28][19] = new Cell(new Floor(),null,null);
        level4[28][20] = new Cell(new Floor(),null,new Rock());
        level4[28][21] = new Cell(new Floor(),new Diamond(),null);
        level4[28][22] = new Cell(new Floor(),new Diamond(),null);
        level4[28][23] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[28][24] = new Cell(new Floor(),null,null);
        level4[28][26] = new Cell(check7,null,null);
        level4[29][3] = new Cell(new Floor(),null,null);
        level4[29][5] = new Cell(new Floor(),null,null);
        level4[29][6] = new Cell(new Floor(),null,scorpion4);
        level4[29][7] = new Cell(new Floor(),null,snake12);
        level4[29][8] = new Cell(new Floor(),null,scorpion4);
        level4[29][13] = new Cell(new Floor(),null,null);
        level4[29][14] = new Cell(new Floor(),null,null);
        level4[29][15] = new Cell(new Floor(),null,null);
        level4[29][16] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[29][17] = new Cell(new Floor(),null,fireTrap7);
        level4[29][18] = new Cell(new Floor(),null,fireTrap8);
        level4[29][19] = new Cell(new Floor(),null,null);
        level4[29][20] = new Cell(new Floor(),null,null);
        level4[29][22] = new Cell(new Floor(),new Chest(new PurpleDiamond(15),null,null,null,new Energy(30)),null);
        level4[29][26] = new Cell(new DoorWithKeyhole().gold,null,null);
        level4[30][3] = new Cell(new Floor(),null,null);
        level4[30][6] = new Cell(new Floor(),null,scorpion4);
        level4[30][7] = new Cell(new Floor(),null,snake12);
        level4[30][8] = new Cell(new Floor(),null,scorpion4);
        level4[30][10] = new Cell(new Floor(),null,new Rock());
        level4[30][17] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[30][18] = new Cell(new Floor(),null,fireTrap8);
        level4[30][20] = new Cell(new Floor(),null,null);
        level4[30][26] = new Cell(new Floor(),null,null);
        level4[31][3] = new Cell(new Floor(),null,null);
        level4[31][4] = new Cell(new Floor(),null,null);
        level4[31][6] = new Cell(new Floor(),null,scorpion4);
        level4[31][8] = new Cell(new Floor(),null,scorpion4);
        level4[31][20] = new Cell(new Floor(),null,null);
        level4[31][26] = new Cell(new Floor(),null,null);
        level4[32][3] = new Cell(pressMechanism3.pressPanel,null,null);
        level4[32][6] = new Cell(new Floor(),null,scorpion4);
        level4[32][7] = new Cell(new Floor(),null,scorpion4);
        level4[32][8] = new Cell(new Floor(),null,scorpion4);
        level4[32][20] = new Cell(new Floor(),null,null);
        level4[32][25] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[32][26] = new Cell(new Floor(),null,null);
        level4[33][7] = new Cell(new Floor(),null,null);
        level4[33][18] = new Cell(new Floor(),null,null);
        level4[33][20] = new Cell(new Floor(),new Chest(null,null,null,null,new Energy(35)),null);
        level4[33][24] = new Cell(new Floor(),new Diamond(),null);
        level4[33][25] = new Cell(new Floor(),new Tumbleweed(),null);
        level4[33][26] = new Cell(new Floor(),null,null);
        level4[34][7] = new Cell(pressMechanism3.door,null,null);
        level4[34][9] = new Cell(new Wall(Wall.Type.CEILING),null,snake13);
        level4[34][10] = new Cell(new Floor(),null,new Rock(snake13));
        level4[34][12] = new Cell(new Floor(),null,snake13);
        level4[34][13] = new Cell(new Floor(),null,snake13);
        level4[34][14] = new Cell(new Floor(),null,snake13);
        level4[34][15] = new Cell(new Floor(),null,snake13);
        level4[34][16] = new Cell(new Floor(),null,snake13);
        level4[34][17] = new Cell(new Floor(),new Tumbleweed(),snake13);
        level4[34][18] = new Cell(new Floor(),null,snake13);
        level4[34][22] = new Cell(new Wall(Wall.Type.RIGHT_LOWER_CORNER),null,snake18);
        level4[34][23] = new Cell(new Floor(),new Diamond(),snake18);
        level4[34][24] = new Cell(new Floor(),new Tumbleweed(),snake18);
        level4[34][25] = new Cell(new Floor(),null,snake18);
        level4[34][26] = new Cell(new Floor(),null,snake18);
        level4[35][7] = new Cell(pressMechanism4.door,null,null);
        level4[35][9] = new Cell(new Wall(Wall.Type.CEILING),null,snake14);
        level4[35][10] = new Cell(new Floor(),null,new Rock(snake14));
        level4[35][12] = new Cell(new Floor(),null,snake14);
        level4[35][13] = new Cell(new Floor(),null,snake14);
        level4[35][14] = new Cell(new Floor(),null,snake14);
        level4[35][15] = new Cell(new Floor(),null,snake14);
        level4[35][16] = new Cell(new Floor(),null,snake14);
        level4[35][17] = new Cell(new Floor(),new Tumbleweed(),snake14);
        level4[35][18] = new Cell(new Floor(),null,snake14);
        level4[35][21] = new Cell(new Wall(Wall.Type.CEILING),null,snake19);
        level4[35][22] = new Cell(new Floor(),new Diamond(),snake19);
        level4[35][23] = new Cell(new Floor(),new Tumbleweed(),snake19);
        level4[35][24] = new Cell(new Floor(),null,snake19);
        level4[35][25] = new Cell(new Floor(),null,snake19);
        level4[35][26] = new Cell(new Floor(),null,snake19);
        level4[36][7] = new Cell(pressMechanism5.door,null,null);
        level4[36][10] = new Cell(new Floor(),null,new Rock());
        level4[36][12] = new Cell(pressMechanism4.pressPanel,null,null);
        level4[36][13] = new Cell(new Wall(Wall.Type.BLOCKED),null,snake15);
        level4[36][14] = new Cell(new Floor(),null,snake15);
        level4[36][15] = new Cell(new Floor(),null,snake15);
        level4[36][16] = new Cell(new Floor(),null,snake15);
        level4[36][17] = new Cell(new Floor(),new Tumbleweed(),snake15);
        level4[36][18] = new Cell(new Floor(),null,snake15);
        level4[36][22] = new Cell(new Floor(),null,new Rock());
        level4[36][23] = new Cell(new Wall(Wall.Type.BLOCKED),null,snake20);
        level4[36][24] = new Cell(new Floor(),null,snake20);
        level4[36][25] = new Cell(new Floor(),null,snake20);
        level4[36][26] = new Cell(new Floor(),null,snake20);
        level4[37][7] = new Cell(new DiamondDoor(50,true),null,null);
        level4[37][9] = new Cell(new Wall(Wall.Type.CEILING),null,snake16);
        level4[37][10] = new Cell(new Floor(),null,new Rock(snake16));
        level4[37][12] = new Cell(new Floor(),null,snake16);
        level4[37][13] = new Cell(new Floor(),null,snake16);
        level4[37][14] = new Cell(new Floor(),null,snake16);
        level4[37][15] = new Cell(new Floor(),null,snake16);
        level4[37][16] = new Cell(new Floor(),null,snake16);
        level4[37][17] = new Cell(new Floor(),new Tumbleweed(),snake16);
        level4[37][18] = new Cell(new Floor(),null,snake16);
        level4[37][22] = new Cell(new Floor(),null,null);
        level4[37][24] = new Cell(new Floor(),null,null);
        level4[37][25] = new Cell(new Floor(),null,null);
        level4[37][26] = new Cell(pressMechanism5.pressPanel,null,null);
        level4[38][7] = new Cell(new Floor(),null,null);
        level4[38][9] = new Cell(new Wall(Wall.Type.CEILING),null,snake17);
        level4[38][10] = new Cell(new Floor(),null,new Rock(snake17));
        level4[38][12] = new Cell(new Floor(),null,snake17);
        level4[38][13] = new Cell(new Floor(),null,snake17);
        level4[38][14] = new Cell(new Floor(),null,snake17);
        level4[38][15] = new Cell(new Floor(),null,snake17);
        level4[38][16] = new Cell(new Floor(),null,snake17);
        level4[38][17] = new Cell(new Floor(),new Tumbleweed(),snake17);
        level4[38][18] = new Cell(new Floor(),null,snake17);
        level4[39][7] = new Cell(new Floor(),null,null);

        //Breakable Walls
        level4[1][13] = new Cell(new BreakableWall(1,13),null,null);
        level4[1][15] = new Cell(new BreakableWall(1,15),null,null);
        level4[2][13] = new Cell(new BreakableWall(2,13),null,null);
        level4[2][14] = new Cell(new BreakableWall(2,14),null,null);
        level4[2][15] = new Cell(new BreakableWall(2,15),null,null);
        level4[4][21] = new Cell(new BreakableWall(4,21),null,snake5);
        level4[4][23] = new Cell(new BreakableWall(4,23),null,snake5);
        level4[4][24] = new Cell(new BreakableWall(4,24),null,snake5);
        level4[5][24] = new Cell(new BreakableWall(5,24),null,null);
        level4[9][23] = new Cell(new BreakableWall(9,23),null,null);
        level4[10][23] = new Cell(new BreakableWall(10,23),null,null);
        level4[11][6] = new Cell(new BreakableWall(11,6),null,null);
        level4[11][7] = new Cell(new BreakableWall(11,7),null,null);
        level4[11][23] = new Cell(new BreakableWall(11,23),null,null);
        level4[12][7] = new Cell(new BreakableWall(12,7),null,null);
        level4[12][23] = new Cell(new BreakableWall(12,23),null,null);
        level4[13][7] = new Cell(new BreakableWall(13,7),null,null);
        level4[13][23] = new Cell(new BreakableWall(13,23),null,null);
        level4[23][9] = new Cell(new BreakableWall(23,9),null,null);
        level4[23][10] = new Cell(new BreakableWall(23,10),null,null);
        level4[23][11] = new Cell(new BreakableWall(23,11),null,null);
        level4[23][12] = new Cell(new BreakableWall(23,12),null,null);
        level4[23][13] = new Cell(new BreakableWall(23,13),null,null);
        level4[27][4] = new Cell(new BreakableWall(27,4),null,null);
        level4[30][11] = new Cell(new BreakableWall(30,11),null,null);
        level4[31][2] = new Cell(new BreakableWall(31,2),null,null);
        level4[31][11] = new Cell(new BreakableWall(31,11),null,null);
        level4[32][11] = new Cell(new BreakableWall(32,11),null,null);
        level4[32][18] = new Cell(new BreakableWall(32,18),null,null);
        level4[32][19] = new Cell(new BreakableWall(32,19),null,null);
        level4[33][11] = new Cell(new BreakableWall(33,11),null,null);
        level4[34][11] = new Cell(new BreakableWall(34,11),null,snake13);
        level4[35][11] = new Cell(new BreakableWall(35,11),null,snake14);
        level4[36][11] = new Cell(new BreakableWall(36,11),null,null);
        level4[37][11] = new Cell(new BreakableWall(37,11),null,snake16);
        level4[37][23] = new Cell(new BreakableWall(37,23),null,null);
        level4[38][11] = new Cell(new BreakableWall(38,11),null,snake17);

        level4[2][2] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),new Diamond(),null);
        level4[2][22] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[2][23] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[2][24] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[2][25] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),new Diamond(),null);
        level4[3][2] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[3][22] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[4][2] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[5][2] = new Cell(new SecretWall(new ImageIcon("mapImages/wallCeiling.png").getImage()),null,null);
        level4[5][22] = new Cell(new SecretWall(new ImageIcon("mapImages/wallLeft.png").getImage()),null,null);
        level4[6][22] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[6][23] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[8][14] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[9][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[10][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),new Chest(null,new RedDiamond(1),null,null,new Energy(15)),null);
        level4[11][11] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[12][4] = new Cell(new SecretWall(new ImageIcon("mapImages/wallFloor.png").getImage()),null,null);
        level4[12][11] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[12][12] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[12][13] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[12][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[13][4] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[13][14] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[14][4] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[14][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),new Chest(new PurpleDiamond(5),new RedDiamond(1),null,null,new Energy(20)),null);
        level4[14][13] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[14][14] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[15][4] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[15][14] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[16][4] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[16][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[17][12] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[17][13] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[17][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[18][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[19][11] = new Cell(new SecretWall(new ImageIcon("mapImages/wallFloor.png").getImage()),null,null);
        level4[19][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[23][21] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[23][22] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[23][23] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[23][24] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[24][21] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,new Rock());
        level4[25][21] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[25][22] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[25][23] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level4[25][24] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[25][25] = new Cell(new SecretWall(new ImageIcon("mapImages/wallCeiling.png").getImage()),null,null);
        level4[30][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level4[30][13] = new Cell(new SecretWall(new ImageIcon("mapImages/wallLeft.png").getImage()),null,null);
        level4[31][1] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,new Rock());
        level4[32][15] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),new Chest(null,new RedDiamond(1),null,new GoldKey(),new Energy(20)),null);
        level4[33][15] = new Cell(new SecretWall(new ImageIcon("mapImages/wallRight.png").getImage()),null,null);


        Util.setWalls(level4);

        for (int i = 0; i < level4.length; i++) {
            for (int j = 0; j < level4[i].length; j++) {
                if(level4[i][j]==null){
                    level4[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1,check2,check3,check4,check5,check6,check7};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level4);
        }

        this.level4 = new Level(level4,2,4,2,4, 400,4);
        this.level4.setCheckpoints(checkpoints);
        this.level4.setAdditionalImages(additionalImages);
    }

    /**
     * Initialize level 5
     */
    private void initLevel5(){
        AdditionalImage[] additionalImages = {new AdditionalImage("additionalImages/wall1.png",12*70+17,9*70+17,4*70-17*2,3*70-17),
                new AdditionalImage("additionalImages/wall10.png", 1*70,21*70+17,7*70-17,4*70-17*2),
                new AdditionalImage("additionalImages/wall8.png", 11*70+17,35*70+17,5*70-17*2,4*70-17),
                new AdditionalImage("additionalImages/picture.png", 21*70+17,11*70+17,4*70-17*2,4*70-17*2),
                new AdditionalImage("additionalImages/wings.png", 35*70+17,4*70,4*70-17,4*70-17),
                new AdditionalImage("additionalImages/wall9.png", 22*70,19*70+17,8*70-17,5*70-17*2),
                new AdditionalImage("additionalImages/wall2.png", 34*70,23*70+17,4*70-17,4*70-17*2),
                new AdditionalImage("additionalImages/column1.png", 9*70+17,7*70+17,1*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column2.png", 7*70,14*70+17,1*70-17*2,3*70-17),
                new AdditionalImage("additionalImages/column3.png", 10*70+17,11*70+17,1*70-17*2,5*70-17*2),
                new AdditionalImage("additionalImages/column4.png", 2*70+17,26*70+17,1*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column4.png", 4*70+17,26*70+17,1*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column4.png", 6*70+17,26*70+17,1*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column4.png", 7*70+17,26*70+17,1*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column4.png", 9*70+17,26*70+17,1*70-17*2,3*70-17*2),
                new AdditionalImage("additionalImages/column4.png", 25*70+17,2*70+17,1*70-17*2,4*70-17*2),
                new AdditionalImage("additionalImages/column2.png", 29*70+17,15*70+17,1*70-17*2,3*70-17),
                new AdditionalImage("additionalImages/column1.png", 31*70,21*70+17,1*70-17,3*70-17*2)};


        Scorpion scorpion1 = new Scorpion(700,560,true,null,null);
        Scorpion scorpion2 = new Scorpion(700,420,false,null,null);

        PressMechanism pressMechanism1 = new PressMechanism(7,10);
        PressMechanism pressMechanism2 = new PressMechanism(8,38);
        PressMechanism pressMechanism3 = new PressMechanism(33,10);

        FireTrap fireTrap1 = new FireTrap(0,false);
        FireTrap fireTrap2 = new FireTrap(2,true);
        FireTrap fireTrap3 = new FireTrap(2,true);
        FireTrap fireTrap4 = new FireTrap(2,true);
        FireTrap fireTrap5 = new FireTrap(2,false);
        FireTrap fireTrap6 = new FireTrap(2,false);
        FireTrap fireTrap7 = new FireTrap(2,false);
        FireTrap fireTrap8 = new FireTrap(2,false);
        FireTrap fireTrap9 = new FireTrap(2,true);
        FireTrap fireTrap10 = new FireTrap(2,true);
        FireTrap fireTrap11 = new FireTrap(2,true);
        FireTrap fireTrap12 = new FireTrap(2,true);

        Snake snake1 = new Snake(210,70,0,true,playPanel);
        Snake snake2 = new Snake(210,70,140,true,playPanel);
        Snake snake3 = new Snake(350,70,0,true,playPanel);
        Snake snake4 = new Snake(560,70,560,true,playPanel);
        Snake snake5 = new Snake(560,70,0,true,playPanel);

        Snake snake6 = new Snake(70,420,0,false,playPanel);
        Snake snake7 = new Snake(70,840,0,false,playPanel);
        Snake snake8 = new Snake(70,840,0,false,playPanel);
        Snake snake9 = new Snake(210,70,0,true,playPanel);
        Snake snake10 = new Snake(70,490,0,false,playPanel);
        Snake snake11 = new Snake(70,840,0,false,playPanel);
        Snake snake12 = new Snake(70,770,0,false,playPanel);

        Snake snake13 = new Snake(420,70,40,true, playPanel);
        Snake snake14 = new Snake(420,70,180,true, playPanel);

        Snake[] snakes = {snake3,snake4, snake5};

        DoubleDoor doubleDoor = new DoubleDoor(snakes,true,14,15);

        Checkpoint check1 = new Checkpoint(5,3,15,11,14,3,4,2);
        int[] additionalCheck2 = {5,13,18,20};
        Checkpoint check2 = new Checkpoint(1,5,4,18,additionalCheck2,13,15,4,4);
        Checkpoint check3 = new Checkpoint(1,24,15,38,13,32,4,4);
        Checkpoint check4 = new Checkpoint(22,4,38,12,22,4,2,2);
        Checkpoint check5 = new Checkpoint(23,13,38,23,23,16,3,3);
        Checkpoint check6 = new Checkpoint(22,24,32,37,22,36,2,7);
        Checkpoint check7 = new Checkpoint(33,27,38,37,33,35,2,6);




        //Floor + (Harmless/Trap)
        Cell[][] level5 = new Cell[40][40];



        level5[0][1] = new Cell(new Floor(), null,null);
        level5[1][1] = new Cell(new Floor(), null,null);
        level5[1][5] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),new RedDiamond(1),null, null,new Energy(40)),null);
        level5[1][17] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),new RedDiamond(1), new SilverKey(), null, new Energy(10)),null);
        level5[1][25] = new Cell(new Floor(), null,null);
        level5[1][26] = new Cell(new Floor(), null,null);
        level5[1][27] = new Cell(new Floor(), null,null);
        level5[1][28] = new Cell(new Floor(), null,null);
        level5[1][29] = new Cell(new Floor(), null,null);
        level5[1][32] = new Cell(new Floor(), null,null);
        level5[1][33] = new Cell(new Floor(), null,null);
        level5[1][34] = new Cell(new Floor(), null,null);
        level5[1][35] = new Cell(new Floor(), new Diamond(),null);
        level5[1][37] = new Cell(new Floor(), new Chest(new PurpleDiamond(6),new RedDiamond(1),new SilverKey(),null,null ),null);

        level5[2][1] = new Cell(new Floor(), null,null);
        level5[2][17] = new Cell(new Floor(), null,null);
        level5[2][18] = new Cell(new Floor(), new Chest(null, null, null, new GoldKey(), null),null);
        level5[2][25] = new Cell(new Floor(), null,null);
        level5[2][29] = new Cell(new Floor(), null,null);
        level5[2][32] = new Cell(new Floor(), null,null);
        level5[2][33] = new Cell(new Floor(), null,null);
        level5[2][34] = new Cell(new Floor(), new Diamond(),null);
        level5[2][35] = new Cell(new Floor(), new Diamond(),null);
        level5[2][37] = new Cell(new Floor(), null,null);
        level5[2][38] = new Cell(new Floor(), new Chest(null,null,null,new GoldKey(),new Energy(20)),null);

        level5[3][1] = new Cell(new Floor(), null,null);
        level5[3][17] = new Cell(new Floor(), null,null);
        level5[3][18] = new Cell(new Floor(), null,null);
        level5[3][25] = new Cell(new Floor(), null,null);
        level5[3][26] = new Cell(new Floor(), null,null);
        level5[3][27] = new Cell(new Floor(), null,null);
        level5[3][28] = new Cell(new Floor(), null,null);
        level5[3][29] = new Cell(new Floor(), null,null);
        level5[3][32] = new Cell(new Floor(), null,null);
        level5[3][33] = new Cell(new Floor(), null,null);
        level5[3][34] = new Cell(new Floor(), new Diamond(),null);
        level5[3][35] = new Cell(new Floor(), new Diamond(),null);
        level5[3][37] = new Cell(new Floor(), null,null);
        level5[3][38] = new Cell(new Floor(), new Chest(new PurpleDiamond(10),null,null,null,null),null);

        level5[4][1] = new Cell(new Floor(), null,null);
        level5[4][18] = new Cell(doubleDoor.leftDoor, null,null);
        level5[4][25] = new Cell(new Floor(), null,null);
        level5[4][29] = new Cell(new Floor(), null,null);
        level5[4][32] = new Cell(new Floor(), null,null);
        level5[4][33] = new Cell(new Floor(), null,null);
        level5[4][34] = new Cell(new Floor(), new Diamond(),null);
        level5[4][35] = new Cell(new Floor(), new Diamond(),null);
        level5[4][37] = new Cell(new Floor(), null,null);
        level5[4][38] = new Cell(new Floor(), null,null);

        level5[5][1] = new Cell(new Floor(), null,null);
        level5[5][3] = new Cell(new Floor(), null,null);
        level5[5][4] = new Cell(new Floor(), null,fireTrap1);
        level5[5][15] = new Cell(new Floor(), new Diamond(),null);
        level5[5][16] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[5][18] = new Cell(new Floor(), null,snake4);
        level5[5][17] = new Cell(new Floor(), null,null);
        level5[5][19] = new Cell(new Floor(), null,fireTrap4);
        level5[5][20] = new Cell(new Floor(), null,snake5);
        level5[5][25] = new Cell(new Floor(), null,null);
        level5[5][26] = new Cell(new Floor(), null,null);
        level5[5][27] = new Cell(new Floor(), null,null);
        level5[5][28] = new Cell(new Floor(), null,null);
        level5[5][29] = new Cell(new Floor(), null,null);
        level5[5][32] = new Cell(new Floor(), null,null);
        level5[5][33] = new Cell(new Floor(), null,null);
        level5[5][34] = new Cell(new Floor(), null,null);
        level5[5][35] = new Cell(new Floor(), new Diamond(),null);
        level5[5][38] = new Cell(pressMechanism2.door, null,null);

        level5[6][1] = new Cell(new Floor(), null,null);
        level5[6][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[6][4] = new Cell(new Floor(), null,fireTrap1);
        level5[6][7] = new Cell(new Floor(), null,snake1);
        level5[6][15] = new Cell(new Floor(), null,new Rock());
        level5[6][16] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[6][17] = new Cell(new Floor(), null,null);
        level5[6][18] = new Cell(new Floor(), null,snake4);
        level5[6][19] = new Cell(new Floor(), null,fireTrap4);
        level5[6][20] = new Cell(new Floor(), null,snake5);
        level5[6][25] = new Cell(new Floor(), null,null);
        level5[6][29] = new Cell(new Floor(), null,null);
        level5[6][32] = new Cell(new Floor(), null,null);
        level5[6][33] = new Cell(new Floor(), null,null);
        level5[6][34] = new Cell(new Floor(), null,null);
        level5[6][35] = new Cell(new Floor(), null,null);
        level5[6][36] = new Cell(new Floor(), null,null);
        level5[6][37] = new Cell(new Floor(), null,null);
        level5[6][38] = new Cell(new Floor(), null,null);

        level5[7][1] = new Cell(new Floor(), null,null);
        level5[7][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[7][4] = new Cell(new Floor(), null,fireTrap1);
        level5[7][5] = new Cell(new Floor(), null,null);
        level5[7][6] = new Cell(new Floor(), null,null);
        level5[7][7] = new Cell(new Floor(), null,snake1);
        level5[7][8] = new Cell(new Floor(), null,null);
        level5[7][9] = new Cell(new Floor(), null,null);
        level5[7][10] = new Cell(pressMechanism1.pressPanel, null,null);
        level5[7][17] = new Cell(new Floor(), null,null);
        level5[7][18] = new Cell(new Floor(), null,snake4);
        level5[7][19] = new Cell(new Floor(), null,fireTrap4);
        level5[7][20] = new Cell(new Floor(), null,snake5);
        level5[7][25] = new Cell(new Floor(), null,new Rock());
        level5[7][29] = new Cell(new Floor(), null,null);
        level5[7][32] = new Cell(new Floor(), null,null);
        level5[7][33] = new Cell(new Floor(), null,null);
        level5[7][34] = new Cell(new Floor(), null,null);
        level5[7][35] = new Cell(new Floor(), null,null);
        level5[7][36] = new Cell(new Floor(), null,null);
        level5[7][37] = new Cell(new Floor(), null,null);
        level5[7][38] = new Cell(new Floor(), null,null);

        level5[8][1] = new Cell(new Floor(), null,null);
        level5[8][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[8][4] = new Cell(new Floor(), null,fireTrap1);
        level5[8][5] = new Cell(new Floor(), null,null);
        level5[8][6] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[8][7] = new Cell(new Floor(), new Diamond(),snake1);
        level5[8][10] = new Cell(pressMechanism1.door, null,null);
        level5[8][14] = new Cell(new Floor(), new Diamond(),null);
        level5[8][15] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[8][16] = new Cell(new Floor(), null,snake3);
        level5[8][17] = new Cell(new Floor(), null,fireTrap3);
        level5[8][18] = new Cell(new Floor(), null,snake4);
        level5[8][19] = new Cell(new Floor(), null,fireTrap4);
        level5[8][20] = new Cell(new Floor(), null,snake5);
        level5[8][24] = new Cell(new Floor(), new Diamond(),null);
        level5[8][25] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[8][26] = new Cell(new Floor(), null,null);
        level5[8][27] = new Cell(new Floor(), null,null);
        level5[8][28] = new Cell(new Floor(), null,null);
        level5[8][29] = new Cell(new Floor(), null,new Rock());
        level5[8][32] = new Cell(new Floor(), null,null);
        level5[8][33] = new Cell(new Floor(), null,null);
        level5[8][34] = new Cell(new Floor(), null,null);
        level5[8][35] = new Cell(new Floor(), null,null);
        level5[8][36] = new Cell(new Floor(), null,null);
        level5[8][37] = new Cell(new Floor(), null,null);
        level5[8][38] = new Cell(pressMechanism2.pressPanel, null,null);

        level5[9][1] = new Cell(new Floor(), null,null);
        level5[9][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[9][5] = new Cell(new Floor(), null,new Rock(null));
        level5[9][6] = new Cell(new Floor(), null,new Rock(null));
        level5[9][10] = new Cell(new Floor(), new Chest(new PurpleDiamond(3),null,new SilverKey(),null,null),null);
        level5[9][11] = new Cell(new Floor(), new Chest(null,new RedDiamond(1), null, new GoldKey(),new Energy(50)),null);
        level5[9][14] = new Cell(new Floor(), null,new Rock());
        level5[9][15] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[9][16] = new Cell(new Floor(), null,snake3);
        level5[9][17] = new Cell(new Floor(), null,fireTrap3);
        level5[9][18] = new Cell(new Floor(), null,snake4);
        level5[9][20] = new Cell(new Floor(), null,snake5);
        level5[9][24] = new Cell(new Floor(), null,new Rock());
        level5[9][25] = new Cell(new Floor(), null,new Rock());
        level5[9][29] = new Cell(new Floor(), null,null);
        level5[9][32] = new Cell(new Floor(), null,null);
        level5[9][33] = new Cell(new Floor(), null,null);
        level5[9][34] = new Cell(new Floor(), null,null);
        level5[9][35] = new Cell(new Floor(), null,null);
        level5[9][36] = new Cell(new Floor(), null,null);

        level5[10][1] = new Cell(new BlockedDoor(), null,null);
        level5[10][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[10][4] = new Cell(new Floor(), null,fireTrap2);
        level5[10][5] = new Cell(new Floor(), null,null);
        level5[10][6] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[10][7] = new Cell(new Floor(), new Diamond(),snake2);
        level5[10][10] = new Cell(pressMechanism1.door, null,null);
        level5[10][16] = new Cell(new Floor(), null,snake3);
        level5[10][17] = new Cell(new Floor(), null,fireTrap3);
        level5[10][18] = new Cell(new Floor(), null,snake4);
        level5[10][20] = new Cell(new Floor(), null,snake5);
        level5[10][25] = new Cell(new Floor(), null,null);
        level5[10][26] = new Cell(new Floor(), null,null);
        level5[10][29] = new Cell(new Floor(), null,null);
        level5[10][32] = new Cell(new Floor(), null,null);
        level5[10][33] = new Cell(new Floor(), null,null);
        level5[10][34] = new Cell(new Floor(), null,null);
        level5[10][35] = new Cell(new Floor(), null,null);

        level5[11][1] = new Cell(new Floor(), null,null);
        level5[11][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[11][4] = new Cell(new Floor(), null,fireTrap2);
        level5[11][5] = new Cell(new Floor(), null,null);
        level5[11][6] = new Cell(new Floor(), null,null);
        level5[11][7] = new Cell(new Floor(), null,snake2);
        level5[11][8] = new Cell(new Floor(), null,null);
        level5[11][9] = new Cell(new Floor(), null,null);
        level5[11][10] = new Cell(new Floor(), null,null);
        level5[11][13] = new Cell(new Floor(), new Diamond(),null);
        level5[11][14] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[11][15] = new Cell(new Floor(), null,null);
        level5[11][16] = new Cell(new Floor(), null,snake3);
        level5[11][17] = new Cell(new Floor(), null,fireTrap3);
        level5[11][18] = new Cell(new Floor(), null,snake4);
        level5[11][20] = new Cell(new Floor(), null,snake5);
        level5[11][23] = new Cell(new Floor(), null,null);
        level5[11][24] = new Cell(new Floor(), null,null);
        level5[11][25] = new Cell(new Floor(), null,null);
        level5[11][26] = new Cell(new Floor(), new Diamond(),null);
        level5[11][27] = new Cell(new Floor(), new Diamond(),null);
        level5[11][28] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[11][29] = new Cell(new Floor(), null,null);
        level5[11][31] = new Cell(new Floor(), null,null);
        level5[11][32] = new Cell(new Floor(), null,null);
        level5[11][33] = new Cell(new Floor(), null,null);
        level5[11][34] = new Cell(new Floor(), null,null);

        level5[12][1] = new Cell(new Floor(), null,null);
        level5[12][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[12][4] = new Cell(new Floor(), null,fireTrap2);
        level5[12][7] = new Cell(new Floor(), null,snake2);
        level5[12][13] = new Cell(new Floor(), null,new Rock());
        level5[12][14] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[12][15] = new Cell(new Floor(), null,null);
        level5[12][16] = new Cell(new Floor(), null,snake3);
        level5[12][18] = new Cell(new Floor(), null,snake4);
        level5[12][20] = new Cell(new Floor(), null,snake5);
        level5[12][23] = new Cell(new Floor(), null,null);
        level5[12][24] = new Cell(new Floor(), null,null);
        level5[12][25] = new Cell(new Floor(), null,null);
        level5[12][26] = new Cell(new Floor(), new Diamond(),null);
        level5[12][27] = new Cell(new Floor(), new Diamond(),null);
        level5[12][29] = new Cell(new Floor(), null,null);
        level5[12][30] = new Cell(new Floor(), null,null);
        level5[12][31] = new Cell(new Floor(), null,null);
        level5[12][32] = new Cell(new Floor(), null,null);
        level5[12][33] = new Cell(new Floor(), null,null);

        level5[13][1] = new Cell(new Floor(), null,null);
        level5[13][3] = new Cell(new Floor(), null,null);
        level5[13][4] = new Cell(new Floor(), null,fireTrap2);
        level5[13][15] = new Cell(check2, null,null);
        level5[13][23] = new Cell(new Floor(), null,null);
        level5[13][24] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[13][25] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[13][26] = new Cell(new Floor(), new Diamond(),null);
        level5[13][29] = new Cell(new Floor(), null,null);
        level5[13][30] = new Cell(new Floor(), null,null);
        level5[13][31] = new Cell(new Floor(), null,null);
        level5[13][32] = new Cell(check3, null,null);

        level5[14][1] = new Cell(new Floor(), null,null);
        level5[14][3] = new Cell(check1, null,null);
        level5[14][15] = new Cell(doubleDoor.rightDoor, null,null);
        level5[14][23] = new Cell(new Floor(), null,null);
        level5[14][24] = new Cell(new Floor(), null,new Rock());
        level5[14][25] = new Cell(new Floor(), new Diamond(),null);
        level5[14][32] = new Cell(new Floor(), null,null);

        level5[15][1] = new Cell(new Floor(), null,null);
        level5[15][3] = new Cell(new DoorWithKeyhole().silver, null,null);
        level5[15][15] = new Cell(new DoorWithKeyhole().silver, null,null);
        level5[15][32] = new Cell(new DoorWithKeyhole().silver, null,null);


        level5[16][1] = new Cell(new Floor(), null,null);
        level5[16][2] = new Cell(new Floor(), null,fireTrap5);
        level5[16][3] = new Cell(new Floor(), null,snake6);
        level5[16][4] = new Cell(new Floor(), null,snake6);
        level5[16][5] = new Cell(new Floor(), null,snake6);
        level5[16][6] = new Cell(new Floor(), null,snake6);
        level5[16][7] = new Cell(new Floor(), null,snake6);
        level5[16][8] = new Cell(new Floor(), null,snake6);
        level5[16][9] = new Cell(new Floor(), null,fireTrap6);
        level5[16][10] = new Cell(new Floor(), null,snake7);
        level5[16][11] = new Cell(new Floor(), null,snake7);
        level5[16][12] = new Cell(new Floor(), null,snake7);
        level5[16][13] = new Cell(new Floor(), null,snake7);
        level5[16][14] = new Cell(new Floor(), null,snake7);
        level5[16][15] = new Cell(new Floor(), null,snake7);
        level5[16][16] = new Cell(new Floor(), null,snake7);
        level5[16][17] = new Cell(new Floor(), null,snake7);
        level5[16][18] = new Cell(new Floor(), null,snake7);
        level5[16][19] = new Cell(new Floor(), null,snake7);
        level5[16][20] = new Cell(new Floor(), null,snake7);
        level5[16][21] = new Cell(new Floor(), null,snake7);
        level5[16][22] = new Cell(new Floor(), null,fireTrap7);
        level5[16][23] = new Cell(new Floor(), null,null);
        level5[16][24] = new Cell(new Floor(), null,fireTrap8);
        level5[16][25] = new Cell(new Floor(), null,snake8);
        level5[16][26] = new Cell(new Floor(), null,snake8);
        level5[16][27] = new Cell(new Floor(), null,snake8);
        level5[16][28] = new Cell(new Floor(), null,snake8);
        level5[16][29] = new Cell(new Floor(), null,snake8);
        level5[16][30] = new Cell(new Floor(), null,snake8);
        level5[16][31] = new Cell(new Floor(), null,snake8);
        level5[16][32] = new Cell(new Floor(), null,snake8);
        level5[16][33] = new Cell(new Floor(), null,snake8);
        level5[16][34] = new Cell(new Floor(), null,snake8);
        level5[16][35] = new Cell(new Floor(), null,snake8);
        level5[16][36] = new Cell(new Floor(), null,snake8);
        level5[16][38] = new Cell(new Floor(), null,null);


        level5[17][1] = new Cell(new Floor(), null,null);
        level5[17][2] = new Cell(new Floor(), null,fireTrap5);
        level5[17][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][5] = new Cell(new Floor(), new Diamond(),null);
        level5[17][7] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][8] = new Cell(new Floor(), null,fireTrap9);
        level5[17][9] = new Cell(new Floor(), null,fireTrap6);
        level5[17][10] = new Cell(new Floor(), null,fireTrap10);
        level5[17][11] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][12] = new Cell(new Floor(), new Diamond(),null);
        level5[17][14] = new Cell(new Floor(), new Diamond(),null);
        level5[17][16] = new Cell(new Floor(), null,new Rock());
        level5[17][18] = new Cell(new Floor(), new Diamond(),null);
        level5[17][20] = new Cell(new Floor(), new Diamond(),null);
        level5[17][21] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][22] = new Cell(new Floor(), null,fireTrap7);
        level5[17][23] = new Cell(new Floor(), null,fireTrap11);
        level5[17][24] = new Cell(new Floor(), null,fireTrap8);
        level5[17][25] = new Cell(new Floor(), null,fireTrap12);
        level5[17][26] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][27] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][28] = new Cell(new Floor(), null,new Rock());
        level5[17][29] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][31] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][32] = new Cell(new Floor(), null,new Rock());
        level5[17][33] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[17][35] = new Cell(new Floor(), null,null);
        level5[17][36] = new Cell(new Floor(), null,null);
        level5[17][37] = new Cell(new Floor(), null,snake9);
        level5[17][38] = new Cell(new Floor(), null,null);

        level5[18][1] = new Cell(new Floor(), null,null);
        level5[18][2] = new Cell(new Floor(), null,fireTrap5);
        level5[18][8] = new Cell(new Floor(), null,fireTrap9);
        level5[18][9] = new Cell(new Floor(), null,fireTrap6);
        level5[18][10] = new Cell(new Floor(), null,fireTrap10);
        level5[18][11] = new Cell(new Floor(), new Chest(new PurpleDiamond(3),null,null,null,new Energy(30)),null);
        level5[18][21] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[18][22] = new Cell(new Floor(), null,fireTrap7);
        level5[18][23] = new Cell(new Floor(), null,fireTrap11);
        level5[18][24] = new Cell(new Floor(), null,fireTrap8);
        level5[18][25] = new Cell(new Floor(), null,fireTrap12);
        level5[18][26] = new Cell(new Floor(), new Diamond(),null);
        level5[18][28] = new Cell(new Floor(), new Chest(null,null,null,null, new Energy(50)),null);
        level5[18][30] = new Cell(new Floor(), new Diamond(),null);
        level5[18][32] = new Cell(new Floor(), new Chest(new PurpleDiamond(5), null, null, null, new Energy(30)),null);
        level5[18][34] = new Cell(new Floor(), new Diamond(),null);
        level5[18][36] = new Cell(new Floor(), null,null);
        level5[18][37] = new Cell(new Floor(), null,snake9);
        level5[18][38] = new Cell(new Floor(), new Chest(null,null, new SilverKey(),null,new Energy(30)),null);

        level5[19][1] = new Cell(new Floor(), null,null);
        level5[19][2] = new Cell(new Floor(), null,fireTrap5);
        level5[19][3] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][5] = new Cell(new Floor(), new Chest(new PurpleDiamond(5),null,null,null,new Energy(30)),null);
        level5[19][7] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][8] = new Cell(new Floor(), null,fireTrap9);
        level5[19][9] = new Cell(new Floor(), null,fireTrap6);
        level5[19][10] = new Cell(new Floor(), null,fireTrap10);
        level5[19][11] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][12] = new Cell(new Floor(), new Diamond(),null);
        level5[19][14] = new Cell(new Floor(), new Diamond(),null);
        level5[19][16] = new Cell(new Floor(), null,new Rock());
        level5[19][18] = new Cell(new Floor(), new Diamond(),null);
        level5[19][20] = new Cell(new Floor(), new Diamond(),null);
        level5[19][21] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][22] = new Cell(new Floor(), null,fireTrap7);
        level5[19][23] = new Cell(new Floor(), null,fireTrap11);
        level5[19][24] = new Cell(new Floor(), null,fireTrap8);
        level5[19][25] = new Cell(new Floor(), null,fireTrap12);
        level5[19][26] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][27] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][29] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][30] = new Cell(new Floor(), null,new Rock());
        level5[19][31] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][33] = new Cell(new Floor(), new Tumbleweed(),null);
        level5[19][34] = new Cell(new Floor(), null,null);
        level5[19][35] = new Cell(new Floor(), null,null);
        level5[19][36] = new Cell(new Floor(), null,null);
        level5[19][37] = new Cell(new Floor(), null,snake9);
        level5[19][38] = new Cell(new Floor(), null,null);




        level5[20][1] = new Cell(new Floor(),null,snake10);
        level5[20][2] = new Cell(new Floor(),null,snake10);
        level5[20][3] = new Cell(new Floor(),null,snake10);
        level5[20][4] = new Cell(new Floor(),null,snake10);
        level5[20][5] = new Cell(new Floor(),null,snake10);
        level5[20][6] = new Cell(new Floor(),null,snake10);
        level5[20][7] = new Cell(new Floor(),null,snake10);
        level5[20][8] = new Cell(new Floor(),null,fireTrap9);
        level5[20][9] = new Cell(new Floor(),null,null);
        level5[20][10] = new Cell(new Floor(),null,fireTrap10);
        level5[20][11] = new Cell(new Floor(),null,snake11);
        level5[20][12] = new Cell(new Floor(),null,snake11);
        level5[20][13] = new Cell(new Floor(),null,snake11);
        level5[20][14] = new Cell(new Floor(),null,snake11);
        level5[20][15] = new Cell(new Floor(),null,snake11);
        level5[20][16] = new Cell(new Floor(),null,snake11);
        level5[20][17] = new Cell(new Floor(),null,snake11);
        level5[20][18] = new Cell(new Floor(),null,snake11);
        level5[20][19] = new Cell(new Floor(),null,snake11);
        level5[20][20] = new Cell(new Floor(),null,snake11);
        level5[20][21] = new Cell(new Floor(),null,snake11);
        level5[20][22] = new Cell(new Floor(),null,snake11);
        level5[20][23] = new Cell(new Floor(),null,fireTrap11);
        level5[20][24] = new Cell(new Floor(),null,null);
        level5[20][25] = new Cell(new Floor(),null,fireTrap12);
        level5[20][26] = new Cell(new Floor(),null,snake12);
        level5[20][27] = new Cell(new Floor(),null,snake12);
        level5[20][28] = new Cell(new Floor(),null,snake12);
        level5[20][29] = new Cell(new Floor(),null,snake12);
        level5[20][30] = new Cell(new Floor(),null,snake12);
        level5[20][31] = new Cell(new Floor(),null,snake12);
        level5[20][32] = new Cell(new Floor(),null,snake12);
        level5[20][33] = new Cell(new Floor(),null,snake12);
        level5[20][34] = new Cell(new Floor(),null,snake12);
        level5[20][35] = new Cell(new Floor(),null,snake12);
        level5[20][36] = new Cell(new Floor(),null,snake12);
        //level5[20][36] = new Cell(new Floor(),new Chest(new PurpleDiamond(20), null,
                //new SilverKey(), null, null),null);//only for test
        level5[20][38] = new Cell(new Floor(),null,null);
        level5[21][1] = new Cell(new DoorWithKeyhole().gold,null,null);
        level5[21][4] = new Cell(new DoorWithKeyhole().silver,null,null);
        level5[21][16] = new Cell(new DiamondDoor(80, false),null,null);
        level5[21][36] = new Cell(new DoorWithKeyhole().silver,null,null);
        level5[22][1] = new Cell(new DoorWithKeyhole().gold,null,null);
        level5[22][4] = new Cell(check4,null,null);
        level5[22][16] = new Cell(new DoorWithKeyhole().silver,null,null);
        level5[22][36] = new Cell(check6,null,null);
        level5[23][1] = new Cell(new DoorWithKeyhole().gold,null,null);
        level5[23][4] = new Cell(new Floor(),null,null);
        level5[23][7] = new Cell(new Floor(),null,null);
        level5[23][8] = new Cell(new Floor(),null,null);
        level5[23][9] = new Cell(new Floor(),null,null);
        level5[23][10] = new Cell(new Floor(),null,new Rock());
        level5[23][16] = new Cell(check5,null,null);
        level5[23][24] = new Cell(new Floor(),null,scorpion1);
        level5[23][25] = new Cell(new Floor(),null,scorpion1);
        level5[23][26] = new Cell(new Floor(),null,scorpion1);
        level5[23][27] = new Cell(new Floor(),null,scorpion1);
        level5[23][28] = new Cell(new Floor(),null,scorpion1);
        level5[23][29] = new Cell(new Floor(),null,scorpion1);
        level5[23][30] = new Cell(new Floor(),null,scorpion1);
        level5[23][31] = new Cell(new Floor(),null,scorpion1);
        level5[23][32] = new Cell(new Floor(),null,scorpion2);
        level5[23][33] = new Cell(new Floor(),null,scorpion2);
        level5[23][34] = new Cell(new Floor(),null,scorpion2);
        level5[23][35] = new Cell(new Floor(),null,scorpion2);
        level5[23][36] = new Cell(new Floor(),null,scorpion2);
        level5[23][37] = new Cell(new Floor(),null,scorpion2);
        level5[24][1] = new Cell(new DoorWithKeyhole().gold,null,null);
        level5[24][4] = new Cell(new Floor(),null,null);
        level5[24][5] = new Cell(new Floor(),null,null);
        level5[24][6] = new Cell(new Floor(),null,null);
        level5[24][7] = new Cell(new Floor(),null,null);
        level5[24][8] = new Cell(new Floor(),null,null);
        level5[24][9] = new Cell(new Floor(),null,new Rock());
        level5[24][10] = new Cell(new Floor(),null,new Rock());
        level5[24][15] = new Cell(new Floor(),null,null);
        level5[24][16] = new Cell(new Floor(),null,null);
        level5[24][17] = new Cell(new Floor(),null,null);
        level5[24][18] = new Cell(new Floor(),null,null);
        level5[24][24] = new Cell(new Floor(),null,scorpion1);
        level5[24][31] = new Cell(new Floor(),null,scorpion1);
        level5[24][32] = new Cell(new Floor(),null,scorpion2);
        level5[24][37] = new Cell(new Floor(),null,null);
        level5[25][1] = new Cell(new DoorWithKeyhole().gold,null,null);
        level5[25][6] = new Cell(new Floor(),null,null);
        level5[25][7] = new Cell(new Floor(),null,null);
        level5[25][8] = new Cell(new Floor(),null,null);
        level5[25][9] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[25][10] = new Cell(new Floor(),null,null);
        level5[25][11] = new Cell(new Floor(),new Diamond(),null);
        level5[25][12] = new Cell(new Floor(),new Diamond(),null);
        level5[25][15] = new Cell(new Floor(),null,null);
        level5[25][16] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[25][17] = new Cell(new Floor(),null,null);
        level5[25][18] = new Cell(new Floor(),new Diamond(),null);
        level5[25][24] = new Cell(new Floor(),null,scorpion1);
        level5[25][26] = new Cell(new Floor(),null,null);
        level5[25][27] = new Cell(new Floor(),null,snake13);
        level5[25][28] = new Cell(new Floor(),null,null);
        level5[25][29] = new Cell(new Floor(),null,null);
        level5[25][30] = new Cell(new Floor(),null,null);
        level5[25][31] = new Cell(new Floor(),null,scorpion1);
        level5[25][32] = new Cell(new Floor(),null,scorpion2);
        level5[25][33] = new Cell(new Floor(),null,null);
        level5[25][34] = new Cell(new Floor(),null,snake14);
        level5[25][35] = new Cell(new Floor(),new Diamond(),null);
        level5[25][37] = new Cell(new Floor(),null,scorpion2);
        level5[26][1] = new Cell(new DoorWithKeyhole().gold,null,null);
        level5[26][5] = new Cell(new Floor(),null,null);
        level5[26][6] = new Cell(new Floor(),null,null);
        level5[26][7] = new Cell(new Floor(),null,null);
        level5[26][8] = new Cell(new Floor(),null,new Rock());
        level5[26][9] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[26][10] = new Cell(new Floor(),null,null);
        level5[26][11] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[26][12] = new Cell(new Floor(),null,null);
        level5[26][15] = new Cell(new Floor(),null,new Rock());
        level5[26][16] = new Cell(new Floor(),new Diamond(),null);
        level5[26][24] = new Cell(new Floor(),null,scorpion1);
        level5[26][26] = new Cell(new Floor(),null,null);
        level5[26][27] = new Cell(new Floor(),null,snake13);
        level5[26][31] = new Cell(new Floor(),null,scorpion1);
        level5[26][32] = new Cell(new Floor(),null,scorpion2);
        level5[26][33] = new Cell(new Floor(),null,null);
        level5[26][34] = new Cell(new Floor(),null,snake14);
        level5[26][35] = new Cell(new Floor(),new Diamond(),null);
        level5[26][37] = new Cell(new Floor(),null,scorpion2);
        level5[27][1] = new Cell(new DiamondDoor(90, true),null,null);
        level5[27][5] = new Cell(new Floor(),null,new Rock());
        level5[27][6] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][8] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][9] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][10] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][11] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][12] = new Cell(new Floor(),null,null);
        level5[27][15] = new Cell(new Floor(),new Diamond(),null);
        level5[27][16] = new Cell(new Floor(),new Diamond(),null);
        level5[27][18] = new Cell(new Floor(),null,null);
        level5[27][24] = new Cell(new Floor(),null,scorpion1);
        level5[27][26] = new Cell(new Floor(),null,null);
        level5[27][27] = new Cell(new Floor(),null,snake13);
        level5[27][28] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[27][29] = new Cell(new Floor(),new Chest(new PurpleDiamond(5), null,
                new SilverKey(), null, new Energy(20)),null);
        level5[27][31] = new Cell(new Floor(),null,scorpion1);
        level5[27][32] = new Cell(new Floor(),null,scorpion2);
        level5[27][34] = new Cell(new Floor(),null,snake14);
        level5[27][35] = new Cell(new Floor(),null,new Rock());
        level5[27][37] = new Cell(new Floor(),null,scorpion2);
        level5[28][1] = new Cell(new Floor(),null,null);
        level5[28][4] = new Cell(new Floor(),null,null);
        level5[28][5] = new Cell(new Floor(),null,null);
        level5[28][6] = new Cell(new Floor(),new Diamond(),null);
        level5[28][7] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[28][8] = new Cell(new Floor(),null,null);
        level5[28][9] = new Cell(new Floor(),null,null);
        level5[28][10] = new Cell(new Floor(),new Diamond(),null);
        level5[28][12] = new Cell(new Floor(),null,null);
        level5[28][15] = new Cell(new Floor(),null,new Rock());
        level5[28][18] = new Cell(new Floor(),null,null);
        level5[28][24] = new Cell(new Floor(),null,scorpion1);
        level5[28][26] = new Cell(new Floor(),null,null);
        level5[28][27] = new Cell(new Floor(),null,snake13);
        level5[28][28] = new Cell(new Floor(),null,new Rock());
        level5[28][29] = new Cell(new Floor(),new Diamond(),null);
        level5[28][31] = new Cell(new Floor(),null,scorpion1);
        level5[28][32] = new Cell(new Floor(),null,scorpion2);
        level5[28][34] = new Cell(new Floor(),null,snake14);
        level5[28][35] = new Cell(new Floor(),null,new Rock());
        level5[28][37] = new Cell(new Floor(),null,scorpion2);
        level5[29][1] = new Cell(new Floor(),null,null);
        level5[29][4] = new Cell(new Floor(),null,null);
        level5[29][5] = new Cell(new Floor(),null,null);
        level5[29][6] = new Cell(new Floor(),null,null);
        level5[29][7] = new Cell(new Floor(),new Diamond(),null);
        level5[29][9] = new Cell(new Floor(),null,null);
        level5[29][10] = new Cell(new Floor(),null,null);
        level5[29][11] = new Cell(new Floor(),null,null);
        level5[29][12] = new Cell(new Floor(),null,null);
        level5[29][18] = new Cell(new Floor(),new Diamond(),null);
        level5[29][24] = new Cell(new Floor(),null,scorpion1);
        level5[29][26] = new Cell(new Floor(),null,null);
        level5[29][27] = new Cell(new Floor(),null,snake13);
        level5[29][31] = new Cell(new Floor(),null,scorpion1);
        level5[29][32] = new Cell(new Floor(),null,scorpion2);
        level5[29][33] = new Cell(new Floor(),null,null);
        level5[29][34] = new Cell(new Floor(),null,snake14);
        level5[29][35] = new Cell(new Floor(),new Diamond(),null);
        level5[29][37] = new Cell(new Floor(),null,scorpion2);
        level5[30][1] = new Cell(new Floor(),null,null);
        level5[30][4] = new Cell(new Floor(),null,null);
        level5[30][5] = new Cell(new Floor(),null,null);
        level5[30][6] = new Cell(new Floor(),null,null);
        level5[30][7] = new Cell(new Floor(),null,null);
        level5[30][8] = new Cell(new Floor(),null,null);
        level5[30][9] = new Cell(new Floor(),null,null);
        level5[30][10] = new Cell(new Floor(),null,null);
        level5[30][11] = new Cell(new Floor(),new Diamond(),null);
        level5[30][17] = new Cell(new Floor(),null,null);
        level5[30][18] = new Cell(new Floor(),null,null);
        level5[30][20] = new Cell(new Floor(),new Chest(new PurpleDiamond(10), new RedDiamond(1),
                null, null, null),null);
        level5[30][24] = new Cell(new Floor(),null,scorpion1);
        level5[30][26] = new Cell(new Floor(),null,null);
        level5[30][27] = new Cell(new Floor(),null,snake13);
        level5[30][28] = new Cell(new Floor(),null,null);
        level5[30][29] = new Cell(new Floor(),null,null);
        level5[30][30] = new Cell(new Floor(),null,null);
        level5[30][31] = new Cell(new Floor(),null,scorpion1);
        level5[30][32] = new Cell(new Floor(),null,scorpion2);
        level5[30][33] = new Cell(new Floor(),null,null);
        level5[30][34] = new Cell(new Floor(),null,snake14);
        level5[30][35] = new Cell(new Floor(),new Diamond(),null);
        level5[30][37] = new Cell(new Floor(),null,scorpion2);
        level5[31][4] = new Cell(new Floor(),null,null);
        level5[31][5] = new Cell(new Floor(),null,null);
        level5[31][6] = new Cell(new Floor(),null,null);
        level5[31][7] = new Cell(new Floor(),null,null);
        level5[31][8] = new Cell(new Floor(),null,null);
        level5[31][9] = new Cell(new Floor(),null,null);
        level5[31][10] = new Cell(new Floor(),null,null);
        level5[31][11] = new Cell(new Floor(),null,null);
        level5[31][17] = new Cell(new Floor(),null,null);
        level5[31][18] = new Cell(new Floor(),null,new Rock());
        level5[31][20] = new Cell(new Floor(),new Chest(null, null,
                null, new GoldKey(), new Energy(20)),null);
        level5[31][24] = new Cell(new Floor(),null,scorpion1);
        level5[31][31] = new Cell(new Floor(),null,scorpion1);
        level5[31][32] = new Cell(new Floor(),null,scorpion2);
        level5[31][37] = new Cell(new Floor(),null,scorpion2);
        level5[32][4] = new Cell(new Floor(),null,null);
        level5[32][5] = new Cell(new Floor(),null,null);
        level5[32][6] = new Cell(new Floor(),null,null);
        level5[32][7] = new Cell(new Floor(),null,null);
        level5[32][8] = new Cell(new Floor(),null,null);
        level5[32][9] = new Cell(new Floor(),null,null);
        level5[32][10] = new Cell(new Floor(),null,null);
        level5[32][17] = new Cell(new Floor(),null,new Rock());
        level5[32][18] = new Cell(new Floor(),null,new Rock());
        level5[32][20] = new Cell(new Floor(),null,null);
        level5[32][21] = new Cell(new Floor(),null,null);
        level5[32][22] = new Cell(new Floor(),null,null);
        level5[32][24] = new Cell(new Floor(),null,scorpion1);
        level5[32][25] = new Cell(new Floor(),null,scorpion1);
        level5[32][26] = new Cell(new Floor(),null,scorpion1);
        level5[32][27] = new Cell(new Floor(),null,scorpion1);
        level5[32][28] = new Cell(new Floor(),null,scorpion1);
        level5[32][29] = new Cell(new Floor(),null,scorpion1);
        level5[32][30] = new Cell(new Floor(),null,scorpion1);
        level5[32][31] = new Cell(new Floor(),null,scorpion1);
        level5[32][32] = new Cell(new Floor(),null,scorpion2);
        level5[32][33] = new Cell(new Floor(),null,scorpion2);
        level5[32][34] = new Cell(new Floor(),null,scorpion2);
        level5[32][35] = new Cell(new Floor(),null,scorpion2);//check
        level5[32][36] = new Cell(new Floor(),null,scorpion2);
        level5[32][37] = new Cell(new Floor(),null,scorpion2);
        level5[33][4] = new Cell(new Floor(),null,null);
        level5[33][5] = new Cell(new Floor(),null,null);
        level5[33][6] = new Cell(new Floor(),null,null);
        level5[33][7] = new Cell(new Floor(),null,null);
        level5[33][8] = new Cell(new Floor(),null,null);
        level5[33][9] = new Cell(new Floor(),null,null);
        level5[33][10] = new Cell(pressMechanism3.pressPanel,null,null);
        level5[33][17] = new Cell(new Floor(),null,null);
        level5[33][18] = new Cell(new Floor(),null,new Rock());
        level5[33][22] = new Cell(new Floor(),null,null);
        level5[33][35] = new Cell(check7,null,null);
        level5[34][4] = new Cell(new Floor(),null,null);
        level5[34][5] = new Cell(new Floor(),null,null);
        level5[34][6] = new Cell(new Floor(),null,null);
        level5[34][7] = new Cell(new Floor(),null,null);
        level5[34][9] = new Cell(pressMechanism3.door,null,null);
        level5[34][17] = new Cell(new Floor(),null,null);
        level5[34][18] = new Cell(new Floor(),null,null);
        level5[34][19] = new Cell(new Floor(),null,null);
        level5[34][20] = new Cell(new Floor(),null,new Rock());
        level5[34][22] = new Cell(new Floor(),null,null);
        level5[34][32] = new Cell(new Floor(),null,new Rock());
        level5[34][33] = new Cell(new Floor(),new Diamond(),null);
        level5[34][34] = new Cell(new Floor(),new Diamond(),null);
        level5[34][35] = new Cell(new Floor(),new Diamond(),null);
        level5[34][36] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[34][37] = new Cell(new Floor(),null,null);
        level5[35][9] = new Cell(new Floor(),null,null);
        level5[35][18] = new Cell(new Floor(),null,null);
        level5[35][19] = new Cell(new Floor(),null,null);
        level5[35][20] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[35][21] = new Cell(new Floor(),null,new Rock());
        level5[35][22] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[35][29] = new Cell(new Floor(),new Chest(null, new RedDiamond(1),
                null, new GoldKey(), new Energy(20)),null);
        level5[35][36] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[35][37] = new Cell(new Floor(),null,null);
        level5[36][8] = new Cell(new Floor(),null,null);
        level5[36][9] = new Cell(new Floor(),null,null);
        level5[36][10] = new Cell(new Floor(),new Chest(new PurpleDiamond(6), null,
                null, null, null),null);
        level5[36][20] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[36][21] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[36][22] = new Cell(new Floor(),new Diamond(),null);
        level5[36][27] = new Cell(new Floor(),null,null);
        level5[36][29] = new Cell(new Floor(),null,null);
        level5[36][32] = new Cell(new Floor(),null,new Rock());
        level5[36][33] = new Cell(new Floor(),new Diamond(),null);
        level5[36][34] = new Cell(new Floor(),new Diamond(),null);
        level5[36][35] = new Cell(new Floor(),new Diamond(),null);
        level5[36][36] = new Cell(new Floor(),new Tumbleweed(),null);
        level5[36][37] = new Cell(new Floor(),null,null);
        level5[37][8] = new Cell(new Floor(),null,null);
        level5[37][9] = new Cell(new Floor(),new Chest(null, new RedDiamond(1),
                new SilverKey(), null, null),null);
        level5[37][21] = new Cell(new Floor(),null,null);
        level5[37][22] = new Cell(new Floor(),null,null);
        level5[37][27] = new Cell(new Floor(),null,new Rock());
        level5[37][36] = new Cell(new Floor(),null,null);
        level5[38][8] = new Cell(new Floor(),null,null);
        level5[38][9] = new Cell(new Floor(),null,null);
        level5[38][10] = new Cell(new Floor(),new Chest(new PurpleDiamond(4), null,
                null, new GoldKey(), new Energy(20)),null);
        level5[38][15] = new Cell(new Floor(),new Chest(new PurpleDiamond(7), new RedDiamond(1),
                null, null, null),null);
        level5[38][21] = new Cell(new Floor(),null,null);
        level5[38][22] = new Cell(new Floor(),null,null);
        level5[38][23] = new Cell(new Floor(),null,null);
        level5[38][27] = new Cell(new Floor(),null,null);
        level5[38][28] = new Cell(new Floor(),null,null);
        level5[38][29] = new Cell(new Floor(),null,null);
        level5[38][30] = new Cell(new Floor(),null,null);
        level5[38][33] = new Cell(new Floor(),new Diamond(),null);

        //SecretWalls

        level5[1][6] = new Cell(new SecretWall(Wall.Type.FLOOR),null,null);
        level5[1][7] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[1][11] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[1][12] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[1][13] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[1][14] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[1][15] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);

        level5[2][7] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[2][10] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[2][11] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[2][15] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);

        level5[3][7] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[3][8] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[3][10] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[3][13] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[3][14] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[3][15] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);

        level5[4][8] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[4][9] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[4][10] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[4][12] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[4][13] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);

        level5[5][12] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);

        level5[6][12] = new Cell(new SecretWall(Wall.Type.SECRET),null,null);
        level5[6][13] = new Cell(new SecretWall(Wall.Type.CENTER),null,null);
        level5[7][13] = new Cell(new SecretWall(Wall.Type.RIGHT_LOWER_BRICK),null,null);
        level5[8][13] = new Cell(new SecretWall(Wall.Type.CEILING),null,null);



        level5[30][15] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level5[30][16] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[31][15] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[32][15] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[33][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level5[33][15] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[34][12] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[34][13] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[34][14] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level5[35][12] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[36][12] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level5[36][13] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);
        level5[37][13] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[37][14] = new Cell(new SecretWall(new ImageIcon("mapImages/wall.png").getImage()),null,null);
        level5[37][15] = new Cell(new SecretWall(new ImageIcon("mapImages/secretWall.png").getImage()),null,null);

        //BreakableWalls
        level5[26][17] = new Cell(new BreakableWall(26,17),null,null);
        level5[26][18] = new Cell(new BreakableWall(26,18),null,null);
        level5[27][17] = new Cell(new BreakableWall(27,17),null,null);
        level5[28][16] = new Cell(new BreakableWall(28,16),null,null);
        level5[28][17] = new Cell(new BreakableWall(28,17),null,null);
        level5[37][28] = new Cell(new BreakableWall(37,28),null,null);
        level5[37][29] = new Cell(new BreakableWall(27,29),null,null);
        level5[38][31] = new Cell(new BreakableWall(38,31),null,null);
        level5[38][32] = new Cell(new BreakableWall(38,32),null,null);
        level5[38][34] = new Cell(new BreakableWall(38,34),null,null);
        level5[38][35] = new Cell(new BreakableWall(38,35),null,null);
        level5[38][36] = new Cell(new BreakableWall(38,36),null,null);

        Util.setWalls(level5);

        for (int i = 0; i < level5.length; i++) {
            for (int j = 0; j < level5[i].length; j++) {
                if(level5[i][j]==null){
                    level5[i][j] = new Cell(new Wall(),null,null);
                }
            }
        }

        Checkpoint[] checkpoints = {check1, check2,check3,check4,check5,check6,check7};
        for (Checkpoint checkpoint: checkpoints){
            checkpoint.setInitialMatrix(level5);
        }

        this.level5 = new Level(level5,3,1,11,1, 200, 5);
        this.level5.setCheckpoints(checkpoints);
        this.level5.setAdditionalImages(additionalImages);
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




}
