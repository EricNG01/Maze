package MazeRelated;

import java.time.LocalDateTime;

/**
 * This is the class to create a Maze object
 */
public class Maze {
    private final Cell[][] maze;
    private final int rows, cols;
    private Cell goal = null;
    private Cell start = null;
    private final String author;
    private final String mazeName;
    private final LocalDateTime createdDatetime;
    private LocalDateTime lastEditedDatetime;

    /**
     * The constructor of a maze object
     * @param rows the height of the maze
     * @param cols the width of the maze
     */
    public Maze(int rows, int cols, String mazeName, String author) {
        maze = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.mazeName = mazeName;
        this.author = author;
        createdDatetime = LocalDateTime.now();
        lastEditedDatetime = createdDatetime;
        mazeInitialization(maze, rows, cols);
    }

    private void mazeInitialization(Cell[][] maze, int rows, int cols) {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                maze[row][col] = new Cell(row, col);
    }


    public void setStart(Cell start) {
        this.start = start;
    }
    public Cell getStart() {
        return start;
    }
    public void setGoal(Cell goal) {
        this.goal = goal;
    }
    public Cell getGoal() {
        return goal;
    }

    /**
     * The getter method to get the Cell object on (row, col) in the maze
     * @param row the row number of the cell
     * @param col the column number of the cell
     * @return a cell object
     */
    public Cell getCell(int row, int col) {
        return maze[row][col];
    }

    /**
     * The getter method to get the height of the maze
     * @return the height of the maze
     */
    public int getRows() {
        return rows;
    }
    /**
     * The getter method to get the width of the maze
     * @return the width of the maze
     */
    public int getCols() {
        return cols;
    }

    public void setLastEditedDatetime(LocalDateTime dateTime) {
        lastEditedDatetime = dateTime;
    }
    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }
    public LocalDateTime getLastEditedDatetime() {
        return lastEditedDatetime;
    }
    public String getMazeName() {
        return mazeName;
    }
    public String getAuthor() {
        return author;
    }
}
