package GUI;

import GUI.Components.CellButton;
import MazeRelated.Cell;
import MazeRelated.CreateMaze;
import MazeRelated.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazePage extends JFrame {
    // Window size
    Dimension windowSize = new Dimension(1000, 1000);
    // Background color for the panels
    public Color bgColor = new Color(0xFFFCF2);
    // Border color for the panels
    public Color borderColor = new Color(0xCCC5B9);
    public Color buttonColor = new Color(0x403D39);

    private static Maze maze;
    public MazePage(int rows, int cols, boolean isBlankMaze) {

        setTitle("Maze");
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);
        setLayout(new BorderLayout());

        // If the user choose auto-generation, run the algorithm to generate a maze
        if (!isBlankMaze) {
            maze = CreateMaze.autoGenMaze(new Maze(rows, cols));
        }
        else {
            maze = new Maze(rows, cols);
        }
        // The panel in the left on this page
        JPanel leftPnl = new JPanel();
        leftPnl.setLayout(null);
        leftPnl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, borderColor));
        leftPnl.setMinimumSize(new Dimension(200, 1000));
        leftPnl.setMaximumSize(new Dimension(200, 1000));
        leftPnl.setPreferredSize(new Dimension(200, 1000));

        JButton start = createButton("Starting Point");
        JButton goal = createButton("Goal");
        JButton logo = createButton("Insert a logo");
        JButton startImage = createButton("Set Starting Point image");
        JButton goalImage = createButton("Set Goal image");
        JButton optimalPath = createButton("Search an optimal path");
        JButton mazeInfo = createButton("Information for this maze");
        JButton save = createButton("Save the maze");

        start.setBounds(10, 160, 180, 50);
        leftPnl.add(start);
        goal.setBounds(10, 250, 180, 50);
        leftPnl.add(goal);
        logo.setBounds(10, 340, 180, 50);
        leftPnl.add(logo);
        startImage.setBounds(10, 430, 180, 50);
        leftPnl.add(startImage);
        goalImage.setBounds(10, 520, 180, 50);
        leftPnl.add(goalImage);
        optimalPath.setBounds(10, 610, 180, 50);
        leftPnl.add(optimalPath);
        mazeInfo.setBounds(10, 700, 180, 50);
        leftPnl.add(mazeInfo);
        save.setBounds(10, 790, 180, 50);
        leftPnl.add(save);

        getContentPane().add(leftPnl, BorderLayout.WEST);



        // The panel in the middle on this page
        JPanel middlePnl = new JPanel();
        middlePnl.setLayout(null);
        middlePnl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                middlePnl.add(new CellButton(row, col, maze));

        getContentPane().add(middlePnl, BorderLayout.CENTER);
        pack();
        setLocation(new Point(300, 30));
        setVisible(true);

        // Components wiring
        mazeInfo.addActionListener(e -> new MazeInfoPage(maze.getRows(), maze.getCols()));
        // End of components wiring

    }
    private JButton createButton(String title) {
        JButton btn = new JButton(title);
        btn.setBackground(buttonColor);
        btn.setForeground(bgColor);
        btn.setFocusPainted(false);
        btn.setRolloverEnabled(false);
        return btn;
    }
    public static Maze getMaze() {
        return maze;
    }
}
