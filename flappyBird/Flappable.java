package evolution.flappyBird;

import javafx.scene.input.KeyEvent;

public interface Flappable {
    Boolean gameOver();
    void handleKeyPress(KeyEvent e);
    void updateBirdVelocity();
    void createBirds();
}
