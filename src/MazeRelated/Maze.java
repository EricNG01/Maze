package MazeRelated;

public class Maze {
    private final Cell[][] maze;
    private final int rows, cols;

    public Maze(int rows, int cols) {
        maze = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        mazeInitialization(maze, rows, cols);
    }

    private void mazeInitialization(Cell[][] maze, int rows, int cols) {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                maze[row][col] = new Cell(row, col);
    }

    public Cell getCell(int row, int col) {
        return maze[row][col];
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
}
