package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class GameRepleyGuiFrame extends JFrame implements GameStateListener {
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

  public GameRepleyGuiFrame(SosGameRecorderAndReplayer.ReplayOfSosGame sosGameReplay) {
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
}
