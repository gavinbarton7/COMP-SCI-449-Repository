package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class GameReplayGuiFrame extends JFrame implements GameStateListener {
  private SosGameController controller;
  private Board board;
  private JLabel currentPlayerLabel;
  private JLabel moveCountLabel;
  private SosGameRecorderAndReplayer.ReplayOfSosGame sosGameReplay;
  private int indexOfCurrentMove;
  private Timer replayTimer;
  private JButton playButton;
  private JButton pauseButton;
  private JButton resetButton;
  private boolean isGameReplayPaused;

  @Override
  public void onGameStateChanged() {
  }

  @Override
  public void onGameEnded() {
  }

  public GameReplayGuiFrame(SosGameRecorderAndReplayer.ReplayOfSosGame sosGameReplay) {
    this.sosGameReplay = sosGameReplay;
    this.controller = new SosGameController();
    this.indexOfCurrentMove = 0;
    this.isGameReplayPaused = false;

    this.setTitle("SOS Game - Replay Mode");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.pack();
    this.setLocationRelativeTo(null);
    this.setSize(700, 600);
    this.setVisible(true);
  }

  private void setupGameReplay() {
    controller.setBoardSize(sosGameReplay.boardSize);
    controller.setGameMode(sosGameReplay.gameMode);
    controller.setBluePlayerTypeSelection(sosGameReplay.bluePlayerType);
    controller.setRedPlayerTypeSelection(sosGameReplay.redPlayerType);
    controller.startOfANewGame();
  }

  private void createTopPanel() {
    String gameModeForLabel;
    String bluePlayerTypeForLabel;
    String redPlayerTypeForLabel;

    JPanel topPanel = new JPanel(new BorderLayout());

    if (sosGameReplay.gameMode.equals("S")) {
      gameModeForLabel = "Simple";
    } else if (sosGameReplay.gameMode.equals("G")) {
      gameModeForLabel = "General";
    } else {
      gameModeForLabel = "";
    }

    if (sosGameReplay.bluePlayerType.equals("H")) {
      bluePlayerTypeForLabel = "Human";
    } else if (sosGameReplay.bluePlayerType.equals("C")) {
      bluePlayerTypeForLabel = "Computer";
    } else {
      bluePlayerTypeForLabel = "";
    }

    if (sosGameReplay.redPlayerType.equals("H")) {
      redPlayerTypeForLabel = "Human";
    } else if (sosGameReplay.redPlayerType.equals("C")) {
      redPlayerTypeForLabel = "Computer";
    } else {
      redPlayerTypeForLabel = "";
    }

    JLabel titleLabel = new JLabel("SOS Game Replay      Game Mode: " + (gameModeForLabel) +
        "      Blue Player Type: " + (bluePlayerTypeForLabel) + "      Red Player Type: " +
        (redPlayerTypeForLabel), SwingConstants.CENTER);


    topPanel.add(titleLabel, BorderLayout.CENTER);
    this.add(topPanel, BorderLayout.NORTH);
  }

  private void createGameBoardPanel() {
    board = new Board(controller, this);
    this.add(board, BorderLayout.CENTER);
  }
}
