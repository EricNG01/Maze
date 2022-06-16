package GUI;

import MazeRelated.CreateMaze;

import javax.swing.*;
import java.awt.*;

/**
 * It is the maze info page class. Users can read the important data about the current maze like row and col number, dead end percentage etc.
 */
public class MazeInfoPage extends JFrame {
    Dimension windowSize = new Dimension(250, 500);
    // Background color for the panels
    public Color bgColor = new Color(0xFFFCF2);
    // Border color for the panels
    public Color borderColor = new Color(0xCCC5B9);

    /**
     * This is the constructor of the maze info page.
     * @param rows the height of the maze
     * @param cols the width of the maze
     */
    public MazeInfoPage(int rows, int cols) {
        setTitle("Maze Information");
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);

        JPanel mainPnl = new JPanel();
        mainPnl.setBackground(bgColor);
        mainPnl.setBorder(BorderFactory.createLineBorder(borderColor, 2, true));
        mainPnl.setLayout(null);

        JLabel rowsLabel = new JLabel("Rows: " + rows);
        JLabel colsLabel = new JLabel("Columns: " + cols);
        JLabel deadEnd = new JLabel("Dead end percentage: " + CreateMaze.deadEndPercentage(MazePage.getMaze()) + "%");

        rowsLabel.setBounds(10, 20, 230, 50);
        colsLabel.setBounds(10, 90, 230, 50);
        deadEnd.setBounds(10, 160, 230, 50);
        mainPnl.add(rowsLabel);
        mainPnl.add(colsLabel);
        mainPnl.add(deadEnd);


        getContentPane().add(mainPnl);
        pack();
        setLocation(new Point(30, 400));
        setResizable(false);
        setVisible(true);
    }
}
