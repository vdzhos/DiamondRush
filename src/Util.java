import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Util {

    public static Font getFont(String fileName, float size){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fileName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }

    /**
     * waits certain time and then performs the action given in ActionListener
     * @param milliseconds
     * @param actionListener
     */
    public static void wait(int milliseconds, ActionListener actionListener){
        Timer t1 = new Timer(milliseconds, actionListener);
        t1.start();
        Timer t2 = new Timer(milliseconds, null);
        t2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.stop();
                t2.stop();
            }
        });
        t2.start();
    }
}
