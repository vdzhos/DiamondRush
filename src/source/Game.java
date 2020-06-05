//package source;
//
//
//
//import javax.swing.*;
//import java.applet.AudioClip;
//import java.awt.*;
//
//
//import static javax.swing.BorderFactory.createEmptyBorder;
//
//public class Game extends JFrame {
//
//    private JScrollPane scrollPane;
//    private PlayPanel playPanel;
//    private JPanel upper;
//
//    public Game(){
//        setSize(Values.MAIN_WINDOW_WIDTH, Values.MAIN_WINDOW_LENGTH);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        getContentPane().setLayout(new BorderLayout());
//        setResizable(false);
//
//        init();
//        add(upper, BorderLayout.NORTH);
//        add(playPanel, BorderLayout.CENTER);
//        revalidate();
//    }
//
//    private void init() {
//        upper = new StatusBarPanel(new GameFrame());
//        upper.setPreferredSize(new Dimension(700,100));
//
////        Boy boy = new Boy(0, 0);
//        playPanel = new PlayPanel(1);
//        addKeyListener(playPanel);
//
//        scrollPane = new JScrollPane(playPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setBorder(createEmptyBorder());
//    }
//    public static void main(String[] args) {
//        Game game = new Game();
//        JScrollBar vertical = game.scrollPane.getVerticalScrollBar();
//        vertical.setValue(vertical.getMaximum());
//
//    }
//
//}
