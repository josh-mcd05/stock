package stock.commands;

import model.Model;

/**
 * Interface for a possible command that a user can run from the controller.
 */
public interface StockCommand {

  /**
   * Executes this command.
   */
  public void run(Model m);

  /**
   * Gives a message about success of command.
   * @return the message
   */
  public String message();
}
