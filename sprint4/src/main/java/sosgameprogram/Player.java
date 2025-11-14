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
}
