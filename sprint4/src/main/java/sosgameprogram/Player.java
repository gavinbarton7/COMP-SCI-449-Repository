package sosgameprogram;

public abstract class Player {
  protected String playerLetterSelction;
  protected SosGameController controller;

  protected void setPlayerLetterSelction(String selectedLetter) {
    playerLetterSelction = selectedLetter;
  }

  protected SosGame getCurrentGame() {
    return controller.getGame();
  }
}
