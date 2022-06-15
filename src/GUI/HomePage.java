package GUI;

import GUI.Exceptions.textFieldException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage extends JFrame {
    // Background color for the homePage
    public Color bgColor = new Color(0xFFFCF2);

    public HomePage() {

        setTitle("Setup Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250, 250));
        setLayout(new BorderLayout(5, 5));

        JPanel mazeSetupPnl = new JPanel(new GridBagLayout());
        mazeSetupPnl.setBackground(bgColor);
        mazeSetupPnl.setPreferredSize(new Dimension(250, 250));

        final JFormattedTextField rowsTextField = new JFormattedTextField();
        final JFormattedTextField colsTextField = new JFormattedTextField();
        JButton blank = new JButton("Blank");
        JButton autoGen = new JButton("Auto");

        blank.setFocusPainted(false);
        autoGen.setFocusPainted(false);


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
        gbc.anchor = GridBagConstraints.LINE_END;
        mazeSetupPnl.add(new JLabel("ROWS"), gbc);
        gbc.gridy = 1;
        mazeSetupPnl.add(new JLabel("COLUMNS"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mazeSetupPnl.add(rowsTextField, gbc);
        gbc.gridy = 1;
        mazeSetupPnl.add(colsTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mazeSetupPnl.add(autoGen, gbc);
        gbc.gridx = 1;
        mazeSetupPnl.add(blank, gbc);
        // end of Home page interface setting

        getContentPane().add(mazeSetupPnl, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
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
        rowsTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowsTextField.setText("");
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
        colsTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colsTextField.setText("");
            }
        });

        autoGen.addActionListener(event -> {
            try {
                int rows = Integer.parseInt(rowsTextField.getText());
                int cols = Integer.parseInt(colsTextField.getText());
                if ( rows < 2 || rows > 100 || cols < 2 || cols > 100)
                    throw new textFieldException();
                rowsTextField.setText("");
                colsTextField.setText("");
                System.out.println("auto-gen, rows: " + rows + ", cols: " + cols);
                MazePage mazePage = new MazePage(rows, cols);
                setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid value for rows and columns",
                        "", JOptionPane.ERROR_MESSAGE);
            } catch (textFieldException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid value for rows and columns\nRange: 2 - 100",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        });

        blank.addActionListener(event -> {
            try {
                int rows = Integer.parseInt(rowsTextField.getText());
                int cols = Integer.parseInt(colsTextField.getText());
                if ( rows < 2 || rows > 100 || cols < 2 || cols > 100)
                    throw new textFieldException();
                rowsTextField.setText("");
                colsTextField.setText("");
                System.out.println("auto-gen, rows: " + rows + ", cols: " + cols);
                MazePage mazePage = new MazePage(rows, cols);
                setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid value for rows and columns",
                        "", JOptionPane.ERROR_MESSAGE);
            } catch (textFieldException e) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid value for rows and columns\nRange: 2 - 100",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        });
        // End of components wiring

    }
}
