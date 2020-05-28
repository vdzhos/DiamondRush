package source;

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

}
