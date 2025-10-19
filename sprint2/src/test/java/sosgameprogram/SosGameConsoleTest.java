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
  public void testAC1_1_ValidBoardSizeEntered() {
    // Given: the game has not begun
    // (implicit in the setup - new game instance)

    // When: the player enters a value between 3 and 10 in the "Board Size" box
    boolean result = game.setBoardSize(5);

    // Then: the value of the board size will be saved into memory for use once the game begins
    assertTrue(result);
    assertEquals( 5, game.getBoardSize());
  }

  @Test
  public void testAC1_1_ValidBoardSizeBoundaryLower() {
    // Test the lower boundary (3)
    boolean result = game.setBoardSize(3);
    assertTrue(result);
    assertEquals(3, game.getBoardSize());
  }

  @Test
  public void testAC1_1_ValidBoardSizeBoundaryUpper() {
    // Test the upper boundary (10)
    boolean result = game.setBoardSize(10);
    assertTrue(result);
    assertEquals(10, game.getBoardSize());
  }

  @Test
  public void testAC1_1_ValidBoardSizeMiddleRange() {
    // Test a middle value
    boolean result = game.setBoardSize(7);
    assertTrue(result);
    assertEquals(7, game.getBoardSize());
  }

  @Test
  public void testAC1_2_InvalidBoardSizeTooLow() {
    // Test a middle value
    boolean result = game.setBoardSize(2);
    assertFalse(result);
    assertEquals(-1, game.getBoardSize());
  }

  @Test
  public void testAC1_3_InvalidBoardSizeTooHigh() {
    boolean result = game.setBoardSize(11);
    assertFalse(result);
    assertEquals(-1, game.getBoardSize());
  }

  @Test
  public void testAC2_1_SimpleGameModeSelectionWithBoardSize() {
    game.setBoardSize(5);
    boolean result = game.setGameMode("S");
    assertTrue(result);
    assertEquals("S", game.getGameMode());
  }

  @Test
  public void testAC2_2_GeneralGameModeSelectionWithBoardSize() {
    game.setBoardSize(5);
    boolean result = game.setGameMode("G");
    assertTrue(result);
    assertEquals("G", game.getGameMode());
  }

  @Test
  public void testAC2_3_SimpleGameModeSelectionWithoutBoardSize() {
    boolean result = game.setGameMode("S");
    assertFalse(result);
    assertEquals(null, game.getGameMode());
  }

  @Test
  public void testAC2_3_GeneralGameModeSelectionWithoutBoardSize() {
    boolean result = game.setGameMode("G");
    assertFalse(result);
    assertEquals(null, game.getGameMode());
  }

  @Test
  public void testAC6_1_ValidBluePlayerMoveWithSLetterNoSOS() {
    // Given: an ongoing game with the red player's turn
    game.setBoardSize(5);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("B"); // Set to red player's turn
    game.setBluePlayerLetterSelection("S"); // Red player chooses "S"

    // Ensure the target cell is unoccupied
    int testRow = 3;
    int testCol = 4;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When: the blue player selects an unoccupied square
    // And: the move does not result in the formation of an "SOS" sequence on the board
    game.setCellContent(testRow, testCol);

    // Then: the move is recorded on the board by placing "S" on the selected unoccupied square
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And: it becomes the blue player's turn
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_1_ValidBluePlayerMoveWithOLetterNoSOS() {
    // Given: an ongoing game with the red player's turn, red player chooses "O"
    game.setBoardSize(5);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("O");

    int testRow = 3;
    int testCol = 2;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When: the blue player selects an unoccupied square with "O"
    // And: the move does not result in SOS formation
    game.setCellContent(testRow, testCol);

    // Then: the move is recorded with "O"
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And: it becomes the blue player's turn
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_2_ValidRedPlayerMoveWithSLetterNoSOS() {
    // Given: an ongoing game with the red player's turn
    game.setBoardSize(3);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("R"); // Set to red player's turn
    game.setRedPlayerLetterSelection("S"); // Red player chooses "S"

    // Ensure the target cell is unoccupied
    int testRow = 1;
    int testCol = 1;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When: the red player selects an unoccupied square
    // And: the move does not result in the formation of an "SOS" sequence on the board
    game.setCellContent(testRow, testCol);

    // Then: the move is recorded on the board by placing "S" on the selected unoccupied square
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And: it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_2_ValidRedPlayerMoveWithOLetterNoSOS() {
    // Given: an ongoing game with the red player's turn, red player chooses "O"
    game.setBoardSize(3);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    int testRow = 0;
    int testCol = 2;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When: the red player selects an unoccupied square with "O"
    // And: the move does not result in SOS formation
    game.setCellContent(testRow, testCol);

    // Then: the move is recorded with "O"
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And: it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }
}