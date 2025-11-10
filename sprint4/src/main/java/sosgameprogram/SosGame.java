package sosgameprogram;

import java.util.List;
import java.util.ArrayList;

public abstract class SosGame {

  private int boardSize;
  // currentPlayer holds "B" for blue player or "R" for red player
  private String currentPlayer;
  private String[][] gameBoard;
  private String redPlayerLetterSelection;
  private String bluePlayerLetterSelection;
  // Stores the result of the SOS game ("BV" for blue player victory, "RV" for red playe victory,
  // and "D" for draw
  private String gameResult;
  private boolean gameInProgress;
  // This list stores the red and blue lines for the SOS formation
  private List<SosLine> sosLines;

  public SosGame() {
    // Note: -1 is used as an initial value to tell if the board size has been set yet
    this.boardSize = -1;
    // currentPlayer is initialized to "B" since blue player moves first in every game
    this.currentPlayer = "B";
    this.redPlayerLetterSelection = "";
    this.bluePlayerLetterSelection = "";
    this.gameResult = null;
    this.gameInProgress = false;
    this.sosLines = new ArrayList<>();
  }

  public String getCellContent(int row, int column) {
    if(row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
      return gameBoard[row][column];
    } else {
      return "Invalid cell reference";
    }
  }

  // Updates the content of an unoccupied cell when either play makes on move on it, but returns
  // false if the cell is occupied
  public boolean setCellContent(int row, int column) {
    if (gameBoard[row][column].equals("")) {
      playerValidMove(row, column);
      return true;
    }
    return false;
  }

  public List<SosLine> getSosLines() {
    return sosLines;
  }

  public static class SosLine {
    public int firstRow;
    public int firstColumn;
    public int lastRow;
    public int lastColumn;
    public String player;

    public SosLine(int firstRow, int firstColumn, int lastRow, int lastColumn, String player) {
      this.firstRow = firstRow;
      this.firstColumn = firstColumn;
      this.lastRow = lastRow;
      this.lastColumn = lastColumn;
      this.player = player;
    }
  }

  // Sets the size of the board if inputted size is between 3 and 10, but returns false if inputted
  // size is outside the range of 3-10
  protected boolean setBoardSize(int sizeInput) {
    if (sizeInput >= 3 && sizeInput <= 10) {
      this.boardSize = sizeInput;
      return true;
    }
    return false;
  }

  protected int getBoardSize() {
    return boardSize;
  }

  // Sets up an empty board of the selected board size and sets the current player to the
  // blue player since the blue player moves first in every game
  protected void setUpForNewGame() {
    gameBoard = new String[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        gameBoard[i][j] = "";
      }
    }
    setCurrentPlayer("B");
    gameResult = null;
    gameInProgress = true;
    sosLines.clear();
  }

  protected void setCurrentPlayer(String currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  protected String getCurrentPlayer() {
    return currentPlayer;
  }

  protected void changeTurns() {
    if (currentPlayer.equals("B")) {
      setCurrentPlayer("R");
    } else if (currentPlayer.equals("R")) {
      setCurrentPlayer("B");
    }
  }

  // Sets the letter selection for the red player
  protected void setRedPlayerLetterSelection(String letterSelection) {
    if (letterSelection.equals("S")) {
      redPlayerLetterSelection = "S";
    } else if (letterSelection.equals("O")) {
      redPlayerLetterSelection = "O";
    }
  }

  protected String getRedPlayerLetterSelection() {
    return redPlayerLetterSelection;
  }

  // Sets the letter selection for the blue player
  protected void setBluePlayerLetterSelection(String letterSelection) {
    if (letterSelection.equals("S")) {
      bluePlayerLetterSelection = "S";
    } else if (letterSelection.equals("O")) {
      bluePlayerLetterSelection = "O";
    }
  }

  protected String getBluePlayerLetterSelection() {
    return bluePlayerLetterSelection;
  }

  protected abstract void bluePlayerValidMove(int row, int column);

  protected abstract void redPlayerValidMove(int row, int column);

  protected void setGameResult(String gameResult) {
    this.gameResult = gameResult;
  }

  protected String getGameResult() {
    return gameResult;
  }

  public boolean isGameInProgress() {
    return gameInProgress;
  }

  protected void setGameInProgress(boolean gameInProgress) {
    this.gameInProgress = gameInProgress;
  }


  protected void makeMove(int row, int column, String playerLetterSelection) {
    gameBoard[row][column] = playerLetterSelection;
  }

  protected String[][] getGameBoard () {
    return gameBoard;
  }

  // Checks to see if an SOS sequence has been formed after every move
  protected int checkForSosFormation(int row, int column) {
    int scoreIncrement = 0;

    // Detects and SOS formation in the form of
    // S O S
    // After an S is placed to the left of "OS" to form horizontal SOS
    if (column >= 0 && column + 2 < boardSize) {
      if (gameBoard[row][column].equals("S") && gameBoard[row][column + 1].equals("O") &&
          gameBoard[row][column + 2].equals("S")) {
        sosLines.add(new SosLine(row, column, row, column + 2, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S O S
    // After an O is placed between to Ss to form a horizontal SOS
    if (column >= 1 && column + 1 < boardSize) {
      if (gameBoard[row][column - 1].equals("S") && gameBoard[row][column].equals("O") &&
          gameBoard[row][column + 1].equals("S")) {
        sosLines.add(new SosLine(row, column - 1, row, column + 1,
            currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S O S
    // After an S is placed to the left of "SO" to form a horizontal SOS
    if (column >= 2) {
      if (gameBoard[row][column - 2].equals("S") && gameBoard[row][column - 1].equals("O") &&
          gameBoard[row][column].equals("S")) {
        sosLines.add(new SosLine(row, column - 2, row, column, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S
    // O
    // S
    // After an S is placed above "OS" to form vertical SOS
    if (row >= 0 && row + 2 < boardSize) {
      if (gameBoard[row][column].equals("S") &&  gameBoard[row + 1][column].equals("O") &&
          gameBoard[row + 2][column].equals("S")) {
        sosLines.add(new SosLine(row, column, row + 2, column, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S
    // O
    // S
    // After an O is placed between to Ss to form a vertical SOS
    if (row >= 1 && row + 1 < boardSize) {
      if (gameBoard[row - 1][column].equals("S") && gameBoard[row][column].equals("O") &&
          gameBoard[row + 1][column].equals("S")) {
        sosLines.add(new SosLine(row - 1, column, row + 1, column, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S
    // O
    // S
    // After an S is placed below an "SO" to form a vertical SOS
    if (row >= 2) {
      if (gameBoard[row - 2][column].equals("S") && gameBoard[row - 1][column].equals("O") &&
          gameBoard[row][column].equals("S")) {
        sosLines.add(new SosLine(row - 2, column, row, column, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S
    //   O
    //     S
    // After an S is placed to the top left "OS" to form diagonal SOS
    if (row >= 0 && row + 2 < boardSize && column >= 0 && column + 2 < boardSize) {
      if (gameBoard[row][column].equals("S") && gameBoard[row + 1][column + 1].equals("O") &&
          gameBoard[row + 2][column + 2].equals("S")) {
        sosLines.add(new SosLine(row, column, row + 2, column + 2,
            currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S
    //   O
    //     S
    // After an O is placed between 2 Ss to form diagonal SOS
    if (row >= 1 && row + 1 < boardSize && column >= 1 && column + 1 < boardSize) {
      if (gameBoard[row - 1][column - 1].equals("S") && gameBoard[row][column].equals("O") &&
          gameBoard[row + 1][column + 1].equals("S")) {
        sosLines.add(new SosLine(row - 1, column - 1, row + 1,
            column + 1, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    // S
    //   O
    //     S
    // After an S is placed to the bottom right of an "SO" to form diagonal SOS
    if (row >= 2 && column >= 2) {
      if (gameBoard[row - 2][column - 2].equals("S") &&
          gameBoard[row - 1][column - 1].equals("O") && gameBoard[row][column].equals("S")) {
        sosLines.add(new SosLine(row - 2, column - 2, row, column,
            currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    //     S
    //   O
    // S
    // After an S is placed to the top right "OS" to form diagonal SOS
    if (row >= 0 && row + 2 < boardSize && column >= 2 && column < boardSize) {
      if (gameBoard[row][column].equals("S") && gameBoard[row + 1][column - 1].equals("O") &&
          gameBoard[row + 2][column - 2].equals("S")) {
        sosLines.add(new SosLine(row, column, row + 2, column - 2,
            currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    //     S
    //   O
    // S
    // After an O is placed between two Ss to form diagonal SOS
    if (row >= 1 && row + 1 < boardSize && column >= 1 && column + 1 < boardSize) {
      if (gameBoard[row - 1][column + 1].equals("S") && gameBoard[row][column].equals("O") &&
          gameBoard[row + 1][column - 1].equals("S")) {
        sosLines.add(new SosLine(row - 1, column + 1, row + 1,
            column - 1, currentPlayer));
        scoreIncrement++;
      }
    }

    // Detects and SOS formation in the form of
    //     S
    //   O
    // S
    // After an S is placed to the bottom left of "OS" to form diagonal SOS
    if (row >= 2 && column + 2 < boardSize) {
      if (gameBoard[row - 2][column + 2].equals("S") &&
          gameBoard[row - 1][column + 1].equals("O") && gameBoard[row][column].equals("S")) {
        sosLines.add(new SosLine(row - 2, column + 2, row, column,
            currentPlayer));
        scoreIncrement++;
      }
    }

    return scoreIncrement;
  }

  // Checks to see if the game board is full
  protected boolean isBoardFull() {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (gameBoard[i][j].equals("")) {
          return false;
        }
      }
    }
    return true;
  }

  private void playerValidMove(int row, int column) {
    if (currentPlayer.equals("B")) {
      bluePlayerValidMove(row, column);
    } else if (currentPlayer.equals("R")) {
      redPlayerValidMove(row, column);
    }
  }
}