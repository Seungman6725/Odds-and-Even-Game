package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/**
 * Subclass of the Strategy class that represents a strategy.
 *
 * <p>This class overrides the setFingers method which returns a random amount of fingers from 0 to
 * 5.
 */
public class RandomStrategy implements Strategy {

  /**
   * returns a random number that ranges from 0 to 5(both inclusive).
   *
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @param choice What the user thought the sum would be(odd or even)
   * @return Returns the amount of fingers that the ai plays
   */
  @Override
  public int setFingers(List<Integer> choiceList, Choice choice) {

    // Return a random amount of fingers from 0 to 5(inclusive)
    return Utils.getRandomNumberRange(0, 5);
  }
}
