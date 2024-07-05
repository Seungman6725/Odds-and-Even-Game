package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {

  private int roundNumber = 0;
  private int userFingerAmount;
  private int aiFingerAmount;
  private int fingersSum;
  private int roundsUserWon = 0;
  private int roundsAiWon = 0;
  private String userName;
  private String sumEvenOrOdd;
  private String aiName = "HAL-9000";
  private String result;
  private String inputString;
  private Choice userChoice;
  private Difficulty userDifficultyLevel;
  private List<Integer> choiceList = new ArrayList<Integer>();
  private Strategy lastStrategy;
  private Order lastStrategyHolder = new Order(new RandomStrategy());

  /**
   * Initialises a new game for the user to play.
   *
   * @param difficulty The difficulty the user chose(Easy, Medium, Hard)
   * @param choice What the user think the resulting sum would be(even or odd)
   * @param options Array containing information of the user
   */
  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    // Print welcome message
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);

    // Set round number to one everytime a new game is initialised
    roundNumber = 1;

    // Collect username
    userName = options[0];

    // Store values into fields to use in other methods
    userChoice = choice;
    userDifficultyLevel = difficulty;

    // Clear the amount of fingers the user used everytime a new game is initialised
    choiceList.clear();

    // Reset the number of rounds that the user and ai won when a new game is initialised
    roundsAiWon = 0;
    roundsUserWon = 0;
  }

  /**
   * Method that prints according messages and read the inputs from the user to determine the right
   * strategy to use. Contains functionality of the game.
   */
  public void play() {

    // If a new game was not initialised display error message
    if (roundNumber == 0) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Show correct round number
    MessageCli.START_ROUND.printMessage(Integer.toString(roundNumber));
    roundNumber++;

    // Print according message for the amount of fingers
    MessageCli.ASK_INPUT.printMessage();

    // Read the input
    inputString = Utils.scanner.nextLine();

    // Check if the input is between 0 and 5(inclusive), else print error message
    while (!(Utils.isInteger(inputString))
        || Integer.parseInt(inputString) < 0
        || Integer.parseInt(inputString) > 5) {
      MessageCli.INVALID_INPUT.printMessage();
      MessageCli.ASK_INPUT.printMessage();
      inputString = Utils.scanner.nextLine();
    }

    // Store finger amount in to variable
    userFingerAmount = Integer.parseInt(inputString);

    // Print according message
    MessageCli.PRINT_INFO_HAND.printMessage(userName, inputString);

    // Initialise correct instance of difficulty
    DifficultyLevel difficulty = DifficultyFactory.createDifficulty(userDifficultyLevel);

    // Collect last strategy used
    lastStrategy = difficulty.returnStrategy(result, lastStrategy, choiceList);

    // set order with last used strategy
    lastStrategyHolder.setStrategy(lastStrategy);

    // Retrieve finger amount from corresponding strategy
    aiFingerAmount = lastStrategyHolder.process(choiceList, userChoice);

    // Store sum into a variable
    fingersSum = aiFingerAmount + userFingerAmount;

    // Store correct choice in variable
    if (Utils.isEven(fingersSum)) {
      sumEvenOrOdd = "EVEN";
    } else if (Utils.isOdd(fingersSum)) {
      sumEvenOrOdd = "ODD";
    }

    // Print the amount of fingers that the ai played
    MessageCli.PRINT_INFO_HAND.printMessage(aiName, Integer.toString(aiFingerAmount));

    // Check if the user made the right choice(even)
    if (userChoice == Choice.EVEN && Utils.isEven(fingersSum)) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(
          Integer.toString(fingersSum), sumEvenOrOdd, userName);
      result = "W";
      roundsUserWon++;
    } else if (userChoice == Choice.ODD
        && Utils.isOdd(fingersSum)) { // Check if the user made the right choice(odd)
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(
          Integer.toString(fingersSum), sumEvenOrOdd, userName);
      result = "W";
      roundsUserWon++;
    } else if (userChoice == Choice.EVEN
        && Utils.isOdd(fingersSum)) { // Check if the user made the wrong choice(even)
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(
          Integer.toString(fingersSum), sumEvenOrOdd, aiName);
      result = "L";
      roundsAiWon++;
    } else if (userChoice == Choice.ODD
        && Utils.isEven(fingersSum)) { // Check if the user made the wrong choice(odd)
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(
          Integer.toString(fingersSum), sumEvenOrOdd, aiName);
      result = "L";
      roundsAiWon++;
    }

    // Add the number that the player picked into a list
    choiceList.add(userFingerAmount);
  }

  /**
   * Method that ends that instance of a game and prints out the rounds won and lost by the user and
   * ai and prints message containing the final winner.
   */
  public void endGame() {
    // If a new game was not initialised display error message
    if (roundNumber == 0) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Print the amount of rounds won/lost for both sides
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        userName, Integer.toString(roundsUserWon), Integer.toString(roundsAiWon));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        aiName, Integer.toString(roundsAiWon), Integer.toString(roundsUserWon));

    // Print the correct winner
    // When the user wins
    if (roundsUserWon > roundsAiWon) {
      MessageCli.PRINT_END_GAME.printMessage(userName);
    } else if (roundsAiWon > roundsUserWon) { // When the ai wins
      MessageCli.PRINT_END_GAME.printMessage(aiName);
    } else if (roundsAiWon == roundsUserWon) { // When the game is a tie
      MessageCli.PRINT_END_GAME_TIE.printMessage();
    }

    // Set the round number to zero so the game "ends"
    roundNumber = 0;
  }

  /**
   * Method that prints a message that shows the amount of rounds that the user and ai won and lost,
   * up to that point in time.
   */
  public void showStats() {

    // If a new game was not initialised display error message
    if (roundNumber == 0) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    MessageCli.PRINT_PLAYER_WINS.printMessage(
        userName, Integer.toString(roundsUserWon), Integer.toString(roundsAiWon));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        aiName, Integer.toString(roundsAiWon), Integer.toString(roundsUserWon));
  }
}
