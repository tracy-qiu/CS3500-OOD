import java.io.FileWriter;
import java.io.IOException;

import model.ITicTacToe;
import model.TicTacToeImpl;
import view.DesktopView;
import view.GUI_VIEW;
import view.IView;
import view.Utils;

import static org.junit.Assert.*;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public class TicTacToeImplTest {
  @org.junit.Test
  public void move() throws Exception {


    ITicTacToe model = TicTacToeImpl.getGame();

    ITicTacToe model2 = TicTacToeImpl.getGame();

    assertEquals(true, model == model2);


  }

  //parseInt
  // IntegerException
  // helper method that takes in the scanner and keeps asking for a number
  // keep try catching until we get a number


}