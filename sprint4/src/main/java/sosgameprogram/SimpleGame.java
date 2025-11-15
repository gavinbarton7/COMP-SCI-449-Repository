package sosgameprogram;

public class SimpleGame extends SosGame {

  @Override
  protected void bluePlayerValidMove(int row, int column) {
    makeMove(row, column, getBluePlayerLetterSelection());
    simpleGameOver(row, column);
    if (isGameInProgress() == true && getCellContent(row, column).equals("") == false) {
      changeTurns();
    }
  }

  @Override
  protected void redPlayerValidMove(int row, int column) {
    makeMove(row, column, getRedPlayerLetterSelection());
    simpleGameOver(row, column);
    if (isGameInProgress() == true && getCellContent(row, column).equals("") == false) {
      changeTurns();
    }
  }


  private void simpleGameOver(int row, int column) {
    if (checkForSosFormation(row, column) > 0) {
      setGameInProgress(false);
      if (getCurrentPlayerColor().equals("B")) {
        setGameResult("BV");
      } else if (getCurrentPlayerColor().equals("R")) {
        setGameResult("RV");
      }
    }

    if (isBoardFull() == true) {
      setGameInProgress(false);
      setGameResult("D");
    }
  }
}