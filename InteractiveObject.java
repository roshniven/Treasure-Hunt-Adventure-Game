//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Interactive Object
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

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains a program that implements a simple version of an interactive object in the
 * adventure game.
 */
public class InteractiveObject implements Clickable {

  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image.
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is
  // clicked the first time, if any

  /**
   * Creates a new interactive object with no next clue and sets its image, name, x and y positions,
   * its message, and activation status. When created, an interactive object is active, and not
   * clicked on yet.
   * 
   * @param name    name to be assigned to this interactive object
   * @param x       x-position to be assigned to this interactive object
   * @param y       y-position to be assigned to this interactive object
   * @param message message to be displayed on the console each time this interactive object is
   *                clicked
   */
  public InteractiveObject(java.lang.String name, int x, int y, java.lang.String message) {
    this.nextClue = null;
    this.NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    this.isActive = true;
    this.wasClicked = false;
    this.image = processing.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Creates a new interactive object with a next clue to be activated when this interactive object
   * is clicked for the first time. This constructor sets the image of the newly created interactive
   * object, its name, x and y positions, its message, and activation status. When created, an
   * interactive object is active, and not clicked on yet. Also, this constructor deactivates the
   * next clue of this interactive object.
   * 
   * @param name     name to be assigned to this interactive object
   * @param x        x-position to be assigned to this interactive object
   * @param y        y-position to be assigned to this interactive object
   * @param message  message to be displayed on the console each time this interactive object is
   *                 clicked
   * @param nextClue a reference to a non-null InteractiveObject which represents the next clue
   *                 associated to this interactive object.
   */
  public InteractiveObject(java.lang.String name, int x, int y, java.lang.String message,
      InteractiveObject nextClue) {
    this(name, x, y, message);
    this.nextClue = nextClue;
    this.nextClue.isActive = false;
  }

  /**
   * Activates this interactive object.
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * Activates the next clue of this interactive object and adds it to the treasure hunt
   * application.
   * 
   * @throws IllegalArgumentException with descriptive error message if the nextClue of this
   *                                  interactive object is null
   */
  protected void activateNextClue() throws NoSuchElementException {
    // check if next clue is null
    if (this.nextClue == null) {
      throw new NoSuchElementException("This next clue is null.");
    }
    this.nextClue.activate();
    // add the object to the treasure hunt application
    processing.add(this.nextClue);
  }

  /**
   * Deactivates this interactive object.
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * Draws this interactive object to the display window at positions x and y.
   */
  @Override
  public void draw() {
    processing.image(this.image, this.x, this.y);
  }

  /**
   * Gets the x-position of this interactive object.
   * 
   * @return the x-position of this interactive object
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-position of this interactive object.
   * 
   * @return the y-position of this interactive object
   */
  public int getY() {
    return this.y;
  }

  /**
   * Checks whether the name of this interactive object equals to the name passed as input
   * parameter.
   * 
   * @param name name to compare to
   * @return true if the name of this interactive object equals the provided name, false otherwise
   */
  public boolean hasName​(java.lang.String name) {
    return this.NAME.equals(name);
  }

  /**
   * Checks whether this interactive object is active.
   * 
   * @return true if this interactive object is active and false otherwise
   */
  public boolean isActive() {
    return this.isActive;
  }

  /**
   * Checks whether the mouse is over the image of this interactive object.
   * 
   * @return true if the mouse is over the image of this interactive object, and false otherwise
   */
  @Override
  public boolean isMouseOver() {
    // check whether the mouse is over the image of this interactive object
    if (processing.mouseX >= this.x && processing.mouseX <= (this.x + this.image.width)
        && processing.mouseY >= this.y && processing.mouseY <= (this.y + this.image.height)) {
      return true;
    }
    return false;
  }

  /**
   * Gets the message of this interactive object.
   * 
   * @return the message to be displayed each time this interactive object is clicked
   */
  public java.lang.String message() {
    return this.message;
  }

  /**
   * This method operates each time the mouse is pressed. This interactive object responds to the
   * mouse clicks as follows. If the mouse is clicked two operations will be performed as follows:
   * (1) The message of this interactive object must be displayed to the console. (2) If this
   * interactive object has a next clue and was not clicked, its next clue will be activated and its
   * wasClicked setting will be updated.
   */
  @Override
  public void mousePressed() {
    // the operations are performed only if the mouse is over the object
    if (isMouseOver()) {
      System.out.println(this.message);
      // check if the object has a next clue but was not clicked
      if (this.nextClue != null && this.wasClicked == false) {
        this.wasClicked = true;
        // activate the next clue of the interactive object
        this.activateNextClue();
      }
    }
  }

  /**
   * This method operates each time the mouse is released. It implements a default behavior for an
   * interactive object which does nothing.
   */
  @Override
  public void mouseReleased() {
  }

  /**
   * Moves the current x and y positions of this interactive object with dx and dy, respectively.
   * 
   * @param dx move to be added to the x position of this interactive object
   * @param dy move to be added to the y position of this interactive object
   */
  public void move​(int dx, int dy) {
    // add dx and dy to the current x and y positions of the interactive object
    this.x = this.x + dx;
    this.y = this.y + dy;
  }

  /**
   * Sets the next clue associated to this interactive object.
   * 
   * @param nextClue the next clue to be assigned to this interactive object
   */
  public void setNextClue​(InteractiveObject nextClue) {
    this.nextClue = nextClue;
  }

  /**
   * Sets the PApplet object of the treasure hunt application where all the interactive objects will
   * be drawn.
   * 
   * @param processing represents the reference to the TreasureHunt PApplet object where all the
   *                   interactive objects will be drawn.
   */
  public static void setProcessing​(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }
}
