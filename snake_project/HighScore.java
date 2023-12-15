package snake_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScore extends JPanel implements ActionListener {
    private JLabel scoreLabel;
    private JLabel highscoreLabel;
    private JButton backButton;

    private CardLayout cardLayout;
    private JPanel controlPanel;
    private FrontPanel frontPanel;
    private ImageIcon backgroundImage = new ImageIcon("back.png"); // Background image path

    public HighScore(CardLayout cardLayout, JPanel controlPanel, FrontPanel frontPanel) {
        this.cardLayout = cardLayout;
        this.controlPanel = controlPanel;
        this.frontPanel = frontPanel;
        setLayout(null); // Set a null layout for absolute positioning

        // Create and configure the score label
        scoreLabel = new JLabel("High Score");
        scoreLabel.setForeground(Color.PINK);
        scoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
        scoreLabel.setBounds((GamePanel.SCREEN_WIDTH - 250) / 2, 100, 400, 45);
        add(scoreLabel);

        // Create and configure the "High Score" label
        highscoreLabel = new JLabel();
        highscoreLabel.setForeground(Color.PINK);
        highscoreLabel.setFont(new Font("Arial", Font.BOLD, 55));
        highscoreLabel.setBounds((GamePanel.SCREEN_WIDTH - 50) / 2, 250, 400, 75);
        updateHighScoreLabel(); // Call the method to update high score
        add(highscoreLabel);

        // Create and configure the "Play Again" button
        backButton = new JButton("Back");
        backButton.setBackground(Color.GREEN);
        backButton.setForeground(Color.PINK);
        backButton.setBounds((GamePanel.SCREEN_WIDTH - 200) / 2, 450, 200, 40);
        backButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.addActionListener(this);
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 600, 650, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(controlPanel, "front_panel");
    }

    // Method to update the high score label
    private void updateHighScoreLabel() {
        try {
            FileReader fileReader = new FileReader("highscore.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            if (line != null) {
                int highScore = Integer.parseInt(line);
                highscoreLabel.setText("" + highScore);
            } else {
                highscoreLabel.setText("High Score: N/A");
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
