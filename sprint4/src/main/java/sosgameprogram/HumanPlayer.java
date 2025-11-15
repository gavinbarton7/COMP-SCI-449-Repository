package sosgameprogram;

public class HumanPlayer extends Player {
  SosGuiFrame gui;

  public HumanPlayer(String color) {
    super("H", color);
  }

  private void setLetterSelection() {
    playerLetterSelection = gui.selectLetter();
  }

  protected PlayerMove moveSelection(SosGame game) {
    return null;
  }
}
