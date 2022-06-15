package MazeRelated;

public class Cell {
    private final int row, col;
    private boolean wallState;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.wallState = false;
    }

    /***
     *
     * @param wallState the state of the cell, true if it is a wall, false otherwise
     */
    public void setWallState(boolean wallState) {
        this.wallState = wallState;
    }

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
