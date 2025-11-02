package sosgameprogram;

public class SimpleGame extends SosGame {

  @Override
  public void bluePlayerValidMove(int row, int column) {
    makeMove(row, column, getBluePlayerLetterSelection());
    simpleGameOver(row, column);
    changeTurns();
  }

  @Override
  public void redPlayerValidMove(int row, int column) {
    makeMove(row, column, getRedPlayerLetterSelection());
    simpleGameOver(row, column);
    changeTurns();
  }


  public void simpleGameOver(int row, int column) {
    if (checkForSosFormation(row, column) > 0) {
      setGameInProgress(false);
      if (getCurrentPlayer().equals("B")) {
        setGameResult("BV");
      } else if (getCurrentPlayer().equals("R")) {
        setGameResult("RV");
      }
    }

    if (isBoardFull() == true) {
      setGameInProgress(false);
      setGameResult("D");
    }
  }
}
