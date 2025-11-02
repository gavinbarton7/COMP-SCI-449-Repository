package sosgameprogram;

public class GeneralGame extends SosGame {
  private int bluePlayerScore = 0;
  private int redPlayerScore = 0;

  @Override
  public void bluePlayerValidMove(int row, int column) {
    makeMove(row, column, bluePlayerLetterSelection);
    bluePlayerScore = handleScoring(row, column, bluePlayerScore);
    generalGameOver();
  }

  @Override
  public void redPlayerValidMove(int row, int column) {
    makeMove(row, column, redPlayerLetterSelection);
    redPlayerScore = handleScoring(row, column, redPlayerScore);
    generalGameOver();
  }

  public int getBluePlayerScore() {
    return bluePlayerScore;
  }

  public int getRedPlayerScore() {
    return redPlayerScore;
  }

  private int handleScoring(int row, int column, int playerScore) {
    int sosCount = checkForSosFormation(row, column);
    if (sosCount > 0) {
      playerScore += sosCount;
    } else {
      changeTurns();
    }
    return playerScore;
  }

  private void generalGameOver() {
    if (isBoardFull()) {
      setGeneralGameResult(bluePlayerScore, redPlayerScore);
      gameInProgress = false;
    }
  }

  public void setGeneralGameResult(int bluePlayerScore, int redPlayerScore) {
    if (bluePlayerScore > redPlayerScore) {
      setGameResult("BV");
    } else if (bluePlayerScore < redPlayerScore) {
      setGameResult("RV");
    } else {
      setGameResult("D");
    }
  }
}
