package snake_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrontPanel implements ActionListener {
    private  JButton start = new JButton("Start");
    private  JButton exit = new JButton("Exit");
    private JButton highScore = new JButton("High Score");
    private JLabel Title_label;
    private JLabel snake_label;
    private ImageIcon img = new ImageIcon("text.gif");
    private ImageIcon img2 = new ImageIcon("Snake2.png");
    private ImageIcon backgroundImage = new ImageIcon("back.png");
    private CardLayout cardLayout;
    private JPanel controlPanel;
    private GamePanel gamePanel;

    private JPanel frontPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, 600, 650, this);
        }
    };
    FrontPanel(CardLayout cardLayout, JPanel controlPanel) {
        this.cardLayout = cardLayout;
        this.controlPanel = controlPanel;


        Title_label = new JLabel(img);
        snake_label = new JLabel(img2);

        Title_label.setPreferredSize(new Dimension(600, 200));
        snake_label.setPreferredSize(new Dimension(600, 250));

        Title_label.setBounds(0,0,600,200);
        snake_label.setBounds(0,200,600,200);

        exit.setBackground(new Color(192, 0, 0, 255));
        exit.setForeground(new Color(168, 178, 168));
        exit.setFont(new Font("Arial", Font.PLAIN, 30));
        exit.setFocusable(false);

        start.setBackground(new Color(0, 110, 0));
        start.setForeground(new Color(164, 196, 196));
        start.setFont(new Font("Arial", Font.PLAIN, 30));
        start.setFocusable(false);
        start.setBounds(180,440,200,50);

        highScore.setBackground(new Color(78, 154, 78));
        highScore.setForeground(new Color(164, 196, 196));
        highScore.setFont(new Font("Arial", Font.PLAIN, 30));
        highScore.setFocusable(false);
        highScore.setBounds(180,500,200,50);

        highScore.addActionListener(this);
        start.addActionListener(this);
        exit.addActionListener(this);
        exit.setBounds(180,560,200,50);

        frontPanel.setLayout(null);
        frontPanel.add(Title_label);
        frontPanel.add(snake_label);
        frontPanel.add(start);
        frontPanel.add(highScore);
        frontPanel.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == start) {
            GamePanel gamePanel  = new GamePanel(cardLayout, controlPanel);
            controlPanel.add(gamePanel, "game_panel");
            cardLayout.show(controlPanel, "game_panel");
            SwingUtilities.invokeLater(gamePanel::requestFocus);
        }
        else if (e.getSource() == highScore) {
            cardLayout.show(controlPanel, "high_score");
            SwingUtilities.invokeLater(gamePanel::requestFocus);
        }
    }

    public JPanel getFrontPanel() {
        return frontPanel;
    }
}
