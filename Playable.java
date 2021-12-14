package evolution;
import javafx.scene.input.KeyEvent;
/**
 * this is the interface that all the game classes implement
 * used this generic type to instantiate the different game classes in the Game enum
 */
public interface Playable {
    boolean gameOver();
    void reStart();
    void updateGame();
    void handleKeyPressed(KeyEvent e);


}
