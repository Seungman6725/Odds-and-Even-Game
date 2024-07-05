package nz.ac.auckland.se281;

import java.util.List;

/**
 * Subclass of the DifficultyLevel class that override the returnStrategy method.
 *
 * <p>This class overrides the returnStrategy method and returns an instance of a random strategy
 * for the first 3 rounds than returns instances of top strategy after that.
 */
public class MediumDifficulty implements DifficultyLevel {

  /**
   * Returns an instance of a random strategy in the first 3 rounds than only return instances of
   * top strategy after that.
   *
   * @param result result of the last match(W means the user won, L means the user lost)
   * @param lastStrategy strategy that was used by the ai in the last round
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @return returns random strategy for the first three rounds than returns top strategy
   */
  @Override
  public Strategy returnStrategy(String result, Strategy lastStrategy, List<Integer> choiceList) {
    if (choiceList.size() < 3) {
      return new RandomStrategy();
    } else {
      return new TopStrategy();
    }
  }
}
