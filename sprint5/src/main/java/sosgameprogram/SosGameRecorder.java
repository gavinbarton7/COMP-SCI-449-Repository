package sosgameprogram;

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
}
