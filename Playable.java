package evolution;

import javafx.scene.input.KeyEvent;

public interface Playable {
    boolean gameOver();
    void reStart();
    void updateGame();
    void handleKeyPressed(KeyEvent e);


}
