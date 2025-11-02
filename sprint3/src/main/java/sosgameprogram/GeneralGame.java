package sosgameprogram;

public class GeneralGame extends SosGame {
  private int bluePlayerScore = 0;
  private int redPlayerScore = 0;

  @Override
  protected void bluePlayerValidMove(int row, int column) {
    makeMove(row, column, getBluePlayerLetterSelection());
    bluePlayerScore = handleScoring(row, column, bluePlayerScore);
    generalGameOver();
    if (isGameInProgress() == true && getCellContent(row, column).equals("") == false &&
        checkForSosFormation(row, column) == 0) {
      changeTurns();
    }
  }

  @Override
  protected void redPlayerValidMove(int row, int column) {
    makeMove(row, column, getRedPlayerLetterSelection());
    redPlayerScore = handleScoring(row, column, redPlayerScore);
    generalGameOver();
    if (isGameInProgress() == true && getCellContent(row, column).equals("") == false &&
        checkForSosFormation(row, column) == 0) {
      changeTurns();
    }
  }

  private int handleScoring(int row, int column, int playerScore) {
    int sosCount = checkForSosFormation(row, column);
    if (sosCount > 0) {
      playerScore += sosCount;
    }
    return playerScore;
  }

  private void generalGameOver() {
    if (isBoardFull()) {
      setGeneralGameResult(bluePlayerScore, redPlayerScore);
      setGameInProgress(false);
    }
  }

  private void setGeneralGameResult(int bluePlayerScore, int redPlayerScore) {
    if (bluePlayerScore > redPlayerScore) {
      setGameResult("BV");
    } else if (bluePlayerScore < redPlayerScore) {
      setGameResult("RV");
    } else {
      setGameResult("D");
    }
  }
}
