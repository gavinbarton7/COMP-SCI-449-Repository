package sosgameprogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SosGameTest {
  private SosGameController controller;

  @BeforeEach
  public void setUp() {
    controller = new SosGameController();
  }

  @Test
  public void testAC1_1_ValidBoardSizeEntered() {
    // Given: the game has not begun
    // (implicit in the setup - new game instance)

    // When: the player enters a value between 3 and 10 in the "Board Size" box
    boolean result = controller.setBoardSize(5);

    // Then: the value of the board size will be saved into memory for use once the game begins
    assertTrue(result);
    assertEquals( 5, controller.getBoardSize());
  }

  @Test
  public void testAC1_1_ValidBoardSizeBoundaryLower() {
    // Test the lower boundary (3)
    boolean result = controller.setBoardSize(3);
    assertTrue(result);
    assertEquals(3, controller.getBoardSize());
  }

  @Test
  public void testAC1_1_ValidBoardSizeBoundaryUpper() {
    // Test the upper boundary (10)
    boolean result = controller.setBoardSize(10);
    assertTrue(result);
    assertEquals(10, controller.getBoardSize());
  }

  @Test
  public void testAC2_1_SimpleGameModeSelectionWithBoardSize() {
    // Given the board size has been chosen
    controller.setBoardSize(5);

    // And the game has not begun

    // When the player selects the “simple game” radio button
    boolean result = controller.setGameMode("S");
    assertTrue(result);

    // Then the game mode is saved into memory for use once the game begins
    assertEquals("S", controller.getGameMode());
  }

  @Test
  public void testAC2_2_GeneralGameModeSelectionWithBoardSize() {
    // Given the board size has been chosen
    controller.setBoardSize(5);

    // And the game has not begun

    // When the player selects the “general game” radio button
    boolean result = controller.setGameMode("G");
    assertTrue(result);

    // Then the game mode is saved into memory for use once the game begins
    assertEquals("G", controller.getGameMode());
  }

  @Test
  public void testAC3_2_InvalidRowIndex() {
    // Given a board of size nxn, with n being the chosen board size
    controller.setBoardSize(7);
    controller.setGameMode("S");
    controller.startOfANewGame();
    int row = 7;
    int col = 5;

    SosGame game = controller.getGame();
    // When a cell is referenced by row index > n
    // Then the cell reference is invalid
    assertEquals("Invalid cell reference", game.getCellContent(row, col));
  }

  @Test
  public void testAC3_3_InvalidColumnIndex() {
    // Given a board of size nxn, with n being the chosen board size
    controller.setBoardSize(6);
    controller.setGameMode("S");
    controller.startOfANewGame();
    int row = 3;
    int col = 6;

    SosGame game = controller.getGame();
    // When a cell is referenced by column index > n
    // Then the cell reference is invalid
    assertEquals("Invalid cell reference", game.getCellContent(row, col));
  }

  @Test
  public void testAC4_1_SimpleGameValidBluePlayerMoveWithSLetter() {
    // Given an ongoing game with the blue player's turn
    controller.setBoardSize(5);
    controller.setGameMode("S");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");

    SosGame game = controller.getGame();

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
    controller.setBoardSize(5);
    controller.setGameMode("S");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("O");


    SosGame game = controller.getGame();
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
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("S");

    SosGame game = controller.getGame();
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
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
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
    controller.setBoardSize(5);
    controller.setGameMode("S");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
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
    controller.setBoardSize(7);
    controller.setGameMode("S");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");

    SosGame game = controller.getGame();
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
    controller.setBoardSize(5);
    controller.setGameMode("G");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B"); // Set to red player's turn
    controller.setBluePlayerLetterSelection("S"); // Red player chooses "S"

    SosGame game = controller.getGame();
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
    controller.setBoardSize(5);
    controller.setGameMode("G");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("O");

    SosGame game = controller.getGame();
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
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R"); // Set to red player's turn
    controller.setRedPlayerLetterSelection("S"); // Red player chooses "S"

    SosGame game = controller.getGame();
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
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
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
    controller.setBoardSize(9);
    controller.setGameMode("G");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 7;
    int testCol = 8;
    game.setCellContent(testRow, testCol);

    // Given an ongoing game with the blue player's turn
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");

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
    controller.setBoardSize(4);
    controller.setGameMode("G");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");

    SosGame game = controller.getGame();
    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 3;
    int testCol = 0;
    game.setCellContent(testRow, testCol);

    // Given an ongoing game with the red player's turn
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");

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