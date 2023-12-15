package snake_project;
/**
 *           name --------------------------------Id
 *         Abel Adane ......................... Ugr/25638/14-+
 *         Abdullah Omar ..................... Ugr/25446/14
 *         Siham Anwar ....................... Ugr/25382/14
 *         Sina Yesuf ....................... Ugr/26322/14
 *         Webi Muleta ....................... Ugr/25314/14
 * */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game  {

    Game(){
        JFrame main_frame = new JFrame();
        CardLayout gameLayout = new CardLayout();
        JPanel controlPanel = new JPanel(gameLayout);


        GamePanel gamePanel = new GamePanel(gameLayout, controlPanel);
        GameOverPanel gameOver = new GameOverPanel(gameLayout, controlPanel, gamePanel);
        FrontPanel frontPanel = new FrontPanel(gameLayout, controlPanel);
        HighScore highScore = new HighScore(gameLayout, controlPanel, frontPanel);


        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(600,650);
        main_frame.setTitle("Snake Game");
        main_frame.setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("snake2.png");
        main_frame.setIconImage(icon);
        main_frame.setVisible(true);
        controlPanel.add(frontPanel.getFrontPanel(), "front_panel");

        controlPanel.add(highScore, "high_score");

        main_frame.add(controlPanel);
        gameLayout.show(controlPanel, "front_panel");
    }

    public static void main(String[] args) throws IOException {
        new Game();

    }
}