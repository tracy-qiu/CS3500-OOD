package controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * Class that represents a fake Action Event used for testing the GUI and MVCCommandCOntroller.
 */
public class FakeActionEvent extends ActionEvent {
  public FakeActionEvent(String command) {
    super(new JButton(), 1, command);
  }

}
