//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Draggable Object
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
 * This class contains a program that implements a simple version of a draggable object in the
 * adventure game.
 */
public class DraggableObject extends InteractiveObject {

  private boolean isDragging; // indicates whether this object is being dragged or not
  private int oldMouseX; // old x-position of the mouse
  private int oldMouseY; // old y-position of the mouse

  /**
   * Creates a new Draggable object with a given name, x and y position, and "Drag Me!" as a default
   * message. When created a new draggable object is not dragging.
   * 
   * @param name name to be assigned to this draggable object
   * @param x    x-position of this draggable object in the display window
   * @param y    y-position of this draggable object in the display window
   */
  public DraggableObject(java.lang.String name, int x, int y) {
    super(name, x, y, "Drag Me!");
    this.isDragging = false;
  }

  /**
   * Creates a new Draggable object with a given name, x and y position, and a specific message.
   * When created a new draggable object is not dragging.
   * 
   * @param name    name to be assigned to this draggable object
   * @param x       x-position of this draggable object in the display window
   * @param y       y-position of this draggable object in the display window
   * @param message message to be displayed when this draggable object is clicked
   */
  public DraggableObject(java.lang.String name, int x, int y, java.lang.String message) {
    super(name, x, y, message);
    this.isDragging = false;
  }

  /**
   * Draws this draggable object to the display window. If this object is dragging, this method sets
   * its position to follow the mouse moves. Then, it draws its image to the its current position.
   */
  @Override
  public void draw() {
    // if the object is dragging, set its position to follow the mouse moves
    if (isDragging()) {
      this.move​(processing.mouseX - getX(), processing.mouseY - getY());
    }
    // draw an image of the object at its current position
    processing.image(this.image, this.getX(), this.getY());
  }

  /**
   * Checks whether this draggable object is being dragged.
   * 
   * @return true if this object is being dragged, false otherwise
   */
  public boolean isDragging() {
    return this.isDragging;
  }

  /**
   * Starts dragging this object when it is clicked.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      startDragging();
    }
  }

  /**
   * Stops dragging this object if the mouse is released.
   */
  @Override
  public void mouseReleased() {
    stopDragging();
  }

  /**
   * Starts dragging this draggable object and updates the oldMouseX and oldMouseY positions to the
   * current position of the mouse.
   */
  public void startDragging() {
    this.isDragging = true;
    // update the oldMouseX and oldMouseY positions to the current position of the mouse
    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Stops dragging this draggable object.
   */
  public void stopDragging() {
    this.isDragging = false;
  }

}
