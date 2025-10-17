package sosgameprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SosGuiFrame extends JFrame {
  private SosGameConsole game;
  private Board board;

  public SosGuiFrame() {
    this.game = new SosGameConsole();
    this.board = new Board();
    this.setTitle("SOS Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    createTopPanel();
    createBottomPanel();
    createRedPlayerPanel();
    createBluePlayerPanel();

    // Create an object for the SOS game board panel
    Board board = new Board();

    // Adds the "TopPanel" panel to the "NORTH" section of the GUI layout and the checkbox to the
    // "SOUTH" section of the GUI layout
    this.add(board, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
  }

  // Creates top panel of GUI (I decided to put the panels in separate methods for increased
  // modularity)
  public void createTopPanel() {
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

    // Creates the New Game button and a place for the user to input there board size on the GUI
    JButton newGameButton = new JButton("New Game");
    JTextField boardSizeInput = new JTextField(3);
    JLabel boardSizeLabel = new JLabel("Board Size: ");
    topLeftPanel.add(newGameButton);
    topLeftPanel.add(boardSizeLabel);
    topLeftPanel.add(boardSizeInput);

    // Looks for the test in the boardSizeInput text box to see if it is a valid number between 3
    // and 10, and sets the board size if so and displays an error message to the user if not
    boardSizeInput.addActionListener(e -> {
      String sizeText = boardSizeInput.getText().trim();
      if (sizeText.isEmpty() == false) {
        try {
          int size = Integer.parseInt(sizeText);
          if (game.setBoardSize(size) == false) {
            JOptionPane.showMessageDialog(this,
                "Please enter a board size between 3 and 10",
                "Invalid Board Size",
                JOptionPane.WARNING_MESSAGE);
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this,
              "Please enter a valid number for the board size",
              "Invalid Input",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    // Starts a new game when the new button is clicked if the board size and game mode have been
    // selected
    newGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startOfANewGame();
      }
    });

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

    // Tells the program what to do when the user click on the "Simple" radio button
    simpleGameRadioButton.addActionListener(e -> {
      if (game.getBoardSize() == -1) {
        JOptionPane.showMessageDialog(this,
            "Please choose board size before selecting a game mode",
            "No Board Size",
            JOptionPane.WARNING_MESSAGE);
        simpleGeneralGroup.clearSelection();
      } else {
        game.setGameMode("S");
      }
    });

    // Tells the program what to do when the user click on the "General" radio button
    generalGameRadioButton.addActionListener(e -> {
      if (game.getBoardSize() == -1) {
        JOptionPane.showMessageDialog(this,
            "Please choose board size before selecting a game mode",
            "No Board Size",
            JOptionPane.WARNING_MESSAGE);
        simpleGeneralGroup.clearSelection();
      } else {
        game.setGameMode("G");
      }
    });

    // Add the SOS game label and the simple and general game radio buttons to the "topRightPanel"
    // panel
    topRightPanel.add(sosGameLabel);
    topRightPanel.add(simpleGameRadioButton);
    topRightPanel.add(generalGameRadioButton);

    this.add(topPanel, BorderLayout.NORTH);
  }

  public void createBottomPanel() {
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());

    // Creates the checkbox for whether or not the game will be recorded
    JCheckBox recordGamecheckBox = new JCheckBox();
    recordGamecheckBox.setText("Record Game");

    bottomPanel.add(recordGamecheckBox, BorderLayout.WEST);
    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  public void createRedPlayerPanel() {
    // Creates label for the Red player
    JLabel redPlayerLabel = new JLabel();
    redPlayerLabel.setText(" Red Player");

    // Creates radio S and O buttons for red player
    JRadioButton redPlayerSButton = new JRadioButton();
    redPlayerSButton.setText("S");
    JRadioButton redPlayerOButton = new JRadioButton();
    redPlayerOButton.setText("O");

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

    this.add(redPlayerPanel, BorderLayout.WEST);
  }

  public void createBluePlayerPanel () {
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

    this.add(bluePlayerPanel, BorderLayout.EAST);
  }

  public void startOfANewGame() {
    // Checks to see if the board size and game mode have been entered
    if (game.getBoardSize() == -1) {
      JOptionPane.showMessageDialog(this,
          "You must choose a board size to play a game",
          "No Board Size",
          JOptionPane.WARNING_MESSAGE);
      return;
    } else if (game.getGameMode() == null) {
      JOptionPane.showMessageDialog(this,
          "You must choose a game mode to play a game",
          "No Game Mode",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
  }
}
