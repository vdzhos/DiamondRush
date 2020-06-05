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

    /**
     * constructor with parameters
     * @param path
     */
    public AnimatableImage(String path){
        File file = new File(path);
        name = file.getName().split("\\.")[0];
        this.image = new ImageIcon(path).getImage();
    }

    /**
     * animates buttons
     * @param component
     */
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

    /**
     * animates buttons
     * @param component
     */
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

    /**
     * animates buttons
     * @param component
     */
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

    /**
     * animates buttons
     * @param component
     */
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

    /**
     * animates buttons
     * @param component
     */
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

    /**
     * animates buttons
     * @param component
     */
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

    /**
     * sets new image to the object
     * @param path
     */
    public void setImage(String path){
        File file = new File(path);
        name = file.getName().split("\\.")[0];
        this.image = new ImageIcon(path).getImage();
    }



}
