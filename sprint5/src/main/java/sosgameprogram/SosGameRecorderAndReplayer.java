package sosgameprogram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SosGameRecorderAndReplayer {

  private List<String> movesInRecordedGame;
  private String gameMode;
  private int boardSize;
  private String bluePlayerType;
  private String redPlayerType;
  private boolean isRecordingGame;

  public SosGameRecorderAndReplayer() {
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
      writer.write("Game Mode: " + gameMode);
      writer.newLine();
      writer.write("Board Size: " + boardSize);
      writer.newLine();
      writer.write("Blue Player Type: " + bluePlayerType);
      writer.newLine();
      writer.write("Red Player Type: " + redPlayerType);
      writer.newLine();


      // This (and following for loop) writes all of the moves made in the game to the file
      writer.write("Moves Made in the Game: ");
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

  public static class ReplayOfSosGame {
    public String gameMode;
    public int boardSize;
    public String bluePlayerType;
    public String redPlayerType;
    public List<ReplayMoveFromGame> gameMoves;

    public ReplayOfSosGame() {
      gameMoves = new ArrayList<>();
    }

    public static class ReplayMoveFromGame {
      public String player;
      public int row;
      public int column;
      public String letter;

      public ReplayMoveFromGame(String player, int row, int column, String letter) {
        this.player = player;
        this.row = row;
        this.column = column;
        this.letter = letter;
      }
    }
  }
  
  public static ReplayOfSosGame loadGameDataFromFile(String fileName) {
    ReplayOfSosGame replay = new ReplayOfSosGame();
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(fileName));
      String line;
      boolean readingMovesFromGame = false;

      while ((line = reader.readLine()) != null) {
        if (line.startsWith("Game Mode: ")) {
          replay.gameMode = line.substring(11).trim();
        } else if (line.startsWith("Board Size: ")) {
          replay.boardSize = Integer.parseInt(line.substring(12).trim());
        } else if (line.startsWith("Blue Player Type: ")) {
          replay.bluePlayerType = line.substring(18).trim();
        } else if (line.startsWith("Red Player Type: ")) {
          replay.redPlayerType = line.substring(17).trim();
        } else if (line.equals("Moves Made in the Game: ")) {
          readingMovesFromGame = true;
        } else if (readingMovesFromGame == true && line.trim().isEmpty() == false) {
          String[] parts = line.split(",");
          if (parts.length == 4) {
            ReplayOfSosGame.ReplayMoveFromGame move = new ReplayOfSosGame.ReplayMoveFromGame(
                parts[0].trim(), Integer.parseInt(parts[1].trim()),
                Integer.parseInt(parts[2].trim()), parts[3].trim()
            );
            replay.gameMoves.add(move);
          }
        }
      }

      return replay;

    } catch (IOException | NumberFormatException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
