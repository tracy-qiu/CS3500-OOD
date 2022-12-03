package controller.runnables;

import view.GUIView;

/**
 * The button for refreshing the GUI.
 */
public class RefreshButtonAction implements Runnable {

  private final GUIView view;

  /**
   * The constructor for the button.
   * @param view the view
   */
  public RefreshButtonAction(GUIView view) {
    this.view = view;
  }

  /**
   * Runs the button action.
   */
  @Override
  public void run() {
    this.view.refresh();
  }
}
