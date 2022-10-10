package view;

import java.util.Objects;

import model.ITicTacToe;
import model.ITicTacToeModelState;

/**
 * Created by vidojemihajlovikj on 9/20/22.
 */
public class DesktopView implements IView {
  private final ITicTacToeModelState model;
  private final Appendable out;

  public DesktopView(ITicTacToeModelState model, Appendable out) {
    this.model = Objects.requireNonNull(model);
    this.out = Objects.requireNonNull(out);
  }


  @Override
  public String render() {

    for ( int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Utils.write(out, this.model.getPlayerAt(i,j).toString() + "|");
      }
      Utils.write(out, "\n*********");
    }

    return out.toString();
  }
}
