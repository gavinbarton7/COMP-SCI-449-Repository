package sosgameprogram;

public class GeneralGame extends SosGame {
  protected int bluePlayerScore = 0;
  protected int redPlayerScore = 0;

  // Updates the content of an unoccupied cell when either play makes on move on it, but returns
  // false if the cell is occupied
  public boolean setCellContent(int row, int column) {
    if (gameBoard[row][column].equals("")) {
      playerValidMove(row, column);
      return true;
    }
    return false;
  }

  public void playerValidMove(int row, int column) {
    if (currentPlayer.equals("B")) {
      bluePlayerValidMove(row, column);
    } else if (currentPlayer.equals("R")) {
      redPlayerValidMove(row, column);
    }
  }

  @Override
  public void bluePlayerValidMove(int row, int column) {
    if (bluePlayerLetterSelection.equals("S")) {
      gameBoard[row][column] = "S";
      if (checkForSosFormation(row, column) > 0) {
        bluePlayerScore += checkForSosFormation(row, column);
      } else {
        changeTurns();
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        gameInProgress = false;
        return;
      }
    } else if (bluePlayerLetterSelection.equals("O")) {
      gameBoard[row][column] = "O";
      if (checkForSosFormation(row, column) > 0) {
        bluePlayerScore += checkForSosFormation(row, column);
      } else {
        changeTurns();
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        gameInProgress = false;
        return;
      }
    }
  }

  @Override
  public void redPlayerValidMove(int row, int column) {
    if (redPlayerLetterSelection.equals("S")) {
      gameBoard[row][column] = "S";
      if (checkForSosFormation(row, column) > 0) {
        redPlayerScore += checkForSosFormation(row, column);
      } else {
        changeTurns();
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        gameInProgress = false;
        return;
      }
    } else if (redPlayerLetterSelection.equals("O")) {
      gameBoard[row][column] = "O";
      if (checkForSosFormation(row, column) > 0) {
        redPlayerScore += checkForSosFormation(row, column);
      } else {
        changeTurns();
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        gameInProgress = false;
        return;
      }

    }
  }

  public int getBluePlayerScore() {
    return bluePlayerScore;
  }

  public int getRedPlayerScore() {
    return redPlayerScore;
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
