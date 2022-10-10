package model;

import view.Utils;

public class TicTacToeImpl implements ITicTacToe{
  private final Player board[][];
  boolean xnext = true;
  public static int objectCounter = 0;
  public static TicTacToeImpl singleton = null;


  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String nextPlayer() {
    return null;
  }

  @Override
  public String winner() {
    return null;
  }

  @Override
  public boolean hasWinner() {
    return false;
  }

  @Override
  public String[][] getBoard() {
    return new String[0][];
  }

  // now this the constructor to create game
  public static ITicTacToe getGame() {
    if (singleton == null) {
      singleton = new TicTacToeImpl();
    }
    // after it is created the first time it returns the already created model
    return singleton;
  }
  private class TicTacToeImpl() {
    this.board = new Player[3][3];

    for (int i = 0; i<3; i++){
      for (int j = 0; j<3; j++) {
        this.board[i][j] = Player.EMPTY;
      }
    }

    this.objectCounter += 1;
    if (this.objectCounter ==1 ) {
      singleton = this;
    }

  }

  @Override
  public String toStringMObile(){
    String result = "";
    StringBuilder stringBuilder = new StringBuilder();
    for ( int i = 0; i< 3; i++) {
      for (int j = 0; j < 3; j++) {
        Utils.write(stringBuilder, this.board[i][j].toString() + "|");
      }
      Utils.write(stringBuilder, "\n");
    }
    return stringBuilder.toString();
  }

  @Override
  public String toStringDesktop(){
    String result = "";
    StringBuilder stringBuilder = new StringBuilder();
    for ( int i = 0; i< 3; i++) {
      for (int j = 0; j < 3; j++) {
        Utils.write(stringBuilder, this.board[i][j].toString() + "|");
      }
      Utils.write(stringBuilder, "\n");
    }
    return stringBuilder.toString();
  }

  public String toFile() {
    try {
      Appendable stringBuilder = new FileWriter("textOutput.txt");

    }
  }
}
