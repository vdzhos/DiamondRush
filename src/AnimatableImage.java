import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class AnimatableImage {

    public Image image;
    public String name;

    public AnimatableImage(String path){
        File file = new File(path);
        name = file.getName().split("\\.")[0];
        this.image = new ImageIcon(path).getImage();
    }

    public void animate(JComponent component){
        image = new ImageIcon("statusBar/"+name+"Shade.png").getImage();
        component.repaint();
        Timer t = new Timer(150, null);
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



}
