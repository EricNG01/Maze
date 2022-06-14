package GUI;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    Color bgColor = new Color(0xFFFCF2);
    public HomePage() {
        setTitle("Draw Maze");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel pnl1 = new JPanel(new GridBagLayout());
        JButton btn1 = new JButton("Button 1");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        





        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
