package sosgameprogram;

public class HumanPlayer extends Player {
  SosGuiFrame gui;

  private void setLetterSelection() {
    playerLetterSelection = gui.selectLetter();
  }

  protected PlayerMove moveSelection(SosGame game) {
    return null;
  }
}
