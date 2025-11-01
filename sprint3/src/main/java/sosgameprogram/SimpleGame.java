package sosgameprogram;

public class SimpleGame extends SosGame {

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
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) > 0) {
          gameResult = "BV";
          return;
        } else {
          gameResult = "D";
          return;
        }
      }
      changeTurns();
    } else if (bluePlayerLetterSelection.equals("O")) {
      gameBoard[row][column] = "O";
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) > 0) {
          gameResult = "BV";
          return;
        } else {
          gameResult = "D";
          return;
        }
      }
      changeTurns();
    }
  }

  @Override
  public void redPlayerValidMove(int row, int column) {
    if (redPlayerLetterSelection.equals("S")) {
      gameBoard[row][column] = "S";
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) > 0) {
          gameResult = "RV";
          return;
        } else {
          gameResult = "D";
          return;
        }
      }
      changeTurns();
    } else if (redPlayerLetterSelection.equals("O")) {
      gameBoard[row][column] = "O";
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) > 0) {
          gameResult = "RV";
          return;
        } else {
          gameResult = "D";
          return;
        }
      }
      changeTurns();
    }
  }


  public boolean simpleGameOver(int row, int column) {
    if (checkForSosFormation(row, column) > 0) {
      gameInProgress = false;
      return true;
    }

    if (isBoardFull() == true) {
      gameInProgress = false;
      return true;
    }

    return false;
  }
}
