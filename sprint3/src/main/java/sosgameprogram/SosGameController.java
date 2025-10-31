package sosgameprogram;

// This class manages the controls for the game, such as selecting the simple/general radio
// buttons, the S and O radio buttons, etc.
public class SosGameController {

  private SosGame game;
  private int boardSize;
  private String gameMode;

  public SosGameController() {
    this.game = null;
    this.boardSize = -1;
    this.gameMode = null;
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

  public SosGame getGame() {
    return game;
  }

  // Sets the letter selection for the red player
  public void setRedPlayerLetterSelection(String letterSelection) {
    game.setRedPlayerLetterSelection(letterSelection);
  }

  // Sets the letter selection for the blue player
  public void setBluePlayerLetterSelection(String letterSelection) {
    game.setBluePlayerLetterSelection(letterSelection);
  }

  public String getCurrentPlayer() {
    return game.getCurrentPlayer();
  }

  public void startOfANewGame() {
    if (boardSize == -1 || gameMode == null) {
      return;
    }

    // Create the appropriate game instance
    if (gameMode.equals("S")) {
      game = new SimpleGame();
    } else {
      game = new GeneralGame();
    }

    game.setBoardSize(boardSize);
    game.setUpForNewGame();
  }
}
