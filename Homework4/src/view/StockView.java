package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * View for stock controller. Handles all output and GUI.
 */
public class StockView extends AView {
  private JTextField input;
  private JTextArea output;
  Consumer<String> commandCallback;

  /**
   * Sets up an interface with all text at the top, an input text box at the bottom and a
   * run and quit button to the right.
   */
  public StockView() {
    super();
    JPanel buttonPanel;
    JButton runButton;
    JButton quitButton;
    this.setTitle("Portfolio Manager");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(30);
    buttonPanel.add(input);

    runButton = new JButton("Enter");
    runButton.addActionListener((ActionEvent e) -> {
      if (commandCallback != null) { //if there is a command callback
        commandCallback.accept(input.getText()); //send command to be processed
        input.setText(""); //clear the input text field
      }
    });
    buttonPanel.add(runButton);

    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);



    output = new JTextArea();
    this.add(output, BorderLayout.CENTER);
  }

  @Override
  public void clear() {
    output.setText("");
  }

  @Override
  public void appendOutput(String output) {
    this.output.append(output);
  }

  @Override
  public void setCommandCallback(Consumer<String> commandCallback) {
    this.commandCallback = commandCallback;
  }

}
