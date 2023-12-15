package snake_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.io.*;

public class GamePanel extends JPanel implements ActionListener {

    final static int SCREEN_WIDTH = 600;
    final static int SCREEN_HEIGHT = 650;
    private ImageIcon backgroundImage = new ImageIcon("back.png");
    final static int UNIT_SIZE = 25;
    final static int delay = 150;
    final static int GAME_UNIT_X = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    final static int GAME_UNIT_Y =  (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    final int[] x = new int[GAME_UNIT_X];

    final int[] y = new int[GAME_UNIT_Y];
    int appleX;
    int appleY;
    public int apple_eaten=0;
    int currentHighScore;
    int bodyParts = 6;
    boolean running = false;
    Random random = new Random();
    Timer timer = new Timer(delay,this);
    private CardLayout cardLayout;
    private JPanel controlPanel;

GamePanel(CardLayout cardLayout, JPanel controlPanel) {
    y[0] = UNIT_SIZE;
    x[0] = 0;
    CustomKeyAdapter.direction = 'R';
    this.cardLayout = cardLayout;
    this.controlPanel = controlPanel;

    startGame();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT ));
    this.addKeyListener(new CustomKeyAdapter());
    this.setFocusable(true);
}


    public void move(){

        for(int i=bodyParts;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (CustomKeyAdapter.direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
    }

    public void startGame(){
    newApple();
    running = true;
    timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void updateHighScore(int newHighScore) {
        try {
            FileReader fileReader = new FileReader("highscore.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            currentHighScore = 0;

            if (line != null) {
                currentHighScore = Integer.parseInt(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Couldn't read from the file HighScore.txt");
        }


            if (newHighScore > currentHighScore) {
                try {
                    FileWriter fileWriter = new FileWriter("highscore.txt");
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println(newHighScore);
                    printWriter.close();
                } catch (Exception e) {
                    System.out.println("Couldn't write to the file highscore.txt");
                }
            }

    }
    public void draw(Graphics g){
        if(running){
            g.drawImage(backgroundImage.getImage(), 0, 0, 600, 650, this);
            g.setColor(new Color(14, 91, 14));
            g.setFont(new Font(Font.MONOSPACED,Font.BOLD,35));
            g.drawString("Score: "+apple_eaten,(SCREEN_WIDTH/2) - 100, 40);
            g.setColor(new Color(190, 1, 1, 255));
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for(int i=0;i<bodyParts;i++){
                if(i==0){
                    g.setColor(new Color(0,100,0));
                }
                g.setColor(new Color(random.nextInt(100),255,random.nextInt(175)));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
        else{
            Component[] components = controlPanel.getComponents();
            for (Component component : components) {
                if (component.getName() != null && component.getName().equals("game_over")) {
                    controlPanel.remove(component);
                }
            }
            GameOverPanel gameOver= new GameOverPanel(cardLayout, controlPanel, this);
            gameOver.updateScore(apple_eaten);
            controlPanel.add(gameOver, "game_over");
            cardLayout.show(controlPanel, "game_over");
            SwingUtilities.invokeLater(gameOver::requestFocus);

        }
    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }
    public void checkApple(){
        if(x[0]==appleX && y[0]==appleY){
            bodyParts++;
            apple_eaten++;
            newApple();
        }
    }
    public void checkCollision(){
        for(int i=bodyParts;i>0;i--){
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }
        if(x[0]<0){
            running=false;
        }
        if(x[0]>SCREEN_WIDTH - 10){
            running=false;
        }
        if(y[0]<0){
            running=false;
        }
        if(y[0]>SCREEN_HEIGHT - 14){
            running=false;
        }

        if(!running){
            timer.stop();
            updateHighScore(apple_eaten);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();

    }
}
