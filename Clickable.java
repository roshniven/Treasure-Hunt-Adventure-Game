//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Clickable
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
