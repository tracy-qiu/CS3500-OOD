package view;

import java.util.Objects;

import model.ITicTacToe;

/**
 * Created by vidojemihajlovikj on 9/20/22.
 */
public class MobileView implements IView {
  private final ITicTacToe model;

  public MobileView(ITicTacToe model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public String render() {
    Appendable stringBuilder = new StringBuilder();
    for ( int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Utils.write(stringBuilder, this.model.getPlayerAt(i,j).toString() + "|");
      }
      Utils.write(stringBuilder, "\n");
    }

    return stringBuilder.toString();
  }
}
