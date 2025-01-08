package controller;

import view.StockView;

/**
 * Controller.Controller for testing the program.
 */
public class MockController implements Controller {
  private StockView view;

  /**
   * Constructor.
   */
  public MockController() {
    this.view = new StockView();
  }

  /**
   * This runs a program.
   */
  public void run() {
    this.view.setCommandCallback(this::accept);
    this.view.makeVisible();
  }

  /**
   * This is a default command function that the user inputs a command.
   * @param command command
   * @return the command
   */
  public String processCommand(String command) {
    return command;
  }

  /**
   * This makes sure the view accepts user input correctly.
   * @param s command
   */
  public void accept(String s) {
    String command = s;
    String status = "";

    try {
      status = processCommand(command);
    } catch (Exception ex) {
      view.showErrorMessage(ex.getMessage());
    }
    view.appendOutput(command);
    view.refresh();
  }
}

