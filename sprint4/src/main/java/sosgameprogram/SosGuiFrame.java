package sosgameprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class SosGuiFrame extends JFrame implements GameStateListener {
  private SosGameController controller;
  private Board board;
  private JLabel currentPlayerLabel;
  private JRadioButton simpleGameRadioButton;
  private JRadioButton generalGameRadioButton;
  private ButtonGroup simpleGeneralGroup;
  private JTextField boardSizeInput;
  private JButton newGameButton;
  ButtonGroup bluePlayerSOGroup;
  ButtonGroup redPlayerSOGroup;
  ButtonGroup bluePlayerHumanComputerGroup;
  ButtonGroup redPlayerHumanComputerGroup;

  @Override
  public void onGameStateChanged() {
    updateCurrentPlayerLabel();
  }

  @Override
  public void onGameEnded() {
    endOfGameMessage();
  }

  public SosGuiFrame() {
    this.controller = new SosGameController();
    this.setTitle("SOS Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    createTopPanel();
    createBottomPanel();
    createRedPlayerPanel();
    createBluePlayerPanel();
    createGameBoardPanel();

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

    // Adds the New Game button and a place for the user to input there board size on the GUI
    newGameButton = new JButton("New Game");
    boardSizeInput = new JTextField(3);
    JLabel boardSizeLabel = new JLabel("Board Size: ");
    topLeftPanel.add(newGameButton);
    topLeftPanel.add(boardSizeLabel);
    topLeftPanel.add(boardSizeInput);

    // Looks for the test in the boardSizeInput text box to see if it is a valid number between 3
    // and 10, and sets the board size if so and displays an error message to the user if not
    boardSizeInput.addActionListener(e -> {
      boardSizeEntered();
    });

    // Starts a new game when the new button is clicked if the board size and game mode have been
    // selected
    newGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startNewGame();
      }
    });

    // Sets up the labels for the radio buttons for choosing simple or general game
    simpleGameRadioButton = new JRadioButton();
    simpleGameRadioButton.setText("Simple Game");
    generalGameRadioButton = new JRadioButton();
    generalGameRadioButton.setText("General Game");

    // Adds the simple and general radio buttons to the simpleGeneral button group
    simpleGeneralGroup = new ButtonGroup();
    simpleGeneralGroup.add(simpleGameRadioButton);
    simpleGeneralGroup.add(generalGameRadioButton);

    // Tells the program what to do when the user click on the "Simple" radio button
    simpleGameRadioButton.addActionListener(e -> {
      simpleRadioButtonClicked();
    });

    generalGameRadioButton.addActionListener(e -> {
      generalRadioButtonClicked();
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
    bottomPanel.setLayout(new BorderLayout());

    // Creates the checkbox for whether or not the game will be recorded
    JCheckBox recordGamecheckBox = new JCheckBox();
    recordGamecheckBox.setText("Record Game");

    currentPlayerLabel = new JLabel("Current Player: Blue", SwingConstants.CENTER);
    currentPlayerLabel.setForeground(Color.BLUE);

    bottomPanel.add(recordGamecheckBox, BorderLayout.WEST);
    bottomPanel.add(currentPlayerLabel);
    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  public void createRedPlayerPanel() {
    // Creates label for the Red player
    JLabel redPlayerLabel = new JLabel();
    redPlayerLabel.setText(" Red Player ");

    // Creates radio S and O buttons for red player
    JRadioButton redPlayerSButton = new JRadioButton();
    redPlayerSButton.setText("S");
    redPlayerSButton.setBorder(new EmptyBorder(10, 20, 5, 0));
    JRadioButton redPlayerOButton = new JRadioButton();
    redPlayerOButton.setText("O");
    redPlayerOButton.setBorder(new EmptyBorder(5, 20, 10, 0));


    JRadioButton redPlayerHumanButton = new JRadioButton();
    redPlayerHumanButton.setText("Human");
    JRadioButton redPlayerComputerButton = new JRadioButton();
    redPlayerComputerButton.setText("Computer");

    // Adds action listener that set the letter selection for the red player based on which
    // letter radio button they have selected
    redPlayerSButton.addActionListener(e ->
        controller.setRedPlayerLetterSelection("S")
    );
    redPlayerOButton.addActionListener(e ->
        controller.setRedPlayerLetterSelection("O")
    );

    // Adds action listeners that set the player type for the red player based on which player
    // type radio button they have selected
    redPlayerHumanButton.addActionListener( e ->
        controller.setRedPlayerType("H")
    );

    redPlayerComputerButton.addActionListener( e ->
        controller.setRedPlayerType("C")
    );

    // Creates a group for the player buttons and adds the buttons for the red player
    redPlayerSOGroup = new ButtonGroup();
    redPlayerSOGroup.add(redPlayerSButton);
    redPlayerSOGroup.add(redPlayerOButton);

    JLabel spacingLabel = new JLabel();
    spacingLabel.setText(" ");

    redPlayerHumanComputerGroup = new ButtonGroup();
    redPlayerHumanComputerGroup.add(redPlayerHumanButton);
    redPlayerHumanComputerGroup.add(redPlayerComputerButton);

    // Creates a panel that holds the SO buttons for the red player
    JPanel redPlayerPanel = new JPanel();
    redPlayerPanel.setLayout(new BoxLayout(redPlayerPanel, BoxLayout.Y_AXIS));
    redPlayerPanel.add(redPlayerLabel);
    redPlayerPanel.add(redPlayerHumanButton);
    redPlayerPanel.add(redPlayerSButton);
    redPlayerPanel.add(redPlayerOButton);
    redPlayerPanel.add(redPlayerComputerButton);

    this.add(redPlayerPanel, BorderLayout.EAST);
  }

  public void createBluePlayerPanel () {
    // Creates radio S and O buttons for the blue player
    JRadioButton bluePlayerSButton = new JRadioButton();
    bluePlayerSButton.setText("S");
    bluePlayerSButton.setBorder(new EmptyBorder(10, 20, 5, 0));
    JRadioButton bluePlayerOButton = new JRadioButton();
    bluePlayerOButton.setBorder(new EmptyBorder(5, 20, 10, 0));
    bluePlayerOButton.setText("O");

    JRadioButton bluePlayerHumanButton = new JRadioButton();
    bluePlayerHumanButton.setText("Human");
    JRadioButton bluePlayerComputerButton = new JRadioButton();
    bluePlayerComputerButton.setText("Computer");

    // Creates label for the Blue player
    JLabel bluePlayerLabel = new JLabel();
    bluePlayerLabel.setText(" Blue Player ");

    // Creates a group for the player buttons and adds the buttons for the blue player
    bluePlayerSOGroup = new ButtonGroup();
    bluePlayerSOGroup.add(bluePlayerSButton);
    bluePlayerSOGroup.add(bluePlayerOButton);

    bluePlayerHumanComputerGroup = new ButtonGroup();
    bluePlayerHumanComputerGroup.add(bluePlayerHumanButton);
    bluePlayerHumanComputerGroup.add(bluePlayerComputerButton);

    // Adds action listeners that set the letter selection for the blue player based on which
    // letter radio button they have selected
    bluePlayerSButton.addActionListener(e ->
        controller.setBluePlayerLetterSelection("S")
    );
    bluePlayerOButton.addActionListener(e ->
        controller.setBluePlayerLetterSelection("O")
    );


    // Adds action listeners that set the player type for the blue player based on which player
    // type radio button they have selected
    bluePlayerHumanButton.addActionListener( e ->
        controller.setBluePlayerType("H")
    );

    bluePlayerComputerButton.addActionListener( e ->
        controller.setBluePlayerType("C")
    );

    // Creates a panel that holds the SO buttons for the blue player
    JPanel bluePlayerPanel = new JPanel();
    bluePlayerPanel.setLayout(new BoxLayout(bluePlayerPanel, BoxLayout.Y_AXIS));
    bluePlayerPanel.add(bluePlayerLabel);
    bluePlayerPanel.add(bluePlayerHumanButton);
    bluePlayerPanel.add(bluePlayerSButton);
    bluePlayerPanel.add(bluePlayerOButton);
    bluePlayerPanel.add(bluePlayerComputerButton);

    this.add(bluePlayerPanel, BorderLayout.WEST);
  }

  public void startNewGame() {
    // Checks to see if the board size and game mode have been entered
    if (controller.getBoardSize() == -1) {
      JOptionPane.showMessageDialog(this,
          "You must choose a board size to play a game",
          "No Board Size",
          JOptionPane.WARNING_MESSAGE);
    } else if (controller.getGameMode() == null) {
      JOptionPane.showMessageDialog(this,
          "You must choose a game mode to play a game",
          "No Game Mode",
          JOptionPane.WARNING_MESSAGE);
    } else if (controller.getbluePlayerType() == null || controller.getRedPlayerType() == null) {
      JOptionPane.showMessageDialog(this,
          "Please select player type for both players",
          "Player Type Not Selected",
          JOptionPane.WARNING_MESSAGE);
    } else {
      bluePlayerSOGroup.clearSelection();
      redPlayerSOGroup.clearSelection();
      board.newBoard();
      controller.startOfANewGame();
      updateCurrentPlayerLabel();
    }
  }

  public void createGameBoardPanel() {
    // Create an object for the SOS game board panel
    board = new Board(controller, this);

    // Adds the "TopPanel" panel to the "NORTH" section of the GUI layout and the checkbox to the
    // "SOUTH" section of the GUI layout
    this.add(board, BorderLayout.CENTER);
  }

  // Changes the label for the current player after each move
  public void updateCurrentPlayerLabel() {
    String currentPlayer = controller.getCurrentPlayer();
    if (currentPlayer.equals("B")) {
      currentPlayerLabel.setText("Current turn: Blue");
      currentPlayerLabel.setForeground(Color.BLUE);
    } else if (currentPlayer.equals("R")) {
      currentPlayerLabel.setText("Current turn: Red");
      currentPlayerLabel.setForeground(Color.RED);
    }
  }

  public void simpleRadioButtonClicked() {
    if (controller.setGameMode("S") == false) {
      JOptionPane.showMessageDialog(this,
              "Please choose board size before selecting a game mode",
              "No Board Size",
              JOptionPane.WARNING_MESSAGE);
      simpleGeneralGroup.clearSelection();
    }
  }

  public void generalRadioButtonClicked() {
    if (controller.setGameMode("G") == false) {
      JOptionPane.showMessageDialog(this,
              "Please choose board size before selecting a game mode",
              "No Board Size",
              JOptionPane.WARNING_MESSAGE);
      simpleGeneralGroup.clearSelection();
    }
  }

  public void boardSizeEntered() {
    String sizeText = boardSizeInput.getText().trim();
    if (sizeText.isEmpty() == false) {
      try {
        int size = Integer.parseInt(sizeText);
        if (controller.setBoardSize(size) == false) {
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
  }

  public void endOfGameMessage() {
    String result = controller.getGameResult();
    String message = null;

    if (result.equals("D")) {
      message = "This game will end in a draw!";
    } else if (result.equals("BV")) {
      message = "The Blue Player has won!";
    } else if (result.equals("RV")) {
      message = "The Red Player has won!";
    }

    JOptionPane.showMessageDialog(this, message, "Game Over",
        JOptionPane.INFORMATION_MESSAGE);
  }
}