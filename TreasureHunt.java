import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains a program that models the display window of a treasure hunt style adventure
 * game. This game includes graphical elements that a user can click on, and drag onto
 * others to find clues, solve puzzles and eventually win the game.
 */
public class TreasureHunt extends PApplet {

  private PImage backgroundImage; // PImage object which represents the background image
  private ArrayList<Clickable> gameObjects; // list storing objects instances of Clickable

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // The PApplet.main() method takes a String input parameter which represents
    // the name of your PApplet class.
    PApplet.main("TreasureHunt");
  }

  /**
   * Sets the size of the application display window.
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines initial environment properties, loads background images and fonts, loads the clues, and
   * initializes the instance fields, as the program starts.
   */
  @Override
  public void setup() {

    this.focused = true; // Confirms that this graphic display window is "focused," meaning that it
    // is active and will accept mouse or keyboard input.
    this.imageMode(PApplet.CORNERS); // Interprets the x and y position of an image to the position
                                     // of its upper-left corner on the screen
    this.getSurface().setTitle("Treasure Hunt"); // Displays the title of the display window
    this.rectMode(PApplet.CORNERS); // Sets the location from which rectangles are drawn.
    // rectMode(CORNERS) interprets the first two parameters of rect() method as the position of the
    // upper left corner, and the third and fourth parameters as the position of the opposite
    // corner. rect() method draws a rectangle to the display window

    this.textSize(13); // sets the text font size to 13
    this.textAlign(PApplet.CENTER, PApplet.CENTER); // sets the text alignment to center
    InteractiveObject.setProcessing​(this); // sets the processing of an interactive object
    Button.setProcessing(this); // sets the processing of a button

    initGame(); // calls the method to initialize the game
  }

  /**
   * Initializes the game settings and list of objects.
   */
  public void initGame() {
    /*
     * load the background image using PApplet.loadImage() method and assign the returned PApplet
     * reference to backgroundImage instance data field this.backgroundImage = loadImage("images" +
     * File.separator + "background.png");
     */

    // Create a new ArrayList of Clickable and assign its reference to the gameObjects instance
    // field
    ArrayList<Clickable> objectsInGame = new ArrayList<Clickable>();
    this.gameObjects = objectsInGame;
    /*
     * InteractiveObject koala = new InteractiveObject("koala", 350, 65,
     * "What a cute stuffed koala! It looks like a real one!"); this.gameObjects.add(koala);
     * InteractiveObject coinTarget = new InteractiveObject("coinTarget", 65, 510,
     * "This is where the gold coin must be dropped."); this.gameObjects.add(coinTarget);
     * InteractiveObject map = new InteractiveObject("map", 330, 550,
     * "The map says to drop the gold coin there.", coinTarget); this.gameObjects.add(map);
     */

    // create a new restart game button and a screenshot button
    RestartGameButton restartButton = new RestartGameButton(0, 0);
    ScreenshotButton ssButton = new ScreenshotButton(140, 0);
    // add the buttons to the list of game objects
    this.gameObjects.add(restartButton);
    this.gameObjects.add(ssButton);

    // load the game settings
    loadGameSettings("clues" + File.separator + "treasureHunt.clues");
  }

  /**
   * Adds a Clickable object, giving its reference, to the list of game objects.
   * 
   * @param clickableObject reference to an object instance of Clickable to add
   */
  public void add(Clickable clickableObject) {
    this.gameObjects.add(clickableObject);
  }

  /**
   * Updates the treasure hunt game display window. Draws the background image, draws all clickable
   * objects stored in the list of game objects, then removes all the interactive objects which are
   * no longer active.
   */
  @Override
  public void draw() {
    // 1. draw background image to the position (0, 0) of this display window
    image(backgroundImage, 0, 0);

    // 2. traverse the list of gameObjects and draw all clickable objects
    for (int i = 0; i < this.gameObjects.size(); i++) {
      gameObjects.get(i).draw();
    }

    // 3. traverse the list of gameObjects and remove deactivated interactive objects
    for (int i = 0; i < this.gameObjects.size(); i++) {
      if (gameObjects.get(i) instanceof InteractiveObject) {
        // cast the object to an interactive object
        InteractiveObject object = (InteractiveObject) gameObjects.get(i);
        if (!object.isActive()) {
          // remove the object if it is not active
          gameObjects.remove(i);
          i--;
        }
      }
    }
  }

  /**
   * Operates each time the mouse is pressed.
   */
  @Override
  public void mousePressed() {
    // traverse the list of gameObjects and call mousePressed() method of each object stored in
    // the list
    for (int i = 0; i < this.gameObjects.size(); i++) {
      gameObjects.get(i).mousePressed();
    }
  }

  /**
   * Operates each time the mouse is released.
   */
  @Override
  public void mouseReleased() {
    // traverse the list of gameObjects and call mouseReleased() method of each object stored
    // in the list
    for (int i = 0; i < this.gameObjects.size(); i++) {
      gameObjects.get(i).mouseReleased();
    }
  }

  /**
   * Helper method to retrieve the reference to the interactive object whose name matches the name
   * passed as input from the gameObjects list names (case-sensitive comparison). If multiple
   * objects have that name, this method will return the first (lowest-index) reference found.
   * 
   * @param name name of the object that is being found
   * @return a reference to an interactive object with the specified name, or null when none is
   *         found
   */
  protected InteractiveObject findObjectByName​(java.lang.String name) {
    // traverse through the whole array of game objects
    for (int i = 0; i < this.gameObjects.size(); i++) {
      // clickable objects can be typecast
      if (gameObjects.get(i) instanceof InteractiveObject) {
        InteractiveObject object = (InteractiveObject) gameObjects.get(i);
        // check if the name matches
        if (object.hasName​(name)) {
          return object;
        }
      }
    }
    return null;
  }

  /**
   * This method loads a background image, prints out some introductory text, and then reads in a
   * set of interactive objects descriptions from a text file with the provided name. These
   * represent the different clues for our treasure hunt adventure game. The image is stored in
   * this.backgroundImage, and the activated interactive objects are added to the this.gameObjects
   * list.
   * 
   * @param filename relative path of file to load, relative to current working directory
   */
  private void loadGameSettings(String filename) {
    // start reading file contents
    Scanner fin = null;
    int lineNumber = 1; // report first line in file as lineNumber 1
    try {
      fin = new Scanner(new File(filename));

      // read and store background image
      String backgroundImageFilename = fin.nextLine().trim();
      backgroundImageFilename = "images" + File.separator + backgroundImageFilename + ".png";
      backgroundImage = loadImage(backgroundImageFilename);
      lineNumber++;

      // read and print out introductory text
      String introductoryText = fin.nextLine().trim();
      System.out.println(introductoryText);
      lineNumber++;

      // then read and create new objects, one line per interactive object
      while (fin.hasNextLine()) {
        String line = fin.nextLine().trim();
        if (line.length() < 1)
          continue;

        // fields are delimited by colons within a given line
        String[] parts = line.split(":");
        if (parts.length < 5) {
          continue; // line mal-formatted
        }
        InteractiveObject newObject = null;

        // first letter in line determines the type of the interactive object to create
        if (Character.toUpperCase(line.charAt(0)) == 'C')
          newObject = loadNewInteractiveObject(parts);
        else if (Character.toUpperCase(line.charAt(0)) == 'D')
          newObject = loadNewDroppableObject(parts);

        // even deactivated object references are being added to the gameObjects arraylist,
        // so they can be found.
        // these deactivated object references will be removed, when draw() is first called
        gameObjects.add(newObject);
        if (Character.isLowerCase(line.charAt(0))) // lower case denotes non-active object
          newObject.deactivate();
        lineNumber++;
      }

      // catch and report warnings related to any problems experienced loading this file
    } catch (FileNotFoundException e) {
      System.out.println("WARNING: Unable to find or load file: " + filename);
    } catch (RuntimeException e) {
      System.out.println("WARNING: Problem loading file: " + filename + " line: " + lineNumber);
      e.printStackTrace();
    } finally {
      if (fin != null)
        fin.close();
    }
  }


  /**
   * This method creates and returns a new ClickableObject based on the properties specified as
   * strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: - C: indicates that a
   *              ClickableObject is being created - name: the name of the newly created interactive
   *              object - x: the starting x position (as an int) for this object - y: the starting
   *              y position (as an int) for this object - message: a string of text to display when
   *              this object is clicked - name of the object to activate (optional): activates this
   *              object when clicked
   * @return the newly created object
   */
  private InteractiveObject loadNewInteractiveObject(String[] parts) {
    // C: name: x: y: message: name of object to activate (optional)

    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    String message = parts[4].trim();
    InteractiveObject activate = null;
    if (parts.length > 5) {
      activate = findObjectByName​(parts[5].trim());
      // create new clickable object
      if (activate != null) {
        return new InteractiveObject(name, x, y, message, activate);
      } else {
        System.out.println("WARNING: Failed to find an interactive object with name: " + name);
      }
    }
    return new InteractiveObject(name, x, y, message);
  }

  /**
   * This method creates and returns a new DroppableObject based on the properties specified as
   * strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: - D: indicates that a
   *              DroppableObject is being created - name: the name of the newly created droppable
   *              object - x: the starting x position (as an int) for this object - y: the starting
   *              y position (as an int) for this object - message: a string of text to display when
   *              this object is dropped on target - name of the object to activate (optional):
   *              activates this object when dropped on target
   * 
   * @return the newly created droppable object
   */
  private DroppableObject loadNewDroppableObject(String[] parts) {
    // D: name: x: y: target: message: name of object to activate (optional)

    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    InteractiveObject dropTarget = findObjectByName​(parts[4].trim());
    if (!(dropTarget instanceof InteractiveObject))
      dropTarget = null;
    String message = parts[5].trim();
    InteractiveObject activate = null;
    if (parts.length > 6)
      activate = findObjectByName​(parts[6].trim());
    // create and return the new droppable object
    return new DroppableObject(name, x, y, message, dropTarget, activate);
  }

}
