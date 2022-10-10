package model;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public interface ITicTacToeModelState {
  //observer
  boolean isGameOver();
  Player nextPlayer();
  String winner();
  boolean hasWinner();
  Player getPlayerAt(int x, int y);
  int getWidth();
  int getHeight();

}
