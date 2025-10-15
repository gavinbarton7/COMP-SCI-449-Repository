package sosgameprogram;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

  private Board board;

  int boardSize;
  // Note: I will later create a method that sets this variable to "1" for simple game and "2"
  // for general game
  int gameMode;

  public static void main(String[] args) {
    // Creates frame for SOS GUI and sets the frame size
    SosGuiFrame frame = new SosGuiFrame();
    frame.setSize(500,500);
  }

  public void setBoardSize(int sizeInput) {
    boardSize = sizeInput;
  }

}
