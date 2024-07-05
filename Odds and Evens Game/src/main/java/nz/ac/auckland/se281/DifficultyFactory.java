package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Difficulty;

/**
 * Class that manages the different difficulties.
 *
 * <p>This class returns the correct instance of the difficulty depending on the difficulty that the
 * user chose.
 */
public class DifficultyFactory {

  /**
   * Receives difficulty chosen by the user and returns an instance of the received difficulty.
   *
   * @param difficulty The difficulty the user chose(Easy, Medium, Hard)
   * @return An instance of a difficulty
   */
  public static DifficultyLevel createDifficulty(Difficulty difficulty) {

    // Switch case so method returns the correct instance of the difficulty according to the
    // difficulty chosen by the user
    switch (difficulty) {
      case EASY:
        return new EasyDifficulty();

      case MEDIUM:
        return new MediumDifficulty();

      case HARD:
        return new HardDifficulty();

      default:
        return null;
    }
  }
}
