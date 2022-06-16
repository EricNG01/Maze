package GUI;

import GUI.Components.CellButton;
import MazeRelated.CreateMaze;
import MazeRelated.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MazePage extends JFrame {
    // Window size
    Dimension windowSize = new Dimension(1400, 1080);
    // Background color for the panels
    public Color bgColor = new Color(0xFFFCF2);
    // Border color for the panels
    public Color borderColor = new Color(0xCCC5B9);
    // Button color
    public Color buttonColor = new Color(0x403D39);

    // Cell colors
    public static Color CELL_COLOUR = new Color(0xFFFCF2);
    public static Color WALL_COLOUR = new Color(0x403D39);
    public static Color START_COLOUR = new Color(0xF38F68);
    public static Color GOAL_COLOUR = new Color(0xEB5E28);

    private static Maze maze;
    public MazePage(int rows, int cols, boolean isBlankMaze) {

        setTitle("Maze");
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);
        setLayout(new BorderLayout());

        // If the user choose auto-generation, run the algorithm to generate a maze
        if (!isBlankMaze) {
            maze = CreateMaze.autoGenMaze(rows, cols);
        }
        else {
            maze = CreateMaze.blankMaze(new Maze(rows, cols));
        }

        // The panel in the left on this page
        JPanel leftPnl = new JPanel();
        leftPnl.setLayout(null);
        leftPnl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, borderColor));
        leftPnl.setPreferredSize(new Dimension(200, 1080));

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
        middlePnl.setPreferredSize(new Dimension(1000, 1080));
        middlePnl.setLayout(null);
        middlePnl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));

        // The cell has chosen to be a square
        // So the width and height of the cell are the same
        int cellSize;
        if (rows > cols) {
            cellSize = ((int) middlePnl.getPreferredSize().getWidth() - 100) / rows;
        }
        else {
            cellSize = ((int) middlePnl.getPreferredSize().getWidth() - 100) / cols;
        }
        CellButton.setHEIGHT(cellSize);
        CellButton.setWIDTH(cellSize);

        int offsetX = ((int)middlePnl.getPreferredSize().getWidth() - (cellSize * cols)) / 2;
        int offsetY = ((int)middlePnl.getPreferredSize().getHeight()  - (cellSize * rows)) / 2;

        CellButton[][] cellButtons = createCellButton(rows, cols, offsetX, offsetY);
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                middlePnl.add(cellButtons[row][col]);

        getContentPane().add(middlePnl, BorderLayout.CENTER);
        pack();
        setLocation(new Point(300, 0));
        setVisible(true);
        setResizable(false);

        // Components wiring
        mazeInfo.addActionListener(e -> new MazeInfoPage(maze.getRows(), maze.getCols()));
        start.addActionListener(e -> {
            System.out.println(e.getSource().toString());
            for (int row = 0; row < rows; row++)
                for (int col = 0; col < cols; col++)
                    cellButtons[row][col].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getSource().getClass() == CellButton.class) {
                                CellButton btn = (CellButton) e.getSource();
                                if (!maze.getCell(btn.getRow(), btn.getCol()).getWallState()) {
                                    maze.setStart(maze.getCell(btn.getRow(), btn.getCol()));
                                    cellButtons[btn.getRow()][btn.getCol()].setBackground(START_COLOUR);
                                    cellButtons[btn.getRow()][btn.getCol()].removeMouseListener(this);
                                }

                            }
                        }
                    });
        });
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
    private CellButton[][] createCellButton(int rows, int cols, int offsetX, int offsetY) {
        CellButton[][] buttons = new CellButton[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++) {
                buttons[row][col] = new CellButton(row, col, offsetX, offsetY);
                buttons[row][col].addActionListener(setWallAction(buttons, row, col));
            }

        return buttons;
    }

    private ActionListener setWallAction(CellButton[][] buttons, int row, int col) {
        return e -> {
            if (maze.getCell(row, col).getWallState()) {
                buttons[row][col].setBackground(CELL_COLOUR);
                maze.getCell(row, col).setWallState(false);
            }
            else {
                buttons[row][col].setBackground(WALL_COLOUR);
                maze.getCell(row, col).setWallState(true);
            }
            // Console log to check if the button functions properly
        System.out.println("(" + maze.getCell(row,col).getRow() + ", " + maze.getCell(row,col).getCol() + "): " +maze.getCell(row,col).getWallState());
        };
    }

    public static Maze getMaze() {
        return maze;
    }
}
