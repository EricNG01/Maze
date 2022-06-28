package GUI;

import GUI.Exceptions.textFieldException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HomePage extends JFrame {
    // Background color for the homePage
    public Color bgColor = new Color(0xFFFCF2);
    public Color buttonColor = new Color(0x403D39);
    private final int MAX_ROWS = 101;
    private final int MAX_COLS = 101;
    private final int MIN_ROWS = 5;
    private final int MIN_COLS = 5;

    /**
     * This is the constructor to create the home page of the software
     * Users can create different size and type of maze in this page
     */
    public HomePage() {

        setTitle("Setup Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250, 250));
        setLayout(new BorderLayout(5, 5));

        JPanel mazeSetupPnl = new JPanel(new GridBagLayout());
        mazeSetupPnl.setBackground(bgColor);
        mazeSetupPnl.setPreferredSize(new Dimension(250, 250));

        final JTextField rowsTextField = new JTextField();
        final JTextField colsTextField = new JTextField();
        final JTextField mazeNameTextField = new JTextField();
        final JTextField authorTextField = new JTextField();

        JButton blank = createButton("Blank");
        JButton autoGen = createButton("Auto");


        // Home page interface setting
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mazeSetupPnl.add(new JLabel("ROWS"), gbc);
        gbc.gridy = 1;
        mazeSetupPnl.add(new JLabel("COLUMNS"), gbc);
        gbc.gridy = 2;
        mazeSetupPnl.add(new JLabel("Maze name"), gbc);
        gbc.gridy = 3;
        mazeSetupPnl.add(new JLabel("Author"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mazeSetupPnl.add(rowsTextField, gbc);
        gbc.gridy = 1;
        mazeSetupPnl.add(colsTextField, gbc);
        gbc.gridy = 2;
        mazeSetupPnl.add(mazeNameTextField, gbc);
        gbc.gridy = 3;
        mazeSetupPnl.add(authorTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        mazeSetupPnl.add(autoGen, gbc);
        gbc.gridx = 1;
        mazeSetupPnl.add(blank, gbc);
        // end of Home page interface setting

        getContentPane().add(mazeSetupPnl, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setLocation(new Point(30, 30));
        setResizable(false);
        setVisible(true);


        // Components wiring
        rowsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        });

        colsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        });
        mazeNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetterOrDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        });
        authorTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetterOrDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
                    e.consume();
            }
        });

        autoGen.addActionListener(event -> {
            try {
                int rows = Integer.parseInt(rowsTextField.getText());
                int cols = Integer.parseInt(colsTextField.getText());
                String mazeName = mazeNameTextField.getText();
                String author = authorTextField.getText();

                if ( rows < MIN_ROWS || rows > MAX_ROWS || cols < MIN_COLS || cols > MAX_COLS || rows % 2 == 0 || cols % 2 == 0)
                    throw new textFieldException();
                if ( mazeName.isEmpty() || author.isEmpty())
                    throw new textFieldException();
                mazeNameTextField.setText("");
                authorTextField.setText("");
                rowsTextField.setText("");
                colsTextField.setText("");

//                System.out.println("auto-gen, rows: " + rows + ", cols: " + cols);

                new MazePage(rows, cols, mazeName, author, false);
                setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid value for rows and columns",
                        "", JOptionPane.ERROR_MESSAGE);
            } catch (textFieldException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter an odd number for rows and columns\nRange: 5 - 101",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        });

        blank.addActionListener(event -> {
            try {
                int rows = Integer.parseInt(rowsTextField.getText());
                int cols = Integer.parseInt(colsTextField.getText());
                String mazeName = mazeNameTextField.getText();
                String author = authorTextField.getText();
                if ( rows < MIN_ROWS || rows > MAX_ROWS || cols < MIN_COLS || cols > MAX_COLS || rows % 2 == 0 || cols % 2 == 0)
                    throw new textFieldException();
                if ( mazeName.isEmpty() || author.isEmpty())
                    throw new textFieldException();
                mazeNameTextField.setText("");
                authorTextField.setText("");
                rowsTextField.setText("");
                colsTextField.setText("");

//                System.out.println("auto-gen, rows: " + rows + ", cols: " + cols);

                new MazePage(rows, cols, mazeName, author, true);
                setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid value for rows and columns",
                        "", JOptionPane.ERROR_MESSAGE);
            } catch (textFieldException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter an odd number for rows and columns\nRange: 5 - 101",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        });
        // End of components wiring

    }
    private JButton createButton(String title) {
        JButton btn = new JButton(title);
        btn.setBackground(buttonColor);
        btn.setForeground(bgColor);
        btn.setFocusPainted(false);
        btn.setRolloverEnabled(false);
        return btn;
    }
}
