import maps.Cell;
import maps.Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Game extends JFrame {

    private JScrollPane scrollPane;
    private PlayPanel playPanel;
    private JPanel upper;

    public Game(){
        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);

        init();
        add(upper, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
    }

    private void init() {
        upper = new JPanel();
        upper.setPreferredSize(new Dimension(700,100));

        playPanel = new PlayPanel();

        scrollPane = new JScrollPane(playPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(createEmptyBorder());
    }

    public static void main(String[] args) {
        Game game = new Game();
        JScrollBar vertical = game.scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

//        Timer t = new Timer(20, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                game.playPanel.repaint();
//            }
//        });
//        Boy boy = new Boy(game.playPanel, t, 200, 200);
//        game.addKeyListener(boy);
//        boy.start();
//        game.playPanel.add(boy);
//        game.getContentPane().add(game.playPanel);
//        t.start();
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
