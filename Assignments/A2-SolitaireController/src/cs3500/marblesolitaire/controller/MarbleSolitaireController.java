package cs3500.marblesolitaire.controller;

/**
 * This MarbleSolitaireController interface is the controller that communicates with the
 * model and view to play the marble soliataire game.
 */
public interface MarbleSolitaireController {

  /**
   * This method plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output.
   */
  void playGame() throws IllegalStateException;
}
