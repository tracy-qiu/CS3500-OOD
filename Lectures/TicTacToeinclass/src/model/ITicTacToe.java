package model;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public interface ITicTacToe extends ITicTacToeModelState{

  //mutating
  void move(int x, int y);
}
