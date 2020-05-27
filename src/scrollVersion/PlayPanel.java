//package scrollVersion;
//
//import maps.Cell;
//import maps.Level;
//import maps.Maps;
//import scrollVersion.Boy;
//import source.Values;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class PlayPanel extends JPanel implements KeyListener {
//
//    private Boy boy;
//    private JPanel panel;
//    private Maps maps;
//
//    private int mapX;
//    private int mapY;
//
//    private int positionOnScreenX;
//    private int positionOnScreenY;
//
//    private int positionOnMapX;
//    private int positionOnMapY;
//
//    private boolean stonesAreInited = false;
//
//
//    // map parameters
//    int mapWidth;
//    int mapHeight;
//
//    int panelWidth = 700;
//    int panelHeight = 700;
//
//
//    private boolean mapMovesToRight;
//    private boolean boyMovesToRight;
//
//    private boolean mapMovesToLeft;
//    private boolean boyMovesToLeft;
//
//    private boolean mapMovesUp;
//    private boolean boyMovesUp;
//
//    private boolean mapMovesDown;
//    private boolean boyMovesDown;
//
//
//    private Level currentLevel;
//    private Cell[][] levelMatrix;
//
//    private JScrollPane scrollPane;
//    private JScrollBar vertical;
//    private JScrollBar horisontal;
//
//    private boolean running = true;
//
//
//    public PlayPanel(Boy boy, int currentLevel) {
//        panel = this;
//        panel.setLayout(null);
//        setPreferredSize(new Dimension(2800, 1540));
//        this.boy = boy;
//        maps = new Maps();
//        initBars();
//        initLevel(currentLevel);
//        calculateInitialValuesOfMap();
//    }
//
//    private void initBars() {
//        scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//
//        vertical = scrollPane.getVerticalScrollBar();
//        horisontal = scrollPane.getHorizontalScrollBar();
//    }
//
//    private void initLevel(int currentLevel) {
//        switch (currentLevel){
//            case 1:
//                this.currentLevel = maps.getLevel1();
//                break;
//            case 2:
//                this.currentLevel = maps.getLevel2();
//                break;
//            case 3:
//                this.currentLevel = maps.getLevel3();
//                break;
//            case 4:
//                this.currentLevel = maps.getLevel4();
//                break;
//            case 5:
//                this.currentLevel = maps.getLevel5();
//                break;
//        }
//        levelMatrix = this.currentLevel.getMatrix();
//    }
//
//    private void calculateInitialValuesOfMap() {
//
//        mapWidth = currentLevel.getMatrix().length* Values.CELL_SIZE;
//        mapHeight = currentLevel.getMatrix()[0].length* Values.CELL_SIZE;
//
//        positionOnScreenX = currentLevel.getPositionOnScreenX();
//        positionOnScreenY = currentLevel.getPositionOnScreenY();
//
//        positionOnMapX = currentLevel.getPositionOnMapX();
//        positionOnMapY = currentLevel.getPositionOnMapY();
//
//        boy.x = positionOnMapX* Values.CELL_SIZE;
//        boy.y = positionOnMapY* Values.CELL_SIZE;
//
//        boy.screenX = positionOnScreenX* Values.CELL_SIZE;
//        boy.screenY = positionOnScreenY* Values.CELL_SIZE;
//        System.out.println(boy.screenX + " " + boy.screenY);
//    }
//
//    int i = 0;
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (i == 0){
//            horisontal.setValue(0);
//            vertical.setValue((positionOnMapY -positionOnScreenY)*70);
//            i = 1;
//        }
//        Graphics2D g2 = (Graphics2D) g;
//        for (int i = 0; i < levelMatrix.length; i++) {
//            for (int j = 0; j < levelMatrix[i].length; j++) {
//                levelMatrix[i][j].getBlock().paintObject(g2,i*70,j*70);
//            }
//        }
//        for (int i = 0; i < levelMatrix.length; i++) {
//            for (int j = 0; j < levelMatrix[i].length; j++) {
//                if (levelMatrix[i][j].getTrapObject() != null) {
//                    if (levelMatrix[i][j].getTrapObject() instanceof Rock){
//                        if (!stonesAreInited) ((Rock)levelMatrix[i][j].getTrapObject()).initVars(this, maps, i, j);
//                        levelMatrix[i][j].getTrapObject().paintObject(g2, mapX, mapY);
//                    }
//                    else{
//                        JLabel label = levelMatrix[i][j].getTrapObject().getLabel();
//                        if ((label != null) && (label.getParent() != panel)) {
//                            Dimension size = label.getPreferredSize();
//                            label.setBounds(i*70,j*70, size.width, size.height);
//                            add(label);
//                        } else if (label != null){
//                            Dimension size = label.getPreferredSize();
//                            label.setBounds((i-1)*70,j*70, size.width, size.height);
//                            //revalidate();
//                        }
//                    }
//                }
//            }
//        }
//        g2.drawImage(boy.currentPicture, boy.x, boy.y, boy.width, boy.height, null);
//        stonesAreInited = true;
//        //System.out.println(boy.isMoving);
//    }
//
//
//    private void setMovementRight() {
//        if ((this.getX() != 0 && boy.screenX < 350) || (this.getX() == 0 || this.getX() == -(mapWidth - panelWidth))) {
//            boyMovesToRight = true;
//            mapMovesToRight = false;
//        }
//        if (boy.screenX == 350 && this.getX() != -(mapWidth - panelWidth)) {
//            boyMovesToRight = false;
//            mapMovesToRight = true;
//        }
//    }
//
//    private void setMovementLeft() {
//        if (this.getX() == 0 || (boy.screenX > 280)) {
//            mapMovesToLeft = false;
//            boyMovesToLeft = true;
//        }
//        if (boy.screenX == 280 && this.getX() != 0) {
//            mapMovesToLeft = true;
//            boyMovesToLeft = false;
//        }
//    }
//
//    private void setMovementUp() {
//        if ((this.getY() == 0) || (this.getY() != 0 && boy.screenY > 140)) {
//            mapMovesUp = false;
//            boyMovesUp = true;
//        }
//        System.out.println((this.getY() == 0) || (this.getY() != 0 && boy.screenY > 140));
//        System.out.println(boy.screenY);
//        if (this.getY() != 0 && boy.screenY == 140) {
//            mapMovesUp = true;
//            boyMovesUp = false;
//        }
//        System.out.println(this.getY() != 0 && boy.screenY == 140);
//    }
//
//    private void setMovementDown() {
//        if ((this.getY() == -(mapHeight - panelHeight)) || (this.getY() != -(mapHeight - panelHeight) && boy.screenY < 350)) {
//            mapMovesDown = false;
//            boyMovesDown = true;
//        }
//        if (this.getY() != -(mapHeight - panelHeight) && boy.screenY == 350) {
//            mapMovesDown = true;
//            boyMovesDown = false;
//        }
//    }
//
//
//    private void moveMapToRight(){
////        boy.screenX += Values.CELL_SIZE/7;
//        horisontal.setValue(horisontal.getValue()+ Values.CELL_SIZE/7);
//    }
//
//    private void moveMapToLeft(){
////        boy.screenX -= Values.CELL_SIZE/7;
//        horisontal.setValue(horisontal.getValue()- Values.CELL_SIZE/7);
//    }
//
//    private void moveMapUp(){
////        boy.screenY -= Values.CELL_SIZE/7;
//        vertical.setValue(vertical.getValue()- Values.CELL_SIZE/7);
//    }
//
//    private void moveMapDown(){
////        boy.screenY += Values.CELL_SIZE/7;
//        vertical.setValue(vertical.getValue()+ Values.CELL_SIZE/7);
//    }
//
//
//
//    private void moveBoy(){
//        if (running) {
//            Timer t = new Timer(100, null);
//            t.addActionListener(new AbstractAction() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (boy.whatMove == 1 && boyMovesUp) boy.moveUp();
//                    else if (boy.whatMove == 1 && mapMovesUp) {
//                        boy.moveUpAnimation();
//                        moveMapUp();
//                    } else if (boy.whatMove == 2 && boyMovesDown) boy.moveDown();
//                    else if (boy.whatMove == 2 && mapMovesDown) {
//                        boy.moveDownAnimation();
//                        moveMapDown();
//                    } else if (boy.whatMove == 3 && boyMovesToLeft) boy.moveLeft();
//                    else if (boy.whatMove == 3 && mapMovesToLeft) {
//                        boy.moveLeftAnimation();
//                        moveMapToLeft();
//                    } else if (boy.whatMove == 4 && boyMovesToRight) boy.moveRight();
//                    else if (boy.whatMove == 4 && mapMovesToRight) {
//                        boy.moveRightAnimation();
//                        moveMapToRight();
//                    } else if (boy.whatMove == 5) boy.shoveLeftAndMove();
//                    else if (boy.whatMove == 6) boy.shoveLeftAndStand();
//                    else if (boy.whatMove == 7) boy.shoveRightAndMove();
//                    else if (boy.whatMove == 8) boy.shoveRightAndStand();
//                    else if (boy.whatMove == 9) boy.findInChest();
//                    else if (boy.whatMove == 10) boy.holdAStone();
//                    else if (boy.whatMove == 11) boy.attackUp();
//                    else if (boy.whatMove == 12) boy.attackDown();
//                    else if (boy.whatMove == 13) boy.attackLeft();
//                    else if (boy.whatMove == 14) boy.attackRight();
//                    else if (boy.whatMove == 15) boy.openWithGoldKeyLeft();
//                    else if (boy.whatMove == 16) boy.openWithGoldKeyRight();
//                    else if (boy.whatMove == 17) boy.openWithSilverKeyLeft();
//                    else if (boy.whatMove == 18) boy.openWithSilverKeyRight();
////                    if (boyMovesToRight||boyMovesToLeft||boyMovesDown||boyMovesUp)
//                        repaint();
//                    if (boy.i == 7) {
//                        boy.i = 0;
//                        boy.isMoving = false;
//                        t.stop();
//                    }
//                }
//            });
//            t.start();
//        }
//    }
//
//
//    private boolean isAllowedUp(){
//        return boy.screenY != 0;
//    }
//
//    private boolean isAllowedDown(){
//        return boy.screenY != panelHeight- boy.height;
//    }
//
//    private boolean isAllowedRight(){
//        return boy.screenX != panelWidth- boy.width;
//    }
//
//    private boolean isAllowedLeft(){
//        return boy.screenX != 0;
//    }
//
//
//
//
////    private void moveBoy(){
////        Timer t = new Timer(100, null);
////        t.addActionListener(new AbstractAction() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                if (boy.whatMove == 1) boy.moveUp();
////                else if (boy.whatMove == 2) boy.moveDown();
////                else if (boy.whatMove == 3) boy.moveLeft();
////                else if (boy.whatMove == 4) boy.moveRight();
////                else if (boy.whatMove == 5) boy.shoveLeftAndMove();
////                else if (boy.whatMove == 6) boy.shoveLeftAndStand();
////                else if (boy.whatMove == 7) boy.shoveRightAndMove();
////                else if (boy.whatMove == 8) boy.shoveRightAndStand();
////                else if (boy.whatMove == 9) boy.findInChest();
////                else if (boy.whatMove == 10) boy.holdAStone();
////                else if (boy.whatMove == 11) boy.attackUp();
////                else if (boy.whatMove == 12) boy.attackDown();
////                else if (boy.whatMove == 13) boy.attackLeft();
////                else if (boy.whatMove == 14) boy.attackRight();
////                else if (boy.whatMove == 15) boy.openWithGoldKeyLeft();
////                else if (boy.whatMove == 16) boy.openWithGoldKeyRight();
////                else if (boy.whatMove == 17) boy.openWithSilverKeyLeft();
////                else if (boy.whatMove == 18) boy.openWithSilverKeyRight();
////                repaint();
////                if (boy.i == 7){
////                    boy.i = 0;
////                    boy.isMoving = false;
////                    t.stop();
////                }
////            }
////        });
////        t.start();
////    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if ((e.getKeyCode() == KeyEvent.VK_UP) && (boy.isMoving == false) && isAllowedUp()) {
//            setMovementUp();
//            boy.whatMove = 1;
//            boy.isMoving = true;
//            moveBoy();
//        }
//        if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (boy.isMoving == false) && isAllowedDown()) {
//            setMovementDown();
//            boy.whatMove = 2;
//            boy.isMoving = true;
//            moveBoy();
//        }
//        if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (boy.isMoving == false) && isAllowedLeft()) {
//            setMovementLeft();
//            boy.whatMove = 3;
//            //if stone is left boy.whatMove = 5;
//            //if wall is left boy.whatMove = 6;
//            boy.isMoving = true;
//            moveBoy();
//        }
//        if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (boy.isMoving == false) && isAllowedRight()) {
//            setMovementRight();
//            boy.whatMove = 4;
//            //if stone is right boy.whatMove = 7;
//            //if wall is right boy.whatMove = 8;
//            boy.isMoving = true;
//            moveBoy();
//        }
//        if ((e.getKeyCode() == KeyEvent.VK_SPACE) && (boy.isMoving == false)) {
//            if (boy.currentPicture == boy.walkUp2) boy.whatMove = 11;
//            else if (boy.currentPicture == boy.standClear) boy.whatMove = 12;
//            else if ((boy.currentPicture == boy.standLeft)
//                    || (boy.currentPicture == boy.walkLeft6)) boy.whatMove = 13;
//            else if ((boy.currentPicture == boy.standRight)
//                    || (boy.currentPicture == boy.walkRight6)) boy.whatMove = 14;
//            boy.isMoving = true;
//            moveBoy();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//
//    public JScrollPane getScrollPane() {
//        return scrollPane;
//    }
//
//    public Level getCurrentLevel() {
//        return currentLevel;
//    }
//
//    public void setRunning(boolean running) {
//        this.running = running;
//    }
//}
//
//
