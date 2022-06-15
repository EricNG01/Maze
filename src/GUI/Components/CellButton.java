package GUI.Components;

import GUI.MazePage;
import MazeRelated.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellButton extends JButton{

    // CONSTANTS for the looking of the buttons
    public static int WIDTH = 20;
    public static int HEIGHT = 20;
    public static int WALL_THICKNESS = 1;
    public static Color CELL_COLOUR = new Color(0xCCC5B9);
    public static Color WALL_COLOUR = new Color(0x403D39);
    public static Color CLICKED_COLOUR = new Color(0xEB5E28);



    private final int row, col;
    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;
        setBounds( (col + 1) * (WIDTH), (row + 1) * (HEIGHT), WIDTH, HEIGHT );
        setBackground(CELL_COLOUR);
        setBorder(BorderFactory.createMatteBorder(
                WALL_THICKNESS,
                WALL_THICKNESS,
                WALL_THICKNESS,
                WALL_THICKNESS,
                WALL_COLOUR));
        setFocusPainted(false);

    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

}
