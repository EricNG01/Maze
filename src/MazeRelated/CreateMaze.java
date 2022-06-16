package MazeRelated;

import java.util.*;

public class CreateMaze {
    /***
     * Using binary tree algorithm to generate a maze
     * Ref: <a href="https://medium.com/analytics-vidhya/maze-generations-algorithms-and-visualizations-9f5e88a3ae37"> maze generation algorithm</a>
     * @param rows, the height of the maze
     * @param cols, the width of the maze
     * @return an auto-generated maze
     */
    public static Maze autoGenMaze (int rows, int cols) {
        // the change in x and y coordinates for four directions
        int[] dx = new int[]{0, 2, 0, -2};
        int[] dy = new int[]{-2, 0, 2, 0};

        // 2D array to identify the visited cells during the algorithm
        boolean[][] visited = new boolean[rows][cols];

        // Initialize the 2D array
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = false;

        Maze maze = blankMaze(new Maze (rows, cols));

        // Randomly choose the starting cell
        Random rand = new Random();
        Cell current = new Cell(1, 1);
        visited[current.getRow()][current.getCol()] = true;

        // A set to store all the cells in maze that can be used
        ArrayList<Cell> validCells = new ArrayList<>();
        for (int row = 1; row < rows - 1; row += 2)
            for (int col = 1; col < cols - 1; col += 2)
                validCells.add(maze.getCell(row, col));

        validCells.remove(current);

        for (Cell c : validCells) {

            current = c;
            visited[current.getRow()][current.getCol()] = true;
            maze.getCell(current.getRow(), current.getCol()).setWallState(false);
            HashMap<Integer, Cell> neighbours = new HashMap<>();

            // Console log for the Current cell
//            System.out.println("Current: " + current.getRow() + ", " + current.getCol());


            for (int i = 0; i < 4; i += 3) {
                // Get the row and col number of east and south neighbours
                int nextRow = current.getRow() + dy[i];
                int nextCol = current.getCol() + dx[i];

                if ( nextRow >= 1 && nextRow <= rows - 2 && nextCol >= 1 && nextCol <= cols - 2 && visited[nextRow][nextCol]) {
                    neighbours.put(i, new Cell(nextRow, nextCol));
                }
            }

            // Console log to print all neighbours
//            for (Cell cc:
//                    neighbours.values()) {
//                System.out.println(cc.getRow() + ", " + cc.getCol());
//            }

            // if the current cell has no neighbour in direction north of west, skip this iteration
            if (neighbours.isEmpty()) continue;

            ArrayList<Integer> direction = new ArrayList<>(neighbours.keySet());
            int index = rand.nextInt(direction.size());
            int backward = direction.get(index);
            Cell backwardCell = neighbours.get(backward);

            // Console log to check the chosen backward cell
//             System.out.println("backward: " + backwardCell.getRow() + ", " + backwardCell.getCol());

            if (backward == 0) {
                maze.getCell(backwardCell.getRow() + 1, backwardCell.getCol()).setWallState(false);
                visited[backwardCell.getRow() + 1][backwardCell.getCol()] = true;
            }
            else if (backward == 3) {
                maze.getCell(backwardCell.getRow(), backwardCell.getCol() + 1).setWallState(false);
                visited[backwardCell.getRow()][backwardCell.getCol() + 1] = true;
            }

        }

        // For those unvisited cells, set them all to be a wall
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (!visited[i][j]) {
                    maze.getCell(i, j).setWallState(true);
                    visited[i][j] = true;
                }

        return maze;
    }

    /***
     * Create a blank maze bounded with 4 walls
     * @param maze the current maze on the maze page
     * @return a bounded maze
     */
    public static Maze blankMaze (Maze maze) {

        for (int i = 0; i < maze.getRows(); i++) {
            maze.getCell(i, 0).setWallState(true);
            maze.getCell(i, maze.getCols() - 1).setWallState(true);
        }
        for (int i = 0; i < maze.getCols(); i++) {
            maze.getCell(0, i).setWallState(true);
            maze.getCell(maze.getRows() - 1, i).setWallState(true);
        }

        return maze;
    }
}
