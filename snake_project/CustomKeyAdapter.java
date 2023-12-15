package snake_project;
import java.awt.event.*;

public class CustomKeyAdapter extends KeyAdapter {
    public static char direction = 'L';

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT && direction != 'R') {
            direction = 'L';
        } else if (keyCode == KeyEvent.VK_RIGHT && direction != 'L') {
            direction = 'R';
        } else if (keyCode == KeyEvent.VK_UP && direction != 'D') {
            direction = 'U';
        } else if (keyCode == KeyEvent.VK_DOWN && direction != 'U') {
            direction = 'D';
        }
    }

}
