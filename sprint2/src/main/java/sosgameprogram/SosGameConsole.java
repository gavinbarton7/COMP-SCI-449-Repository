package sosgameprogram;

public class SosGameConsole {

  int boardSize;
  // gameMode holds "S" for simple game or "G" for general game
  String gameMode;
  // currentPlayer holds "B" for blue player or "R" for red player
  String currentPlayer;
  String[][] gameBoard;
  boolean gameCurrentlyPlaying;
  boolean isBoardSizeSet;


  public SosGameConsole() {
    this.boardSize = 8;
    this.gameMode = "S";
    this.currentPlayer = "B";
    this.gameCurrentlyPlaying = false;
  }

  // Sets the size of the board if inputted size is between 3 and 10, but returns false if
  // inputted size is outside the range of 3-10
  public boolean setBoardSize(int sizeInput) {
    if (sizeInput >= 3 && sizeInput <= 10) {
      this.boardSize = sizeInput;
      this.isBoardSizeSet = true;
      return true;
    }
    return false;
  }

  public int getBoardSize() {
    return boardSize;
  }

  public void setGameMode(String modeValue) {
    this.gameMode = modeValue;
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
    currentPlayer = "B";
    gameCurrentlyPlaying = true;
  }

  public String getCurrentPlayer() {
    return currentPlayer;
  }

  public void changeTurns() {
    if (currentPlayer.equals("B")) {
      currentPlayer = "R";
    } else if (currentPlayer.equals("R")) {
      currentPlayer = "B";
    }
  }
}
