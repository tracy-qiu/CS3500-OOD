import model.ITicTacToe;
import model.TicTacToeImpl;
import view.DesktopView;
import view.MobileView;

public class TicTacToeImplTest {
  @org.junit.Test
  public void move() throws Exception {
    ITicTacToe model = new TicTacToeImpl();
//    IView desktopView = new DesktopView();
//    IView mobileView = new MobileView();
    //assertEquals("", model.toStringDesktop());
//    assertEquals("", desktopView.render());
    ITicTacToe model = TicTacToeImpl.getGame();
    // singleton pattern - only allows you to create one game
    ITicTacToe model2 = TicTacToeImpl.getGame();
    assertEquals(true, model == model2);
  }
}
