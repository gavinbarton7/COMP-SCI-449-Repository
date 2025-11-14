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

  protected abstract PlayerMove moveSelection(SosGame game);

  public static class PlayerMove {
    public int row;
    public int column;
    public String letter;

    public PlayerMove(int row, int column, String letter) {
      this.row = row;
      this.column = column;
      this.letter = letter;
    }
  }
}
