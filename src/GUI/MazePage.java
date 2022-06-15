package GUI;

import GUI.Components.CellButton;
import MazeRelated.CreateMaze;
import MazeRelated.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazePage extends JFrame {
    // Background color for the MazePage
    public Color bgColor = new Color(0xFFFCF2);

    private static Maze maze;
    public MazePage(int rows, int cols, boolean isBlankMaze) {

        setTitle("Maze");
        setMinimumSize(new Dimension(250, 250));
        setLayout(new BorderLayout(5, 5));

        // If the user choose auto-generation, run the algorithm to generate a maze
        if (!isBlankMaze) {
            maze = CreateMaze.autoGenMaze(new Maze(rows, cols));
        }
        else {
            maze = new Maze(rows, cols);
        }

        JPanel pnl2 = new JPanel();
        pnl2.setLayout(null);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                pnl2.add(new CellButton(row, col, maze));

        getContentPane().add(pnl2);
        pack();
        setLocation(new Point(40, 40));
        setVisible(true);
    }

    public static Maze getMaze() {
        return maze;
    }
}
