package sosgameprogram;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
  private Random random;

  public ComputerPlayer() {
    this.random = new Random();
  }

  // This method implements the computer players strategy for determining it's next move.
  // It first prioritizes forming an SOS on the move so it can win a simple game or get a point
  // and another turn in a general game. If forming an SOS isn't possible, it attempts to avoid
  // any moves that would give its opponenet, the other player, an opportunity to form an
  // SOS sequence on their next move. If the only valid moves left are ones that can't form an SOS
  // sequence nor prevent the other player from forming and SOS sequence on their next turn, then
  // the computer selects a random move of the moves that are left.
  private playerMove moveSelection(SosGame game) {
    int boardSize = game.getBoardSize();

    playerMove formingSOSSequenceMove = findMoveThatFormsSOSSequence(game, boardSize);
    if (formingSOSSequenceMove != null) {
      return formingSOSSequenceMove;
    }

    playerMove preventingOpponentSOSSequenceMove = findMoveToAvoidSOSSequenceByOtherPlayer(game, boardSize);
    if (preventingOpponentSOSSequenceMove != null) {
      return preventingOpponentSOSSequenceMove;
    }

    playerMove randomMove = completelyRandomMove(game, boardSize);
    if (randomMove != null) {
      return randomMove;
    }

    return null;
  }

  private playerMove findMoveThatFormsSOSSequence(SosGame game, int boardSize) {
    for (int row = 0; row < boardSize; row++) {
      for (int column = 0; column < boardSize; column++) {
        if (game.getCellContent(row, column).equals("")) {
          if (moveWouldFormSOSSequence(game, row, column, "S")) {
            return new playerMove(row, column, "S");
          } else if (moveWouldFormSOSSequence(game, row, column, "O")) {
            return new playerMove(row, column, "O");
          }
        }
      }
    }

    return null;
  }

  private playerMove findMoveToAvoidSOSSequenceByOtherPlayer(SosGame game, int boardSize){
    for (int row = 0; row < boardSize; row++) {
      for (int column = 0; column < boardSize; column++) {
        if (game.getCellContent(row, column).equals("")) {
          // Tries placing 'S' on a particular space to see if it would allow other player to form
          // SOS on the next move
          if (moveThatWillAllowOtherPlayerSOS(game, row, column, "S") == false) {
            return new playerMove(row, column, "S");
          }
          // Tries placing 'O' on a particular space to see if it would allow other player to form
          // SOS on the next move
          if (moveThatWillAllowOtherPlayerSOS(game, row, column, "O") == false) {
            return new playerMove(row, column, "O");
          }
        }
      }
    }

    return null;
  }

  // This method implements functionality for selecting a completely random move in a scenario
  // where no move can be made to form an SOS, and any move made on the board will give the other
  // player an opportunity to form an SOS on the next turn
  private playerMove completelyRandomMove(SosGame game, int boardSize) {
    List<playerMove> validBoardMoves = new ArrayList<>();

    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (game.getCellContent(row, col).equals("")) {
          validBoardMoves.add(new playerMove(row, col, "S"));
          validBoardMoves.add(new playerMove(row, col, "O"));
        }
      }
    }

    if (validBoardMoves.isEmpty() == false) {
      return validBoardMoves.get(random.nextInt(validBoardMoves.size()));
    }

    return null;
  }

  // If there is no way the computer player can form an SOS on the move, this
  // method implements the functionality for avoiding moves that would give the other player an
  // opportunity to form and SOS sequence on the next move (avoid "SO" and "OS" with an empty space
  // the other player could place an S in to form an SOS sequence, and avoid having to Ss with an
  // empty space between where the other player could place an O to form an SOS sequence)
  private boolean moveThatWillAllowOtherPlayerSOS(SosGame game, int row, int column,
                                                     String letterSelected) {
    int boardSize = game.getBoardSize();
    String[][] board = game.getGameBoard();

    // Temporarily places a letter in the board space for the purpose of testing potential
    // next moves by the other player after the given move by the computer player
    board[row][column] = letterSelected;

    boolean otherPlayerPotentialSOS = false;

    // Checks all the empty cells on the board to see if the other player could form an SOS
    // sequence after the potential move by the computer player.
    for (int r = 0; r < boardSize && otherPlayerPotentialSOS == false; r++) {
      for (int c = 0; c < boardSize && otherPlayerPotentialSOS == false; c++) {
        if (board[r][c].equals("")) {
          if (moveWouldFormSOSSequence(game, r, c, "S")) {
            otherPlayerPotentialSOS = true;
          }
          if (moveWouldFormSOSSequence(game, r, c, "O")) {
            otherPlayerPotentialSOS = true;
          }
        }
      }
    }

    // Removes the temporary letter placement from the board after checking for potential next
    // moves by the other player is over
    board[row][column] = "";

    return otherPlayerPotentialSOS;
  }

  private boolean moveWouldFormSOSSequence(SosGame game, int row, int column, String letter) {
    int boardSize = game.getBoardSize();
    String[][] board = game.getGameBoard();
    boolean formsSosSequence = false;

    // Checks for potential SOS formation by placing an S to the left of an "OS" to form a
    // horizontal sequence in the form of
    // S O S
    if (column + 2 < boardSize && letter.equals("S")) {
      if (board[row][column + 1].equals("O") && board[row][column + 2].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an O between to Ss to form a
    // horizontal sequence in the form of
    // S O S
    if (column >= 1 && column + 1 < boardSize && letter.equals("O")) {
      if (board[row][column - 1].equals("S") && board[row][column + 1].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S to the right of an "SO" to form a
    // horizontal sequence in the form of
    // S O S
    if (column >= 2 && letter.equals("S")) {
      if (board[row][column - 2].equals("S") && board[row][column - 1].equals("O")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S above an "OS" to form a
    // vertical sequence in the form of
    // S
    // O
    // S
    if (row + 2 < boardSize && letter.equals("S")) {
      if (board[row + 1][column].equals("O") && board[row + 2][column].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an O between two Ss to form a
    // vertical sequence in the form of
    // S
    // O
    // S
    if (row >= 1 && row + 1 < boardSize && letter.equals("O")) {
      if (board[row - 1][column].equals("S") && board[row + 1][column].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S below an "OS" to form a
    // vertical sequence in the form of
    // S
    // O
    // S
    if (row >= 2 && letter.equals("S")) {
      if (board[row - 2][column].equals("S") && board[row - 1][column].equals("O")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S to the top left of an "OS" to form a
    // diagonal sequence in the form of
    // S
    //   O
    //     S
    if (row + 2 < boardSize && column + 2 < boardSize && letter.equals("S")) {
      if (board[row + 1][column + 1].equals("O") && board[row + 2][column + 2].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an O between two Ss to form a
    // diagonal sequence in the form of
    // S
    //   O
    //     S
    if (row >= 1 && row + 1 < boardSize && column >= 1 && column + 1 < boardSize &&
        letter.equals("O")) {
      if (board[row - 1][column - 1].equals("S") && board[row + 1][column + 1].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S to the bottom of an "SO" to form a
    // diagonal sequence in the form of
    // S
    //   O
    //     S
    if (row >= 2 && column >= 2 && letter.equals("S")) {
      if (board[row - 2][column - 2].equals("S") && board[row - 1][column - 1].equals("O")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S to the top right of an "OS" to form a
    // diagonal sequence in the form of
    //     S
    //   O
    // S
    if (row + 2 < boardSize && column >= 2 && letter.equals("S")) {
      if (board[row + 1][column - 1].equals("O") && board[row + 2][column - 2].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an O between two Ss to form a
    // diagonal sequence in the form of
    //     S
    //   O
    // S
    if (row >= 1 && row + 1 < boardSize && column >= 1 && column + 1 < boardSize &&
        letter.equals("O")) {
      if (board[row - 1][column + 1].equals("S") && board[row + 1][column - 1].equals("S")) {
        formsSosSequence = true;
      }
    }

    // Checks for potential SOS formation by placing an S to the  bottom left of an "SO" to form a
    // diagonal sequence in the form of
    //     S
    //   O
    // S
    if (row >= 2 && column + 2 < boardSize && letter.equals("S")) {
      if (board[row - 2][column + 2].equals("S") && board[row - 1][column + 1].equals("O")) {
        formsSosSequence = true;
      }
    }

    return formsSosSequence;
  }
}
