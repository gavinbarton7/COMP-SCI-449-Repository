package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

  int boardSize = 10;
  // For testing purposes with my new method of drawing the board.
  // Will be removed in final commit for this sprint

  // Sets the board size in pixels
  public Board() {
    setSize(300,300);
  }

  // Draws board
  public void paint(Graphics board) {
    for (int x = 30; x <= boardSize*30; x += 30)
      for (int y = 30; y <= boardSize*30; y += 30)
        board.drawRect(x, y, 30, 30);
  }
}
