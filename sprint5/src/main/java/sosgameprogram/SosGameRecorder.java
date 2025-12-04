package sosgameprogram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SosGameRecorder {

  private List<String> movesInRecordedGame;
  private String gameMode;
  private int boardSize;
  private String bluePlayerType;
  private String redPlayerType;
  private boolean isRecordingGame;

  public SosGameRecorder() {
    this.movesInRecordedGame = new ArrayList<>();
    this.isRecordingGame = false;
  }

  public void beginRecordingGame(String gameMode, int boardSize, String bluePlayerType,
                              String redPlayerType) {
    this.gameMode = gameMode;
    this.boardSize = boardSize;
    this.bluePlayerType = bluePlayerType;
    this.redPlayerType = redPlayerType;
    this.isRecordingGame = true;
    this.movesInRecordedGame.clear();
  }

  public void recordMove(String player, int row, int column, String letter) {
    if (isRecordingGame) {
      String move = player + "," + row + "," + column + "," + letter;
      movesInRecordedGame.add(move);
    }
  }

  public boolean saveGameRecordingToOutputFile(String fileName) {

    if (movesInRecordedGame.isEmpty()) {
      return false;
    }

    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(fileName));

      // Writes all of the pre-game configurations to the file
      writer.write("Game Mode:" + gameMode);
      writer.newLine();
      writer.write("Board Size:" + boardSize);
      writer.newLine();
      writer.write("Blue Player Type:" + bluePlayerType);
      writer.newLine();
      writer.write("Red Player Type:" + redPlayerType);
      writer.newLine();


      // This (and following for loop) writes all of the moves made in the game to the file
      writer.write("Moves Made in the Game:");
      writer.newLine();

      for (String move : movesInRecordedGame) {
        writer.write(move);
        writer.newLine();
      }

      writer.flush();
      return true;

    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void stopGameRecording() {
    this.isRecordingGame = false;
  }

  public boolean isGameCurrentlyRecording() {
    return isRecordingGame;
  }

  public int getNumberOfMoves() {
    return movesInRecordedGame.size();
  }
}
