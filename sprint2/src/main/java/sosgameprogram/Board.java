package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

  private SosGameConsole game;
  private SosGuiFrame gameGui;
  int cellSize = 40;
  int boardOffest = 10;

  // Sets the board size in pixels
  public Board(SosGameConsole game, SosGuiFrame gameGui) {
    this.game = game;
    this.gameGui = gameGui;
  }

  // Draws board
  protected void paintComponent(Graphics board) {
    super.paintComponent(board);

    // If no game mode is selected, then the functions returns without painting a board (to keep
    // the board from being painted without also starting a game at the same time since a game
    // can not start unless a mode is also selected in addition to the board size selection)
    if (game.getGameMode() == null) {
      return;
    }

    int boardSize = game.getBoardSize();
    if (boardSize != -1){
      for (int x = boardOffest; x <= boardSize*cellSize; x += cellSize)
        for (int y = boardOffest; y <= boardSize*cellSize; y += cellSize)
          board.drawRect(x, y, cellSize, cellSize);
    }
  }

  public void newBoard() {
    repaint();
  }
}
