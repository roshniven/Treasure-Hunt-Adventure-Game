//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Restart Game Button
// Course: CS 300 Spring 2022
//
// Author: Roshni Venkat
// Email: rvenkat@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Shraddha Byndoor
// Partner Email: sbyndoor@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

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
