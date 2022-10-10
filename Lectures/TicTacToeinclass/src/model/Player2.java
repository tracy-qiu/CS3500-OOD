package model;

import java.util.Objects;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public enum Player2 {
  O("O"),X("X"),IS_EMPTY(" ");

  private final String value;

  private Player2(String value){
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public String toString(){
    return this.value;
  }

}
