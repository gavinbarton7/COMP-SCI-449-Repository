package sosgameprogram;

public class SosGameConsole {

  int boardSize;
  // gameMode holds "S" for simple game or "G" for general game
  String gameMode;
  // currentPlayer holds "B" for blue player or "R" for red player
  String currentPlayer;

  public SosGameConsole() {
    this.boardSize = 8;
    this.gameMode = "S";
    this.currentPlayer = "B";
  }

  // Sets the size of the board
  public void setBoardSize(int sizeInput) {
    this.boardSize = sizeInput;
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
}
