package sosgameprogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SosGameConsoleTest {
  private SosGameConsole game;

  @BeforeEach
  public void setUp() {
    game = new SosGameConsole();
  }


  @Test
  public void testAC6_2_ValidMoveByRedPlayerNoSOS() {
    // Given an ongoing game with the red player's turn
    game.setBoardSize(3);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setRedPlayerLetterSelection("O");
    game.setCurrentPlayer("R"); // Switch to red player

    // When the red player selects an unoccupied square
    boolean moveResult = game.setCellContent(1, 1);

    // Then the move is recorded and it becomes blue player's turn
    assertTrue(moveResult);
    assertEquals("O", game.getCellContent(1, 1));
    assertEquals("B", game.getCurrentPlayer());
  }

}