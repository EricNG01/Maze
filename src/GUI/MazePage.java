package GUI;

import GUI.Components.CellButton;
import MazeRelated.Cell;
import MazeRelated.CreateMaze;
import MazeRelated.Maze;
import MazeRelated.MazeCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Stack;

public class MazePage extends JFrame{
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
    public static Color SOLUTION_COLOUR = new Color(0xF7B9A1);

    private static Maze maze;
    private final static MazeCollection mazeCollection = new MazeCollection();
    // JButton
    private final JButton start, goal, optimalPath;
    // Flag variables
    private boolean choosingStartingPoint = false;
    private boolean choosingGoal = false;
    private boolean showOptimalPath = false;

    /**
     * Constructor of the MazePage class. It is a window for drawing a maze
     * @param rows height of the maze
     * @param cols width of the maze
     * @param isBlankMaze true if user select a blank maze, false otherwise
     */
    public MazePage(int rows, int cols, String mazeName, String author, boolean isBlankMaze) {

        setTitle("Maze");
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);
        setLayout(new BorderLayout());

        // If the user choose auto-generation, run the algorithm to generate a maze
        if (!isBlankMaze) {
            maze = CreateMaze.autoGenMaze(rows, cols, mazeName, author);
        }
        else {
            maze = CreateMaze.blankMaze(new Maze(rows, cols, mazeName, author));
        }

        // The panel in the left on this page
        JPanel leftPnl = new JPanel();
        leftPnl.setLayout(null);
        leftPnl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, borderColor));
        leftPnl.setPreferredSize(new Dimension(200, 1080));

        start = createButton("Starting Point");
        goal = createButton("Goal");
        JButton logo = createButton("Insert a logo");
        JButton startImage = createButton("Set Starting Point image");
        JButton goalImage = createButton("Set Goal image");
        optimalPath = createButton("Search an optimal path");
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
        optimalPath.setEnabled(false);
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
            choosingStartingPoint = true;
            start.setEnabled(false);
            goal.setEnabled(false);
        });
        goal.addActionListener(e -> {
            choosingGoal = true;
            goal.setEnabled(false);
            start.setEnabled(false);
        });
        optimalPath.addActionListener(e -> {
            Stack<Cell> path = CreateMaze.optimalSolution(maze);
            if (showOptimalPath) {
                optimalPath.setText("Search an optimal path");
                showOptimalPath = false;

                assert path != null;
                for (Cell c: path) {
                    if (c != maze.getStart() && c != maze.getGoal()) {
                        cellButtons[c.getRow()][c.getCol()].setEnabled(true);
                        cellButtons[c.getRow()][c.getCol()].setBackground(CELL_COLOUR);
                    }

                }
            }
            else {
                if (path != null) {
                    optimalPath.setText("Hide an optimal path");
                    showOptimalPath = true;

                    for (Cell c: path) {
                        if (c != maze.getStart() && c != maze.getGoal()) {
                            cellButtons[c.getRow()][c.getCol()].setEnabled(false);
                            cellButtons[c.getRow()][c.getCol()].setBackground(SOLUTION_COLOUR);
                        }

                    }

                }
                else {
                    JOptionPane.showMessageDialog(this, "No solution path is found");
                }
            }
            repaint();
            revalidate();
        });
        save.addActionListener(e -> {
            int index = mazeCollection.searchMaze(maze);
            if (index == -1) mazeCollection.addMaze(maze);
            else mazeCollection.updateMaze(maze, index);
            System.out.println(index);
            mazeCollection.printAllMazes();
            System.out.println("Testing");
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
            if (choosingStartingPoint) {

                // Display an error message if users choose a wall
                if (maze.getCell(row, col).getWallState()) {
                    JOptionPane.showMessageDialog(this,
                            "Please select a non-wall cell",
                            "Invalid starting point",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Normalise the starting point if there already have one
                    if (maze.getStart() != null) {
                        buttons[maze.getStart().getRow()][maze.getStart().getCol()].setBackground(CELL_COLOUR);
                        buttons[maze.getStart().getRow()][maze.getStart().getCol()].setEnabled(true);
                    }

                    // Store the chosen starting point into the maze
                    maze.setStart(maze.getCell(row, col));
                    // Change the color of the chosen cell
                    buttons[row][col].setBackground(START_COLOUR);
                    // Disable the chosen cell (button)
                    buttons[row][col].setEnabled(false);
                }
                // Assign false to the flag which indicates the users finish choosing the starting point
                choosingStartingPoint = false;
                start.setEnabled(true);
                goal.setEnabled(true);
                // Enable the optimal path button if there exists both starting point and goal
                optimalPath.setEnabled(maze.getStart() != null && maze.getGoal() != null);
            }
            else if (choosingGoal) {

                // Display an error message if users choose a wall
                if (maze.getCell(row, col).getWallState()) {
                    JOptionPane.showMessageDialog(this,
                            "Please select a non-wall cell",
                            "Invalid starting point",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Normalise the starting point if there already have one
                    if (maze.getGoal() != null) {
                        buttons[maze.getGoal().getRow()][maze.getGoal().getCol()].setBackground(CELL_COLOUR);
                        buttons[maze.getGoal().getRow()][maze.getGoal().getCol()].setEnabled(true);
                    }

                    // Store the chosen starting point into the maze
                    maze.setGoal(maze.getCell(row, col));
                    // Change the color of the chosen cell
                    buttons[row][col].setBackground(GOAL_COLOUR);
                    // Disable the chosen cell (button)
                    buttons[row][col].setEnabled(false);
                }
                // Assign false to the flag which indicates the users finish choosing the starting point
                choosingGoal = false;
                goal.setEnabled(true);
                start.setEnabled(true);
                // Enable the optimal path button if there exists both starting point and goal
                optimalPath.setEnabled(maze.getStart() != null && maze.getGoal() != null);
            }
            else {
                if (maze.getCell(row, col).getWallState()) {
                    buttons[row][col].setBackground(CELL_COLOUR);
                    maze.getCell(row, col).setWallState(false);
                }
                else {
                    buttons[row][col].setBackground(WALL_COLOUR);
                    maze.getCell(row, col).setWallState(true);
                }
            }
            // Console log to check if the button functions properly
//        System.out.println("(" + maze.getCell(row,col).getRow() + ", " + maze.getCell(row,col).getCol() + "): " +maze.getCell(row,col).getWallState());
        };
    }

    /**
     * Getter method for the maze currently displayed on the maze page
     * @return the current maze
     */
    public static Maze getMaze() {
        return maze;
    }

}
