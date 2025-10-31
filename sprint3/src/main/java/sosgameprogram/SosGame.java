package sosgameprogram;

public class SosGame {

  private int boardSize;
  // gameMode holds "S" for simple game or "G" for general game
  private String gameMode;
  // currentPlayer holds "B" for blue player or "R" for red player
  private String currentPlayer;
  private String[][] gameBoard;
  private String redPlayerLetterSelection;
  private String bluePlayerLetterSelection;
  // Stores the result of the SOS game ("BV" for blue player victory, "RV" for red playe victory,
  // and "D" for draw
  private String gameResult;

  public SosGame() {
    // Note: -1 is used as an initial value to tell if the board size has been set yet
    this.boardSize = -1;
    this.gameMode = null;
    // currentPlayer is initialized to "B" since blue player moves first in every game
    this.currentPlayer = "B";
    this.redPlayerLetterSelection = "";
    this.bluePlayerLetterSelection = "";
    this.gameResult = null;
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

  public boolean setGameMode(String modeValue) {
    if (boardSize == -1) {
      return false;
    } else if (modeValue == "S" || modeValue == "G") {
      this.gameMode = modeValue;
      return true;
    } else {
      return false;
    }
  }

  public String getGameMode() {
    return gameMode;
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

  public void bluePlayerValidMove(int row, int column) {
    if (bluePlayerLetterSelection == "S") {
      gameBoard[row][column] = "S";
      changeTurns();
    } else if (bluePlayerLetterSelection == "O") {
      gameBoard[row][column] = "O";
      changeTurns();
    }
  }

  public void redPlayerValidMove(int row, int column) {
    if (redPlayerLetterSelection == "S") {
      gameBoard[row][column] = "S";
      changeTurns();
    } else if (redPlayerLetterSelection == "O") {
      gameBoard[row][column] = "O";
      changeTurns();
    }
  }

  public void setGameResult(String gameResult) {
    this.gameResult = gameResult;
  }

  public String getGameResult() {
    return gameResult;
  }

  // Checks to see if an SOS sequence has been formed after every move
  public boolean checkForSosFormation(int row, int column) {

    // Detects and SOS formation in the form of
    // S O S
    // After an S is placed to the left of "OS" to form horizontal SOS
    if (column >= 0 && column + 2 < boardSize) {
      if (gameBoard[row][column] == "S" && gameBoard[row][column + 1] == "O" &&
          gameBoard[row][column + 2] == "S") {
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S O S
    // After an O is placed between to Ss to form a horizontal SOS
    if (column >= 1 && column + 1 < boardSize) {
      if (gameBoard[row][column - 1] == "S" && gameBoard[row][column] == "O" &&
          gameBoard[row][column + 1] == "S") {
        return true;
      }
    }

    // Detects and SOS formation in the form of
    // S O S
    // After an S is placed to the left of "SO" to form a horizontal SOS
    if (column >= 2) {
      if (gameBoard[row][column - 2] == "S" && gameBoard[row][column - 1] == "O" &&
          gameBoard[row][column] == "S") {
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
}