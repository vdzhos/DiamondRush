import javax.swing.*;
import java.awt.*;

public class PauseMenuDialog extends JDialog {

    private JFrame f;

    private Image backgroundImage = new ImageIcon("pauseMenu/pauseBackCut1.png").getImage();

    public PauseMenuDialog(JFrame frame) {
        super(frame, "", true);
        f = frame;
        setLocation(frame.getX()+Values.PAUSE_MENU_SHIFT_X, frame.getY()+Values.PAUSE_MENU_SHIFT_Y);
        setSize(Values.PAUSE_MENU_WIDTH,Values.PAUSE_MENU_LENGTH);
        setUndecorated(true);
    }

    public void paint(Graphics g){
        g.setColor(new Color(239,209,137));
        g.fillRect(0,0,Values.PAUSE_MENU_WIDTH,Values.PAUSE_MENU_LENGTH);
        g.drawImage(backgroundImage,0,0,Values.PAUSE_MENU_WIDTH,Values.PAUSE_MENU_LENGTH,null);

    }



    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setSize(700,800);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PauseMenuDialog p = new PauseMenuDialog(f);

//        StatusBarPanel statusBarPanel = new StatusBarPanel();
//        f.add(statusBarPanel);
        f.setVisible(true);
        p.setVisible(true);
//        String[] a = Toolkit.getDefaultToolkit().getFontList();
//        for(String s:a){
//            System.out.println(s);
//        }
//        Timer t = new Timer(1000, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                statusBarPanel.setCurrentEnergyLevel(statusBarPanel.getCurrentEnergyLevel()-5);
//                statusBarPanel.setCurrentNumberOfGoldKeys(statusBarPanel.getCurrentNumberOfGoldKeys()+1);
//            }
//        });
//        t.start();
    }

}


