package sosgameprogram;

// This class manages the controls for the game, such as selecting the simple/general radio
// buttons, the S and O radio buttons, the current player's turn etc. It essentially acts
// as an intermediary between the GUI and the main game internal logic in SosGame since SosGame
// is an abstract class, and games can only be instantiated as "SimpleGame" or "GeneralGame"
public class SosGameController {

  private SosGame game;
  private int boardSize;
  private String gameMode;
  private String bluePlayerType;
  private String redPlayerType;
  private Player bluePlayer;
  private Player redPlayer;

  public SosGameController() {
    this.game = null;
    this.boardSize = -1;
    this.gameMode = null;
    this.bluePlayerType = null;
    this.redPlayerType = null;
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
    } else if (modeValue.equals("S") || modeValue.equals("G")) {
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

  public void setCurrentPlayer(String player) {
    game.setCurrentPlayer(player);
  }

  public String getCurrentPlayer() {
    return game.getCurrentPlayer();
  }

  public void startOfANewGame() {
    if (boardSize == -1 || gameMode == null) {
      return;
    }

    // Creates the appropriate game instance based on whether simple or general game mode is
    // selected
    if (gameMode.equals("S")) {
      game = new SimpleGame();
    } else if (gameMode.equals("G")) {
      game = new GeneralGame();
    }

    setUpPlayers();

    game.setBoardSize(boardSize);
    game.setUpForNewGame();
  }

  public String getGameResult() {
    return game.getGameResult();
  }

  public void setBluePlayerType(String playerTypeSelection) {
    this.bluePlayerType = playerTypeSelection;
  }

  public void setRedPlayerType(String playerTypeSelection) {
    this.redPlayerType = playerTypeSelection;
  }

  public String getbluePlayerType() {
    return bluePlayerType;
  }

  public String getRedPlayerType() {
    return redPlayerType;
  }

  private void setUpPlayers() {
    if (bluePlayerType.equals("H")) {
      bluePlayer = new HumanPlayer();
    } else if (bluePlayerType.equals("C")) {
      bluePlayer = new ComputerPlayer();
    }

    if (redPlayerType.equals("H")) {
      redPlayer = new HumanPlayer();
    } else if (redPlayerType.equals("C")) {
      redPlayer = new ComputerPlayer();
    }

  }
}