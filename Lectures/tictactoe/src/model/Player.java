package model;

import java.util.Objects;
public class Player {
  private final String value;

  private Player(String value) {
    this.value = Objects.requireNonNull(value);
  }

  @Override
  public String toString() {
    return this.value;
  }

  //static doesnt need a field to be accessed
  public static Player x = new Player("x");
  public static Player o = new Player("o);
}
