package sosgameprogram;

public class ComputerPlayer extends Player {
  private SosGameController controller;

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
