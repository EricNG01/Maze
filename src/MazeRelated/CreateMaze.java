package MazeRelated;

import java.util.*;

public class CreateMaze {
    public static Maze autoGenMaze(Maze maze) {
        // the change in x and y coordinates for four directions
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{-1, 0, 1, 0};

        // The size of the maze
        int rows = maze.getRows();
        int cols = maze.getCols();

        // total number of cell in maze
        int totalCell = rows * cols;

        // Counter to check the number of visited cell
        int sumVisited = 0;

        // 2D array to identify the visited cells during the algorithm
        boolean[][] visited = new boolean[rows][cols];
        // Initialize the 2D array
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = false;

        // A stack to store the visiting path in order
        Stack<Cell> path = new Stack<>();

        // Randomly choose the starting cell
        Random rand = new Random();
        Cell current = new Cell(rand.nextInt(rows - 2) + 1, rand.nextInt(cols - 2) + 1);
        visited[current.getRow()][current.getCol()] = true;
        sumVisited++;
        path.push(current);

        while (sumVisited < totalCell) {

            // Console log for the first cell
            System.out.println("Current: " + current.getRow() + ", " + current.getCol());

            HashMap<Integer, Cell> neighbours = new HashMap<>();

            for (int i = 0; i < 4; i++) {
                // Get the row and col number of the neighbours
                int nextRow = current.getRow() + dy[i];
                int nextCol = current.getCol() + dx[i];

                if ( nextRow >= 0 && nextRow <= rows - 1 && nextCol >= 0 && nextCol <= cols - 1 && !visited[nextRow][nextCol]) {
                    neighbours.put(i, new Cell(nextRow, nextCol));
                }
            }

            // Console log for all neighbours
            for (Cell c:
                    neighbours.values()) {
                System.out.println(c.getRow() + ", " + c.getCol());
            }

            if (neighbours.isEmpty()) {
                if (path.isEmpty()) break;
                current = path.pop();
            }
            else {
                ArrayList<Integer> direction = new ArrayList<>(neighbours.keySet());
                int index = rand.nextInt(direction.size());
                int next = direction.get(index);
                Cell nextCell = neighbours.get(next);

                direction.remove(index);
                neighbours.remove(next);

                if (neighbours.size() > 1) {
                    index = rand.nextInt(direction.size());
                    next = direction.get(index);
                    maze.getCell(neighbours.get(next).getRow(), neighbours.get(next).getCol()).setWallState(true);
                }


                current = nextCell;
                path.push(current);
                sumVisited++;
                visited[current.getRow()][current.getCol()] = true;
            }

        }

        return maze;
    }
}
