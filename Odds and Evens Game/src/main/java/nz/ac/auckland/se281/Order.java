package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/**
 * Class that represents the context class for the strategy design pattern.
 *
 * <p>Class that provides methods that to manage the strategy instances for it to be returned to the
 * "main" (Game) class.
 */
public class Order {

  private Strategy strategy;

  public Order(Strategy strategy) {

    this.strategy = strategy;
  }

  /**
   * Method that can set the strategy(either Random or Top).
   *
   * @param strategy The strategy you want to set the instace to
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Method that returns the finger amount that the ai plays
   *
   * @param choiceList List with all the finger amount that the user uses
   * @param choice What the user think the resulting sum would be(even or odd)
   * @return Returns the finger amount according to the strategy
   */
  public int process(List<Integer> choiceList, Choice choice) {
    return strategy.setFingers(choiceList, choice);
  }

  /**
   * Method that returns the current instance of the strategy
   *
   * @return Returns the current instance of the strategy.
   */
  public Strategy getStrategy() {
    return strategy;
  }
}
