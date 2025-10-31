package sosgameprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {

  private SosGame game;
  private SosGuiFrame gameGui;
  private int cellSize = 40;
  private int boardOffset = 10;


  public Board(SosGame game, SosGuiFrame gameGui) {
    this.game = game;
    this.gameGui = gameGui;

    // Adds listener for clicks on the SOS game GUI
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        cellClickHandler(e.getX(), e.getY());
      }
    });
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

    drawBoardGrid(board);
    drawSAndOLetters(board);
  }

  // Tells program what to do if a cell is clicked on the board
  private void cellClickHandler(int x, int y) {
    int boardSize = game.getBoardSize();

    // Determines which cell was clicked
    int col = (x - boardOffset) / cellSize;
    int row = (y - boardOffset) / cellSize;

    // Checks to see if the click is within the game board and paints S or O if the cell is
    // unoccupied
    if (row >= 0 && row < boardSize && col >= 0 && col < boardSize) {
      if (game.setCellContent(row, col)) {
        repaint();
      }
    }
  }

  public void newBoard() {
    repaint();
  }

  // This method draws the grid of the board of the users selected board size
  private void drawBoardGrid(Graphics board) {
    int boardSize = game.getBoardSize();
    if (boardSize != -1) {
      for (int x = boardOffset; x <= boardSize * cellSize; x += cellSize) {
        for (int y = boardOffset; y <= boardSize * cellSize; y += cellSize) {
          board.drawRect(x, y, cellSize, cellSize);
        }
      }
    }
  }

  // This method Paints the S and O letters on the occupied board cells and leaves the unoccupied
  // cells empty after each move, also call the updateCurrentPlayerLabel method after the move to
  // indicate that it is now the other players turn
  private void drawSAndOLetters(Graphics board) {
    int boardSize = game.getBoardSize();
    Graphics2D g2d = (Graphics2D) board;
    // Sets the font and font size for the letters to be placed on the board after move
    g2d.setFont(new Font("Arial", Font.BOLD, 14));


    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        String cellContent = game.getCellContent(row, col);
        if ((cellContent == "") == false) {
          g2d.setColor(Color.BLACK);

          FontMetrics fm = g2d.getFontMetrics();
          int textWidth = fm.stringWidth(cellContent);
          int textHeight = fm.getAscent();
          int textX = boardOffset + col * cellSize + (cellSize - textWidth) / 2;
          int textY = boardOffset + row * cellSize + (cellSize + textHeight) / 2;

          g2d.drawString(cellContent, textX, textY);
          gameGui.updateCurrentPlayerLabel();
        }
      }
    }
  }
}