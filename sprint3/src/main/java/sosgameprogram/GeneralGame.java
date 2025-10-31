package sosgameprogram;

public class GeneralGame extends SosGame {
  protected int bluePlayerScore = 0;
  protected int redPlayerScore = 0;

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

  @Override
  public void bluePlayerValidMove(int row, int column) {
    if (bluePlayerLetterSelection == "S") {
      gameBoard[row][column] = "S";
      if (checkForSosFormation(row, column) == true) {
        bluePlayerScore++;
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        return;
      }
      changeTurns();
    } else if (bluePlayerLetterSelection == "O") {
      gameBoard[row][column] = "O";
      if (checkForSosFormation(row, column) == true) {
        bluePlayerScore++;
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        return;
      }
      changeTurns();
    }
  }

  @Override
  public void redPlayerValidMove(int row, int column) {
    if (redPlayerLetterSelection == "S") {
      gameBoard[row][column] = "S";
      if (checkForSosFormation(row, column) == true) {
        redPlayerScore++;
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        return;
      }
      changeTurns();
    } else if (redPlayerLetterSelection == "O") {
      gameBoard[row][column] = "O";
      if (checkForSosFormation(row, column) == true) {
        redPlayerScore++;
      }
      if (isBoardFull() == true) {
        setGeneralGameResult(bluePlayerScore, redPlayerScore);
        return;
      }
      changeTurns();
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
