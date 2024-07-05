package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/**
 * Interface that represents a strategy.
 *
 * <p>This interface provides a method to be overriden by its subclasses.
 */
public interface Strategy {

  /**
   * Method to be overriden by the branching subclasses(strategies).
   *
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @param choice What the user thought the sum would be(odd or even)
   * @return Returns the amount of fingers that the ai plays
   */
  public int setFingers(List<Integer> choiceList, Choice choice);
}
