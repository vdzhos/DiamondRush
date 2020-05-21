import maps.Cell;
import maps.Maps;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Game extends JFrame {

    private JScrollPane scrollPane;
    private JPanel panel;
    private JPanel upper;

    public Game(){
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(new BorderLayout());

        init();
        add(upper, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
    }

    private void init() {

        upper = new JPanel();
        upper.setPreferredSize(new Dimension(700,100));

        panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(2800, 1540));

        Maps maps = new Maps();
        Cell[][] level1 = maps.getLevel1();
        for (int i = 0; i < level1.length; i++) {
            for (int j = 0; j < level1[i].length; j++) {
                panel.add(level1[i][j].getBlock().getLabel()).setBounds(i*70,j*70,70,70);
            }
        }

        scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(createEmptyBorder());
    }

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                Game game = new Game();
//                JPanel panel = new JPanel();
//                Boy boy = new Boy();
//                game.addKeyListener(boy);
//                boy.start();
//                panel.add(boy);
//                game.getContentPane().add(panel);
//                game.setVisible(true);
//            }
//        });
        Game game = new Game();
        JScrollBar vertical = game.scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
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
