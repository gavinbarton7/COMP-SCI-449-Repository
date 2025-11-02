package sosgameprogram;

public class SimpleGame extends SosGame {

  @Override
  public void bluePlayerValidMove(int row, int column) {
    makeMove(row, column, bluePlayerLetterSelection);
    simpleGameOver(row, column);
    changeTurns();
  }

  @Override
  public void redPlayerValidMove(int row, int column) {
    makeMove(row, column, redPlayerLetterSelection);
    simpleGameOver(row, column);
    changeTurns();
  }


  public void simpleGameOver(int row, int column) {
    if (checkForSosFormation(row, column) > 0) {
      gameInProgress = false;
      if (getCurrentPlayer().equals("B")) {
        gameResult = "BV";
      } else if (getCurrentPlayer().equals("R")) {
        gameResult = "RV";
      }
    }

    if (isBoardFull() == true) {
      gameInProgress = false;
      gameResult = "D";
    }
  }
}
