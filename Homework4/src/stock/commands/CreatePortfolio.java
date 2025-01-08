package stock.commands;

import model.Model;

/**
 * Facilitates creating a portfolio from controller to model.
 */
public class CreatePortfolio implements StockCommand {

  @Override
  public void run(Model m) {
    m.createPortfolio();
  }

  @Override
  public String message() {
    return "A new portfolio has been created";
  }
}
