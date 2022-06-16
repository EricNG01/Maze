package GUI.Components;

import GUI.MazePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellButton extends JButton implements ActionListener{

    // CONSTANTS for the looking of the buttons
    private static int WIDTH, HEIGHT;
    public static Color CELL_COLOUR = new Color(0xFFFCF2);
    public static Color WALL_COLOUR = new Color(0x403D39);
    public static Color BORDER_COLOUR = new Color(0xCCC5B9);



    private final int row, col;
    public CellButton(int row, int col, int offsetX, int offsetY) {
        this.row = row;
        this.col = col;
        addActionListener(this);

        if (MazePage.getMaze().getCell(row, col).getWallState()) setBackground(WALL_COLOUR);
        else setBackground(CELL_COLOUR);
        setBounds( col * (WIDTH) + offsetX, row * (HEIGHT) + offsetY, WIDTH, HEIGHT );

        setBorder(BorderFactory.createMatteBorder(1,1,1,1, BORDER_COLOUR));
        setFocusPainted(false);
    }

    /***
     * Set the width of the cell
     * @param width the width of the cell
     */
    public static void setWIDTH(int width) {
        WIDTH = width;
    }
    /***
     * Set the height of the cell
     * @param height the height of the cell
     */
    public static void setHEIGHT(int height) {
        HEIGHT = height;
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
