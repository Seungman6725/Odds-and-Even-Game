package nz.ac.auckland.se281;

import java.util.List;

/**
 * Subclass of the DifficultyLevel class that override the returnStrategy method.
 *
 * <p>This class overrides the returnStrategy method and returns an instance of a random strategy
 * for the first 3 rounds than alternates between strategies depending on the result of the last
 * round.
 */
public class HardDifficulty implements DifficultyLevel {

  /**
   * Returns an instance of a random strategy in the first 3 rounds than switches strategy if the
   * previous round was lost by the ai, but continues to use the same strategy as long as the ai
   * wins.
   *
   * @param result result of the last match(W means the user won, L means the user lost)
   * @param lastStrategy strategy that was used by the ai in the last round
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @return Returns random strategy for the first three rounds, than alternates between the
   *     strategy if the ai lost the last round and returns the same strategy as before is the ai
   *     won
   */
  @Override
  public Strategy returnStrategy(String result, Strategy lastStrategy, List<Integer> choiceList) {

    if (choiceList.size() < 3) {
      return new RandomStrategy();
    }

    // ai lost last round with random strategy
    if (result.equals("W") && lastStrategy instanceof RandomStrategy) {
      return new TopStrategy();
    } else if (result.equals("W") && lastStrategy instanceof TopStrategy) {
      // ai lost last round with top strategy
      return new RandomStrategy();
    } else {
      // When ai won last round
      return lastStrategy;
    }
  }
}
