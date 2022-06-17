package MazeRelated;

import java.util.*;


/**
 * This is the class contains all required function for a maze. Like maze generation, dead end calculation etc.
 */
public class CreateMaze{
    /**
     * Using binary tree algorithm to generate a maze
     * Ref: <a href="https://medium.com/analytics-vidhya/maze-generations-algorithms-and-visualizations-9f5e88a3ae37"> maze generation algorithm</a>
     * @param rows, the height of the maze
     * @param cols, the width of the maze
     * @return an auto-generated maze
     */
    public static Maze autoGenMaze (int rows, int cols, String mazeName, String author) {
        // the change in x and y coordinates for four directions
        int[] dx = new int[]{0, 2, 0, -2};
        int[] dy = new int[]{-2, 0, 2, 0};

        // 2D array to identify the visited cells during the algorithm
        boolean[][] visited = new boolean[rows][cols];

        // Initialize the 2D array
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = false;

        Maze maze = blankMaze(new Maze (rows, cols, mazeName, author));

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

    /**
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

    /**
     * Compute the dead end percentage of the maze
     * A dead end cell is defined as a reachable cell (non-wall cell) which has three walls surrounded
     * The equation: (Number of dead end) / (Number of reachable cell) * 100
     * The percentage is rounded up to 2 decimal places
     * @param maze the current maze on the maze page
     * @return the dead end percentage of the maze
     */
    public static double deadEndPercentage(Maze maze) {
        int reachable = 0;
        int deadEnd = 0;
        for (int row = 0; row < maze.getRows(); row++)
            for (int col = 0; col < maze.getCols(); col++)
                if (!maze.getCell(row, col).getWallState()) {
                    reachable++;

                    int wall = 0;
                    int[] dx = {0, 1, 0, -1};
                    int[] dy = {-1, 0, 1, 0};

                    for (int i = 0; i < 4; i++) {

                        // Skip the iteration if the index is out of bound
                        if (row + dy[i] > maze.getRows() - 1 || row + dy[i] < 0
                            || col + dx[i] > maze.getCols() - 1 || col + dx[i] < 0) continue;

                        if (maze.getCell(row + dy[i], col + dx[i]).getWallState())
                                wall++;
                    }
                    if (wall == 3) deadEnd++;
                }
        return Math.round((double)deadEnd/reachable * 100);
    }


    private record Node(Cell self, CreateMaze.Node parent, int distance) {
    }

    /**
     * Compute the optimal solution of the current maze
     * Decided to use Breadth First Search (BFS)
     *
     *
     * @param maze the current maze on the maze page
     * @return the optimal solution
     */
    public static Stack<Cell> optimalSolution(Maze maze) {
        // A stack of cells storing the optimal path series
        Stack<Cell> optimalPath = new Stack<>();

        // Get the starting point and goal from the maze
        Cell start = maze.getStart();
        Cell goal = maze.getGoal();

        // Pre-processing
        // Mark all the wall as visited
        boolean[][] isVisited = new boolean[maze.getRows()][maze.getCols()];
        for (int row = 0; row < maze.getRows(); row++)
            for (int col = 0; col < maze.getCols(); col++) {
                isVisited[row][col] = maze.getCell(row, col).getWallState();
            }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        Node current = new Node(start, null, 0);
        isVisited[current.self().getRow()][current.self().getCol()] = true;
        LinkedList<Node> queue = new LinkedList<>();
        queue.push(current);

        while(true) {

            if (current.self() == goal) {

                while (current != null) {
                    optimalPath.push(current.self);
                    current = current.parent;
                }
                // Show the cells of the path in console log for validation
//                for (Cell c: optimalPath) System.out.println(c.getRow() + ", " + c.getCol());
//                System.out.println("Solved");
                break;
            }
            else if (queue.isEmpty()) {
//                System.out.println("Didn't find a solution");
                return null;
            }
            else {
                while (!queue.isEmpty()) {
                    current = queue.removeFirst();

                    if (current.self() == goal) break;

                    for (int i = 0; i < 4; i++) {
                        int nextCol = current.self().getCol() + dx[i];
                        int nextRow = current.self().getRow() + dy[i];

                        if (nextCol < 0 || nextCol > maze.getCols() - 1
                                || nextRow < 0 || nextRow > maze.getRows() - 1
                                || isVisited[nextRow][nextCol])
                            continue;
                        queue.addLast(new Node(maze.getCell(nextRow, nextCol), current, current.distance() + 1));
                        isVisited[nextRow][nextCol] = true;

                    }
                }
            }
        }

        return optimalPath;

    }
}
