package evolution.flappyBird;

import javafx.scene.input.KeyEvent;

/**
 * this is the interface that the manualBird class, MultiplayerBird class  and Population class implement
 */

public interface Flappable {
    Boolean gameOver();
    void handleKeyPress(KeyEvent e);
    void updateWithTimeline();
}
