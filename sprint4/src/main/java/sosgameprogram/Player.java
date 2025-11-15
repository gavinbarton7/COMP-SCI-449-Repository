package sosgameprogram;

public abstract class Player {
  protected String playerLetterSelection;
  protected String playerType;
  protected String playerColor;

  public Player(String type, String color) {
    this.playerType = type;
    this.playerColor = color;
  }

  public String getPlayerColor() {
    return playerColor;
  }

  public String getPlayerType() {
    return playerType;
  }

  public String getPlayerLetterSelection() {
    return playerLetterSelection;
  }

  protected abstract void setPlayerLetterSelection();

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
