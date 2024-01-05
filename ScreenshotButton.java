//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Screenshot Button
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
 * This class models a screenshot button.
 */
public class ScreenshotButton extends Button implements Clickable {

  /**
   * Creates a new ScreenshotButton object labeled "Take a screenshot" at a specific position on the
   * screen.
   * 
   * @param x x-position to be assigned to this screenshot button
   * @param y y-position to be assigned to this screenshot button
   */
  public ScreenshotButton(int x, int y) {
    super("Take a screenshot", x, y);
  }

  /**
   * Defines the behavior of this button when the mouse is pressed. When it is clicked, this button
   * takes a screenshot of the game screen. This method calls the PApplet.save() method to save the
   * screenshot with the filename "screenshot.png".
   */
  @Override
  public void mousePressed() {
    if (isMouseOver())
      processing.save("screenshot.png");
  }

}
