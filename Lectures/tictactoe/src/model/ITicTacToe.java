package model;

public interface ITicTacToe {
  //observer
  boolean isGameOver();
  String nextPlayer();
  String winner();
  boolean hasWinner();
  String[][] getBoard();

}
