package nz.ac.auckland.se281;

import java.util.List;

/**
 * Represents a difficulty level.
 *
 * <p>This class provides a method that returns an instance of a strategy.
 */
public interface DifficultyLevel {

  /**
   * Method to be overriden in the branching subclasses(difficulties).
   *
   * @param result result of the last match(W means the user won, L means the user lost)
   * @param lastStrategy strategy that was used by the ai in the last round
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @return an instance of a strategy
   */
  public Strategy returnStrategy(String result, Strategy lastStrategy, List<Integer> choiceList);
}
