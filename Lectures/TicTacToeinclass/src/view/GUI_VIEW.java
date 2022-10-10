package view;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import model.ITicTacToe;

/**
 * Created by vidojemihajlovikj on 9/20/22.
 */
public class GUI_VIEW extends JFrame implements IView  {
  private final ITicTacToe model;

  public GUI_VIEW(ITicTacToe model) {
    this.model = Objects.requireNonNull(model);
    setSize(new Dimension(model.getWidth() * 100, model.getHeight() * 100));
  }

  @Override
  public void paint(Graphics graphics){
    graphics.setColor(Color.red);
    graphics.fillRect(20,20,100,100);
  }

  @Override
  public String render() {
    setVisible(true);
    return null;
  }
}
