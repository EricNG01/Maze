package GUI.Components;

import GUI.MazePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellButton extends JButton implements ActionListener{

    // CONSTANTS for the looking of the buttons
    private int WIDTH = 40;
    private int HEIGHT = 40;
    public static Color CELL_COLOUR = new Color(0xFFFCF2);
    public static Color WALL_COLOUR = new Color(0x403D39);
    public static Color BORDER_COLOUR = new Color(0xCCC5B9);



    private final int row, col;
    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;
        addActionListener(this);

        if (MazePage.getMaze().getCell(row, col).getWallState()) setBackground(WALL_COLOUR);
        else setBackground(CELL_COLOUR);
        setBounds( (col + 1) * (WIDTH), (row + 1) * (HEIGHT), WIDTH, HEIGHT );

        setBorder(BorderFactory.createMatteBorder(1,1,1,1, BORDER_COLOUR));
        setFocusPainted(false);
    }

    /***
     * Set the width of the cell
     * @param WIDTH the width of the cell
     */
    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }
    /***
     * Set the height of the cell
     * @param HEIGHT the height of the cell
     */
    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
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
