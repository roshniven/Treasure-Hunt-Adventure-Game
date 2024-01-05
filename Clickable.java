
/**
 * This interface contains a program that models a clickable object in the adventure game.
 */
public interface Clickable {

  public void draw(); // draws this Clickable object to the screen

  public void mousePressed(); // defines the behavior of this Clickable object
  // each time the mouse is pressed

  public void mouseReleased(); // defines the behavior of this Clickable object
  // each time the mouse is released

  public boolean isMouseOver(); // returns true if the mouse is over this clickable object

}
