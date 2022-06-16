package GUI;

import MazeRelated.CreateMaze;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * It is the maze info page class. Users can read the important data about the current maze like row and col number, dead end percentage etc.
 */
public class MazeInfoPage extends JFrame {
    Dimension windowSize = new Dimension(250, 500);

    /**
     * This is the constructor of the maze info page.
     * @param rows the height of the maze
     * @param cols the width of the maze
     */
    public MazeInfoPage(int rows, int cols) {
        // Background color for the panels
        Color bgColor = new Color(0xFFFCF2);
        // Border color for the panels
        Color borderColor = new Color(0xCCC5B9);

        setTitle("Maze Information");
        setMinimumSize(windowSize);
        setPreferredSize(windowSize);

        JPanel mainPnl = new JPanel();
        mainPnl.setBackground(bgColor);
        mainPnl.setBorder(BorderFactory.createLineBorder(borderColor, 2, true));
        mainPnl.setLayout(null);

        JLabel mazeNameLabel = new JLabel("Maze name: " + MazePage.getMaze().getMazeName());
        JLabel authorLabel = new JLabel("Author: " + MazePage.getMaze().getAuthor());

        JLabel rowsLabel = new JLabel("Rows: " + rows);
        JLabel colsLabel = new JLabel("Columns: " + cols);

        JLabel deadEndLabel = new JLabel("Dead end percentage: "
                + CreateMaze.deadEndPercentage(MazePage.getMaze()) + "%");

        JLabel createdDatetimeLabel = new JLabel("Created DateTime:"
                + MazePage.getMaze().getCreatedDatetime().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
        JLabel lastEditedDatetimeLabel = new JLabel("Last Edited DateTime:"
                + MazePage.getMaze().getLastEditedDatetime().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));

        mazeNameLabel.setBounds(10, 20, 230, 30);
        authorLabel.setBounds(10, 70, 230, 30);
        rowsLabel.setBounds(10, 120, 230, 30);
        colsLabel.setBounds(10, 170, 230, 30);
        deadEndLabel.setBounds(10, 220, 230, 30);
        createdDatetimeLabel.setBounds(10, 270, 230, 30);
        lastEditedDatetimeLabel.setBounds(10, 320, 230, 30);


        mainPnl.add(mazeNameLabel);
        mainPnl.add(authorLabel);

        mainPnl.add(rowsLabel);
        mainPnl.add(colsLabel);

        mainPnl.add(deadEndLabel);

        mainPnl.add(createdDatetimeLabel);
        mainPnl.add(lastEditedDatetimeLabel);


        getContentPane().add(mainPnl);
        pack();
        setLocation(new Point(30, 400));
        setResizable(false);
        setVisible(true);
    }
}
