package snake_project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOverPanel extends JPanel implements ActionListener {
    private JLabel scoreLabel;
    private JLabel gameOverLabel;
    private JButton playAgainButton;
    private JButton exitButton;
    private CardLayout cardLayout;
    private JPanel controlPanel;
    private GamePanel gamePanel;
    private ImageIcon backgroundImage = new ImageIcon("back.png"); // Background image path

    public GameOverPanel(CardLayout cardLayout, JPanel controlPanel, GamePanel gamePanel) {
        this.cardLayout = cardLayout;
        this.controlPanel = controlPanel;
        this.gamePanel = gamePanel;
        setLayout(null);


        scoreLabel = new JLabel("Score: " + gamePanel.apple_eaten);
        scoreLabel.setForeground(new Color(7, 33, 7));
        scoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
        scoreLabel.setBounds((GamePanel.SCREEN_WIDTH - 250) / 2, 250, 400, 45);
        add(scoreLabel);


        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(new Color(7, 33, 7));
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 55));
        gameOverLabel.setBounds((GamePanel.SCREEN_WIDTH - 350) / 2, 100, 400, 75);
        add(gameOverLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBackground(new Color(0, 110, 0));
        playAgainButton.setForeground(new Color(164, 196, 196));
        playAgainButton.setBounds((GamePanel.SCREEN_WIDTH - 220) / 2, 400, 200, 50);
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 30));
        playAgainButton.addActionListener(this);
        add(playAgainButton);


        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(192, 0, 0, 255));
        exitButton.setForeground(new Color(234, 232, 232, 255));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        exitButton.setBounds((GamePanel.SCREEN_WIDTH - 220) / 2, 470, 200, 50);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, 600, 650, this);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == playAgainButton) {

            GamePanel newGamePanel = new GamePanel(cardLayout, controlPanel);
            controlPanel.add(newGamePanel, "game_panel");
            cardLayout.show(controlPanel, "game_panel");
            SwingUtilities.invokeLater(newGamePanel::requestFocus);
        }
        scoreLabel.setText("Score: " + gamePanel.apple_eaten);
    }
}
