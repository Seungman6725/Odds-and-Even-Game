package nz.ac.auckland.se281;

import java.util.List;
import nz.ac.auckland.se281.Main.Choice;

/**
 * Subclass of the Strategy class that represents a more complex algorithm.
 *
 * <p>This class overrides the setFingers method which returns the amount of fingers that the ai
 * plays according to the top strategy.
 */
public class TopStrategy implements Strategy {

  /**
   * Checks what kind of number the user plays more(odd or even) and depending on that, play the
   * right amount of fingers, i.e. if the user plays more even numbers and predicted that the sum
   * would be even, play an odd number so the sum is odd.
   *
   * @param choiceList A list containing all the previous number of fingers that the user played
   * @param choice What the user thought the sum would be(odd or even)
   * @return Returns the amount of fingers that the ai plays
   */
  @Override
  public int setFingers(List<Integer> choiceList, Choice choice) {

    int amountOfEven = 0;
    int amountOfOdd = 0;

    // Increment corresponding variable(odd or even)
    for (int number : choiceList) {
      if (Utils.isEven(number)) {
        amountOfEven++;
      } else if (Utils.isOdd(number)) {
        amountOfOdd++;
      }
    }

    // Check if there are more even than odd number and vice versa
    // When the user choice was Even
    if (choice == Choice.EVEN) {
      if (amountOfOdd > amountOfEven) {
        return Utils.getRandomEvenNumber();
      } else if (amountOfEven > amountOfOdd) {
        return Utils.getRandomOddNumber();
      } else if (amountOfEven == amountOfOdd) {
        return Utils.getRandomNumberRange(0, 5);
      }
    }

    // When the user choice was Odd
    if (choice == Choice.ODD) {
      if (amountOfOdd > amountOfEven) {
        return Utils.getRandomOddNumber();
      } else if (amountOfEven > amountOfOdd) {
        return Utils.getRandomEvenNumber();
      } else if (amountOfEven == amountOfOdd) {
        return Utils.getRandomNumberRange(0, 5);
      }
    }
    return -1;
  }
}
