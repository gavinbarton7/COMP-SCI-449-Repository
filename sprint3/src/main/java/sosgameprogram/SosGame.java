package sosgameprogram;

import java.util.List;
import java.util.ArrayList;

public abstract class SosGame {

  protected int boardSize;
  // gameMode holds "S" for simple game or "G" for general game
  protected String gameMode;
  // currentPlayer holds "B" for blue player or "R" for red player
  protected String currentPlayer;
  protected String[][] gameBoard;
  protected String redPlayerLetterSelection;
  protected String bluePlayerLetterSelection;
  // Stores the result of the SOS game ("BV" for blue player victory, "RV" for red playe victory,
  // and "D" for draw
  protected String gameResult;
  protected boolean gameInProgress;
  // This list stores the red and blue lines for the SOS formation
  protected List<SosLine> sosLines;

  public SosGame() {
    // Note: -1 is used as an initial value to tell if the board size has been set yet
    this.boardSize = -1;
    this.gameMode = null;
    // currentPlayer is initialized to "B" since blue player moves first in every game
    this.currentPlayer = "B";
    this.redPlayerLetterSelection = "";
    this.bluePlayerLetterSelection = "";
    this.gameResult = null;
    this.gameInProgress = false;
    this.sosLines = new ArrayList<>();
  }

  // Sets the size of the board if inputted size is between 3 and 10, but returns false if inputted
  // size is outside the range of 3-10
  public boolean setBoardSize(int sizeInput) {
    if (sizeInput >= 3 && sizeInput <= 10) {
      this.boardSize = sizeInput;
      return true;
    }
    return false;
  }

  public int getBoardSize() {
    return boardSize;
  }

  // Sets up an empty board of the selected board size and sets the current player to the
  // blue player since the blue player moves first in every game
  public void setUpForNewGame() {
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

  public void setCurrentPlayer(String currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public String getCurrentPlayer() {
    return currentPlayer;
  }

  public void changeTurns() {
    if (currentPlayer == "B") {
      setCurrentPlayer("R");
    } else if (currentPlayer == "R") {
      setCurrentPlayer("B");
    }
  }

  // Sets the letter selection for the red player
  public void setRedPlayerLetterSelection(String letterSelection) {
    if (letterSelection == "S") {
      redPlayerLetterSelection = "S";
    } else if (letterSelection == "O") {
      redPlayerLetterSelection = "O";
    }
  }

  public String getRedPlayerLetterSelection() {
    return redPlayerLetterSelection;
  }

  // Sets the letter selection for the blue player
  public void setBluePlayerLetterSelection(String letterSelection) {
    if (letterSelection == "S") {
      bluePlayerLetterSelection = "S";
    } else if (letterSelection == "O") {
      bluePlayerLetterSelection = "O";
    }
  }

  public String getBluePlayerLetterSelection() {
    return bluePlayerLetterSelection;
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
    if (gameBoard[row][column] == "") {
      playerValidMove(row, column);
      return true;
    }
    return false;
  }

  public void playerValidMove(int row, int column) {
    if (currentPlayer == "B") {
      bluePlayerValidMove(row, column);
    } else if (currentPlayer == "R") {
      redPlayerValidMove(row, column);
    }
  }

  public abstract void bluePlayerValidMove(int row, int column);

  public abstract void redPlayerValidMove(int row, int column);

  public void setGameResult(String gameResult) {
    this.gameResult = gameResult;
  }

  public String getGameResult() {
    return gameResult;
  }

  public boolean isGameInProgress() {
    return gameInProgress;
  }

  // Checks to see if an SOS sequence has been formed after every move
  public boolean checkForSosFormation(int row, int column) {

    // Detects and SOS formation in the form of
    // S O S
    // After an S is placed to the left of "OS" to form horizontal SOS
    if (column >= 0 && column + 2 < boardSize) {
      if (gameBoard[row][column] == "S" && gameBoard[row][column + 1] == "O" &&
          gameBoard[row][column + 2] == "S") {
        sosLines.add(new SosLine(row, column, row, column + 2, currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S O S
    // After an O is placed between to Ss to form a horizontal SOS
    if (column >= 1 && column + 1 < boardSize) {
      if (gameBoard[row][column - 1] == "S" && gameBoard[row][column] == "O" &&
          gameBoard[row][column + 1] == "S") {
        sosLines.add(new SosLine(row, column - 1, row, column + 1,
            currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S O S
    // After an S is placed to the left of "SO" to form a horizontal SOS
    if (column >= 2) {
      if (gameBoard[row][column - 2] == "S" && gameBoard[row][column - 1] == "O" &&
          gameBoard[row][column] == "S") {
        sosLines.add(new SosLine(row, column - 2, row, column, currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S
    // O
    // S
    // After an S is placed above "OS" to form vertical SOS
    if (row >= 0 && row + 2 < boardSize) {
      if (gameBoard[row][column] == "S" &&  gameBoard[row + 1][column] == "O" &&
          gameBoard[row + 2][column] == "S") {
        sosLines.add(new SosLine(row, column, row + 2, column, currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S
    // O
    // S
    // After an O is placed between to Ss to form a vertical SOS
    if (row >= 1 && row + 1 < boardSize) {
      if (gameBoard[row - 1][column] == "S" && gameBoard[row][column] == "O" &&
          gameBoard[row + 1][column] == "S") {
        sosLines.add(new SosLine(row - 1, column, row + 1, column, currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S
    // O
    // S
    // After an S is placed below an "SO" to form a vertical SOS
    if (row >= 2) {
      if (gameBoard[row - 2][column] == "S" && gameBoard[row - 1][column] == "O" &&
          gameBoard[row][column] == "S") {
        sosLines.add(new SosLine(row - 2, column, row, column, currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S
    //   O
    //     S
    // After an S is placed to the top left "OS" to form diagonal SOS
    if (row >= 0 && row + 2 < boardSize && column >= 0 && column + 2 < boardSize) {
      if (gameBoard[row][column] == "S" && gameBoard[row + 1][column + 1] == "O" &&
          gameBoard[row + 2][column + 2] == "S") {
        sosLines.add(new SosLine(row, column, row + 2, column + 2,
            currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S
    //   O
    //     S
    // After an O is placed between 2 Ss to form diagonal SOS
    if (row >= 1 && row + 1 < boardSize && column >= 1 && column + 1 < boardSize) {
      if (gameBoard[row - 1][column - 1] == "S" && gameBoard[row][column] == "O" &&
          gameBoard[row + 1][column + 1] == "S") {
        sosLines.add(new SosLine(row - 1, column - 1, row + 1,
            column + 1, currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S
    //   O
    //     S
    // After an S is placed to the bottom right of an "SO" to form diagonal SOS
    if (row >= 2 && column >= 2) {
      if (gameBoard[row - 2][column - 2] == "S" && gameBoard[row - 1][column - 1] == "O" &&
          gameBoard[row][column] == "S") {
        sosLines.add(new SosLine(row - 2, column - 2, row, column,
            currentPlayer));
        return true;
      }
    }

    // Detects and SOS formation in the form of
    //     S
    //   O
    // S
    // After an S is placed to the top right "OS" to form diagonal SOS
    if (row >= 0 && row + 2 < boardSize && column >= 2 && column < boardSize) {
      if (gameBoard[row][column] == "S" && gameBoard[row + 1][column - 1] == "O" &&
          gameBoard[row + 2][column - 2] == "S") {
        sosLines.add(new SosLine(row, column, row + 2, column - 2,
            currentPlayer));
        return true;
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
        return true;
      }
    }

    // Detects and SOS formation in the form of
    //     S
    //   O
    // S
    // After an S is placed to the bottom left of "OS" to form diagonal SOS
    if (row >= 2 && column + 2 < boardSize) {
      if (gameBoard[row - 2][column + 2] == "S" && gameBoard[row - 1][column + 1] == "O" &&
          gameBoard[row][column] == "S") {
        sosLines.add(new SosLine(row - 2, column + 2, row, column,
            currentPlayer));
        return true;
      }
    }

    return false;
  }

  // Checks to see if the game board is full
  public boolean isBoardFull() {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (gameBoard[i][j] == "") {
          return false;
        }
      }
    }
    return true;
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
}