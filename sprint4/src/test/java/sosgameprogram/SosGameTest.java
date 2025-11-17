package sosgameprogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

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
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC4_1_SimpleGameValidBluePlayerMoveWithOLetter() {
    // Given an ongoing game with the red player's turn, red player chooses "O"
    controller.setBoardSize(5);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC4_2_SimpleGameValidRedPlayerMoveWithSLetter() {
    // Given an ongoing game with the red player's turn
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC4_2_SimpleGameValidRedPlayerMoveWithOLetter() {
    // Given an ongoing game with the red player's turn
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("B", controller.getCurrentPlayer());
  }


  @Test
  public void testAC4_3_SimpleGameInvalidBluePlayerMoveOnBoard() {
    controller.setBoardSize(5);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 3;
    int testCol = 3;
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
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC4_4_SimpleGameInvalidRedPlayerMoveOnBoard() {
    // Given an ongoing game with the red player's turn
    controller.setBoardSize(7);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");

    SosGame game = controller.getGame();
    // Creates an occupied set at index 3, 3 to use in this test
    int testRow = 4;
    int testCol = 4;
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
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC5_1_BluePlayerVictory() {
    // Given an ongoing game with no SOS on the board
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();

    game.setCurrentPlayer("B");
    game.setCellContent(0, 0);

    game.setCurrentPlayer("R");
    game.setCellContent(0, 1);

    // When the blue player makes a valid move to form an SOS
    game.setCurrentPlayer("B");
    boolean result = game.setCellContent(0, 2);
    assertTrue(result);
    assertEquals(1, game.checkForSosFormation(0,2));

    // Then the game is over
    assertFalse(game.isGameInProgress());

    // And the blue player has won
    assertEquals("BV", controller.getGameResult());
  }

  @Test
  public void testAC5_2_ContinuingGameAfterBluePlayerMove() {
    // Given an ongoing game with no SOS on the board
    controller.setBoardSize(4);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");

    SosGame game = controller.getGame();

    // When the blue player makes a valid move
    game.setCurrentPlayer("B");
    boolean result = game.setCellContent(0, 0);
    assertTrue(result);

    // And there is still no SOS on the board
    assertEquals(0, game.checkForSosFormation(0, 0));

    // Then the game continues
    assertTrue(game.isGameInProgress());

    // And it is the red player's turn
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC5_3_RedPlayerVictory() {
    // Given an ongoing game with no SOS on the board
    controller.setBoardSize(5);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();

    game.setCurrentPlayer("B");
    game.setCellContent(1, 0);

    game.setCurrentPlayer("R");
    game.setCellContent(1, 1);

    game.setCurrentPlayer("B");
    game.setCellContent(2, 1);

    // When the red player makes a valid move to form an SOS
    game.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("S");
    boolean result = game.setCellContent(1, 2);
    assertTrue(result);
    assertEquals(1, game.checkForSosFormation(1,2));

    // Then the game is over
    assertFalse(game.isGameInProgress());

    // And the red player has won
    assertEquals("RV", controller.getGameResult());
  }

  @Test
  public void testAC5_4_ContinuingGameAfterRedPlayerMove() {
    // Given an ongoing game with no SOS on the board
    controller.setBoardSize(6);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();

    game.setCurrentPlayer("B");
    game.setCellContent(0, 0);

    // When the red player makes a valid move
    game.setCurrentPlayer("R");
    boolean result = game.setCellContent(1, 1);
    assertTrue(result);

    // And there is still no SOS on the board
    assertEquals(0, game.checkForSosFormation(1, 1));

    // Then the game continues
    assertTrue(game.isGameInProgress());

    // And it is the blue player's turn
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC5_5_DrawGame() {
    // Given an ongoing game with no SOS on the board
    // And there is only one unoccupied space left on the board
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("S");

    SosGame game = controller.getGame();

    // Fills the board with Ss except for the bottom right cell
    game.setCurrentPlayer("B");
    game.setCellContent(0, 0);

    game.setCurrentPlayer("R");
    game.setCellContent(0, 1);

    game.setCurrentPlayer("B");
    game.setCellContent(0, 2);

    game.setCurrentPlayer("R");
    game.setCellContent(1, 0);

    game.setCurrentPlayer("B");
    game.setCellContent(1, 1);

    game.setCurrentPlayer("R");
    game.setCellContent(1, 2);

    game.setCurrentPlayer("B");
    game.setCellContent(2, 0);

    game.setCurrentPlayer("R");
    game.setCellContent(2, 1);

    // When either player makes a valid move
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("O");
    boolean result = game.setCellContent(2, 2);
    assertTrue(result);

    // And there is still no SOS on the board after the move
    assertEquals(0, game.checkForSosFormation(2, 2));

    // Then the game is over
    assertFalse(game.isGameInProgress());

    // And it is a draw
    assertEquals("D", controller.getGameResult());
  }



  @Test
  public void testAC6_1_GeneralGameValidBluePlayerMoveWithSLetterNoSOS() {
    // Given an ongoing game with the blue player's turn
    controller.setBoardSize(5);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC6_1_GeneralGameValidBluePlayerMoveWithOLetterNoSOS() {
    // Given an ongoing game with the red player's turn, red player chooses "O"
    controller.setBoardSize(5);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC6_2_GeneralGameValidRedPlayerMoveWithSLetterNoSOS() {
    // Given an ongoing game with the red player's turn
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC6_2_GeneralGameValidRedPlayerMoveWithOLetterNoSOS() {
    // Given an ongoing game with the red player's turn, red player chooses "O"
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC6_3_GeneralGameInvalidBluePlayerMoveOnBoard() {
    controller.setBoardSize(9);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC6_4_GeneralGameInvalidRedPlayerMoveOnBoard() {
    controller.setBoardSize(4);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
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
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC7_1_BluePlayerVictory() {
    // Given an ongoing game where there is only one unoccupied cell left on the board
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
    GeneralGame generalGame = (GeneralGame) game;

    controller.setCurrentPlayer("B");
    game.setCellContent(0, 0);

    controller.setCurrentPlayer("R");
    game.setCellContent(0, 1);

    // Blue player gets on point from forming an SOS
    controller.setCurrentPlayer("B");
    game.setCellContent(0, 2);

    controller.setCurrentPlayer("B");
    game.setCellContent(1, 0); // O

    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("S");
    game.setCellContent(1, 1);

    controller.setCurrentPlayer("B");
    game.setCellContent(1, 2);

    controller.setCurrentPlayer("R");
    game.setCellContent(2, 0);

    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");
    game.setCellContent(2, 1);

    // When the player whose turn it is makes a valid move
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("S");
    game.setCellContent(2, 2);

    int blueScoreBeforeFinal = generalGame.getBluePlayerScore();
    int redScoreBeforeFinal = generalGame.getRedPlayerScore();

    // And the blue player has formed more SOS's than the red player after the final cell is filled
    assertTrue(generalGame.getBluePlayerScore() > generalGame.getRedPlayerScore());

    // Then the game is over
    assertFalse(game.isGameInProgress());

    // And the blue player has won
    assertEquals("BV", controller.getGameResult());
  }

  @Test
  public void testAC7_2_ContinuingGameAfterBluePlayerMoveNoSOS() {
    // Given an ongoing game with more than one unoccupied space on the board
    controller.setBoardSize(4);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");

    SosGame game = controller.getGame();

    // When the blue player makes a valid move
    game.setCurrentPlayer("B");
    boolean result = game.setCellContent(0, 0);
    assertTrue(result);

    // And the move doesn't result in the formation of an SOS sequence on the board
    assertEquals(0, game.checkForSosFormation(0, 0));

    // Then the game continues
    assertTrue(game.isGameInProgress());

    // And it becomes the red player's turn
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC7_3_ContinuingGameAfterBluePlayerMoveWithSOS() {
    // Given an ongoing game with more than one unoccupied space on the board
    controller.setBoardSize(5);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();

    SosGame game = controller.getGame();

    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");
    game.setCellContent(0, 0);

    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");
    game.setCellContent(0, 1);

    // When the blue player makes a valid move that results in the formation of an SOS sequence
    controller.setCurrentPlayer("B");
    controller.setBluePlayerLetterSelection("S");
    boolean result = game.setCellContent(0, 2); // Blue places S to form SOS
    assertTrue(result);
    assertEquals(1, game.checkForSosFormation(0,2));

    // Then the game continues
    assertTrue(game.isGameInProgress());

    // And the blue player gets another turn
    assertEquals("B", controller.getCurrentPlayer());
  }

  @Test
  public void testAC7_4_RedPlayerVictory() {
    // Given an ongoing game where there is only one unoccupied cell left on the board
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("S");

    SosGame game = controller.getGame();
    GeneralGame generalGame = (GeneralGame) game;

    controller.setCurrentPlayer("B");
    controller.setRedPlayerLetterSelection("S");
    game.setCellContent(0, 0);

    controller.setCurrentPlayer("R");
    game.setCellContent(2, 0);

    controller.setCurrentPlayer("B");
    game.setCellContent(0, 1);

    // Red player forms an SOS to score a point
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("O");
    game.setCellContent(1, 0);

    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("S");
    game.setCellContent(1, 1);

    controller.setCurrentPlayer("B");
    game.setCellContent(0, 2);

    controller.setCurrentPlayer("R");
    game.setCellContent(1, 2);

    controller.setCurrentPlayer("B");
    game.setCellContent(2, 1);

    // When the player whose turn it is makes a valid move
    controller.setCurrentPlayer("R");
    game.setCellContent(2, 2);

    // And the red player has formed more SOS's than the blue player after the final cell is filled
    assertTrue(generalGame.getRedPlayerScore() > generalGame.getBluePlayerScore());

    // Then the game is over
    assertFalse(game.isGameInProgress());

    // And the red player has won
    assertEquals("RV", game.getGameResult());
  }

  @Test
  public void testAC7_5_ContinuingGameAfterRedPlayerMoveNoSOS() {
    // Given an ongoing game with more than one unoccupied space on the board
    controller.setBoardSize(6);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();

    controller.setCurrentPlayer("B");
    game.setCellContent(0, 0); // Blue places S

    // When the red player makes a valid move that doesn't result in the formation of an SOS
    // sequence on the board
    controller.setCurrentPlayer("R");
    boolean result = game.setCellContent(1, 1); // Red places O
    assertTrue(result);
    assertEquals(0, game.checkForSosFormation(1, 1));

    // Then the game continues
    assertTrue(game.isGameInProgress());

    // And it becomes the blue player's turn
    assertEquals("B", game.getCurrentPlayer());
  }

  @Test
  public void testAC7_6_ContinuingGameAfterRedPlayerMoveWithSOS() {
    // Given an ongoing game with more than one unoccupied space on the board
    controller.setBoardSize(7);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("S");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();

    controller.setCurrentPlayer("B");
    game.setCellContent(1, 0);

    controller.setCurrentPlayer("R");
    game.setCellContent(1, 1);

    controller.setCurrentPlayer("B");
    game.setCellContent(0, 0);

    // When the red player makes a valid move that results in the formation of an SOS sequence
    // on the board
    controller.setCurrentPlayer("R");
    controller.setRedPlayerLetterSelection("S");
    boolean result = game.setCellContent(1, 2);
    assertTrue(result);
    assertEquals(1, game.checkForSosFormation(1,2));

    // Then the game continues
    assertTrue(game.isGameInProgress());

    // And the red player gets another turn
    assertEquals("R", controller.getCurrentPlayer());
  }

  @Test
  public void testAC7_7_DrawGame() {
    // Given an ongoing game where there is only one unoccupied cell left on the board
    controller.setBoardSize(3);
    controller.setGameMode("G");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();
    controller.setBluePlayerLetterSelection("O");
    controller.setRedPlayerLetterSelection("O");

    SosGame game = controller.getGame();
    GeneralGame generalGame = (GeneralGame) game;

    game.setCurrentPlayer("B");
    game.setCellContent(0, 0);

    game.setCurrentPlayer("R");
    game.setCellContent(0, 1);

    game.setCurrentPlayer("B");
    game.setCellContent(0, 2);

    game.setCurrentPlayer("B");
    game.setCellContent(1, 0);

    game.setCurrentPlayer("R");
    game.setCellContent(1, 1);

    game.setCurrentPlayer("B");
    game.setCellContent(1, 2);

    game.setCurrentPlayer("R");
    game.setCellContent(2, 0);

    game.setCurrentPlayer("B");
    game.setCellContent(2, 1);

    // When either player makes a valid move
    game.setCurrentPlayer("R");
    controller.setBluePlayerLetterSelection("S");
    game.setCellContent(2, 2);

    // And the number of SOS's formed by the blue player after the final cell is filled is equal
    // to the number of SOS's formed by the red player
    assertEquals(generalGame.getBluePlayerScore(), generalGame.getRedPlayerScore());

    // Then the game is over
    assertFalse(game.isGameInProgress());

    // And it is a draw
    assertEquals("D", controller.getGameResult());
  }

  @Test
  public void testAC8_1_HumanVsComputerSetup() {
    // Given no game is in progress
    // And the board size has been selected
    // And the game mode has been selected
    controller.setBoardSize(3);
    controller.setGameMode("S");

    // When the player type, human or computer, is selected for both players
    // And the "New Game" button is selected
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("C");
    controller.startOfANewGame();

    // Then the two players are set up with functionality based on the type
    SosGame game = controller.getGame();
    assertNotNull(game);
    assertTrue(game.isGameInProgress());

    // Check that the correct player objects were created
    controller.setCurrentPlayer("B");
    assertEquals("H", controller.getCurrentPlayerType());
    assertTrue(controller.getObjectOfCurrentPlayer() instanceof HumanPlayer);

    controller.setCurrentPlayer("R");
    assertEquals("C", controller.getCurrentPlayerType());
    assertTrue(controller.getObjectOfCurrentPlayer() instanceof ComputerPlayer);
  }

  @Test
  public void testAC8_1_ComputerVsComputerSetup() {
    // Given no game is in progress
    // And the board size has been selected
    // And the game mode has been selected
    controller.setBoardSize(3);
    controller.setGameMode("G");

    // When the player type, human or computer, is selected for both players
    // And the "New Game" button is selected
    controller.setBluePlayerTypeSelection("C");
    controller.setRedPlayerTypeSelection("C");
    controller.startOfANewGame();

    // Then the two players are set up with functionality based on the type
    SosGame game = controller.getGame();
    assertNotNull(game);
    assertTrue(game.isGameInProgress());

    controller.setCurrentPlayer("B");
    assertEquals("C", controller.getCurrentPlayerType());
    assertTrue(controller.getObjectOfCurrentPlayer() instanceof ComputerPlayer);

    controller.setCurrentPlayer("R");
    assertEquals("C", controller.getCurrentPlayerType());
    assertTrue(controller.getObjectOfCurrentPlayer() instanceof ComputerPlayer);
  }

  @Test
  public void testAC9_1_ComputerFormsHorizontalSOS() {
    // Given an ongoing game
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("C");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();

    SosGame game = controller.getGame();

    // Set up the board for a winning move:
    // S O _
    // _ _ _
    // _ _ _
    // Manually make moves to set up the board
    game.makeMove(0, 0, "S");
    game.makeMove(0, 1, "O");

    // When it's the computer player's turn
    controller.setCurrentPlayer("B");

    // Then the computer player makes the move to form an SOS
    Player.PlayerMove move = controller.moveByComputerPlayer();

    // Assert the computer chose the winning move
    assertNotNull(move);
    assertEquals(0, move.row);
    assertEquals(2, move.column);
    assertEquals("S", move.letter);
  }

  @Test
  public void testAC9_1_ComputerFormsVerticalSOSWithO() {
    // Given an ongoing game
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("H");
    controller.setRedPlayerTypeSelection("C"); // Red is Computer
    controller.startOfANewGame();

    SosGame game = controller.getGame();

    // Set up the board for a winning move:
    // S _ _
    // _ _ _
    // S _ _
    game.makeMove(0, 0, "S");
    game.makeMove(2, 0, "S");

    // When it's the computer player's turn
    controller.setCurrentPlayer("R");

    // Then the computer player makes the move to form an SOS
    Player.PlayerMove move = controller.moveByComputerPlayer();

    // Assert the computer chose the winning move
    assertNotNull(move);
    assertEquals(1, move.row);
    assertEquals(0, move.column);
    assertEquals("O", move.letter);
  }

  @Test
  public void testAC9_2_ComputerAvoidsAllOpponentSOSTraps() {
    // Given an ongoing game
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("C");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();

    SosGame game = controller.getGame();

    // Set up the board with an opponent's 'S' at (0, 0)
    // S _ _
    // _ _ _
    // _ _ _
    // This creates six "unsafe" moves for the computer (Blue)
    // that would allow Red to win on the next turn.
    game.makeMove(0, 0, "S"); // Simulate Red's move

    // When it's the computer player's turn
    controller.setCurrentPlayer("B");

    // And the computer determines there is no option to form an SOS (AC 9.1 fails)
    // And the computer determines there are "safe" moves
    Player.PlayerMove move = controller.moveByComputerPlayer();

    // Then the computer player selects a "safe" move
    assertNotNull(move);

    // Asserts the computer did NOT make any of the "unsafe" moves
    String actualMoveSignature = "R:" + move.row + " C:" + move.column + " L:" + move.letter;

    // Define all 6 possible unsafe moves
    Set<String> unsafeMoves = new HashSet<>();
    // S O S unsafe moves (playing an 'O')
    unsafeMoves.add("R:0 C:1 L:O"); // Horizontal move: S O _
    unsafeMoves.add("R:1 C:0 L:O"); // Vertical move: S O _ (down)
    unsafeMoves.add("R:1 C:1 L:O"); // Diagonal move: S O _ (diag)
    // S _ S unsafe moves (playing an 'S')
    unsafeMoves.add("R:0 C:2 L:S"); // Horizontal move: S _ S
    unsafeMoves.add("R:2 C:0 L:S"); // Vertical move: S _ S (down)
    unsafeMoves.add("R:2 C:2 L:S"); // Diagonal move: S _ S (diag)


    assertFalse(unsafeMoves.contains(actualMoveSignature));
  }

  @Test
  public void testAC9_3_ComputerMakesRandomMoveOnEmptyBoard() {
    // Given an ongoing game
    controller.setBoardSize(3);
    controller.setGameMode("S");
    controller.setBluePlayerTypeSelection("C");
    controller.setRedPlayerTypeSelection("H");
    controller.startOfANewGame();

    // When the computer player scans the board and finds that every cell is unoccupied
    controller.setCurrentPlayer("B");

    // Then the computer player randomly selects a move
    Player.PlayerMove move = controller.moveByComputerPlayer();

    // Assert that *a* valid move was made
    assertNotNull(move);
    assertTrue(move.row >= 0 && move.row < 3);
    assertTrue(move.column >= 0 && move.column < 3);
    assertTrue(move.letter.equals("S") || move.letter.equals("O"));
  }
}

