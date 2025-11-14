package sosgameprogram;

public class ComputerPlayer extends Player {

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
          // Try placing 'S'
          if (moveThatWillAllowOtherPlayerSOS(game, row, column, "S") == false) {
            return new playerMove(row, column, "S");
          }
          // Try placing 'O'
          if (moveThatWillAllowOtherPlayerSOS(game, row, column, "O") == false) {
            return new playerMove(row, column, "O");
          }
        }
      }
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
