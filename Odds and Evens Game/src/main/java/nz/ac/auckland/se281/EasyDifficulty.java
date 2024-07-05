package nz.ac.auckland.se281;

import java.util.List;

/**
 * Subclass of the DifficultyLevel class that override the returnStrategy method.
 *
 * <p>This class overrides the returnStrategy method and returns an instance of a random strategy
 * everytime
 */
public class EasyDifficulty implements DifficultyLevel {

  /**
   * Always return an instance of a random strategy(i.e. this difficulty only uses the random
   * strategy).
   *
   * @param result result of the last match(W means the user won, L means the user lost)
   * @param lastStrategy strategy that was used by the ai in the last round
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @return instance of a random strategy.
   */
  @Override
  public Strategy returnStrategy(String result, Strategy lastStrategy, List<Integer> choiceList) {

    return new RandomStrategy();
  }
}
