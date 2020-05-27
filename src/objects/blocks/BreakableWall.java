package objects.blocks;

import maps.Cell;
import source.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BreakableWall implements Block{

    private int x;
    private int y;

    private Image image = new ImageIcon("mapImages/breakableWall.png").getImage();

    public BreakableWall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean pass() {
        return false;
    }

    public void breakWall(Cell[][] matrix, PlayPanel panel){
        Cell[][] map = matrix;
        ArrayList<Cell> all = new ArrayList<>();
        all.add(map[x][y]);
        int newlyAdded = 1;
        all.addAll(otherBreakablesCheck(x,y,map,all));
        do{
            int counter = 0;
            for (int i = all.size() - newlyAdded; i < all.size() ; i++) {
                BreakableWall bw = (BreakableWall) all.get(i).getBlock();
                ArrayList<Cell> toBeAdded = otherBreakablesCheck(bw.x,bw.y,map,all);
                all.addAll(toBeAdded);
                counter+=toBeAdded.size();
            }
            newlyAdded = counter;
        }while (newlyAdded!=0);
        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(all.size()>0){
                    all.get(0).setBlock(new Floor());
                    panel.repaint();
                    panel.disappearFromCell(x, y);
                    all.remove(0);
                }else{
                    Timer t = (Timer)e.getSource();
                    t.stop();
                }
            }
        });
        timer.start();
    }

    @Override
    public void paintObject(Graphics2D g2, int x, int y) {
        g2.drawImage(image,x,y,70,70,null);
    }

    private ArrayList<Cell> otherBreakablesCheck(int x, int y, Cell[][] map, ArrayList<Cell> previous){
        ArrayList<Cell> result = new ArrayList<>();
        Point[] points = {new Point(x,y+1), new Point(x-1,y),new Point(x,y-1),new Point(x+1,y)};

        for (int i = 0; i < points.length; i++) {
            Cell cell = map[(int)points[i].getX()][(int)points[i].getY()];
            if(cell!=null && cell.getBlock() instanceof BreakableWall){
                if(!previous.contains(cell)){
                    result.add(cell);
//                    result.addAll(otherBreakablesCheck((int)points[i].getX(),(int)points[i].getY(),map,result));
                }
            }
        }
        return result;
    }
}
