package sosgameprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {

  private SosGameController controller;
  private GameStateListener listener;
  private int cellSize = 40;
  private int boardOffset = 10;

  public Board(SosGameController controller, GameStateListener listener) {
    this.controller = controller;
    this.listener = listener;

    // Adds listener for clicks on the SOS game GUI
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        cellClickHandler(e.getX(), e.getY());
      }
    });
  }

  public void newBoard() {
    repaint();
  }

  // Draws board
  protected void paintComponent(Graphics board) {
    super.paintComponent(board);

    // If no game mode is selected, then the functions returns without painting a board (to keep
    // the board from being painted without also starting a game at the same time since a game
    // can not start unless a mode is also selected in addition to the board size selection)
    if (controller.getGameMode() == null) {
      return;
    }

    drawBoardGrid(board);
    drawSAndOLetters(board);
    drawSosLines(board);
  }

  // Tells program what to do if a cell is clicked on the board
  private void cellClickHandler(int x, int y) {
    int boardSize = controller.getBoardSize();

    SosGame game = controller.getGame();
    // Determines which cell was clicked
    int col = (x - boardOffset) / cellSize;
    int row = (y - boardOffset) / cellSize;

    if (game.isGameInProgress() == true) {
      if (controller.getObjectOfCurrentPlayer() instanceof HumanPlayer) {
        // Checks to see if the click is within the game board and paints S or O if the cell is
        // unoccupied
        if (row >= 0 && row < boardSize && col >= 0 && col < boardSize) {
          if (game.setCellContent(row, col)) {
            repaint();
          }
        }

        if (game.isGameInProgress() == false) {
          listener.onGameEnded();
        }
      }
    }
  }

  public void computerPlayerMoveExecution(int row, int column, String selectedletter) {
    SosGame game = controller.getGame();

    if (game == null || game.isGameInProgress() == false) {
      return;
    }

    if (controller.getCurrentPlayer().equals("B")) {
      controller.setBluePlayerLetterSelection(selectedletter);
    } else if (controller.getCurrentPlayer().equals("R")) {
      controller.setRedPlayerLetterSelection(selectedletter);
    }

    if (game.setCellContent(row, column)) {
      repaint();
    }

    if (game.isGameInProgress() == false) {
      listener.onGameEnded();
    }
  }

  // This method draws the grid of the board of the users selected board size
  private void drawBoardGrid(Graphics board) {
    int boardSize = controller.getBoardSize();
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
    SosGame game = controller.getGame();
    int boardSize = game.getBoardSize();
    Graphics2D g2d = (Graphics2D) board;
    // Sets the font and font size for the letters to be placed on the board after move
    g2d.setFont(new Font("Arial", Font.BOLD, 14));


    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        String cellContent = game.getCellContent(row, col);
        if ((cellContent.equals("")) == false) {
          g2d.setColor(Color.BLACK);

          FontMetrics fm = g2d.getFontMetrics();
          int textWidth = fm.stringWidth(cellContent);
          int textHeight = fm.getAscent();
          int textX = boardOffset + col * cellSize + (cellSize - textWidth) / 2;
          int textY = boardOffset + row * cellSize + (cellSize + textHeight) / 2;

          g2d.drawString(cellContent, textX, textY);
          listener.onGameStateChanged();
        }
      }
    }
  }

  // This class is used to draw the lines for the SOS fomations
  private void drawSosLines(Graphics board) {
    SosGame game = controller.getGame();

    Graphics2D g2d = (Graphics2D) board;
    g2d.setStroke(new BasicStroke(1));

    for (SosGame.SosLine line : game.getSosLines()) {
      if (line.player.equals("B")) {
        g2d.setColor(Color.BLUE);
      } else if  (line.player.equals("R")) {
        g2d.setColor(Color.RED);
      }

      int startX = boardOffset + line.firstColumn * cellSize + cellSize / 2;
      int startY = boardOffset + line.firstRow * cellSize + cellSize / 2;
      int endX = boardOffset + line.lastColumn * cellSize + cellSize / 2;
      int endY = boardOffset + line.lastRow * cellSize + cellSize / 2;

      g2d.drawLine(startX, startY, endX, endY);
    }
  }
}