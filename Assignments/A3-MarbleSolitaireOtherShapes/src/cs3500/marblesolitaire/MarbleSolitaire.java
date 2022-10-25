package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Marble solitaire main program to interface with the user to play any of the types of
 * marble solitaire : english, european, or triangle.
 */
public final class MarbleSolitaire {
  static String gameType;
  static int size;
  static int row;
  static int col;

  /**
   * Main class that takes in user configuration of the game and user inputs to play the game.
   *
   * @param args configuration arguments for game set up
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = null;
    int num_parameters = args.length;
    checkParameters(args);

    switch (args[0]) {
      case "english":
        gameType = "english";
        for (int index = 1; index < num_parameters; index++) {
          if (args[index].equals("-size")) {
            if (index + 2 < num_parameters) {
              if (args[index + 2].equals("-hole")) {
                model = new EnglishSolitaireModel(size, row, col);
                break;
              }
            } else {
              model = new EnglishSolitaireModel(size);
              break;
            }
          }
          if (args[index].equals("-hole")) {
            if (index + 3 < num_parameters) {
              if (args[index + 3].equals("-size")) {
                model = new EnglishSolitaireModel(size, row, col);
                break;
              }
            } else {
              model = new EnglishSolitaireModel(row, col);
              break;
            }
          }
        }
        model = new EnglishSolitaireModel();
        break;

      case "european":
        gameType = "european";
        for (int index = 1; index < num_parameters; index++) {
          if (args[index].equals("-size")) {
            if (index + 2 < num_parameters) {
              if (args[index + 2].equals("-hole")) {
                model = new EuropeanSolitaireModel(size, row, col);
                break;
              }
            } else {
              model = new EuropeanSolitaireModel(size);
              break;
            }
          }
          if (args[index].equals("-hole")) {
            if (index + 3 < num_parameters) {
              if (args[index + 3].equals("-size")) {
                model = new EuropeanSolitaireModel(size, row, col);
                break;
              }
            } else {
              model = new EuropeanSolitaireModel(row, col);
              break;
            }
          }
        }
        model = new EuropeanSolitaireModel();
        break;

      case "triangle":
        gameType = "triangle";
        for (int index = 1; index < num_parameters; index++) {
          if (args[index].equals("-size")) {
            if (index + 2 < num_parameters) {
              if (args[index + 2].equals("-hole")) {
                model = new TriangleSolitaireModel(size, row, col);
                break;
              }
            } else {
              model = new TriangleSolitaireModel(size);
              break;
            }
          }
          if (args[index].equals("-hole")) {
            if (index + 3 < num_parameters) {
              if (args[index + 3].equals("-size")) {
                model = new TriangleSolitaireModel(size, row, col);
                break;
              }
            } else {
              model = new TriangleSolitaireModel(row, col);
              break;
            }
          }
        }
        model = new TriangleSolitaireModel();
        break;
      default:
        break;
    }

    Readable userInput = new InputStreamReader(System.in);
    Appendable controllerOutput = System.out;
    MarbleSolitaireView view = null;
    if (gameType.equals("english") || gameType.equals("european")) {
      view = new MarbleSolitaireTextView(model, controllerOutput);
    } else {
      view = new TriangleSolitaireTextView(model, controllerOutput);
    }
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            userInput);
    controller.playGame();
  }

  /**
   * From the user input, parse the hole location and size if provided.
   *
   * @param args user input for game configuration
   */
  private static void checkParameters(String[] args) {
    int num_parameters = args.length;
    for (int index = 1; index < num_parameters; index++) {
      if (args[index].equals("-size")) {
        try {
          size = Integer.parseInt(args[index + 1]);
        } catch (NumberFormatException e) {
          continue;
        }
      } else if (args[index].equals("-hole")) {
        try {
          row = Integer.parseInt(args[index + 1]) - 1;
          col = Integer.parseInt(args[index + 2]) - 1;
        } catch (NumberFormatException e) {
          continue;
        }
      }
    }
  }

}
