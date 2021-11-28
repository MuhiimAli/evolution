package evolution;

import javafx.scene.input.KeyEvent;

public interface Playable {
    void gameOver();//ToDo this one is complicated too
    void reStart();
    void back();
    void pause();//TODO is this one necessary
    void updateGame();
    void handleKeyPressed(KeyEvent e);


}
