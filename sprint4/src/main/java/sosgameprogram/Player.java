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

  // This method returns null by default because some player types such as Human Players
  // or other player types that may be implemented in future changes to this code,
  // do not have any specific logic outside of the internal game and GUI logic that is needed
  // to select a move, the moveSelection method for them will return null. However,
  // other player types, such as Computer Players or other player types that may be implemented
  // in future changes to this code, have special functionality that determines which move they
  // will make, and this class can be overidden in the ComputerPlayer subclass or other
  // subclasses that might be used in future changes to this program to implement there
  // specific logic for move selection.
  protected PlayerMove moveSelection(SosGame game) {
    return null;
  }

  // This class creates objects that store moves by a player.
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
