import javax.swing.*;

public class Game extends JFrame {

    public Game(){
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH - 20);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
                JPanel panel = new JPanel();
                Boy boy = new Boy();
                game.addKeyListener(boy);
                boy.start();
                panel.add(boy);
                game.getContentPane().add(panel);
                game.setVisible(true);
            }
        });
    }

}
