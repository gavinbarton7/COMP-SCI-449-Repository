package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

  // For testing purposes with my new method of drawing the board.
  // Will be removed in final commit for this sprint
  int boardSize = 8;

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

  /*
  protected void paintComponent(Graphics board) {
    super.paintComponent(board);

    // Sets the color of the board to black
    board.setColor(Color.BLACK);

    // Draws the horizontal lines of SOS game board
    board.drawLine(25, 50, 225, 50);
    board.drawLine(25, 75, 225, 75);
    board.drawLine(25, 100, 225, 100);
    board.drawLine(25, 125, 225, 125);
    board.drawLine(25, 150, 225, 150);
    board.drawLine(25, 175, 225, 175);
    board.drawLine(25, 200, 225, 200);
    board.drawLine(25, 225, 225, 225);
    board.drawLine(25, 250, 225, 250);

    // Draws the vertical lines of SOS game board
    board.drawLine(25, 50, 25, 250);
    board.drawLine(50, 50, 50, 250);
    board.drawLine(75, 50, 75, 250);
    board.drawLine(100, 50, 100, 250);
    board.drawLine(125, 50, 125, 250);
    board.drawLine(150, 50, 150, 250);
    board.drawLine(175, 50, 175, 250);
    board.drawLine(200, 50, 200, 250);
    board.drawLine(225, 50, 225, 250);
  }
  */
}
