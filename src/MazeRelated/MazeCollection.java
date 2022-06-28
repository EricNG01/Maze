package MazeRelated;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MazeCollection {
    ArrayList<Maze> mazes;

    public MazeCollection() {
        mazes = new ArrayList<>();
    }

    public int searchMaze(Maze maze) {
        for (Maze m: mazes) {
            if (m.getMazeName().equals(maze.getMazeName())) return mazes.indexOf(m);
        }
        return -1;
    }
    public void addMaze(Maze maze) {
        mazes.add(maze);
    }
    public void updateMaze(Maze maze, int index) {
        mazes.remove(index);
        maze.setLastEditedDatetime(LocalDateTime.now());
        addMaze(maze);
    }

    public void printAllMazes() {
        for (Maze m: mazes) {
            System.out.println(m.getMazeName() + ", " + m.getLastEditedDatetime());
        }
    }
}
