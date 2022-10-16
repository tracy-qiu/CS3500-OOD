package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is the implementation of MarbleSolitaireController that creates functionality
 * to play the game by communicating with the model and view.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable input;

  boolean isGameOver = false;

  /**
   * Constructor for MarbleSolitarieControllerImpl that takes in a model, view, and input to create
   * the controller.
   *
   * @param model MarbleSolitaireModel that stores the state of the game
   * @param view  MarbleSolitaireView that renders the view of the game
   * @param input Readable input, string input for game moves
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) {
    if (model == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire model"));
    }
    this.model = model;

    if (view == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire view"));
    }
    this.view = view;

    if (input == null) {
      throw new IllegalArgumentException(String.format("Null marble solitaire input"));
    }
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    this.renderBoardAndScore();
    Scanner scanner = new Scanner(this.input);

    int i = 0;
    ArrayList<Integer> coordinates = new ArrayList<Integer>();
    while (!isGameOver) {
      while (scanner.hasNext()) {
        try {
          coordinates.add(nextMove(scanner));
        } catch (IllegalStateException e) {
          break;
        }
        if (coordinates.size() == 4) {
          try {
            this.model.move(coordinates.get(0), coordinates.get(1), coordinates.get(2),
                    coordinates.get(3));
            this.renderBoardAndScore();
            coordinates.clear();
          } catch (IllegalArgumentException e) {
            this.renderMessage("Invalid move. Play again.\n" + e + " \n");
            coordinates.clear();
          }
        }
        if (this.model.isGameOver()) {
          this.endGame(false);
          break;
        }
      }
      if (!isGameOver && !scanner.hasNext()) {
        throw new IllegalStateException(String.format("No more inputs, game failed to continue\n"));
      }
    }
  }

  /**
   * This method reads in the input from the scanner and parses the next int that will be used
   * for the next move.
   * @param scanner Scanner object to read in the inputs
   * @return int that is a coordinate of the next move
   * @throws IllegalStateException if a "q" or "Q" is the input
   */
  private int nextMove(Scanner scanner) throws IllegalStateException {
    int coordinate = 0;
    while (scanner.hasNext()) {
      String next = scanner.next();
      if (next.equalsIgnoreCase("q")) {
        this.endGame(true);
        throw new IllegalStateException(String.format("Game ended\n"));
      }
      try {
        if (Integer.parseInt(next) - 1 >= 0) {
          coordinate = Integer.parseInt(next) - 1;
          break;
        }
      } catch (NumberFormatException e) {
        continue;
      }
    }
    return coordinate;
  }

  /**
   * This method renders the board and the score through the view.
   * @throws IllegalStateException if the view fails to render
   */
  private void renderBoardAndScore() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(String.format("View failed to render"));
    }
  }

  /**
   * This method renders a message using the view.
   * @param message this message is appended to the output
   * @throws IllegalStateException if the message fails to render
   */
  private void renderMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(String.format("Failed to render message"));
    }
  }

  /**
   * This method sets the isGameOver boolean true and renders the game over message, board,
   * and score.
   * @param quit if the game was end by "q" or "Q" input to determine what the message should be
   */
  private void endGame(boolean quit) {
    isGameOver = true;
    if (quit) {
      this.renderMessage("Game quit!\nState of game when quit:\n");
    } else {
      this.renderMessage("Game over!\n");
    }
    this.renderBoardAndScore();
  }
}
