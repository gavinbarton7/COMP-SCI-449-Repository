package sosgameprogram;

public class SimpleGame extends SosGame {

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
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) == true) {
          gameResult = "BV";
          return;
        } else {
          gameResult = "D";
          return;
        }
      }
      changeTurns();
    } else if (bluePlayerLetterSelection == "O") {
      gameBoard[row][column] = "O";
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) == true) {
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
    if (redPlayerLetterSelection == "S") {
      gameBoard[row][column] = "S";
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) == true) {
          gameResult = "RV";
          return;
        } else {
          gameResult = "D";
          return;
        }
      }
      changeTurns();
    } else if (redPlayerLetterSelection == "O") {
      gameBoard[row][column] = "O";
      if (simpleGameOver(row, column) == true){
        if (checkForSosFormation(row, column) == true) {
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
    if (checkForSosFormation(row, column) == true) {
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
