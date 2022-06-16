package MazeRelated;

/**
 * The class to create a Cell object
 */
public class Cell {
    private final int row, col;
    private boolean wallState;

    /**
     * Constructor of a cell object
     * @param row the row number of the cell
     * @param col the column number of the cell
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.wallState = false;
    }

    /**
     * Setter method to set the state of the cell
     * @param wallState the state of the cell, true if it is a wall, false otherwise
     */
    public void setWallState(boolean wallState) {
        this.wallState = wallState;
    }
    /**
     * Getter method to get the state of the cell
     * @return  the state of the cell, true if it is a wall, false otherwise
     */
    public boolean getWallState() {
        return wallState;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
