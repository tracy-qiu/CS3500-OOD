package model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public class TicTacToeImpl implements ITicTacToe {
  private final Player[][] board;
  boolean xnext = true;
  public static int objectCounter = 0;
  public static TicTacToeImpl singleton = null;

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player nextPlayer() {
    if ( xnext )
      return Player.X;
    else
      return Player.O;

  }

  @Override
  public String winner() {
    return null;
  }

  @Override
  public boolean hasWinner() {
    return false;
  }

  public int getWidth(){
    return 3;
  }

  public int getHeight(){
    return 3;
  }

  public Player getPlayerAt(int x, int y){
    if ( x < 0 || x>2 || y < 0 || y > 2 ){
      throw new IllegalArgumentException("Invalid pos");
    }

    return this.board[x][y];
  }

  public static ITicTacToe getGame(){
    if ( singleton == null ){
      singleton = new TicTacToeImpl();
    }

    return singleton;
  }

  private TicTacToeImpl() {
    this.board = new Player[3][3];

    for ( int i = 0; i < 3; i++){
      for ( int j = 0; j < 3; j++){
        this.board[i][j] = Player.EMPTY;
      }
    }
//
//    this.objectCounter += 1;
//    if (this.objectCounter == 1 ){
//      singleton = this;
//    }

  }



  @Override
  public void move(int x, int y) {


    if ( x < 0 || x>2 || y < 0 || y > 2 ){
      throw new IllegalArgumentException("Invalid pos");
    }

    if ( this.board[x][y] != Player.EMPTY ){
      throw new IllegalArgumentException("Space is not empty");
    }

    if ( xnext )
      this.board[x][y] = Player.X;
    else
      this.board[x][y] = Player.O;

    this.xnext = !this.xnext;//switch to the other player.

  }



}
