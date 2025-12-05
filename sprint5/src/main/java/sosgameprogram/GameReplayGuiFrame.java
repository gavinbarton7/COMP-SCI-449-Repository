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
    updateCurrentPlayerLabel();
  }

  @Override
  public void onGameEnded() {
    if (replayTimer != null) {
      replayTimer.stop();
    }
    JOptionPane.showMessageDialog(this,
        getReplayedGameResultMessage(),
        "Replay Finished Message",
        JOptionPane.INFORMATION_MESSAGE);
  }

  public GameReplayGuiFrame(SosGameRecorderAndReplayer.ReplayOfSosGame sosGameReplay) {
    this.sosGameReplay = sosGameReplay;
    this.controller = new SosGameController();
    this.indexOfCurrentMove = 0;
    this.isGameReplayPaused = false;

    this.setTitle("SOS Game - Replay Mode");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());

    setupGameReplay();
    createTopPanel();
    createGameBoardPanel();
    createBottomPanel();

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

  private void createBottomPanel() {
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

    // Creates a game replay status panel that indicates which player is about to make a move based
    // (in other words, it shows what the current turn label said at a given point when the game
    // was originally played live) and the number of moves that have been made in the replay so far
    JPanel statusPanel = new JPanel(new GridLayout(2, 1));

    currentPlayerLabel = new JLabel("Current turn: Blue", SwingConstants.CENTER);
    currentPlayerLabel.setForeground(Color.BLUE);

    moveCountLabel = new JLabel("Moves made so far: 0 out of " +
        sosGameReplay.gameMoves.size(), SwingConstants.CENTER);

    statusPanel.add(currentPlayerLabel);
    statusPanel.add(moveCountLabel);

    // Creates a replay controls panel with a play button to start, a pause button to pause in
    // the middle of a replay, and a reset button to clear the board on the replay gui frame and
    // start the replay over by clicking the "play" button again.
    JPanel replayControlsPanel = new JPanel(new FlowLayout());

    resetButton = new JButton("Reset");
    playButton = new JButton("Play");
    pauseButton = new JButton("Pause");

    playButton.addActionListener(e -> playGameReplay());
    pauseButton.addActionListener(e -> pauseGameReplay());
    resetButton.addActionListener(e -> resetGameReplay());

    replayControlsPanel.add(playButton);
    replayControlsPanel.add(pauseButton);
    replayControlsPanel.add(resetButton);

    bottomPanel.add(statusPanel);
    bottomPanel.add(replayControlsPanel);

    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  // Changes the label for the current player after each move
  private void updateCurrentPlayerLabel() {
    String currentPlayerColor = controller.getCurrentPlayer();
    if (currentPlayerColor.equals("B")) {
      currentPlayerLabel.setText("Current turn: Blue");
      currentPlayerLabel.setForeground(Color.BLUE);
    } else if (currentPlayerColor.equals("R")) {
      currentPlayerLabel.setText("Current turn: Red");
      currentPlayerLabel.setForeground(Color.RED);
    }
  }

  // Used for play the game replay from the beginning or resuming it when the "play"
  // button is clicked
  private void playGameReplay() {
    if (indexOfCurrentMove >= sosGameReplay.gameMoves.size()) {
      resetGameReplay();
      return;
    }

    isGameReplayPaused = false;

    replayTimer = new Timer(1000, e -> {
      if (isGameReplayPaused == false && indexOfCurrentMove < sosGameReplay.gameMoves.size()) {
        executeReplayedGameMove(indexOfCurrentMove);
        indexOfCurrentMove++;
        updateMoveCount();

        if (indexOfCurrentMove >= sosGameReplay.gameMoves.size()) {
          ((Timer)e.getSource()).stop();
          onGameEnded();
        }
      }
    });
    replayTimer.start();
  }

  // Used for pausing in the middle of a game replay (when pause button is clicked)
  private void pauseGameReplay() {
    isGameReplayPaused = true;
    if (replayTimer != null) {
      replayTimer.stop();
    }
  }

  // Used for resetting the replay board to be blank and restarting the timer between moves
  // when the "Reset" button, is clicked
  private void resetGameReplay() {
    if (replayTimer != null) {
      replayTimer.stop();
    }
    indexOfCurrentMove = 0;
    isGameReplayPaused = false;

    setupGameReplay();
    board.newBoard();
    updateCurrentPlayerLabel();
    updateMoveCount();
  }

  // Used for painting each move on the board during the game replay
  private void executeReplayedGameMove(int index) {
    SosGameRecorderAndReplayer.ReplayOfSosGame.ReplayMoveFromGame currentMove =
        sosGameReplay.gameMoves.get(index);

    controller.setCurrentPlayer(currentMove.player);

    if (currentMove.player.equals("B")) {
      controller.setBluePlayerLetterSelection(currentMove.letter);
    } else {
      controller.setRedPlayerLetterSelection(currentMove.letter);
    }

    controller.getGame().setCellContent(currentMove.row, currentMove.column);
    board.repaint();
    updateCurrentPlayerLabel();
  }

  // Used to update the move count label after each move
  private void updateMoveCount() {
    moveCountLabel.setText("Moves made so far: " + (indexOfCurrentMove) + " out of " +
        sosGameReplay.gameMoves.size());
  }

  private String getReplayedGameResultMessage() {
    String replayedGameResult = controller.getGameResult();

    if (replayedGameResult.equals("BV")) {
     return "The blue player won this game.";
    } else if (replayedGameResult.equals("RV")) {
      return "The blue player won this game.";
    } else if (replayedGameResult.equals("D")) {
      return "The blue player won this game.";
    } else {
      return "";
    }
  }
}
