import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AnimatableImage {

    public Image image;
    public String name;

    public AnimatableImage(String path){
        File file = new File(path);
        name = file.getName().split("\\.")[0];
        this.image = new ImageIcon(path).getImage();
    }



}
