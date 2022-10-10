package view;

import java.io.IOException;

import model.ITicTacToe;
import model.Player2;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public class Utils {

  public static String playerValue(Player2 player){
    return "";
  }

  public static void write(Appendable out, String message){
    try{
      out.append(message);
    }catch(IOException e){
      throw new IllegalStateException("Something went wrong while writing to the appendable.");
    }
  }

  public static IView viewMaker(ITicTacToe model){
    return new GUI_VIEW(model);
  }
}
