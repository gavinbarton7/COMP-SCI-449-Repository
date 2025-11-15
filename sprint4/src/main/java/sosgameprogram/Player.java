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

  protected PlayerMove moveSelection(SosGame game) {
    return null;
  }

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
