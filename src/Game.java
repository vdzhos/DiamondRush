import javax.swing.*;
import java.awt.event.ActionEvent;

public class Game extends JFrame {

    public Game(){
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
                JPanel panel = new JPanel();
                Timer t = new Timer(20, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.repaint();
                    }
                });
                Boy boy = new Boy(t);
                game.addKeyListener(boy);
                boy.start();
                panel.add(boy);
                game.getContentPane().add(panel);
                game.setVisible(true);
            }
        });
    }*/

}
