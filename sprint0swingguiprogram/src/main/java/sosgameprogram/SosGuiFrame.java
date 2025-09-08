package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class SosGuiFrame extends JFrame {
  public SosGuiFrame() {
    this.setTitle("SOS Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // creates panel for top of GUI
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());

    // creates panel for top right of GUI and adds it to the "WEST" section
    // of the top panel
    JPanel topRightPanel = new JPanel();
    topPanel.add(topRightPanel, BorderLayout.WEST);

    // Creates a label for the SOS Game
    JLabel sosGameLabel = new JLabel();
    sosGameLabel.setText("SOS Game");

    // Creates the checkbox for whether or not the game will be recorded
    JCheckBox recordGamecheckBox = new JCheckBox();
    recordGamecheckBox.setText("Record Game");

    // Creates the radio buttons for choosing simple or general game
    JRadioButton simpleGameRadioButton = new JRadioButton();
    simpleGameRadioButton.setText("Simple Game");
    JRadioButton generalGameRadioButton = new JRadioButton();
    generalGameRadioButton.setText("General Game");
    simpleGameRadioButton.setBounds(200,200,10,10);

    // Creates a button group for the simple and general game radio buttons and adds them to the
    // group
    ButtonGroup simpleGenralGroup = new ButtonGroup();
    simpleGenralGroup.add(simpleGameRadioButton);
    simpleGenralGroup.add(generalGameRadioButton);

    // Add the SOS game label and the simple and general game radio buttons to the "topRightPanel"
    // panel
    topRightPanel.add(sosGameLabel);
    topRightPanel.add(generalGameRadioButton);
    topRightPanel.add(simpleGameRadioButton);

    // Creates radio S and O buttons for player 1
    JRadioButton redPlayerSButton = new JRadioButton();
    redPlayerSButton.setText("S");
    JRadioButton redPlayerOButton = new JRadioButton();
    redPlayerOButton.setText("O");

    // Creates label for the Red player
    JLabel redPlayerLabel = new JLabel();
    redPlayerLabel.setText(" Red Player");

    // Creates a group for the player buttons and adds the buttons for the red player
    ButtonGroup redPlayerSOGroup = new ButtonGroup();
    redPlayerSOGroup.add(redPlayerSButton);
    redPlayerSOGroup.add(redPlayerOButton);

    // Creates a panel that holds the SO buttons for the red player
    JPanel redPlayerPanel = new JPanel();
    redPlayerPanel.setLayout(new BoxLayout(redPlayerPanel, BoxLayout.Y_AXIS));
    redPlayerPanel.add(redPlayerLabel);
    redPlayerPanel.add(redPlayerSButton);
    redPlayerPanel.add(redPlayerOButton);

    // Creates radio S and O buttons for the blue player
    JRadioButton bluePlayerSButton = new JRadioButton();
    bluePlayerSButton.setText("S");
    JRadioButton bluePlayerOButton = new JRadioButton();
    bluePlayerOButton.setText("O");

    // Creates label for the Blue player
    JLabel bluePlayerLabel = new JLabel();
    bluePlayerLabel.setText(" Blue Player ");

    // Creates a group for the player buttons and adds the buttons for the blue player
    ButtonGroup bluePlayerSOGroup = new ButtonGroup();
    bluePlayerSOGroup.add(bluePlayerSButton);
    bluePlayerSOGroup.add(bluePlayerOButton);

    // Creates a panel that holds the SO buttons for the blue player
    JPanel bluePlayerPanel = new JPanel();
    bluePlayerPanel.setLayout(new BoxLayout(bluePlayerPanel, BoxLayout.Y_AXIS));
    bluePlayerPanel.add(bluePlayerLabel);
    bluePlayerPanel.add(bluePlayerSButton);
    bluePlayerPanel.add(bluePlayerOButton);

    // Create an object for the SOS game board panel
    SosGamePanel SosGamePanel = new SosGamePanel();

    // Adds the "TopPanel" panel to the "NORTH" section of the GUI layout and the checkbox to the
    // "SOUTH" section of the GUI layout
    this.add(topPanel, BorderLayout.NORTH);
    this.add(recordGamecheckBox, BorderLayout.SOUTH);
    this.add(SosGamePanel, BorderLayout.CENTER);
    this.add(redPlayerPanel, BorderLayout.WEST);
    this.add(bluePlayerPanel, BorderLayout.EAST);
    this.pack();
    this.setVisible(true);
  }


  // This class creates a Panel for the SOS game board by drawing an 8x8 grid
  private class SosGamePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics board) {
      super.paintComponent(board);

      // Sets the color of the board to black
      board.setColor(Color.BLACK);

      // Draws the horizontal lines of SOS game board
      board.drawLine(25, 50, 225, 50);
      board.drawLine(25, 75, 225, 75);
      board.drawLine(25, 100, 225, 100);
      board.drawLine(25, 125, 225, 125);
      board.drawLine(25, 150, 225, 150);
      board.drawLine(25, 175, 225, 175);
      board.drawLine(25, 200, 225, 200);
      board.drawLine(25, 225, 225, 225);
      board.drawLine(25, 250, 225, 250);

      // Draws the vertical lines of SOS game board
      board.drawLine(25, 50, 25, 250);
      board.drawLine(50, 50, 50, 250);
      board.drawLine(75, 50, 75, 250);
      board.drawLine(100, 50, 100, 250);
      board.drawLine(125, 50, 125, 250);
      board.drawLine(150, 50, 150, 250);
      board.drawLine(175, 50, 175, 250);
      board.drawLine(200, 50, 200, 250);
      board.drawLine(225, 50, 225, 250);
    }
  }
}
