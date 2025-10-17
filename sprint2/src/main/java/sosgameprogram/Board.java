package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

  private SosGameConsole game;
  private SosGuiFrame gameGui;

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
      for (int x = 30; x <= boardSize*30; x += 30)
        for (int y = 30; y <= boardSize*30; y += 30)
          board.drawRect(x, y, 30, 30);
    }
  }

  public void newBoard() {
    repaint();
  }
}
