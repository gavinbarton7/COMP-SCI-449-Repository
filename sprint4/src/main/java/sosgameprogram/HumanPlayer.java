package sosgameprogram;

public class HumanPlayer extends Player {
  SosGuiFrame gui;

  private void setLetterSelection() {
    playerLetterSelection = gui.selectLetter();
  }
}
