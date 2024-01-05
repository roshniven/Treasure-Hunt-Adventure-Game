/**
 * This class models a restart game button.
 */
public class RestartGameButton extends Button implements Clickable {

  /**
   * Creates a new RestartGameButton object labeled "Restart Game" at a specific position on the
   * screen.
   * 
   * @param x x-position to be assigned to this restart game button
   * @param y y-position to be assigned to this restart game button
   */
  public RestartGameButton(int x, int y) {
    super("Restart Game", x, y);
  }

  /**
   * Defines the behavior of this button when the mouse is pressed. This button resets the progress
   * and restarts the game if it is clicked. It calls the TreasureHunt.initGame() to reset progress
   * and return to initialization state.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver())
      // cast the object of type PApplet to type Treasure Hunt
      ((TreasureHunt) Button.processing).initGame();
  }

}
