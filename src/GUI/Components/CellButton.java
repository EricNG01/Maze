package GUI.Components;

import GUI.MazePage;
import MazeRelated.Cell;
import MazeRelated.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellButton extends JButton implements ActionListener{

    // CONSTANTS for the looking of the buttons
    public static int WIDTH = 40;
    public static int HEIGHT = WIDTH;
    public static int WALL_THICKNESS = 1;
    public static Color CELL_COLOUR = new Color(0xFFFCF2);
    public static Color WALL_COLOUR = new Color(0x403D39);
    public static Color BORDER_COLOUR = new Color(0xCCC5B9);



    private final int row, col;
    public CellButton(int row, int col, Maze maze) {
        this.row = row;
        this.col = col;
        addActionListener(this);

        if (MazePage.getMaze().getCell(row, col).getWallState()) setBackground(WALL_COLOUR);
        else setBackground(CELL_COLOUR);
        setBounds( (col + 1) * (WIDTH), (row + 1) * (HEIGHT), WIDTH, HEIGHT );
        setBorder(BorderFactory.createMatteBorder(
                WALL_THICKNESS,
                WALL_THICKNESS,
                WALL_THICKNESS,
                WALL_THICKNESS,
                BORDER_COLOUR));
        setFocusPainted(false);
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MazePage.getMaze().getCell(row, col).getWallState()) {
            setBackground(CELL_COLOUR);
            MazePage.getMaze().getCell(row, col).setWallState(false);
        }
        else {
            setBackground(WALL_COLOUR);
            MazePage.getMaze().getCell(row, col).setWallState(true);
        }
    }
}
