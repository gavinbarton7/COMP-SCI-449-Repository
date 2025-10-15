package sosgameprogram;

import javax.swing.*;
import java.awt.*;

public class SosGuiFrame extends JFrame {
  public SosGuiFrame() {
    this.setTitle("SOS Game");
    this.setTitle("SOS Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // creates panel for top of GUI
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());

    // creates panel for top right of GUI and adds it to the "WEST" section
    // of the top panel
    JPanel topRightPanel = new JPanel();
    JPanel topLeftPanel = new JPanel();
    topPanel.add(topRightPanel, BorderLayout.WEST);
    topPanel.add(topLeftPanel, BorderLayout.EAST);

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
    ButtonGroup simpleGeneralGroup = new ButtonGroup();
    simpleGeneralGroup.add(simpleGameRadioButton);
    simpleGeneralGroup.add(generalGameRadioButton);

    JTextField boardSizeInput = new JTextField(2);
    JLabel boardSizeLabel = new JLabel("Board Size ");
    topLeftPanel.add(boardSizeLabel);
    topLeftPanel.add(boardSizeInput);

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
    Board board = new Board();

    // Adds the "TopPanel" panel to the "NORTH" section of the GUI layout and the checkbox to the
    // "SOUTH" section of the GUI layout
    this.add(topPanel, BorderLayout.NORTH);
    this.add(recordGamecheckBox, BorderLayout.SOUTH);
    this.add(board, BorderLayout.CENTER);
    this.add(redPlayerPanel, BorderLayout.WEST);
    this.add(bluePlayerPanel, BorderLayout.EAST);
    this.pack();
    this.setVisible(true);


  }
}
