package sosgameprogram;

public abstract class Player {
  protected String playerLetterSelection;
  protected SosGameController controller;

  public String getPlayerLetterSelection() {
    return playerLetterSelection;
  }

  protected SosGame getCurrentGame() {
    return controller.getGame();
  }

  public static class playerMove {
    public int row;
    public int column;
    public String letter;

    public playerMove(int row, int column, String letter) {
      this.row = row;
      this.column = column;
      this.letter = letter;
    }
  }
}
