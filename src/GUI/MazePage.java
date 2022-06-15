package GUI;

import GUI.Components.CellButton;
import MazeRelated.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazePage extends JFrame {
    // Background color for the MazePage
    public Color bgColor = new Color(0xFFFCF2);

    private static Maze maze;
    public MazePage(int rows, int cols) {

        setTitle("Maze");
        setMinimumSize(new Dimension(250, 250));
        setLayout(new BorderLayout(5, 5));

        maze = new Maze(rows, cols);
        // Testing key binding
        JPanel pnl = new JPanel();
        pnl.setBackground(bgColor);
        JButton btn1 = new JButton("Button 1");
        btn1.setFocusPainted(false);
        btn1.setBorder(BorderFactory.createMatteBorder(2,2,2,0, Color.BLACK));
        btn1.addActionListener(e -> btn1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    btn1.setBorder(BorderFactory.createMatteBorder(0,0,0,2, Color.BLACK));
            }
        }));
        pnl.add(btn1);

//        getContentPane().add(pnl);
        // End of key binding test
        JPanel pnl2 = new JPanel();
        pnl2.setLayout(null);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                pnl2.add(new CellButton(row, col));

        getContentPane().add(pnl2);
        pack();
        setLocation(new Point(40, 40));
        setVisible(true);
    }

    public static Maze getMaze() {
        return maze;
    }
}
