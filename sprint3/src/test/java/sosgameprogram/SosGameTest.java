package sosgameprogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SosGameTest {
  private SosGame game;

  @BeforeEach
  public void setUp() {
    game = new SosGame();
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
  public void testAC2_1_SimpleGameModeSelectionWithBoardSize() {
    // Given the board size has been chosen
    game.setBoardSize(5);

    // And the game has not begun

    // When the player selects the “simple game” radio button
    boolean result = game.setGameMode("S");
    assertTrue(result);

    // Then the game mode is saved into memory for use once the game begins
    assertEquals("S", game.getGameMode());
  }

  @Test
  public void testAC2_2_GeneralGameModeSelectionWithBoardSize() {
    // Given the board size has been chosen
    game.setBoardSize(5);

    // And the game has not begun

    // When the player selects the “general game” radio button
    boolean result = game.setGameMode("G");
    assertTrue(result);

    // Then the game mode is saved into memory for use once the game begins
    assertEquals("G", game.getGameMode());
  }

  @Test
  public void testAC3_2_InvalidRowIndex() {
    // Given a board of size nxn, with n being the chosen board size
    game.setBoardSize(7);
    game.setGameMode("S");
    game.setUpForNewGame();
    int row = 7;
    int col = 5;

    // When a cell is referenced by row index > n
    // Then the cell reference is invalid
    assertEquals("Invalid cell reference", game.getCellContent(row, col));
  }

  @Test
  public void testAC3_3_InvalidColumnIndex() {
    // Given a board of size nxn, with n being the chosen board size
    game.setBoardSize(6);
    game.setGameMode("S");
    game.setUpForNewGame();
    int row = 3;
    int col = 6;

    // When a cell is referenced by column index > n
    // Then the cell reference is invalid
    assertEquals("Invalid cell reference", game.getCellContent(row, col));
  }

  @Test
  public void testAC4_1_SimpleGameValidBluePlayerMoveWithSLetter() {
    // Given an ongoing game with the blue player's turn
    game.setBoardSize(5);
    game.setGameMode("S");
    game.setUpForNewGame();
    game.setCurrentPlayer("B"); // Set to red player's turn
    game.setBluePlayerLetterSelection("S"); // Red player chooses "S"

    // Ensure the target cell is unoccupied
    int testRow = 3;
    int testCol = 4;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the blue player selects an unoccupied square
    // And the move does not result in the formation of an "SOS" sequence on the board
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And it becomes the red player's turn
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC4_1_SimpleGameValidBluePlayerMoveWithOLetter() {
    // Given an ongoing game with the red player's turn, red player chooses "O"
    game.setBoardSize(5);
    game.setGameMode("S");
    game.setUpForNewGame();
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("O");

    int testRow = 3;
    int testCol = 2;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the blue player selects an unoccupied square with "O"
    // And the move does not result in SOS formation
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And it becomes the red player's turn
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC4_2_SimpleGameValidRedPlayerMoveWithSLetter() {
    // Given an ongoing game with the red player's turn
    game.setBoardSize(3);
    game.setGameMode("S");
    game.setUpForNewGame();
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("S");

    // Ensure the target cell is unoccupied
    int testRow = 1;
    int testCol = 1;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the red player selects an unoccupied square
    // And the move does not result in the formation of an "SOS" sequence on the board
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the red player has selected, is placed on the selected
    // unoccupied square
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC4_2_SimpleGameValidRedPlayerMoveWithOLetter() {
    // Given an ongoing game with the red player's turn
    game.setBoardSize(3);
    game.setGameMode("S");
    game.setUpForNewGame();
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    int testRow = 0;
    int testCol = 2;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the red player selects an unoccupied square
    // And the move does not result in SOS formation
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }


  @Test
  public void testAC4_3_SimpleGameInvalidBluePlayerMoveOnBoard() {
    game.setBoardSize(5);
    game.setGameMode("S");
    game.setUpForNewGame();
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 3;
    int testCol = 3;
    game.setCellContent(testRow, testCol);

    // Given an ongoing game with the blue player's turn
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("S");

    // When the blue player selects an occupied square
    assertEquals("O", game.getCellContent(testRow, testCol));
    boolean result = game.setCellContent(testRow, testCol);
    assertFalse(result);

    // Then the square remains the same
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And the turn is not changed
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC4_4_SimpleGameInvalidRedPlayerMoveOnBoard() {
    // Given an ongoing game with the red player's turn
    game.setBoardSize(7);
    game.setGameMode("S");
    game.setUpForNewGame();
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("S");

    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 4;
    int testCol = 4;
    game.setCellContent(testRow, testCol);

    // Given an ongoing game with the red player's turn
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    // When the red player selects an occupied square
    assertEquals("S", game.getCellContent(testRow, testCol));
    boolean result = game.setCellContent(testRow, testCol);
    assertFalse(result);

    // Then the square remains the same
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And the turn is not changed
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_1_GeneralGameValidBluePlayerMoveWithSLetterNoSOS() {
    // Given an ongoing game with the blue player's turn
    game.setBoardSize(5);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("B"); // Set to red player's turn
    game.setBluePlayerLetterSelection("S"); // Red player chooses "S"

    // Ensure the target cell is unoccupied
    int testRow = 3;
    int testCol = 4;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the blue player selects an unoccupied square
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And it becomes the red player's turn
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_1_GeneralGameValidBluePlayerMoveWithOLetterNoSOS() {
    // Given an ongoing game with the red player's turn, red player chooses "O"
    game.setBoardSize(5);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("O");

    // Ensure the target cell is unoccupied
    int testRow = 3;
    int testCol = 2;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the blue player selects an unoccupied square
    // And the move does not result in SOS formation
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And it becomes the red player's turn
    assertEquals("R", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_2_GeneralGameValidRedPlayerMoveWithSLetterNoSOS() {
    // Given an ongoing game with the red player's turn
    game.setBoardSize(3);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("R"); // Set to red player's turn
    game.setRedPlayerLetterSelection("S"); // Red player chooses "S"

    // Ensure the target cell is unoccupied
    int testRow = 1;
    int testCol = 1;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the red player selects an unoccupied square
    // And the move does not result in the formation of an "SOS" sequence on the board
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_2_GeneralGameValidRedPlayerMoveWithOLetterNoSOS() {
    // Given: an ongoing game with the red player's turn, red player chooses "O"
    game.setBoardSize(3);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    int testRow = 0;
    int testCol = 2;
    assertEquals("", game.getCellContent(testRow, testCol));

    // When the red player selects an unoccupied square
    // And the move does not result in SOS formation
    boolean result = game.setCellContent(testRow, testCol);
    assertTrue(result);

    // Then “S” or “O”, whichever letter the blue player has selected, is placed on the selected
    // unoccupied square
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_3_GeneralGameInvalidBluePlayerMoveOnBoard() {
    game.setBoardSize(9);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 7;
    int testCol = 8;
    game.setCellContent(testRow, testCol);

    // Given an ongoing game with the blue player's turn
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("S");

    // When the blue player selects an occupied square
    assertEquals("O", game.getCellContent(testRow, testCol));
    boolean result = game.setCellContent(testRow, testCol);
    assertFalse(result);

    // Then the square remains the same
    assertEquals("O", game.getCellContent(testRow, testCol));

    // And the turn is not changed
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC6_4_GeneralGameInvalidRedPlayerMoveOnBoard() {
    game.setBoardSize(4);
    game.setGameMode("G");
    game.setUpForNewGame();
    game.setCurrentPlayer("B");
    game.setBluePlayerLetterSelection("S");

    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 3;
    int testCol = 0;
    game.setCellContent(testRow, testCol);

    // Given an ongoing game with the red player's turn
    game.setCurrentPlayer("R");
    game.setRedPlayerLetterSelection("O");

    // When the red player selects an occupied square
    assertEquals("S", game.getCellContent(testRow, testCol));
    boolean result = game.setCellContent(testRow, testCol);
    assertFalse(result);

    // Then the square remains the same
    assertEquals("S", game.getCellContent(testRow, testCol));

    // And the turn is not changed
    assertEquals("R", game.getCurrentPlayer());
  }
}