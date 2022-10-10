package model;

import java.util.Objects;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public class Player {
  private final String value;

  private Player(String value){
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public String toString(){
    return this.value;
  }

  public static Player X = new Player("X");
  public static Player O = new Player("O");
  public static Player EMPTY = new Player(" ");
}
